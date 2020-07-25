package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import pageFactory.RegisterPageObject;


public class PageGeneratorManager {
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
		
	}
	
	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
		
	}
	public static MediaPageObject getMediaPage(WebDriver driver) {
		return new MediaPageObject(driver);
		
	}
	public static PagesPageObject getPagesPage(WebDriver driver) {
		return new PagesPageObject(driver);
		
	}
		public static PostsPageObject getPostsPage(WebDriver driver) {
		return new PostsPageObject(driver);
		
	}



}
