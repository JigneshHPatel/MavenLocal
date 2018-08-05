/**
 * 
 */
package browsers;

import java.io.File;
import java.io.PrintStream;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import config.CmpConsent;
import config.Screenshot;
import util.SystemDateTime;

/**
 * @author jigneshkumarpatel
 *
 *         This class is for before set up contains Extent report setup contains
 *         browsers config
 *
 */
public class BeforeAfter {
	// protected static ThreadLocal<RemoteWebDriver> remoteDriver = new
	// ThreadLocal<>();
	public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExtentHtmlReporter htmlReporter;
	public static String line = "<<==============================>>";
	//public static JavascriptExecutor je = (JavascriptExecutor) getDriver();
	public static Capabilities caps;

	private static DataFileReader dataReader = new DataFileReader();

	@BeforeSuite
	public void beforeSuite() {

		report = new ExtentReports(System.getProperty("user.dir") + "/testReport/" + Url.currentDate + "/"
				+ Url.currentDate + "-Report.html");
		report.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));

	}

	@Parameters("browser")
	@BeforeTest(alwaysRun = true)
	public void setup(String browser, ITestContext ctx) throws Exception {

		String suitTest = ctx.getCurrentXmlTest().getName();
		logger = report.startTest("<font color='magenta';>" + suitTest + "</font>");
		logger.log(LogStatus.INFO, "<font color='cyan';>" + suitTest + "</font>");

		System.out.println("<~˜~˜~~˜~˜~~˜~˜~~˜~˜~>");


	}

	@SuppressWarnings("deprecation")
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method, ITestContext ctx, String browser) throws Exception {

		if (browser.equalsIgnoreCase("chrome")) {

			if (Url.os.contains("mac")) {
				// driver = new ChromeDriver();
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("disable-infobars");
				threadDriver.set(new ChromeDriver(options));
			} else {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
				/*
				 * ChromeOptions options = new ChromeOptions(); options.setBinary(
				 * "C:\\Users\\jigneshkumar.patel\\Downloads\\GoogleChromePortable64\\GoogleChromePortable.exe"
				 * ); driver = new ChromeDriver(options);
				 */
				// driver = new ChromeDriver();
				threadDriver.set(new ChromeDriver());
			}
		}
		if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
			if (Url.os.contains("mac")) {
				// driver = new FirefoxDriver();
				threadDriver.set(new FirefoxDriver());
			} else {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
				// driver = new FirefoxDriver();
				threadDriver.set(new FirefoxDriver());
			}
		}
		if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\IEDriverServer.exe");
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			capability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capability.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, 1);
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			// driver = new InternetExplorerDriver(capability);
			threadDriver.set(new InternetExplorerDriver(capability));
		}

		getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		if(browser.equalsIgnoreCase("chrome")) {
		getDriver().manage().window().fullscreen();
		}
		caps = ((RemoteWebDriver) getDriver()).getCapabilities();
		
		String suitTest = ctx.getCurrentXmlTest().getName();
		
		logger = report.startTest((suitTest + "-" + "<font color='#b4dcff'>" + this.getClass().getSimpleName()
				+ "</font>" + "--" + "<font color='#00ffff'>" + method.getName() + "</font>"));

		logger.log(LogStatus.INFO, "****" + method.getName().toUpperCase() + "****");
		logger.log(LogStatus.INFO, method.getAnnotation(Test.class).description());
		
		System.out.println(caps.getBrowserName() + caps.getVersion() + "--" + caps.getPlatform());
		info(getDriver(), caps.getBrowserName() + caps.getVersion() + "--" + caps.getPlatform());

		getDriver().get(dataReader.getBaseUrl());
		Thread.sleep(2000);
		CmpConsent.gdprConsent();
		
		System.out.println("-------------------------------------------------");
		System.out.println("  *** " + method.getName() + " ***");
		System.out.println("-------------------------------------------------");

	}

	public static WebDriver getDriver() {
		WebDriver driver = threadDriver.get();
		return driver;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, ITestContext context, Method method) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			fail(getDriver(), method.getName());
			info(getDriver(), " Failed Screenshot");

			// Screenshot
			try {
				Screenshot.takeimg(method, result, context);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			// error log file
			String fileName = context.getCurrentXmlTest().getName() + method.getName();
			File logFile = new File(System.getProperty("user.dir") + "/testReport/" + SystemDateTime.currentDateTime()
					+ "/" + fileName + ".log");
			try {
				PrintStream ps = new PrintStream(logFile);
				result.getThrowable().printStackTrace(ps);
				ps.close();
				logger.log(LogStatus.INFO, "<a href='" + "./testReport/" + SystemDateTime.currentDateTime() + "/"
						+ fileName + ".log" + "'target='_blank'>Open this log file for detailed error log</a>");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		report.endTest(logger);
		report.flush();

		getDriver().quit();
		
		System.out.println(line);

	}

	@AfterTest(alwaysRun = true)
	public void after() {
		System.gc();
	}

	public static void pass(WebDriver driver, String passMsg) {
		logger.log(LogStatus.PASS, "<font color='#008000';>" + passMsg + "</font>");
	}

	public static void fail(WebDriver driver, String failMsg) {
		logger.log(LogStatus.FAIL, "<font color='#dc143c';>" + failMsg + "</font>");
	}

	public static void info(WebDriver driver, String infoMsg) {
		logger.log(LogStatus.INFO, "<font color='#9fdbe2';>" + infoMsg + "</font>");
	}

	public static void error(WebDriver driver, String errorMsg) {
		logger.log(LogStatus.ERROR, "<font color='#FFFF00';>" + errorMsg + "</font>");
	}

	public static void warning(WebDriver driver, String warningMsg) {
		logger.log(LogStatus.WARNING, "<font color='	#ff7f50'>" + warningMsg + "</font>");
	}

}