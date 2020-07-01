package pageObjects.bankGugu;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.bankGugu.HomePageUI;

public class HomePageObject extends AbstractPage{
	WebDriver driver;
	//Constructor - Hàm khởi tạo
	public HomePageObject(WebDriver mapDriver) {
		driver = mapDriver;
	
	}
	public boolean isWelcomeMessageDisplayed() {
		waitForElementVisible(driver, HomePageUI.WELCOME_MSG);
		return isElementDisplayed(driver, HomePageUI.WELCOME_MSG);
	}
}
