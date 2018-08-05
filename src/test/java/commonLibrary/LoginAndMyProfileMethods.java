/**
 * 
 */
package commonLibrary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.RegistrationObj;

/**
 * @author jigneshkumarpatel generic methods for all logins, my profile edit and
 *         registration
 */
public class LoginAndMyProfileMethods extends browsers.BeforeAfter {
	public static void AllLoginMethods(WebDriver driver, String expectedText, String actualText, WebElement emailField,
			String emailAddress, WebElement passField, String password, WebElement loginButton, String printMsg)
			throws Exception {
		RegistrationObj regObj = new RegistrationObj(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			if (expectedText.equalsIgnoreCase(actualText)) {
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
		}

		if (printMsg.equals("Twitter")) {
			try {
				regObj.TwitterAuthorisationIsPresent();
				System.out.println("Need Authorisation for twitter");
				pass(driver, "Twitter login page present");
				info(driver, "Need Authorisation for twitter");
				return;
			} catch (Exception e) {
			}

		}
		wait.until(ExpectedConditions.elementToBeClickable(regObj.MyProfileButton));
		try {
			if (regObj.MyProfileButton.isDisplayed()) {
				System.out.println("User is Logged in by " + printMsg + " AND My Profile button is present");
				pass(driver, "User is Logged in by " + printMsg + " AND My Profile button is present");
				if (printMsg.equals("Direct")) {
					MyProfile(driver);
				}
			}
		} catch (Exception e) {
			System.out.println("MyProfile button is ***NOT*** present");
			System.out.println(e.getMessage());
			fail(driver, "MyProfile button is ***NOT*** present");
		}
		// If logout button is displayed test is passed
		wait.until(ExpectedConditions.elementToBeClickable(regObj.LogoutButton));
		if (regObj.LogoutButton.isDisplayed()) {
			regObj.LogoutButton.click();
			System.out.println(printMsg + " Login is Passed");
			pass(driver, printMsg + " Login is Passed");
		} else {
			System.out.println(printMsg + " Login is ***Failed***");
			fail(driver, printMsg + " Login is ***Failed***");
		}
		wait.until(ExpectedConditions.elementToBeClickable(regObj.LoginButtonOnHomepage));
		if (regObj.LoginButtonOnHomepage.isDisplayed()) {
			System.out.println("User Logout successfully");
			info(driver, "User Logout successfully");
		}

	}

	public static void MyProfile(WebDriver driver) throws Exception {
		RegistrationObj regObj = new RegistrationObj(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		regObj.MyProfileButton.click();
		info(driver, "My Profile is clicked");
		String profileUrl = driver.getCurrentUrl();
		if (regObj.EditMyProfileButton.isDisplayed()
				&& profileUrl.endsWith("dailymail.co.uk/registration/profile.html")) {
			System.out.println("Profile page is Present");
			pass(driver, "Profile page is Present");
		}
		try {
			regObj.EditMyProfileButton.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(regObj.EditProfileHeading));
			regObj.EditProfileHeadingIsPresent();
			info(driver, "edit profile page is present");
		} catch (Exception e) {
			System.out.println("edit profile heading is ***NOT*** present");
		}

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// get current date time with Date()
		Date date = new Date();
		// Now format the date
		String todaysDate = dateFormat.format(date);
		String newChange = "New changes made on " + todaysDate;

		regObj.MyProfileAboutme.clear();
		regObj.MyProfileAboutme.sendKeys(newChange);
		regObj.EditMyProfileConfirmChangeButton.click();
		wait.until(ExpectedConditions.visibilityOf(regObj.MyProfileAboutme));
		try {
			newChange.equals(regObj.getMyProfileAboutmeText());
			System.out.println("Edit Profile is Pass");
			pass(driver, "Edit profile is pass");
		} catch (Exception e) {
			System.out.println("Edit Profile is ***FAIL***");
			fail(driver, "Edit Profile is ***FAIL***");
		}
		regObj.BackToMyProfile.click();
		Thread.sleep(2000);
		try {
			driver.getCurrentUrl().endsWith("dailymail.co.uk/registration/profile.html");
			regObj.getMyProfileAboutmeDescription().equals(newChange);
			System.out.println("New Change Present on Profile page");
			System.out.println("Back to MyProfile is PASS");
			info(driver, "New Change Present on Profile page");
			pass(driver, "Back to MyProfile is PASS");
		} catch (Exception e) {
			System.out.println("Back to MyProfile is ***FAIL***");
			fail(driver, "Back to MyProfile is ***FAIL***");
		}

		System.out.println("<<==~~~~~~~MyProfile Test Finished~~~~~~~==>>");
	}

}
