package com.q2o.roughwork;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Slider {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		WebDriver driver=new FirefoxDriver();
		
		driver.get("https://jqueryui.com/slider/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		//List<WebElement> silde=driver.findElements(By.tagName("iframe"));
		////iframe[@class='demo-frame']
		
		driver.switchTo().frame(0);
		
		WebElement element=driver.findElement(By.xpath("//*[@id='slider']/span"));
		
		int x=element.getLocation().x;
		int y=element.getLocation().y;
		
		System.out.println(x+"  "+y);
		
		Actions act=new Actions(driver);
		
		act.dragAndDropBy(element, 100, 0).build().perform();
		
		int x1=element.getLocation().x;
		int y1=element.getLocation().y;
		
		System.out.println(x1+"  "+y1);
		
		act.dragAndDropBy(element,-50, 0).build().perform();;
		
		int x2=element.getLocation().x;
		int y2=element.getLocation().y;
		
		System.out.println(x2+"  "+y2);
		//int xOffset=element.getLocation().x;
		
		//act.clickAndHold(element).dragAndDropBy(element, xOffset,1000).build().perform();;
		
	
	}

}
