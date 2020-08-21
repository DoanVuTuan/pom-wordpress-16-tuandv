package com.nopcommerce.endUser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.DataHelper;

public class Register_01 extends AbstractTest {

	@Parameters({ "browser", "url" })

	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		data = DataHelper.getData();
		firstName = data.getFirstName();
		lastName = data.getLastName();
		email = data.getEmail();
		companyName = data.getCompanyName();
		passsword = data.getPassword();
	}

	@Test
	public void Post_01_Register_To_System() {
		driver.findElement(By.className("ico-register")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(passsword);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(passsword);

		driver.findElement(By.id("register-button")).click();

	}
	@Test
	public void Post_02_Verify_My_Account() {
		driver.findElement(By.className("ico-account")).click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		verifyEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		verifyEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		verifyEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		verifyEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-condition : Close browser");
		closeBrowserAndDriver(driver);
	}

	WebDriver driver;
	DataHelper data;
	String firstName, lastName, email, companyName, passsword;

}
