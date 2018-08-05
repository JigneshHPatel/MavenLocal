/**
 * 
 */
package commonLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import commonLibrary.OpenNewTabMethod.newTabMethod;
import pageObjects.SocialshareObj;
import util.DataFileReader;
import util.SystemDateTime;

/**
 * @author jigneshkumarpatel Login for facebook, twitter Find email, password
 *         filed and passed if clicked submit String prntMsg = prnt + " shared
 *         on fb or tw"
 */
public class SocialLoginsMethods extends browsers.BeforeAfter {
	private static DataFileReader dataReader = new DataFileReader();

	public enum socialMethodType {
		facebook, twitter
	}

	public static boolean AssertDisplayed() {
		return true;
	}

	public static boolean AssertEnabled() {
		return true;
	}

	public static void SocialIconInDrawerTest(WebDriver driver, WebElement share_icon) throws Exception {
		SocialshareObj socialObj = new SocialshareObj(driver);

		// Click on share-icon to open drawer
		WaitMethods.WaitUntilElementClickable(driver, share_icon);
		share_icon.click();
		Thread.sleep(1000);

		// Click and open social icons from drawer
		for (int i = 1; i <= socialObj.linksin_shareicon_module_arti_pre.size(); i++) {
			WebElement drawerIcon = driver.findElement(By.xpath(socialObj.SocialIconsOnArticlePreview(i)));
			String name = drawerIcon.getAttribute("class").toLowerCase();

			CLickSocialSahreIconAndAssert(driver, drawerIcon, name, false);
		}
	}

	public static void CLickSocialSahreIconAndAssert(WebDriver driver, WebElement icon, String iconName,
			boolean IsLoginAndPost) throws Exception {
		String parent = driver.getWindowHandle();
		try {
			IsIconDisplayed(icon);
		} catch (Exception e) {
		}
		if (IsIconClickable(icon)) {
			System.out.println(iconName + " is clickable");
			pass(driver, iconName + " is clickable");
		}
		if (iconName.contains("print") || iconName.contains("email")) {

		} else {

			OpenNewTabMethod.NewTabMethod(driver, icon, null, null, newTabMethod.socialIcons);

			try {
				Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(iconName));
				System.out.println(iconName + " window opened");
				pass(driver, iconName + " window opened");
			} catch (Exception e) {
				System.out.println(iconName + " is not present in new tab");
				warning(driver, iconName + " is not present in new tab");
			}

			if (IsLoginAndPost && (iconName.equalsIgnoreCase("facebook") || iconName.equalsIgnoreCase("twitter"))) {
				if (iconName.equalsIgnoreCase("facebook")) {
					SocialLoginsMethods.SocialLoginMethod(driver, "", socialMethodType.facebook);
				} else if (iconName.equalsIgnoreCase("twitter")) {
					SocialLoginsMethods.SocialLoginMethod(driver, "", socialMethodType.twitter);
				}
			}
			if (driver.getWindowHandles().size() > 1) {
				driver.close();
			}
			driver.switchTo().window(parent);
			Thread.sleep(2000);
		}
	}

	public static boolean IsIconDisplayed(WebElement icon) {
		return icon.isDisplayed();
	}

	public static boolean IsIconClickable(WebElement icon) {
		return icon.isEnabled();
	}

	public static void SocialLoginMethod(WebDriver driver, String prntMsg, socialMethodType SocialMethodType)
			throws Exception {

		SocialshareObj socialObj = new SocialshareObj(driver);

		switch (SocialMethodType) {
		case facebook:
			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("www.facebook.com"));
			socialObj.facebookUsernameField.clear();
			socialObj.facebookUsernameField.sendKeys(dataReader.getFacebookUsername());
			socialObj.facebookPasswordField.clear();
			socialObj.facebookPasswordField.sendKeys(dataReader.getFacebookPassword());
			socialObj.facebookSubmitButton.click();
			Thread.sleep(2000);
			System.out.println("Logged into FB");
			pass(driver, "Logged into FB");

			if (currentUrl.contains("www.facebook.com/login")) {
				socialObj.facebookPostButton.click();
				Thread.sleep(2000);
				System.out.println(prntMsg + " shared on FB");
				pass(driver, prntMsg + " shared on FB");
			}

			break;

		case twitter:
			Assert.assertTrue(driver.getCurrentUrl().contains("twitter.com"));
			if (driver.getCurrentUrl().contains("https://twitter.com/intent/user")) {
				socialObj.twitterFollowButton.click();
				Thread.sleep(2000);
			} else {
				String sharefield = socialObj.twitterShareArticleTitleField.getAttribute("value");
				String sharelink = SystemDateTime.currentDateTime() + sharefield;
				socialObj.twitterShareArticleTitleField.clear();
				socialObj.twitterShareArticleTitleField.sendKeys(sharelink);
			}
			socialObj.twitterUsernameField.clear();
			socialObj.twitterUsernameField.sendKeys(dataReader.getTwitterUsername());
			socialObj.twitterPasswordField.clear();
			socialObj.twitterPasswordField.sendKeys(dataReader.getTwitterPassword());
			socialObj.TwitterSubmitButton.click();
			System.out.println(prntMsg + " shared on Twitter");
			pass(driver, prntMsg + " shared on Twitter");

			break;

		default:
			break;

		}

	}

}
