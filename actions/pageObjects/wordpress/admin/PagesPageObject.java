package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.admin.DashboardPageUI;
import pageUI.wordpress.admin.MediaPageUI;
import pageUI.wordpress.admin.PagesPageUI;

public class PagesPageObject extends AbstractPage {

	WebDriver driver;
	public PagesPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}


	
}
