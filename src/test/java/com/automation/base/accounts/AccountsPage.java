package com.automation.base.accounts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.automation.pages.base.BasePage;

public class AccountsPage extends BasePage {

	public AccountsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
	
	 @FindBy(id="Account_Tab") WebElement accountsTab;
	 @FindBy(xpath="//input[@name='new']") WebElement newbutton;
	 @FindBy(xpath="//*[contains(@id,'acc2')]") WebElement accountname;
	 @FindBy(xpath = "//select[@id='acc6']") WebElement typeDropdown;
	 @FindBy(xpath = "//*[@id='00Nbm000004LSF9']")WebElement customerPriorityDropdown;
	 @FindBy(xpath = "//*[@id='bottomButtonRow']/input[1]") WebElement saveButton;

	   
	    // Methods for page interactions

	    public void clickAccountsTab() {
	        clickElement(accountsTab, "Accounts tab");
	    }

	    public void clickNewButton() {
	        clickElement(newbutton, "New button in accounts tab");
	    }

	    public void enterAccountName(String accountName) {
	        enterText(accountname, accountName, "Account name");
	    }

	    public void selectType(String type) {
	        Select select = new Select(typeDropdown);
	        select.selectByVisibleText(type);
	    }

	    public void selectCustomerPriority(String priority) {
	        Select select = new Select(customerPriorityDropdown);
	        select.selectByVisibleText(priority);
	    }

	    public void clickSaveButton() {
	        clickElement(saveButton, "Save button");
	    }

	    public void createNewAccount(String accountName, String type, String priority) {
	        clickNewButton();
	        enterAccountName(accountName);
	        selectType(type);
	        selectCustomerPriority(priority);
	        clickSaveButton();
	    }
}
