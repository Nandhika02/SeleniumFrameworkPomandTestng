package com.automation.base.opportunities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.automation.pages.base.BasePage;

public class OpportunitesPage extends BasePage{

	public OpportunitesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
	 @FindBy(id="Opportunity_Tab") WebElement opportunitesTab;
	 @FindBy(xpath="//input[@name='new']") WebElement newbutton;
	 
	 @FindBy(id="opp3") WebElement oppname;
	 @FindBy(id = "opp4") WebElement accountname;
	 @FindBy(id = "opp11")WebElement stageDropdown;
	 @FindBy(id= "opp6") WebElement leadsourceDropdown;
	 
	 @FindBy(id= "opp12") WebElement probability;
	 @FindBy(id= "opp9") WebElement calender;
	 @FindBy(className= "buttonBar") WebElement todaysDate;
	 @FindBy(className= "btn") WebElement saveButton;
	 @FindBy(xpath="//*[@id='toolsContent']/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a") WebElement oppPipelinelink;
	                      //*[@id="toolsContent"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a
	
	
	
	 public void clickOppTab() {
	        clickElement(opportunitesTab, "opportunites tab");
	    }

	    public void clickNewButton() {
	        clickElement(newbutton, "New button in opportunites tab");
	    }

	    public void enterAccountName(String accountName) {
	        enterText(accountname, accountName, "Account name");
	    }
	    
	    public void enterOppName(String accountName) {
	        enterText(oppname, accountName, "Opportunities name");
	    }
	    
	    public void selectStage(String stage) {
	        Select select = new Select(stageDropdown);
	        select.selectByVisibleText(stage);
	    }
	    
	    public void selectLeadsource(String leadsource) {
	        Select select = new Select(leadsourceDropdown);
	        select.selectByVisibleText(leadsource);
	    }
	    
	    public void enterProbability(String prob) {
	    	enterText(probability,prob,"probability");
	    }
	    
	    public void clickCalender() {
	    	clickElement(calender, "close date");
	    }
	    
	    public void clicktodaysDate() {
	    	clickElement(todaysDate, "todays date");
	    }
	    
	    public void clicksaveButton() {
	    	clickElement(saveButton, "Save");
	    }
	    
	    public void clickPipelinelink() {
	    	clickElement(oppPipelinelink,"Opportunities Pipeline link");
	    }
}
