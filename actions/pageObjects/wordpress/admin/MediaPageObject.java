package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.admin.DashboardPageUI;
import pageUI.wordpress.admin.MediaPageUI;

public class MediaPageObject extends AbstractPage {

	WebDriver driver;
	public MediaPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}
	public void clickToAddNewButton() {
		waitForElementClickable(driver, MediaPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, MediaPageUI.ADD_NEW_BUTTON);
	}



	
}
