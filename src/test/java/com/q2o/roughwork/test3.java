package com.q2o.roughwork;

import com.q2o.util.Constant;
import com.q2o.util.Xls_Reader;

public class test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String testcaseName="LocalDeal";
		
		Xls_Reader xls=new Xls_Reader("C:\\Users\\dshahi\\Desktop\\mvn\\DataDrivenFrameWork\\src\\test\\java\\com\\q2o\\xls\\suiteCCW.xlsx");
		
         int testcaseRowNum=1;
         
           for(int rowNum=1;rowNum<=xls.getRowCount(Constant.DATA_SHEET);rowNum++){
        	 String  colVal= xls.getCellData(Constant.DATA_SHEET, 0, rowNum);
        	  if(colVal.equalsIgnoreCase(testcaseName))
        	   break;
        	  else
        		  testcaseRowNum++;
        	  
        }
         
         System.out.println(testcaseRowNum);
         
         int colnameRow=testcaseRowNum+1;
         int rowDatastartNum=testcaseRowNum+2;
         
         System.out.println(rowDatastartNum);
         int rowsNums=testcaseRowNum+2;
         int datarows=0;
         
         while(true){
        	String runmodeColval=xls.getCellData(Constant.DATA_SHEET, 0, rowsNums++);
        	 if(runmodeColval.equals(""))
        	   break;
        	 else 
        		 datarows++;
          }
        
         
    System.out.println(datarows);
    int dataCol=0;
     
     while(true){
    	 String columnsName=xls.getCellData(Constant.DATA_SHEET, dataCol, colnameRow);
    		if(columnsName.equals(""))
    			break;
    		else
    			dataCol++;
    	 }
    
     System.out.println(dataCol);
     
     Object[][] data=new Object[datarows][dataCol];
     int rowsN=0;
     for(int row=rowDatastartNum;row<rowDatastartNum+datarows;row++){
    	for(int col=1;col<dataCol;col++){
    		 System.out.println(rowsN+" "+(col-1));
    	   data[rowsN][col-1]=xls.getCellData(Constant.DATA_SHEET, col, row);
    	 }
    	 rowsN++;
     }
     
     
     
        	
       
	}
	
	
       

	}


