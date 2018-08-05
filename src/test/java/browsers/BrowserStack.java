/**
 * @author jigneshkumarpatel
 * BrowsrStack setup
 * before after methods
 * extent report config.
 */
package browsers;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import config.Screenshot;

public class BrowserStack {

	private static DataFileReader dataReader = new DataFileReader();

	// protected static ThreadLocal<RemoteWebDriver> remoteDriver = new
	// ThreadLocal<>();
	public static ThreadLocal<WebDriver> remoteDriver = new ThreadLocal<WebDriver>();
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExtentHtmlReporter htmlReporter;
	public static final String line = "⇦⇦⇦⇦⇦⇦⇦⇦⇦⇦࿇࿇࿇࿇࿇࿇࿇࿇࿇࿇࿇࿇࿇࿇࿇࿇⇨⇨⇨⇨⇨⇨⇨⇨⇨⇨";
	// public static JavascriptExecutor je = (JavascriptExecutor) driver;
	// public static final String username = "jigneshpatel34";
	// public static final String key = "dxqmFdZ94Q1GiXYSpQXy";

	public static final String username = dataReader.getBrowserstackUsername();//"testmol3";
	public static final String key = dataReader.getBrowserstackKey();//"Sjs5y2Ysg7UMdzi6fLXv";

	public static final String url = "https://" + username + ":" + key + "@hub-cloud.browserstack.com/wd/hub";
	public static Capabilities caps;// = ((RemoteWebDriver) driver).getCapabilities();
	public static String platformInfo;

