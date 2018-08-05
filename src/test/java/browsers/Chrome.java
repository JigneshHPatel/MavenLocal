/**
 * 
 */
package browsers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * @author jigneshkumarpatel
 *
 */
public class Chrome {

	public static WebDriver driver;

	@BeforeTest
	public void setup() throws InterruptedException {

		driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://www.dailymail.co.uk/home/");
		Thread.sleep(2000);
		try {
			if (driver.findElement(By.xpath("//button[text()='Got it']")).isDisplayed()) {
				driver.findElement(By.xpath("//button[text()='Got it']")).click();
				Thread.sleep(2000);
			}
			if (driver.findElement(By.cssSelector("button.mol-ads-cmp--close")).isDisplayed()) {
				driver.findElement(By.cssSelector("button.mol-ads-cmp--close")).click();
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@AfterTest
	public void after() {
		driver.quit();
	}

}
