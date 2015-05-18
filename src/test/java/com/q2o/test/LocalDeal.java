package com.q2o.test;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver; 
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.q2o.util.ExcelUtil;
import com.q2o.util.Xls_Reader;

public class LocalDeal {
	

 @DataProvider
 public Object[][] fetchData(){
	 
	 Xls_Reader xls=new Xls_Reader("C:\\Users\\dshahi\\Desktop\\mvn\\DataDrivenFrameWork\\src\\test\\java\\com\\q2o\\xls\\suiteCCW.xlsx");
	 return  ExcelUtil.getTestData(xls, this.getClass().getSimpleName());
 }
	
  @Test(dataProvider="fetchData")
  public void test1(Hashtable<String,String> table) {
	  
	  System.out.println(table.get("Runmodes"));
	  System.out.println(table.get("userid"));
	  System.out.println(table.get("password"));
  }
  
  @Test
  public void  test2(){

	   System.out.println("test2");
	  
  }
  
  @Test
  public void test3(){
	  
	 throw  new SkipException("skiiping test 3");
  }
}
