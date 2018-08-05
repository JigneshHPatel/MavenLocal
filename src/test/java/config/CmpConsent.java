/**
 * 
 */
package config;

import org.openqa.selenium.By;

/**
 * @author jigneshkumarpatel
 * 
 *         give consent to cmp banner and close confirmation message
 *
 */
public class CmpConsent extends browsers.BeforeAfter {
	public static void gdprConsent() throws InterruptedException {

		try {
			if (getDriver().findElement(By.xpath("//button[text()='Got it']")).isDisplayed()) {
				getDriver().findElement(By.xpath("//button[text()='Got it']")).click();
				Thread.sleep(2000);
				System.out.println("CMP consent is given");
			}
		} catch (Exception e) {
			System.out.println("CMP banner is ***NOT*** present");
		}
		try {
			if (getDriver().findElement(By.cssSelector("button.mol-ads-cmp--close")).isDisplayed()) {
				getDriver().findElement(By.cssSelector("button.mol-ads-cmp--close")).click();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("Confirmation message is ***NOT*** present");
		}

	}

}
