package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;



public class HomePageObject extends AbsttractPage{
	WebDriver driver;
	//Constructor - Hàm khởi tạo
	public HomePageObject(WebDriver mapDriver) {
		driver = mapDriver;
		PageFactory.initElements(driver, this);
	
	}
	@FindBy(how = How.XPATH, using ="//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")
	private WebElement welcomeMsg;
	
	
	public boolean isWelcomeMessageDisplayed() {
		waitForElementVisible(driver, welcomeMsg);
		return isElementDisplayed(driver, welcomeMsg);
	}
	
//	public static final String WELCOME_MSG = "//marquee[text()="Welcome To Manager's Page of Guru99 Bank"]";
}
