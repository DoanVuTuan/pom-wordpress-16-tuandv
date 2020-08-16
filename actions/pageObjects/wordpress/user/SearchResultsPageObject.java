package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.admin.DashboardPageUI;
import pageUI.wordpress.admin.MediaPageUI;
import pageUI.wordpress.admin.PostsPageUI;
import pageUI.wordpress.user.SearchResultsPageUI;


public class SearchResultsPageObject extends AbstractPage {

	WebDriver driver;
	public SearchResultsPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}
	public boolean isPostTitleDisplayedOnHeader(String newPostTitle) {
		waitForElementClickable(driver, SearchResultsPageUI.POST_TITLE_ON_HEADER, newPostTitle);
		return isElementDisplayed(driver, SearchResultsPageUI.POST_TITLE_ON_HEADER, newPostTitle);
	}



	
}
