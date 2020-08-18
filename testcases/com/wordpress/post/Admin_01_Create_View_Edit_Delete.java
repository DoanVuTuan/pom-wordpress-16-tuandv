package com.wordpress.post;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.WordpressPageGeneratorManager;
import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.LoginPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.NewEditPostsPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;
import pageObjects.wordpress.user.HomePageObject;
import pageObjects.wordpress.user.PostDeatailPageObject;
import pageObjects.wordpress.user.SearchResultsPageObject;

public class Admin_01_Create_View_Edit_Delete extends AbstractTest {
	String featureImgName = "kia-seltos.jpg";
	int Number = randomNumber();
	String today = getWordpressToday();
	String author ="Automation FC";
	String newPostTitle = "[TuanDV] New Title" + Number;
	String newPostContent = "[TuanDV] New Content "+ Number;
	String newPostTag = "tuandv_new_post_"+Number;
	String newPostCategory = "NEW LIVE CODING";
	
	String editPostTitle = "[TuanDV] Edit Title" + Number;
	String editPostTag = "tuandv_edit_post_"+Number;
	String editPostCategory = "EDIT LIVE CODING";


	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		log.info("Pre-condition : Step 01 - Navigate to login page");
		// Step 01 - Mở URL --> chuyển qua login page
		loginAdminPage = WordpressPageGeneratorManager.getLoginAdminPage(driver);

		log.info("Login Page : Step 01 - Input to 'Email' textbox ");
		loginAdminPage.inputToEmailTextbox(GlobalConstants.WORDPRESS_ID);

		log.info("Login Page : Step 02 - Click to 'Continue' button ");
		loginAdminPage.clickToContinueOrLoginButton();

		log.info("Login Page : Step 03 - Input to 'Password' textbox ");
		loginAdminPage.inputToPasswordTextbox(GlobalConstants.WORDPRESS_PWD);

		log.info("Login Page : Step 04 - Navigate to Dashboard page");
		dashboardAdminPage = loginAdminPage.clickToContinueOrLoginButton();

