package com.automation.tests;



import org.testng.annotations.Test;

//import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.automation.base.BaseTest;
/*
import com.automation.base.accounts.AccountsPage;
import com.automation.base.contacts.ContactsPage;
import com.automation.base.leads.LeadsPage;
import com.automation.base.opportunities.OpportunitesPage;
import com.automation.pages.home.HomePage;
*/
import com.automation.pages.login.LoginPage;
import com.automation.utility.Constants;
import com.automation.utility.ExtentReportsUtility;
import com.automation.utility.PropertiesUtility;

public class LoginTests extends BaseTest{

	// Create a logger instance for logging
    private Logger mylog = LogManager.getLogger(LoginTests.class);
    ExtentReportsUtility report = ExtentReportsUtility.getInstance();

 

    
    @Test(priority = 2)
    public void valid_login() throws InterruptedException {
    	
    	 report.startSingleTestReport("valid_login");
         mylog.info("Starting the valid login test");
       
        String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
        String passwordData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "password");

        mylog.info("Navigating to the login page");
       
        // Create an instance of LoginPage
        LoginPage loginpage = new LoginPage(driver);

        
        mylog.info("Entering username and password");
        loginpage.enterUserName(usernameData);
        loginpage.enterPassword(passwordData);
        loginpage.clickLoginButton();
        mylog.info("Clicked login button");

        Thread.sleep(5000);
      
        String actualTitle = driver.getTitle();
        String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

        mylog.info("Expected Title: " + expectedTitle);
        mylog.info("Actual Title: " + actualTitle);

       
        try {
            Assert.assertEquals(actualTitle, expectedTitle);
            mylog.info("Login successful. Titles match.");
        } catch (AssertionError e) {
            mylog.error("Login failed. Titles do not match.", e);
            throw e;  
        }
       // mylog.info("Test case completed.");
    }
    
    @Test(priority = 1)
    public void invalid_login() {
    	report.startSingleTestReport("Invalid_login");
        mylog.info("Starting the invalid login test");
        
        String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
        String passwordData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "empty");

        mylog.info("Navigating to the login page");
       
        // Create an instance of LoginPage
        LoginPage loginpage = new LoginPage(driver);

        
        mylog.info("Entering username and password");
        loginpage.enterUserName(usernameData);
        loginpage.enterPassword(passwordData);
        loginpage.clickLoginButton();
        mylog.info("Clicked login button");

      
        String actualErrorMessage = loginpage.getErrorMessage(); 
        String expectedErrorMessage = "Please enter your password.";

        mylog.info("Expected Error Message: " + expectedErrorMessage);
        mylog.info("Actual Error Message: " + actualErrorMessage);

       
        try {
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "The error message displayed is incorrect.");
            mylog.info("Invalid login test passed as expected, error message displayed.");
            ExtentReportsUtility.getInstance().logTestpassed("Invalid login test passed. Error message: " + actualErrorMessage);
        } catch (AssertionError e) {
            mylog.error("Error message did not match. Test failed.", e);
            ExtentReportsUtility.getInstance().logTestFailedWithException(e);
            throw e;  
        }
        
        mylog.info("Test case completed.");
    }
    
   /* @Test//(priority = 4, dependsOnMethods = "valid_login")
    public void createAccount() throws InterruptedException {
    	report.startSingleTestReport("Create account");
        mylog.info("Starting the create account test.");
	
        valid_login();
		 
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
    
    @Test(priority = 3)
    public void userDopdown() throws InterruptedException {
    	report.startSingleTestReport("User Dropdown test");
        mylog.info("Starting the dropdown test.");
	
        valid_login();
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
    }
    

    @Test(priority = 5)
    public void createNewOpp() throws InterruptedException {
    	report.startSingleTestReport("User Dropdown test");
        mylog.info("Starting the dropdown test.");
	
        valid_login();
        
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
	
        valid_login();
        
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
       
    @Test(priority = 7)
    public void CheckNewbuttononLeads() throws InterruptedException {
    	report.startSingleTestReport("Check new button on leads page");
        mylog.info("Starting the validation of new button on leads page.");
		
        valid_login();
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
    
    @Test(priority = 8)
    public void logout() throws InterruptedException {
    	report.startSingleTestReport("Logout");
        mylog.info("Starting the Logout test.");
        
		valid_login();
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
    }
    
    @Test(priority = 9)
    public void checknewandsavebutton() throws InterruptedException {
    	report.startSingleTestReport("check new and save button");
        mylog.info("Starting the validation of new and save button.");
        
		valid_login();
		
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
        
    @Test(priority = 10)
    public void validatehomepage() throws InterruptedException {
    	report.startSingleTestReport("validate homepage");
        mylog.info("Starting the validation of home page.");
		
        valid_login();
        
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
    }
*/
    
    }
    
    
    

