package cljCheck;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import browsers.Url;
import config.WaitObj;

public class Sample2 {
	public static WebDriver driver;
	public static final String USERNAME = "jigneshpatel34";
	public static final String AUTOMATE_KEY = "dxqmFdZ94Q1GiXYSpQXy";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	

	@BeforeTest(enabled = false)
	public void beforeLocal() {

		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@BeforeTest // (enabled = false)
	public void beforeBS() throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", "Chrome");
		caps.setCapability("browser_version", "66.0");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "10");
		caps.setCapability("resolution", "1920x1080");

		driver = new RemoteWebDriver(new URL(URL), caps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @SuppressWarnings("unchecked")
	@Test // (retryAnalyzer = Retry.class)
	public void sample1() throws InterruptedException {

		// driver.get("http://www.dailymail.co.uk/tvshowbiz/article-5701183/");

		//JavascriptExecutor je = (JavascriptExecutor) driver;
		driver.get("http://www.dailymail.co.uk/home/index.html");
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("UK Home"), "HomePage is present");
/*
		for (int article = 1; article < 5; article++) {
			driver.findElement(By.xpath("//div[@class='femail item']/div/ul/li[" + article + "]/a")).click();
			List<WebElement> TotalImages = driver.findElements(By.xpath("//div[@class='mol-img']/div[2]/ul"));
			if (!TotalImages.isEmpty()) {
				break;
			}
		}
		*/

		// List<WebElement> allImgs=nul@SuppressWarnings("unchecked")
		/*
		 * List<WebElement> eles = (List<WebElement>)
		 * je.executeScript("var results = new Array();" +
		 * "var element = document.evaluate(\"//div[@class='mol-img']/div[2]/ul\", document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);"
		 * + "for ( var i = 0 ; i < element.snapshotLength ; i++ )" + "{" +
		 * "results.push(element.snapshotItem(i));" + "}" + "return results;","");
		 */
		// allImgs= (List<WebElement>) je.executeScript("return
		// document.evaluate('//div[@class='mol-img']/div[2]/ul');",allImgs);
	/*	System.out.println(driver.getCurrentUrl());
		List<WebElement> TotalImages = driver.findElements(By.xpath("//div[@class='mol-img']/div[2]/ul"));
		System.out.println("Total images in article are: " + TotalImages.size());

		WebElement image = TotalImages.get(0);

		List<WebElement> shareLinks = image.findElements(By.tagName("li"));
		System.out.println("Total share icons on image are: " + (shareLinks.size() - 1));
		for (int i = 0; i < shareLinks.size(); i++) {
			WebElement shareLink = shareLinks.get(i);
			String socialName = shareLink.getAttribute("data-social-scope");
			if (socialName.equalsIgnoreCase("email") || socialName.equalsIgnoreCase("link")) {
				break;
			}
			je.executeScript("arguments[0].click()", shareLink);
			Thread.sleep(2000);
			if (socialName.equalsIgnoreCase("google")) {
				je.executeScript("arguments[0].click()", shareLink);
				Thread.sleep(2000);
			}
			Set<String> handler = driver.getWindowHandles();
			Iterator<String> it = handler.iterator();
			String parent = it.next();

			String child = it.next();

			driver.switchTo().window(child);
			System.out.println(socialName);
			Thread.sleep(2000);
			socialSharing(driver, socialName);
			Set<String> handlerNew = driver.getWindowHandles();
			if (handlerNew.size() > 1) {
				driver.close();
			}
			driver.switchTo().window(parent);
			System.out.println("------------");

		}
 	*/
	}

	public static void socialSharing(WebDriver driver, String socialName) throws InterruptedException {
		if (socialName.equalsIgnoreCase("facebook")) {
			Assert.assertTrue(driver.getCurrentUrl().contains("www.facebook.com/login"));
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys("testmolfb6@gmail.com");
			driver.findElement(By.id("pass")).clear();
			driver.findElement(By.id("pass")).sendKeys("testmol");
			driver.findElement(By.cssSelector("input[name='login']")).click();
			Thread.sleep(2000);
			System.out.println("Logged into FB");

			driver.findElement(By.id("u_0_1v")).click();
			Thread.sleep(2000);
			System.out.println("Shared on FB");

		} else if (socialName.equalsIgnoreCase("twitter")) {
			Assert.assertTrue(driver.getCurrentUrl().contains("twitter.com"));
			String sharefield = driver.findElement(By.id("status")).getAttribute("value");
			String sharelink = Url.currentDate + sharefield;
			driver.findElement(By.id("status")).clear();
			driver.findElement(By.id("status")).sendKeys(sharelink);
			driver.findElement(By.id("username_or_email")).clear();
			driver.findElement(By.id("username_or_email")).sendKeys("testmoltw13");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("testmol");
			driver.findElement(By.cssSelector("input[value='Log in and Tweet']")).click();
			System.out.println("Shared on Twitter");

		} else if (socialName.equalsIgnoreCase("pinterest")) {
			// Assert.assertTrue(driver.getCurrentUrl().contains("pinterest"));
			// System.out.println("Pass for Pinterest");
			Thread.sleep(3000);
			try {
				WaitObj.wait(driver, driver.findElement(By.xpath("//div[@title='Test']")));
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
			try {
				if (driver.getCurrentUrl().contains("www.pinterest.co.uk/login")) {
					System.out.println("Login Required for pinterest");
					driver.findElement(By.id("email")).clear();
					driver.findElement(By.id("email")).sendKeys("testmolfb6@gmail.com");
					driver.findElement(By.id("password")).clear();
					driver.findElement(By.id("password")).sendKeys("testmolfb");
					driver.findElement(By.xpath("//div[text()='Log in']")).click();
					System.out.println("clicked on login");
					Thread.sleep(3000);
					driver.findElement(By.xpath("//div[@title='Test']")).click();
					System.out.println("clicked on test");
					Thread.sleep(3000);
					System.out.println("Shared on Pinterest");

				} else if (driver.getCurrentUrl().contains("www.pinterest.co.uk/pin/create/button")) {
					System.out.println("User is already logged in");
					driver.findElement(By.xpath("//div[@title='Test']")).click();
					Thread.sleep(3000);
					System.out.println("Shared on Pinterest");

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} else if (socialName.equalsIgnoreCase("gplus") || socialName.equalsIgnoreCase("google")) {
			// Assert.assertTrue(driver.getCurrentUrl().contains("google.com/signin"));
			// System.out.println("Pass for GooglePlus");
			driver.findElement(By.id("identifierId")).clear();
			driver.findElement(By.id("identifierId")).sendKeys("testmolfb6");
			driver.findElement(By.cssSelector("#identifierNext>content>span")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[type='password']")).clear();
			driver.findElement(By.cssSelector("input[type='password']")).sendKeys("testmolfb");
			driver.findElement(By.xpath("//div[@id='passwordNext']/content/span")).click();
			Thread.sleep(2000);
			if (driver.getPageSource().contains("Verify it's you")) {
				System.out.println("Authantication Required");

			} else if (!driver.getPageSource().contains("Verify it's you")) {
				driver.findElement(By.xpath("//div[@guidedhelpid='sharebutton']")).click();
				Thread.sleep(2000);
				System.out.println("Shared on googlePlus");
			}
		}
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
