package com.automation.base;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.utility.Constants;
import com.automation.utility.ExtentReportsUtility;
import com.automation.utility.PropertiesUtility;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected static WebDriver driver=null;
	private Logger mylog = LogManager.getLogger(BaseTest.class);
	protected ExtentReportsUtility report = ExtentReportsUtility.getInstance();

	@BeforeSuite
    public void setUp() {
        // Initialize the ExtentReport before tests start
        report.startExtentReport();
    }

    @AfterSuite
    public void tearDown() {
        // Finalize and flush the report after tests complete
        report.endReport();
    }
	
	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeMethod(@Optional("chrome") String name) {
		
		mylog.info("*********************setUpBeforeMethod**************************");
		//report.startExtentReport();  // Start the Extent report
       // report.startSingleTestReport("Test setup");
		launchBrowser("chrome");
		String url=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"url");
		goToUrl(url);
		
	}
	   
	@AfterMethod
	public void tearDownAfterMethod() {
		 //report.endReport(); 
		closeDriver();
		mylog.info("****************tearDownAfterTestMethod*******************");
	}
	
	public void launchBrowser(String browserName) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "safari":
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
            break;

		default:
			break;
		}
	}
	
	public void goToUrl(String url){
		driver.get(url);
		mylog.info(url + "is entered");
		//extentReportUtility.logTestInfo("go to url "+url);
		
	}
	
	public void closeDriver(){
		 if (driver != null) {
	            driver.quit();
	            mylog.info("Browser closed.");
	            report.logTestInfo("Browser closed.");
	        }
		
	}
	
	
			
			public void takeScreenshot(String filepath) {
				TakesScreenshot screenCapture=(TakesScreenshot)driver;
				File src= screenCapture.getScreenshotAs(OutputType.FILE);
				File destFile=new File(filepath);
				try {
					Files.copy(src, destFile);
					mylog.info("screen captured");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mylog.error("problem occured during screenshot taking");
				}
			}
			
			

}
