 package com.automation.pages.login;
 import org.openqa.selenium.Alert;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.support.FindBy;
 import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.pages.base.BasePage;

public class LoginPage extends BasePage {

	
    @FindBy(id = "username") WebElement userNameEle;
    @FindBy(id = "password")WebElement passwordElement;
    @FindBy(id = "Login") WebElement loginButtonElement;
    @FindBy(id= "error") WebElement errorMessageElement;

   
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);  // Initialize WebElements
    }

    // Method to enter the username
    public void enterUserName(String username) {
        enterText(userNameEle, username, "username field");
    }

    // Method to enter the password
    public void enterPassword(String password) {
       
     // Wait for the password field to be visible and enabled
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(passwordElement));
        wait.until(ExpectedConditions.elementToBeClickable(passwordElement));
        
      enterText(passwordElement, password, "password field");
    }

    // Method to click the login button
    public void clickLoginButton() {
        clickElement(loginButtonElement, "login button");
    }

    // Method to switch to an error alert
    public Alert switchToErrorAlert() {
        return switchToAlert();
    }

    // Method to get alert text for login error
    public String extractTextFromAlert(Alert alert) {
        return getAlertText(alert, "Login Error Alert");
    }
    
    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(errorMessageElement));  // Wait for the error message to be visible
        return errorMessageElement.getText();  
    }
}
