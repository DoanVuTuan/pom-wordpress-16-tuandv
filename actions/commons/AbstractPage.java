package commons;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;
import pageUI.bankGugu.AbstractBankPageUI;
import pageUI.wordpress.admin.AbstractWordpressPageUI;
import pageUI.wordpress.admin.NewEditPostsPageUI;
import pageObjects.wordpress.user.HomePageObject;
import pageObjects.wordpress.user.PostDeatailPageObject;
import pageObjects.wordpress.user.SearchResultsPageObject;

public abstract class AbstractPage {

	public void openURL(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void foward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void sendkeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void waitAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait;
		explicitWait = new WebDriverWait(driver, 15);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		// Get ID of all window/tab
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {

			// Check ID # parentID
			if (!runWindow.equals(parentID)) {

				// Switch to window
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		// Get all window/tab ID
		Set<String> allWindows = driver.getWindowHandles();
		// loop to check each ID
		for (String runWindow : allWindows) {

			// Switch to window which is checking
			driver.switchTo().window(runWindow);

			// Get window title
			String currentWindow = driver.getTitle();

			// verify window title = expected title ?
			if (currentWindow.equals(title)) {
				break;
			}
		}

	}

	public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {

		// get all tab/window id
		Set<String> allWindows = driver.getWindowHandles();

		// loop
		for (String runWindow : allWindows) {
			// Check ID # parentID
			if (!runWindow.equals(parentID)) {
				// Switch to window/tab
				driver.switchTo().window(runWindow);
				// close active window/tab
				driver.close();
			}
		}
		// switch to parent tab
		driver.switchTo().window(parentID);

	}

	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}

	public String castToObject(String locator, String... values) {
		return String.format(locator, (Object[]) values);
	}

	public void clickToElement(WebDriver driver, String locator) {
		findElementByXpath(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		findElementByXpath(driver, castToObject(locator, values)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(value);
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value, String... values) {
		element = findElementByXpath(driver, castToObject(locator, values));
		element.clear();
		element.sendKeys(value);
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return findElementByXpath(driver, locator).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText().trim();
	}

	public String getElementText(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, castToObject(locator, values)).getText().trim();
	}

	public void selectValueInDropdown(WebDriver driver, String locator, String value) {
		select = new Select(findElementByXpath(driver, locator));
		select.selectByVisibleText(value);
	}

	public String getSelectedItemInDropdown(WebDriver driver, String locator, String value) {
		select = new Select(findElementByXpath(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		// 1- Click vào thẻ (cha) để xổ ra tất cả các item con

		findElementByXpath(driver, parentLocator).click();
		sleepInSeconds(1);

		// 2- Chờ cho tất cả các item con được load ra (tìm tag chứa text)
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));

		// Đưa tất cả các item trong dropdown vào list để kiểm tra

		elements = findElementsByXpath(driver, childItemLocator);

		// 3 -Duyện từng giá trị đang có trong list
		for (WebElement item : elements) {
			// 4- Kiểm tra xem text của các giá trị có item nào bằng với text mong muốn hay ko
			if (item.getText().equals(expectedItem)) {
				// 5- Scroll xuống đúng đến item này
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);

				// 6- Click item
				item.click();
				sleepInSeconds(1);
				break;
			}

		}

	}

	public int countElementNumber(WebDriver driver, String locator) {

		elements = findElementsByXpath(driver, locator);
		return elements.size();
	}

	public int countElementNumber(WebDriver driver, String locator, String... values) {

		elements = findElementsByXpath(driver, castToObject(locator, values));
		return elements.size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			clickToElementByJS(driver, locator);
		}
	}

