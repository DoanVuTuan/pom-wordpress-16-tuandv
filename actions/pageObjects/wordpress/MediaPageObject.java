package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.DashboardPageUI;
import pageUI.wordpress.MediaPageUI;

public class MediaPageObject extends AbstractPage {

	WebDriver driver;
	public MediaPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}


	
}
