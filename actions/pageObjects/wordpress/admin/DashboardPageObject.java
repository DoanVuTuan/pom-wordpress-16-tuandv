package pageObjects.wordpress.admin;

import java.util.Date;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.GlobalConstants;
import pageUI.wordpress.admin.DashboardPageUI;

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
		sleepInSeconds(GlobalConstants.SHORT_TIMEOUT);
		waitForElementClickable(driver, DashboardPageUI.SCREEN_OPTION_BUTTON);
		clickToElement(driver, DashboardPageUI.SCREEN_OPTION_BUTTON);
		sleepInSeconds(GlobalConstants.SHORT_TIMEOUT);
		
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
