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

    public LoginPage clickLogin() {
        UIElementExtensions.performClick(lnkLogin);
        return new LoginPage(driver);
    }

    public EmployeeListPage clickEmployeeList(){
        lnkEmployeeList.click();
        return new EmployeeListPage(driver);
    }

}
