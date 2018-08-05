/**
 * 
 */
package browsers;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author jigneshkumarpatel
 *
 *         This class is before after annotation for CHROME
 */
public class BeforeCH {
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static String line = "<<==============================================>>";

	@BeforeSuite
	public void beforeSuite() {

		report = new ExtentReports(System.getProperty("user.dir") + "/testReport/Report-" + Url.currentDate + ".html");
		report.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));

	}

	@BeforeTest(alwaysRun = true)

	public void before(ITestContext ctx) throws Exception {
		String suitTest = ctx.getCurrentXmlTest().getName();

		if (Url.os.contains("win")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (Url.os.contains("mac")) {
			driver = new ChromeDriver();
		}

		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName();
		String browserVersion = caps.getVersion();
		logger = report.startTest("<font color='magenta';>" + suitTest + "</font>");
		String suiteinfo = suitTest + " Start";
		logger.log(LogStatus.INFO, "<font color='cyan';>" + suiteinfo + "</font>");

		if (suitTest.equals("cljTestChrome") && driver != null) {
			logger.log(LogStatus.INFO, "Chrome is open", browserName + "  " + browserVersion);

		} else {
			logger.log(LogStatus.ERROR, "Browser is not open");
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		if (driver != null) {
			// logger.log(LogStatus.INFO, "Chrome driver is open");
		}

		// Url.URL(driver);

		// driver.get("http://www.dailymail.co.uk/home/index.html");
		// driver.get(Url.baseurl+"/home/index.html");
		/*
		 * try{ assertEquals("Home",driver.findElement(By.cssSelector(
		 * ".h1-page-last-updated>h1")).getText()); }catch(Exception e){
		 * System.out.println(e.getMessage()); }
		 * System.out.println("****"+driver.getTitle()+"****");
		 */
		System.out.println("<=======================>");
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method, ITestContext ctx) throws Exception {
		// logger =
		// report.startTest((this.getClass().getSimpleName()+""+method.getName()),
		// method.getName());

		String suitTest = ctx.getCurrentXmlTest().getName();
		// logger = report.startTest("<font color='magenta';>"+suitTest+"</font>");
		// String suiteinfo = suitTest+" Start";
		// logger.log(LogStatus.INFO, "<font color='cyan';>"+suiteinfo+"</font>");
		if (suitTest.equals("cljTestChrome") && driver != null) {
			logger = report.startTest(
					("CH" + "-" + "<font color='#b4dcff'>" + this.getClass().getSimpleName() + "-" + method.getName())
							+ "</font>",
					"-" + "<font color='white'>" + method.getName() + "</font>");

		} else if (suitTest.equals("cljTestFirefox") && driver != null) {
			logger = report.startTest(
					("FF" + "-" + "<font color='#b4dcff'>" + this.getClass().getSimpleName() + "-" + method.getName())
							+ "</font>",
					"-" + "<font color='white'>" + method.getName() + "</font>");

		} else {
			logger.log(LogStatus.ERROR, "Browser is not open");
		}

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, ITestContext context) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, result.getMethod().toString());
			// Reporter.log("<Thread-id: " + Thread.currentThread().getId() + "> " +
			// message, true);
			// logger.log(LogStatus.INFO,
			// Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(t,
			// e););
			// info(driver,org.openqa.selenium.TimeoutException.class.toString());
			// info(driver,org.openqa.selenium.remote.RemoteWebDriver.class.toString());
			// logger.log(LogStatus.FAIL, "<font color='FF0000';>FAIL</font>");

			logger.log(LogStatus.INFO, logger.getDescription());
		}

		report.endTest(logger);
		report.flush();

	}

	@AfterTest(alwaysRun = true)
	public void after() {
		driver.quit();
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

}
