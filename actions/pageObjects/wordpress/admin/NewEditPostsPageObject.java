package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.WordpressPageGeneratorManager;
import pageUI.wordpress.admin.DashboardPageUI;
import pageUI.wordpress.admin.MediaPageUI;
import pageUI.wordpress.admin.PostsPageUI;

public class NewEditPostsPageObject extends AbstractPage {

	WebDriver driver;

	public NewEditPostsPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public void inputToPostContentTextbox() {
		// Switch to iframe

		// Sendkey

		// Switch to default content

	}

	public void clickToPublishButton() {
		// wait to clickable

		// click button

		// wait for loading icon invisible

	}

	public void clickToUpdateButton() {
		// wait to clickable

		// click button

		// wait for loading icon invisible
	}

	public PostsPageObject clickToMoveToTrashButton() {
		// wait to clickable

		// click button
		return WordpressPageGeneratorManager.getPostsAdminPage(driver);
	}

}
