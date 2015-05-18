package com.q2o.roughwork;

import com.q2o.util.Xls_Reader;

public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ccwSuitePath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\q2o\\xls\\suiteCCW.xlsx";
		Xls_Reader xls=new Xls_Reader(ccwSuitePath);
		
		System.out.println(isTestRunnable(xls,"BehaviourDeal"));

	}

	
	public static boolean isTestRunnable(Xls_Reader xls,String testCaseName){
		
		int rows=xls.getRowCount("TestCases");
		
		for(int i=2;i<=rows;i++){
        	  
        	String testNameInExcel=xls.getCellData("TestCases", "TestCases_Name", i);
        	String runmode=xls.getCellData("TestCases", "Runmodes", i);
        	
        	if(testCaseName.equalsIgnoreCase(testNameInExcel)){
        		
        		if(runmode.equalsIgnoreCase("Y"))
        			 return true;
        		else
        			return false;
        	}
        
         }
		
		return false;
		
		}
}
