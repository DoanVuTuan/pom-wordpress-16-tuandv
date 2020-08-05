package pageObjects.wordpress;

import java.util.Date;

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

	public void clickToScreenOption() {
		waitForElementVisible(driver, DashboardPageUI.SCREEN_OPTION_BUTTON);
		clickToElement(driver, DashboardPageUI.SCREEN_OPTION_BUTTON);
		sleepInSeconds(2);
		
	}

	public boolean isActivityCheckboxDisplayed() {
		//waitForElementVisible(driver, DashboardPageUI.ACTIVITY_CHECKBOX);
		return isElementDisplayed(driver, DashboardPageUI.ACTIVITY_CHECKBOX);
	}

	public boolean isAllPostsSubMenuDisplayed() {
		//waitForElementVisible(driver, DashboardPageUI.ALL_POSTS_SUBMENU);
		return isElementDisplayed(driver, DashboardPageUI.ALL_POSTS_SUBMENU);
	}


	public boolean isPlansMenuDisplayed() {
		return isElementDisplayed(driver, DashboardPageUI.PLANS_LINK);
	}

	public boolean isPlansMenuUndisplayed() {
		
		return isElementUndisplayed(driver, DashboardPageUI.PLANS_LINK);
	}


}
