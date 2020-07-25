package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.DashboardPageUI;
import pageUI.wordpress.MediaPageUI;
import pageUI.wordpress.PagesPageUI;

public class PagesPageObject extends AbstractPage {

	WebDriver driver;
	public PagesPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}


	
}
