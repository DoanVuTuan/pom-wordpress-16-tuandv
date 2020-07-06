package com.bankguru.login;

import org.testng.annotations.Test;

import pageFactory.PageGeneratorManager;
import pageFactory.HomePageObject;
import pageFactory.LoginPageObject;
import pageFactory.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_02_RegisterAndLogin_PageFactory {
	WebDriver driver;
	LoginPageObject loginPage;
	HomePageObject homePage;
	RegisterPageObject registerPage;

	String userIDValue, passwordvalue, loginPageUrl;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// Open url
		driver.get("http://demo.guru99.com/v4/");
		// Khởi tạo login page
		//loginPage = new LoginPageObject(driver);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPageUrl = loginPage.getLoginPageUrl();
	}

	@Test
	public void TC_01_Register() {

		// navigate to register page
		registerPage = loginPage.clickToHereLink();
		// Khởi tạo register page

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

		homePage = loginPage.clickToLoginButton();
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
