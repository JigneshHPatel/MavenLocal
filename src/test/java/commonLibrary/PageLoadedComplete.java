/**
 * 
 */
package commonLibrary;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author jigneshkumarpatel
 *
 *         This class test if page is loaded by javaScript.
 *
 */
public class PageLoadedComplete extends browsers.BeforeAfter {
	public static void PageLoaded(WebDriver driver, String channel) {
		try {
			if (((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")) {
				System.out.println(channel + " page loaded COMPLETELY");
				String passMsg = channel + " page loaded COMPLETELY";
				pass(driver, passMsg);
			} else {
				System.out.println(channel + " page NOT loaded");
				info(driver, channel + "page ***NOT*** loaded");
			}
		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
			String errorMsg = e.getMessage();
			error(driver, errorMsg);
		}
	}

}
