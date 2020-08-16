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
	String newPostCheckbox = "NEW LIVE CODING";


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

		newEditPostAdminPage.selectCategoryCheckbox(newPostCheckbox);

		newEditPostAdminPage.inputToTagTextbox(newPostTag);

		newEditPostAdminPage.clickToAddTagButton();

		newEditPostAdminPage.clickToSetFeatureImageLink();

		newEditPostAdminPage.clickToUploadFileTab();

		newEditPostAdminPage.uploadMultipleFiles(driver, featureImgName);

		verifyTrue(newEditPostAdminPage.areFileUplaodedDisplayed(driver, featureImgName));

		newEditPostAdminPage.clickToSetFeatureImageButton();

		verifyTrue(newEditPostAdminPage.isFeatureImageDisplayed(featureImgName));

		newEditPostAdminPage.clickToPublishButton();

		verifyTrue(newEditPostAdminPage.isSuccessMessageDisplayedWithValue(driver, "Post published."));

		// Search_Post_At_Admin_Page
		newEditPostAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = WordpressPageGeneratorManager.getPostsAdminPage(driver);

		postsAdminPage.inputToSearchTextbox(newPostTitle);

		postsAdminPage.clickToSearchPostsButton();

		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Title", newPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Author", author));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Categories", newPostCheckbox));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Tags", newPostTag));
	
		
		

		// View_Post_At_User_Page
		homeUserPage = postsAdminPage.openEndUserPage(driver);

		// design in AbstractPage
		verifyTrue(homeUserPage.isPostDisplayedOnLatestPost(driver, newPostCheckbox, newPostTitle, today));
//		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver,"title", "kia-seltos.jpg"));
//
//		// Go_Post_Detail_At_Admin_Page
//		postDetailUserPage = homeUserPage.clickToPostDetailWithTitleName("title");
//
//		verifyTrue(postDetailUserPage.isCateggoryNameDisplayed("category"));
//		verifyTrue(postDetailUserPage.isTitleNameDisplayed("title"));
//		verifyTrue(postDetailUserPage.isImageDisplayed("kia-seltos.jpg"));
//		verifyTrue(postDetailUserPage.isContentDisplayed("content"));
//		verifyTrue(postDetailUserPage.isCreatedDateDisplayed("created date"));
//		verifyTrue(postDetailUserPage.isAuthorDisplayed("author"));
//
//		// Search_Post_At_User_Page
//		searchResultsUserPage = postDetailUserPage.inputToSearchTextboxAtUserPage(driver, "title");
//
//		verifyTrue(searchResultsUserPage.isPostDisplayedOnLatestPost(driver,"category", "title", "created date"));
//		verifyTrue(searchResultsUserPage.isPostImageDisplayedAtPostTitleName(driver, "title", "kia-seltos.jpg"));

	}
