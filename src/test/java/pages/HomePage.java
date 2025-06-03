package pages;

import extensions.UIElementExtensions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locators for Login
    @FindBy(linkText = "Login")
    private WebElement lnkLogin;

    @FindBy(linkText = "Employee List")
    private WebElement lnkEmployeeList;

    @FindBy(linkText = "Log off")
    private WebElement lnkLogOff;

    public LoginPage clickLogin() {
        UIElementExtensions.performClick(lnkLogin);
        return new LoginPage(driver);
    }

    public EmployeeListPage clickEmployeeList(){
        UIElementExtensions.performClick(lnkEmployeeList);
        return new EmployeeListPage(driver);
    }

    public boolean isLogOffLinkDisplayed() {
        try {
            return lnkLogOff != null && lnkLogOff.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void performLogOff() {
        // Ensure lnkLogOff is not null and is displayed before clicking
        // This check is useful if performLogOff could be called when not logged in,
        // though isLogOffLinkDisplayed is a better check for that state.
        if (isLogOffLinkDisplayed()) {
            UIElementExtensions.performClick(lnkLogOff);
        } else {
            // Optionally, handle the case where LogOff link isn't there,
            // though in the current test flow, it should always be there if called.
            System.err.println("Log off link not displayed, cannot perform log off action from HomePage.");
            // Or throw an exception: throw new IllegalStateException("Log off link not available to click.");
        }
    }
}
