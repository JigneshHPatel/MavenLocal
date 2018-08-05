/**
 * 
 */
package config;

import java.util.ArrayList;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

//import org.openqa.selenium.interactions.Actions;

/**
 * @author jigneshkumarpatel
 *
 */

public class NewTab extends browsers.BeforeAfter {

	// public static WebDriver driver;
	public static void newtab(WebDriver driver, WebElement element, String expected, String printMsg, int type)
			throws Exception {
		Actions action = new Actions(driver);
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String parent = driver.getWindowHandle();
		switch (type) {
		case 1:

			try {
				WaitObj.wait(driver, element);

				if (caps.getPlatform().toString().toLowerCase().contains("mac")) {
					System.out.println("mac method");
					action.moveToElement(element).keyDown(Keys.COMMAND).click().keyUp(Keys.COMMAND).build().perform();
					// String NewTab = Keys.chord(Keys.COMMAND,Keys.RETURN);
					// element.sendKeys(NewTab);
					// action.moveToElement(element).contextClick().sendKeys(Keys.ARROW_DOWN).click().build().perform();
					Thread.sleep(2000);
				} else {
					System.out.println("win method");
					String NewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

					element.sendKeys(NewTab);

					Thread.sleep(2000);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				error(driver, e1.getMessage());
			}

			ArrayList<String> child = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("Total Windows are " + child.size());
			if (!child.isEmpty()) {
				driver.switchTo().window(child.get(1));
			} else {
				System.out.println("New window is not opened");
			}
			if (caps.getBrowserName().toString().equalsIgnoreCase("Edge")) {
				Thread.sleep(2000);
			}
			Thread.sleep(2000);
			String actual = driver.getCurrentUrl();
			try {
				if (actual.contains(expected)) {
					System.out.println("Pass for " + printMsg);
					String passMsg = "Pass for " + printMsg;
					pass(driver, passMsg);
				} else {
					System.out.println("Fail for " + printMsg);
					String failMsg = "Fail for " + printMsg;
					fail(driver, failMsg);
				}
			} catch (Exception e) {
				String errorMsg = e.getMessage();
				System.out.println("***{");
				System.out.println(errorMsg);
				System.out.println("***}");
				error(driver, errorMsg);
			} finally {
				if (child.size() > 1) {
					driver.close();
				}
				driver.switchTo().window(parent);
				if (caps.getBrowserName().toString().toLowerCase().contains("edge")) {
					Thread.sleep(2000);
				}
				Thread.sleep(2000);
			}

			break;

		case 2:

			break;
		default:
			break;
		}

	}

}
