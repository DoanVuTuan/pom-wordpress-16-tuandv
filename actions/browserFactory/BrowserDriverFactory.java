package browserFactory;

public class BrowserDriverFactory {

	public static DriverManager getBrowserDriver(String browserName) {
		DriverManager driverManager;
		switch (browserName) {
		case "chorme":
			driverManager = new ChromeDriverManager();
			break;
		case "firefox":
			driverManager = new FirefoxDriverManager();
			break;

		default:
			driverManager = new ChromeHeadlessDriverManager();
			break;
		}
		return driverManager;
		
		
	}
}
