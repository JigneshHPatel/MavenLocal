package cljCheck;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import org.testng.annotations.*;

public class JsError {
	static WebDriver driver;

	@BeforeTest
	public static void setUp() throws Exception {

		ChromeOptions cap = new ChromeOptions();

		LoggingPreferences pref = new LoggingPreferences();
		pref.enable(LogType.BROWSER, Level.ALL);
		cap.setCapability(CapabilityType.LOGGING_PREFS, pref);
		driver = new ChromeDriver(cap);

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public static void GetJSErrosLog(WebDriver driver) throws IOException {
		// Capture all JSerrors and print In console.
		LogEntries jserrors = driver.manage().logs().get(LogType.BROWSER);
		File f = new File("/Users/jigneshkumarpatel/Desktop/output.text");
		FileWriter FW = new FileWriter(f);
		BufferedWriter BW = new BufferedWriter(FW);
		for (LogEntry error : jserrors) {

			System.out.println(error.getMessage());

			BW.write(System.lineSeparator());
			BW.write(error.getMessage());
			BW.write(System.lineSeparator());

		}
		BW.close();
	}

	@Test
	public void testMethod() throws IOException, Exception {
		driver.get("https://www4.dailymailint.co.uk/");
		Thread.sleep(2000);
		// Call GetJSErrosLog() to log and print JSErrors In console.
		GetJSErrosLog(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}