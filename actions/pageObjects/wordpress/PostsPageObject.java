package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.DashboardPageUI;
import pageUI.wordpress.MediaPageUI;
import pageUI.wordpress.PostsPageUI;


public class PostsPageObject extends AbstractPage {

	WebDriver driver;
	public PostsPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}


	
}
