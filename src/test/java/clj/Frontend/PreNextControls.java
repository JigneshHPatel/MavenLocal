/**
 * 
 */
package clj.Frontend;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import pageObjects.ArticlePageObj;
import util.Urls;
import util.Retry;

/**
 * @author jigneshkumarpatel
 *
 *
 *         Previous Next control on article page
 */

public class PreNextControls extends browsers.BeforeAfter {

	@Test(retryAnalyzer = Retry.class)
	public void PreNextControl() throws Exception {

		ArticlePageObj articleObj = new ArticlePageObj(getDriver());

		getDriver().get(Urls.articleUrl);
		Thread.sleep(3000);
		String firstarticleurl = getDriver().getCurrentUrl();
		System.out.println(firstarticleurl);
		info(getDriver(), "Current Article is  " + firstarticleurl);
		JavascriptExecutor je = (JavascriptExecutor) getDriver();

		// Next Button
		System.out.println("~~~~~~~~~~~~~~~" + System.lineSeparator() + "Next Button" + System.lineSeparator()
				+ "~~~~~~~~~~~~~~~");

		je.executeScript("arguments[0].scrollIntoView(true);", articleObj.nextButton);
		je.executeScript("arguments[0].style.display='block';", articleObj.nextButton);

		try {
			je.executeScript("arguments[0].click();", articleObj.nextButton);
			Thread.sleep(3000);

			String nextArticleUrl = getDriver().getCurrentUrl();

			if (nextArticleUrl.equals(firstarticleurl)) {
				System.out.println("Test ***FAIL*** for Next button");
				fail(getDriver(), "Test ***FAIL*** for Next button");
				return;
			} else {
				System.out.println("Test pass for Next button");
				System.out.println(nextArticleUrl);
				pass(getDriver(), "Test pass for Next button  " + nextArticleUrl);
			}

			je.executeScript("arguments[0].style.display='block';", articleObj.nextButton);
			je.executeScript("arguments[0].click();", articleObj.nextButton);
			Thread.sleep(3000);

			// stop if firefox as it does not work for firefox
			if (caps.getBrowserName().toLowerCase().contains("firefox")) {
				return;
			}
			// Previous button
			System.out.println("~~~~~~~~~~~~~~~" + System.lineSeparator() + "Previous Button" + System.lineSeparator()
					+ "~~~~~~~~~~~~~~~");

			je.executeScript("arguments[0].style.display='block';", articleObj.previousButton);
			Thread.sleep(2000);
			je.executeScript("arguments[0].click();", articleObj.previousButton);
			Thread.sleep(3000);
			if (getDriver().getCurrentUrl().equals(nextArticleUrl)) {
				System.out.println(getDriver().getCurrentUrl());
				info(getDriver(), getDriver().getCurrentUrl());
				System.out.println("Test pass for Previous button");
				pass(getDriver(), "Test pass for Previous button");
			} else {
				System.out.println("Test ***FAIL*** for Previous button");
				fail(getDriver(), "Test ***FAIL*** for Previous button");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			error(getDriver(), e.getMessage());
		}
	}

}
