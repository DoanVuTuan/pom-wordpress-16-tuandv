package com.bankguru.login;

import org.testng.annotations.Test;

import pageObjects.bankGugu.HomePageObject;
import pageObjects.bankGugu.LoginPageObject;
import pageObjects.bankGugu.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_01_RegisterAndLogin_PageObject {
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
		loginPage = new LoginPageObject(driver);
		loginPageUrl = loginPage.getLoginPageUrl();
	}

	@Test
	public void TC_01_Register() {

		// navigate to register page
		loginPage.clickToHereLink();
		// Khởi tạo register page
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToEmailTextbox("tuandv"+randomNumber()+"@gmail.com");
		registerPage.clickToSubmitButton();

		userIDValue = registerPage.getUserIDText();
		passwordvalue = registerPage.getPasswordText();

		// Navigate to Login page
		registerPage.openLoginPage(loginPageUrl);

		// Khởi tạo lại login page
		loginPage = new LoginPageObject(driver);
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
