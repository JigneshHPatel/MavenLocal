package cljCheck;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//import net.lightbody.*;
import org.browsermob.core.har.Har;
import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
//import org.browsermob.proxy.ProxyServer;
//import org.browsermob.core.har.Har;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Tracking {
	public static WebDriver driver;

	@Test
	public void tracking() throws IOException, Exception {

		// Start the BrowserMob pro.
		ProxyServer server = new ProxyServer(9090);
		server.start();

		// Get the selenium pro object.
		Proxy proxy = server.seleniumProxy();

		// Set desired capability for using Pro Server
		DesiredCapabilities capabilities = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		capabilities.setCapability(CapabilityType.PROXY, proxy);
		options.merge(capabilities);

		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\jigneshkumar.patel\\Desktop\\Automation\\Jar\\chromedriver.exe");
		driver = new ChromeDriver(options);

		driver.get("http://www.dailymail.co.uk/home/index.html");
		
		Thread.sleep(2000);
		server.newHar("TrackingData");
		driver.findElement(By.cssSelector(".facebook-like-static")).click();
		// driver.findElement(By.xpath("//*[@id='page-container']/div[2]/ul/li[2]/span/a")).click();
		// driver.findElement(By.xpath("//*[@id='page-container']/div[2]/ul/li[11]/span/a")).click();

		Thread.sleep(3000);

		// Collect the performance data from the BrowserMob proxy server.
		// Get the HAR data.
		Har ar = server.getHar();

		// Write the HAR Data in a File
		File arFile = new File("/Users/jigneshkumarpatel/Desktop/trackingResult/TrackingT.json");
		ar.writeTo(arFile);

		// Stop the BrowserMob Proxy Server
		server.stop();

		driver.quit();

		FileReader fileReader = new FileReader(arFile);

		// File arFile = new
		// File("C:\\Users\\jigneshkumar.patel\\Desktop\\TrackingT.json");
		// FileReader fileReader = new FileReader(arFile);

		Gson gson = new Gson();
		JsonObject element = gson.fromJson(fileReader, JsonObject.class);
		JsonObject log = element.get("log").getAsJsonObject();

		/*
		 * JsonArray entries = log.get("entries").getAsJsonArray();
		 * 
		 * for(int i = 0; i < entries.size(); i++) { JsonObject entry =
		 * entries.get(i).getAsJsonObject(); JsonObject response =
		 * entry.get("response").getAsJsonObject(); int status =
		 * response.get("status").getAsInt(); System.out.println(status+"   "+i);
		 * 
		 * 
		 * 
		 * }
		 */

		JsonArray entries = log.get("entries").getAsJsonArray();
		System.out.println(" Total Entries are: " + entries.size());
		for (int i = 0; i < entries.size(); i++) {
			JsonObject entry = entries.get(i).getAsJsonObject();
			JsonObject request = entry.get("request").getAsJsonObject();
			String url = request.get("url").getAsString();
			System.out.println(url);

			// JsonObject method = response.get("method").getAsJsonObject();
			// System.out.println(method);

		}

		/*
		 * HarReader harReader = new HarReader(); Har har = harReader.readFromFile(new
		 * File("C:\\Users\\jigneshkumar.patel\\Desktop\\Automation\\Track.har"));
		 * System.out.println(har.getLog().getCreator().getName());
		 */
	}

}