	public void checkToCheckbox(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, castToObject(locator, values));
		if (!element.isSelected()) {
			clickToElementByJS(driver, castToObject(locator, values));
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			clickToElementByJS(driver, locator);
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, castToObject(locator, values));
		if (element.isSelected()) {
			clickToElementByJS(driver, castToObject(locator, values));
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return findElementByXpath(driver, locator).isDisplayed();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean isElementDisplayed(WebDriver driver, String locator,String... values) {
		try {
			return findElementByXpath(driver, castToObject(locator, values)).isDisplayed();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}

	}


	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = findElementsByXpath(driver, locator);

		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible");
			System.out.println("End time = " + new Date().toString());
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			System.out.println("End time = " + new Date().toString());
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}

	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = findElementsByXpath(driver, castToObject(locator, values));

		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible");
			System.out.println("End time = " + new Date().toString());
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			System.out.println("End time = " + new Date().toString());
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}

	}

	public boolean isElementEnable(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isSelected();
	}

	public void switchToFrameOrIFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(findElementByXpath(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(driver, locator)).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, locator), key).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String... values) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, castToObject(locator, values)), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaSript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(WebDriver driver, String expectedText) {
		jsExecutor = (JavascriptExecutor) driver;
		String actualText = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
		return actualText.equals(expectedText);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void scrollToTopPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0,0)");
	}

	// Element
	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		sleepInSeconds(2);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", findElementByXpath(driver, locator));
	}
	
	public void clickToElementByJS(WebDriver driver, String locator,String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", findElementByXpath(driver, castToObject(locator, values)));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, locator));
		sleepInSeconds(1);
	}

	public void scrollToElement(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, castToObject(locator, values)));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", findElementByXpath(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", findElementByXpath(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", findElementByXpath(driver, castToObject(locator, values)));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0]" + ".naturalWidth !='undefined' && arguments[0]" + ".naturalWidth > 0", findElementByXpath(driver, locator));
		if (status) {
			return true;
		}
		return false;

	}

	public boolean isImageLoaded(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0]" + ".naturalWidth !='undefined' && arguments[0]" + ".naturalWidth > 0", findElementByXpath(driver, castToObject(locator, values)));
		if (status) {
			return true;
		}
		return false;

	}
	
