package pack1;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class The_Cart {

	public static void CartTest(WebDriver driver,Logger logger)
	{
		//check  empty
		isEmpty( driver, logger);

		//add item to check
		addItem(1, driver);

		// check item add and price 
		sleep();
		isType1InCart( driver, logger);

		//back
		back( driver, logger);
				
		removeItem ( driver, logger); 				

		
		sleep();
		back( driver, logger);
		sleep();
		driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[1]/strong")).click();
		
		 //try to update  amount
		 logger.debug("expected:"+" msg :You have modified your shopping cart!");
		 update( driver, logger ,"5");
		 
		 //try to update more then we have
		 logger.debug("expected:"+"Fail");
		 update( driver, logger ,"1000000000000000000");
		
		 
		//   0 form item = remove
		 logger.debug("expected:"+"The item has been removed and cart is not empty");
		 update( driver, logger ,"0");
		 if(!driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[3]")).getAttribute("innerHTML").equals("product 11"))		
			{
				logger.debug("The item has been removed and cart is not empty");
			}
			else
				logger.error("Fail");


//		//remove

		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/span/button[2]")).click();

		if(driver.findElement(By.xpath("//*[@id=\"content\"]/p")).isDisplayed())
		{
			logger.debug("The item has been removed and cart is empty");
		}
		else
			logger.debug("Not removed");
		
		  logger.info("End Test CartTest-> back to main page"); 
		  sleep();
	      driver.findElement(By.xpath("//*[@id=\"logo\"]/h1/a")).click();

	}
	static void  addItem(int o,WebDriver driver)
	{
		if (o==1)//computer
			driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/button[1]")).click();

		else// iphone
			driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div/div[3]/button[1]")).click();

	}

	static void  isEmpty(WebDriver driver,Logger logger) 
	{
		driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).click();
		logger.debug("Check if cart empty");
		logger.debug("expected:"+"cart empty"); 

		if(driver.findElement(By.xpath("//*[@id=\"cart\"]/ul")).isDisplayed())
			logger.info("Your shopping cart is empty!");
		else
			logger.info("Your shopping cart is not empty!");

	}
	static void  isType1InCart (WebDriver driver,Logger logger)
	{
		logger.debug("check if item is in cart and the price is update"); 
		logger.debug("expected:"+"item in cart and price update"); 

		driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[1]/strong")).click();
		if(driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[1]/a/img")).isDisplayed())
		{
			sleep();
			logger.info(driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/table/tbody/tr[2]/td[2]")).getText());			
		}
		else
			logger.info(driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/table/tbody/tr[2]/td[2]")).getText());
			logger.info("Item did is not in cart");
	}

	static void  removeItem (WebDriver driver,Logger logger)
	{
		sleep();
		logger.debug("Remove item from cart with more then 1 item");
		logger.debug("expected:"+"The item has been removed and cart is not empty");

		driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[1]/strong")).click();
		sleep();
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/span/button[2]")).click();
		sleep();
		if(!driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[3]")).getAttribute("innerHTML").equals("product 11"))		
		{
			logger.debug("The item has been removed and cart is not empty");
		}
		else
			logger.debug("Fail");

	}
	static void  back (WebDriver driver,Logger logger)
	{
		sleep();
		logger.debug("Check if Continue Shopping sending to main page");
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[1]")).click();	
		//add 1 more
				try {
					addItem(2, driver);
					logger.debug("Back to main page");}
				catch (Exception e) {	e.printStackTrace();}
	}

	static void update(WebDriver driver,Logger logger ,String n)
	{
	driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).click();
	driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/input")).clear();
	sleep();
	driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/input")).sendKeys(n);
	driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/span/button[1]/i")).click();
	if(driver.findElement(By.xpath("//*[@id=\"checkout-cart\"]/div[1]")).isDisplayed())
	{
		logger.debug("You have modified your shopping cart!");
	}
	else
	logger.error("Fail");
	
	}
static void  sleep()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();}
	}
}