package com.bankguru.login;

import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import pageObjects.bankGugu.HomePageObject;
import pageObjects.bankGugu.LoginPageObject;
import pageObjects.bankGugu.PageGeneratorManager;
import pageObjects.bankGugu.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_03_Dynamic_Page_Element extends AbstractTest {
	WebDriver driver;
	LoginPageObject loginPage;
	HomePageObject homePage;
	RegisterPageObject registerPage;

	String userIDValue, passwordvalue, loginPageUrl;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, "http://demo.guru99.com/v4/");
		// Khởi tạo login page
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPageUrl = loginPage.getLoginPageUrl();
	}

	@Test
	public void TC_01_Register() {

		// navigate to register page
		loginPage.clickToHereLink();
		
		registerPage.inputToEmailTextbox("tuandv" + randomNumber() + "@gmail.com");
		registerPage.clickToSubmitButton();

		userIDValue = registerPage.getUserIDText();
		passwordvalue = registerPage.getPasswordText();

		// Navigate to Login page
		loginPage = registerPage.openLoginPage(loginPageUrl);

	
	}

	@Test
	public void TC_02_Login() {
		loginPage.inputToUserIDTextbox(userIDValue);

		loginPage.inputToPasswordTextbox(passwordvalue);

		loginPage.clickToLoginButton();

		homePage = new HomePageObject(driver);

		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

}
