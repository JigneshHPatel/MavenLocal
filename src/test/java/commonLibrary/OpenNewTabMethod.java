/**
 * 
 */
package commonLibrary;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.NewTabValue;

/**
 * @author jigneshkumarpatel
 * 
 *         Open link in newtab by keys.chord based on OS. Assert actual url
 *         contain expected url. printMsg = "pass for"+printMsg
 *
 */
public class OpenNewTabMethod extends browsers.BeforeAfter {

	public enum newTabMethod {
		links, socialIcons
	}

	public static void NewTabMethod(WebDriver driver, WebElement ClickElement, String ExpectedUrl, String printMsg,
			newTabMethod openNewTabType) throws Exception {

		String parent = driver.getWindowHandle();

		switch (openNewTabType) {

		case links:
			try {
				WaitMethods.WaitUntilElementClickable(driver, ClickElement);
				System.out.println(NewTabValue.NewTab());
				ClickElement.sendKeys(NewTabValue.NewTab());
				Thread.sleep(2000);
			} catch (Exception e1) {
				System.out.println("NewTab is NOT opened");
				e1.printStackTrace();
				error(driver, e1.getMessage());
			}

			ArrayList<String> child = new ArrayList<String>(driver.getWindowHandles());

			if (!child.isEmpty()) {
				driver.switchTo().window(child.get(1));
			} else {
				System.out.println("New window is not opened");
			}
			Thread.sleep(2000);
			String actualUrl = driver.getCurrentUrl();
			try {
				if (actualUrl.contains(ExpectedUrl)) {
					System.out.println("Pass for " + printMsg);
					pass(driver, "Pass for " + printMsg);
				} else {
					System.out.println("Fail for " + printMsg);
					fail(driver, "Fail for " + printMsg);
				}
			} catch (Exception e) {
				String errorMsg = e.getMessage();
				System.out.println("***{");
				System.out.println(errorMsg);
				System.out.println("***}");
				error(driver, errorMsg);
			} finally {
				ArrayList<String> finalChild = new ArrayList<String>(driver.getWindowHandles());
				if (finalChild.size() > 1) {
					driver.close();
				}
				driver.switchTo().window(parent);
				Thread.sleep(2000);
			}

			break;

		case socialIcons:

			Thread.sleep(2000);
			WaitMethods.WaitUntilElementClickable(driver, ClickElement);
			try {
				ClickElement.click();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			Thread.sleep(2000);

			ArrayList<String> Child = new ArrayList<String>(driver.getWindowHandles());

			if (Child.size() > 1) {
				driver.switchTo().window(Child.get(1));
			} else {
				System.out.println(ClickElement + " ***Not*** clicked");
				fail(driver, "Fail for " + ClickElement + " Element not clicked");

			}

			break;

		default:
			break;
		}

	}

}
