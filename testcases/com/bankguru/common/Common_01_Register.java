package com.bankguru.common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import commons.AbstractTest;
import pageObjects.bankGugu.LoginPageObject;
import pageObjects.bankGugu.RegisterPageObject;

public class Common_01_Register extends AbstractTest {
	WebDriver driver;
	LoginPageObject loginPage;

	RegisterPageObject registerPage;

	public static String userIDValue, passwordvalue;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName, "http://demo.guru99.com/v4/");

		// Khởi tạo login page
		loginPage = new LoginPageObject(driver);
		// navigate to register page
		loginPage.clickToHereLink();
		// Khởi tạo register page
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToEmailTextbox("tuandv" + randomNumber() + "@gmail.com");
		registerPage.clickToSubmitButton();
		userIDValue = registerPage.getUserIDText();
		passwordvalue = registerPage.getPasswordText();
		

	}
	@AfterTest(alwaysRun = true)
	public void afterTest() {
		closeBrowserAndDriver(driver);

	}

}
