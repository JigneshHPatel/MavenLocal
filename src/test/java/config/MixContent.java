package config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MixContent {

	static WebDriver driver;

	public static void getMixContentWarning(WebDriver driver, String filename)
			throws IOException, InterruptedException {
		

		List<WebElement> allLinks = driver
				.findElements(By.xpath("//h2[@class='linkro-darkred']//a[contains(@href,'article')]"));

		System.out.println(driver.getCurrentUrl());

		System.out.println(allLinks.size());
		
		File f = new File("/Users/jigneshkumarpatel/Desktop/MixContent23/" + filename + ".text");
		FileWriter FW = new FileWriter(f);
		BufferedWriter BW = new BufferedWriter(FW);
		
		for (WebElement link : allLinks) {
			
		
			String text = link.getAttribute("href");
			System.out.println(text);
			
			String newTab = Keys.chord(Keys.COMMAND, Keys.RETURN);
			(new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(link));
			link.sendKeys(newTab);
			Thread.sleep(2000);
			Set<String> windowHandles = driver.getWindowHandles();
			Iterator<String> itr = windowHandles.iterator();
			
			if (itr.hasNext()) {
				String parent = itr.next();
				String child = itr.next();
				driver.switchTo().window(child);
				Thread.sleep(4000);
				((JavascriptExecutor) driver).executeScript("return window.stop");

				LogEntries jserrors = driver.manage().logs().get(LogType.BROWSER);
				BW.write("**********************************");
				BW.write("**********************************");
				BW.write(driver.getCurrentUrl());
				BW.write(System.lineSeparator());
				BW.write("------------------------");
				for (LogEntry error : jserrors) {

					System.out.println(error.getMessage());

					BW.write(System.lineSeparator());
					BW.write(error.getMessage());
					BW.write(System.lineSeparator());

					driver.close();
					driver.switchTo().window(parent);
				}
				
			}
			
		}
		BW.close();
		/*	if (text.contains("dailymailint.co.uk/")) {
				
				File f = new File("/Users/jigneshkumarpatel/Desktop/MixContent/" + filename + ".text");
				FileWriter FW = new FileWriter(f);
				BufferedWriter BW = new BufferedWriter(FW);

				// System.out.println(text);
				// articles.add(text);
				String newTab = Keys.chord(Keys.COMMAND, Keys.RETURN);
				(new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(link));
				link.sendKeys(newTab);
				Thread.sleep(2000);
				Set<String> windowHandles = driver.getWindowHandles();
				Iterator<String> itr = windowHandles.iterator();
				if (itr.hasNext()) {
					String parent = itr.next();
					String child = itr.next();
					driver.switchTo().window(child);
					Thread.sleep(4000);
					((JavascriptExecutor) driver).executeScript("return window.stop");

					LogEntries jserrors = driver.manage().logs().get(LogType.BROWSER);

					String articleID = (String) ((JavascriptExecutor) driver)
							.executeScript("return PageCriteria.articleId");
					System.out.println(articleID);
					String articleCH = (String) ((JavascriptExecutor) driver)
							.executeScript("return PageCriteria.subchannel");
					System.out.println(articleCH);

					// filename = articleCH + "_" + articleID;

					BW.write("**********************************");
					BW.write("**********************************");
					BW.write(driver.getCurrentUrl());
					BW.write(System.lineSeparator());
					BW.write("------------------------");

					for (LogEntry error : jserrors) {

						System.out.println(error.getMessage());

						BW.write(System.lineSeparator());
						BW.write(error.getMessage());
						BW.write(System.lineSeparator());

						driver.close();
						driver.switchTo().window(parent);
					}

				}
				BW.close();
			}*/
			
		

	}
}
