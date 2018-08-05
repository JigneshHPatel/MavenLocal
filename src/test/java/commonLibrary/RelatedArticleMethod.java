/**
 * 
 */
package commonLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonLibrary.OpenNewTabMethod.newTabMethod;
import pageObjects.ArticlePageObj;

/**
 * @author jigneshkumarpatel
 *
 *         Related article method. Assert if related module is present, click on
 *
 */
public class RelatedArticleMethod extends browsers.BeforeAfter {

	public static void relatedArticle(WebDriver driver) throws Exception {

		ArticlePageObj articlePage = new ArticlePageObj(driver);
		driver.manage().window().maximize();

		// Scroll until related module
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					articlePage.relatedArticleModule);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(2000);

		// Assert share this article text
		try {
			if (articlePage.fbsharetext_onrelated.isDisplayed()) {
				System.out.println(articlePage.fbsharetext_onrelated.getText());
				pass(driver, "Share this article is present");
			} else {
				System.out.println("Share this Article is not present");
				warning(driver, "Share this Article is not present");
			}
		} catch (Exception e) {
			e.printStackTrace();
			error(driver, e.getMessage());
		}

		// Check if FB icon is present and click able
		try {
			if (articlePage.fbicon_onrelated.isDisplayed() && articlePage.fbicon_onrelated.isEnabled()) {
				System.out.println("Facebook icon on Related is present");
				pass(driver, "Facebook icon on Related is present");

			} else {
				System.out.println("Facebook icon on Related is NOT present");
				warning(driver, "Facebook icon on Related is NOT present");
			}
		} catch (Exception e) {
			e.printStackTrace();
			error(driver, e.getMessage());
		}

		// Related Articles in Module
		int NoOfArticles = articlePage.totalRelatedArticles.size();
		System.out.println("Total Related articles in module are " + NoOfArticles);

		for (int i = 1; i < NoOfArticles; i++) {

			WebElement relatedArticleLink = driver.findElement(By.xpath(ArticlePageObj.getRelatedArticle(i)));
			String expectedArticleUrl = relatedArticleLink.getAttribute("href");
			String printMsg = " related article " + i;

			OpenNewTabMethod.NewTabMethod(driver, relatedArticleLink, expectedArticleUrl, printMsg, newTabMethod.links);

		}
	}

}
