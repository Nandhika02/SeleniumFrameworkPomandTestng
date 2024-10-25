package com.automation.tests;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


import com.automation.base.BaseTest;
import com.automation.base.leads.LeadsPage;
import com.automation.utility.ExtentReportsUtility;

public class LeadsTests extends BaseTest {

	private Logger mylog = LogManager.getLogger(LeadsTests.class);
    ExtentReportsUtility report = ExtentReportsUtility.getInstance();
    
    
	@Test//(priority = 7)
    public void CheckNewbuttononLeads() throws InterruptedException {
    	report.startSingleTestReport("Check new button on leads page");
        mylog.info("Starting the validation of new button on leads page.");
		
        LoginTests auto = new LoginTests();
        auto.valid_login();
        
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		LeadsPage leadspage= new LeadsPage(driver);
		
		leadspage.clickLeadsTab();
		leadspage.clickNewButton();
		leadspage.enterLastName("XYZ");
		leadspage.enterCompanyName("XYZ");
		leadspage.clicksaveButton();
		
		String actualTitle = driver.getTitle();
	    String expectedTitle = "Lead: XYZ ~ Salesforce - Developer Edition";

	        mylog.info("Expected Title: " + expectedTitle);
	        mylog.info("Actual Title: " + actualTitle);

	       
	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("New lead created");
	        } catch (AssertionError e) {
	            mylog.error("New lead not created. Titles do not match.", e);
	            throw e;  
	        }
	        mylog.info("New button on leads page validated.");
	        mylog.info("Test case completed.");
		
}
}
