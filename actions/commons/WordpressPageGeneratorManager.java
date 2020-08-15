package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.LoginPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.NewEditPostsPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;
import pageObjects.wordpress.user.HomePageObject;
import pageObjects.wordpress.user.PostDeatailPageObject;
import pageObjects.wordpress.user.SearchResultsPageObject;

public class WordpressPageGeneratorManager {

	/*Admin pages*/
	public static LoginPageObject getLoginAdminPage(WebDriver driver) {
		return new LoginPageObject(driver);

	}

	public static DashboardPageObject getDashboardAdminPage(WebDriver driver) {
		return new DashboardPageObject(driver);

	}

	public static MediaPageObject getMediaAdminPage(WebDriver driver) {
		return new MediaPageObject(driver);

	}

	public static PagesPageObject getPagesAdminPage(WebDriver driver) {
		return new PagesPageObject(driver);

	}

	public static PostsPageObject getPostsAdminPage(WebDriver driver) {
		return new PostsPageObject(driver);

	}

	public static NewEditPostsPageObject getNewEditPostsAdminPage(WebDriver driver) {
		return new NewEditPostsPageObject(driver);

	}
	
	
	/*User pages*/

	public static HomePageObject getHomeUserPage(WebDriver driver) {
		return new HomePageObject(driver);

	}

	public static PostDeatailPageObject getPostDeatailUserPage(WebDriver driver) {
		return new PostDeatailPageObject(driver);

	}

	public static SearchResultsPageObject getSearchResultsUserPage(WebDriver driver) {
		return new SearchResultsPageObject(driver);

	}

}