	@BeforeSuite
	public void beforeSuite() {

		report = new ExtentReports(System.getProperty("user.dir") + "/testReport/" + Url.currentDate + "/"
				+ Url.currentDate + "-Report.html");
		report.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));

	}

	@Parameters(value = { "os", "os_version", "browser", "browser_version" })

	@BeforeTest(alwaysRun = true)
	public void Before(String os, String os_version, String browser, String browser_version, ITestContext ctx)
			throws MalformedURLException {
		/*
		 * DesiredCapabilities capabilities= new DesiredCapabilities();
		 * capabilities.setCapability("browserstack.debug", "true");
		 * capabilities.setCapability("browserstack.console", "errors");
		 * capabilities.setCapability("resolution", "1680x1050");
		 * capabilities.setCapability("os", os);
		 * capabilities.setCapability("os_version", os_version);
		 * capabilities.setCapability("browser", browser);
		 * capabilities.setCapability("browser_version", browser_version);
		 * //capabilities.setCapability("browserstack.selenium_version", "3.10.0");
		 * //capabilities.setCapability("browserstack.local", "true");
		 * if(browser.equalsIgnoreCase("firefox")) { System.out.println(browser);
		 * capabilities.setCapability("browserstack.geckodriver", "0.18.0"); } URL
		 * serverUrl= new URL(url); driver = new
		 * RemoteWebDriver(serverUrl,capabilities); driver.manage().deleteAllCookies();
		 * driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		 * driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS); try {
		 * Thread.sleep(2000); } catch (InterruptedException e1) { e1.getMessage(); }
		 * driver.manage().window().maximize(); try { setup(driver,browser,ctx); } catch
		 * (Exception e) { System.out.println(e.getMessage()); }
		 */
		String suitTest = ctx.getCurrentXmlTest().getName();
		logger = report.startTest("<font color='magenta';>" + suitTest + "</font>");
		logger.log(LogStatus.INFO, "<font color='cyan';>" + suitTest + "</font>");

		// Url.URL(driver);
		System.out.println("<~˜~˜~~˜~˜~~˜~˜~~˜~˜~>");
	}

	public static void setup(WebDriver driver, String browser, ITestContext ctx) throws Exception {

		String suitTest = ctx.getCurrentXmlTest().getName();

		caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName();
		String browserVersion = caps.getVersion();
		platformInfo = caps.getPlatform().toString().toLowerCase();
		logger = report.startTest("<font color='magenta';>" + suitTest + "</font>" + browserName);
		logger.log(LogStatus.INFO, "<font color='cyan';>" + suitTest + "</font>");
		info(driver, " Browser Information: " + browserName + browserVersion);
		info(driver, "Platform " + caps.getPlatform());
		System.out.println(browserName + browserVersion + " " + platformInfo);

	}

	@Parameters(value = { "os", "os_version", "browser", "browser_version" })
	@BeforeMethod(alwaysRun = true)
	// public void beforeMethod(Method method, ITestContext ctx ) throws Exception{
	public void beforeMethod(String os, String os_version, String browser, String browser_version, Method method,
			ITestContext ctx) throws Exception {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserstack.debug", "true");
		capabilities.setCapability("browserstack.console", "errors");
		capabilities.setCapability("resolution", "1920x1080");
		capabilities.setCapability("os", os);
		capabilities.setCapability("os_version", os_version);
		capabilities.setCapability("browser", browser);
		capabilities.setCapability("browser_version", browser_version);
		// capabilities.setCapability("browserstack.selenium_version", "3.10.0");
		// capabilities.setCapability("browserstack.local", "true");
		if (browser.equalsIgnoreCase("firefox")) {
			System.out.println(browser);
			capabilities.setCapability("browserstack.geckodriver", "0.18.0");
		} else if (browser.equalsIgnoreCase("IE")) {
			capabilities.setCapability("browserstack.ie.enablePopups", "true");
			capabilities.setCapability("resolution", "2048x1536");
			capabilities.setCapability("browserstack.ie.noFlash", "false");
			capabilities.setCapability("browserstack.ie.driver", "2.37");
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
					org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT);
		} else if (browser.equalsIgnoreCase("Edge")) {
			capabilities.setCapability("browserstack.edge.enablePopups", "true");
			capabilities.setCapability("resolution", "2048x1536");
			capabilities.setCapability("browserstack.edge.noFlash", "false");

		}
		URL serverUrl = new URL(url);
		remoteDriver.set(new RemoteWebDriver(serverUrl, capabilities));
		// driver = new RemoteWebDriver(serverUrl, capabilities);

		Thread.sleep(2000);
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		Thread.sleep(3000);

		getDriver().manage().window().maximize();

		Thread.sleep(3000);
		String suitTest = ctx.getCurrentXmlTest().getName();
		logger = report.startTest((suitTest + "-" + "<font color='#b4dcff'>" + this.getClass().getSimpleName()
				+ "</font>" + "--" + "<font color='#00ffff'>" + method.getName() + "</font>"));

		logger.log(LogStatus.INFO, "****" + method.getName().toUpperCase() + "****");
		logger.log(LogStatus.INFO, method.getAnnotation(Test.class).description());
		// Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		caps = ((RemoteWebDriver) getDriver()).getCapabilities();
		System.out.println(caps.getBrowserName() + caps.getVersion() + "--" + caps.getPlatform());
		info(getDriver(), caps.getBrowserName() + caps.getVersion() + "--" + caps.getPlatform());

	}

	public WebDriver getDriver() {

		// Get driver from ThreadLocalMap
		return remoteDriver.get();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, ITestContext context, Method method) throws Exception {
		/*
		 * if(result.getStatus()==ITestResult.SKIP){ Iterator<ITestResult>
		 * skippedTestCases = context.getSkippedTests().getAllResults().iterator();
		 * while (skippedTestCases.hasNext()) { ITestResult skippedTestCase =
		 * skippedTestCases.next(); ITestNGMethod testmethod =
		 * skippedTestCase.getMethod(); if
		 * (context.getSkippedTests().getResults(testmethod).size() > 0) {
		 * System.out.println("Removing:" + skippedTestCase.getTestClass().toString());
		 * skippedTestCases.remove(); } } }
		 */
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
			File logFile = new File(
					System.getProperty("user.dir") + "/testReport/" + Url.currentDate + "/" + fileName + ".log");
			PrintStream ps = new PrintStream(logFile);
			result.getThrowable().printStackTrace(ps);
			ps.close();
			logger.log(LogStatus.INFO, "<a href='" + Url.currentDate + "/" + fileName + ".log"
					+ "'target='_blank'>Open this log file for detailed error log</a>");

		}

		report.endTest(logger);
		report.flush();

		if (getDriver() != null) {
			getDriver().quit();
		}
	}

	@AfterTest(alwaysRun = true)
	public void after() {
		/*
		 * if(driver!= null) { driver.quit(); }
		 */
		System.gc();
	}

	public static void pass(WebDriver remotedriver, String passMsg) {
		logger.log(LogStatus.PASS, "<font color='#00ff00';>" + passMsg + "</font>");
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