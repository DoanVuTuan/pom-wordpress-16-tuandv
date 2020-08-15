package com.wordpress.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
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

public class Login_08_Dynamic_Loacator_Rest_Param extends AbstractTest {

	@Parameters("browser")

	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		// Steep 01 - Mở URL --> chuyển qua login page
		loginPage = WordpressPageGeneratorManager.getLoginAdminPage(driver);

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
		mediaPage.openMenuPageByName(driver, "Posts");
		postsPage = WordpressPageGeneratorManager.getPostsAdminPage(driver);
		
		
		// Posts --> Pages
		 postsPage.openMenuPageByName(driver, "Pages");
		 pagesPage = WordpressPageGeneratorManager.getPagesAdminPage(driver);
		 
		// Pages --> Media
		 pagesPage.openMenuPageByName(driver, "Media");
		 mediaPage = WordpressPageGeneratorManager.getMediaAdminPage(driver);
		 
		 
		// Media --> Posts
		 mediaPage.openMenuPageByName(driver, "Posts");
		 postsPage = WordpressPageGeneratorManager.getPostsAdminPage(driver);
		 
		 
		// Posts --> Media
		 postsPage.openMenuPageByName(driver, "Media");
		 mediaPage = WordpressPageGeneratorManager.getMediaAdminPage(driver);
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
