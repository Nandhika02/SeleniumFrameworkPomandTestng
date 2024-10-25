package salesforceWithTestng;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.automation.base.Base2;
import com.automation.tests.LoginTests;
import com.automation.utility.Constants;
import com.automation.utility.ExtentReportsUtility;
import com.automation.utility.PropertiesUtility;


public class allSalesforceTestcases extends Base2{
	 private Logger mylog = LogManager.getLogger(LoginTests.class);
	    ExtentReportsUtility report = ExtentReportsUtility.getInstance();

    
    @Test
    public void invalidlogin() throws InterruptedException{
		
    	report.startSingleTestReport("valid_login");
        mylog.info("Starting the valid login test");
      
       String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
       String passwordData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "empty");

       mylog.info("Navigating to the login page");
      
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement usernameEle=driver.findElement(By.xpath("//*[@id=\"username\"]"));
		enterText(usernameEle, usernameData, "username");
		
		WebElement passwordEle = driver.findElement(By.id("password"));
		enterText(passwordEle, passwordData, "password");
		
		WebElement buttonEle= driver.findElement(By.id("Login"));
		clickElement(buttonEle, "login button");
		
		
		 String actualErrorMessage = driver.findElement(By.id("error")).getText();
		
	     String expectedErrorMessage = "Please enter your password.";

