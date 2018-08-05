/**
 * 
 */
package commonLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author jigneshkumarpatel
 *
 */
public class CmpConsent extends browsers.BeforeAfter {

	public static void gdprConsent(WebDriver driver) throws InterruptedException {

		try {
			driver.findElement(By.xpath("//button[text()='Got it']")).click();
			Thread.sleep(2000);
			System.out.println("CMP consent is given");
		} catch (Exception e) {
			System.out.println("CMP banner is ***NOT*** present");
		}
		try {
			driver.findElement(By.cssSelector("button.mol-ads-cmp--close")).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Confirmation message is ***NOT*** present");
		}
	}
}