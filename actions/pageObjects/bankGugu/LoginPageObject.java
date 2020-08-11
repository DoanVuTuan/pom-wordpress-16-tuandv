package pageObjects.bankGugu;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.bankGugu.LoginPageUI;

public class LoginPageObject extends AbstractPage{
	WebDriver driver;
	//Constructor - Hàm khởi tạo
	public LoginPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	
	}
	public RegisterPageObject clickToHereLink() {
		waitForElementClickable(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
		
	}
	public void inputToUserIDTextbox(String userIDValue) {
		waitForElementVisible(driver, LoginPageUI.USER_ID_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USER_ID_TEXTBOX, userIDValue);
		
	}
	public void inputToPasswordTextbox(String passwordvalue) {
		waitForElementVisible(driver, LoginPageUI.PWD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PWD_TEXTBOX, passwordvalue);
		
	}
	public void clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		
	}
	public String getLoginPageUrl() {
		
		return getCurrentUrl(driver);
	}
}
