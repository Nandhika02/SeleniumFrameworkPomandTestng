package practiceTests;


//import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.time.Duration;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.utility.Constants;
import com.automation.utility.PropertiesUtility;

//import Assignment.ExcelUtility;

public class AutomationScripts extends BaseFirebase{

	@Test
	public void invalid_password_login() throws InterruptedException {

		WebElement usernameEle = driver.findElement(By.xpath("//*[@id=\"email_field\"]"));// 60sec
		waitForVisibility(usernameEle, 30, "username");
		String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
		enterText(usernameEle, usernameData, "username");
		
		WebElement passwordEle = driver.findElement(By.id("password_field"));
		enterText(passwordEle, "123", "password");

		WebElement buttonEle = driver.findElement(By.tagName("button"));
		clickElement(buttonEle, "login button");

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2))
				.withMessage("alert is not present after fluent wait waiting time 30sec")
				.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());

		try {
			//Alert alert = switchToAlert();
			//String data = getAlertText(alert, "login error alertbox");
			//AcceptAlert(alert);
			System.out.println("passed");
		} catch (NoAlertPresentException e) {
			
			Assert.fail("no alert apeared", e);
			
		}

	}
	
	@DataProvider(name="logindata")
	//public Object[][] readData(){
		//return ExcelUtility.readAllDataFromXlToArray(Constants.Student_XLDATA, "Sheet1");
	//}

	@Test //(dataProvider = "logindata")
	public void valid_login() {
		String expectedData = "Student Registration Form";

		Login_Firebase();
		WebElement homeTextEle = driver.findElement(By.tagName("h1"));
		//waitForVtextToBePresentInElement(homeTextEle, 30, "Student Registration Form", "HomePageText");

		String actualData = getTextFromElement(homeTextEle, "HomePageText");
	
		AssertJUnit.assertEquals(actualData, expectedData);
	
		/*catch (Throwable e) {
			System.out.println("inside catch------------------------------");
			String filename=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());	
			takeScreenshot(Constants.SCREENSHOTS_DIRECTORY_PATH+filename+".png");
		}*/
		

	}
	
	/*private Logger mylog = LogManager.getLogger(AutomationScripts.class);

	@DataProvider(name="logindata")
	public Object[][] readData(){
		return ExcelUtils.readAllDataFromXlToArray(Constants.Student_XLDATA, "Sheet1");
	}

	@Test //(dataProvider = "logindata")
	public void valid_login() {
		String expectedData = "Student Registration Form";

		String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
		String passwordData=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
		
		//driver=11
		LoginPage loginpage=new LoginPage(driver);
		loginpage.enterUserName(usernameData);
		loginpage.enterPassword(passwordData);
		driver=loginpage.clickLoginButton();
		System.out.println("driver===="+driver);
		HomePage homepage=new HomePage(driver);// 12
		String actualData=homepage.getTextFromStudentRegistrationFormText();
		
		
		Assert.assertEquals(actualData, expectedData);
	
		

	}
	
	@Test
	public void invalid_password_login() throws InterruptedException {
		String ExpAlertText="Error : The password is invalid or the user does not have a password.-----";
		String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
		
		
		//driver=11
		LoginPage loginpage=new LoginPage(driver);
		loginpage.enterUserName(usernameData);
		loginpage.enterPassword("1234");
		loginpage.clickLoginButton();
		Alert alert=loginpage.switchToErrorAlert();
		String actualAlertText=loginpage.extractTextFromAlert(alert);
		loginpage.acceptErrorAlert(alert);
		Assert.assertEquals(actualAlertText, ExpAlertText);

		
			

	}*/
}
