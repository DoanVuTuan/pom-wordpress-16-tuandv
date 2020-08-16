package pageUI.wordpress.admin;

public class NewEditPostsPageUI {
	public static final String ADD_TITLE_TEXTBOX = "//input[@id='title']";
	public static final String TINY_MCE_IFRAME = "//iframe[@id='content_ifr']";
	public static final String TINY_MCE_TEXTBOX = "//body[@id='tinymce']/p";
	public static final String CATEGORY_CHECKBOX = "//label[contains(text(),'%s')]";
	public static final String TAG_TEXTBOX = "//input[@id='new-tag-post_tag']";
	public static final String ADD_TAG_BUTTON = "//input[@class='button tagadd']";
	public static final String SET_FEATURE_IMAGE_LINK = "//a[@id='set-post-thumbnail']";
	public static final String UPLOAD_FILE_TAB = "//button[@id='menu-item-upload']";
	public static final String SET_FEATURE_IMAGE_BUTTON = "//button[text()='Set featured image']";
	public static final String FEATURE_IMAGE_THUMBNAIL = "//a[@id='set-post-thumbnail']//img[contains(@src,'%s')]";
	public static final String PUBLISH_BUTTON = "//input[@id='publish']";
	
}
