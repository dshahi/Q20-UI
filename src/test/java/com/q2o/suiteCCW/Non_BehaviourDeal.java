package com.q2o.suiteCCW;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.q2o.base.TestBase;
import com.q2o.util.Constant;
import com.q2o.util.TestDataProvider;


public class Non_BehaviourDeal extends TestBase {


	@Test(dataProviderClass=TestDataProvider.class,dataProvider="suiteCCWDataProvider")
	  public void non_behaviourDeal(Hashtable<String,String> table) throws InterruptedException {
		     validateRunmodes(this.getClass().getSimpleName(),Constant.First_Suite, table.get(Constant.TestRummodeColumnName));
		 
	
		     
		     ccwLogin(table.get(Constant.Browser_ColName), table.get(Constant.URL_COL), table.get(Constant.USER_ID_COL), table.get(Constant.PASSWORD_COL));
	            clickOnSubmenu("dealQuotesManu_xpath", "createQuoteSubmenu_xpath");
	            waitForPageLoad();
	            input("dealName_xpath",table.get(Constant.DEALNAME_COL));
	            ccwDropdown("selectCAM_xpath",table.get(Constant.CAM_COL));
	            selectRecentEndcustomer();
	            customerContactDetails();
	            selectPartnerContact();
	            click("createDealBtn_xpath");
	            waitForPageLoad();
	            WaitForElement("priceListabtDeal_xpath");
	          
	            ccwDropdown("priceListabtDeal_xpath",table.get(Constant.PRICELIST_COL));
	            ccwDropdown("intendedUseabtDeal_xpath",table.get(Constant.INTENDEDUSE_COL));
	            ccwDropdown("dealCategoryabtDeal_xpath",table.get(Constant.DEALCATEGORY_COL));
	           // Thread.sleep(2000);
	          
	            click("saveAndContinueBottomAbtDeal_xpath");
	            
	            
	  }
}
