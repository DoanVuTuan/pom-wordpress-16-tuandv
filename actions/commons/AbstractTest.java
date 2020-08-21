package commons;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
	private WebDriver driver;

	// Chỉ cho class nào kế thừa AbstractTest mới dùng được
	protected final Log log;

	// Constructor : khởi tạo log ra đầu tiên khi gọi đến AbstractTest
	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	protected void showBrowserConsoleLogs(WebDriver drive) {
		if (driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				System.out.println("--------------- " + logging.getLevel().toString() + "--------------- \n" + logging.getMessage());
			}
		}
	}

	protected WebDriver getBrowserDriver(String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,GlobalConstants.BROWSER_LOG_FOLDER + "\\Firefox"+getDateNumber()+".log");
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");
			/*setting download folder*/
			options.addPreference("browser.download.folderList", 2);
			options.addPreference("browser.download.dir", GlobalConstants.DOWNLOAD_FOLDER);
			options.addPreference("browser.download.useDownloadDir", true);
			options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			options.addPreference("pdfjs.disabled", true);
			driver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox_headless")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(true);
			driver = new FirefoxDriver(firefoxOptions);
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			/*setting download folder*/
			HashMap<String, Object> chromePerfs = new HashMap<String, Object>();
			chromePerfs.put("profile.default_content_settings.popups", 0);
			chromePerfs.put("download.default_directory",GlobalConstants.DOWNLOAD_FOLDER);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			/*Disabled info bar*/
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.setExperimentalOption("prefs", chromePerfs);
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("chrome_headless")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=11920x1080");
			driver = new ChromeDriver(options);
		} else {
			System.out.println("Please select browser!");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.ADMIN_WORDPRESS_URL);
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String appURL) {
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println("Please select browser!");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);
		return driver;
	}

	protected void closeBrowser() {
		driver.quit();
	}

	protected int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	protected Date getDateTimeNow() {
		Date date = new Date();
		return date;

	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;
			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {

			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	protected void closeBrowserAndDriver(WebDriver driver) {
		try {
			// get ra tên của OS và convert qua chữ thường
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			// Khai báo 1 biến command line để thực thi
			String cmd = "";
			if (driver != null) {
				driver.quit();
			}

			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			}

			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

			log.info("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime();
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return day + "";
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime();
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return month + "";
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime();
		return now.getYear() + "";
	}

	protected String getWordpressToday() {

		return getCurrentDay() + "/" + getCurrentMonth() + "/" + getCurrentYear();
	}
	
	private String getDateNumber() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return formatter.format(date);
	}

}
