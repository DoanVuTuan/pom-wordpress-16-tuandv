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
	public NewEditPostsPageObject clickToPostDeatailByTitleName(String string) {
		// TODO Auto-generated method stub
		return WordpressPageGeneratorManager.getNewEditPostsAdminPage(driver);
	}
	


	
}
