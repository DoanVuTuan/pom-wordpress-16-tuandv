package commons;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.wordpress.MediaPageObject;
import pageObjects.wordpress.PageGeneratorManager;
import pageObjects.wordpress.PagesPageObject;
import pageObjects.wordpress.PostsPageObject;

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

	public void checkToCheckBox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckBox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			element.click();
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

	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, castToObject(locator, values)).isDisplayed();
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

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", findElementByXpath(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", findElementByXpath(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0]" + ".naturalWidth !='undefined' && arguments[0]" + ".naturalWidth > 0", findElementByXpath(driver, locator));
		if (status) {
			return true;
		}
		return false;

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

	public void waitForElementInisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForAllElementsInisible(WebDriver driver, String locator) {
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
		sendkeyToElement(driver, AbstractPageUI.UPLOAD_FILE_TYPE, fullFileName);
	}

	public boolean areFileUplaodedDisplayed(WebDriver driver, String... fileNames) {
		boolean status = false;
		int number = fileNames.length;

		waitForAllElementsInisible(driver, AbstractPageUI.MEDIA_PROGRESS_BAR_ICON);
		waitForElementsVisible(driver, AbstractPageUI.ALL_UPLOADED_IMG);
		elements = findElementsByXpath(driver, AbstractPageUI.ALL_UPLOADED_IMG);

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
		waitForElementClickable(driver, AbstractPageUI.POSTS_LINK);
		clickToElement(driver, AbstractPageUI.POSTS_LINK);
		return PageGeneratorManager.getPostsPage(driver);
	}

	public PagesPageObject clickToPagesMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.PAGES_LINK);
		clickToElement(driver, AbstractPageUI.PAGES_LINK);
		return PageGeneratorManager.getPagesPage(driver);

	}

	public MediaPageObject clickToMediaMenu(WebDriver driver) {

		waitForElementClickable(driver, AbstractPageUI.MEDIA_LINK);
		clickToElement(driver, AbstractPageUI.MEDIA_LINK);
		return PageGeneratorManager.getMediaPage(driver);

	}

	// Dynamic locator
	// 1. Apply cho những app it commons page

	public AbstractPage clickToDynamicPage(WebDriver driver, String pageName) {

		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		if (pageName.equals("Pages")) {
			return PageGeneratorManager.getPagesPage(driver);
		} else if (pageName.equals("Posts")) {
			return PageGeneratorManager.getPostsPage(driver);
		} else if (pageName.equals("Media")) {
			return PageGeneratorManager.getMediaPage(driver);
		} else {
			return PageGeneratorManager.getDashboardPage(driver);
		}

	}

	// 2. Apply cho những app nhiều common pages
	public void clickToDynamicPages(WebDriver driver, String pageName) {

		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);

	}

	private Select select;
	private Actions action;
	private WebElement element;
	private List<WebElement> elements;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
}
