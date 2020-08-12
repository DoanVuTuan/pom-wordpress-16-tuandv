package com.bankguru.login;

import org.testng.annotations.Test;
import commons.AbstractTest;
import pageObjects.bankGugu.EditCustomerPageObject;
import pageObjects.bankGugu.HomePageObject;
import pageObjects.bankGugu.LoginPageObject;
import pageObjects.bankGugu.NewCustomerPageObject;
import pageObjects.bankGugu.PageGeneratorManager;
import pageObjects.bankGugu.RegisterPageObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Login_03_Dynamic_Page_Element extends AbstractTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, "http://demo.guru99.com/v4/");
		// Khởi tạo login page

		customerName = "tuandv";
		gender = "Male";
		dateOfBrith = "1970-06-01";
		address = "234 vietlot";
		city = "HN";
		state = "HN";
		PIN = "696969";
		mobileNumber = "0911111111";
		email = "tuandv" + randomNumber() + "@gmail.com";

		editAddress = "456 mega";
		editCity = "HCM";
		editState = "HCM";
		editPIN = "969696";
		editMobileNumber = "0911111110";
		editEmail = "tuandv" + randomNumber() + "@yahoo.com";

		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPageUrl = loginPage.getLoginPageUrl();
	}

	@Test
	public void TC_01_Register() {

		// navigate to register page
		loginPage.clickToDynamicLink(driver, "here");
		;
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		registerPage.inputToDynamicTextbox(driver, "emailid", email);
		registerPage.clickToDynamicButton(driver, "Submit");

		userIDValue = registerPage.getDynamicValueByColumnName(driver, "User ID");
		passwordvalue = registerPage.getDynamicValueByColumnName(driver, "Password");

		// Navigate to Login page
		loginPage = registerPage.openLoginPage(loginPageUrl);

	}

	@Test
	public void TC_02_LoginToSystem() {
		loginPage.inputToDynamicTextbox(driver, "uid", userIDValue);
		loginPage.inputToDynamicTextbox(driver, "password", passwordvalue);

		loginPage.clickToDynamicButton(driver, "LOGIN");

		homePage = new HomePageObject(driver);

		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isWelcomeMessageDisplayed());

	}

	@Test
	public void TC_03_NewCustomer() {
		homePage.clickToDynamicLink(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		newCustomerPage.inputToDynamicTextbox(driver, "name", customerName);
		newCustomerPage.clickToDynamicRadioButton(driver, "m");
		newCustomerPage.inputToDynamicTextbox(driver, "dob", dateOfBrith);
		newCustomerPage.inputToDynamicTextarea(driver, "addr", address);
		newCustomerPage.inputToDynamicTextbox(driver, "city", city);
		newCustomerPage.inputToDynamicTextbox(driver, "state", state);
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", PIN);
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", mobileNumber);
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", email);
		newCustomerPage.inputToDynamicTextbox(driver, "password", passwordvalue);
		newCustomerPage.clickToDynamicButton(driver, "Submit");

		verifyTrue(newCustomerPage.isDynamicMEssageDisplayed(driver, "Customer Registered Successfully!!!"));

		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Customer Name"), customerName);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Gender"), gender);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Birthdate"), dateOfBrith);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Address"), address);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "City"), city);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "State"), state);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Pin"), PIN);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Mobile No."), mobileNumber);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Email"), email);

		newCustomerID = newCustomerPage.getDynamicValueByColumnName(driver, "Customer ID");
	}

	@Test
	public void TC_04_EditCustomer() {
		newCustomerPage.clickToDynamicLink(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);

		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		editCustomerPage.clickToDynamicButton(driver, "Submit");

		editCustomerPage.inputToDynamicTextarea(driver, "addr", editAddress);
		editCustomerPage.inputToDynamicTextbox(driver, "city", editCity);
		editCustomerPage.inputToDynamicTextbox(driver, "state", editState);
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", editPIN);
		editCustomerPage.inputToDynamicTextbox(driver, "telephoneno", editMobileNumber);
		editCustomerPage.inputToDynamicTextbox(driver, "emailid", editEmail);
		editCustomerPage.clickToDynamicButton(driver, "Submit");
		
		verifyTrue(editCustomerPage.isDynamicMEssageDisplayed(driver, "Customer details updated Successfully!!!"));
		
		verifyEquals(editCustomerPage.getDynamicValueByColumnName(driver, "Customer Name"), customerName);
		verifyEquals(editCustomerPage.getDynamicValueByColumnName(driver, "Gender"), gender);
		verifyEquals(editCustomerPage.getDynamicValueByColumnName(driver, "Birthdate"), dateOfBrith);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Address"), editAddress);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "City"), editCity);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "State"), editState);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Pin"), editPIN);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Mobile No."), editMobileNumber);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Email"), editEmail);

	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	WebDriver driver;
	LoginPageObject loginPage;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	NewCustomerPageObject newCustomerPage;
	EditCustomerPageObject editCustomerPage;

	String userIDValue, passwordvalue, loginPageUrl, newCustomerID;
	String customerName, gender, dateOfBrith, address, city, state, PIN, mobileNumber, email;
	String editAddress, editCity, editState, editPIN, editMobileNumber, editEmail;

}
