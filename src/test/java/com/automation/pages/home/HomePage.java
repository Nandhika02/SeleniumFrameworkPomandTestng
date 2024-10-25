package com.automation.pages.home;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.pages.base.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

	@FindBy(linkText = "My Settings") WebElement mySettingsOption;
    @FindBy(linkText = "Developer Console")WebElement developerConsoleOption;
    @FindBy(xpath = "//a[@title='Logout']") WebElement logoutButton;  
    @FindBy(id = "userNavLabel") WebElement userDropdownMenu;
    @FindBy(xpath = "//*[@id='userNav-menuItems']/a") List<WebElement> userDropdownItems;
    @FindBy(linkText = "My Profile") WebElement myProfileOption;
    @FindBy(linkText = "Logout") WebElement logoutOption;
    @FindBy(linkText = "Switching to Lightning Experience") WebElement switchToLightningOption;
    
    @FindBy(xpath="//*[@class='currentStatusUserName']") WebElement namelink;
    @FindBy(id = "Lead_Tab") WebElement leadsTab;
    @FindBy(id="Account_Tab") WebElement accountsTab;
    @FindBy(id = "home_Tab") WebElement homeTab;
    @FindBy(id="Opportunity_Tab") WebElement opportunitesTab;
    @FindBy(xpath="//*[@id='Contact_Tab']") WebElement contactTab;
    
    
   
    public void clickHomeTab() {
        clickElement(homeTab, "Home Tab");
    }
    
    public void clickNameLink() {
    	clickElement(namelink, "Full Name link");
    }

    public void clickOpportunitesTab() {
    	clickElement(opportunitesTab,"Opportunites Tab");
    }
    
    public void clickLogout() {
        clickElement(logoutButton, "Logout Button");
    }

    public void openUserDropdown() {
        clickElement(userDropdownMenu, "User Dropdown Menu");
    }

    public List<WebElement> getDropdownMenuItems() {
        wait.until(ExpectedConditions.visibilityOfAllElements(userDropdownItems));
        return userDropdownItems;
    }
    
    public int getDropdownMenuItemCount() {
        return userDropdownItems.size();
    }
    
    // Method to log all dropdown item texts
    public void logDropdownItems() {
        for (WebElement item : userDropdownItems) {
            System.out.println(item.getText());
        }
    }
    
    public void clickContactsTab() {
        clickElement(contactTab, "Contacts Tab");
    }
    public void clickLeadsTab() {
        clickElement(leadsTab, "Leads Tab");
    }
    
    public void clickaccountsTab() {
        clickElement(accountsTab, "Contacts Tab");
    }
    
    public void clickmySettingsOption() {
        clickElement(mySettingsOption, "My Settings Option");
    }
    
    public void clickmyProfile() {
    	clickElement(myProfileOption, "My profile");
    }
   
}

