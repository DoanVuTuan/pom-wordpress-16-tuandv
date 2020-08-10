package com.bankguru.user;

import org.testng.annotations.Test;

import com.bankguru.common.Common_01_Register;

import commons.AbstractTest;
import pageObjects.bankGugu.HomePageObject;
import pageObjects.bankGugu.LoginPageObject;
import pageObjects.bankGugu.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_01_Validate_User extends AbstractTest {
	WebDriver driver;
	LoginPageObject loginPage;
	HomePageObject homePage;
	RegisterPageObject registerPage;

	

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, "http://demo.guru99.com/v4/");
		// Khởi tạo login page
		loginPage = new LoginPageObject(driver);

		loginPage.inputToUserIDTextbox(Common_01_Register.userIDValue);
		loginPage.inputToPasswordTextbox(Common_01_Register.passwordvalue);
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());

	}

	@Test
	public void TC_01_Validate_Name() {

	}

	@Test
	public void TC_02_Validate_Address() {

	}

	@Test
	public void TC_03_Validate_Phone() {

	}

	@Test
	public void TC_04_Validate_City() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
