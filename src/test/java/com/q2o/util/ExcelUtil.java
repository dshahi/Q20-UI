package com.q2o.util;

import java.util.Hashtable;

public class ExcelUtil {

	
	public static boolean  isSuiteRunnable(Xls_Reader xls,String suitename){
		
		int rows=xls.getRowCount(Constant.Suite_Sheet);
		
		for(int i=2;i<=rows;i++){
        	  
        	String suiteNameInExcel=xls.getCellData(Constant.Suite_Sheet, Constant.SuiteNameCoLumn, i);
        	String runmode=xls.getCellData(Constant.Suite_Sheet, Constant.SuiteRummodeColumnName, i);
        	
        	if(suitename.equalsIgnoreCase(suiteNameInExcel)){
        		
        		if(runmode.equalsIgnoreCase(Constant.RUNMODE_YES))
        			 return true;
        		else
        			return false;
        	}
        
         }
		
		return false;
	}
	
	public static boolean isTestRunnable(Xls_Reader xls,String testCaseName){
		
		int rows=xls.getRowCount(Constant.TestcaseSheetName);
		
		for(int i=2;i<=rows;i++){
        	  
        	String testNameInExcel=xls.getCellData(Constant.TestcaseSheetName, Constant.TestCasesNameCoLumn, i);
        	String runmode=xls.getCellData(Constant.TestcaseSheetName, Constant.TestRummodeColumnName, i);
        	
        	if(testCaseName.equalsIgnoreCase(testNameInExcel)){
        		
        		if(runmode.equalsIgnoreCase(Constant.RUNMODE_YES))
        			 return true;
        		else
        			return false;
        	}
        
         }
		
		return false;
		
		}
	
	
public static Object[][] getData(Xls_Reader xls,String testcaseName){
		int testcaseRowNum=1;
        
        for(int rowNum=1;rowNum<=xls.getRowCount(Constant.DATA_SHEET);rowNum++){
     	 String  colVal= xls.getCellData(Constant.DATA_SHEET, 0, rowNum);
     	  if(colVal.equalsIgnoreCase(testcaseName))
     	   break;
     	  else
     		  testcaseRowNum++;
     	  
     }
      

      
      int colnameRow=testcaseRowNum+1;
      int rowDatastartNum=testcaseRowNum+2;
      
   
      int rowsNums=testcaseRowNum+2;
      int datarows=0;
      
      while(true){
     	String runmodeColval=xls.getCellData(Constant.DATA_SHEET, 0, rowsNums++);
     	 if(runmodeColval.equals(""))
     	   break;
     	 else 
     		 datarows++;
       }
     
      

 int dataCol=0;
  
  while(true){
 	 String columnsName=xls.getCellData(Constant.DATA_SHEET, dataCol, colnameRow);
 		if(columnsName.equals(""))
 			break;
 		else
 			dataCol++;
 	 }
 
  Object[][] data=new Object[datarows][dataCol-1];
  int rowsN=0;
  for(int row=rowDatastartNum;row<rowDatastartNum+datarows;row++){
 	 for(int col=1;col<dataCol;col++){
 		 
 		data[rowsN][col-1]=xls.getCellData(Constant.DATA_SHEET, col, row);
 	
 	 }
 	 rowsN++;
 	
  }
  
   return data;
		
	}

public static Object[][] getTestData(Xls_Reader xls,String testcaseName){
	
	int testcaseRowNum=1;
    
    for(int rowNum=1;rowNum<=xls.getRowCount(Constant.DATA_SHEET);rowNum++){
 	 String  colVal= xls.getCellData(Constant.DATA_SHEET, 0, rowNum);
 	  if(colVal.equalsIgnoreCase(testcaseName))
 	   break;
 	  else
 		  testcaseRowNum++;
 	  
 }
  

  
  int colnameRow=testcaseRowNum+1;
  int rowDatastartNum=testcaseRowNum+2;
  

  int rowsNums=testcaseRowNum+2;
  int datarows=0;
  
  while(true){
 	String runmodeColval=xls.getCellData(Constant.DATA_SHEET, 0, rowsNums++);
 	 if(runmodeColval.equals(""))
 	   break;
 	 else 
 		 datarows++;
   }
 
  

int dataCol=0;

while(true){
	 String columnsName=xls.getCellData(Constant.DATA_SHEET, dataCol, colnameRow);
		if(columnsName.equals(""))
			break;
		else
			dataCol++;
	 }
	
	
Object[][] data=new Object[datarows][1];
int rowsN=0;
for(int row=rowDatastartNum;row<rowDatastartNum+datarows;row++){
	Hashtable<String,String> table=new Hashtable<String,String>();
	 for(int col=0;col<dataCol;col++){
		  //data[rowsN][col-1]=xls.getCellData(Constant.DATA_SHEET, col, row);
		  table.put(xls.getCellData(Constant.DATA_SHEET, col, colnameRow), xls.getCellData(Constant.DATA_SHEET, col, row));
	 }
	 
	 data[rowsN][0]=table;
	 rowsN++;
	
}
	
	return data;
}

}
