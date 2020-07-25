package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.DashboardPageUI;

public class DashboardPageObject extends AbstractPage {

	WebDriver driver;
	public DashboardPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public boolean isHeaderTextDisplayed() {
		waitForElementVisible(driver, DashboardPageUI.HEADER_TEXT);
		return isElementDisplayed(driver, DashboardPageUI.HEADER_TEXT);
	}


}
