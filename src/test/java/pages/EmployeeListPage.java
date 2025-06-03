package pages;

import extensions.UIElementExtensions;
import org.openqa.selenium.By; // Ensured By is imported
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

	public CreateEmployeePage clickCreateNew() {
		UIElementExtensions.performClick(btnCreateNew);
		return new CreateEmployeePage(driver);
	}

	// Assuming a generic table for employees. A more specific locator is available.
	@FindBy(xpath = "//table[contains(@class, 'table')]")
	private WebElement tblEmployees;

	public boolean isEmployeePresent(String employeeName) {
		// Check if table is displayed first
		try {
			if (tblEmployees == null || !tblEmployees.isDisplayed()) {
				System.out.println("Employee table is not displayed or not found by PageFactory.");
				return false;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Employee table threw NoSuchElementException on isDisplayed check.");
			return false;
		}

		// Construct XPath to find a cell (td) containing the employee's name the table
		// This is a simple check; more robust checks might involve specific columns
		String xpathForEmployee = ".//td[normalize-space()='" + employeeName + "']";
		// Used normalize-space() to handle potential leading/trailing spaces in cell
		try {
			WebElement employeeCell = tblEmployees.findElement(By.xpath(xpathForEmployee));
			return employeeCell.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out
					.println("Employee '" + employeeName + "' not found in the table using XPath: " + xpathForEmployee);
			return false;
		}
	}
}
