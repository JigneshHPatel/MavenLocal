/**
 * 
 */
package clj.Frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import commonLibrary.CloseFlyouts;
import commonLibrary.GetArticleUrl;
import commonLibrary.ReaderCommentMethods;
import commonLibrary.WaitMethods;
import pageObjects.ArticlePageObj;
import pageObjects.RegistrationObj;
import util.DataFileReader;
import util.Retry;
import util.SystemDateTime;

/**
 * @author jigneshkumarpatel Test for Reader comment info, post comment, reply,
 *         report abuse, rate and comment from fb on article page
 *
 */

public class ReaderComment extends browsers.BeforeAfter {

	private static DataFileReader dataReader = new DataFileReader();

	@Test(retryAnalyzer = Retry.class, description = "Get wide and unmod article from puff and save in prpertyfile")
	public void a_ArticleUrl() throws InterruptedException {
		GetArticleUrl.getReaderCommentArticle(getDriver());
	}

	@Test(retryAnalyzer = Retry.class, description = "Info on comments e.g. user info, newest,oldest etc.")
	public void b_CommentInfo() throws Exception {
		ArticlePageObj articleObj = new ArticlePageObj(getDriver());
		ReaderCommentMethods.commentSection(getDriver());

		try {
			articleObj.moderationMessageIsDisplayed();
			System.out.println(articleObj.getModerationMessage());
			pass(getDriver(), articleObj.getModerationMessage());
		} catch (Exception e) {
		}

		for (WebElement tab : articleObj.CommentTabs) {
			String tabName = tab.getText();
			System.out.println("Total comments on " + tabName + " are " + articleObj.TotalComments());
			pass(getDriver(), "Total comments on " + tabName + " are " + articleObj.TotalComments());
		}
		System.out.println("User Info is " + articleObj.getUserInfoOnComment() + articleObj.getUserGeoOnComment());
		pass(getDriver(), "User Info is " + articleObj.getUserInfoOnComment() + articleObj.getUserGeoOnComment());
	}

	@Test(retryAnalyzer = Retry.class, description = "posting direct comment by logging from post comment path")
	public void c_PostComment() throws Exception {
		ArticlePageObj articleObj = new ArticlePageObj(getDriver());
		ReaderCommentMethods.commentSection(getDriver());

		ReaderCommentMethods.commentLoginAndVerifySubmission(getDriver(), articleObj.CommentMessageField,
				articleObj.commentSubmitButton);

	}

	@Test(retryAnalyzer = Retry.class, description = "Post Reply on comment in oldest filter")
	public void d_PostReply() throws Exception {

		ArticlePageObj articleObj = new ArticlePageObj(getDriver());
		ReaderCommentMethods.commentSection(getDriver());

		// click on oldest tab
		articleObj.CommentTabs.get(1).click();
		Thread.sleep(5000);

		WaitMethods.WaitUntilElementClickable(getDriver(), articleObj.allReply.get(0));
		// articleObj.allReply.get(0).click();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", articleObj.allReply.get(0));
		Thread.sleep(3000);
		ReaderCommentMethods.commentLoginAndVerifySubmission(getDriver(), articleObj.ReplyMessageField,
				articleObj.replySubmitButton);
	}

	@Test(retryAnalyzer = Retry.class, description = "Posting comment to Facebook and Dailymail")
	public void e_FbComment() throws Exception {
		ArticlePageObj articleObj = new ArticlePageObj(getDriver());
		RegistrationObj regObj = new RegistrationObj(getDriver());

		ReaderCommentMethods.commentSection(getDriver());

		articleObj.CommentMessageField.clear();
		articleObj.CommentMessageField.sendKeys("comment" + SystemDateTime.currentDateTime());
		articleObj.isMessagePresentInCommentField();

		CloseFlyouts.closeNewsletter(getDriver());

		articleObj.postCommentToFacebookCheckbox.click();
		Thread.sleep(2000);
		WaitMethods.WaitUntilElementClickable(getDriver(), articleObj.yesButtonOnFacebookAlert);
		// articleObj.yesButtonOnFacebookAlert.click();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", articleObj.yesButtonOnFacebookAlert);
		Thread.sleep(3000);
		try {
			Assert.assertTrue(getDriver().getCurrentUrl().contains("www.facebook.com/login"));
			System.out.println("Facebook login page is present");
			pass(getDriver(), "Facebook login page is present");
		} catch (Exception e) {
			System.out.println("Facebook login page is ***NOT*** present");
			fail(getDriver(), "Facebook login page is ***NOT*** present");
		}

		regObj.FacebookLoginEmailField.clear();
		regObj.FacebookLoginEmailField.sendKeys(dataReader.getFacebookUsername());
		regObj.FacebookLoginPasswordField.clear();
		regObj.FacebookLoginPasswordField.sendKeys(dataReader.getFacebookPassword());
		regObj.FacebookLoginSubmitButton.click();
		Thread.sleep(2000);
		WaitMethods.WaitUntilElementClickable(getDriver(), articleObj.commentSubmitButton);
		articleObj.commentSubmitButton.click();
		WaitMethods.WaitUntilElementVisible(getDriver(), articleObj.FacebookCommentSubmissionMessage);
		String message = articleObj.FacebookCommentSubmissionMessage.getText();
		if (message.equalsIgnoreCase("Your comment will also be posted to the Facebook account for Don Joe")) {
			System.out.println("Comment posted by FB");
			pass(getDriver(), "Comment posted by FB, submission message displayed " + message);
		} else {
			System.out.println("confirmation message not present");
			warning(getDriver(), "confirmation message not present");
		}
	}

