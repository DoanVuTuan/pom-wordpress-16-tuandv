package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.WordpressPageGeneratorManager;
import pageUI.wordpress.admin.DashboardPageUI;
import pageUI.wordpress.admin.MediaPageUI;
import pageUI.wordpress.admin.NewEditPostsPageUI;
import pageUI.wordpress.admin.PostsPageUI;

public class NewEditPostsPageObject extends AbstractPage {

	WebDriver driver;

	public NewEditPostsPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public void clickToPublishOrUpdateButton() {

		// wait to clickable
		waitForElementClickable(driver, NewEditPostsPageUI.PUBLISH_OR_UPDATE_BUTTON);
		// click button
		// clickToElement(driver, NewEditPostsPageUI.PUBLISH_BUTTON);
		clickToElementByJS(driver, NewEditPostsPageUI.PUBLISH_OR_UPDATE_BUTTON);
		

	}

	public PostsPageObject clickToMoveToTrashButton() {
		// wait to clickable
		waitForElementClickable(driver, NewEditPostsPageUI.MOVE_TO_TRASH_BUTTON);
		// click button
		clickToElementByJS(driver, NewEditPostsPageUI.MOVE_TO_TRASH_BUTTON);
		return WordpressPageGeneratorManager.getPostsAdminPage(driver);
	}

	public void inputToPostTitleTextbox(String titleValue) {
		waitForElementVisible(driver, NewEditPostsPageUI.ADD_TITLE_TEXTBOX);
		sendkeyToElement(driver, NewEditPostsPageUI.ADD_TITLE_TEXTBOX, titleValue);

	}

	public void inputToPostContentTextbox(String value) {
		switchToFrameOrIFrame(driver, NewEditPostsPageUI.TINY_MCE_IFRAME);
		waitForElementsVisible(driver, NewEditPostsPageUI.TINY_MCE_TEXTBOX);
		sendkeyToElement(driver, NewEditPostsPageUI.TINY_MCE_TEXTBOX, value);
		switchToDefaultContent(driver);
	}

	public void selectCategoryCheckbox(String newPostCategory) {
		waitForElementClickable(driver, NewEditPostsPageUI.CATEGORY_CHECKBOX, newPostCategory);
		
		checkToCheckbox(driver, NewEditPostsPageUI.CATEGORY_CHECKBOX, newPostCategory);

	}

	public void deselectCategoryCheckbox(String newPostCategory) {
		waitForElementClickable(driver, NewEditPostsPageUI.CATEGORY_CHECKBOX, newPostCategory);
		
		uncheckToCheckbox(driver, NewEditPostsPageUI.CATEGORY_CHECKBOX, newPostCategory);

	}

	public void inputToTagTextbox(String tagValue) {
		waitForElementVisible(driver, NewEditPostsPageUI.TAG_TEXTBOX);
		sendkeyToElement(driver, NewEditPostsPageUI.TAG_TEXTBOX, tagValue);

	}

	public void clickToAddTagButton() {
		waitForElementVisible(driver, NewEditPostsPageUI.ADD_TAG_BUTTON);
		clickToElement(driver, NewEditPostsPageUI.ADD_TAG_BUTTON);

	}

	public void clickToSetFeatureImageLink() {
		waitForElementClickable(driver, NewEditPostsPageUI.SET_FEATURE_IMAGE_LINK);
		clickToElement(driver, NewEditPostsPageUI.SET_FEATURE_IMAGE_LINK);

	}

	public void clickToUploadFileTab() {
		waitForElementClickable(driver, NewEditPostsPageUI.UPLOAD_FILE_TAB);
		clickToElement(driver, NewEditPostsPageUI.UPLOAD_FILE_TAB);

	}

	public void clickToSetFeatureImageButton() {
		waitForElementClickable(driver, NewEditPostsPageUI.SET_FEATURE_IMAGE_BUTTON);
		clickToElement(driver, NewEditPostsPageUI.SET_FEATURE_IMAGE_BUTTON);

	}

	public boolean isFeatureImageDisplayed(String imgName) {
		String[] files = imgName.split("\\.");
		waitForElementVisible(driver, NewEditPostsPageUI.FEATURE_IMAGE_THUMBNAIL, files[0]);
		return isElementDisplayed(driver, NewEditPostsPageUI.FEATURE_IMAGE_THUMBNAIL, files[0]);
	}

	public void clickToDeleteTagIconWithTagName(String newPostTag) {
		waitForElementClickable(driver, NewEditPostsPageUI.DELETE_TAG_NAME_ICON, newPostTag);
		clickToElement(driver, NewEditPostsPageUI.DELETE_TAG_NAME_ICON, newPostTag);

	}

}
