/**
 * 
 */
package cljCheck;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import browsers.Url;
import config.Retry;
import config.WaitObj;

/**
 * @author jigneshkumarpatel Test for Reader comment on article page
 *
 */

public class ReaderComment extends browsers.BeforeAfter {

	private static Properties pr = new Properties();
	

	@Test(retryAnalyzer = Retry.class, description = "Get wide and unmod article from puff and save in prpertyfile")
	public void a_ArticleUrl() throws InterruptedException {
		System.out.println("------------------------");
		System.out.println("***a_ArticleUrl***");
		System.out.println("------------------------");

		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		for (int i = 1; i < 10; i++) {
			WebElement puffArticle = getDriver()
					.findElement(By.cssSelector(".femail.item>div>ul>li:nth-child(" + i + ")>a"));
			WaitObj.wait(getDriver(), puffArticle);
			puffArticle.click();
			Thread.sleep(2000);

			String rcStatus = (String) je.executeScript("return PageCriteria.readerComments");
			String articleType = (String) je.executeScript("return PageCriteria.articleStyle");
			System.out.println("Comment status  " + rcStatus);
			System.out.println("Article type " + articleType);

			if (rcStatus.equalsIgnoreCase("W") && articleType.equalsIgnoreCase("wide")) {

				System.out.println("Article " + i);

				File f = new File(System.getProperty("user.dir") + "/RCarticle.properties");
				if (f.exists()) {
					f.delete();
				}

				try {
					f.createNewFile();
					FileInputStream fin = new FileInputStream(f);
					FileOutputStream fos = new FileOutputStream(f, true);
					pr.load(fin);
					pr.setProperty("RcArticleUrl", getDriver().getCurrentUrl());
					pr.store(fos, "article url for reader comment");
					fos.close();
					fin.close();

				} catch (Exception e) {
					System.out.println(e);
				}

				break;

			} else {
				getDriver().navigate().back();
				Thread.sleep(3000);
			}
		}

		System.out.println(pr.getProperty("RcArticleUrl"));
	}

