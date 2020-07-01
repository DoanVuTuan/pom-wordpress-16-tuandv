package pageObjects.bankGugu;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.bankGugu.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;

	//Constructor - Hàm khởi tạo
	public RegisterPageObject(WebDriver mapDriver) {
		driver = mapDriver;

	}

	public void inputToEmailTextbox(String value) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, value);
		
	}

	public String getUserIDText() {
		waitForElementVisible(driver, RegisterPageUI.USER_ID_TEXT);
		return getElementText(driver, RegisterPageUI.USER_ID_TEXT);
	}

	public String getPasswordText() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXT);
		return getElementText(driver, RegisterPageUI.PASSWORD_TEXT);
	}

	public void clickToSubmitButton() {
		waitForElementClickable(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
		
	}

	public void openLoginPage(String loginPageUrl) {
		openURL(driver, loginPageUrl);
		
	}
}
