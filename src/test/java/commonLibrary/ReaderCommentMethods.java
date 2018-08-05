/**
 * 
 */
package commonLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.ArticlePageObj;
import pageObjects.RegistrationObj;
import util.DataFileReader;
import util.SystemDateTime;

/**
 * @author jigneshkumarpatel generic methods for reader commnets
 */
public class ReaderCommentMethods extends browsers.BeforeAfter {

	public static void commentSection(WebDriver driver) throws Exception {
		Properties prop = new Properties();
		ArticlePageObj articleObj = new ArticlePageObj(driver);

		File file = new File(System.getProperty("user.dir") + "/RCarticle.properties");
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);

		driver.get(prop.getProperty("RcArticleUrl"));
		WaitMethods.WaitUntilElementClickable(driver, articleObj.CommentIconTop);
		articleObj.CommentIconTop.click();
		Thread.sleep(3000);
		WaitMethods.WaitUntilElementVisible(driver, articleObj.CommentModule);
		CloseFlyouts.closeVideoPlayer(getDriver());
		CloseFlyouts.closeNewsletter(getDriver());
		System.out.println(driver.getCurrentUrl());
		info(driver, driver.getCurrentUrl());
	}

	public static void commentLoginAndVerifySubmission(WebDriver driver, WebElement messageField,
			WebElement submitButton) throws InterruptedException {
		RegistrationObj regObj = new RegistrationObj(driver);
		ArticlePageObj articleObj = new ArticlePageObj(driver);
		DataFileReader dataReader = new DataFileReader();

		// click on comment/ reply and type in field then click submit
		messageField.click();
		// messageField.clear();
		driver.switchTo().activeElement().sendKeys("comment" + SystemDateTime.currentDateTime());

		try {
			Assert.assertTrue(messageField.getAttribute("value").contains("comment"));
		} catch (Exception e) {
		}

		try {
			CloseFlyouts.closeNewsletter(driver);
		} catch (Exception e) {
		}

		WaitMethods.WaitUntilElementClickable(driver, submitButton);
		submitButton.click();
		Thread.sleep(2000);

		// login
		if (regObj.DirectLoginHeadingIsPresent()) {
			regObj.DirectLoginEmailField.clear();
			regObj.DirectLoginEmailField.sendKeys(dataReader.getDirectloginUsername());
			regObj.DirectLoginPasswordField.clear();
			regObj.DirectLoginPasswordField.sendKeys(dataReader.getDirectloginPassword());
			regObj.LoginButtonOnLoginpage.click();
			Thread.sleep(2000);
		} else {
			System.out.println("User is already logged in");
		}
		// Assert comment submission message
		try {
			WaitMethods.WaitUntilElementClickable(driver, articleObj.CommentIconTop);
			articleObj.CommentIconTop.click();
			Thread.sleep(2000);
			WaitMethods.WaitUntilElementVisible(driver, articleObj.commentSubmissionMessage);
			articleObj.commentSubmissionMessageIsPresent();
			System.out.println(articleObj.getCommentSubmissionMessage() + " is Present");
			pass(driver, "comment submission message is present");
		} catch (Exception e) {

		}
		// Logout
		WaitMethods.WaitUntilElementClickable(driver, regObj.LogoutButton);
		try {
			regObj.LogoutButton.click();
		} catch (Exception e) {
		}
	}

	public static void rateComment(WebDriver driver, String voteMethod) throws InterruptedException {

		ArticlePageObj articleObj = new ArticlePageObj(driver);

		CloseFlyouts.closeNewsletter(driver);

		String rateCount = articleObj.getRateCount(voteMethod);
		// parse text into integer
		System.out.println(voteMethod + " count are " + Integer.parseInt(rateCount));
		info(driver, voteMethod + " count are " + Integer.parseInt(rateCount));

		// articleObj.rateButton(voteMethod).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", articleObj.rateButton(voteMethod));
		Thread.sleep(3000);

		System.out
				.println(voteMethod + " count after is " + Integer.parseInt(articleObj.getRateCountAfter(voteMethod)));
		info(driver, voteMethod + " after is " + Integer.parseInt(articleObj.getRateCountAfter(voteMethod)));
		if (!articleObj.getRateCountAfter(voteMethod).equals(rateCount)) {
			System.out.println("Rate is incremented after " + voteMethod);
			pass(driver, "Rate is incremented after " + voteMethod);
		}

	}

}
