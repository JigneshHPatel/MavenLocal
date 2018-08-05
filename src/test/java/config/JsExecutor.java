/**
 * 
 */
package config;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author jigneshkumarpatel
 * 
 * javaScriptExecutor methods 
 * e.g. scrollIntoView, click
 *
 */
public class JsExecutor extends browsers.BeforeAfter{
	public static void scrollIntoView(WebDriver driver, WebElement element) throws Exception {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);",element);
		Thread.sleep(2000);
	}
	
	public static void clickElement(WebDriver driver, WebElement element) throws Exception {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WaitObj.wait(driver, element);
		je.executeScript("arguments[0].click();",element);
		Thread.sleep(2000);
		
	}

}
