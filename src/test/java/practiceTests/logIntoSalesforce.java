package practiceTests;

import org.testng.annotations.Test;

import com.automation.utility.ExcelUtility;

import org.testng.annotations.BeforeClass;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.DataProvider;


import io.github.bonigarcia.wdm.WebDriverManager;

public class logIntoSalesforce {

	WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://login.salesforce.com/");
    }

    @Test(dataProvider = "LoginData")
    public void loginTest(String user, String pwd, String exp) {
        try {
            // Enter username
            WebElement username = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            username.clear();
            username.sendKeys(user);

            // Enter password
            WebElement passwordbox = driver.findElement(By.name("pw"));
            passwordbox.clear();
            passwordbox.sendKeys(pwd);

            // Click login button
            WebElement loginbutton = driver.findElement(By.xpath("//*[@id=\"Login\"]"));
            loginbutton.click();

            WebDriverWait wait = new WebDriverWait(driver, 10);

            if (exp.equals("Valid")) {
                // Valid login: wait for home page title
                boolean isHomePage = wait.until(ExpectedConditions.titleContains("Home Page"));
                Assert.assertTrue(isHomePage, "Expected 'Home Page' but got a different page.");
                
                // Perform logout for valid login
                WebElement navbutton = driver.findElement(By.xpath("//*[@id='userNavLabel']"));
                navbutton.click();
                WebElement logout = driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[5]"));
                logout.click();
                
                System.out.println("Valid login test passed.");
                
            } else if (exp.equals("Invalid")) {
                // Invalid login: wait for error message to appear
                WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
                Assert.assertTrue(errorMsg.isDisplayed(), "Expected an error message, but none was found.");
                System.out.println("Invalid login test passed: " + errorMsg.getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {
    	
        // Hardcoded login data; replace with Excel file reading logic if needed
        /*Object logindata[][] = {
            {"nandhika@tekarch.com", "22UTA21@psg", "Valid"},
            {"nandhia@tekarch.com", "22uta21@psg", "Invalid"},
            {"mandhika@tekarch.com", "22UTA21@PSG", "Invalid"},
            {"nandhika@tarch.com", "2UTA21@psg", "Invalid"},
            {"nandhikasam@tekarch.com", "22uTA21@psg", "Invalid"}
        };*/
    	
    	String path="./src/test/resources/Logindata.xlsx";
    	ExcelUtility xlutil=new ExcelUtility(path);
    	
    	int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);	
				
		Object logindata[][]=new Object[totalrows][totalcols];
			
		
		for(int i=1;i<=totalrows;i++) //1
		{
			for(int j=0;j<totalcols;j++) //0
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
				
		}

        return logindata;
    }

    @AfterClass
    void tearDown() {
        if (driver != null) {
            driver.quit();  // Close the browser and end the session
        }
    }
	
		
	}

