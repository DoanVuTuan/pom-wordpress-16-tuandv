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
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_12_Log_Report_HTML extends AbstractTest {
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
		log.info("Pre-condition : Step 01 - Navigate to login page");
		// Step 01 - Mở URL --> chuyển qua login page
		loginPage = PageGeneratorManager.getLoginPage(driver);

	}

	@Test
	public void TC_01_LoginToSystem() {
		log.info("Login Page : Step 01 - Input to 'Email' textbox ");
		loginPage.inputToEmailTextbox(GlobalConstants.WORDPRESS_ID);
		
		log.info("Login Page : Step 02 - Click to 'Continue' button ");
		loginPage.clickToContinueOrLoginButton();
		
		log.info("Login Page : Step 03 - Input to 'Password' textbox ");
		loginPage.inputToPasswordTextbox(GlobalConstants.WORDPRESS_PWD);
		
		log.info("Login Page : Step 04 - Navigate to Dashboard page");
		dashboardPage = loginPage.clickToContinueOrLoginButton();
		
		log.info("Dasboard Page : Step 01 - Verify header text displayed");
		verifyTrue(dashboardPage.isHeaderTextDisplayed());
	}

	@Test
	public void TC_02_Element_Undisplayed_In_DOM() {

		
		log.info("Dasboard Page : Step 02 - Click to Screen option");
		dashboardPage.clickToScreenOption();
		
		log.info("Dasboard Page : Step 03 - Verify 'Activity' checkbox is displayed");
		verifyTrue(dashboardPage.isActivityCheckboxDisplayed());
			
		log.info("Dasboard Page : Step 04 - Click to Screen option ");
		dashboardPage.clickToScreenOption();
		
		log.info("Dasboard Page : Step 05 - Verify 'Activity Checkbox' undisplayed");
		verifyFalse(dashboardPage.isActivityCheckboxDisplayed()); 
		
		log.info("Dasboard Page : Step 06 - Verify 'All Posts menu ' undisplayed");
		verifyFalse(dashboardPage.isAllPostsSubMenuDisplayed());
		
	}

	@Test
	public void TC_03_Element_Not_In_DOM() {
		
		log.info("Dasboard Page : Step 02 - Verify 'Plans Menu' undisplayed");
		verifyTrue(dashboardPage.isPlansMenuUndisplayed());
	}

	@AfterClass
	public void afterClass() {
		log.info("Post-condition : Close browser");
		driver.quit();
	}

}
