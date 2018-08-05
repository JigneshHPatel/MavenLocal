/**
 * 
 */
package commonLibrary;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import commonLibrary.OpenNewTabMethod.newTabMethod;
import commonLibrary.SocialLoginsMethods.socialMethodType;
import pageObjects.PollObj;

/**
 * @author jigneshkumarpatel Generic method for poll, assert if poll is present,
 *         questions, answers and social icons.
 */
public class PollMethod extends browsers.BeforeAfter {

	public static void poll(WebDriver driver) throws Exception {
		PollObj pollObj = new PollObj(driver);

		if (pollObj.PollIsDisplayed()) {
			System.out.println("Poll is present");
			pass(driver, "Poll is present");
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", pollObj.Poll);
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
			Thread.sleep(2000);
			if (pollObj.PollImageIsDisplayed()) {
				System.out.println("Poll image is present");
				pass(driver, "Poll image is present");
			} else {
				System.out.println("Poll image is ***NOT*** present");
				fail(driver, "Poll image is ***NOT*** present");
			}
			System.out.println("Poll Question is: " + pollObj.PollQuestionText());
			pass(driver, "Poll Question is: " + pollObj.PollQuestionText());

			System.out.println("Total Poll Answers are: " + pollObj.PollAnswers.size());
			info(driver, "Total Poll Answers are: " + pollObj.PollAnswers.size());
			for (WebElement pollAns : pollObj.PollAnswers) {

				System.out.println(pollAns.getText());
				pass(driver, pollAns.getText());
			}
			// click on first Answer
			pollObj.PollFirstQuestion.click();
			Thread.sleep(2000);

			System.out.println("Total post answers are:  " + pollObj.PollAnswersAfterVote.size());
			info(driver, "Total post answers are:  " + pollObj.PollAnswersAfterVote.size());
			for (int p = 1; p <= pollObj.PollAnswersAfterVote.size(); p++) {
				String postAns = driver.findElement(By.xpath(pollObj.PollAnswerAfterVote(p))).getText();
				String percentage = driver.findElement(By.xpath(pollObj.PollAnswerPercentage(p))).getText();
				String votes = driver.findElement(By.xpath(pollObj.PollAnswerVotes(p))).getText();

				System.out.println("Post vote Result for: " + p);
				System.out.println(postAns + "  |  " + percentage + "  |  " + votes);
				System.out.println("-------------------");
				info(driver, "Post vote Result for: " + p);
				pass(driver, postAns + "  |  " + percentage + "  |  " + votes);
			}

			if (pollObj.PollSocialShareModuleIsDisplayed()) {
				System.out.println("Socialshare module is present");
				try {
					pollObj.AssertPollSocialshareHeading();
					System.out.println("Now share your opinion heading is present");
					pass(driver, "Now share your opinion heading is present");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}

			for (WebElement socialIcon : pollObj.TotalSocialShares) {
				String socialName = socialIcon.getAttribute("class");
				System.out.println(socialName);
				info(driver, socialName);
				String parent = driver.getWindowHandle();
				if (socialName.equalsIgnoreCase("gplus")) {
					System.out.println("googleplus is present");
					continue;
				}
				OpenNewTabMethod.NewTabMethod(driver, socialIcon, null, null, newTabMethod.socialIcons);
				if (socialName.equalsIgnoreCase("facebook")) {

					SocialLoginsMethods.SocialLoginMethod(driver, "poll", socialMethodType.facebook);

				} else if (socialName.equalsIgnoreCase("twitter")) {

					SocialLoginsMethods.SocialLoginMethod(driver, "poll", socialMethodType.twitter);

				} else if (socialName.equalsIgnoreCase("pinterest")) {
					Assert.assertTrue(driver.getCurrentUrl().contains("pinterest"));
					System.out.println("Pass for Pinterest");
					pass(driver, "Pass for Pinterest");
				} else if (socialName.equalsIgnoreCase("gplus")) {
					Assert.assertTrue(driver.getCurrentUrl().contains("google.com/signin"));
					System.out.println("Pass for GooglePlus");
					pass(driver, "Pass for GooglePlus");

				}
				ArrayList<String> child2 = new ArrayList<String>(driver.getWindowHandles());
				if (child2.size() > 1) {
					driver.close();
				}
				driver.switchTo().window(parent);
				Thread.sleep(2000);
				System.out.println("-----------");

			}

		} else {
			System.out.println("Poll is ***NOT*** present");
			fail(driver, "Poll is ***NOT*** present");
		}
	}

}
