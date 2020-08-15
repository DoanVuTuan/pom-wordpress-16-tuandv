package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.WordpressPageGeneratorManager;
import pageUI.wordpress.admin.DashboardPageUI;
import pageUI.wordpress.admin.MediaPageUI;
import pageUI.wordpress.admin.PostsPageUI;


public class HomePageObject extends AbstractPage {

	WebDriver driver;
	public HomePageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}
	public PostDeatailPageObject clickToPostDetailWithTitleName(String string) {
		// TODO Auto-generated method stub
		
		return WordpressPageGeneratorManager.getPostDeatailUserPage(driver);
	}


	
}
