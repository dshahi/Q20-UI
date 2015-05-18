package com.q2o.suiteSFDC;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.q2o.base.TestBase;
import com.q2o.util.Constant;
import com.q2o.util.ExcelUtil;
import com.q2o.util.TestDataProvider;
import com.q2o.util.Xls_Reader;

public class RNSDQuote extends TestBase {
	

		
	  @Test(dataProviderClass=TestDataProvider.class,dataProvider="suiteSFDCDataProvider")
	  public void rnsdQuote(Hashtable<String,String> table) throws InterruptedException {
		    validateRunmodes(this.getClass().getSimpleName(),Constant.Second_Suite, table.get(Constant.TestRummodeColumnName));
		  
	
		    loginToSFDC(table.get(Constant.Browser_ColName),table.get(Constant.URL_COL),table.get(Constant.USER_ID_COL),table.get(Constant.PASSWORD_COL));
			  waitForPageLoad();
			  
			 createSFDCOpty(table.get(Constant.DEALNAME_COL),table.get(Constant.ACCOUNTNAME_COL),table.get(Constant.EXPECTED_PROD_AMT_COL),
					      table.get(Constant.STAGE_COL),table.get(Constant.INSTALLBASE_COL),table.get(Constant.FORCASTING_POS_COL)
					      ,table.get(Constant.COMP1_COL),table.get(Constant.COMP2_COL),table.get(Constant.PARTNER_COMPANY_COL));
			  
			  
			  click("newQuoteBtn_xpath");
			  waitForPageLoad();
			  ccwDropdown("intendeduse_xpath",table.get(Constant.INTENDEDUSE_COL));
			  ccwDropdown("pricelist_xpath",table.get(Constant.PRICELIST_COL));
			  click("transactionalQuoteRadionBtn_xpath");
			  ccwDropdown("dealCategory_xpath",table.get(Constant.DEALCATEGORY_COL));
			  ccwDropdown("buyMethod_xpath",table.get(Constant.BUYMETHOD_COL));
			  moveMouseToElement("partnerAddressSection_xpath");
			  ccwDropdown("selectPartnerDropdown_xpath",table.get(Constant.PARTNER_COMPANY_COL));
			  WaitForElement("createDealBtnSFDC_xpath");
			  click("createDealBtnSFDC_xpath");
			  waitForPageLoad();
			  WaitForElement("importSavedConfigLink_xpath");
			  clickAndWaitForElement("importSavedConfigLink_xpath","importConfigurationHeader_xpath");
			  clickAndWaitForElement("importAsOption_xpath","asQuoteInfoMeesage_xpath");
			  input("searchASQuoteBox_id",table.get(Constant.ASTQUOTE_COL));
			  ccwDropdown("ASPTDropdown_xpath",table.get(Constant.ASPTQUOTETYPE_COL));
			  click("searchASPTQuoteBtn_xpath");
			  
			  boolean result=isElementPresent("searchResultASPTQUOTE_xpath",5);
			 
			  Assert.assertTrue(result, "No Result Found for AST Quote Search");
		
	  }
}


