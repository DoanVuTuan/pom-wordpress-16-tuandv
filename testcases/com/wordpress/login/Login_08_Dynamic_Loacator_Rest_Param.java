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

public class Login_08_Dynamic_Loacator_Rest_Param extends AbstractTest {

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
	// Apply when pages < 20
	public void TC_02_NavigateToPage() {
		// Dashboard --> Posts
		postsPage = (PostsPageObject) dashboardPage.clickToDynamicPage(driver, "Posts");

		// Posts --> Pages
		pagesPage = (PagesPageObject) postsPage.clickToDynamicPage(driver, "Pages");

		// Pages --> Media
		mediaPage = (MediaPageObject) pagesPage.clickToDynamicPage(driver, "Media");

		// Media --> Posts
		postsPage = (PostsPageObject) mediaPage.clickToDynamicPage(driver, "Posts");

		// Posts --> Media
		mediaPage = (MediaPageObject) postsPage.clickToDynamicPage(driver, "Media");
	}
	
	@Test
	// Apply when pages >20
	public void TC_03_NavigateToPages() {
		
		// Media --> Posts
		mediaPage.clickToDynamicPages(driver, "Posts");
		postsPage = PageGeneratorManager.getPostsPage(driver);
		
		
		// Posts --> Pages
		 postsPage.clickToDynamicPages(driver, "Pages");
		 pagesPage = PageGeneratorManager.getPagesPage(driver);
		 
		// Pages --> Media
		 pagesPage.clickToDynamicPages(driver, "Media");
		 mediaPage = PageGeneratorManager.getMediaPage(driver);
		 
		 
		// Media --> Posts
		 mediaPage.clickToDynamicPages(driver, "Posts");
		 postsPage = PageGeneratorManager.getPostsPage(driver);
		 
		 
		// Posts --> Media
		 postsPage.clickToDynamicPages(driver, "Media");
		 mediaPage = PageGeneratorManager.getMediaPage(driver);
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
