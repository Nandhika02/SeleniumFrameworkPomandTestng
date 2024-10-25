package com.automation.tests;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


import com.automation.base.BaseTest;
import com.automation.base.contacts.ContactsPage;
import com.automation.utility.ExtentReportsUtility;

public class ContactsTests extends BaseTest{

	private Logger mylog = LogManager.getLogger(ContactsTests.class);
    ExtentReportsUtility report = ExtentReportsUtility.getInstance();
	
	@Test
	 public void checknewandsavebutton() throws InterruptedException {
	    	report.startSingleTestReport("check new and save button");
	        mylog.info("Starting the validation of new and save button.");
	        
	        LoginTests auto = new LoginTests();
	        auto.valid_login();
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
			
			ContactsPage contactspage= new ContactsPage(driver);
			
			contactspage.clickContactsTab();
			contactspage.clickNewButton();
			contactspage.enterLastName("indian");
			contactspage.enteraccountName("tekarch");
			contactspage.clicksaveandnewButton();
			Thread.sleep(5000);
			
			String actualTitle = driver.getTitle();
		    String expectedTitle = "Contact Edit: New Contact ~ Salesforce - Developer Edition";

		        try {
		            Assert.assertEquals(actualTitle, expectedTitle);
		            mylog.info("New Contact edit page opened");
		        } catch (AssertionError e) {
		            mylog.error("New Contact edit page not opened", e);
		            throw e;  
		        }
			
			Thread.sleep(5000);
			mylog.info("New contacts created and page opened for another contact creation.");
			mylog.info("Test case completed.");
			
	    }
}
