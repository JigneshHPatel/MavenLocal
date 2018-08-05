/**
 * 
 */
package commonLibrary;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author jigneshkumar.patel fff carousel on channel page
 */
public class FFFMethods {
	static WebDriver driver;
	public static String line = "<<=====~~~~~=====~~~~~=====~~~~~=====~~~~~=====>>";

	public static void fffCarosel(WebDriver driver) throws Exception {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.cssSelector(".fff-hub")));
		Thread.sleep(2000);

		if (driver.findElement(By.cssSelector(".fff-hub")).isDisplayed()) {
			System.out.println("FFF Module is displayed");
		} else {
			System.out.println("FFF Module is NOT present");
		}
		String fffHeading = driver.findElement(By.cssSelector(".fff-hottest-header")).getText();
		if (fffHeading.equals("Today's hottestfashion finds")) {
			System.out.println("FFF carousel heading is present");
			System.out.println(fffHeading);
		}
		if ("See more".equalsIgnoreCase(driver.findElement(By.cssSelector(".fff-gallery-ui-wrap")).getText())) {
			System.out.println("See more text is displayed");
		} else {
			System.out.println("See more text is |NOT displayed");
		}
		try {
			WebElement rightArrow = driver.findElement(By.cssSelector(".fff-hub-arrow.fff-hub-right-arrow"));
			if (rightArrow.isEnabled()) {
				WaitMethods.WaitUntilElementClickable(driver, rightArrow);
				rightArrow.click();
				Thread.sleep(2000);
				System.out.println("Right Arrow of see more is  clickable");
			} else {
				System.out.println("Right Arrow of see more is NOT clickable");
			}

			WebElement leftArrow = driver.findElement(By.cssSelector(".fff-hub-arrow.fff-hub-left-arrow"));
			if (leftArrow.isEnabled()) {
				leftArrow.click();
				Thread.sleep(2000);
				System.out.println("Left Arrow of see more is  clickable");
			} else {
				System.out.println("Left Arrow of see more is NOT clickable");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<WebElement> fffcarousel = driver.findElements(By.cssSelector(".fff-hottest-list>ul>li"));
		List<WebElement> fffcarouselTitle = driver.findElements(By.cssSelector(".fff-hottest-list>ul>li>div>p"));
		int fff = fffcarousel.size();
		System.out.println("Total article in carousel are " + fff);
		if (fff > 0) {
			System.out.println("FFF carousel articles are " + fff);
		}
		System.out.println(line);

		for (int i = 0; i < 3; i++) {
			try {
				WebElement fffc = fffcarousel.get(i);
				WebElement ffft = fffcarouselTitle.get(i);
				String title = ffft.getText();
				// System.out.println(title);
				fffc.click();

				Thread.sleep(2000);
				String newTitle = driver.switchTo().activeElement().findElement(By.cssSelector(".fff-product-headline"))
						.getText();
				if (newTitle.equalsIgnoreCase(title)) {
					System.out.println(i + 1 + " pass for fff article " + newTitle);
				} else {
					System.out.println(i + 1 + " ***FAIL*** for fff article " + newTitle);
					continue;
				}
				if (driver.switchTo().activeElement().findElement(By.cssSelector(".fff-buybtn.fff-main-product"))
						.isDisplayed()
						&& driver.switchTo().activeElement().findElement(By.cssSelector(".fff-buybtn.fff-main-product"))
								.isEnabled()) {
					System.out.println(i + 1 + " Buy Now displayed and clickable in popup");
				}
				if (driver.switchTo().activeElement().findElement(By.cssSelector(".fff-orig-product-image"))
						.isDisplayed()
						&& driver.switchTo().activeElement().findElement(By.cssSelector("#fff-popup-crop>img"))
								.isDisplayed()) {
					System.out.println(i + 1 + " Both big and small Images are displayed in popup");
				}
				if (driver.switchTo().activeElement().findElement(By.cssSelector(".fff-partners-links"))
						.isDisplayed()) {
					System.out.println(i + 1 + " Selected Partners are displayed in popup");
				}
				if (driver.switchTo().activeElement().findElement(By.cssSelector(".fff-carousel.rotator"))
						.isDisplayed()) {
					System.out.println(i + 1 + " FFF carousel is displayed in popup");
				}

				driver.switchTo().activeElement().findElement(By.cssSelector(".tclose")).click();

				driver.switchTo().defaultContent();

				Thread.sleep(2000);

				System.out.println("<====^^**************^^====>");

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("***FAIL for " + i + 1);
			}
		}
	}

}