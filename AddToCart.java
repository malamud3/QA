package pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import org.openqa.selenium.support.ui.Select;
import javax.naming.spi.DirStateFactory.Result;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.logging.log4j.*;
 
public class AddToCart {

	public static void addToCartTest(WebDriver driver,Logger logger)
	{
		//1 add fast item
		
		String last=driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).getAttribute("innerHTML");
		logger.debug(" TEST: Add to cart an product without special options");
    	driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/button[1]")).click();
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		if(driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).getAttribute("innerHTML").compareTo(last) > 0)
		{
			last=driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).getAttribute("innerHTML");
			logger.info("Add successfully: "+last);
		}
	else
		logger.error("Fail");
		
		//2 add the same again        
		logger.info(" TEST: add the same product again"); 

		driver.findElement(By.xpath("//*[@id=\"logo\"]/h1/a")).click();
		last=driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).getAttribute("innerHTML");
		 logger.debug("expected:"+"Add successfully");
    	driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/button[1]")).click();
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		if(driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).getAttribute("innerHTML").compareTo(last) > 0)
		{
			last=driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).getAttribute("innerHTML");
			logger.info("Add successfully: "+last);
		}
		else
			logger.error("Fail");		
		
		//3 add Product with options to choose 
		last=driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).getAttribute("innerHTML");
		logger.info(" TEST: add product with options to choose "); 
		logger.debug("expected:"+"Add successfully");
		driver.findElement(By.xpath("//*[@id=\"logo\"]/h1/a")).click(); // home page
    	driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[4]/div/div[3]/button[1]")).click();// camara
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 Select select;
		 select = new Select (driver.findElement(By.id("input-option226")));
		 List <WebElement> elementCount = select.getOptions();
		 if(select.getOptions().size()==1 &&elementCount.get(0).getText().equals("--- Please Select ---"))
		 {
		    logger.info("Add successfully: "+last);

		 }
		 else
				logger.error("Fail");		


        //4 add item we don't have in stock
			logger.info(" TEST: aadd item we don't have in stock "); 
		 last=driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).getAttribute("innerHTML");// get cart info
		    driver.findElement(By.xpath("//*[@id=\"logo\"]/h1/a")).click(); // home page
        	driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div/div[2]/h4/a")).click();// iphone
	    	driver.findElement(By.xpath("//*[@id=\"input-quantity\"]")).click();// iphone
			driver.findElement(By.name("quantity")).sendKeys("200000000000");	
        	driver.findElement(By.xpath("//*[@id=\"button-cart\"]")).click();
	    try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		logger.debug("expected:"+ "fail to add"); 
		if(driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).getAttribute("innerHTML").compareTo(last) > 0)
		{
		last=driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).getAttribute("innerHTML");
			logger.info("Add successfully"+ last);
	}
		else
			logger.info("fail to add");
		
		// end -> back to main page
		logger.info("End Test AddToCart-> back to main page"); 
        driver.findElement(By.xpath("//*[@id=\"logo\"]/h1/a")).click();

	}

	
}