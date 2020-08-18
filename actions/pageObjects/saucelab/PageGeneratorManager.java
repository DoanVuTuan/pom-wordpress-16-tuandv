package pageObjects.saucelab;

import org.openqa.selenium.WebDriver;



public class PageGeneratorManager {
	
	public static SortPageObject getSortPage(WebDriver driver) {
		return new SortPageObject(driver);
		
	}
	


}
