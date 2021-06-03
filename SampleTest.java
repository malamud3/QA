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

public class SampleTest {
	private static WebDriver driver;
	private static Map<String, Object> vars;
	static JavascriptExecutor js;
	static	Logger logger;

	@Before
	public void setUp()  throws IOException{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\ofiii\\chromedriver.exe");	
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		ReadExcl objExcelFile = new ReadExcl();
		logger= LogManager.getLogger(SampleTest.class);
		  
		objExcelFile.readExcel("exlFiles","Login.xls","Login");
				
	}

	@Test 
	public void Login()  {
		int rowCount=ReadExcl.getRowcount();		  
		Sheet thsSheet=ReadExcl.getsheet();


		driver.get("http://tutorialsninja.com/demo/index.php?route=common/home");
		logger.info("opening website");

		driver.manage().window().maximize();
		logger.info("Set window size");
		
		logger.info("Start Test: Login"); 

		driver.findElement(By.className("dropdown")).click();
		logger.info("Clicked My Account Menu");

		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]")).click();
		logger.info("Select Login ");
		
		if(driver.findElement(By.id("account-login")).isDisplayed())
		logger.debug("Account-login  page is loaded");
		else
		logger.error("Account-login  page isnt loaded");

		for (int i = 1; i < rowCount+1; i++) {
	             Row row = thsSheet.getRow(i);
	             
				driver.findElement(By.name("email")).clear();
				driver.findElement(By.name("password")).clear();

					driver.findElement(By.name("email")).sendKeys(row.getCell(1).getStringCellValue());
					logger.debug("New email insert");
					driver.findElement(By.name("password")).sendKeys((row.getCell(2).getStringCellValue()));
					logger.debug("New password insert");
		

				try {
					driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
					logger.debug("Login  button pressed");

				} catch (Exception e1)
				{logger.debug("Login button not pressed");}
					
					logger.debug("expected:"+  row.getCell(3).getStringCellValue()); 
					logger.info(row.getCell(0).getStringCellValue());

				try {  
					if(driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).isDisplayed())
					 						
						{logger.error(" Warning: No match for E-Mail Address and/or Password");}
					
				} catch (Exception e) 
				{ logger.debug("Successfully logged in");
				}
				}
	}


	public static void main(String args[]) throws IOException {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		org.junit.runner.Result result ; //FIX this
		
        result = junit.run(SampleTest.class); // Replace "SampleTest" with the name of your class
        result = junit.run();
        
        logger.info("Back to main page for further testing");
        driver.findElement(By.xpath("//*[@id=\"logo\"]/h1/a")).click();
        
        
		  logger.info("Start Test: CartTest"); 
          The_Cart.CartTest( driver ,logger);

		  logger.info("Start Test: addToCartTest"); 
          AddToCart.addToCartTest( driver ,logger);
          
		  logger.info("Start Test: AddToWishListProduct"); 
          AddToWishList.AddToWishListProduct( driver ,logger);
          
		  logger.info("Start Test: EnterTheProduct"); 
          EnterTheProductPage.EnterTheProduct( driver ,logger);
     
		  logger.info("Start Test: Paymant"); 
		  CheckOut.CheckOutTest( driver ,logger);
	
		  driver.quit();				
		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			System.exit(1);
		} else {
			System.out.println("Test finished successfully.");
			System.exit(0);
		}
	}
//	@After
//	public void tearDown() {
//	
//	}
}