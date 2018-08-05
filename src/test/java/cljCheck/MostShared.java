/**
 * 
 */
package cljCheck;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import browsers.DataFileReader;
import config.MostSharedObj;
import config.Retry;

/**
 * @author jigneshkumar.patel This class is for RAW work
 */

// public class MostShared extends browsers.BrowserStack {
public class MostShared extends browsers.BeforeAfter {
	private static DataFileReader dataReader = new DataFileReader();

	@Test(retryAnalyzer = Retry.class)
	public void MostShared_UK_HomePage() throws Exception {
		// UK Home Page
		System.out.println("*** UK home start ***");
		// info(getDriver(), "*** UK home start ***");
		info(getDriver(), getDriver().getCurrentUrl());
		// getDriver().get(Url.baseurl+"/home/index.html");
		mostshared();
		System.out.println("*** UK home finished ***");
		System.out.println("~-*-~_~-*-~_~-*-~_~-*-~_~-*-~_~-*");
	}

	@Test(retryAnalyzer = Retry.class)
	public void MostShared_US_HomePage() throws Exception {
		// US Home Page
		System.out.println("*** US home start ***");
		// info(getDriver(), "*** US home start ***");
		getDriver().get(dataReader.getBaseUrl() + "/ushome/index.html");
		// getDriver().findElement(By.linkText("U.S.")).click();
		Thread.sleep(3000);
		try {
			assertEquals(getDriver().getCurrentUrl(), dataReader.getBaseUrl() + "/ushome/index.html");
			System.out.println(getDriver().getCurrentUrl());
			info(getDriver(), getDriver().getCurrentUrl());
		} catch (Exception e) {
			System.out.println(e);
			error(getDriver(), e.getMessage());
		}
		config.CmpConsent.gdprConsent();
		mostshared();
		System.out.println("*** US home finished ***");
		System.out.println("~-*-~_~-*-~_~-*-~_~-*-~_~-*-~_~-*");

	}

	public static void mostshared() throws Exception {

		MostSharedObj page = new MostSharedObj(getDriver());
		Actions action = new Actions(getDriver());
		try {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);",
					page.mostsharedmodule);
		} catch (Exception e1) {
			e1.printStackTrace();
			info(getDriver(), e1.getMessage());
		}
		Thread.sleep(2000);
		if (page.mostsharedmodule.isDisplayed()) {
			try {
				assertEquals(page.mostsharedtitle.getText().toUpperCase(), "MOST SHARED RIGHT NOW");
				pass(getDriver(), "MOST SHARED RIGHT NOW Title is Present");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error(getDriver(), e.getMessage());
			}
			System.out.println(page.mostsharedtitle.getText() + " Title is Present");

			List<WebElement> msrl = page.linksinmsr;
			System.out.println("Total Articles in Most Shared are " + msrl.size());
			info(getDriver(), "Total Articles in Most Shared are " + msrl.size());
			for (int i = 0; i < 2; i++) {
				String article = page.linksinmsr.get(i).getAttribute("href");
				String parent = getDriver().getWindowHandle();
				try {
					WebElement element = page.linksinmsr.get(i);

					try {
						action.moveToElement(element).build().perform();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Thread.sleep(2000);
					if (caps.getPlatform().toString().toLowerCase().contains("mac")) {
						String NewTab = Keys.chord(Keys.COMMAND, Keys.RETURN);
						element.sendKeys(NewTab);
						Thread.sleep(2000);
					} else {
						String NewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

						element.sendKeys(NewTab);
						Thread.sleep(2000);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					error(getDriver(), e.getMessage());
				}
				ArrayList<String> child = new ArrayList<String>(getDriver().getWindowHandles());
				getDriver().switchTo().window(child.get(1));
				Thread.sleep(2000);

				String articleurl = getDriver().getCurrentUrl();

				if (articleurl.contains(article)) {
					System.out.println(i + 1 + " article pass");
					System.out.println(articleurl);
					pass(getDriver(), "Pass for Shared article " + i);
					info(getDriver(), articleurl);
					System.out.println("~˜~˜~˜~˜~˜~˜~˜~˜~˜~˜~˜~˜~˜~˜~");
				} else {
					System.out.println(i + 1 + " article ***FAIL***");
					fail(getDriver(), i + 1 + " article ***FAIL***");
				}

				if (child.size() > 1) {
					getDriver().close();
				}

				getDriver().switchTo().window(parent);
				Thread.sleep(3000);
			}

		} else {
			System.out.println("Most shared module is NOT present");
			fail(getDriver(), "Most Shared Module is ***NOT*** Present ");
		}

	}
}
