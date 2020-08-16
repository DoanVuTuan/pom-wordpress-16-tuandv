package pageUI.wordpress.user;

public class PostDeatailPageUI {

	public static final String CATEGORY_NAME = "//p[@class='post-categories']//a[contains(text(),'%s')]";
	public static final String POST_TITLE_NAME = "//h1[@class='post-title' and text() ='%s']";
	public static final String POST_IMAGE_NAME = "//img[contains(@src,'%s')]";
	public static final String POST_CONTENT_VALUE = "//div[@class= 'post-content']/p[contains(text(),'%s')]";
	public static final String POST_TAG_NAME = "//div[@class= 'post-tags']/a[contains(text(),'%s')]";
	public static final String POST_CREATED_DATE = "//span[@class='post-meta-date']//a[text()='%s']";
	public static final String POST_AUTHOR_NAME = "//span[@class='post-meta-author']//a[text()='%s']";

}