		log.info("Dasboard Page : Step 01 - Verify header text displayed");
		verifyTrue(dashboardAdminPage.isHeaderTextDisplayed());

	}

	@Test
	public void Post_01_Create_New_Post_At_Admin_Page() {
		// Go to post page
		dashboardAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = WordpressPageGeneratorManager.getPostsAdminPage(driver);

		newEditPostAdminPage = postsAdminPage.clickToAddNewButton();

		newEditPostAdminPage.inputToPostTitleTextbox(newPostTitle);

		newEditPostAdminPage.inputToPostContentTextbox(newPostContent);

		newEditPostAdminPage.selectCategoryCheckbox(newPostCategory);

		newEditPostAdminPage.inputToTagTextbox(newPostTag);

		newEditPostAdminPage.clickToAddTagButton();

		newEditPostAdminPage.clickToSetFeatureImageLink();

		newEditPostAdminPage.clickToUploadFileTab();

		newEditPostAdminPage.uploadMultipleFiles(driver, featureImgName);

		verifyTrue(newEditPostAdminPage.areFileUplaodedDisplayed(driver, featureImgName));

		newEditPostAdminPage.clickToSetFeatureImageButton();

		verifyTrue(newEditPostAdminPage.isFeatureImageDisplayed(featureImgName));

		newEditPostAdminPage.clickToPublishOrUpdateButton();

		verifyTrue(newEditPostAdminPage.isSuccessMessageDisplayedWithValue(driver, "Post published."));

		// Search_Post_At_Admin_Page
		newEditPostAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = WordpressPageGeneratorManager.getPostsAdminPage(driver);

		postsAdminPage.inputToSearchTextbox(newPostTitle);

		postsAdminPage.clickToSearchPostsButton();

		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Title", newPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Author", author));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Categories", newPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Tags", newPostTag));
		
		// View_Post_At_User_Page
		homeUserPage = postsAdminPage.openEndUserPage(driver);

		// design in AbstractPage
		verifyTrue(homeUserPage.isPostDisplayedOnLatestPost(driver, newPostCategory, newPostTitle, today));
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver,newPostTitle, featureImgName));

		// Go_Post_Detail_At_Admin_Page
		postDetailUserPage = homeUserPage.clickToPostDetailWithTitleName(driver, newPostTitle);

		verifyTrue(postDetailUserPage.isCategoryNameDisplayed(newPostCategory));
		verifyTrue(postDetailUserPage.isTitleNameDisplayed(newPostTitle));
		verifyTrue(postDetailUserPage.isImageDisplayed(featureImgName));
		verifyTrue(postDetailUserPage.isContenValuetDisplayed(newPostContent));
		verifyTrue(postDetailUserPage.isPostTagDisplayed(newPostTag));
		verifyTrue(postDetailUserPage.isCreatedDateDisplayed(today));
		verifyTrue(postDetailUserPage.isAuthorDisplayed(author));

		// Search_Post_At_User_Page
		searchResultsUserPage = postDetailUserPage.inputToSearchTextboxAtUserPage(driver, newPostTitle);

		verifyTrue(searchResultsUserPage.isPostTitleDisplayedOnHeader(newPostTitle));
		
		verifyTrue(searchResultsUserPage.isPostDisplayedOnLatestPost(driver,newPostCategory, newPostTitle, today));
		verifyTrue(searchResultsUserPage.isPostImageDisplayedAtPostTitleName(driver, newPostTitle, featureImgName));

	}

	@Test
	public void Post_02_Edit_Post_At_Admin_Page() {

		// Navigate to admin site
		dashboardAdminPage = searchResultsUserPage.openAdminLoggedPage(driver);

		dashboardAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = WordpressPageGeneratorManager.getPostsAdminPage(driver);
		// Search_Post_At_Admin_Page

		postsAdminPage.inputToSearchTextbox(newPostTitle);

		postsAdminPage.clickToSearchPostsButton();

		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Title", newPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Author", author));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Categories", newPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Tags", newPostTag));

		// Click to post detail
		newEditPostAdminPage = postsAdminPage.clickToPostDeatailByTitleName(newPostTitle);

		// Edit post
		newEditPostAdminPage.inputToPostTitleTextbox(editPostTitle);
		newEditPostAdminPage.deselectCategoryCheckbox(newPostCategory);
		newEditPostAdminPage.selectCategoryCheckbox(editPostCategory);
		newEditPostAdminPage.inputToTagTextbox(editPostTag);
		newEditPostAdminPage.clickToAddTagButton();
		newEditPostAdminPage.clickToDeleteTagIconWithTagName(newPostTag);		
		newEditPostAdminPage.clickToPublishOrUpdateButton();
		verifyTrue(newEditPostAdminPage.isSuccessMessageDisplayedWithValue(driver,"Post updated"));

		
		// Search_Post_At_Admin_Page
		newEditPostAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = WordpressPageGeneratorManager.getPostsAdminPage(driver);

		postsAdminPage.inputToSearchTextbox(editPostTitle);

		postsAdminPage.clickToSearchPostsButton();

		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Title", editPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Author", author));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Categories", editPostCategory));
		verifyTrue(postsAdminPage.isRowValueUndisplayedAtColumn(driver, "Categories", newPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Tags", editPostTag));

		// View_Post_At_User_Page
		homeUserPage = postsAdminPage.openEndUserPage(driver);

		// design in AbstractPage
		verifyTrue(homeUserPage.isPostDisplayedOnLatestPost(driver, editPostCategory, editPostTitle, today));
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver,editPostTitle, featureImgName));

		// Go_Post_Detail_At_Admin_Page
		postDetailUserPage = homeUserPage.clickToPostDetailWithTitleName(driver, editPostTitle);

		verifyTrue(postDetailUserPage.isCategoryNameUndisplayed(newPostCategory));
		verifyTrue(postDetailUserPage.isCategoryNameDisplayed(editPostCategory));
		verifyTrue(postDetailUserPage.isTitleNameUndisplayed(newPostTitle));
		verifyTrue(postDetailUserPage.isTitleNameDisplayed(editPostTitle));
		verifyTrue(postDetailUserPage.isImageDisplayed(featureImgName));
		verifyTrue(postDetailUserPage.isContenValuetDisplayed(newPostContent));
		verifyTrue(postDetailUserPage.isPostTagUndisplayed(newPostTag));
		verifyTrue(postDetailUserPage.isPostTagDisplayed(editPostTag));
		verifyTrue(postDetailUserPage.isCreatedDateDisplayed(today));
		verifyTrue(postDetailUserPage.isAuthorDisplayed(author));

		// Search_Post_At_User_Page
		searchResultsUserPage = postDetailUserPage.inputToSearchTextboxAtUserPage(driver, editPostTitle);

		verifyTrue(searchResultsUserPage.isPostTitleDisplayedOnHeader(editPostTitle));
		
		verifyTrue(searchResultsUserPage.isPostDisplayedOnLatestPost(driver,editPostCategory, editPostTitle, today));
		verifyTrue(searchResultsUserPage.isPostImageDisplayedAtPostTitleName(driver, editPostTitle, featureImgName));
	}

	@Test
	public void Post_03_Delete_Post_At_Admin_Page() {
		// Navigate to admin site
		dashboardAdminPage = searchResultsUserPage.openAdminLoggedPage(driver);

		dashboardAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = WordpressPageGeneratorManager.getPostsAdminPage(driver);
		// Search_Post_At_Admin_Page

		postsAdminPage.inputToSearchTextbox(editPostTitle);

		postsAdminPage.clickToSearchPostsButton();

		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Title", editPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Author", author));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Categories", editPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Tags", editPostTag));

		newEditPostAdminPage = postsAdminPage.clickToPostDeatailByTitleName(editPostTitle);

		postsAdminPage = newEditPostAdminPage.clickToMoveToTrashButton();

		verifyTrue(postsAdminPage.isSuccessMessageDisplayedWithValue(driver, "1 post moved to the Trash."));

		// Search_Post_At_Admin_Page

		postsAdminPage.inputToSearchTextbox(editPostTitle);
		postsAdminPage.clickToSearchPostsButton();

		verifyTrue(postsAdminPage.isRowValueUndisplayedAtColumn(driver, "Title", editPostTitle));
		verifyTrue(postsAdminPage.isRowValueUndisplayedAtColumn(driver, "Author", author));
		verifyTrue(postsAdminPage.isRowValueUndisplayedAtColumn(driver, "Categories", editPostCategory));
		verifyTrue(postsAdminPage.isRowValueUndisplayedAtColumn(driver, "Tags", editPostTag));

		verifyTrue(postsAdminPage.isNoPostFoundMessageDisplayed());

		// View_Post_At_User_Page
		homeUserPage = postsAdminPage.openEndUserPage(driver);

	

//		Search_Post_At_User_Page

		searchResultsUserPage = homeUserPage.inputToSearchTextboxAtUserPage(driver,editPostTitle);

		verifyTrue(searchResultsUserPage.isErrorMessageDisplayed());
		
	}
	


	@AfterClass
	public void afterClass() {
		log.info("Post-condition : Close browser");
		closeBrowserAndDriver(driver);
	}

	WebDriver driver;
	LoginPageObject loginAdminPage;
	DashboardPageObject dashboardAdminPage;
	PostsPageObject postsAdminPage;
	MediaPageObject mediaAdminPage;
	PagesPageObject pagesAdminPage;
	NewEditPostsPageObject newEditPostAdminPage;
	HomePageObject homeUserPage;
	PostDeatailPageObject postDetailUserPage;
	SearchResultsPageObject searchResultsUserPage;

}
