package extensions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UIElementExtensions {

	public static void performEnterText(WebElement locator, String value) {
		locator.click();
		locator.clear();
		locator.sendKeys(value);
	}

	public static void performClick(WebElement locator) {
		locator.click();
	}

	public static void performDropDownSelectionByText(WebElement locator, String dropDownText) {
		var select = new Select(locator);
		select.selectByVisibleText(dropDownText);
	}

	public static void performDropDownSelectionByIndex(WebElement locator, int index) {
		var select = new Select(locator);
		select.selectByIndex(index);
	}

	public static void performDropDownSelectionByValue(WebElement locator, String dropDownValue) {
		var select = new Select(locator);
		select.selectByValue(dropDownValue);
	}
}
