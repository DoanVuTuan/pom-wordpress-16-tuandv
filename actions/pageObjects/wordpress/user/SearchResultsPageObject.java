package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.admin.DashboardPageUI;
import pageUI.wordpress.admin.MediaPageUI;
import pageUI.wordpress.admin.PostsPageUI;


public class SearchResultsPageObject extends AbstractPage {

	WebDriver driver;
	public SearchResultsPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}


	
}
