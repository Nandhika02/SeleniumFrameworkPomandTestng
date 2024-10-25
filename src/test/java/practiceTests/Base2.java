package practiceTests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base2 {

	
	protected static WebDriver driver = null;

    // Launches the specified browser
    public static void launchBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            default:
                System.out.println("Browser not supported");
                break;
        }
    }

    // Navigates to the specified URL
    public static void goToUrl(String url) throws InterruptedException {
        driver.get(url);
        System.out.println(url + " is entered");
        Thread.sleep(5000); // Consider replacing with WebDriver wait
    }

    // Enters text into an element
    public static void enterText(WebElement ele, String data, String objectName) {
        if (ele.isDisplayed()) {
            ele.clear();
            ele.sendKeys(data);
            System.out.println("Data is entered in the " + objectName);
        } else {
            System.out.println(objectName + " textbox is not displayed");
        }
    }

    // Clicks on an element
    public static void clickElement(WebElement ele, String objectName) {
        if (ele.isEnabled()) {
            ele.click();
            System.out.println(objectName + " button is clicked");
        } else {
            System.out.println(objectName + " button is not displayed");
        }
    }

    // Select options in dropdowns
    public static void selectByValue(WebElement ele, String value) {
        Select select = new Select(ele);
        select.selectByValue(value);
    }

    public static void selectByText(WebElement ele, String value) {
        Select select = new Select(ele);
        select.selectByVisibleText(value);
    }

    public static void selectByIndex(WebElement ele, int value) {
        Select select = new Select(ele);
        select.selectByIndex(value);
    }

    // Selects an element (checkbox or radio button)
    public static void selectElement(WebElement ele, String objectName) {
        if (!ele.isSelected()) {
            ele.click();
            System.out.println(objectName + " button is selected");
        } else {
            System.out.println(objectName + " button is already selected");
        }
    }

    // Closes the WebDriver
    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            System.out.println("Driver closed");
        }
    }

    // User dropdown functionality
    public static void userDropdown(String option) {
        WebElement navButton = driver.findElement(By.xpath("//div[@id='userNavButton']"));
        clickElement(navButton, "User Navigation Button");

        List<WebElement> userDropdownMenu = driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
        for (WebElement userDropdown : userDropdownMenu) {
            if (userDropdown.getText().contains(option)) {
                userDropdown.click();
                System.out.println("Clicked on: " + option);
                break;
            }
        }
    }

    // Login method
    public static void login() throws InterruptedException {
        launchBrowser("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set implicit wait

        goToUrl("https://login.salesforce.com/");
        Thread.sleep(5000); // Consider replacing with a more efficient wait

        WebElement usernameEle = driver.findElement(By.xpath("//*[@id='username']"));
        enterText(usernameEle, "nandhika@tekarch.com", "username");

        WebElement passwordEle = driver.findElement(By.id("password"));
        enterText(passwordEle, "22UTA21@psg", "password");

        WebElement buttonEle = driver.findElement(By.id("Login"));
        clickElement(buttonEle, "Login Button");
    }

    // Switch to alert and return it
    public static Alert switchToAlert() {
        Alert alert = driver.switchTo().alert();
        System.out.println("Switched to an alert");
        return alert;
    }

    // Wait for the visibility of an element
    public static void waitForVisibility(WebElement ele, long timeInSec, String objectName) {
        System.out.println(objectName + " waiting for visibility for a maximum of " + timeInSec + " sec");
        WebDriverWait wait = new WebDriverWait(driver, timeInSec);
        wait.until(ExpectedConditions.visibilityOf(ele));
        System.out.println(objectName + " is visible now");
    }

    // Extract text from an element
    public static String getTextFromElement(WebElement ele, String objectName) {
        String data = null;
        if (ele.isDisplayed()) {
            data = ele.getText();
            System.out.println("Text is extracted from " + objectName);
        } else {
            System.out.println(objectName + " not displayed");
        }
        return data;
    }

    // Placeholder for screenshot functionality
    public static void takeScreenshot() {
        // Implement screenshot functionality
        System.out.println("Screenshot taken (functionality not yet implemented)");
    }
		
		
}









