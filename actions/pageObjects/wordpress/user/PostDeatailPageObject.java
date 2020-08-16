package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;
import commons.AbstractPage;
import pageUI.wordpress.admin.AbstractWordpressPageUI;
import pageUI.wordpress.user.PostDeatailPageUI;

public class PostDeatailPageObject extends AbstractPage {

	WebDriver driver;

	public PostDeatailPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public boolean isCategoryNameDisplayed(String newPostCategoryCheckbox) {
		waitForElementVisible(driver, PostDeatailPageUI.CATEGORY_NAME, newPostCategoryCheckbox);
		return isElementDisplayed(driver, PostDeatailPageUI.CATEGORY_NAME, newPostCategoryCheckbox);
	}

	public boolean isImageDisplayed(String featureImgName) {
		featureImgName = featureImgName.split("\\.")[0];
		waitForElementVisible(driver, PostDeatailPageUI.POST_IMAGE_NAME, featureImgName);
		return isElementDisplayed(driver, PostDeatailPageUI.POST_IMAGE_NAME, featureImgName)
				&& isImageLoaded(driver, PostDeatailPageUI.POST_IMAGE_NAME, featureImgName);
	}

	public boolean isTitleNameDisplayed(String newPostTitle) {
		waitForElementVisible(driver, PostDeatailPageUI.POST_TITLE_NAME, newPostTitle);
		return isElementDisplayed(driver, PostDeatailPageUI.POST_TITLE_NAME, newPostTitle);
	}

	public boolean isContenValuetDisplayed(String newPostContent) {
		waitForElementVisible(driver, PostDeatailPageUI.POST_CONTENT_VALUE, newPostContent);
		return isElementDisplayed(driver, PostDeatailPageUI.POST_CONTENT_VALUE, newPostContent);
	}

	public boolean isCreatedDateDisplayed(String today) {
		waitForElementVisible(driver, PostDeatailPageUI.POST_CREATED_DATE, today);
		return isElementDisplayed(driver, PostDeatailPageUI.POST_CREATED_DATE, today);
	}

	public boolean isAuthorDisplayed(String author) {
		waitForElementVisible(driver, PostDeatailPageUI.POST_AUTHOR_NAME, author);
		return isElementDisplayed(driver, PostDeatailPageUI.POST_AUTHOR_NAME, author);
	}

}
