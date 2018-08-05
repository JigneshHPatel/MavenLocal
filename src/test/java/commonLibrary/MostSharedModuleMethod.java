/**
 * 
 */
package commonLibrary;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonLibrary.OpenNewTabMethod.newTabMethod;
import pageObjects.MostSharedObj;

/**
 * @author jigneshkumarpatel
 *
 *         Generic method for Most Shared Module on channel pages.
 *
 *         assert if module is present and open first two articles.
 *
 */
public class MostSharedModuleMethod extends browsers.BeforeAfter {

	public static void MostShared(WebDriver driver) throws Exception {

		MostSharedObj MostSharedPage = new MostSharedObj(driver);

		// Scroll page until most shared module is in view port
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					MostSharedPage.mostsharedmodule);
		} catch (Exception e) {

		}
		Thread.sleep(3000);

		// Assert if module is displayed and title
		if (MostSharedPage.isMostSharedModuleDisplayed()) {
			try {
				MostSharedPage.MostSharedTitlePresent();
				pass(driver, "MOST SHARED RIGHT NOW Title is Present");
				System.out.println(MostSharedPage.mostsharedtitle.getText() + " Title is Present");
			} catch (Exception e) {
				System.out.println("Title assertion Failed");
				System.out.println(e.getMessage());
				error(driver, "Title assertion Failed");
			}

			// List total articles in module
			System.out.println("Total Articles in Most Shared are " + MostSharedPage.linksinmsr.size());
			info(driver, "Total Articles in Most Shared are " + MostSharedPage.linksinmsr.size());

			// Open top two articles in new tab and assert url
			for (int i = 0; i < 2; i++) {
				WebElement MostSharedArticle = MostSharedPage.linksinmsr.get(i);
				String ExpectedArticleUrl = MostSharedPage.linksinmsr.get(i).getAttribute("href");

				commonLibrary.OpenNewTabMethod.NewTabMethod(driver, MostSharedArticle, ExpectedArticleUrl,
						" article " + (i + 1), newTabMethod.links);
			}

		} else {
			System.out.println("Most shared module is NOT present");
			fail(driver, "Most Shared Module is ***NOT*** Present ");
		}

	}

}
