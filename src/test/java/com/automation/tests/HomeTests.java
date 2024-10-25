package com.automation.tests;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


import com.automation.base.BaseTest;
import com.automation.pages.home.HomePage;
import com.automation.utility.ExtentReportsUtility;

public class HomeTests extends BaseTest{


	private Logger mylog = LogManager.getLogger(HomeTests.class);
    ExtentReportsUtility report = ExtentReportsUtility.getInstance();
	
	
	@Test
    public void userDopdown() throws InterruptedException {
    	report.startSingleTestReport("User Dropdown test");
        mylog.info("Starting the dropdown test.");
	
        LoginTests auto = new LoginTests();
        auto.valid_login();
        
        mylog.info("Selecting the username dropdown");
        
        HomePage homepage= new HomePage(driver);
        
        homepage.openUserDropdown();
        Thread.sleep(5000);

        homepage.logDropdownItems();

       
        int actual = homepage.getDropdownMenuItemCount();
        int expected = 5;  

        mylog.info("Expected number of dropdown items: " + expected);
        mylog.info("Actual number of dropdown items: " + actual);

       
        try {
            Assert.assertEquals(actual, expected, "All contents under dropdown are available.");
            mylog.info("Dropdown items count matched.");
        } catch (AssertionError e) {
            mylog.error("All contents are not present. Test failed.");
            throw e;
        }

        mylog.info("Test case completed.");
        report.endReport();
    }
	
	 @Test//(priority = 8)
	    public void logout() throws InterruptedException {
	    	report.startSingleTestReport("Logout");
	        mylog.info("Starting the Logout test.");
	        
	        
	        LoginTests auto = new LoginTests();
	        auto.valid_login();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
			
			HomePage homepage=new HomePage(driver);
			
			homepage.openUserDropdown();
			homepage.clickLogout();
			
			Thread.sleep(5000);
			
			 String actualTitle = driver.getTitle();
		     String expectedTitle = "Login | Salesforce";

		        mylog.info("Expected Title: " + expectedTitle);
		        mylog.info("Actual Title: " + actualTitle);

		       
		        try {
		            Assert.assertEquals(actualTitle, expectedTitle);
		            mylog.info("Logout successful. Titles match.");
		        } catch (AssertionError e) {
		            mylog.error("Logout failed. Titles do not match.", e);
		            throw e;  
		        }
		        mylog.info("Test case completed.");
		        report.endReport();
}
	 @Test//(priority = 10)
	    public void validatehomepage() throws InterruptedException {
	    	report.startSingleTestReport("validate homepage");
	        mylog.info("Starting the validation of home page.");
			
	        LoginTests auto = new LoginTests();
	        auto.valid_login();
	        
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
			
			HomePage homepage= new HomePage(driver);
			
			homepage.clickHomeTab();
			homepage.clickNameLink();
			
			String profiletitle=driver.getTitle();
			
			homepage.openUserDropdown();
			homepage.clickmyProfile();
			
			String myprofiletitle=driver.getTitle();
			
			try {
	            Assert.assertEquals(profiletitle, myprofiletitle);
	            mylog.info("Titles matched.Both pages are same");
	        } catch (AssertionError e) {
	            mylog.error("Both pages are different. Titles do not match.", e);
	            throw e;  
	        }
	        mylog.info("Test case completed.");
	        report.endReport();
	    } 

}
