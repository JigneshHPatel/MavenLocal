package cljCheck;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import browsers.adNamesFileReader;

/**
 * @author jigneshkumarpatel
 *
 */
public class Ads extends browsers.Chrome {
	private static List<String> actualAds = new ArrayList<String>();
	private static adNamesFileReader adNameReader = new adNamesFileReader();

	@Test(enabled = false)
	public void MpuHomePage() throws Exception {

		JavascriptExecutor je = (JavascriptExecutor) driver;
		// driver.get("http://www.dailymail.co.uk/news/article-5877933/");
		// Thread.sleep(3000);
		je.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);

		// wait until page loaded
		for (int j = 0; j < 25; j++) {
			Thread.sleep(1000);
			if (je.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}

		}
/*
		List<String> adNames = Arrays.asList("billboard", "jwplayer", "inread_player", "inread_player_top",
				"leader_bottom", "leader_lower_middle", "leader_middle", "leader_very_bottom", "mpu_home",
				"mpu_mobile_top", "mpu_puff_15", "mpu_puff_30", "mpu_puff_45", "mpu_puff_other_55", "mpu_puff_other_65",
				"mpu_puff_other_75", "mpu_puff_other_85", "mpu_puff_other_95", "mpu_puff_other_105",
				"mpu_puff_other_115", "mpu_puff_other_125", "mpu_puff_other_135", "mpu_puff_other_145", "mpu_top",
				"sky_left_top", "sky_right_top");
*/
		Long a = (Long) je.executeScript("return adverts.performance.state.auctions.length");

		for (int i = 0; i < a; i++) {
			//for (String adName : adNames) {
			for (String adName : adNameReader.ads()) {
				try {
					String js = "return adverts.performance.state.auctions[" + i + "]['slots']['" + adName
							+ "']['ad rendered']";
					String result = je.executeScript(js).toString();
					if (result != null) {
						System.out.println(adName + " is Visible");
						System.out.println(adName + " = " + result);
						System.out.println("*************");
						actualAds.add(adName);

					} else {
						System.out.println(adName + " is ***NOT*** rendered");
					}

				} catch (Exception e) {

				}

			}
		}

		System.out.println("Total ads present are: " + actualAds.size());

		System.out.println(System.lineSeparator() + "======================" + System.lineSeparator());

		//for (String ad : adNames) {
			for (String ad : adNameReader.ads()) {
			if (actualAds.contains(ad)) {

			} else {
				System.out.println(ad + " is NOT Present");
			}
		}
	}

	@Test(enabled = false)
	public void MpuArticlePage() throws Exception {
		driver.get("http://www.dailymail.co.uk/news/article-5877933/");
		Thread.sleep(5000);

		WebElement infiniteList = driver.findElement(By.id("infinite-list"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", infiniteList);
		Thread.sleep(2000);

		try {
			for (int s = 1; s < 18; s++) {

				WebElement infi = driver.findElement(By.xpath("//div[@id='infinite-list']/div[" + s + "]/div[10]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", infi);
				Thread.sleep(3000);
			}
		} catch (Exception e) {

		}

		// wait until page loaded
		for (int j = 0; j < 25; j++) {
			Thread.sleep(1000);
			if (((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
					.equals("complete")) {
				break;
			}

		}

		AdConsoleOutput.AdConsole(driver);

	}

	@Test()
	public void Taboola() throws Exception {
		driver.get("http://www.dailymail.co.uk/news/article-5877933/");
		Thread.sleep(5000);

		WebElement infiniteList = driver.findElement(By.id("infinite-list"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", infiniteList);
		Thread.sleep(2000);

		System.out.println("==========================================");

		if (driver.findElement(By.id("taboola-below-main-column")).isDisplayed()) {
			System.out.println("Taboola ad Before MWV is displayed");
			List<WebElement> totalTaboolaBeforeMWV = driver.findElements(
					By.xpath("//div[@id='taboola-below-main-column']//a[@class=' item-thumbnail-href ']"));

			System.out.println(
					"Total taboola ads loaded before MWV taboola container are: " + totalTaboolaBeforeMWV.size());

			for (WebElement taboolBeforeMWV : totalTaboolaBeforeMWV) {

				System.out.println((totalTaboolaBeforeMWV.indexOf(taboolBeforeMWV) + 1) + " ad is present");
				System.out.println(taboolBeforeMWV.getAttribute("title"));
				System.out.println("---------------------");
			}
		} else {
			System.out.println("Taboola ad BEFORE MWV is ***NOT*** displayed");
		}
		System.out.println("==========================================");

		if (driver.findElement(By.id("taboola-below-article-thumbnails-2nd")).isDisplayed()) {
			System.out.println("Taboola ad AFTER MWV is displayed");
			List<WebElement> totalTaboolaAfterMWV = driver.findElements(
					By.xpath("//div[@id='taboola-below-article-thumbnails-2nd']//a[@class=' item-thumbnail-href ']"));

			for (WebElement taboolaAfterMWV : totalTaboolaAfterMWV) {
				System.out.println((totalTaboolaAfterMWV.indexOf(taboolaAfterMWV) + 1) + " ad is present");
				System.out.println(taboolaAfterMWV.getAttribute("title"));
				System.out.println("---------------------");
			}

		} else {
			System.out.println("Taboola ad AFTER MWV is ***NOT*** displayed");
		}

		System.out.println("==========================================");

		try {
			for (int s = 1; s < 18; s++) {

				WebElement infi = driver.findElement(By.xpath("//div[@id='infinite-list']/div[" + s + "]/div[10]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", infi);
				Thread.sleep(3000);
			}
		} catch (Exception e) {

		}

		// wait until page loaded
		for (int j = 0; j < 25; j++) {
			Thread.sleep(1000);
			if (((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
					.equals("complete")) {
				break;
			}

		}

		List<WebElement> taboolaAds = driver.findElements(By.xpath("//div[contains(@id,'taboola-stream-thumbnails')]"));
		System.out.println("Total Taboola in article page are " + taboolaAds.size());

		for (WebElement taboola : taboolaAds) {
			if (taboola.isDisplayed()) {
				System.out.println(
						(taboolaAds.indexOf(taboola) + 1) + " : " + taboola.getAttribute("id") + "  is displayed");

			}
		}
	}

}
