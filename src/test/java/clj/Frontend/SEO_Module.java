/**
 * 
 */
package clj.Frontend;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import commonLibrary.OpenNewTabMethod;
import commonLibrary.OpenNewTabMethod.newTabMethod;
import pageObjects.SEOModuleObj;
import util.Retry;

/**
 * @author jigneshkumar.patel
 *
 *         This is SEO module test on channel page Test if all article links and
 *         images are displayed open two article from module
 *
 *
 */
public class SEO_Module extends browsers.BeforeAfter {

	@Test(retryAnalyzer = Retry.class, description = "SEO module test, test all links and images displayes in module and open two articles from module")
	public static void seoModule() throws Exception {

		SEOModuleObj seoModule = new SEOModuleObj(getDriver());

		if (seoModule.SEOModule.isDisplayed()) {
			System.out.println("SEO module is present on homepage");
			pass(getDriver(), "SEO module is present on homepage");
			try {
				seoModule.AssertSEOHeading();
				System.out.println("SEO heading is displayed");
				pass(getDriver(), "SEO heading is displayed");
			} catch (Exception e) {
				System.out.println("SEO heading is ***NOT*** displayed");
				System.out.println(e.getMessage());
			}

			System.out.println("Total articles in SEO module are " + seoModule.TotalSEOArticles());
			info(getDriver(), "Total articles in SEO module are " + seoModule.TotalSEOArticles());
			for (int i = 0; i < seoModule.TotalSEOArticles(); i++) {
				WebElement seoArticle = seoModule.SEOArticles.get(i);
				if (seoArticle.isDisplayed() && seoArticle.isEnabled()) {
					System.out.println("SEO article " + (i + 1) + " displayed");
					pass(getDriver(), "SEO article " + (i + 1) + " displayed");
				} else {
					System.out.println("SEO article " + (i + 1) + " ***FAILED***");
					fail(getDriver(), "SEO article " + (i + 1) + " ***FAILED***");
				}

			}
			for (int a = 0; a < 2; a++) {
				WebElement seoArticle = seoModule.SEOArticles.get(a);
				String seoArticleUrlExpected = seoArticle.getAttribute("href");
				String printMsg = " SEO Article " + (a + 1);
				OpenNewTabMethod.NewTabMethod(getDriver(), seoArticle, seoArticleUrlExpected, printMsg, newTabMethod.links);
			}

			// Images
			if (seoModule.TotalSEOImages() == seoModule.TotalSEOArticles()) {
				System.out.println("Total images are present for all SEO articles");
				pass(getDriver(), "Total images are present for all SEO articles");
			}

			for (int img = 0; img < seoModule.TotalSEOImages(); img++) {
				WebElement seoArticleImage = seoModule.SEOArticleImages.get(img);
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

}
