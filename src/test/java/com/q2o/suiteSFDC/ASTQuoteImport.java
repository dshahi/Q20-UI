package com.q2o.suiteSFDC;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.q2o.base.TestBase;
import com.q2o.util.Constant;
import com.q2o.util.TestDataProvider;

public class ASTQuoteImport extends TestBase{
	  @Test(dataProviderClass=TestDataProvider.class,dataProvider="suiteSFDCDataProvider")
	  public void astQuoteImport(Hashtable<String,String> table) throws InterruptedException {
		    validateRunmodes(this.getClass().getSimpleName(),Constant.Second_Suite, table.get(Constant.TestRummodeColumnName));
		  
		        loginToSFDC(table.get(Constant.Browser_ColName),table.get(Constant.URL_COL),table.get(Constant.USER_ID_COL),table.get(Constant.PASSWORD_COL));
				
				  
			    createSFDCOpty(table.get(Constant.DEALNAME_COL),table.get(Constant.ACCOUNTNAME_COL),table.get(Constant.EXPECTED_PROD_AMT_COL),
						      table.get(Constant.STAGE_COL),table.get(Constant.INSTALLBASE_COL),table.get(Constant.FORCASTING_POS_COL)
						      ,table.get(Constant.COMP1_COL),table.get(Constant.COMP2_COL),table.get(Constant.PARTNER_COMPANY_COL));
				  
				
			    createQuoteFromSFDCOpty(table.get(Constant.INTENDEDUSE_COL), table.get(Constant.PRICELIST_COL), table.get(Constant.DEALCATEGORY_COL), table.get(Constant.BUYMETHOD_COL), table.get(Constant.PARTNER_COMPANY_COL));
				
				  WaitForElement("importSavedConfigLink_xpath");
				  clickAndWaitForElement("importSavedConfigLink_xpath","importConfigurationHeader_xpath");
				  clickAndWaitForElement("importAsOption_xpath","asQuoteInfoMeesage_xpath");
				  input("searchASQuoteBox_id",table.get(Constant.ASTQUOTE_COL));
				  ccwDropdown("ASPTDropdown_xpath",table.get(Constant.ASPTQUOTETYPE_COL));
				  click("searchASPTQuoteBtn_xpath");
				  
				  if(isElementPresent("searchResultTableRow_xpath",10)){
					  
					  click("serachResultFirstRadioButton_xpath");
					  WaitForElement("continueBtnASPTPage_xpath");
					  click("continueBtnASPTPage_xpath");
					  waitForPageLoad();
					  boolean importASTsku=isElementPresent("ItemPageTableFirstData_xpath",10);
					  Assert.assertTrue(importASTsku,"Import of AST Quote unssuccessful");
					  
					  
				  }
				  else
					  Assert.fail("No Quote Found in serach Result");
				 
				  
		    
	  }

}
