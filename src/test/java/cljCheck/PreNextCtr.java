/**
 * 
 */
package cljCheck;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import browsers.DataFileReader;
import config.Retry;

/**
 * @author jigneshkumarpatel
 *
 *
 *         Previous Next control on article page
 */
// public class PreNextCtr extends browsers.BeforeCH{
public class PreNextCtr extends browsers.BeforeAfter {
	private static DataFileReader dataReader = new DataFileReader();

	@Test(retryAnalyzer = Retry.class)
	public void PreNextControls() throws Exception {
		System.out.println("****PreNextControls****");
		// SocialShareObj page= new SocialShareObj(getDriver());
		getDriver().get(dataReader.getBaseUrl());
		// page.firstarticle.click();
		Thread.sleep(3000);
		String firstarticleurl = getDriver().getCurrentUrl();
		System.out.println(firstarticleurl);
		info(getDriver(), "Current Article is  " + firstarticleurl);
		JavascriptExecutor je = (JavascriptExecutor) getDriver();

		// Next Button
		System.out.println("~~~~~~~~~~~~~~~" + System.lineSeparator() + "Next Button" + System.lineSeparator()
				+ "~~~~~~~~~~~~~~~");
		WebElement element = getDriver().findElement(By.cssSelector(".nextBtn-3HPLH"));
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		je.executeScript("arguments[0].style.display='block';", element);
		try {
			je.executeScript("arguments[0].click();", element);
			// getDriver().findElement(By.cssSelector(".nextBtn-3HPLH")).click();
			Thread.sleep(3000);
			String nexturl = getDriver().getCurrentUrl();
			if (nexturl.equals(firstarticleurl)) {
				System.out.println("Test ***FAIL*** for Next button");
				fail(getDriver(), "Test ***FAIL*** for Next button");
				return;
			} else {
				System.out.println("Test pass for Next button");
				System.out.println(nexturl);
				pass(getDriver(), "Test pass for Next button  " + nexturl);
			}
			WebElement nextelement = getDriver().findElement(By.cssSelector(".nextBtn-3HPLH"));
			je.executeScript("arguments[0].style.display='block';", nextelement);
			je.executeScript("arguments[0].click();", nextelement);
			Thread.sleep(3000);

			// stop if firefox
			if (caps.getBrowserName().toLowerCase().contains("firefox")) {
				return;
			}
			// Previous button
			System.out.println("~~~~~~~~~~~~~~~" + System.lineSeparator() + "Previous Button" + System.lineSeparator()
					+ "~~~~~~~~~~~~~~~");
			// WebElement pelement =
			// getDriver().findElement(By.cssSelector("div.previousBtn-2T0ps"));
			je.executeScript("arguments[0].style.display='block';",
					getDriver().findElement(By.cssSelector("div.previousBtn-2T0ps")));
			WebElement pelement = getDriver().findElement(By.cssSelector("div.previousBtn-2T0ps"));
			Thread.sleep(2000);
			// new Actions(getDriver()).moveToElement(pelement).click().build().perform();
			je.executeScript("arguments[0].click();", pelement);
			Thread.sleep(3000);
			if (getDriver().getCurrentUrl().equals(nexturl)) {
				System.out.println(getDriver().getCurrentUrl());
				info(getDriver(), getDriver().getCurrentUrl());
				System.out.println("Test pass for Previous button");
				pass(getDriver(), "Test pass for Previous button");
			} else {
				System.out.println("Test ***FAIL*** for Previous button");
				fail(getDriver(), "Test ***FAIL*** for Previous button");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			error(getDriver(), e.getMessage());
		}

		System.out.println(line);

	}

}
