package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.wordpress.DashboardPageObject;
import pageObjects.wordpress.LoginPageObject;


public class PageGeneratorManager {
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
		
	}
	
		public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
		
	}

}
