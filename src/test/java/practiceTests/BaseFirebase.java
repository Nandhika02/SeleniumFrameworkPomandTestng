package practiceTests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.base.Base2;
import com.automation.utility.Constants;
import com.automation.utility.PropertiesUtility;

public class BaseFirebase extends Base2 {
	
	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeMethod(@Optional("chrome") String name) throws InterruptedException {
		System.out.println("*********************setUpBeforeMethod**************************");
		launchBrowser("chrome");
		String url=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"url");
		goToUrl(url);
		
	}
	
	@AfterMethod
	public void tearDownAfterMethod() {
		closeDriver();
		System.out.println("****************tearDownAfterTestMethod*******************");
	}
	
	public void Login_Firebase() {
		String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
		String passwordData=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
		
		WebElement usernameEle = driver.findElement(By.xpath("//*[@id=\"email_field\"]"));
		waitForVisibility(usernameEle, 30, "username");
		enterText(usernameEle,usernameData, "username");

		WebElement passwordEle = driver.findElement(By.id("password_field"));
		enterText(passwordEle,passwordData, "password");

		WebElement buttonEle = driver.findElement(By.tagName("button"));
		clickElement(buttonEle, "login button");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
