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

public class Login_02_ValidateLoginForm_Apply_AbstactPage_Init {
	AbstractPage abstractPage;
	WebDriver driver;

	String emailTextbox = "//input[@id='usernameOrEmail']";
	String passwordTextbox = "//input[@id='password']";
	String loginButton = "//div[@class='login__form-action']/button";
	String emailErrorMesssag = "//div[@class='form-input-validation is-error']/span";
	String passwordErrorMesssage = "//div[@class='form-input-validation is-error']/span";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//abstractPage = new AbstractPage();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
//		driver.get("https://automationfc.wordpress.com/wp-admin");

		abstractPage.openURL(driver, "https://automationfc.wordpress.com/wp-admin");
	}

	@Test
	public void Validate_01_EmptyEmail() {
//		driver.findElement(emailTextboxBy).sendKeys("");
//		driver.findElement(loginButtonBy).click();

		abstractPage.sendkeyToElement(driver, emailTextbox, "");
		abstractPage.clickToElement(driver, loginButton);

		Assert.assertEquals(abstractPage.getElementText(driver, emailErrorMesssag), "Please enter a username or email address.");

	}

	@Test
	public void Validate_02_InvalidEmail() {
//		driver.findElement(emailTextboxBy).sendKeys("123@123.123");
//		driver.findElement(loginButtonBy).click();
//		
		abstractPage.sendkeyToElement(driver, emailTextbox, "123@123.123");
		abstractPage.clickToElement(driver, loginButton);

		Assert.assertEquals(abstractPage.getElementText(driver, emailErrorMesssag), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void Validate_03_EmailNotExist() {
//		driver.findElement(emailTextboxBy).sendKeys("tuandv." + randomNumber() + "@gmail.com");
//		driver.findElement(loginButtonBy).click();
		// User does not exist.\n Would you like to \n create a new account\n?

		abstractPage.sendkeyToElement(driver, emailTextbox, "tuandv." + randomNumber() + "@gmail.com");
		abstractPage.clickToElement(driver, loginButton);

		Assert.assertEquals(abstractPage.getElementText(driver, emailErrorMesssag), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPassword() {
//		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		abstractPage.sendkeyToElement(driver, emailTextbox, "automationeditor");
//		driver.findElement(loginButtonBy).click();
		abstractPage.clickToElement(driver, loginButton);
//		driver.findElement(passwordTextboxBy).sendKeys("");
		abstractPage.sendkeyToElement(driver, passwordTextbox, "");
//		driver.findElement(loginButtonBy).click();
		abstractPage.clickToElement(driver, loginButton);

		Assert.assertEquals(abstractPage.getElementText(driver, passwordErrorMesssage), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PasswordLessThanSixChars() {
//		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
//		driver.findElement(loginButtonBy).click();
//		driver.findElement(passwordTextboxBy).sendKeys("123");
//		driver.findElement(loginButtonBy).click();

		abstractPage.sendkeyToElement(driver, emailTextbox, "automationeditor");
		abstractPage.clickToElement(driver, loginButton);
		abstractPage.sendkeyToElement(driver, passwordTextbox, "123");
		abstractPage.clickToElement(driver, loginButton);

		Assert.assertEquals(abstractPage.getElementText(driver, passwordErrorMesssage), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPassword() {
//		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
//		driver.findElement(loginButtonBy).click();
//		driver.findElement(passwordTextboxBy).sendKeys("automationfc");
//		driver.findElement(loginButtonBy).click();

		abstractPage.sendkeyToElement(driver, emailTextbox, "automationeditor");
		abstractPage.clickToElement(driver, loginButton);
		abstractPage.sendkeyToElement(driver, passwordTextbox, "automationfc");
		abstractPage.clickToElement(driver, loginButton);

//		Assert.assertTrue(driver.findElement(By.xpath("//h1[(text()='Dashboard')]")).isDisplayed());
		Assert.assertTrue(abstractPage.isElementDisplayed(driver, "//h1[(text()='Dashboard')]"));

//		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='dashboard-widgets-wrap']")).isDisplayed());
		Assert.assertTrue(abstractPage.isElementDisplayed(driver, "//div[@id='dashboard-widgets-wrap']"));

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
