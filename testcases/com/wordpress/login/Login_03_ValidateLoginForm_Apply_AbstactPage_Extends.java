package com.wordpress.login;

import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class Login_03_ValidateLoginForm_Apply_AbstactPage_Extends extends AbstractPage {
	WebDriver driver;

	String emailTextbox = "//input[@id='usernameOrEmail']";
	String passwordTextbox = "//input[@id='password']";
	String loginButton = "//div[@class='login__form-action']/button";
	String emailErrorMesssag = "//div[@class='form-input-validation is-error']/span";
	String passwordErrorMesssage = "//div[@class='form-input-validation is-error']/span";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {

		openURL(driver, "https://automationfc.wordpress.com/wp-admin");
	}

	@Test
	public void Validate_01_EmptyEmail() {

		sendkeyToElement(driver, emailTextbox, "");
		clickToElement(driver, loginButton);

		Assert.assertEquals(getElementText(driver, emailErrorMesssag), "Please enter a username or email address.");

	}

	@Test
	public void Validate_02_InvalidEmail() {

		sendkeyToElement(driver, emailTextbox, "123@123.123");
		clickToElement(driver, loginButton);

		Assert.assertEquals(getElementText(driver, emailErrorMesssag), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void Validate_03_EmailNotExist() {

		sendkeyToElement(driver, emailTextbox, "tuandv." + randomNumber() + "@gmail.com");
		clickToElement(driver, loginButton);

		Assert.assertEquals(getElementText(driver, emailErrorMesssag), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPassword() {

		sendkeyToElement(driver, emailTextbox, "automationeditor");

		clickToElement(driver, loginButton);
		sendkeyToElement(driver, passwordTextbox, "");
		clickToElement(driver, loginButton);

		Assert.assertEquals(getElementText(driver, passwordErrorMesssage), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PasswordLessThanSixChars() {

		sendkeyToElement(driver, emailTextbox, "automationeditor");
		clickToElement(driver, loginButton);
		sendkeyToElement(driver, passwordTextbox, "123");
		clickToElement(driver, loginButton);

		Assert.assertEquals(getElementText(driver, passwordErrorMesssage), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPassword() {

		sendkeyToElement(driver, emailTextbox, "automationeditor");
		clickToElement(driver, loginButton);
		sendkeyToElement(driver, passwordTextbox, "automationfc");
		clickToElement(driver, loginButton);

		Assert.assertTrue(isElementDisplayed(driver, "//h1[(text()='Dashboard')]"));

		Assert.assertTrue(isElementDisplayed(driver, "//div[@id='dashboard-widgets-wrap']"));

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
