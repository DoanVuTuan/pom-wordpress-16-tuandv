package com.wordpress.login;

import org.testng.annotations.Test;
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

public class Login_01_ValidateLoginForm_Repeat_Yourself {
	WebDriver driver;
	

	By emailTextboxBy = By.xpath("//input[@id='usernameOrEmail']");
	By passwordTextboxBy = By.xpath("//input[@id='password']");
	By loginButtonBy = By.xpath("//div[@class='login__form-action']/button");

	By emailErrorMesssageBy = By.xpath("//div[@class='form-input-validation is-error']/span");
	By passwordErrorMesssageBy = By.xpath("//div[@class='form-input-validation is-error']/span");

	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://automationfc.wordpress.com/wp-admin");
	}

	@Test
	public void Validate_01_EmptyEmail() {
		driver.findElement(emailTextboxBy).sendKeys("");
		driver.findElement(loginButtonBy).click();

		Assert.assertEquals(driver.findElement(emailErrorMesssageBy).getText().trim(), "Please enter a username or email address.");

	}

	@Test
	public void Validate_02_InvalidEmail() {
		driver.findElement(emailTextboxBy).sendKeys("123@123.123");
		driver.findElement(loginButtonBy).click();

		Assert.assertEquals(driver.findElement(emailErrorMesssageBy).getText().trim(), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void Validate_03_EmailNotExist() {
		driver.findElement(emailTextboxBy).sendKeys("tuandv." + randomNumber() + "@gmail.com");
		driver.findElement(loginButtonBy).click();
		// User does not exist.\n Would you like to \n create a new account\n?

		Assert.assertEquals(driver.findElement(emailErrorMesssageBy).getText().trim(), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPassword() {
		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		driver.findElement(loginButtonBy).click();
		driver.findElement(passwordTextboxBy).sendKeys("");
		driver.findElement(loginButtonBy).click();

		Assert.assertEquals(driver.findElement(passwordErrorMesssageBy).getText().trim(), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PasswordLessThanSixChars() {
		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		driver.findElement(loginButtonBy).click();
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(loginButtonBy).click();

		Assert.assertEquals(driver.findElement(passwordErrorMesssageBy).getText().trim(), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPassword() {
		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		driver.findElement(loginButtonBy).click();
		driver.findElement(passwordTextboxBy).sendKeys("automationfc");
		driver.findElement(loginButtonBy).click();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[(text()='Dashboard')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='dashboard-widgets-wrap']")).isDisplayed());

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