	      mylog.info("Expected Error Message: " + expectedErrorMessage);
	      mylog.info("Actual Error Message: " + actualErrorMessage);

	       
	        try {
	            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "The error message displayed is incorrect.");
	            mylog.info("Invalid login test passed as expected, error message displayed.");
	            
	        } catch (AssertionError e) {
	        	mylog.error("Error message did not match. Test failed.");
	            
	            throw e;  
	        }
	        
	        mylog.info("Test case completed.");
		
    }

    @Test
    public void validlogin() throws InterruptedException{
    	
    	report.startSingleTestReport("valid_login");
        mylog.info("Starting the valid login test");
      
       String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
       String passwordData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "password");

       mylog.info("Navigating to the login page");
      
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement usernameEle=driver.findElement(By.xpath("//*[@id=\"username\"]"));
		enterText(usernameEle, usernameData, "username");
		
		WebElement passwordEle = driver.findElement(By.id("password"));
		enterText(passwordEle, passwordData, "password");
		
		WebElement buttonEle= driver.findElement(By.id("Login"));
		clickElement(buttonEle, "login button");
		
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
	        mylog.info("Test case completed.");

		userDropdown("Logout");
		
		Thread.sleep(5000);
		
	}
    
    @Test
    public void checkRememberme() throws InterruptedException {
		
    	report.startSingleTestReport("Remember me checkbox");
        mylog.info("Starting the remember me checkbox test.");
      
       String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
       String passwordData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "password");

       mylog.info("Navigating to the login page");
      
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement usernameEle=driver.findElement(By.xpath("//*[@id=\"username\"]"));
		enterText(usernameEle, usernameData, "username");
		
		WebElement passwordEle = driver.findElement(By.id("password"));
		enterText(passwordEle, passwordData, "password");
		
		WebElement rememberme= driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[3]/label"));
		rememberme.click();

		WebElement buttonEle= driver.findElement(By.id("Login"));
		clickElement(buttonEle, "login button");
		
		userDropdown("Logout");
		Thread.sleep(5000);
		
		String actualMessage = driver.findElement(By.id("hint_back_chooser")).getText();
		
	     String expectedMessage = "1 Saved Username";

	      mylog.info("Expected Error Message: " + expectedMessage);
	       mylog.info("Actual Error Message: " + actualMessage);

	       
	        try {
	            Assert.assertEquals(actualMessage, expectedMessage);
	            mylog.info("remember me checked box clicked,saved message displayed.");
	            
	        } catch (AssertionError e) {
	        	mylog.error("saved message did not match. Test failed.");
	            
	            throw e;  
	        }
	        
	        mylog.info("Test case completed.");
		
	}
    

    @Test
    public void forgotpassword4A() throws InterruptedException {
    	
    	report.startSingleTestReport("forgotpassword link");
        mylog.info("Starting the forgot password link test.");
		
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement forgetpassword= driver.findElement(By.xpath("//*[@id=\"forgot_password_link\"]"));
		clickElement(forgetpassword, "forgot password");
		
		Thread.sleep(5000);
		
		String actualpageTitle= driver.getTitle();
		
	    String expectedpageTitle = "Forgot Your Password | Salesforce";

	      mylog.info("Expected Error Message: " + expectedpageTitle);
	      mylog.info("Actual Error Message: " + actualpageTitle);

	        try {
	            Assert.assertEquals(actualpageTitle, expectedpageTitle);
	            mylog.info("Forgot your password page opened.");
	            
	        } catch (AssertionError e) {
	        	mylog.error("Forgot your password page did not opened. Test failed.");
	            
	            throw e;  
	        }
		
		WebElement username= driver.findElement(By.id("un"));
		enterText(username, "nandhika.sampathkumar@gmail.com", "mail id");
		
		WebElement continuebutton= driver.findElement(By.xpath("//*[@id=\"continue\"]"));
		clickElement(continuebutton, "continue");
	
		String actualpageTitle1= driver.getTitle();
		
	    String expectedpageTitle1 = "Check Your Email | Salesforce";

	      mylog.info("Expected Error Message: " + expectedpageTitle1);
	      mylog.info("Actual Error Message: " + actualpageTitle1);

	        try {
	            Assert.assertEquals(actualpageTitle1, expectedpageTitle1);
	            mylog.info("Check Your Email page opened.");
	            
	        } catch (AssertionError e) {
	        	mylog.error("Check Your Email page did not opened. Test failed.");
	            
	            throw e;  
	        }
	        mylog.info("Test case completed.");
	}
	
    @Test
	public void forgotpassword4B() throws InterruptedException {
    	report.startSingleTestReport("forgot password.");
        mylog.info("Starting the invalid password test.");
		
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement usernameEle=driver.findElement(By.xpath("//*[@id=\"username\"]"));
		enterText(usernameEle, "123", "username");
		
		WebElement passwordEle = driver.findElement(By.id("password"));
		enterText(passwordEle, "22132", "password");
		
		WebElement buttonEle= driver.findElement(By.id("Login"));
		clickElement(buttonEle, "login button");
		
		 String actualErrorMessage = driver.findElement(By.id("error")).getText();
			
	     String expectedErrorMessage = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";

	      mylog.info("Expected Error Message: " + expectedErrorMessage);
	      mylog.info("Actual Error Message: " + actualErrorMessage);

	       
	        try {
	            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "The error message displayed is incorrect.");
	            mylog.info("Error message matched.");
	            
	        } catch (AssertionError e) {
	        	mylog.error("Error message did not match. Test failed.");
	            
	            throw e;  
	        }
	        
	        mylog.info("Test case completed.");
		
	}
	
    @Test
	public void validatedropdown() throws InterruptedException {
    	
    	report.startSingleTestReport("Dropdown menu");
        mylog.info("Starting the validation of dropdown menu.");
		
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		Thread.sleep(5000);
		WebElement navbutton= driver.findElement(By.xpath("//div[@id='userNavButton']"));
		clickElement(navbutton, "navigation");
		
		List<WebElement> userDropdown = driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
	   
	    Thread.sleep(5000);
	    for (WebElement buttons : userDropdown ) {
            System.out.println(buttons.getText());   
	    }
        
	   int actual = userDropdown .size();
	   int expected = 5;
	    
	    mylog.info("Expected Error Message: " + expected);
	    mylog.info("Actual Error Message: " + actual);

	    try {
	            Assert.assertEquals(actual, expected, "All contents under dropdown are available.");
	            mylog.info("Error message matched.");
	            
	        } catch (AssertionError e) {
	        	mylog.error("All contents are not present." + "Test failed.");
	            
	            throw e;  
	        }
	        mylog.info("Test case completed.");
	}
	
    @Test
	public void myprofile() throws InterruptedException {
		
    	report.startSingleTestReport("My Profile");
        mylog.info("Starting the my profile page test.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 

		userDropdown("My Profile");
		
		/*WebElement editprofile= driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img"));
		clickElement(editprofile,"edit profile");
		mylog.info("edit profile pen icon is clicked");
		
		driver.switchTo().frame("contactInfoContentId");
        
       
        WebElement aboutTab = driver.findElement(By.id("aboutTab"));
        clickElement(aboutTab,"about tab");
        
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        enterText(lastNameField, "sampat", "last name");
     
        WebElement saveButton = driver.findElement(By.xpath("//*[@value=\"Save All\"]"));
        saveButton.click();
        mylog.info("Last Name is updated");
        
        driver.switchTo().defaultContent();
		
        WebElement postLink=driver.findElement(By.xpath("//*[@id='profileFeed']/div/div[1]/ul/li[1]/a"));
		clickElement(postLink, "Post link");
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement iframeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"cke_contents cke_reset\"]/iframe")));
		driver.switchTo().frame(iframeElement);
		
        WebElement postTextArea = driver.findElement(By.tagName("body"));
        enterText(postTextArea, "hello", "text");

        WebElement shareButton = driver.findElement(By.id("publishersharebutton"));
        shareButton.click();
        mylog.info("Message is posted.");
      
        driver.switchTo().defaultContent();*/
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
        WebElement fileLink=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='publisherAttachContentPost']/span[1]")));
		clickElement(fileLink, "File link");
		
		 
        WebElement uploadLink=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='chatterUploadFileAction']")));
		clickElement(uploadLink, "Upload a file from your computer");
        
	    driver.findElement(By.xpath("//input[@id='chatterFile']")).sendKeys("/Users/nandy/Desktop/Sample/Textfile.txt");
		
	    WebElement shareButton = driver.findElement(By.id("publishersharebutton"));
	    clickElement(shareButton,"Share the file");
    
        Thread.sleep(5000);
        mylog.info("New file uploaded");
        mylog.info("Test case completed.");
        
	}
	
    @Test
	public void mysetting() throws InterruptedException {
    	
    	report.startSingleTestReport("My Settings");
        mylog.info("Starting the my settings page test.");
		
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 

		userDropdown("My Settings");
		
		
		//error will show in reports as its already added
		/*WebElement displayandlayout= driver.findElement(By.id("DisplayAndLayout"));
		clickElement(displayandlayout,"display and layout");
		
		WebElement custmytab= driver.findElement(By.xpath("//*[@id=\"DisplayAndLayout_child\"]/div[1]"));
		clickElement(custmytab,"customize my tab");
		
		WebElement custapp= driver.findElement(By.id("p4"));
		Select select = new Select(custapp);
		select.selectByVisibleText("Salesforce Chatter");
		
		WebElement selectreport= driver.findElement(By.id("duel_select_0"));
		Select select1 = new Select(selectreport);
		select1.selectByVisibleText("Reports");
		
		WebElement clickadd= driver.findElement(By.xpath("//*[@id='duel_select_0_right']/img"));
		clickElement(clickadd,"click add");
		
		WebElement savebutton= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savebutton, "save button");*/
		
		WebElement email= driver.findElement(By.xpath("//*[@id=\"EmailSetup\"]/a"));
		clickElement(email,"email");
		
		WebElement emailsettings= driver.findElement(By.xpath("//*[@id=\"EmailSettings_font\"]"));
		clickElement(emailsettings,"email settings");
		
		WebElement emailname=driver.findElement(By.id("sender_name"));
		enterText(emailname, "nandhika abcd", "email name");
		
		WebElement emailaddress=driver.findElement(By.id("sender_email"));
		enterText(emailaddress, "nandhika@tekarch.com", "emailid");
		
		WebElement autoccRadio=driver.findElement(By.xpath("//*[@id='auto_bcc1']"));
		selectElement(autoccRadio, "automatic cc radio button");
		 mylog.info("All the updates are made.");
		Thread.sleep(5000);
		
		WebElement savebutton= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savebutton, "save button");
		mylog.info("Test case completed.");
		
		
    }	
	
    @Test
	public void developerconsole() throws InterruptedException {
		
    	report.startSingleTestReport("Developer Console");
        mylog.info("Starting the Developer Console page test.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 

		userDropdown("Developer Console");
		mylog.info("Developer Console option clicked");
		
		Thread.sleep(3000);
		
		String mainWindow = driver.getWindowHandle(); //get current window handle
		
		// Switch to Developer Console window
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);

                Thread.sleep(5000);
                // Close the Developer Console window
                driver.close();
                break;
            }
        }
        
        // Switch back to the main window
        driver.switchTo().window(mainWindow);
        driver.quit();

        mylog.info("Developer Console page closed.");
        mylog.info("Test case completed.");
		
	}
	
    @Test
	public void logout() throws InterruptedException {
		
    	report.startSingleTestReport("Logout");
        mylog.info("Starting the Logout test.");
        
		login(); 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		userDropdown("Logout");
		mylog.info("Logout option clicked");
		
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
	
    @Test
	public void createaccount() throws InterruptedException {
    	report.startSingleTestReport("Create account");
        mylog.info("Starting the create account test.");
	
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement accountstab= driver.findElement(By.id("Account_Tab"));
		clickElement(accountstab, "accounts tab");
		
		WebElement newinaccountstab= driver.findElement(By.xpath("//input[@name='new']"));
		clickElement(newinaccountstab, "new button in accounts tab");
		mylog.info("New button for creating new account is clicked.");
		
		WebElement accountname=driver.findElement(By.xpath("//*[contains(@id,'acc2')]"));
		enterText(accountname, "tekarch US", "accountname");
		

		WebElement type = driver.findElement(By.xpath("//select[@id='acc6']"));
		Select select = new Select(type);
		select.selectByVisibleText("Technology Partner");
		

		WebElement custpriority = driver.findElement(By.xpath("//*[@id='00Nbm000004LSF9']"));
		Select select1 = new Select(custpriority);
		select1.selectByVisibleText("High");
		
		WebElement savebutton= driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]"));
		clickElement(savebutton, "save button");
		
		mylog.info("Test case completed.");
		
	}
	
	@Test
	public void createnewviewinaccount() throws InterruptedException {
		report.startSingleTestReport("Create new view in accounts.");
        mylog.info("Starting the create new view in accounts test.");
		
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement accountstab= driver.findElement(By.id("Account_Tab"));
		clickElement(accountstab, "accounts tab");
		
		WebElement createnewview= driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		clickElement(createnewview, "create new view");
		
		WebElement viewname=driver.findElement(By.xpath("//*[@id=\"fname\"]"));
		enterText(viewname, "2nd view", "view name");
		
		WebElement viewuniquename=driver.findElement(By.xpath("//*[@id=\"devname\"]"));
		enterText(viewuniquename, "X2nd_view", "unique view name");
		
		WebElement savebutton= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savebutton, "save button");
		mylog.info("Test case completed.");
	}
	
	@Test
	public void editviewinaccount() throws InterruptedException {
		report.startSingleTestReport("Create edit view in accounts.");
        mylog.info("Starting the create edit view in accounts test.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement accountstab= driver.findElement(By.id("Account_Tab"));
		clickElement(accountstab, "accounts tab");
		
		WebElement viewdropdown = driver.findElement(By.id("fcf"));
		Select select = new Select(viewdropdown);
		select.selectByVisibleText("2nd view");
		
		WebElement clickedit= driver.findElement(By.xpath("//*[@id='filter_element']/div/span/span[2]/a[1]"));
		clickElement(clickedit, "click edit");
		
		WebElement changeviewname = driver.findElement(By.xpath("//*[@id='fname']"));
		enterText(changeviewname, "new 2nd view", "new view name");
		
		WebElement fieldname = driver.findElement(By.id("fcol1"));
		Select select1 = new Select(fieldname);
		select1.selectByVisibleText("Account Name");
		
		WebElement operator = driver.findElement(By.id("fop1"));
		Select select2 = new Select(operator);
		select2.selectByVisibleText("contains");

		WebElement value = driver.findElement(By.id("fval1"));
		enterText(value, "a", "value");
		
		WebElement savebutton= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savebutton, "save button");
		
		
		WebElement mergeaccounts= driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a"));
		clickElement(mergeaccounts, "merge accounts");
		mylog.info("Test case completed.");
	}
	
	@Test
	public void mergeaccounts() throws InterruptedException {
		report.startSingleTestReport("Merge accounts.");
        mylog.info("Starting the Merge accounts test.");
		
		login();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement accountstab= driver.findElement(By.id("Account_Tab"));
		clickElement(accountstab, "accounts tab");
		
		WebElement mergeaccounts= driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a"));
		clickElement(mergeaccounts, "merge accounts");
		
		WebElement enteraccountname = driver.findElement(By.id("srch"));
		enterText(enteraccountname, "tekarch", "name");

		WebElement clickfindaccounts= driver.findElement(By.name("srchbutton"));
		clickElement(clickfindaccounts, "find accounts");
		
		WebElement clicknext= driver.findElement(By.name("goNext"));
		clickElement(clicknext, "next");
		
		WebElement clickmerge= driver.findElement(By.xpath("//*[@name='save']"));
		clickElement(clickmerge, "merge");
		mylog.info("Accounts merged");
		
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		mylog.info("Test case completed.");
		
	}
	
	@Test
	public void createaccountreport() throws InterruptedException {
		
		report.startSingleTestReport("Create accounts report.");
        mylog.info("Starting the create account report test.");
		
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement accountstab= driver.findElement(By.id("Account_Tab"));
		clickElement(accountstab, "accounts tab");
		
		WebElement accountswithact= driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a"));
		clickElement(accountswithact, "accounts with act within 30days");
		
		WebElement calender= driver.findElement(By.id("ext-gen152"));
		clickElement(calender, "calender");
		
		WebElement todaysdate= driver.findElement(By.id("ext-gen280"));
		clickElement(todaysdate, "todaysdate");
		
		WebElement calender1= driver.findElement(By.id("ext-gen154"));
		clickElement(calender1, "calender");
		
		WebElement todaysdate1= driver.findElement(By.id("ext-gen295"));
		clickElement(todaysdate1, "todaysdate");
		
		WebElement savebutton= driver.findElement(By.id("saveReportBtn"));
		clickElement(savebutton, "save button");
		
		WebElement reportname=driver.findElement(By.xpath("//*[@id=\"saveReportDlg_reportNameField\"]"));
		enterText(reportname, "report2", "report name");
		
		WebElement reportuniquename=driver.findElement(By.xpath("//*[@id=\"saveReportDlg_DeveloperName\"]"));
		enterText(reportuniquename, "report_2", "report unique name");
		
		Thread.sleep(5000);
		WebElement saveandrun= driver.findElement(By.id("dlgSaveAndRun"));
		clickElement(saveandrun, "save");
		
		Thread.sleep(5000);
		
		String actualReortname = driver.findElement(By.className("content")).getText();
	    String expectedreportname = "report2";

	      mylog.info("Expected Error Message: " + expectedreportname);
	      mylog.info("Actual Error Message: " + actualReortname);

	        try {
	            Assert.assertEquals(actualReortname, expectedreportname);
	            mylog.info("Acount Report created.");
	            
	        } catch (AssertionError e) {
	        	mylog.error("Account report not create. Test failed.");
	            
	            throw e;  
	        }
	        mylog.info("Test case completed.");
	}
	
	@Test
	public void opportunitiesdropdown() throws InterruptedException {
		
		report.startSingleTestReport("Opportunites dropdown.");
        mylog.info("Starting the opportunited dropdown test.");
        
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement opportunitiestab= driver.findElement(By.id("Opportunity_Tab"));
		clickElement(opportunitiestab, "opportunities tab");
		
		
		WebElement viewtab= driver.findElement(By.id("fcf"));
		Select select = new Select(viewtab);
		List<WebElement> allOptions = select.getOptions(); 
		for (WebElement option :allOptions) { 
		mylog.info(option.getText()); 
		}
		mylog.info("All contents are present");
        mylog.info("Test case completed.");
		
	}
	
	@Test
	public void createnewopp() throws InterruptedException {
		
		report.startSingleTestReport("create new opportunites");
        mylog.info("Starting the create new opportunites test.");
        
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement opportunitiestab= driver.findElement(By.id("Opportunity_Tab"));
		clickElement(opportunitiestab, "opportunities tab");
		
		WebElement clicknew= driver.findElement(By.xpath("//input[@name='new']"));
		clickElement(clicknew, "click new");
		
		WebElement oppname=driver.findElement(By.id("opp3"));
		enterText(oppname, "2nd opp", "opp name");
		
		WebElement accountname = driver.findElement(By.id("opp4"));
		enterText(accountname, "tekarch", "tekarch");
		
		WebElement stage = driver.findElement(By.id("opp11"));
		Select select1 = new Select(stage);
		select1.selectByVisibleText("Prospecting");
		
		WebElement leadsource = driver.findElement(By.id("opp6"));
		Select select = new Select(leadsource);
		select.selectByVisibleText("Web");
		
		WebElement probability=driver.findElement(By.id("opp12"));
		enterText(probability, "10", "probability");
		
		WebElement calender= driver.findElement(By.id("opp9"));
		clickElement(calender, "calender");
		
		WebElement todaysdate= driver.findElement(By.className("buttonBar"));
		clickElement(todaysdate, "todaysdate");
		
		WebElement save=driver.findElement(By.className("btn"));
		clickElement(save, "save");
		Thread.sleep(5000);
		
		 mylog.info("New opportunites is created.");
		 mylog.info("Test case completed.");
		
	}
	
	@Test
	public void testopppipelinereport() throws InterruptedException {

		report.startSingleTestReport("Test opportunites pipeline report.");
        mylog.info("Starting the opportunites pipeline report test.");
        
		login();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement opportunitiestab= driver.findElement(By.id("Opportunity_Tab"));
		clickElement(opportunitiestab, "opportunities tab");
		
		WebElement OppPipelinelink = driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a"));
		clickElement(OppPipelinelink, "Opportunity Pipeline link ");
		
		String actualTitle = driver.getTitle();
	     String expectedTitle = "Opportunity Pipeline ~ Salesforce - Developer Edition";

	        mylog.info("Expected Title: " + expectedTitle);
	        mylog.info("Actual Title: " + actualTitle);

	       
	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("Opportunity Pipeline link successful. Titles match.");
	        } catch (AssertionError e) {
	            mylog.error("Opportunity Pipeline link not successful. Titles do not match.", e);
	            throw e;  
	        }
	        mylog.info("Test case completed.");
	}
	
	@Test
	public void teststuckoppreport() throws InterruptedException {
		
		report.startSingleTestReport("Test Stuck Opportunities Report.");
        mylog.info("Starting the Stuck Opportunities Report test.");
        
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement opportunitiestab= driver.findElement(By.id("Opportunity_Tab"));
		clickElement(opportunitiestab, "opportunities tab");
		
		
		WebElement  StuckOpplink   = driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[2]/a"));
		clickElement(StuckOpplink, " Stuck Opportunities link");
		
		String actualTitle = driver.getTitle();
	    String expectedTitle = "Stuck Opportunities ~ Salesforce - Developer Edition";

	        mylog.info("Expected Title: " + expectedTitle);
	        mylog.info("Actual Title: " + actualTitle);

	       
	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("Stuck Opportunities link successful. Titles match.");
	        } catch (AssertionError e) {
	            mylog.error("Stuck Opportunities link not successful. Titles do not match.", e);
	            throw e;  
	        }
	        mylog.info("Test case completed.");
	}
	
	@Test
	public void testquaterlysummary() throws InterruptedException {
		report.startSingleTestReport("Test Opportunities Report.");
        mylog.info("Starting the Opportunities Report test.");
        
        login();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
	
        WebElement opportunitiestab= driver.findElement(By.id("Opportunity_Tab"));
        clickElement(opportunitiestab, "opportunities tab");
	
        WebElement interval = driver.findElement(By.id("quarter_q"));
        selectByIndex(interval , 2);
	
        WebElement include = driver.findElement(By.id("open"));
        selectByIndex(include, 3);
	
        WebElement reportbutton= driver.findElement(By.xpath("//input[@value=\"Run Report\"]"));
        clickElement(reportbutton, "Report button");
	
        String actualTitle = driver.getTitle();
	    String expectedTitle = "Opportunity Report ~ Salesforce - Developer Edition";

	        mylog.info("Expected Title: " + expectedTitle);
	        mylog.info("Actual Title: " + actualTitle);

	       
	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("Opportunities Report page displayed. Titles match.");
	        } catch (AssertionError e) {
	            mylog.error("Opportunities Report page not displayed. Titles do not match.", e);
	            throw e;  
	        }
	        mylog.info("Test case completed.");
	
	}
	
	@Test
	public void checkleadstablink() throws InterruptedException {
		
		report.startSingleTestReport("Leads homepage");
        mylog.info("Starting the leads homepage test.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement leadstab= driver.findElement(By.id("Lead_Tab"));
		clickElement(leadstab, "leads tab");
		
		String actualTitle = driver.getTitle();
	    String expectedTitle = "Leads: Home ~ Salesforce - Developer Edition";

	        mylog.info("Expected Title: " + expectedTitle);
	        mylog.info("Actual Title: " + actualTitle);

	       
	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("Leads home page displayed. Titles match.");
	        } catch (AssertionError e) {
	            mylog.error("Leads home page not displayed. Titles do not match.", e);
	            throw e;  
	        }
	        mylog.info("Test case completed.");

	}
	
	@Test
	public void ValidateViewdropdownlistcontents() throws InterruptedException {
		
		report.startSingleTestReport("Validate View dropdown list contents");
        mylog.info("Starting the Validate View dropdown list contents test.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement leadstab= driver.findElement(By.id("Lead_Tab"));
		clickElement(leadstab, "leads tab");
		
		WebElement viewtab= driver.findElement(By.id("fcf"));
		clickElement(viewtab, "view tab");
		
		WebElement viewtab1= driver.findElement(By.id("fcf"));
		Select select = new Select(viewtab1);
		List<WebElement> allOptions = select.getOptions(); 
		for (WebElement option :allOptions)
		{ 
		System.out.println(option.getText()); 
		}
		  mylog.info("Leads page contents displayed.");
		  mylog.info("Test case completed.");
	}
	
	@Test
	public void FunctionalityoftheGoButton() throws InterruptedException {
		
		report.startSingleTestReport("Validate Go Button");
        mylog.info("Starting the Validate Go button test.");
		login();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement leadstab= driver.findElement(By.id("Lead_Tab"));
		clickElement(leadstab, "leads tab");
		
		WebElement todayslead= driver.findElement(By.id("fcf"));
		Select select = new Select(todayslead);
		select.selectByIndex(3); 
	
		userDropdown("Logout");
		login();
		WebElement leadstab1= driver.findElement(By.id("Lead_Tab"));
		clickElement(leadstab1, "leads tab");
		
		WebElement gobutton=driver.findElement(By.xpath("//input[@title='Go!']"));
		clickElement(gobutton, "Go");
		
		String actualTitle = driver.getTitle();
	    String expectedTitle = "Leads ~ Salesforce - Developer Edition";

	        mylog.info("Expected Title: " + expectedTitle);
	        mylog.info("Actual Title: " + actualTitle);

	       
	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("Leads page displayed. Titles match.");
	        } catch (AssertionError e) {
	            mylog.error("Leads page not displayed. Titles do not match.", e);
	            throw e;  
	        }
	        mylog.info("Test case completed.");
		
	}
	
	@Test
	public void ListitemTodaysLeadswork() throws InterruptedException {
		
		report.startSingleTestReport("Validate Go Button");
        mylog.info("Starting the Validate Go button test.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement leadstab= driver.findElement(By.id("Lead_Tab"));
		clickElement(leadstab, "leads tab");
		
		WebElement todayslead= driver.findElement(By.id("fcf"));
		Select select = new Select(todayslead);
		select.selectByIndex(3); 
		
		WebElement gobutton= driver.findElement(By.xpath("//*[@value=\" Go! \"]"));
		clickElement(gobutton, "go button");
		
		String actualTitle = driver.getTitle();
	    String expectedTitle = "Leads ~ Salesforce - Developer Edition";

	        mylog.info("Expected Title: " + expectedTitle);
	        mylog.info("Actual Title: " + actualTitle);

	       
	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("Todays leads page displayed. Titles match.");
	        } catch (AssertionError e) {
	            mylog.error("Todays leads page not displayed. Titles do not match.", e);
	            throw e;  
	        }
	        mylog.info("Test case completed.");
	}
	
	@Test
	public void CheckNewbuttononLeads() throws InterruptedException {
		
		report.startSingleTestReport("Check new button on leads page");
        mylog.info("Starting the validation of new button on leads page.");
		login();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement leadstab= driver.findElement(By.id("Lead_Tab"));
		clickElement(leadstab, "leads tab");

		WebElement newbutton= driver.findElement(By.xpath("//*[@value=' New ']"));
		clickElement(newbutton, "new button");
		
		WebElement lastname=driver.findElement(By.id("name_lastlea2"));
		enterText(lastname, "ABCDEf", "last name");
		
		WebElement complayname=driver.findElement(By.id("lea3"));
		enterText(complayname, "tkarch", "username");
		
		WebElement savebutton= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savebutton, "save button");
		
		String actualTitle = driver.getTitle();
	    String expectedTitle = "Lead: ABCDEf ~ Salesforce - Developer Edition";

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
	
	@Test
	public void createnewcontact() throws InterruptedException {
		
		report.startSingleTestReport("Create new contact");
        mylog.info("Starting Create new contact test.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement contacttab= driver.findElement(By.id("Contact_Tab"));
		clickElement(contacttab, "contact tab");
		
		WebElement newbutton= driver.findElement(By.xpath("//*[@value=' New ']"));
		clickElement(newbutton, "new button");
		
		WebElement lastname=driver.findElement(By.id("name_lastcon2"));
		enterText(lastname, "sampat", "last name");
		
		WebElement accountname=driver.findElement(By.id("con4"));
		enterText(accountname, "Tekarch", "username");
		
		WebElement savebutton= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savebutton, "save button");
		
		mylog.info("New button on contacts page validated.");
		 mylog.info("Test case completed.");
		
	}
	
	@Test
	public void createnewviewincontact() throws InterruptedException {
		
		report.startSingleTestReport("create new view in contacts page");
        mylog.info("Starting the create new view in contacts test.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement contacttab= driver.findElement(By.xpath("//*[@id='Contact_Tab']"));
		clickElement(contacttab, "contact tab");
		
		WebElement createnewview= driver.findElement(By.xpath("//*[@class='fFooter']//a[2]"));
		clickElement(createnewview, "create new view");
		

		WebElement viewname=driver.findElement(By.id("fname"));
		enterText(viewname, "nandhu view", "view name");
		
		WebElement viewuniquename=driver.findElement(By.id("devname"));
		enterText(viewuniquename, "", "view name");
		
		WebElement savebutton= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savebutton, "save button");
		

		WebElement viewtab= driver.findElement(By.xpath("//*[@class=\"title\"]"));
		Select select = new Select(viewtab);
		List<WebElement> allOptions = select.getOptions(); 
		for (WebElement option :allOptions)
		{ 
		System.out.println(option.getText()); 
		}
		 
		mylog.info("Newly created view id present in the dropdown.");
		 mylog.info("Test case completed.");
		
	}
	
	@Test
	public void checkrecentlycreated() throws InterruptedException {
	
		report.startSingleTestReport("check recently created");
        mylog.info("Starting the recently created test.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement contacttab= driver.findElement(By.xpath("//*[@id='Contact_Tab']"));
		clickElement(contacttab, "contact tab");
		
	
		WebElement recentlycreated= driver.findElement(By.xpath("//*[@id=\"hotlist_mode\"]"));
		Select select = new Select(recentlycreated);
		select.selectByVisibleText("Recently Created");
		
		mylog.info("Newly created contact present.");
		 mylog.info("Test case completed.");
		
	}
	
	@Test
	public void checkmycontacts() throws InterruptedException {
		
		report.startSingleTestReport("check my contacts");
        mylog.info("Starting the check my contacts test.");

		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement contacttab= driver.findElement(By.xpath("//*[@id='Contact_Tab']"));
		clickElement(contacttab, "contact tab");
		
		WebElement mycontacts= driver.findElement(By.id("fcf"));
		Select select = new Select(mycontacts);
		select.selectByIndex(4);
	
		String actualTitle = driver.getTitle();
	    String expectedTitle = "Contacts ~ Salesforce - Developer Edition";

	        mylog.info("Expected Title: " + expectedTitle);
	        mylog.info("Actual Title: " + actualTitle);

	       
	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("Contacts page displayed. Titles match.");
	        } catch (AssertionError e) {
	            mylog.error("Contacts page not displayed. Titles do not match.", e);
	            throw e;  
	        }
	        mylog.info("Test case completed.");
		
		
	}
	
	@Test
    public void viewacontact() throws InterruptedException {
	
		report.startSingleTestReport("check my contacts");
        mylog.info("Starting the check my contacts test.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement contacttab= driver.findElement(By.xpath("//*[@id='Contact_Tab']"));
		clickElement(contacttab, "contact tab");
		
		WebElement recentcontacts= driver.findElement(By.linkText("sampath"));
		clickElement(recentcontacts, "recent contact name");
		
		String actualTitle = driver.getTitle();
	    String expectedTitle = "Contact: sampath ~ Salesforce - Developer Edition";

	        mylog.info("Expected Title: " + expectedTitle);
	        mylog.info("Actual Title: " + actualTitle);

	       
	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("Recently created Contacts page displayed. Titles match.");
	        } catch (AssertionError e) {
	            mylog.error("Recently created Contacts page not displayed. Titles do not match.", e);
	            throw e;  
	        }
	        mylog.info("Test case completed.");
		
		mylog.info("Recently created contact is clicked.");
		mylog.info("Test case completed.");
		
    }
	
	@Test
    public void validerrormessage() throws InterruptedException {
		
		report.startSingleTestReport("valid error message");
        mylog.info("Starting the validation of error message.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement contacttab= driver.findElement(By.xpath("//*[@id='Contact_Tab']"));
		clickElement(contacttab, "contact tab");
		
		WebElement createnewview= driver.findElement(By.xpath("//*[@class='fFooter']//a[2]"));
		clickElement(createnewview, "create new view");
		
		WebElement viewuniquename=driver.findElement(By.id("devname"));
		enterText(viewuniquename, "EFGH", "view name");
		
		WebElement savebutton= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savebutton, "save button");
		
		String actualErrorMessage = driver.findElement(By.className("errorMsg")).getText();
	    String expectedErrorMessage = "Error: You must enter a value";

	      mylog.info("Expected Error Message: " + expectedErrorMessage);
	      mylog.info("Actual Error Message: " + actualErrorMessage);

	       
	        try {
	            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "The error message displayed is incorrect.");
	            mylog.info("Error message displayed.");
	            
	        } catch (AssertionError e) {
	        	mylog.error("Error message did not match. Test failed.");
	            
	            throw e;  
	        }
	        mylog.info("Test case completed.");
    }
    
	@Test
    public void checkcancelbutton() throws InterruptedException {
		
		report.startSingleTestReport("check cancel button");
        mylog.info("Starting the validation of cancel button.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement contacttab= driver.findElement(By.xpath("//*[@id='Contact_Tab']"));
		clickElement(contacttab, "contact tab");
		
		WebElement createnewview= driver.findElement(By.xpath("//*[@class='fFooter']//a[2]"));
		clickElement(createnewview, "create new view");
		

		WebElement viewname=driver.findElement(By.id("fname"));
		enterText(viewname, "ABCD", "view name");
		
		WebElement viewuniquename=driver.findElement(By.id("devname"));
		enterText(viewuniquename, "EFGH", "view name");
		
		WebElement cancelbutton= driver.findElement(By.xpath("//*[@name='cancel']"));
		clickElement(cancelbutton, "cancel button");
		
		String actualTitle = driver.getTitle();
	    String expectedTitle = "Contacts: Home ~ Salesforce - Developer Edition";

	        mylog.info("Expected Title: " + expectedTitle);
	        mylog.info("Actual Title: " + actualTitle);

	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("Back to Contacts page. Titles match.");
	        } catch (AssertionError e) {
	            mylog.error("Contacts page not displayed. Titles do not match.", e);
	            throw e;  
	        }
	        mylog.info("Test case completed.");
	}
    
	@Test
    public void checknewandsavebutton() throws InterruptedException {
		
		report.startSingleTestReport("check new and save button");
        mylog.info("Starting the validation of new and save button.");
        
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement contacttab= driver.findElement(By.xpath("//*[@id='Contact_Tab']"));
		clickElement(contacttab, "contact tab");
		
		WebElement clicknew= driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
		clickElement(clicknew, " new ");
		
		WebElement lastname=driver.findElement(By.id("name_lastcon2"));
		enterText(lastname, "indian", "view name");
		
		WebElement accname=driver.findElement(By.xpath("//*[@id='con4']"));
		enterText(accname, "Tekarch", "view name");
		
		WebElement savenewbutton= driver.findElement(By.xpath("//input[@name='save_new']"));
		clickElement(savenewbutton, "save and new button");
		
		String actualTitle = driver.getTitle();
	    String expectedTitle = "Contact Edit: New Contact ~ Salesforce - Developer Edition";

	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("Contact edit page opened");
	        } catch (AssertionError e) {
	            mylog.error("Contact edit page not opened", e);
	            throw e;  
	        }
		
		Thread.sleep(5000);
		mylog.info("New contacts created.");
		mylog.info("Test case completed.");
		
	}

    @Test
    public void validatehomepage() throws InterruptedException {
    	report.startSingleTestReport("validate homepage");
        mylog.info("Starting the validation of home page.");
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement hometab= driver.findElement(By.id("home_Tab"));
		clickElement(hometab, "home tab");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement name=driver.findElement(By.xpath("//*[@class='currentStatusUserName']"));
		clickElement(name, "full name link");
		
		String profiletitle=driver.getTitle();
		mylog.info(profiletitle);

		userDropdown("My Profile");
		
		String myprofiletitle=driver.getTitle();
		mylog.info(myprofiletitle);
		
		try {
            Assert.assertEquals(profiletitle, myprofiletitle);
            mylog.info("Titles matched.Both pages are same");
        } catch (AssertionError e) {
            mylog.error("Both pages are different. Titles do not match.", e);
            throw e;  
        }
        mylog.info("Test case completed.");
    }
    
    @Test
    public void validatehomepageedit() throws InterruptedException {
    	report.startSingleTestReport("validate homepage edit option");
    	mylog.info("Starting the validation of home page edit option.");
    	
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		userDropdown("My Profile");
		
		WebElement editprofile= driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img"));
		clickElement(editprofile,"edit profile");
		
		driver.switchTo().frame("contactInfoContentId");
        
        WebElement aboutTab = driver.findElement(By.id("aboutTab"));
        clickElement(aboutTab,"about tab");
        
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        enterText(lastNameField, "abcd", "last name");
     
        WebElement saveButton = driver.findElement(By.xpath("//*[@value=\"Save All\"]"));
        saveButton.click();
		
        driver.switchTo().defaultContent();	
        
        String actualTitle = driver.findElement(By.id("tailBreadcrumbNode")).getText();
	    String expectedTitle = "Nandhika abcd ";

	        mylog.info("Expected Title: " + expectedTitle);
	        mylog.info("Actual Title: " + actualTitle);

	        try {
	            Assert.assertEquals(actualTitle, expectedTitle);
	            mylog.info("Names matched,therefore edit icon works.");
	        } catch (AssertionError e) {
	            mylog.error("Names doesnot matched,therefore edit icon failed.", e);
	            throw e;  
	        }
	        mylog.info("Test case completed.");
    }
    
    @Test
    public void verifytabcustomization() throws InterruptedException {
    	
    	report.startSingleTestReport("verify tab customization");
    	mylog.info("Starting the validation of tab customization");
    	
		login();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
    	
    	WebElement addtab=driver.findElement(By.xpath("//*[@id=\"AllTab_Tab\"]"));
    	clickElement(addtab, "customization tab");
    	
    	WebElement custmytab=driver.findElement(By.xpath("//*[@value=\"Customize My Tabs\"]"));
    	clickElement(custmytab, "Customize My Tabs");
		
		WebElement selectreport= driver.findElement(By.id("duel_select_1"));
		Select select1 = new Select(selectreport);
		select1.selectByVisibleText("Leads");
		
		WebElement clickremove= driver.findElement(By.xpath("//*[@id='duel_select_0_left']/img"));
		clickElement(clickremove,"click remove");
		
		WebElement savebutton= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savebutton, "save button");
		
		userDropdown("Logout");
		login();
		
		List<WebElement> tabs = driver.findElements(By.xpath("//*[@id='tabBar']/li/a"));
	    Thread.sleep(5000);
	    for (WebElement tab : tabs) {
            mylog.info(tab.getText());   
	    }
	    
	    WebDriverWait wait = new WebDriverWait(driver, 10); // 10 seconds timeout

	    try {
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Lead_Tab")));
	        mylog.error("Leads tab is still available. Test failed.");
	        Assert.fail("Leads tab should not be present, but it is still visible.");
	    } catch (org.openqa.selenium.TimeoutException e) {
	        mylog.info("Leads tab is not available as expected. Test passed.");
	    }

		  mylog.info("Leads tab removed.");
		  mylog.info("Test case completed.");
		
    }
    
    @Test
    public void blockinganevent() throws InterruptedException {
    	
    	report.startSingleTestReport("blocking an event");
    	mylog.info("Starting the validation of blocking an event");
    	login();

    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement hometab= driver.findElement(By.id("home_Tab"));
		clickElement(hometab, "home tab");
		

		WebElement date=driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a"));
		clickElement(date, "date link");
    	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement selecttimelink= driver.findElement(By.xpath("//*[@id=\"p:f:j_id25:j_id61:28:j_id64\"]/a"));
		clickElement(selecttimelink, "time");
		
		WebElement subjectcombo= driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img"));
		clickElement(subjectcombo, "subject combo");
		
		String mainWindow = driver.getWindowHandle(); //get current window handle
		
		// Switch to Developer Console window
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);

                Thread.sleep(5000);
                
                WebElement selectother= driver.findElement(By.xpath("/html/body/div[2]/ul/li[5]/a"));
        		clickElement(selectother, "date");
                
                break;
            }
        }
        
        driver.switchTo().window(mainWindow);
        
        WebElement endtime= driver.findElement(By.xpath("//*[@id=\"EndDateTime_time\"]"));
		clickElement(endtime, "end time field");
		
		WebElement selecttime=driver.findElement(By.xpath("//*[@id=\"timePickerItem_42\"]"));
		clickElement(selecttime, "end time");
		
		WebElement savebutton= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savebutton, "save button");
		
		mylog.info("Event blocked.");
		mylog.info("Test case completed.");
    }
    
    @Test
    public void blockinganeventwithrecurrance() throws InterruptedException {
    	
    	report.startSingleTestReport("blocking an event");
    	mylog.info("Starting the validation of blocking an event");
    	login();
    	
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		WebElement hometab= driver.findElement(By.id("home_Tab"));
		clickElement(hometab, "home tab");
		

		WebElement date=driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a"));
		clickElement(date, "date link");
    	
		Thread.sleep(5000);
		
		WebElement selecttimelink= driver.findElement(By.xpath("//*[@id=\"p:f:j_id25:j_id61:20:j_id64\"]/a"));
		clickElement(selecttimelink, "time");
		
		WebElement subjectcombo= driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img"));
		clickElement(subjectcombo, "subject combo");
		
		String mainWindow = driver.getWindowHandle(); //get current window handle
		
		// Switch to Developer Console window
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);

                Thread.sleep(5000);
                
                WebElement selectother= driver.findElement(By.xpath("/html/body/div[2]/ul/li[5]/a"));
        		clickElement(selectother, "date");
                
                break;
            }
        }
        driver.switchTo().window(mainWindow);
        
        WebElement endtime= driver.findElement(By.xpath("//*[@id=\"EndDateTime_time\"]"));
		clickElement(endtime, "end time field");
		
		WebElement selecttime=driver.findElement(By.xpath("//*[@id=\"timePickerItem_38\"]"));
		clickElement(selecttime, "end time");
		
		WebElement isrecc=driver.findElement(By.id("IsRecurrence"));
		clickElement(isrecc, "IsRecurrence");
		
		WebElement selectweekly=driver.findElement(By.id("rectypeftw"));
		clickElement(selectweekly, "weekly radio");
		
		WebElement selectendrecctime=driver.findElement(By.xpath("//*[@id=\"RecurrenceEndDateOnly\"]"));
		clickElement(selectendrecctime, "end recc date field");
		
		WebElement selectdate=driver.findElement(By.xpath("//*[@id=\"calRow5\"]/td[1]"));
		clickElement(selectdate, "end recc date");
		
		WebElement savebutton= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savebutton, "save button");
		
		WebElement monthviewicon= driver.findElement(By.xpath("//*[@id=\"bCalDiv\"]/div/div[2]/span[2]/a[3]/img"));
		clickElement(monthviewicon, "month view icon");
		
		Thread.sleep(5000);
		
		mylog.info("Event blocked with recurrance");
		mylog.info("Test case completed.");
 }
}
