package cljCheck;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import browsers.DataFileReader;
import config.Retry;
import config.SocialShareObj;
import config.WaitObj;

/**
 * @author jigneshkumarpatel
 *
 */

public class RelatedArticle extends browsers.BeforeAfter {
	private static DataFileReader dataReader = new DataFileReader();
	static SocialShareObj page = new SocialShareObj(getDriver());

	@Test(retryAnalyzer = Retry.class)
	public static void RelatedArticles() throws Exception {
		System.out.println("-----------------------------");
		System.out.println("***Related Articles***");
		System.out.println("-----------------------------");

		// page.firstarticle.click();
		getDriver().get(dataReader.getArticleUrl());
		Thread.sleep(2000);
		String articleurl = getDriver().getCurrentUrl();
		System.out.println(articleurl);
		info(getDriver(), articleurl);
		System.out.println("---___---___---");
		related(getDriver(), articleurl);

		System.out.println(line);

	}

	public static void related(WebDriver driver, String articleurl) throws Exception {
		driver.manage().window().maximize();
		WebElement relatedmodule = driver.findElement(By.cssSelector(".rotator-panels.link-bogr1.linkro-ccox"));
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", relatedmodule);
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
		Thread.sleep(2000);

		// Share this Article text on top of FB icon
		WebElement fbsharetext_onrelated = driver.findElement(By.cssSelector(".fb>h2"));
		try {
			if (fbsharetext_onrelated.isDisplayed()) {
				System.out.println(fbsharetext_onrelated.getText());
				pass(driver, "Share this article is present");
			} else {
				System.out.println("Share this Article is not present");
				warning(driver, "Share this Article is not present");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			error(driver, e1.getMessage());
		}

		// FB icon
		WebElement fbicon_onrelated = driver
				.findElement(By.xpath("//*[@data-track-module='am-related_carousel^related_carousel']/div[2]/div"));
		try {
			if (fbicon_onrelated.isDisplayed() && fbicon_onrelated.isEnabled()) {
				System.out.println("Facebook icon on Related is present");
				pass(driver, "Facebook icon on Related is present");

			} else {
				System.out.println("Facebook icon on Related is NOT present");
				warning(driver, "Facebook icon on Related is NOT present");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			error(driver, e1.getMessage());
		}

		// Related Articles in Module
		// List<WebElement> relatedarticleLinks =
		// driver.findElements(By.cssSelector(".rotator-panels.link-bogr1.linkro-ccox>li>a"));
		List<WebElement> relatedarticleLinks = driver
				.findElements(By.xpath("//*[@class='rotator-panels link-bogr1 linkro-ccox']/li/a"));
		int l = relatedarticleLinks.size();
		System.out.println("Total Related articles in module are " + l);

		for (int i = 1; i <= l; i++) {
			String expected = driver
					.findElement(By.xpath("//*[@class='rotator-panels link-bogr1 linkro-ccox']/li/a[" + i + "]"))
					.getAttribute("href");
			// System.out.println("Expected url: "+expected);
			WebElement relatedlink = driver
					.findElement(By.xpath("//*[@class='rotator-panels link-bogr1 linkro-ccox']/li/a[" + i + "]"));
			String parent = driver.getWindowHandle();
			Actions action = new Actions(driver);
			try {
				try {
					action.moveToElement(relatedlink).build().perform();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				WaitObj.wait(driver, relatedlink);
				if (caps.getBrowserName().toLowerCase().contains("safari")) {
					action.moveToElement(relatedlink).build().perform();
					// je.executeScript("window.open(‘"+relatedlink+ "’,’_blank’);");
					String NewTab = Keys.chord(Keys.COMMAND, Keys.RETURN);
					relatedlink.sendKeys(NewTab);
					// action.keyDown(Keys.COMMAND).click(relatedlink).keyUp(Keys.COMMAND).build().perform();
					Thread.sleep(2000);
				} else {
					String NewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
					WaitObj.wait(driver, relatedlink);
					relatedlink.sendKeys(NewTab);
					// action.keyDown(Keys.CONTROL).click(relatedlink).keyUp(Keys.CONTROL).build().perform();
					Thread.sleep(2000);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error(driver, e.getMessage());
			}
			// WebDriverWait wait = new WebDriverWait(driver,20);
			// wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			// driver.findElement(By.xpath("//*[@class='rotator-panels link-bogr1
			// linkro-ccox']/li/a["+i+"]")).click();
			ArrayList<String> tmchild = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tmchild.get(1));
			Thread.sleep(2000);
			if (caps.getBrowserName().toString().toLowerCase().contains("edge")) {
				Thread.sleep(2000);
			}

			String actual = driver.getCurrentUrl();
			try {
				if (actual.equals(expected)) {
					System.out.println(actual);
					System.out.println("====>>> pass for related article " + i);
					pass(driver, "pass for related article " + i);
				} else {
					System.out.println("Fail for related article " + i);
					fail(driver, "Fail for related article " + i);
					continue;
				}

				// driver.navigate().back();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error(driver, e.getMessage());
			} finally {
				if (tmchild.size() > 1) {
					driver.close();
				}

				driver.switchTo().window(parent);
				Thread.sleep(3000);
			}

		}

	}

}
