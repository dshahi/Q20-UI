package com.q2o.suiteCCW;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.q2o.base.TestBase;
import com.q2o.util.Constant;
import com.q2o.util.ExcelUtil;
import com.q2o.util.TestDataProvider;
import com.q2o.util.Xls_Reader;

public class IronPortDeal extends TestBase {
	

		
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="suiteCCWDataProvider")
	  public void ironPortDeal(Hashtable<String,String> table) {
		   validateRunmodes(this.getClass().getSimpleName(),Constant.First_Suite, table.get(Constant.TestRummodeColumnName));
		 
	  }


}
