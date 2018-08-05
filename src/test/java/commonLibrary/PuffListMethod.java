/**
 * 
 */
package commonLibrary;

import static org.testng.Assert.assertEquals;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import commonLibrary.OpenNewTabMethod.newTabMethod;

/**
 * @author jigneshkumarpatel generic method for puff list assert if puff and
 *         heading is displayed count total article in puff list click top 2
 *         articles in new tab and assert url. PuffModule = puff module element,
 *         ActualPuffheading = puff heading text find by element, puffArticles =
 *         list of all puff article.
 *
 */

public class PuffListMethod extends browsers.BeforeAfter {
	public static void puffListMethod(WebDriver driver, WebElement PuffModule, String ActualPuffHeading,
			String ExpectedPuffHeading, List<WebElement> puffarticles) {

		try {
			if (PuffModule.isDisplayed()) {
				try {
					assertEquals(ActualPuffHeading, ExpectedPuffHeading);
					System.out.println(ActualPuffHeading + " is Present");
					String passMsg = ActualPuffHeading + " is Present";
					pass(driver, passMsg);
					System.out.println("Total " + ActualPuffHeading + " articles are " + puffarticles.size());
					String infoMsg = "Total " + ActualPuffHeading + " articles are " + puffarticles.size();
					info(driver, infoMsg);
				} catch (Exception e) {
					System.out.println("Puff Heading failed");
					System.out.println(e.getMessage());
					String errorMsg = e.getMessage();
					error(driver, errorMsg);
					error(driver, e.getMessage());
				}
				for (int i = 0; i < puffarticles.size(); i++) {
					WebElement puffArticleLink = puffarticles.get(i);
					if (puffArticleLink.isDisplayed() && puffArticleLink.isEnabled()) {
						if (i < 2) {
							String expected = puffArticleLink.getAttribute("href");
							String printMsg = "Article " + (1 + i);

							OpenNewTabMethod.NewTabMethod(driver, puffArticleLink, expected, printMsg,
									newTabMethod.links);
							Thread.sleep(2000);
							System.out.println("------------");
						}

					} else {
						System.out.println((1 + i) + " Article is ***NOT*** displyed and clickable");
						fail(driver, " Article is ***NOT*** displyed and clickable");
					}
				}
			} else {
				System.out.println(ExpectedPuffHeading + " is NOT present");
				fail(driver, ExpectedPuffHeading + " is NOT present");
			}
		} catch (Exception e) {
			System.out.println("puff module is not present");
			System.out.println(e.getMessage());
			error(driver, e.getMessage());
		}
	}
}
