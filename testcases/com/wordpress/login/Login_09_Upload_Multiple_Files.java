package com.wordpress.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.wordpress.DashboardPageObject;
import pageObjects.wordpress.LoginPageObject;
import pageObjects.wordpress.MediaPageObject;
import pageObjects.wordpress.PageGeneratorManager;
import pageObjects.wordpress.PagesPageObject;
import pageObjects.wordpress.PostsPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_09_Upload_Multiple_Files extends AbstractTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	PostsPageObject postsPage;
	MediaPageObject mediaPage;
	PagesPageObject pagesPage;
	
	String kiaSeltos ="kia-seltos.jpg";
	String i10NLine = "i10-N-line.jpg";
	String vios ="toyota-vios.jpg";
	
	
	@Parameters("browser")

	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		// Steep 01 - Mở URL --> chuyển qua login page
		loginPage = PageGeneratorManager.getLoginPage(driver);

	}

	@Test
	public void TC_01_LoginToSystem() {
		loginPage.inputToEmailTextbox(GlobalConstants.WORDPRESS_ID);
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox(GlobalConstants.WORDPRESS_PWD);

		// Step 02 - Sau khi nhập hợp lệ và click login thì chuyển qua Dashboard
		dashboardPage = loginPage.clickToContinueOrLoginButton();
		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
	}

	@Test

	public void TC_02_Upload_Media() {
		// Dashboard --> mediaPage
		mediaPage = (MediaPageObject) dashboardPage.clickToDynamicPage(driver, "Media");
		
		mediaPage.clickToAddNewButton();
		mediaPage.uploadMultipleFiles(driver, kiaSeltos, i10NLine, vios);
		
		//Verify file uploaded success
		Assert.assertTrue(mediaPage.areFileUplaodedDisplayed(driver, kiaSeltos, i10NLine, vios));
		
		

	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}



}
