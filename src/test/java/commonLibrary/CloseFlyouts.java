/**
 * 
 */
package commonLibrary;

import org.openqa.selenium.WebDriver;

import pageObjects.ArticlePageObj;

/**
 * @author jigneshkumarpatel Close fly-outs if present e.g video, jw player
 */
public class CloseFlyouts extends browsers.BeforeAfter {

	public static void closeVideoPlayer(WebDriver driver) {
		ArticlePageObj articleObj = new ArticlePageObj(driver);
		try {
			WaitMethods.WaitUntilElementVisible(driver, articleObj.videoPlayerCloseButton);
			articleObj.videoPlayerCloseButton.click();
		} catch (Exception e) {
		}

	}

	public static void closeJwPlayer(WebDriver driver) {
		ArticlePageObj articleObj = new ArticlePageObj(driver);
		try {
			WaitMethods.WaitUntilElementVisible(driver, articleObj.jwPlayerCloseButton);
			articleObj.jwPlayerCloseButton.click();
		} catch (Exception e) {
		}
	}
	
	public static void closeNewsletter(WebDriver driver) {
		ArticlePageObj articleObj = new ArticlePageObj(driver);
		try {
			WaitMethods.WaitUntilElementVisible(driver, articleObj.newsletterCloseButton);
			articleObj.jwPlayerCloseButton.click();
		} catch (Exception e) {
		}
	}
}
