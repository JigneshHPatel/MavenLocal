package commonLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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

public class JSErrors {
	static WebDriver driver;
	private List<String> articles = new ArrayList<String>();

	public List<String> getArticles() {
		return articles;

	}

	public static void GetJSErrosLog(WebDriver driver, String filename) throws IOException {

		DateFormat dateformat = new SimpleDateFormat("MMMdd");
		String currentDate = dateformat.format((new Date()));

		// Capture all JSerrors and print In console.
		LogEntries jserrors = driver.manage().logs().get(LogType.BROWSER);
		File f = new File("/Users/jigneshkumarpatel/Desktop/MixContent/" + currentDate + filename + ".text");
		if (!f.exists()) {
			f.createNewFile();
		}
		Writer BW = null;

		BW = new BufferedWriter(new FileWriter(f, true));
		// FileWriter FW = new FileWriter(f);
		// BufferedWriter BW = new BufferedWriter(FW);

		BW.write("**********************************");
		BW.write("**********************************");
		BW.write(driver.getCurrentUrl());
		BW.write("------------------------");
		BW.write(System.lineSeparator());
		for (LogEntry error : jserrors) {

			if (error.getMessage().contains("Mixed Content")) {

				System.out.println(error.getMessage());
				System.out.println(System.lineSeparator());

				BW.write(System.lineSeparator());
				BW.write(error.getMessage());
				BW.write(System.lineSeparator());

			}

		}
		BW.close();

		// Write uniq
		BufferedReader reader = new BufferedReader(new FileReader(f));
		Set<String> lines = new HashSet<String>();
		String line;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		reader.close();
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		for (String unique : lines) {
			writer.write(unique);
			writer.newLine();
		}
		writer.close();

	}

	public static void generics(WebDriver driver, String channel) throws Exception {
		List<WebElement> allLinks = driver
				.findElements(By.xpath("//h2[@class='linkro-darkred']//a[contains(@href,'article')]"));

		System.out.println(allLinks.size());

		int i = 0;
		for (WebElement link : allLinks) {
			if (i == 500) {
				return;
			}
			// System.out.println(link.getAttribute("href"));

			String text = link.getAttribute("href");
			// System.out.println(link.getAttribute("href"));

			if (text.contains("www4.dailymailint.co.uk/")) {

				// System.out.println(text);
				// articles.add(text);

				String newTab = Keys.chord(Keys.COMMAND, Keys.RETURN);
				link.sendKeys(newTab);
				Thread.sleep(3000);
				Set<String> windowHandles = driver.getWindowHandles();
				Iterator<String> itr = windowHandles.iterator();
				try {
					if (itr.hasNext()) {
						String parent = itr.next();
						String child = itr.next();
						driver.switchTo().window(child);
						Thread.sleep(10000);
						((JavascriptExecutor) driver).executeScript("return window.stop");

						/*
						 * String articleID = (String) ((JavascriptExecutor) driver)
						 * .executeScript("return PageCriteria.articleId");
						 * System.out.println(articleID); String articleCH = (String)
						 * ((JavascriptExecutor) driver)
						 * .executeScript("return PageCriteria.subchannel");
						 * System.out.println(articleCH);
						 */
						// String filename = articleCH + "_" + articleID;

						JSErrors.GetJSErrosLog(driver, channel);

						driver.close();
						driver.switchTo().window(parent);

					}
				} catch (Exception e) {

				}

			}

			i++;

		}
		// System.out.println("Total Articles: " + articles.size());
	}
}
