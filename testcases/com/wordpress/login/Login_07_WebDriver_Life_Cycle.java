package com.wordpress.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.WordpressPageGeneratorManager;
import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.LoginPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_07_WebDriver_Life_Cycle extends AbstractTest {

	@Parameters("browser")

	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		// Steep 01 - Mở URL --> chuyển qua login page
		loginPage = WordpressPageGeneratorManager.getLoginAdminPage(driver);

	}

	@Test
	public void TC_01_LoginToSystem() {
		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox("automationfc");

		// Step 02 - Sau khi nhập hợp lệ và click login thì chuyển qua Dashboard
		dashboardPage = loginPage.clickToContinueOrLoginButton();
		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
	}
	@Test
	public void TC_02_NavigateToPage() {
		// Dashboard --> Posts
		postsPage = dashboardPage.clickToPostsMenu(driver);

		// Posts --> Pages
		pagesPage = postsPage.clickToPagesMenu(driver);

		// Pages --> Media
		mediaPage = pagesPage.clickToMediaMenu(driver);

		// Media --> Posts
		postsPage = mediaPage.clickToPostsMenu(driver);

		// Posts --> Media
		mediaPage = postsPage.clickToMediaMenu(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	PostsPageObject postsPage;
	MediaPageObject mediaPage;
	PagesPageObject pagesPage;

}
