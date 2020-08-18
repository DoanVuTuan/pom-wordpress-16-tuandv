package com.saucelab.sort;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;

import pageObjects.saucelab.SortPageObject;

public class Sort_ASC_DESC extends AbstractTest {
	WebDriver driver;
	SortPageObject sortPage;

	@Parameters({ "browser", "url" })

	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		driver = getBrowserDriver(browserName, urlValue);
		sortPage = pageObjects.saucelab.PageGeneratorManager.getSortPage(driver);
	}

	@Test
	public void TC_01_Sort_By_Name() {
		sortPage.selectItemInSortDropdown("Name (A to Z)");
		verifyTrue(sortPage.isNameSortAscending());
		
		sortPage.selectItemInSortDropdown("Name (Z to A)");
		verifyTrue(sortPage.isNameSortDescending());
	}

	@Test
	public void TC_02_Sort_By_Price() {
		sortPage.selectItemInSortDropdown("Price (low to high)");
		verifyTrue(sortPage.isPriceSortAscending());
		
		sortPage.selectItemInSortDropdown("Price (high to low)");
		verifyTrue(sortPage.isPriceSortDescending());
	}

	
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
