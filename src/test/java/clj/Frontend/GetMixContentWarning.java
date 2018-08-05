package clj.Frontend;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import commonLibrary.JSErrors;

public class GetMixContentWarning {
	static WebDriver driver;

	@BeforeTest
	public void setup() throws InterruptedException {

		driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www4.dailymailint.co.uk/home/");
		Thread.sleep(2000);
		try {
			if (driver.findElement(By.xpath("//button[text()='Got it']")).isDisplayed()) {
				driver.findElement(By.xpath("//button[text()='Got it']")).click();
				Thread.sleep(2000);
			}
			if (driver.findElement(By.cssSelector("button.mol-ads-cmp--close")).isDisplayed()) {
				driver.findElement(By.cssSelector("button.mol-ads-cmp--close")).click();
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 1)
	public void news() throws Exception {
		driver.get("https://www4.dailymailint.co.uk/news/");
		Thread.sleep(2000);
		String channel = "news";
		// MixContent.getMixContentWarning(driver, channel);
		JSErrors.generics(driver, channel);
	}

	@Test(priority = 2)
	public void tvshowbiz() throws Exception {
		driver.get("https://www4.dailymailint.co.uk/tvshowbiz/");
		Thread.sleep(2000);
		String channel = "tvshowbiz";
		// MixContent.getMixContentWarning(driver, channel);
		JSErrors.generics(driver, channel);
	}

	@Test(priority = 3)
	public void sport() throws Exception {
		driver.get("https://www4.dailymailint.co.uk/sport/");
		Thread.sleep(2000);
		String channel = "sport";
		// MixContent.getMixContentWarning(driver, channel);
		JSErrors.generics(driver, channel);
	}

	@Test(enabled = false, priority = 4)
	public void femail() throws Exception {
		driver.get("https://www4.dailymailint.co.uk/femail/");
		Thread.sleep(2000);
		String channel = "femail";
		JSErrors.generics(driver, channel);
	}

	@Test(enabled = false, priority = 5)
	public void health() throws Exception {
		driver.get("https://www4.dailymailint.co.uk/health/");
		Thread.sleep(2000);
		String channel = "health";
		JSErrors.generics(driver, channel);
	}

	@Test(enabled = false, priority = 6)
	public void sciencetech() throws Exception {
		driver.get("https://www4.dailymailint.co.uk/sciencetech/");
		Thread.sleep(2000);
		String channel = "sciencetech";
		JSErrors.generics(driver, channel);
	}

	@Test(enabled = false, priority = 7)
	public void money() throws Exception {
		driver.get("https://www4.dailymailint.co.uk/money/");
		Thread.sleep(2000);
		String channel = "money";
		JSErrors.generics(driver, channel);
	}

	@Test(enabled = false, priority = 8)
	public void travel() throws Exception {
		driver.get("https://www4.dailymailint.co.uk/travel/");
		Thread.sleep(2000);
		String channel = "travel";
		JSErrors.generics(driver, channel);
	}

	@Test(enabled = false, priority = 9)
	public void premierleague() throws Exception {
		driver.get("https://www4.dailymailint.co.uk/sport/premierleague/");
		Thread.sleep(2000);
		String channel = "premierleague";
		JSErrors.generics(driver, channel);
	}

	@Test(enabled = false, priority = 10)
	public void diet() throws Exception {
		driver.get("https://www4.dailymailint.co.uk/health/diets/");
		Thread.sleep(2000);
		String channel = "diets";
		commonLibrary.JSErrors.generics(driver, channel);
	}

	@Test(enabled = false, priority = 11)
	public void list() throws Exception {
		List<String> channels = Arrays.asList(

				"/money/markets", "/money/saving", "/money/investing", "/money/bills", "/money/cars", "/money/holidays",
				"/money/cardsloans", "/money/pensions", "/money/mortgageshome", "/money/experts", "/money/buytolet",
				"/money/smallbusiness", "/money/diyinvesting", "/money/guides", "/money/comment",
				"/money/indexinvesting", "/money/markets", "/money/saving", "/money/newslettersignup", "/money/guides"

		);

		for (String channel : channels) {
			System.out.println(channel);

			driver.get("https://www4.dailymailint.co.uk" + channel + "/");
			Thread.sleep(2000);
			// String channel = "diets";
			JSErrors.generics(driver, channel.substring(7));

		}

	}

	@AfterTest
	public void after() {
		driver.quit();
	}

}
