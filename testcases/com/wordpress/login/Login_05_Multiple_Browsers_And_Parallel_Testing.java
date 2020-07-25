package com.wordpress.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.wordpress.DashboardPageObject;
import pageObjects.wordpress.LoginPageObject;
import pageObjects.wordpress.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_05_Multiple_Browsers_And_Parallel_Testing extends AbstractTest {

	@Parameters("browser")

	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	@Test
	public void Validate_01_EmptyEmail() {
		loginPage.inputToEmailTextbox("");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(), "Please enter a username or email address.");
	}

	@Test
	public void Validate_02_InvalidEmail() {
		loginPage.inputToEmailTextbox("123@123.123");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void Validate_03_EmailNotExist() {
		loginPage.inputToEmailTextbox("tuandv" + randomNumber() + "@gmail.com");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPassword() {
		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PasswordLessThanSixChars() {

		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPassword() {

		loginPage.inputToPasswordTextbox("automationfc");
		loginPage.clickToContinueOrLoginButton();
		dashboardPage = new DashboardPageObject(driver);
		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;

}
