package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.WordpressPageGeneratorManager;
import pageUI.wordpress.admin.DashboardPageUI;
import pageUI.wordpress.admin.MediaPageUI;
import pageUI.wordpress.admin.PostsPageUI;


public class PostsPageObject extends AbstractPage {

	WebDriver driver;
	public PostsPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}
	public NewEditPostsPageObject clickToPostDeatailByTitleName(String titleName) {
		waitForElementClickable(driver, PostsPageUI.POST_TITLE_LINK,titleName);
		clickToElement(driver, PostsPageUI.POST_TITLE_LINK,titleName);
		return WordpressPageGeneratorManager.getNewEditPostsAdminPage(driver);
	}
	public NewEditPostsPageObject clickToAddNewButton() {
		waitForElementClickable(driver, PostsPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, PostsPageUI.ADD_NEW_BUTTON);
		return WordpressPageGeneratorManager.getNewEditPostsAdminPage(driver)
				;
	}
	public void inputToSearchTextbox(String postTitle) {
		waitForElementVisible(driver, PostsPageUI.SEARCH_POST_TEXTBOX);
		sendkeyToElement(driver, PostsPageUI.SEARCH_POST_TEXTBOX, postTitle);
		
	}
	public void clickToSearchPostsButton() {
		waitForElementClickable(driver, PostsPageUI.SEARCH_POST_BUTTON);
		clickToElement(driver, PostsPageUI.SEARCH_POST_BUTTON);
		
	}
	public boolean isNoPostFoundMessageDisplayed() {
		waitForElementsVisible(driver, PostsPageUI.NO_POST_FOUND_MSG);
		return isElementDisplayed(driver, PostsPageUI.NO_POST_FOUND_MSG);
	}


	


	
}
