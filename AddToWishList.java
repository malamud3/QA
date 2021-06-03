package pack1;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddToWishList {

	
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
		
	
	private static int getAmountOfIphonesInWishList(WebDriver driver)  {
		
		WebElement simpleTable = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody"));
		List<WebElement> rows = simpleTable.findElements(By.tagName("tr"));
		
		int j = 0;
		wait(1000);
		int amountPhones = 0;
		for (int i = 0; i < rows.size(); i++) {
			j = i + 1;
			String productName = rows.get(i).findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/" + "tr[" + j +"]/td[2]")).getText();
			if(productName.equals("iPhone")) {
				amountPhones++;
			}
		 
		}
		return amountPhones;
		}
	 
		public static void AddToWishListProduct(WebDriver driver,Logger logger)
		{
			
			
	//----------------------------------step-2------------------------------------//		
		
			
			returnToHomePage(driver);
			wait(1000);
		
			logger.debug("press button heart (add to wish list) phone product (after we connect to account)");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div/div[3]/button[2]")).click();
			wait(2000);
			String text1 = driver.findElement(By.xpath("//*[@id=\"common-home\"]/div[1]")).getText();
			 
					
			if(text1.contains("Success")) {
				logger.info("Success to get massage that iphone product added to wish list");
				logger.debug("enter to wish list to check if the product is added");
				wait(1000);
				driver.findElement(By.xpath("//*[@id=\"common-home\"]/div[1]/a[2]")).click();
				
			     
				int amountPhones = getAmountOfIphonesInWishList(driver);
						
					if(amountPhones == 1) {
						logger.info("the iphone product add succefully to wishList");
						
						
						
					}else {
						logger.error("the iphone product dosen't add to wishList");
					}
					
	
			}else {
				logger.error("failed");
				
			}
						
			
			//---------------------------------step-3------------------------------------//				
			
			returnToHomePage(driver);
			wait(1000);
			
			
			logger.debug("press button heart (add to wish list) phone product (after we connect to account) and there is already phone in wish list");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div/div[3]/button[2]")).click();
			wait(2000);
			driver.findElement(By.xpath("//*[@id=\"common-home\"]/div[1]/a[2]")).click();
		    
			
			int amountPhones = getAmountOfIphonesInWishList(driver);
			
			// more than 2
			if(amountPhones == 1) {
				logger.error("the product dosen't add to wish list");
				
								
			}else {
				logger.info("the produt add to wish list");
			}
				
			
			
		
			//----------------------------------step-1------------------------------------//			
			returnToHomePage(driver);
			wait(1000);
			
			logger.debug("log out from account");
			driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]")).click();
			driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[5]")).click();
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/div")).click();
			
			logger.debug("press button heart (add to wish list) phone product");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div/div[3]/button[2]")).click();
			wait(1000);
			String text2 = driver.findElement(By.xpath("//*[@id=\"common-home\"]/div[1]")).getText();
			if(text2.contains("You must")) {
				logger.info("succeded - A text message appears that must be registered for the site or make a connection");
				
			}else {
				logger.error("failed - A text message dosen't appears that must be registered for the site or make a connection");
			}
			
		
		
	}
	
		
	
}
