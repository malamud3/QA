package pack1;
import org.junit.Test;


import org.openqa.selenium.support.ui.Select;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
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

import javax.naming.spi.DirStateFactory.Result;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.logging.log4j.*;
import org.openqa.selenium.support.ui.Select;

//כניסה לדף המוצר
public class EnterTheProductPage {
	
	
 private static void returnToHomePage(WebDriver driver) {
	 // return to home page 
	 driver.findElement(By.xpath("//*[@id=\"logo\"]/h1/a")).click();
	}
	
	
private static void wait(int millis) {
	 try {
		Thread.sleep(millis);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	}
	
 
	public static void EnterTheProduct(WebDriver driver,Logger logger)
	{
	
		//----------------------------------step-1------------------------------------//			
		returnToHomePage(driver);
		logger.debug("press on the product picture");
		// click on the picture of product
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[1]/a")).click();
		if(driver.findElement(By.xpath("//*[@id=\"product-product\"]")).isDisplayed()) {
			logger.info("open page product is succeded");
			
		}else {
			logger.error("open page product failed");
		}
		
		
		
		 
//----------------------------------step-2------------------------------------//		
		
		
		returnToHomePage(driver);
		wait(2000);
		//click on the name of product
		logger.debug("press on the name of product");
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[2]/h4/a")).click();
		if(driver.findElement(By.xpath("//*[@id=\"product-product\"]")).isDisplayed()) {
			logger.info("open page product is succeded");
			
		}else {
			logger.error("open page product failed");
		}
		
		
		
//---------------------------------step-3------------------------------------//				
		
		
		
		returnToHomePage(driver);
		
		logger.debug("press on the product picture");
		// click on the picture of phone product
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div/div[2]/h4/a")).click();
		wait(1000);
		if(driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div")).isDisplayed()) {
			logger.error("failed-show details on the product that is selected and also show Other products");
			
		}else {
			logger.info("succeded-show only details on the product that is selected");;
		}
		
		
		
	     
		
		
		
}
	
	
	
}
	
