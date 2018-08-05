/**
 * 
 */
package clj.Frontend;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import util.Urls;
import commonLibrary.PollMethod;
import commonLibrary.WaitMethods;
import util.Retry;

/**
 * @author jigneshkumarpatel
 * 
 *         Poll on channel Share poll
 *
 */
public class Poll extends browsers.BeforeAfter {

	@Test(retryAnalyzer = Retry.class, description = "On Channel,Check poll image, Question, Answers, vote on answer and share on social media")
	public void a_PollonChannel() throws Exception {

		WaitMethods.WaitUntilElementClickable(getDriver(), getDriver().findElement(By.linkText("Sport")));
		getDriver().findElement(By.linkText("Sport")).click();
		Thread.sleep(3000);
		System.out.println(getDriver().getTitle());
		info(getDriver(), getDriver().getTitle());

		PollMethod.poll(getDriver());

	}

	@Test(retryAnalyzer = Retry.class, description = "On Article, Check poll image, Question, Answers, vote on answer and share on social media")
	public void b_PollonArticle() throws Exception {

		getDriver().get(Urls.pollArticleUrl);

		Thread.sleep(3000);

		System.out.println(getDriver().getCurrentUrl());
		System.out.println(getDriver().getTitle());
		info(getDriver(), getDriver().getTitle());

		PollMethod.poll(getDriver());

	}

}
