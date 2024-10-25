package com.automation.tests;

import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.automation.base.BaseTest;
import com.automation.base.accounts.AccountsPage;
import com.automation.utility.ExtentReportsUtility;

public class AccountsTests extends BaseTest {

	private Logger mylog = LogManager.getLogger(LoginTests.class);
    ExtentReportsUtility report = ExtentReportsUtility.getInstance();
	
    @Test
	 public void createAccount() throws InterruptedException {
	    	report.startSingleTestReport("Create account");
	        mylog.info("Starting the create account test.");
		
	        LoginTests auto = new LoginTests();
	        auto.valid_login();
	        
			mylog.info("Navigating to the accounts page");
	    	
			AccountsPage accountspage= new AccountsPage(driver);
			
			accountspage.clickAccountsTab();
			Thread.sleep(5000);
			
			accountspage.clickNewButton();
			Thread.sleep(5000);
			
			mylog.info("Details for creating a new account are entered.");
			accountspage.enterAccountName("tekarch In");
			accountspage.selectCustomerPriority("High");
			accountspage.selectType("Technology Partner");
			accountspage.clickSaveButton();
			
			mylog.info("New account created.");
			mylog.info("Test case completed.");
	    }
	    
	
	
}
