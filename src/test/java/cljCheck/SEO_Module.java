/**
 * 
 */
package cljCheck;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import browsers.DataFileReader;
import config.Retry;
import config.WaitObj;

/**
 * @author jigneshkumar.patel
 *
 *         This is SEO module test on channel page Test if all article links and
 *         images are displayed open two article from module
 *
 *
 */
public class SEO_Module extends browsers.BeforeAfter {
	private static DataFileReader dataReader = new DataFileReader();

	@Test(retryAnalyzer = Retry.class, description = "SEO module test, test all links and images displayes in module and open two articles from module")
	public static void seoModule() throws Exception {
		getDriver().get(dataReader.getBaseUrl() + "/home/index.html");
		Thread.sleep(2000);
		try {
			asserEquals(dataReader.getBaseUrl() + "/home/index.html", getDriver().getCurrentUrl());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		config.CmpConsent.gdprConsent();
		info(getDriver(), getDriver().getCurrentUrl());
		if (getDriver().findElement(By.xpath("//*[@data-track-module='seo-articles^article-list-module']"))
				.isDisplayed()) {
			System.out.println("SEO module is present on homepage");
			pass(getDriver(), "SEO module is present on homepage");
			try {
				Assert.assertTrue(getDriver().findElement(By.cssSelector(".heading-3-j4x")).getText()
						.contains("IN OTHER NEWS..."));
				System.out.println("SEO heading is displayed");
				pass(getDriver(), "SEO heading is displayed");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			List<WebElement> allSeoArticles = getDriver().findElements(By.cssSelector(".article-1HIOK>a"));
			int totalSeoArticles = allSeoArticles.size();
			System.out.println("Total articles in SEO module are " + totalSeoArticles);
			info(getDriver(), "Total articles in SEO module are " + totalSeoArticles);
			for (int i = 0; i < totalSeoArticles; i++) {
				WebElement seoArticle = allSeoArticles.get(i);
				if (seoArticle.isDisplayed() && seoArticle.isEnabled()) {
					System.out.println("SEO article " + (i + 1) + " displayed");
					pass(getDriver(), "SEO article " + (i + 1) + " displayed");
				} else {
					System.out.println("SEO article " + (i + 1) + " ***FAILED***");
					fail(getDriver(), "SEO article " + (i + 1) + " ***FAILED***");
				}

			}
			for (int a = 0; a < 2; a++) {
				WebElement seoArticle = allSeoArticles.get(a);
				String seoArticleUrlExpected = seoArticle.getAttribute("href");
				String parent = getDriver().getWindowHandle();
				WaitObj.wait(getDriver(), seoArticle);
				if (caps.getPlatform().toString().toLowerCase().contains("mac")) {
					String newTab = Keys.chord(Keys.COMMAND, Keys.RETURN);
					seoArticle.sendKeys(newTab);
					Thread.sleep(2000);
				} else {
					String newTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
					seoArticle.sendKeys(newTab);
					Thread.sleep(2000);
				}

				ArrayList<String> child = new ArrayList<String>(getDriver().getWindowHandles());
				if (!child.isEmpty()) {
					getDriver().switchTo().window(child.get(1));
				}
				try {
					// getDriver().switchTo().wait();
					assertEquals(getDriver().getCurrentUrl(), seoArticleUrlExpected);
					System.out.println("SEO Article " + (a + 1) + " opened");
					System.out.println(getDriver().getCurrentUrl());
					pass(getDriver(), "SEO Article " + (a + 1) + " Passed");
					info(getDriver(), getDriver().getCurrentUrl());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				if (child.size() > 1) {
					getDriver().close();
				}
				getDriver().switchTo().window(parent);
				Thread.sleep(3000);
			}

			// Images
			List<WebElement> allSeoArticleImages = getDriver().findElements(By.cssSelector(".article-1HIOK>a>img"));
			int totalSeoArticleImages = allSeoArticleImages.size();
			if (totalSeoArticleImages == totalSeoArticles) {
				System.out.println("Total images are present for all SEO articles");
				pass(getDriver(), "Total images are present for all SEO articles");
			}

			for (int img = 0; img < totalSeoArticleImages; img++) {
				WebElement seoArticleImage = allSeoArticleImages.get(img);
				if (seoArticleImage.isDisplayed()) {
					System.out.println("Image is displayed for " + (img + 1));
					pass(getDriver(), "Image is displayed for " + (img + 1));
				}

			}

		} else {
			System.out.println("SEO module is ***NOT*** present on homepage");
			fail(getDriver(), "SEO module is ***NOT*** present on homepage");
		}
	}

	private static void asserEquals(String string, String currentUrl) {
		// TODO Auto-generated method stub

	}

}
