package com.automation.base.leads;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.BasePage;

public class LeadsPage extends BasePage{
	
	public LeadsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
	
	
	@FindBy(id = "Lead_Tab") WebElement leadsTab;
	@FindBy(xpath="//input[@name='new']") WebElement newbutton;
	@FindBy(id="name_lastlea2") WebElement lastname;
	@FindBy(id="lea3") WebElement companyname;
	@FindBy(xpath="//input[@name='save']") WebElement saveButton;
	
	

	 public void clickLeadsTab() {
	        clickElement(leadsTab, "opportunites tab");
	    }

	    public void clickNewButton() {
	        clickElement(newbutton, "New button in opportunites tab");
	    }

	    public void enterLastName(String lastName) {
	        enterText(lastname, lastName, "Last name");
	    }
	    
	    public void enterCompanyName(String companyName) {
	        enterText(companyname, companyName, "Company name");
	    }
	    public void clicksaveButton() {
	    	clickElement(saveButton, "Save");
	    }
	    

}
