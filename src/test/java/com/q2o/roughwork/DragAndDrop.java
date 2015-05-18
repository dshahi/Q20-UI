package com.q2o.roughwork;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DragAndDrop {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver=new FirefoxDriver();
		
		driver.get("http://dhtmlx.com/docs/products/dhtmlxTree/");
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	    WebElement source=driver.findElement(By.xpath("//*[@id='treebox1']/div/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[4]/span"));
	    WebElement target=driver.findElement(By.xpath("//*[@id='treebox2']/div/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[4]/span"));
		
	   int x=source.getLocation().x;
	   int y=target.getLocation().y;
	  
	   System.out.println(x+"   "+y);
		Actions act=new Actions(driver);

	   act.clickAndHold(source).moveToElement(target).release(target).build().perform();

		

		
	}

}
