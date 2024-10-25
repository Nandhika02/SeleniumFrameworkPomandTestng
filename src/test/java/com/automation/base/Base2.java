package com.automation.base;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.utility.Constants;
import com.automation.utility.ExtentReportsUtility;
import com.automation.utility.PropertiesUtility;
//import com.automation.utility.ExtentReportsUtility;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base2 {

	protected static WebDriver driver=null;
	protected WebDriverWait wait = null;
	private Logger mylog = LogManager.getLogger(BaseTest.class);
	protected ExtentReportsUtility report = ExtentReportsUtility.getInstance();
	
	
	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeMethod(@Optional("chrome") String name) throws InterruptedException {
		
		mylog.info("*********************setUpBeforeMethod**************************");
		report.startExtentReport();  // Start the Extent report
        report.startSingleTestReport("Test setup");
		launchBrowser("chrome");
		String url=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"url");
		goToUrl(url);
		
	}
	
	@AfterMethod
	public void tearDownAfterMethod() {
		 report.endReport(); 
		closeDriver();
		mylog.info("****************tearDownAfterTestMethod*******************");
	}

    // Launches the specified browser
    public static void launchBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            default:
                System.out.println("Browser not supported");
                break;
        }
    }

    // Navigates to the specified URL
    public  void goToUrl(String url) throws InterruptedException {
        driver.get(url);
        System.out.println(url + " is entered");
        Thread.sleep(5000); // Consider replacing with WebDriver wait
    }

    // Enters text into an element
    public void enterText(WebElement ele, String data, String objectName) {
        if (ele.isDisplayed()) {
            ele.clear();
            ele.sendKeys(data);
            mylog.info ("data is entered in the "+objectName);
           // extentReportUtility.logTestInfo("data is entered in the "+objectName) ;
           
        } else {
        	mylog. error (objectName+" textbox is not diplayed");
        	//extentReportUtility.logTestFailed("data is entered in the "+objectName);
        }
    }

    // Extract text from an element
    public String getTextFromElement(WebElement ele, String objectName) {
        String data = null;
        if (ele.isDisplayed()) {
            data = ele.getText();
            mylog.info("text is extracted from "+objectName);
        } else {
        	mylog.error(objectName+" not dispalyed");
        }
        return data;
    }
    
    
    // Clicks on an element
    public void clickElement(WebElement ele, String objectName) {
        if (ele.isEnabled()) {
            ele.click();
           
            mylog.info (objectName+" button is clicked");
        } else {
        	mylog.error (objectName+" button is not diplayed");
        }
    }

    public void selectCheckBox(WebElement ele, String objectName) {
    		if (!ele.isSelected()) {
    			ele.click ();
    			mylog.info(objectName+" button is selected");
    		}else {
    		mylog. error (objectName+" button is already selected");
    		}
    }
    
    
    // Select options in dropdowns
    public void selectByValue(WebElement ele, String value) {
        Select select = new Select(ele);
        select.selectByValue(value);
    }

    public  void selectByText(WebElement ele, String value) {
        Select select = new Select(ele);
        select.selectByVisibleText(value);
    }

    public  void selectByIndex(WebElement ele, int value) {
        Select select = new Select(ele);
        select.selectByIndex(value);
    }

    // Selects an element (checkbox or radio button)
    public static void selectElement(WebElement ele, String objectName) {
        if (!ele.isSelected()) {
            ele.click();
            System.out.println(objectName + " button is selected");
        } else {
            System.out.println(objectName + " button is already selected");
        }
    }
    
    public WebElement selectFromListUsingText (List<WebElement> list, String text) {
    	
    	WebElement element=null;
    	for (WebElement i : list) {
    		if (i.getText().equalsIgnoreCase(text)) {
    			mylog.info ("selected=" + i.getText());
    			element=i;
    			break;
    		}
    	}
    return element;
    }
    
   

    // User dropdown functionality
    public void userDropdown(String option) {
        WebElement navButton = driver.findElement(By.xpath("//div[@id='userNavButton']"));
        clickElement(navButton, "User Navigation Button");

        List<WebElement> userDropdownMenu = driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
        for (WebElement userDropdown : userDropdownMenu) {
            if (userDropdown.getText().contains(option)) {
                userDropdown.click();
                System.out.println("Clicked on: " + option);
                break;
            }
        }
    }

    // Login method
    public void login() throws InterruptedException {
    	

        String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
        String passwordData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "password");

        mylog.info("Navigating to the login page");
       
 		Thread.sleep(5000);
 		
 		WebElement usernameEle=driver.findElement(By.xpath("//*[@id=\"username\"]"));
 		enterText(usernameEle, usernameData, "username");
 		
 		WebElement passwordEle = driver.findElement(By.id("password"));
 		enterText(passwordEle, passwordData, "password");
 		
 		WebElement buttonEle= driver.findElement(By.id("Login"));
 		clickElement(buttonEle, "login button");
    }

  
    
    // Switch to alert and return it
    public  Alert switchToAlert() {
        Alert alert = driver.switchTo().alert();
        System.out.println("Switched to an alert");
        return alert;
    }

    public String getAlertText (Alert alert, String objectname) {
    	mylog. info ("stracting text in the" + objectname + "alert");
        String text = alert.getText();
        mylog.info ("text is extracted from alert box is==" + text);
        return text;
    }
    
    public void AcceptAlert (Alert alert) {
    alert.accept ();
    mylog.info ("Alert accepted");
    }
    
    public void CancelAlert (Alert alert) {
    alert.dismiss ();
    mylog.info ("Alert cancelled");
    }
    
    public void mouseOverOnElement( WebElement ele, String objectname){

    Actions action = new Actions (driver);
    action.moveToElement (ele).build () .perform () ;
    mylog.info(" cursor moved to web element " + objectname);
    }
    
    public void ContextClickOnElement(WebElement ele, String objName) {
    Actions action = new Actions (driver);
    action.contextClick (ele).build().perform ();
    mylog.info ("right click persormed on web element " + objName);
    }
    
    // Wait for the visibility of an element
    public void waitForVisibility(WebElement ele, long timeInSec, String objectName) {
        mylog.info(objectName + " waiting for visibility for a maximum of " + timeInSec + " sec");
        WebDriverWait wait = new WebDriverWait(driver, timeInSec);
        wait.until(ExpectedConditions.visibilityOf(ele));
        mylog.info(objectName + " is visible now");
    }

    
    public void waitForAlertToPresent (long timeInSec, String ObjectName) {
    
    	mylog.info (ObjectName+ "waiting for visibility for maximum of "+timeInSec+ "sec");
    	wait= new WebDriverWait(driver,timeInSec);
    	wait.until (ExpectedConditions.alertIsPresent () );
    }
    
    public void waitForBlementToClickable (WebElement ele,long timelnSec,String ObiectName) {
    	
    	mylog. info(ObiectName + "waiting for visibility for maximum of "+ timelnSec +"sec");
    	wait=new WebDriverWait (driver,timelnSec);
    	wait.until (ExpectedConditions.elementToBeClickable(ele));
    }
    
    
    public void waitForVtextToBePresentInElement (WebElement ele, long timeInSec, String text, String ObjectName){
    	
    	mylog.info(ObjectName+ "waiting for visibility for maximum of" + timeInSec + "sec");
    	wait=new WebDriverWait (driver,timeInSec);
    	wait.until (ExpectedConditions.textToBePresentInElement (ele, text));
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
    
		
 // Closes the WebDriver
    public void closeDriver() {
        if (driver != null) {
            driver.close();
            System.out.println("Driver closed");
        }
    }
}









