package com.q2o.suiteCCW;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.q2o.base.TestBase;
import com.q2o.util.Constant;
import com.q2o.util.TestDataProvider;


public class BehaviourDeal extends TestBase{
	WebDriver driver=null;


	  @Test(dataProviderClass=TestDataProvider.class,dataProvider="suiteCCWDataProvider")
	  public void behaviourDeal(Hashtable<String,String> table) {
		 
		    validateRunmodes(this.getClass().getSimpleName(),Constant.First_Suite, table.get(Constant.TestRummodeColumnName));
		    ccwLogin(table.get(Constant.Browser_ColName), table.get(Constant.URL_COL), table.get(Constant.USER_ID_COL), table.get(Constant.PASSWORD_COL));
            clickOnSubmenu("dealQuotesManu_xpath", "createDealSubmenu_xpath");
            waitForPageLoad();
            input("dealName_xpath",table.get(Constant.DEALNAME_COL));
            ccwDropdown("selectCAM_xpath",table.get(Constant.CAM_COL));
            selectRecentEndcustomer();
            customerContactDetails();
            selectPartnerContact();
            click("createDealBtn_xpath");
            waitForPageLoad();
		

           //closeBrowser();
            
		    
		   
		   
		  
	
		
	  }
}