	@Test(retryAnalyzer = Retry.class, description = "Info on comments e.g. user info, newest,oldest etc.")
	public void a_CommentInfo() throws Exception {
		System.out.println("------------------------");
		System.out.println("***CommentInfo***");
		System.out.println("------------------------");
		commentSection();

		WebElement modMessage = getDriver()
				.findElement(By.cssSelector("#reader-comments>div.rc-content >div.rc-title>p"));
		if (modMessage.isDisplayed()) {
			System.out.println("Moderation message is " + "'" + modMessage.getText() + "'");
			pass(getDriver(), "Moderation message is " + "'" + modMessage.getText() + "'");
		} else {
			System.out.println("Moderation message is NOT displyaed");
			fail(getDriver(), "Moderation message is NOT displyaed");
		}

		// Newest,Oldest tabs
		List<WebElement> tabs = getDriver().findElements(By.cssSelector("#rc-tabs>li>a"));
		for (WebElement tab : tabs) {
			String tabName = tab.getText();
			List<WebElement> allcomments = getDriver().findElements(By.cssSelector("#js-comments>div"));
			System.out.println("Total comments on " + tabName + " are " + allcomments.size());
			pass(getDriver(), "Total comments on " + tabName + " are " + allcomments.size());
		}
		String userInfo = getDriver().findElement(By.xpath("//*[@id='js-comments']/div[1]/div[2]/p[1]")).getText();
		String userGeo = getDriver().findElement(By.xpath("//*[@id='js-comments']/div[1]/div[2]/p[1]/a")).getText();
		System.out.println("User Info is " + userInfo + userGeo);
		pass(getDriver(), "User Info is " + userInfo + userGeo);
		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "posting direct comment by logging from post comment path")
	public void b_PostComment() throws Exception {
		System.out.println("-------------------------");
		System.out.println("***PostComment***");
		System.out.println("-------------------------");
		commentSection();
		Actions action = new Actions(getDriver());
		getDriver().findElement(By.id("message")).clear();
		getDriver().findElement(By.id("message")).sendKeys("comment" + Url.currentDate);
		Assert.assertTrue(getDriver().findElement(By.id("message")).getAttribute("value").contains("comment"));
		try {
			if (getDriver().findElement(By.cssSelector(".vjs-flyout-close.vjs-flyout-button")).isDisplayed()) {
				getDriver().findElement(By.cssSelector(".vjs-flyout-close.vjs-flyout-button")).click();
				Thread.sleep(1000);
			}
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		WebElement submitComment = getDriver().findElement(By.cssSelector(".btn.btn-submit"));
		try {
			action.moveToElement(submitComment).build().perform();
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

		WaitObj.wait(getDriver(), submitComment);
		// je.executeScript("arguments[0].click();", submitComment);
		submitComment.click();
		Thread.sleep(3000);
		String expectedText = "Login";
		// String actualText =
		// getDriver().findElement(By.cssSelector(".lcolumn>h3")).getText();
		if (expectedText.equalsIgnoreCase(getDriver().findElement(By.cssSelector(".lcolumn>h3")).getText())) {
			WebElement emailField = getDriver().findElement(By.id("reg-lbx-email-page"));
			emailField.clear();
			emailField.sendKeys("testmol@sharklasers.com");
			WebElement passField = getDriver().findElement(By.id("reg-lbx-password-page"));
			passField.clear();
			passField.sendKeys("testmol");
			getDriver().findElement(By.cssSelector(".reg-btn-login")).click();
			Thread.sleep(2000);
		} else {
			System.out.println("User is already logged in");
			info(getDriver(), "User is already logged in");
		}
		try {
			getDriver().findElement(By.cssSelector(".comments-count.home")).click();
			Thread.sleep(4000);
			String submissionMsg = getDriver().findElement(By.cssSelector("div.submission-feedback-message")).getText();

			Assert.assertTrue(submissionMsg.contains("Thanks for sharing your comment"));
			pass(getDriver(), " Comment Pass");
			info(getDriver(), submissionMsg + " is present");
			System.out.println(" Direct comment pass");
			System.out.println(submissionMsg + " is present");
		} catch (Exception e) {
			System.out.println("Submission Massage not present");
			System.out.println(e.getMessage());
			error(getDriver(), e.getMessage());
		}

		try {
			WebElement logoutButton = getDriver().findElement(By.id("logout"));
			WaitObj.wait(getDriver(), logoutButton);
			// je.executeScript("arguments[0].scrollIntoView(true);", logoutButton);
			Thread.sleep(2000);
			logoutButton.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(2000);
		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "Post Reply on comment in oldest filter")
	public void c_PostReply() throws Exception {
		System.out.println("---------------------");
		System.out.println("***PostReply***");
		System.out.println("---------------------");
		commentSection();
		Actions action = new Actions(getDriver());
		// click on oldest tab
		getDriver().findElement(By.cssSelector("#rc-tabs>li:nth-child(2)>a")).click();
		Thread.sleep(5000);

		List<WebElement> allReply = getDriver()
				.findElements(By.cssSelector("button.btn.anchor-reply-comment.js-reply"));
		WebElement reply = allReply.get(0);
		WaitObj.wait(getDriver(), reply);
		reply.click();
		// Thread.sleep(2000);
		try {
			WebElement replyfield = getDriver().findElement(By.cssSelector("div.reply-form.textarea-holder.field"));
			replyfield.click();
			getDriver().switchTo().activeElement().sendKeys("reply" + Url.currentDate);
			Assert.assertTrue(replyfield.getAttribute("value").contains("reply"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		WebElement post = getDriver().findElement(By.cssSelector(".btn.js-reply-submit"));
		WaitObj.wait(getDriver(), post);
		try {
			action.moveToElement(post).build().perform();
		} catch (Exception e1) {

		}
		post.click();

		Thread.sleep(3000);
		String expectedText = "Login";
		// String actualText =
		// getDriver().findElement(By.cssSelector(".lcolumn>h3")).getText();
		if (expectedText.equalsIgnoreCase(getDriver().findElement(By.cssSelector(".lcolumn>h3")).getText())) {
			WebElement emailField = getDriver().findElement(By.id("reg-lbx-email-page"));
			emailField.clear();
			emailField.sendKeys("testmol@sharklasers.com");
			WebElement passField = getDriver().findElement(By.id("reg-lbx-password-page"));
			passField.clear();
			passField.sendKeys("testmol");
			getDriver().findElement(By.cssSelector(".reg-btn-login")).click();
			Thread.sleep(3000);
		} else {
			System.out.println("Login is not present");
			info(getDriver(), "Login is not present");
		}
		try {
			getDriver().findElement(By.cssSelector(".comments-count.home")).click();
			Thread.sleep(4000);
			String submissionMsg = getDriver().findElement(By.cssSelector("div.submission-feedback-message")).getText();

			Assert.assertTrue(getDriver().getPageSource().contains("Thanks for sharing your comment"));
			pass(getDriver(), " Comment Reply Pass");
			info(getDriver(), submissionMsg + " is present");
			System.out.println("Comment Reply pass");
			System.out.println(submissionMsg + " is present");
		} catch (Exception e) {
			System.out.println("Submission Massage not present");
			System.out.println(e.getMessage());
			error(getDriver(), e.getMessage());
		}
		try {

			WebElement logoutButton = getDriver().findElement(By.id("logout"));
			WaitObj.wait(getDriver(), logoutButton);
			// je.executeScript("arguments[0].scrollIntoView(true);", logoutButton);
			Thread.sleep(2000);

			logoutButton.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(2000);
		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "Report abuse, asserting all filed are present in report abuse page and negative test")
	public void d_ReportAbuse() throws Exception {
		System.out.println("---------------------");
		System.out.println("***ReportAbuse***");
		System.out.println("---------------------");
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		commentSection();
		String articleurl = getDriver().getCurrentUrl();
		WebElement reportAbuse = getDriver().findElement(By.cssSelector("#js-comments>div:nth-child(1)>a"));
		try {
			je.executeScript("arguments[0].style='visibility: visible;'", reportAbuse);
			WaitObj.wait(getDriver(), reportAbuse);
			reportAbuse.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			error(getDriver(), e.getMessage());
		}
		Thread.sleep(2000);
		if (getDriver().findElement(By.cssSelector("h2.debate-page-header.box")).getText()
				.equalsIgnoreCase("Report abuse")) {
			Assert.assertTrue(getDriver().findElement(By.xpath("//*[@id='abuse-details']/div[1]/div")).isDisplayed());
			System.out.println("Article title is present on report abuse page");
			pass(getDriver(), "Article title is present on report abuse page");
			Assert.assertTrue(getDriver().findElement(By.xpath("//*[@id='abuse-details']/div[2]/div")).isDisplayed());
			System.out.println("Parent comment is present on report abuse page");
			pass(getDriver(), "Parent comment is present on report abuse page");
			Assert.assertTrue(getDriver().findElement(By.xpath("//*[@id='abuse-details']/div[3]/div")).isDisplayed());
			System.out.println("Comment author details is present on report abuse page");
			pass(getDriver(), "Comment author details is present on report abuse page");
			WebElement emailField = getDriver().findElement(By.id("email"));
			emailField.clear();
			emailField.sendKeys("testmol@sharklasers.com");
			if (emailField.getAttribute("value").contains("testmol@sharklasers.com")) {
				System.out.println("Email field passed");
				pass(getDriver(), "Email field passed");
			}
			WebElement complaint = getDriver().findElement(By.id("complaint"));
			complaint.clear();
			complaint.sendKeys("Test");
			if (complaint.getAttribute("value").contains("Test")) {
				System.out.println("Complaint field passed");
				pass(getDriver(), "Complaint field passed");
			}
			if (getDriver().findElement(By.cssSelector("button.reg-btn.wocc")).isDisplayed()) {
				System.out.println("Submit button is displayed");
				pass(getDriver(), "Submit button is displayed");
			}
			Thread.sleep(2000);
			/*
			 * if(getDriver().findElement(By.cssSelector("div.reg-fld.fail")).isDisplayed())
			 * { System.out.println("Report Abuse error message displyed");
			 * pass(getDriver(),"Report Abuse error message displyed"); }else {
			 * System.out.println("Report Abuse error message is **NOT** displyed");
			 * fail(getDriver(),"Report Abuse error message is **NOT** displyed"); }
			 */

			// Click on back button
			WebElement backButton = getDriver().findElement(By.cssSelector(".reg-btn.reverse-wocc"));
			WaitObj.wait(getDriver(), backButton);
			backButton.click();
			// getDriver().findElement(By.cssSelector("#abuse-details > div:nth-child(10) >
			// div >
			// a")).click();
			Thread.sleep(2000);
			try {
				System.out.println("Aricle url " + articleurl);
				if (getDriver().getCurrentUrl().contains("/comments")) {
					String expectedUrl = articleurl.replace("article", "comments");
					System.out.println("if Expected: " + expectedUrl);
					System.out.println("actual " + getDriver().getCurrentUrl());
					Assert.assertTrue(expectedUrl.contains(getDriver().getCurrentUrl()));
				} else {
					String expectedUrl = articleurl;
					System.out.println("else Expected: " + expectedUrl);
					System.out.println("actual " + getDriver().getCurrentUrl());
					Assert.assertTrue(expectedUrl.contains(getDriver().getCurrentUrl()));
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(getDriver().getCurrentUrl());
			}
			pass(getDriver(), "Report abuse pass");
			System.out.println("Report abuse pass");
		} else {
			System.out.println("Report abuse page is not present");
			fail(getDriver(), "Report abuse page is not present");
		}
		System.out.println(line);

	}

	@Test(retryAnalyzer = Retry.class, description = "voting UP and DOWN comment")
	public void e_RateComment() throws Exception {
		System.out.println("----------------------");
		System.out.println("***RateComment***");
		System.out.println("----------------------");
		commentSection();
		// click on oldest tab
		getDriver().findElement(By.cssSelector("#rc-tabs>li:nth-child(2)>a")).click();
		Thread.sleep(5000);

		List<WebElement> allrate = getDriver()
				.findElements(By.cssSelector("div.rating-button.grey-rating-button.rating-button-up"));
		System.out.println("Total rateButtonsUp are " + allrate.size());
		info(getDriver(), "Total rateButtonsUp are " + allrate.size());
		WebElement rate = allrate.get(0);
		List<WebElement> allrateCount = getDriver().findElements(By.cssSelector("div.rating-button-inline"));
		WebElement rateCountEle = allrateCount.get(1);
		String rateCount = rateCountEle.getText();
		System.out.println("Rate Up count are " + Integer.parseInt(rateCount));
		info(getDriver(), "Rate Up count are " + Integer.parseInt(rateCount));
		rate.click();
		Thread.sleep(3000);
		List<WebElement> allrateCountAfter = getDriver().findElements(By.cssSelector("div.rating-button-inline"));
		WebElement rateCountEleAfter = allrateCountAfter.get(1);
		String rateCountAfter = rateCountEleAfter.getText();
		System.out.println("Rate Up count after is " + Integer.parseInt(rateCountAfter));
		info(getDriver(), "Rate count Up after is " + Integer.parseInt(rateCountAfter));

		if (rateCountAfter.equals(rateCount)) {
			System.out.println("Rateup is not incremented after Rate up");
			warning(getDriver(), "Rateup is not incremented after Rate up");
		} else {
			System.out.println("Rate is incremented after Rate up");
			pass(getDriver(), "Rate is incremented after Rate up");
		}

		// Rate down

		System.out.println("*****Rate down*****");
		List<WebElement> allratedown = getDriver()
				.findElements(By.cssSelector("div.rating-button.grey-rating-button.rating-button-down"));
		System.out.println("Total rateButtonsDown are " + allratedown.size());
		info(getDriver(), "Total rateButtonsDown are " + allratedown.size());
		WebElement ratedown = allratedown.get(1);
		List<WebElement> allrateCountDown = getDriver().findElements(By.cssSelector("div.rating-button-inline"));
		WebElement rateCountDownEle = allrateCountDown.get(0);
		String rateCountDown = rateCountDownEle.getText();
		System.out.println("Rate Down count are " + Integer.parseInt(rateCountDown));
		info(getDriver(), "Rate Down count are " + Integer.parseInt(rateCountDown));
		ratedown.click();
		Thread.sleep(3000);
		List<WebElement> allrateCountDownAfter = getDriver().findElements(By.cssSelector("div.rating-button-inline"));
		WebElement rateCountDownEleAfter = allrateCountDownAfter.get(0);
		String rateCountDownAfter = rateCountDownEleAfter.getText();
		System.out.println("Rate Down count after is " + Integer.parseInt(rateCountDownAfter));
		info(getDriver(), "Rate count Down after is " + Integer.parseInt(rateCountDownAfter));
		/*
		 * if(getDriver().findElement(By.xpath(
		 * "//*[@id='comment-295157525']/div[2]/div[1]/span")).getText().equals("Rated")
		 * ) { System.out.println("Rated text present");
		 * pass(getDriver(),"Rated text present"); }
		 */
		if (rateCountDownAfter.equals(rateCountDown)) {
			System.out.println("RateDown is not incremented after Rate down");
			warning(getDriver(), "RateDown is not incremented after Rate down");
		} else {
			System.out.println("Rate is incremented after Rate Down");
			pass(getDriver(), "Rate is incremented after Rate Down");
		}

		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "Posting comment to Facebook and Dailymail")
	public void f_FbComment() throws Exception {
		System.out.println("----------------------");
		System.out.println("***FbComment***");
		System.out.println("----------------------");
		commentSection();
		WebDriverWait visibleWait = new WebDriverWait(getDriver(), 30);

		getDriver().findElement(By.id("message")).clear();
		getDriver().findElement(By.id("message")).sendKeys("comment" + Url.currentDate);
		Assert.assertTrue(getDriver().findElement(By.id("message")).getAttribute("value").contains("comment"));
		getDriver().findElement(By.id("handlePostToFacebookInput")).click();
		Thread.sleep(2000);
		WaitObj.wait(getDriver(), getDriver().findElement(By.cssSelector(".btn.fbConfirm")));
		getDriver().findElement(By.cssSelector(".btn.fbConfirm")).click();
		Thread.sleep(3000);
		try {
			Assert.assertTrue(getDriver().getCurrentUrl().contains("www.facebook.com/login"));
			System.out.println("Facebook login page is present");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Facebook login page is ***NOT*** present");
		}

		WebElement email = getDriver().findElement(By.id("email"));
		email.clear();
		email.sendKeys("testmolfb1@gmail.com");
		WebElement pass = getDriver().findElement(By.id("pass"));
		pass.clear();
		pass.sendKeys("testmol");
		getDriver().findElement(By.id("loginbutton")).click();
		Thread.sleep(2000);
		WaitObj.wait(getDriver(), getDriver().findElement(By.cssSelector(".btn.btn-submit")));
		getDriver().findElement(By.cssSelector(".btn.btn-submit")).click();
		visibleWait.until(ExpectedConditions
				.visibilityOf(getDriver().findElement(By.cssSelector(".submission-feedback-fbmessage"))));
		String message = getDriver().findElement(By.cssSelector(".submission-feedback-fbmessage")).getText();
		if (message.equalsIgnoreCase("Your comment will also be posted to the Facebook account for Don Joe")) {
			System.out.println("Comment posted by FB");
		} else {
			System.out.println("confirmation message not present");
		}
	}

	public static void commentSection() throws Exception {

		/*
		 * JavascriptExecutor je = (JavascriptExecutor) getDriver(); //
		 * List<WebElement>puffArticles=getDriver().findElements(By.cssSelector(
		 * ".femail.item>div>ul>li>a")); for (int i = 1; i < 10; i++) { WebElement
		 * puffArticle = getDriver()
		 * .findElement(By.cssSelector(".femail.item>div>ul>li:nth-child(" + i +
		 * ")>a")); // String rcArticle = puffArticle.getAttribute("href");
		 * WaitObj.wait(getDriver(), puffArticle); puffArticle.click();
		 * Thread.sleep(2000);
		 * 
		 * String rcStatus = (String)
		 * je.executeScript("return PageCriteria.readerComments"); String articleType =
		 * (String) je.executeScript("return PageCriteria.articleStyle");
		 * System.out.println("Comment status  " + rcStatus);
		 * System.out.println("Article type " + articleType);
		 * 
		 * if (rcStatus.equalsIgnoreCase("W") && articleType.equalsIgnoreCase("wide")) {
		 * 
		 * System.out.println("Article " + i); break;
		 * 
		 * } else { getDriver().navigate().back(); Thread.sleep(3000); } }
		 * 
		 * 
		 */

		getDriver().get(pr.getProperty("RcArticleUrl"));
		Thread.sleep(3000);
		System.out.println(getDriver().getCurrentUrl());
		info(getDriver(), getDriver().getCurrentUrl());
		try {
			if (getDriver().findElement(By.cssSelector(".floater-19vuu")).isDisplayed()) {
				getDriver().findElement(By.cssSelector(".closeButton-1i2Y6")).click();

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			if (getDriver().findElement(By.cssSelector(".vjs-flyout-close.vjs-flyout-button")).isDisplayed()) {
				getDriver().findElement(By.cssSelector(".vjs-flyout-close.vjs-flyout-button")).click();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (getDriver().findElement(By.id("logout")).isDisplayed()) {
			System.out.println("User is already logged in");
			getDriver().findElement(By.id("logout")).click();
			Thread.sleep(2000);
			Assert.assertTrue(getDriver().findElement(By.id("login")).isDisplayed());
			System.out.println("User is logged out");
		}

		WebElement commentIcon = getDriver().findElement(By.cssSelector("a.comments-count.home"));
		if (commentIcon.isDisplayed()) {
			if (getDriver().getPageSource().contains("We are no longer accepting comments on this article.")) {
				System.out.println("comments are NOT allowed on this article");
				warning(getDriver(), "comments are NOT allowed on this article");
			}
			commentIcon.click();
			Thread.sleep(3000);
			try {
				Assert.assertTrue(getDriver().getCurrentUrl().contains("comments"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				if (getDriver().findElement(By.cssSelector(".floater-19vuu")).isDisplayed()) {
					getDriver().findElement(By.cssSelector(".closeButton-1i2Y6")).click();

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {

			System.out.println("comment is NOT present");
			fail(getDriver(), "comment is NOT present");
			return;
		}

	}

}
