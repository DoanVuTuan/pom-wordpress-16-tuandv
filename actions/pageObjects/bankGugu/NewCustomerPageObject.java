package pageObjects.bankGugu;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.bankGugu.HomePageUI;

public class NewCustomerPageObject extends AbstractPage{
	WebDriver driver;
	//Constructor - Hàm khởi tạo
	public NewCustomerPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	
	}
	
}
