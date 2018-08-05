package cljCheck;

import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import browsers.DataFileReader;
import config.CmpConsent;
import config.MyProfileObj;
import config.Retry;
import config.WaitObj;

public class AllLoginsAndMyProfile extends browsers.BeforeAfter {

	private static DataFileReader dataReader = new DataFileReader();

	@Test(retryAnalyzer = Retry.class, description = "Direct login and Profile page")
	public static void a_DirectLogin() throws Exception {
		System.out.println("------------------------");
		System.out.println("****DirectLogin****");
		System.out.println("------------------------");
		loginClick(getDriver());

		String expectedText = "Login";
		String actualText = getDriver().findElement(By.cssSelector(".lcolumn>h3")).getText();
		WebElement emailField = getDriver().findElement(By.id("reg-lbx-email-page"));
		String emailAddress = dataReader.getDirectloginUsername();// "testmol@sharklasers.com";
		WebElement passField = getDriver().findElement(By.id("reg-lbx-password-page"));
		String password = dataReader.getDirectloginPassword();// "testmol";
		WebElement loginButton = getDriver().findElement(By.cssSelector(".reg-btn-login"));
		String printMsg = "Direct";
		AllLoginMethods(getDriver(), expectedText, actualText, emailField, emailAddress, passField, password,
				loginButton, printMsg);
		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "Login by Facebook")
	public void b_FacebookLogin() throws Exception {
		System.out.println("--------------------------");
		System.out.println("****FacebookLogin****");
		System.out.println("--------------------------");
		loginClick(getDriver());

		getDriver().findElement(By.cssSelector("input[value='facebook']")).click();

		Thread.sleep(2000);
		String expectedText = "Log into Facebook";
		String actualText = getDriver().findElement(By.cssSelector("#header_block>span")).getText();
		WebElement emailField = getDriver().findElement(By.id("email"));
		String emailAddress = dataReader.getFacebookUsername();// "testmolfb1@gmail.com";
		WebElement passField = getDriver().findElement(By.id("pass"));
		String password = dataReader.getFacebookPassword();// "testmol";
		WebElement loginButton = getDriver().findElement(By.cssSelector("#loginbutton"));
		String printMsg = "Facebook";
		AllLoginMethods(getDriver(), expectedText, actualText, emailField, emailAddress, passField, password,
				loginButton, printMsg);
		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "Login by Twitter")
	public void c_TwitterLogin() throws Exception {
		System.out.println("------------------------");
		System.out.println("****TwitterLogin****");
		System.out.println("------------------------");
		loginClick(getDriver());
		getDriver().findElement(By.cssSelector("input[value='twitter']")).click();
		Thread.sleep(2000);

		String expectedText = "Authorize Registration - Live to use your account?";
		String actualText = getDriver().findElement(By.cssSelector(".auth>h2")).getText();
		WebElement emailField = getDriver().findElement(By.id("username_or_email"));
		String emailAddress = dataReader.getTwitterUsername();// "testmoltw02";
		WebElement passField = getDriver().findElement(By.id("password"));
		String password = dataReader.getTwitterPassword();// "testmoltw";
		WebElement loginButton = getDriver().findElement(By.cssSelector("#allow"));
		String printMsg = "Twitter";
		AllLoginMethods(getDriver(), expectedText, actualText, emailField, emailAddress, passField, password,
				loginButton, printMsg);
		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "Registration page")
	public void d_Registration() throws Exception {
		System.out.println("------------------------");
		System.out.println("****Registration****");
		System.out.println("------------------------");
		loginClick(getDriver());
		try {
			String newtoMail = getDriver().findElement(By.xpath("//h3[text()='New to MailOnline?']")).getText();
			Assert.assertTrue(newtoMail.equalsIgnoreCase("New to MailOnline?"));
			System.out.println(newtoMail + " is displayed");
			pass(getDriver(), newtoMail + " is displayed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			info(getDriver(), e.getMessage());
		}
		try {
			String shareNews = getDriver().findElement(By.xpath("//h4[text()='Share your views on the news']"))
					.getText();
			Assert.assertTrue(shareNews.equalsIgnoreCase("Share your views on the news"));
			System.out.println(shareNews + " is displayed");
			pass(getDriver(), shareNews + " is displayed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			info(getDriver(), e.getMessage());
		}
		try {
			String status = getDriver().findElement(By.xpath("//h4[text()='Check your status']")).getText();
			Assert.assertTrue(status.equalsIgnoreCase("Check your status"));
			System.out.println(status + " is displayed");
			pass(getDriver(), status + " is displayed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			info(getDriver(), e.getMessage());
		}
		try {
			String compare = getDriver().findElement(By.xpath("//h4[text()='How do you compare']")).getText();
			Assert.assertTrue(compare.equalsIgnoreCase("How do you compare"));
			System.out.println(compare + " is displayed");
			pass(getDriver(), compare + " is displayed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			info(getDriver(), e.getMessage());
		}

		WebElement joinNow = getDriver().findElement(By.cssSelector(".reg-btn.wocc.reg-btn-join"));
		WaitObj.wait(getDriver(), joinNow);
		joinNow.click();
		Thread.sleep(3000);

		try {
			String regTitle = getDriver().findElement(By.tagName("h1")).getText();
			Assert.assertEquals(regTitle, "Thank you for choosing to join");
			System.out.println(regTitle + "--- is displayed");
			pass(getDriver(), regTitle + "--- is displayed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			error(getDriver(), e.getMessage());
		}

		try {
			getDriver().findElement(By.id("reg-email")).clear();
			getDriver().findElement(By.id("reg-email")).sendKeys("test@test.com");
			getDriver().findElement(By.id("reg-name")).clear();
			getDriver().findElement(By.id("reg-name")).sendKeys("TesterMolauto");
			getDriver().findElement(By.id("reg-password")).clear();
			getDriver().findElement(By.id("reg-password")).sendKeys("tester");
			getDriver().findElement(By.id("reg-password-conf")).clear();
			getDriver().findElement(By.id("reg-password-conf")).sendKeys("tester");
			getDriver().findElement(By.id("reg-city")).clear();
			getDriver().findElement(By.id("reg-city")).sendKeys("London");
			getDriver().findElement(By.id("regTerms")).click();
			//getDriver().findElement(By.cssSelector("div.reg-fld>label ")).click();
			getDriver().findElement(By.cssSelector("button.reg-btn.wocc")).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(2000);
		if (getDriver().findElement(By.cssSelector("li.reg-fld-recaptcha.reg-fld.fail")).isDisplayed()) {
			System.out.println("Pass for Registration page");
			pass(getDriver(), "Pass for Registration page");
		} else {
			System.out.println("Fail for Registration page");
			pass(getDriver(), "Fail for Registration page");
		}
	}

	public static void AllLoginMethods(WebDriver driver, String expectedText, String actualText, WebElement emailField,
			String emailAddress, WebElement passField, String password, WebElement loginButton, String printMsg)
			throws Exception {
		MyProfileObj page = new MyProfileObj(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			if (expectedText.equalsIgnoreCase(actualText)) {
				// if(driver.findElement(By.cssSelector("a.reg-btn")).isDisplayed()) {
				emailField.clear();
				emailField.sendKeys(emailAddress);
				passField.clear();
				passField.sendKeys(password);
				loginButton.click();
			} else {
				System.out.println("Login page is not present");
				fail(driver, "Login page is not present");
				return;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			error(driver, e.getMessage());
		}
		if (printMsg.equals("Twitter")) {
			if (driver.findElement(By.cssSelector("div.TopNav-title.u-pullLeft")).getText()
					.contains("Verify your identity")) {
				System.out.println("Need Authorisation for twitter");
				return;

			}

		}

		WebElement profileButton = driver.findElement(By.cssSelector("#logout_comp>li:nth-child(1)>a"));
		wait.until(ExpectedConditions.elementToBeClickable(profileButton));
		try {
			if (profileButton.isDisplayed()) {
				System.out.println("User is Logged in by " + printMsg);
				pass(driver, "User is Logged in by " + printMsg);
				System.out.println("My Profile button is present");
				info(driver, "My Profile button is present");
				if (printMsg.equals("Direct")) {
					MyProfile(driver, profileButton);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			error(driver, e.getMessage());
		}
		WebElement logoutButton = driver.findElement(By.id("logout"));
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		if (logoutButton.isDisplayed()) {
			try {
				logoutButton.click();
				System.out.println(printMsg + " Login is Passed");
				pass(driver, printMsg + " Login is Passed");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println(printMsg + " Login is ***Failed***");
			fail(driver, printMsg + " Login is ***Failed***");
		}
		wait.until(ExpectedConditions.elementToBeClickable(page.login));
		if (page.login.isDisplayed()) {
			System.out.println("User Logout successfully");
			info(driver, "User Logout successfully");
		}

	}

	public static void MyProfile(WebDriver driver, WebElement profileButton) throws Exception {
		MyProfileObj page = new MyProfileObj(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		profileButton.click();
		info(driver, "My Profile is clicked");
		String profileUrl = driver.getCurrentUrl();
		WebElement editProfileButton = driver.findElement(By.cssSelector(".gr-btn.m-t-5"));
		if (editProfileButton.isDisplayed() && profileUrl.endsWith("dailymail.co.uk/registration/profile.html")) {
			System.out.println("Profile page is Present");
			pass(driver, "Profile page is Present");
		}
		try {
			editProfileButton.click();
			Thread.sleep(2000);
			WebElement editProfileText = driver.findElement(By.cssSelector(".ccow.f-18"));
			wait.until(ExpectedConditions.visibilityOf(editProfileText));
			assertEquals("Edit public profile", editProfileText.getText());
			info(driver, "edit profile page is present");
		} catch (Exception e) {
			e.printStackTrace();
			error(driver, e.getMessage());
		}
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// get current date time with Date()
		Date date = new Date();
		// Now format the date
		String todaysDate = dateFormat.format(date);
		String newChange = "New changes made on " + todaysDate;
		page.aboutMe.clear();
		page.aboutMe.sendKeys(newChange);
		page.confirmChange.click();
		wait.until(ExpectedConditions.visibilityOf(page.aboutMe));
		try {
			if (newChange.equals(page.aboutMe.getAttribute("value"))) {
				System.out.println("Edit Profile is Pass");
				pass(driver, "Edit profile is pass");
			} else {
				System.out.println("Edit Profile is ***FAIL***");
				fail(driver, "Edit Profile is ***FAIL***");
			}
		} catch (Exception e) {
			e.printStackTrace();
			error(driver, e.getMessage());
		}

		page.backtoMyprofile.click();
		Thread.sleep(2000);
		if (driver.getCurrentUrl().endsWith("dailymail.co.uk/registration/profile.html")) {
			if (driver.findElement(By.cssSelector("div.js-in")).getText().equals(newChange)) {
				System.out.println("New Change Present on Profile page");
				System.out.println("Back to MyProfile is PASS");
				info(driver, "New Change Present on Profile page");
				pass(driver, "Back to MyProfile is PASS");
			}

		} else {
			System.out.println("Back to MyProfile is ***FAIL***");
			fail(driver, "Back to MyProfile is ***FAIL***");
		}

		// ArrowFactor

		if (page.arrowFactorModule.isDisplayed()) {
			System.out.println("Arrow factor module is displayed");
			pass(driver, "Arrow factor module is displayed");
			try {
				Assert.assertTrue(page.arrowFactorHeading.getText().equalsIgnoreCase("ARROW FACTOR"));
				System.out.println("Arrow factor heading is: " + page.arrowFactorHeading.getText());
				info(driver, "Arrow factor heading is: " + page.arrowFactorHeading.getText());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} else {
			System.out.println("Arrow factor module is ***NOT*** displayed");
			fail(driver, "Arrow factor module is ***NOT*** displayed");
		}

		System.out.println("Total arrow factor tabs are: " + page.arrowFactorTabs.size());

		for (int c = 0; c < page.arrowFactorTabs.size(); c++) {
			WebElement afTab = page.arrowFactorTabs.get(c);
			String tabName = afTab.getText();
			System.out.println("Tab name is " + tabName);
			afTab.click();
			Thread.sleep(2000);
			if (c == 0) {
				try {
					Assert.assertTrue(driver.findElement(By.cssSelector("#arrow-factor-1>div>span")).getText()
							.equalsIgnoreCase("Comments in the last 24 hours:"));
					System.out.println(tabName + " passed");
					pass(driver, tabName + " passed");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			if (c == 1) {
				try {
					Assert.assertTrue(driver.findElement(By.cssSelector("#arrow-factor-2>div>span")).getText()
							.equalsIgnoreCase("Comments in the last 7 days:"));
					System.out.println(tabName + " passed");
					pass(driver, tabName + " passed");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			if (c == 2) {
				try {
					Assert.assertTrue(driver.findElement(By.cssSelector("#arrow-factor-3>div>span")).getText()
							.equalsIgnoreCase("Comments in the last 30 days:"));
					System.out.println(tabName + " passed");
					pass(driver, tabName + " passed");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			if (c == 3) {
				try {
					Assert.assertTrue(driver.findElement(By.cssSelector("#arrow-factor-4>div>span")).getText()
							.equalsIgnoreCase("Total number of comments:"));
					System.out.println(tabName + " passed");
					pass(driver, tabName + " passed");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

		}

		// Comments

		if (page.commentTitle.isDisplayed()) {
			System.out.println("Comment title is present " + page.commentTitle.getText());
			pass(driver, "Comment title is present " + page.commentTitle.getText());
		} else {
			System.out.println("comment module(title) is not present");
			fail(driver, "comment module(title) is not present");
		}

		ArrayList<String> ids = new ArrayList<String>();
		ids.add("comments-time-seven-days");
		ids.add("comments-time-one-year");
		ids.add("comments-time-archive");
		ids.add("comments-time-thirty-days");
		for (String id : ids) {
			String commentPeriodName = id;
			WebElement comment = driver.findElement(By.id(id));
			System.out.println(commentPeriodName);
			WaitObj.wait(driver, comment);
			comment.click();
			Thread.sleep(3000);
			je.executeScript("scroll(0,250);");

			if (driver.getPageSource().contains("User didn't add any comments during this time period.")) {
				System.out.println("No comments present in " + commentPeriodName);
			} else {
				try {
					System.out.println("Total comments in " + commentPeriodName + " " + page.totalComments.size());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
			Thread.sleep(2000);
			System.out.println("-----------------");
		}

		System.out.println("<<==~~~~~~~MyProfile Test Finished~~~~~~~==>>");
	}

	public static void loginClick(WebDriver driver) throws Exception {
		MyProfileObj page = new MyProfileObj(driver);
		driver.manage().deleteAllCookies();
		driver.get(dataReader.getBaseUrl() + "/home/index.html");
		Thread.sleep(2000);
		info(driver, driver.getCurrentUrl());
		CmpConsent.gdprConsent();
		// logger.log(LogStatus.INFO, "Homepage Open");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(page.login));
		page.login.click();
		info(driver, "Login is clicked");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".reg-btn-login")));
		info(driver, driver.getCurrentUrl());
	}

}