//
//	@Test
//	public void Post_02_Edit_Post_At_Admin_Page() {
//
//		// Navigate to admin site
//		dashboardAdminPage = searchResultsUserPage.openAdminLoggedPage(driver);
//
//		dashboardAdminPage.openMenuPageByName(driver, "Posts");
//		postsAdminPage = WordpressPageGeneratorManager.getPostsAdminPage(driver);
//		// Search_Post_At_Admin_Page
//
//		postsAdminPage.inputToSearchTextbox("");
//
//		postsAdminPage.clickToSearchPostsButton();
//
//		verifyTrue(postsAdminPage.isOnlyOneRowDisplayed("title", "author", "category", "tag"));
//
//		// Click to post detail
//		newEditPostAdminPage = postsAdminPage.clickToPostDeatailByTitleName("title");
//
//		// Edit post
//		newEditPostAdminPage.inputToPostTitleTextbox("");
//		newEditPostAdminPage.inputToPostContentTextbox("");
//		newEditPostAdminPage.deselectCategoryCheckbox("");
//		newEditPostAdminPage.selectCategoryCheckbox("");
//		newEditPostAdminPage.inputToTagTextbox("tag_edit_name");
//		newEditPostAdminPage.clickToAddTagButton();
//		newEditPostAdminPage.clickToDeleteTagIconWithTagName("tag_new_name");
//		newEditPostAdminPage.clickToUpdateButton();
//
//		verifyTrue(newEditPostAdminPage.isSuccessMessageDisplayedWithValue("Post updated"));
//
//		// Search_Post_At_Admin_Page
//		newEditPostAdminPage.openMenuPageByName(driver, "Posts");
//		postsAdminPage = WordpressPageGeneratorManager.getPostsAdminPage(driver);
//
//		postsAdminPage.inputToSearchTextbox("");
//
//		postsAdminPage.clickToSearchPostsButton();
//
//		verifyTrue(postsAdminPage.isOnlyOneRowDisplayed("edit_title", "author", "edit_category", "tag_edit_name"));
//
//		// View_Post_At_User_Page
//		homeUserPage = postsAdminPage.openEndUserPage(driver);
//
//		// design in AbstractPage
//		verifyTrue(homeUserPage.isNewPostDisplayedOnLatestPost("edit_category", "edit_title", "created date"));
//		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName("edit_title", "kia-seltos.jpg"));
//
//		// Go_Post_Detail_At_Admin_Page
//		postDetailUserPage = homeUserPage.clickToPostDetailWithTitleName("edit_title");
//
//		verifyTrue(postDetailUserPage.isCateggoryNameDisplayed("edit_category"));
//		verifyTrue(postDetailUserPage.isTitleNameDisplayed("edit_title"));
//		verifyTrue(postDetailUserPage.isImageDisplayed("kia-seltos.jpg"));
//		verifyTrue(postDetailUserPage.isContentDisplayed("edit_content"));
//		verifyTrue(postDetailUserPage.isCreatedDateDisplayed("created date"));
//		verifyTrue(postDetailUserPage.isAuthorDisplayed("author"));
//
//		// Search_Post_At_User_Page
//		searchResultsUserPage = postDetailUserPage.inputToSearchTextboxAtUserPage(driver, "edit_title");
//
//		verifyTrue(searchResultsUserPage.isNewPostDisplayedOnLatestPost("edit_category", "edit_title", "created date"));
//		verifyTrue(searchResultsUserPage.isPostImageDisplayedAtPostTitleName("title", "kia-seltos.jpg"));
//	}
//
//	@Test
//	public void Post_03_Delete_Post_At_Admin_Page() {
//		// Navigate to admin site
//		dashboardAdminPage = searchResultsUserPage.openAdminLoggedPage(driver);
//
//		dashboardAdminPage.openMenuPageByName(driver, "Posts");
//		postsAdminPage = WordpressPageGeneratorManager.getPostsAdminPage(driver);
//		// Search_Post_At_Admin_Page
//
//		postsAdminPage.inputToSearchTextbox("");
//
//		postsAdminPage.clickToSearchPostsButton();
//
//		verifyTrue(postsAdminPage.isOnlyOneRowDisplayed("edit_title", "author", "edit_category", "tag"));
//
//		newEditPostAdminPage = postsAdminPage.clickToPostDeatailByTitleName("edit_title");
//
//		postsAdminPage = newEditPostAdminPage.clickToMoveToTrashButton();
//
//		verifyTrue(postsAdminPage.isSuccessMessageDisplayedWithValue("1 post moved to the trash."));
//
//		// Search_Post_At_Admin_Page
//
//		postsAdminPage.inputToSearchTextbox("");
//		postsAdminPage.clickToSearchPostsButton();
//
//		verifyFalse(postsAdminPage.isOnlyOneRowDisplayed("edit_title", "author", "edit_category", "tag"));
//
//		verifyTrue(postsAdminPage.isNoPostFoundMessageDisplayed("No post found"));
//
//		// View_Post_At_User_Page
//		homeUserPage = postsAdminPage.openEndUserPage(driver);
//
//		// design in AbstractPage
//		verifyFalse(homeUserPage.isNewPostDisplayedOnLatestPost("edit_category", "edit_title", "created date"));
//		verifyFalse(homeUserPage.isPostImageDisplayedAtPostTitleName("edit_title", "kia-seltos.jpg"));
////		Search_Post_At_User_Page
//
//		searchResultsUserPage = homeUserPage.inputToSearchTextboxAtUserPage(driver, "edit_title");
//
//		verifyFalse(searchResultsUserPage.isNewPostDisplayedOnLatestPost("edit_category", "edit_title", "created date"));
//		verifyFalse(searchResultsUserPage.isPostImageDisplayedAtPostTitleName("title", "kia-seltos.jpg"));
//	}

	@AfterClass
	public void afterClass() {
		log.info("Post-condition : Close browser");
	//	closeBrowserAndDriver(driver);
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
