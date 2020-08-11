package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pageFactory.PageGeneratorManager;

public class RegisterPageObject extends AbsttractPage {
	WebDriver driver;

	// Constructor - Hàm khởi tạo
	public RegisterPageObject(WebDriver mapDriver) {
		driver = mapDriver;
		PageFactory.initElements(driver, this);
	}

//	public static final String EMAIL_TEXTBOX = "//input[@name='emailid']";
//	public static final String SUBMIT_BUTTON="//input[@name='btnLogin']";
//	public static final String USER_ID_TEXT="//td[text()='User ID :']//following-sibling::td";
//	public static final String PASSWORD_TEXT="//td[text()='Password :']//following-sibling::td";

	@FindBy(how = How.XPATH, using = "//input[@name='emailid']")
	private WebElement emailTextbox;

	@FindBy(how = How.XPATH, using = "//input[@name='btnLogin']")
	private WebElement submitButton;

	@FindBy(how = How.XPATH, using = "//td[text()='User ID :']//following-sibling::td")
	private WebElement userIDText;

	@FindBy(how = How.XPATH, using = "//td[text()='Password :']//following-sibling::td")
	private WebElement passwordText;

	public void inputToEmailTextbox(String value) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, value);

	}

	public String getUserIDText() {
		waitForElementVisible(driver, userIDText);
		return getElementText(driver, userIDText);
	}

	public String getPasswordText() {
		waitForElementVisible(driver, passwordText);
		return getElementText(driver, passwordText);
	}

	public void clickToSubmitButton() {
		waitForElementClickable(driver, submitButton);
		clickToElement(driver, submitButton);

	}

	public LoginPageObject openLoginPage(String loginPageUrl) {
		openURL(driver, loginPageUrl);
		return PageGeneratorManager.getLoginPage(driver);
	}

}
