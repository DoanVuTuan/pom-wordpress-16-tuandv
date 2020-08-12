package pageUI.bankGugu;

public class AbstractBankPageUI {
	// Textbox
	public static final String DYNAMIC_TEXTBOX = "//input[@name='%s']";
	// Textarea
	public static final String DYNAMIC_TEXTAREA = "//textarea[@name='%s']";
	// Radio button
	public static final String DYNAMIC_RADIO_BUTTON = "//input[@type='radio' and @value='%s']";
	// Menu link
	public static final String DYNAMIC_LINK = "//a[text()='%s']";
	// Button
	public static final String DYNAMIC_BUTTON = "//input[@value ='%s']";
	// Message
	public static final String DYNAMIC_ERR_MESSAGE = "//label[@id='message' and text()='%s']";
	public static final String DYNAMIC_MESSAGE = "//p[@class ='heading3'and text()='%s']";

	// Column value
	public static final String DYNAMIC_VALUE_BY_COLUMN_NAME = "//td[contains(text(),'%s')]/following-sibling::td";
}
