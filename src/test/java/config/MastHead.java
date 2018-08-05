/**
 * 
 */
package config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * @author jigneshkumarpatel
 *
 *Mast Head on page
 *
 */
public class MastHead extends browsers.BeforeAfter{
	public static void mastHead(WebDriver driver) {
		System.out.println("====Mast Head====");
		try {
			Assert.assertTrue(driver.findElement(By.cssSelector("div.masthead ")).isDisplayed());
			System.out.println("Mast head is displayed");
			pass(driver,"Mast head is displayed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(driver.findElement(By.cssSelector("div.masthead>a>img")).getAttribute("alt").contains("MailOnline")) {
			System.out.println("Mast Head contains MailOnline");
			System.out.println("MailOnline");
			info(driver,"Mast Head contains MailOnline");
			pass(driver,"Mast Head contains Channel name");
		}else {
			System.out.println("Mast Head ***NOT*** contains MailOnline");
			fail(driver,"Mast Head ***NOT*** contains MailOnline");
		}
			
		
	}

}
