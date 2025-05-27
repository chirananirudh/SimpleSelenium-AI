package pages;

import extensions.UIElementExtensions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeeListPage {


    private WebDriver driver;

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(linkText = "Create New")
    private WebElement btnCreateNew;


    public CreateEmployeePage clickCreateNew(){
        UIElementExtensions.performClick(btnCreateNew);
        return new CreateEmployeePage(driver);
    }
}
