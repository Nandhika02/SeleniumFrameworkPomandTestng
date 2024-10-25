package com.automation.pages.base;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.utility.ExtentReportsUtility;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait=null;
	private Logger mylog = LogManager.getLogger(BasePage.class);
	protected ExtentReportsUtility extentReportUtility=ExtentReportsUtility.getInstance();
	
	public BasePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	 // Enters text into an element
    public void enterText(WebElement ele, String data, String objectName) {
        if (ele.isDisplayed()) {
            ele.clear();
            ele.sendKeys(data);
            mylog.info ("data is entered in the "+objectName);
            extentReportUtility.logTestInfo("data is entered in the "+objectName) ;
           
        } else {
        	mylog. error (objectName+" textbox is not diplayed");
        	extentReportUtility. logTestFailed("data is entered in the "+objectName);
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
    
}
