package com.automation.tests;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


import com.automation.base.BaseTest;
import com.automation.base.opportunities.OpportunitesPage;
import com.automation.utility.ExtentReportsUtility;

public class OpportunitiesTests extends BaseTest{

	
	private Logger mylog = LogManager.getLogger(OpportunitiesTests.class);
    ExtentReportsUtility report = ExtentReportsUtility.getInstance();
	
	@Test(priority = 5)
    public void createNewOpp() throws InterruptedException {
    	report.startSingleTestReport("New opportunity creation.");
        mylog.info("Starting the dropdown test.");
	
        LoginTests auto = new LoginTests();
        auto.valid_login();
        
        OpportunitesPage opppage= new OpportunitesPage(driver);
        
        opppage.clickOppTab();
        opppage.clickNewButton();
        opppage.enterOppName("opp2");
        opppage.enterAccountName("Tekarch");
        opppage.selectStage("Prospecting");
        opppage.selectLeadsource("Web");
        opppage.enterProbability("10");
        opppage.clickCalender();
        opppage.clicktodaysDate();
        opppage.clicksaveButton();
        
        Thread.sleep(5000);
        String actual=driver.getTitle();
        String expected="Opportunity: opp2 ~ Salesforce - Developer Edition";
        
        mylog.info("Expected Title: " + expected);
        mylog.info("Actual Title: " + actual);

       
        try {
            Assert.assertEquals(actual, expected);
            mylog.info("New opportunity created. Titles match.");
        } catch (AssertionError e) {
            mylog.error("New opportunity not created. Titles do not match.", e);
            throw e;  
        }
        mylog.info("Test case completed.");
    }
    
    @Test(priority = 6)
    public void testopppipelinereport() throws InterruptedException {
    	report.startSingleTestReport("Test opportunites pipeline report.");
        mylog.info("Test Opportunity Pipeline Report");
	
        LoginTests auto = new LoginTests();
        auto.valid_login();
        
        OpportunitesPage opppage= new OpportunitesPage(driver);
        
        opppage.clickOppTab();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        opppage.clickPipelinelink();
        
        String actualTitle = driver.getTitle();
	    String expectedTitle = "Opportunity Pipeline ~ Salesforce - Developer Edition";

	        mylog.info("Expected Title: " + expectedTitle);
	        mylog.info("Actual Title: " + actualTitle);

	       
	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("Opportunity Pipeline Report opened");
	        } catch (AssertionError e) {
	            mylog.error("Opportunity Pipeline Report not opened. Titles do not match.", e);
	            throw e;  
	        }
	        mylog.info("Test case completed.");
	}
}
