/**
 * 
 */
package clj.Frontend;

import static org.testng.Assert.assertEquals;
import java.util.List;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import commonLibrary.FFFMethods;
import util.Urls;

/**
 * @author jigneshkumar.patel and andrew.ewart
 *
 */
// public class FFF extends browsers.BeforeCH{
public class FFF extends browsers.BeforeAfter {

	@Test()
	public void FFFHomepage() throws Exception {
		System.out.println("****FFFHomepage****");
		getDriver().manage().window().fullscreen();
		Thread.sleep(3000);
		FFFMethods.fffCarosel(getDriver());
	}

	@Test(enabled = false)
	public void FFFChannel() throws Exception {
		System.out.println("****FFFChannel****");
		getDriver().manage().window().fullscreen();
		getDriver().findElement(By.linkText("Fashion Finder")).click();
		Thread.sleep(3000);
		String h1 = getDriver().findElement(By.cssSelector(".h1-page-last-updated>h1")).getText();
		assertEquals("Fashion Finder", h1);
		System.out.println("**** " + getDriver().findElement(By.xpath("//*[h1]")).getText() + " ****");
		FFFMethods.fffCarosel(getDriver());
		System.out.println(line);

	}

	@Test
	public void FFF_Article_Wide() throws Exception {
		System.out.println("****FFF_Article_Wide****");
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		getDriver().get(Urls.baseurl + "/tvshowbiz/article-5401363/Mia-Wasikowska-turns-heads-plunging-black-gown.html");
		Thread.sleep(2000);
		je.executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.cssSelector("#fff-inline")));
		if (getDriver().findElement(By.cssSelector("#fff-inline")).isDisplayed()) {
			System.out.println("FFF inline is present on wide article");
			if (getDriver().findElement(By.cssSelector("#fff-popup-crop>img")).isDisplayed()) {
				System.out.println("Big Preview Image is present");
			}
			if (getDriver().findElement(By.cssSelector("#fff-inline-image")).isDisplayed()
					&& getDriver().findElement(By.cssSelector("#fff-inline-image")).isEnabled()) {
				System.out.println("Small Preview Image is present AND clickable");
			}
			if (getDriver().findElement(By.cssSelector(".fff-social-links")).isDisplayed()) {
				List<WebElement> socials = getDriver().findElements(By.cssSelector(".fff-social-links>ul>li"));
				for (WebElement socialEle : socials) {
					if (socialEle.isEnabled()) {
						System.out.println("Pass for " + socialEle.getAttribute("class"));
					}
				}
			} else {
				System.out.println("Social icons are not displayed");
			}
			if (getDriver().findElement(By.cssSelector(".fff-buybtn.fff-main-product")).isDisplayed()
					&& getDriver().findElement(By.cssSelector(".fff-buybtn.fff-main-product")).isEnabled()) {
				System.out.println("Buy button on main product is displayed and clikable");
			}

			if (getDriver().findElement(By.cssSelector(".fff-related-product-container>img")).isDisplayed()) {
				List<WebElement> moreProduct = getDriver()
						.findElements(By.cssSelector(".fff-related-product-container>img"));
				System.out.println("Total Products are " + moreProduct.size());
				for (WebElement moreProdImg : moreProduct) {
					if (moreProdImg.isDisplayed() && moreProdImg.isEnabled()) {
						System.out.println("More Products are displayed");
					}
				}
			}

		} else {
			System.out.println("FFF Inline is ***NOT*** present on wide article");
		}
	}

	@Test
	public void FFF_Article_XWide() throws Exception {
		System.out.println("****FFF_Article_XWide****");
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		getDriver().get(Urls.baseurl + "/news/article-5627047/Duchess-Cambridge-admitted-Lindo-Wing.html");
		Thread.sleep(2000);
		je.executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.cssSelector("#fff-inline")));
		if (getDriver().findElement(By.cssSelector("#fff-inline")).isDisplayed()) {
			System.out.println("FFF inline is present on xwide article");
			if (getDriver().findElement(By.cssSelector("#fff-popup-crop>img")).isDisplayed()) {
				System.out.println("Big Preview Image is present");
			}
			if (getDriver().findElement(By.cssSelector("#fff-inline-image")).isDisplayed()
					&& getDriver().findElement(By.cssSelector("#fff-inline-image")).isEnabled()) {
				System.out.println("Small Preview Image is present AND clickable");
			}
			if (getDriver().findElement(By.cssSelector(".fff-social-links")).isDisplayed()) {
				List<WebElement> socials = getDriver().findElements(By.cssSelector(".fff-social-links>ul>li"));
				for (WebElement socialEle : socials) {
					if (socialEle.isEnabled()) {
						System.out.println("Pass for " + socialEle.getAttribute("class"));
					}
				}
			} else {
				System.out.println("Social icons are not displayed");
			}
			if (getDriver().findElement(By.cssSelector(".fff-buybtn.fff-main-product")).isDisplayed()
					&& getDriver().findElement(By.cssSelector(".fff-buybtn.fff-main-product")).isEnabled()) {
				System.out.println("Buy button on main product is displayed and clikable");
			}

			if (getDriver().findElement(By.cssSelector(".fff-related-product-container>img")).isDisplayed()) {
				List<WebElement> moreProduct = getDriver()
						.findElements(By.cssSelector(".fff-related-product-container>img"));
				System.out.println("Total Products are " + moreProduct.size());
				for (WebElement moreProdImg : moreProduct) {
					if (moreProdImg.isDisplayed() && moreProdImg.isEnabled()) {
						System.out.println("More Products are displayed");
					}
				}
			}

		} else {
			System.out.println("FFF Inline is ***NOT*** present on xwide article");
		}
	}
}