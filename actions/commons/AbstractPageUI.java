package commons;

public class AbstractPageUI {
	public static final String PAGES_LINK = "//a[contains(@class,'menu-icon-page')]";
	public static final String MEDIA_LINK = "//a[contains(@class,'menu-icon-media')]";
	public static final String POSTS_LINK = "//a[contains(@class,'menu-icon-post')]";
	
	//Dynamic locator
	public static final String DYNAMIC_PAGE_LINK = "//div[@class= 'wp-menu-name' and text()='%s']";
}