	@Test(retryAnalyzer = Retry.class, description = "Report abuse, asserting all filed are present in report abuse page and negative test")
	public void f_ReportAbuse() throws Exception {

		RegistrationObj regObj = new RegistrationObj(getDriver());
		ArticlePageObj articleObj = new ArticlePageObj(getDriver());
		JavascriptExecutor je = (JavascriptExecutor) getDriver();

		ReaderCommentMethods.commentSection(getDriver());

		String articleurl = getDriver().getCurrentUrl();
		try {
			je.executeScript("arguments[0].style='visibility: visible;'", articleObj.ReportAbuseFlag);
			WaitMethods.WaitUntilElementClickable(getDriver(), articleObj.ReportAbuseFlag);
			// articleObj.ReportAbuseFlag.click();
			je.executeScript("arguments[0].click();", articleObj.ReportAbuseFlag);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(2000);

		if (regObj.ReportAbuseHeadingIsPresent()) {

			for (int i = 1; i <= 3; i++) {
				Assert.assertTrue(getDriver().findElement(By.xpath(regObj.ReportAbuseFormFields(i))).isDisplayed());
				System.out.println(getDriver().findElement(By.xpath(regObj.ReportAbuseFormLabels(i))).getText()
						+ " is present on report abuse page");
				pass(getDriver(), getDriver().findElement(By.xpath(regObj.ReportAbuseFormLabels(i))).getText()
						+ " is present on report abuse page");
			}
			regObj.FacebookLoginEmailField.clear();
			regObj.FacebookLoginEmailField.sendKeys(dataReader.getDirectloginUsername());
			if (regObj.FacebookLoginEmailField.getAttribute("value").contains(dataReader.getDirectloginUsername())) {
				System.out.println("Email field passed");
				pass(getDriver(), "Email field passed");
			}
			regObj.ReportAbuseComplaintField.clear();
			regObj.ReportAbuseComplaintField.sendKeys("Test");
			if (regObj.ReportAbuseComplaintField.getAttribute("value").contains("Test")) {
				System.out.println("Complaint field passed");
				pass(getDriver(), "Complaint field passed");
			}
			if (regObj.RegistrationSubmitButton.isDisplayed()) {
				System.out.println("Submit button is displayed");
				pass(getDriver(), "Submit button is displayed");
			}
			Thread.sleep(2000);

			// Click on back button
			WaitMethods.WaitUntilElementClickable(getDriver(), regObj.ReportAbuseBackButton);
			regObj.ReportAbuseBackButton.click();

			Thread.sleep(2000);
			Assert.assertTrue(articleurl.contains(getDriver().getCurrentUrl()));
			pass(getDriver(), "Report abuse pass");
			System.out.println("Report abuse pass");
		} else {
			System.out.println("Report abuse page is not present");
			fail(getDriver(), "Report abuse page is not present");
		}

	}

	@Test(retryAnalyzer = Retry.class, description = "voting UP and DOWN comment")
	public void g_RateComment() throws Exception {
		ArticlePageObj articleObj = new ArticlePageObj(getDriver());
		ReaderCommentMethods.commentSection(getDriver());

		try {
			((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,-150);");
		} catch (Exception e) {
		}
		// click on oldest tab
		WaitMethods.WaitUntilElementClickable(getDriver(), articleObj.CommentTabs.get(1));
		try {
			((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,-150);");
		} catch (Exception e) {
		}
		// articleObj.CommentTabs.get(1).click();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", articleObj.CommentTabs.get(1));
		Thread.sleep(5000);

		// Rate UP
		ReaderCommentMethods.rateComment(getDriver(), "voteUp");
		Thread.sleep(2000);

		// Rate down
		ReaderCommentMethods.rateComment(getDriver(), "voteDown");
	}

}
