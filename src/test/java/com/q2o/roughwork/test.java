package com.q2o.roughwork;

import com.q2o.util.Xls_Reader;

public class test {
	
	public static void main(String args[]){
		String suitePath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\q2o\\xls\\Suites.xlsx";
		Xls_Reader xls=new Xls_Reader(suitePath);
		
		System.out.println(isSuiteRunnable(xls,"suiteSFC"));
		
		
	}
	
	public static boolean  isSuiteRunnable(Xls_Reader xls,String suitename){
		
		int rows=xls.getRowCount("Test_Suites");
		
		for(int i=2;i<=rows;i++){
        	  
        	String suiteNameInExcel=xls.getCellData("Test_Suites", "Test_Suites_Name", i);
        	String runmode=xls.getCellData("Test_Suites", "Runmode", i);
        	
        	if(suitename.equalsIgnoreCase(suiteNameInExcel)){
        		
        		if(runmode.equalsIgnoreCase("Y"))
        			 return true;
        		else
        			return false;
        	}
        
         }
		
		return false;
	}

}
