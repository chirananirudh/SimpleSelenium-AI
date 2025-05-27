package gettingStarted;

import extensions.UIElementExtensions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.HomePage;

import java.time.Duration;

public class SeleniumTest {

    public static void main(String[] args) {

        //1. Create a WebDriver with ChromeDriver object
        //2. Launch the browser
        WebDriver driver = new ChromeDriver();

        //3. Navigate to a specific URL
        driver.navigate().to("http://eaapp.somee.com");

        //POM Code
        HomePage homePage = new HomePage(driver);
        var loginPage = homePage.clickLogin();
        homePage = loginPage.performLogin("admin", "password");
        var employeeListPage = homePage.clickEmployeeList();
        var createEmployeePage = employeeListPage.clickCreateNew();
        createEmployeePage.createNewEmployee("AutoUser2", "22222", "autotestuser2@gmail.com", "20000", "Middle");

        Logoff(driver);

    }

    public static void Logoff(WebDriver driver) {
        driver.findElement(By.linkText("Log off")).click();
    }

}
