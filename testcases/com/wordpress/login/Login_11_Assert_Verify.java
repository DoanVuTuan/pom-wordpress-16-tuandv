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

public class Login_11_Assert_Verify extends AbstractTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	PostsPageObject postsPage;
	MediaPageObject mediaPage;
	PagesPageObject pagesPage;

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
		verifyTrue(dashboardPage.isHeaderTextDisplayed());
	}

	@Test
	public void TC_02_Element_Undisplayed_In_DOM() {

		// Click to Screen option : open/active
		dashboardPage.clickToScreenOption();
		verifyTrue(dashboardPage.isActivityCheckboxDisplayed());
		// input[@id='dashboard_activity-hide']
		// Click to Screen option : close/de-active
		dashboardPage.clickToScreenOption();
		verifyTrue(dashboardPage.isActivityCheckboxDisplayed()); //>> failed
		verifyTrue(dashboardPage.isAllPostsSubMenuDisplayed());//>> failed
		// a[text()='All Posts']
	}

	@Test
	public void TC_03_Element_Not_In_DOM() {
		verifyTrue(dashboardPage.isPlansMenuUndisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
