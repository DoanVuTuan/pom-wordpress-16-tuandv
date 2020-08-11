package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pageFactory.PageGeneratorManager;
public class LoginPageObject extends AbsttractPage {

	WebDriver driver;

	// Constructor - Hàm khởi tạo
	public LoginPageObject(WebDriver mapDriver) {
		driver = mapDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'here')]")
	private WebElement hereLink;

	@FindBy(how = How.XPATH, using = "//input[@name='uid']")
	private WebElement userIDTextbox;

	@FindBy(how = How.XPATH, using = "//input[@name='password']")
	private WebElement passwordTextbox;

	@FindBy(how = How.XPATH, using = "//input[@name='btnLogin']")
	private WebElement loginButton;

	public RegisterPageObject  clickToHereLink() {

		waitForElementClickable(driver, hereLink);
		clickToElement(driver, hereLink);
		return PageGeneratorManager.getRegisterPage(driver);

	}

	public void inputToUserIDTextbox(String userIDValue) {
		waitForElementVisible(driver, userIDTextbox);
		sendkeyToElement(driver, userIDTextbox, userIDValue);

	}

	public void inputToPasswordTextbox(String passwordvalue) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, passwordvalue);

	}

	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
		return PageGeneratorManager.getHomePage(driver);

	}

	public String getLoginPageUrl() {
		return getCurrentUrl(driver);

	}

//	public static final String HERE_LINK = "//a[contains(text(),'here')]";
//	public static final String USER_ID_TEXTBOX = "//input[@name='uid']";
//	public static final String PWD_TEXTBOX = "//input[@name='password']";
//	public static final String LOGIN_BUTTON = "//input[@name='btnLogin']";
}
