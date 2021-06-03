package pack1;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.logging.log4j.*;

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
 
public class CheckOut  {


	static void  sleep() {	try {Thread.sleep(1000);} catch (InterruptedException e) {	e.printStackTrace();}}

	public static void CheckOutTest(WebDriver driver,Logger logger) throws IOException
	{
		ReadExcl objExcelFile = new ReadExcl();
		objExcelFile.readExcel("exlFiles","CheckOut.xls","Login");
		CheckOutEmpty( driver, logger);
		CheckOutNotInStock( driver, logger);
		CheckOutStarter( driver, logger);
		logger.error("The information requirements are  unclear,therefor anable to complete the payment");

    }
	static void CheckOutEmpty(WebDriver driver,Logger logger)
	{
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[5]/a/span")).click();
		if(driver.findElement(By.xpath("//*[@id=\"content\"]/p")).isDisplayed())
		{
			logger.debug("Cart is empty");
		}
		else
			logger.error("Fail");
		  sleep();
	      driver.findElement(By.xpath("//*[@id=\"logo\"]/h1/a")).click();
	}
	static void CheckOutNotInStock(WebDriver driver,Logger logger)
	{
		The_Cart.addItem( 2, driver);
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[5]/a/span")).click();
		if(driver.getCurrentUrl().equals("http://tutorialsninja.com/demo/index.php?route=checkout/cart"))
		{
			logger.debug("This product is not in stock,therefore cannot continue to checkout");
		}
		else
			logger.error("Fail");
		//remove
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/span/button[2]")).click();

		if(driver.findElement(By.xpath("//*[@id=\"content\"]/p")).isDisplayed())
		{
			logger.debug("The item has been removed and cart is empty");
		}
		else
			logger.debug("Not removed");
		sleep();
	      driver.findElement(By.xpath("//*[@id=\"logo\"]/h1/a")).click();

	}
	static void CheckOutStarter(WebDriver driver,Logger logger)
	{
		The_Cart.addItem( 1, driver);
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[5]/a/span")).click();
		if(driver.getCurrentUrl().equals("http://tutorialsninja.com/demo/index.php?route=checkout/checkout"))
		{
			logger.debug("Strat paymant");
		}
		else
			logger.error("Fail");
		
	}

}