package pageUI.wordpress;

public class AbstractWordpressPageUI {
	public static final String PAGES_LINK = "//a[contains(@class,'menu-icon-page')]";
	public static final String MEDIA_LINK = "//a[contains(@class,'menu-icon-media')]";
	public static final String POSTS_LINK = "//a[contains(@class,'menu-icon-post')]";
	
	//Dynamic locator
	public static final String DYNAMIC_PAGE_LINK = "//div[@class= 'wp-menu-name' and text()='%s']";
	
	public static final String UPLOAD_FILE_TYPE = "//input[@type='file']";
	public static final String MEDIA_PROGRESS_BAR_ICON = "//div[@class='thumbnail']/div[@class='media-progress-bar']";
	public static final String ALL_UPLOADED_IMG = "//div[@class='thumbnail']//img";
}
