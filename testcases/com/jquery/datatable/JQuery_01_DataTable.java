package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObject.jquery.DataTablePageObject;
import pageObject.jquery.PageGeneratorManager;import pageUI.jquery.DataTablePageUI;


public class JQuery_01_DataTable extends AbstractTest {
	WebDriver driver;
	DataTablePageObject dataTablePage;

	@Parameters({ "browser", "url" })

	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		driver = getBrowserDriver(browserName, urlValue);
		dataTablePage = PageGeneratorManager.getDataTablePage(driver);
	}

	
	public void TC_01_Input_To_Colum_By_Name() {
		
		dataTablePage.inputToColumnByName("Country","Argentina");
		Assert.assertTrue(dataTablePage.isOnlyOneRowDisplayed("Argentina"));
		
		dataTablePage.refresh(driver);
		
		dataTablePage.inputToColumnByName("Total","1504");
		Assert.assertTrue(dataTablePage.isOnlyOneRowDisplayed("1504"));
	}


	public void TC_02_Edit_Delete_By_Country_Name() {
		dataTablePage.refresh(driver);
		dataTablePage.clickToDynamicIconByCountryName("remove","Argentina");
		dataTablePage.clickToDynamicIconByCountryName("remove","Angola");
		dataTablePage.clickToDynamicIconByCountryName("remove","Afghanistan");
		
		
		dataTablePage.clickToDynamicIconByCountryName("edit","Albania");

	}

	
	public void TC_03_Paging_By_Page_Index() {
		dataTablePage.refresh(driver);
		
		dataTablePage.navigateToPageNumberByIndex("10");
		Assert.assertTrue(dataTablePage.isPageActivedByIndex("10"));
		
		
		dataTablePage.navigateToPageNumberByIndex("15");
		Assert.assertTrue(dataTablePage.isPageActivedByIndex("15"));
		
		
		dataTablePage.navigateToPageNumberByIndex("6");
		Assert.assertTrue(dataTablePage.isPageActivedByIndex("6"));
	}

	@Test
	public void TC_04_Dynamic_Row() {
		dataTablePage.openURL(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		
		dataTablePage.inputToDynamicTextBoxAtRowNumber("Company","2","Samsung");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
