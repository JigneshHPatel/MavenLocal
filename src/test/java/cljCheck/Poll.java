/**
 * 
 */
package cljCheck;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import browsers.DataFileReader;
import browsers.Url;
import config.CmpConsent;
import config.Retry;
import config.WaitObj;

/**
 * @author jigneshkumarpatel
 * 
 *         Poll on channel Share poll
 *
 */
public class Poll extends browsers.BeforeAfter {
	private static DataFileReader dataReader = new DataFileReader();

	@Test(retryAnalyzer = Retry.class, description = "On Channel,Check poll image, Question, Answers, vote on answer and share on social media")
	public void a_PollonChannel() throws Exception {
		System.out.println("--------------------------");
		System.out.println("****PollonChannel****");
		System.out.println("--------------------------");
		getDriver().get(dataReader.getBaseUrl() + "/home/index.html");
		Thread.sleep(2000);
		CmpConsent.gdprConsent();
		WaitObj.wait(getDriver(), getDriver().findElement(By.linkText("Sport")));
		getDriver().findElement(By.linkText("Sport")).click();
		Thread.sleep(3000);
		System.out.println(getDriver().getTitle());
		info(getDriver(), getDriver().getTitle());

		poll(getDriver());

		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "On Article, Check poll image, Question, Answers, vote on	answer and	share on social media")

	public void b_PollonArticle() throws Exception {
		System.out.println("--------------------------");
		System.out.println("****PollonArticle****");
		System.out.println("--------------------------");
		getDriver().get(dataReader.getBaseUrl() + "/news/article-3788823/");
		Thread.sleep(3000);
		CmpConsent.gdprConsent();
		System.out.println(getDriver().getCurrentUrl());
		System.out.println(getDriver().getTitle());
		info(getDriver(), getDriver().getTitle());

		poll(getDriver());

		System.out.println(line);
	}

	public static void poll(WebDriver driver) throws Exception {
		if (driver.findElement(By.xpath("//*[contains(@id,'poll-share')]")).isDisplayed()) {
			System.out.println("Poll is present");
			pass(driver, "Poll is present");
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
						driver.findElement(By.xpath("//*[contains(@id,'poll-share')]")));
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
			Thread.sleep(2000);
			if (driver.findElement(By.cssSelector(".bdrcc.poll-img")).isDisplayed()) {
				System.out.println("Poll image is present");
				pass(driver, "Poll image is present");
			} else if (!driver.findElement(By.cssSelector(".bdrcc.poll-img")).isDisplayed()) {
				System.out.println("Poll image is ***NOT*** present");
				fail(driver, "Poll image is ***NOT*** present");
			}
			System.out.println("Poll Question is: " + driver.findElement(By.cssSelector(".poll-question")).getText());
			pass(driver, "Poll Question is: " + driver.findElement(By.cssSelector(".poll-question")).getText());
			List<WebElement> pollAnswers = driver.findElements(By.cssSelector("div.poll-answer>label"));
			System.out.println("Total Poll Answers are: " + pollAnswers.size());
			info(driver, "Total Poll Answers are: " + pollAnswers.size());
			for (WebElement pollAns : pollAnswers) {

				System.out.println(pollAns.getText());
				pass(driver, pollAns.getText());
			}
			// click on first Answer
			driver.findElement(By.xpath("//*[@method='post']/div[1]/label")).click();
			Thread.sleep(2000);
			List<WebElement> totalPostAns = driver
					.findElements(By.xpath("//*[contains(@class,'poll-results cleared')]/ul/li"));
			System.out.println("Total post answers are:  " + totalPostAns.size());
			info(driver, "Total post answers are:  " + totalPostAns.size());
			for (int p = 1; p <= totalPostAns.size(); p++) {
				String postAns = driver
						.findElement(By.xpath("//*[contains(@class,'poll-results cleared')]/ul/li[" + p + "]/span[1]"))
						.getText();
				String ptage = driver
						.findElement(By
								.xpath("//*[contains(@class,'poll-results cleared')]/ul/li[" + p + "]/span[3]/span[1]"))
						.getText();
				String votes = driver
						.findElement(
								By.xpath("//*[contains(@class,'poll-results cleared')]/ul/li[\"+p+\"]/span[3]/span[2]"))
						.getText();
				System.out.println("Post vote Result for: " + p);
				System.out.println(postAns + "  |  " + ptage + "  |  " + votes);
				System.out.println("-------------------");
				info(driver, "Post vote Result for: " + p);
				pass(driver, postAns + "  |  " + ptage + "  |  " + votes);
			}

			if (driver.findElement(By.cssSelector(".dms-poll")).isDisplayed()) {
				System.out.println("Socialshare module is present");
				try {
					Assert.assertTrue(driver.findElement(By.cssSelector(".dms-poll>p")).getText()
							.equalsIgnoreCase("Now share your opinion"));
					System.out.println("Now share your opinion heading is present");
					pass(driver, "Now share your opinion heading is present");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
			List<WebElement> totalSocialShares = driver.findElements(By.cssSelector(".dms-poll>ul>li"));
			for (WebElement socialIcon : totalSocialShares) {
				String socialName = socialIcon.getAttribute("class");
				System.out.println(socialName);
				info(driver, socialName);
				String parent = driver.getWindowHandle();

				try {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", socialIcon);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}

				Thread.sleep(2000);
				WaitObj.wait(driver, socialIcon);
				try {
					socialIcon.click();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				Thread.sleep(2000);

				ArrayList<String> child = new ArrayList<String>(driver.getWindowHandles());
				System.out.println("Total windows " + child.size());
				if (child.size() > 1) {
					driver.switchTo().window(child.get(1));
				} else {
					System.out.println(socialName + " ***Not*** clicked");
					System.out.println("Fail for " + socialName);
					fail(driver, "Fail for " + socialName + " Element not clicked");
					continue;
				}
				if (socialName.equalsIgnoreCase("facebook")) {
					Assert.assertTrue(driver.getCurrentUrl().contains("www.facebook.com/login"));
					driver.findElement(By.id("email")).clear();
					driver.findElement(By.id("email")).sendKeys(dataReader.getFacebookUsername());
					driver.findElement(By.id("pass")).clear();
					driver.findElement(By.id("pass")).sendKeys(dataReader.getFacebookPassword());
					driver.findElement(By.cssSelector("input[name='login']")).click();
					Thread.sleep(2000);
					System.out.println("Logged into FB");
					info(driver, "Logged into FB");
					driver.findElement(By.id("u_0_1v")).click();
					Thread.sleep(2000);
					System.out.println("Poll shared on FB");
					pass(driver, "Poll shared on FB");

				} else if (socialName.equalsIgnoreCase("twitter")) {
					Assert.assertTrue(driver.getCurrentUrl().contains("twitter.com"));
					String sharefield = driver.findElement(By.id("status")).getAttribute("value");
					String sharelink = Url.currentDate + sharefield;
					driver.findElement(By.id("status")).clear();
					driver.findElement(By.id("status")).sendKeys(sharelink);
					driver.findElement(By.id("username_or_email")).clear();
					driver.findElement(By.id("username_or_email")).sendKeys(dataReader.getTwitterUsername());
					driver.findElement(By.id("password")).clear();
					driver.findElement(By.id("password")).sendKeys(dataReader.getTwitterPassword());
					driver.findElement(By.cssSelector("input[value='Log in and Tweet']")).click();
					System.out.println("Poll shared on Twitter");
					pass(driver, "Poll shared on Twitter");

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
