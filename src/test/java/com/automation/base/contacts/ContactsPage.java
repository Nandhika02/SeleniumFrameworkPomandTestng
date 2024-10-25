package com.automation.base.contacts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.BasePage;

public class ContactsPage extends BasePage{

	public ContactsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath="//*[@id='Contact_Tab']") WebElement contactTab;
	@FindBy(xpath="//input[@name='new']") WebElement newbutton;
	@FindBy(id="name_lastcon2") WebElement lastname;
	@FindBy(xpath="//*[@id='con4']") WebElement accountname;
	@FindBy(xpath="//input[@name='save_new']") WebElement savenewButton;
	
	

	 public void clickContactsTab() {
	        clickElement(contactTab, "opportunites tab");
	    }

	    public void clickNewButton() {
	        clickElement(newbutton, "New button in opportunites tab");
	    }
	    
	    public void enterLastName(String lastName) {
	        enterText(lastname, lastName, "Last name");
	    }
	    
	    public void enteraccountName(String accountName) {
	        enterText(accountname, accountName, "Account name");
	    }
	    public void clicksaveandnewButton() {
	    	clickElement(savenewButton, "Save");
	    }
}
