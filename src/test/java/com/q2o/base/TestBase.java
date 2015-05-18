package com.q2o.base;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;

import com.q2o.util.Constant;
import com.q2o.util.ExcelUtil;
import com.q2o.util.Xls_Reader;



public class TestBase  {
	
	public static  Logger app_log=Logger.getLogger("devpinoyLogger");
	public WebDriver driver=null;
	public Properties prop=null;
	
	public void intialize(){
		
		prop=new Properties();
		try {
			FileInputStream fs=new FileInputStream(Constant.PROPERTIES_FILE_PATH);
			prop.load(fs);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

       public void validateRunmodes(String testname,String suiteName,String dataRunMode){
    	  
    	   if(prop==null)
    	      intialize();
    	   
    	 
		app_log.debug("Validating Run Mode of test "+testname+" in "+suiteName);
	    Xls_Reader xls=new Xls_Reader(Constant.XLS_FILE_LOCATION+Constant.Suite_XLS_File);
		Xls_Reader xls1=new Xls_Reader(Constant.XLS_FILE_LOCATION+suiteName+".xlsx");
		boolean suiteRunmode=ExcelUtil.isSuiteRunnable(xls, suiteName);
	    boolean testRunmode=ExcelUtil.isTestRunnable(xls1, testname);
	    boolean datsetRunmode=false;
	    if(dataRunMode.equalsIgnoreCase(Constant.RUNMODE_YES))
	    	datsetRunmode=true;
	    
	   if(!(suiteRunmode && testRunmode && datsetRunmode)){
		    app_log.debug("Skipping test case :"+testname+" - becuase  Run Mode of Either test Suite ,Test Case or Data are Set as No");
	    	throw new SkipException("Skipping test :"+testname+" - becuase  Run Mode of Either test Suite ,Test Case or Data are Set as No");
	   }
	}
       
    public void openBrowser(String browsername)  {
    	
    	try{
    	
    	if(browsername.equalsIgnoreCase("Mozilla")){
    		FirefoxProfile profile=new FirefoxProfile();
    		profile.setEnableNativeEvents(true);
    		driver=new FirefoxDriver(profile);
    		
    	}
    	else if(browsername.equalsIgnoreCase("Chrome")){
    		System.setProperty("webdriver.chrome.driver", Constant.CHROMEDRIVER_EXE_PATH);
    		driver=new ChromeDriver();
    		
    	}
    	else if(browsername.equalsIgnoreCase("IE")){
    		System.setProperty("webdriver.ie.driver", Constant.IEDRIVER_EXE_PATH);
    		driver=new InternetExplorerDriver();
    		
    	}
    	else
    		System.out.println(browsername+Constant.BROWSER_INCORRECT);
    	    app_log.debug(browsername+Constant.BROWSER_INCORRECT);
    	
       }
    	catch(Exception e){
    		Assert.fail(browsername+Constant.BROWSER_ERROR);
    	}
    	
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
    	
    }
    
    public  void closeBrowser(){
    	driver.quit();
    	
    }
    
    
    public void navigateCCW(String url){
    	try{
    	driver.get(url);
    	String pagetile=driver.getTitle();
    	Assert.assertEquals(pagetile, Constant.LoginPage_tile);
    	
    	}
    	catch(Exception e){
    		
    		Assert.fail(Constant.NAVIGATION_ERROR);
    		
    	}
    }
    
    public void navigateSFDC(String url){
    	try{
    	driver.get(url);
    	String pagetile=driver.getTitle();
    	Assert.assertEquals(pagetile, Constant.LOGINPAGE_TITLE);
    	
    	}
    	catch(Exception e){
    		
    		Assert.fail(Constant.NAVIGATION_ERROR);
    		
    	}
    }
    public WebElement element(String objectkey){
    	try{
    		if(objectkey.endsWith("_id"))
    		 return driver.findElement(By.id(prop.getProperty(objectkey)));
    		else if(objectkey.endsWith("_xpath"))
    		 return driver.findElement(By.xpath(prop.getProperty(objectkey)));
    		else if(objectkey.endsWith("_name"))
    		 return driver.findElement(By.name(prop.getProperty(objectkey)));
    		else if(objectkey.endsWith("_classname"))
    		  return driver.findElement(By.className(prop.getProperty(objectkey)));
    		else if(objectkey.endsWith("_linktext"))
    		  return driver.findElement(By.linkText(prop.getProperty(objectkey)));
    		else if(objectkey.endsWith("_tagname"))
    		  return driver.findElement(By.tagName(prop.getProperty(objectkey)));
    		else if(objectkey.endsWith("_partiallinktext"))
    		  return driver.findElement(By.partialLinkText(prop.getProperty(objectkey)));
    		else {
    			return driver.findElement(By.xpath(objectkey));
    			
    			//Assert.fail(Constant.INVALID_SELECTOR);
    		}
    		}
    		catch(InvalidSelectorException e){
    			Assert.fail(Constant.INVALID_SELECTOR_EXCEPTION+objectkey);
    			
    		}
    		catch(NoSuchElementException e){
    			Assert.fail(Constant.ELEMENT_NOT_FOUND+objectkey);
    		}
    		
    		 return null;
    	
    	
    }
    
    public void input(String objectkey,String value){
    	
    	element(objectkey).sendKeys(value);
    	
    }
    
    public void click(String objectkey){
    	element(objectkey).click();
    	
    }
    public void clear(String objectkey){
    	 element(objectkey).clear();
    }
    
    public void clickOnSubmenu(String mainmenu_objectkey,String submenu_objectkey){
    	
    	Actions act=new Actions(driver);
    	act.moveToElement(element(mainmenu_objectkey)).perform();
    	act.click(element(submenu_objectkey)).perform();
    	
    }
    
    public boolean verifyTitle(String expectedTitle){
    	
    	String actualTitle=driver.getTitle();
    	if(expectedTitle.equals(actualTitle))
    		return true;
    	else 
    		return false;
    
    	
    }
    public boolean isElementPresentxpath(String objectXpath,int timeout){
    	driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    	List<WebElement> elements=driver.findElements(By.xpath(objectXpath));
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    			
    	  if(elements.size()==0)
    		  return false;
    	  else 
    		  return true;
    	
    }
	public boolean isElementPresent(String objectkey,int timeout){
		int size=0;
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		if(objectkey.endsWith("_id"))
		   size=driver.findElements(By.id(prop.getProperty(objectkey))).size();
		else if(objectkey.endsWith("_xpath"))
			 size=driver.findElements(By.xpath(prop.getProperty(objectkey))).size();
		else if(objectkey.endsWith("_linktext"))
			size=driver.findElements(By.linkText(prop.getProperty(objectkey))).size();
		if(size==0)
			return false;
		else
			return true;

	}
	
	public String getText(String objectkey){
		
	   return element(objectkey).getText();
		
	}
	
	public void clickAndWaitForElement(String objectkeytoClick,String objecttodisplay) throws InterruptedException{
		
	   click(objectkeytoClick);
	   
	   for(int i=0;i<5;i++){
		   
		   if(isElementPresent(objecttodisplay,4) && element(objecttodisplay).isDisplayed()){
			   return;
		   }
		   else{
			   
			   Thread.sleep(2000);
		   }
	   }
		
	   Assert.fail(Constant.FAIL_TO_FOUND_DISPLAY+objecttodisplay);
	}
	public void WaitForElement(String objecttodisplay) throws InterruptedException{
		
		 for(int i=0;i<5;i++){
			   
			   if(isElementPresent(objecttodisplay,4) && element(objecttodisplay).isDisplayed()){
				   return;
			   }
			   else{
				   
				   Thread.sleep(2000);
			   }
		   }
			
		   Assert.fail(Constant.FAIL_TO_FOUND_DISPLAY+objecttodisplay);
		}
		
	

	public void waitForPageLoad(){
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		while(!js.executeScript("return document.readyState").toString().equals("complete")){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	public void selectfromDropdown(String objectkey,String valueToselect){
	
	Select sel=new Select(element(objectkey));
	sel.selectByVisibleText(valueToselect);
	
	}
	
	public void dobleClick(String objectkey){
		 Actions act=new Actions(driver);
		act.moveToElement(element(objectkey)).doubleClick().build().perform();
		
		
	}
	
	
	public void moveMouseToElement(String objectkey){
		Actions act=new Actions(driver);
		act.moveToElement(element(objectkey)).perform();
		
	}
	
	public void captureScreenshot(String testCaseName){
		
		  File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile,new File(Constant.CapturedScreeshotPath+testCaseName+".jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void uploadFile(String path){
		
		try {
			Process process=new ProcessBuilder(Constant.AUTOITEXEFILE_LOCAION,path,"open").start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void dragAndDropElement(String objectkey,int xoffset,int yoffset){
		
		Actions act=new Actions(driver);
		act.dragAndDropBy(element(objectkey), xoffset, yoffset).build().perform();;
		
	}
	
	public void dragAndDropElement(String sourceObjectkey,String targetObjectKey){
		
		Actions act=new Actions(driver);
		act.dragAndDrop(element(sourceObjectkey),element(targetObjectKey)).build().perform();;
	}
	
	
	public String  decode(String encoded){
		byte[] decoded = Base64.decodeBase64(encoded);      
        return new String(decoded);
	}
	
	public void switchToWindow(){
		Set<String> windowsname=driver.getWindowHandles();
	    Iterator<String> it=windowsname.iterator();
		String mainwindow=it.next();
		String firstpopwindow=it.next();
		driver.switchTo().window(firstpopwindow);
	}
	
	public void switchToFrame(String framename){
		driver.switchTo().frame(framename);
	}
	
	/*******************Application Specific************************************/
	
	public void ccwLogin(String browsername,String URL,String userid,String passowrd){
		
        openBrowser(browsername);
        navigateCCW(URL);
        input("username_id",userid);
        input("passowrd_id",decode(passowrd));
        click("loginbtn_id");
        Assert.assertTrue(verifyTitle(Constant.WorkspaceHomePage),"Log-in unsuccessfull");
	}
	
	public void ccwDropdown(String objectkey,String valueTobeselected){
		
		String  xpath="//a[@title='"+valueTobeselected+"']";
		if(isElementPresentxpath(xpath,4))
			 return;
		 else {
			 
			 click(objectkey);
			 driver.findElement(By.linkText(valueTobeselected)).click();
		 }
		
	  Assert.assertTrue(isElementPresentxpath(xpath,3), "Unable to select value from drop down");

	}
	

	
	public void fastercustomersearch(String objectkey,String endcustomer){
		
	    String xpath="//a[text()='"+endcustomer+"']";
		for(int i=0;i<endcustomer.length();i++){
			
			input(objectkey,Character.toString(endcustomer.charAt(i)));
			
			if(isElementPresentxpath(xpath,3)){
				
				click(xpath);
				break;
			}
		}
		
		
		
	}
	

	
	public void selectRecentEndcustomer(){
		moveMouseToElement("endCustomerSection_xpath");
     try {
			clickAndWaitForElement("englishCustomerSearch_xpath","viewRecentCustomerlink_linktext");
	        clickAndWaitForElement("viewRecentCustomerlink_linktext","selectEndcustomerHeader_xpath");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!isElementPresent("noRecentCustomerRecord_xpath",4))
			click("slectCustomerBtn_xpath");
		else
			Assert.fail("No Recent End Customer Record");
		
		
    
	}
    
	public void customerContactDetails(){
		input("customerContactFirstName_xpath",Constant.DEFFAULT_VALUE);
		input("customerContactLastName_xpath",Constant.DEFFAULT_VALUE);
		input("customerContactTitle_xpath",Constant.DEFFAULT_VALUE);
		input("customerContactPhoneNumber_xpath",Constant.DEFFAULT_VALUE);
		input("customerContactEmail_xpath",Constant.DEFFAULT_VALUE);
		input("customerCompanyURL_xpath",Constant.DEFFAULT_VALUE);
	}
	
	public void selectPartnerContact() {
		clickOnSubmenu("partnerSection_xpath","partnerContactCheckbox_xpath");
		
	}
	
	
	
	/********************SFDC Application Specific*********************************/
	
	
	public void loginToSFDC(String browser,String url,String userid,String pwd){
		  openBrowser(browser);
		  navigateSFDC(url);
		  input("username_xpath",userid);
		  input("password_xpath",pwd);
		  click("logintosfdcBtn_xpath");
		//  Assert.assertTrue(verifyTitle(Constant.SFDCHOMEPAGE_TITLE),"Login unsuccessfull");
		
	}
	
	public void selectSFDCAccount(String accountname){
		String xpathexp="//strong[text()='"+accountname+"']";
		input("accountName_id",accountname);
	    click(xpathexp);
	
	}
	
	public void selectSFDCAccountFromLookUp(String accountname) throws InterruptedException{
		
		click("accountLookUpIcon_xpath");
		Set<String> windowsname=driver.getWindowHandles();
	    Iterator<String> it=windowsname.iterator();
		String mainwindow=it.next();
		String firstpopwindow=it.next();
		driver.switchTo().window(firstpopwindow);
		boolean isdisplayed=verifyTitle(Constant.ACCOUNTPOPUP_TITLE);
		
		if(isdisplayed){
			 
			driver.switchTo().frame("searchFrame");
		
	        input("searchAccountinLooup_xpath",accountname);
		
		    click("goBtninLookUp_xpath");
	         
	       driver.switchTo().defaultContent();
	         
	       driver.switchTo().frame("resultsFrame");
			 
            if(isElementPresent("firstResultAccountSearch_xpath",10))
     	        click("firstResultAccountSearch_xpath");
            else
            	Assert.fail("No Result for Given Account");
				 
			
		 }
		 else
			 Assert.fail("Account search pop up not dispalyed");
		
		driver.switchTo().window(mainwindow);
		
	}
	


	public void selectSFDCPartner(String accountname){
		String xpathexp="//table/tbody/tr[2]/td/div/strong[text()='"+accountname+"']";
		input("primaryPartner_id",accountname);
	    click(xpathexp);
	
	}
	
	
	public void selecCompetitor(String name){
		String xpathExpression="//select[@title='Competitors - Available']/option[text()='"+name+"']";
		if(isElementPresentxpath(xpathExpression,15))
		  click(xpathExpression);
		else {
			Assert.fail("Element Not Found"+xpathExpression);
		}
			
		click("competitorRighArrow_xpath");
		
		
	}
	
	public void createSFDCOpty(String dealname,String account,String expAmout,String stage,String installbase,String forecaset ,String comp1,String comp2,String partner){
		 try{
		  clickAndWaitForElement("opportunityTab_xpath","newOptyBtn_xpath");
		  clickAndWaitForElement("newOptyBtn_xpath","optyContinueBtn_xpath");
		  clickAndWaitForElement("optyContinueBtn_xpath","newOptyPageHeader_xpath");
		  waitForPageLoad();
		  input("optyName_id",dealname);
		 // selectSFDCAccount(account);
		  selectSFDCAccountFromLookUp(account);
		  click("expDatelink_xpath");
		  input("expProdAmount_id",expAmout);
		  selectfromDropdown("stagedropdown_id",stage);
		  selectfromDropdown("installbase_id",installbase);
		  selectfromDropdown("forcastingPosition_id",forecaset);
		  selecCompetitor(comp1);
		  selecCompetitor(comp2);
		  selectSFDCPartner(partner);
		  clickAndWaitForElement("saveButtonUpperRow_xpath","technologyHeader_xpath");
		  clickAndWaitForElement("AddTechnologyBtn_xpath","technologyPopup_xpath");
		  click("cloudtech_id");
		  clickAndWaitForElement("AddBtnTech_xpath","techMixPer_xpath");
		  clear("techMixPer_xpath");
		  Thread.sleep(5000);
		  input("techMixPer_xpath",Constant.TECHMIXDEFAULT);
		  clickAndWaitForElement("tectSaveBtn_xpath","newdealIDSection_xpath");
		  Thread.sleep(2000);
		  waitForPageLoad();
		 }
		  catch(Exception e){
			  Assert.fail("Opty Creation failed");
		  }
	}
	
	
	public void createQuoteFromSFDCOpty(String intedendeduse,String priceList,String dealCategory,String buymenthod,String partnercompany) throws InterruptedException{
		
		  click("newQuoteBtn_xpath");
		  waitForPageLoad();
		  ccwDropdown("intendeduse_xpath",intedendeduse);
		  ccwDropdown("pricelist_xpath",priceList);
		  click("transactionalQuoteRadionBtn_xpath");
		  ccwDropdown("dealCategory_xpath",dealCategory);
		  ccwDropdown("buyMethod_xpath",buymenthod);
		  moveMouseToElement("partnerAddressSection_xpath");
		  ccwDropdown("selectPartnerDropdown_xpath",partnercompany);
		  WaitForElement("createDealBtnSFDC_xpath");
		  click("createDealBtnSFDC_xpath");
		  waitForPageLoad();
		
	}
    

}
