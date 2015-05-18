package com.q2o.util;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	@DataProvider(name="suiteCCWDataProvider")
	public static Object[][] fetchCCWTestData(Method m){
		
		 Xls_Reader xls=new Xls_Reader(Constant.XLS_FILE_LOCATION+Constant.First_Suite_Xls_File);
		 return  ExcelUtil.getTestData(xls,m.getName());
		
		

		
	}
	
	@DataProvider(name="suiteSFDCDataProvider")
	public static Object[][] fetchSFDCTestData(Method m){
		
		 Xls_Reader xls=new Xls_Reader(Constant.XLS_FILE_LOCATION+Constant.Second_Suite_Xls_File);
		 return  ExcelUtil.getTestData(xls,m.getName());
		
		

		
	}
}