//	public void waitUntilJSReady(WebDriver driver) {
//		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
//		jsExecutor = (JavascriptExecutor) driver;
//		try {
//			ExpectedCondition <Boolean> jsLoad = jsExecutor.executeScript("return document.readyState").toString().equals("complete");
//			
//			boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");
//			
//			if(!jsReady) {
//				explicitWait.until(jsLoad);
//			}
//		} catch (WebDriverException ignored) {
//			
//		}
//	}
	
	public boolean waitForJStoLoad(WebDriver driver) {

	  
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		jsExecutor = (JavascriptExecutor) driver;

	    // wait for jQuery to load
	    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        try {
	          return ((Long)jsExecutor.executeScript("return jQuery.active") == 0);
	        }
	        catch (Exception e) {
	          return true;
	        }
	      }
	    };

	    // wait for Javascript to load
	    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        return jsExecutor.executeScript("return document.readyState")
	            .toString().equals("complete");
	      }
	    };

	  return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(locator, values))));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator,String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToObject(locator, values))));
	}

	public void waitForAllElementsInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);

		elements = findElementsByXpath(driver, locator);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));

	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(locator, values))));
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + GlobalConstants.UPLOAD_FOLDER + file + "\n";
		}
		fullFileName = fullFileName.trim();
		sendkeyToElement(driver, AbstractWordpressPageUI.UPLOAD_FILE_TYPE, fullFileName);
		//sleepInSeconds(1);
	}

	public boolean areFileUplaodedDisplayed(WebDriver driver, String... fileNames) {
		boolean status = false;
		int number = fileNames.length;
		waitForJStoLoad(driver);
		waitForAllElementsInvisible(driver, AbstractWordpressPageUI.MEDIA_PROGRESS_BAR_ICON);
		waitForElementsVisible(driver, AbstractWordpressPageUI.ALL_UPLOADED_IMG);
	
		elements = findElementsByXpath(driver, AbstractWordpressPageUI.ALL_UPLOADED_IMG);

		// ArrayList chứa những giá trị này
		List<String> imageValues = new ArrayList<String>();

		// Lấy ra number = fileNames.length (getAttribute("src") của số lượng ảnh đã truyền vào)
		int i = 0;
		for (WebElement image : elements) {
			System.out.println(image.getAttribute("src"));
			imageValues.add(image.getAttribute("src"));
			i++;
			if (i == number) {
				break;
			}
		}

		// Verify file name matching

		for (String fileName : fileNames) {
			// tách chuỗi của tên ảnh dựa vào dấu . --> kia-seltos.jpg
			String[] files = fileName.split("\\.");

			// Lấy phần tử đầu tiên --> kia-seltos
			fileName = files[0].toLowerCase();

			for (i = 0; i < imageValues.size(); i++) {
				if (!imageValues.get(i).contains(fileName)) {

					status = false;
					if (i == imageValues.size() - 1) {
						return status;
					}
				} else {
					status = true;
					break;
				}
			}
		}
		return status;

	}

	// Common page --> Open page

	public PostsPageObject clickToPostsMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractWordpressPageUI.POSTS_LINK);
		clickToElement(driver, AbstractWordpressPageUI.POSTS_LINK);
		return WordpressPageGeneratorManager.getPostsAdminPage(driver);
	}

	public PagesPageObject clickToPagesMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractWordpressPageUI.PAGES_LINK);
		clickToElement(driver, AbstractWordpressPageUI.PAGES_LINK);
		return WordpressPageGeneratorManager.getPagesAdminPage(driver);

	}

	public MediaPageObject clickToMediaMenu(WebDriver driver) {

		waitForElementClickable(driver, AbstractWordpressPageUI.MEDIA_LINK);
		clickToElement(driver, AbstractWordpressPageUI.MEDIA_LINK);
		return WordpressPageGeneratorManager.getMediaAdminPage(driver);

	}

	// Dynamic locator
	// 1. Apply cho những app it commons page

	public AbstractPage clickToDynamicPage(WebDriver driver, String pageName) {

		waitForElementClickable(driver, AbstractWordpressPageUI.DYNAMIC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractWordpressPageUI.DYNAMIC_PAGE_LINK, pageName);
		if (pageName.equals("Pages")) {
			return WordpressPageGeneratorManager.getPagesAdminPage(driver);
		} else if (pageName.equals("Posts")) {
			return WordpressPageGeneratorManager.getPostsAdminPage(driver);
		} else if (pageName.equals("Media")) {
			return WordpressPageGeneratorManager.getMediaAdminPage(driver);
		} else {
			return WordpressPageGeneratorManager.getDashboardAdminPage(driver);
		}

	}

	// 2. Apply cho những app nhiều common pages
	public void openMenuPageByName(WebDriver driver, String pageName) {

		waitForElementClickable(driver, AbstractWordpressPageUI.DYNAMIC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractWordpressPageUI.DYNAMIC_PAGE_LINK, pageName);

	}

	/* Bank GURU Dynamic Page Component */
	public void inputToDynamicTextbox(WebDriver driver, String nameAttributeValue, String inputValue) {
		waitForElementVisible(driver, AbstractBankPageUI.DYNAMIC_TEXTBOX, nameAttributeValue);
		if (nameAttributeValue.equals("dob")) {
			removeAttributeInDOM(driver, AbstractBankPageUI.DYNAMIC_TEXTBOX, "type", nameAttributeValue);
			sleepInSeconds(1);
		}
		sendkeyToElement(driver, AbstractBankPageUI.DYNAMIC_TEXTBOX, inputValue, nameAttributeValue);
	}

	public void inputToDynamicTextarea(WebDriver driver, String nameAttributeValue, String inputValue) {
		waitForElementVisible(driver, AbstractBankPageUI.DYNAMIC_TEXTAREA, nameAttributeValue);
		sendkeyToElement(driver, AbstractBankPageUI.DYNAMIC_TEXTAREA, inputValue, nameAttributeValue);
	}

	public void clickToDynamicButton(WebDriver driver, String buttonValue) {
		waitForElementClickable(driver, AbstractBankPageUI.DYNAMIC_BUTTON, buttonValue);
		clickToElement(driver, AbstractBankPageUI.DYNAMIC_BUTTON, buttonValue);
	}

	public void clickToDynamicRadioButton(WebDriver driver, String radioButtonValue) {
		waitForElementClickable(driver, AbstractBankPageUI.DYNAMIC_RADIO_BUTTON, radioButtonValue);
		clickToElement(driver, AbstractBankPageUI.DYNAMIC_RADIO_BUTTON, radioButtonValue);
	}

	public void clickToDynamicLink(WebDriver driver, String linkPageName) {
		waitForElementClickable(driver, AbstractBankPageUI.DYNAMIC_LINK, linkPageName);
		clickToElement(driver, AbstractBankPageUI.DYNAMIC_LINK, linkPageName);
	}

	public boolean isDynamicMEssageDisplayed(WebDriver driver, String msgText) {
		waitForElementVisible(driver, AbstractBankPageUI.DYNAMIC_MESSAGE, msgText);
		return isElementDisplayed(driver, AbstractBankPageUI.DYNAMIC_MESSAGE, msgText);
	}

	public String getDynamicValueByColumnName(WebDriver driver, String columnName) {
		waitForElementVisible(driver, AbstractBankPageUI.DYNAMIC_VALUE_BY_COLUMN_NAME, columnName);
		return getElementText(driver, AbstractBankPageUI.DYNAMIC_VALUE_BY_COLUMN_NAME, columnName);
	}

	/* Wordpress component */

	public HomePageObject openEndUserPage(WebDriver driver) {
		openURL(driver, GlobalConstants.USER_WORDPRESS_URL);
		return WordpressPageGeneratorManager.getHomeUserPage(driver);
	}

	public DashboardPageObject openAdminLoggedPage(WebDriver driver) {
		openURL(driver, GlobalConstants.ADMIN_WORDPRESS_URL);
		return WordpressPageGeneratorManager.getDashboardAdminPage(driver);
	}

	public SearchResultsPageObject inputToSearchTextboxAtUserPage(WebDriver driver, String searchValue) {
		scrollToTopPage(driver);
		// wait
		waitForElementVisible(driver, AbstractWordpressPageUI.SEARCH_ICON);
		clickToElement(driver, AbstractWordpressPageUI.SEARCH_ICON);
		// sendkey
		sendkeyToElement(driver, AbstractWordpressPageUI.SEARCH_TEXTBOX, searchValue);

		// click search button
		clickToElement(driver, AbstractWordpressPageUI.SEARCH_BUTTON);
		return WordpressPageGeneratorManager.getSearchResultsUserPage(driver);

	}

	public boolean isSuccessMessageDisplayedWithValue(WebDriver driver, String successMsg) {
		waitForElementVisible(driver, AbstractWordpressPageUI.DYNAMIC_SUCCESS_MESSAGE_ON_POST_OR_PAGES_PAGE, successMsg);
		return isElementDisplayed(driver, AbstractWordpressPageUI.DYNAMIC_SUCCESS_MESSAGE_ON_POST_OR_PAGES_PAGE, successMsg);
	}

	public boolean isRowValueDisplayedAtColumn(WebDriver driver, String columnName, String rowValue) {
		waitForElementVisible(driver, AbstractWordpressPageUI.DYNAMIC_ROW_VALUE_AT_COLUMN_NAME, columnName, rowValue);
		return isElementDisplayed(driver, AbstractWordpressPageUI.DYNAMIC_ROW_VALUE_AT_COLUMN_NAME, columnName, rowValue);
	}

	public boolean isRowValueUndisplayedAtColumn(WebDriver driver, String columnName, String rowValue) {
		return isElementUndisplayed(driver, AbstractWordpressPageUI.DYNAMIC_ROW_VALUE_AT_COLUMN_NAME, columnName, rowValue);
	}
	
	public boolean isPostDisplayedOnLatestPost(WebDriver driver, String categoryName, String postTitle, String createdDate) {
		waitForElementVisible(driver, AbstractWordpressPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoryName, postTitle, createdDate);
		return isElementDisplayed(driver, AbstractWordpressPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoryName, postTitle, createdDate);
	}
	


	public boolean isPostImageDisplayedAtPostTitleName(WebDriver driver, String postTitle, String imgName) {
		imgName = imgName.split("\\.")[0];
		waitForElementVisible(driver, AbstractWordpressPageUI.DYNAMIC_POST_AVATAR_IMAGEBY_TITLE, postTitle, imgName);
		waitForJStoLoad(driver);
		return isElementDisplayed(driver, AbstractWordpressPageUI.DYNAMIC_POST_AVATAR_IMAGEBY_TITLE, postTitle, imgName) 
				&& isImageLoaded(driver, AbstractWordpressPageUI.DYNAMIC_POST_AVATAR_IMAGEBY_TITLE, postTitle, imgName);
	}

	public PostDeatailPageObject clickToPostDetailWithTitleName(WebDriver driver, String postTitle) {
		waitForElementVisible(driver, AbstractWordpressPageUI.DYNAMIC_POST_TITLE, postTitle);
		clickToElement(driver, AbstractWordpressPageUI.DYNAMIC_POST_TITLE, postTitle);
		return WordpressPageGeneratorManager.getPostDeatailUserPage(driver);
	}

	private Select select;
	private Actions action;
	private WebElement element;
	private List<WebElement> elements;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
}
