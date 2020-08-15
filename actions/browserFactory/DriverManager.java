package browserFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import commons.GlobalConstants;

public abstract class DriverManager {
	protected WebDriver driver;

	protected abstract void createDriver();

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}

	}

	public WebDriver getDriver() {
		//singleton pattern
		if (driver == null) {
			createDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.ADMIN_WORDPRESS_URL);
		return driver;

	}

}
