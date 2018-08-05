/**
 * 
 */
package commonLibrary;

import org.openqa.selenium.WebDriver;

import pageObjects.AllPagesObj;

/**
 * @author jigneshkumarpatel Assert if masthead is displayed and img contains
 *         'MailOnline'. Elements are in AllPagesObj.
 */
public class MastHead extends browsers.BeforeAfter {
	public static void mastHead(WebDriver driver) {
		AllPagesObj allObj = new AllPagesObj(driver);
		try {
			allObj.MastHeadIsDisplayed();
			System.out.println("Mast head is displayed");
			pass(driver, "Mast head is displayed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (allObj.MastHeadImgTextIsPresent()) {
			System.out.println("Mast Head contains MailOnline");
			pass(driver, "Mast Head contains MailOnline");
		} else {
			System.out.println("Mast Head ***NOT*** contains MailOnline");
			fail(driver, "Mast Head ***NOT*** contains MailOnline");
		}

	}

}