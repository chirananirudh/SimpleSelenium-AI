package gettingStarted;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert; // Added TestNG Assert
import pages.CreateEmployeePage;
import pages.EmployeeListPage;
import pages.HomePage;
import pages.LoginPage;

public class SeleniumTest {

    private WebDriver driver;
    private HomePage homePage;
    private Properties properties;

    @BeforeMethod
    public void setUp() {
        // Load properties
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/testdata.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not read testdata.properties file", e);
        }

        //1. Create a WebDriver with ChromeDriver object
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu"); // often recommended with headless
//        options.addArguments("--window-size=1920,1080"); // optional, but good for consistency
//        options.addArguments("--no-sandbox"); //  Bypass OS security model, REQUIRED for running as root user (often the case in CI)
//        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//        options.addArguments("--remote-allow-origins=*"); // allow all origins
//        options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/chrome-user-data-" + System.currentTimeMillis());

        //2. Launch the browser
        driver = new ChromeDriver(options);
        
        //3. Navigate to a specific URL
        driver.navigate().to(properties.getProperty("url"));

        //Initialize Page Object
        homePage = new HomePage(driver);
    }

    @Test
    public void testCreateEmployee() {
        //POM Code
        LoginPage loginPage = homePage.clickLogin();
        homePage = loginPage.performLogin(properties.getProperty("username"), properties.getProperty("password"));
        // Assertion for successful login
        Assert.assertTrue(homePage.isLogOffLinkDisplayed(), "Login failed: Log off link not displayed.");

        EmployeeListPage employeeListPage = homePage.clickEmployeeList();
        CreateEmployeePage createEmployeePage = employeeListPage.clickCreateNew();
        // Store the returned EmployeeListPage after creating an employee
        employeeListPage = createEmployeePage.createNewEmployee(
            properties.getProperty("employee.name"),
            properties.getProperty("employee.salary"),
            properties.getProperty("employee.email"),
            properties.getProperty("employee.phone"),
            properties.getProperty("employee.department")
        );
        // Assertion for successful employee creation
        Assert.assertTrue(employeeListPage.isEmployeePresent(properties.getProperty("employee.name")), "Employee creation failed: Employee not found in the list.");
    }

    @AfterMethod
    public void tearDown() {
        if (homePage != null) { // Ensure homePage is initialized
            homePage.performLogOff();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
