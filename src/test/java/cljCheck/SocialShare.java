/**
 * 
 */
package cljCheck;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import browsers.DataFileReader;
import config.Retry;
import config.SocialShareObj;
import config.WaitObj;

/**
 * @author jigneshkumarpatel
 *
 *
 *         Social share icon on homepage,article preview, and article page.
 * @param <WebDriver>
 */

public class SocialShare extends browsers.BeforeAfter {

	private static DataFileReader dataReader = new DataFileReader();
	static Actions action = (Actions) getDriver();

	@Test(retryAnalyzer = Retry.class, description = "FB icon on top left of the page")
	public void a_FacebookLike_TopLeft_HomePage() throws Exception {
		System.out.println("------------------------------------------------");
		System.out.println("****FacebookLike_TopLeft_HomePage****");
		System.out.println("------------------------------------------------");
		SocialShareObj page = new SocialShareObj(getDriver());
		// Facebook Like button on top left of page
		getDriver().get(dataReader.getBaseUrl() + "/home/index.html");
		Thread.sleep(2000);
		info(getDriver(), getDriver().getCurrentUrl());

		if (page.facebookbutton.isDisplayed()) {
			System.out.println("Facebook Like button on top right of page is present");
			pass(getDriver(), "Facebook Like button on top right of page is present");
			String name = "facebook"; // page.facebookbutton.getAttribute("data-href");
			System.out.println("Button url is " + name);
			String parent = getDriver().getWindowHandle();
			try {
				// je.executeScript("arguments[0].click();", page.facebookbutton);
				page.facebookbutton.click();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				logger.log(LogStatus.ERROR, e.getMessage());
			}
			Thread.sleep(2000);
			ArrayList<String> child = new ArrayList<String>(getDriver().getWindowHandles());
			getDriver().switchTo().window(child.get(1));
			Thread.sleep(2000);
			try {
				if (getDriver().getCurrentUrl().endsWith("facebook.com/DailyMail")) {
					System.out.println("Pass for FB button on top left");
					pass(getDriver(), "Pass for FB button on top left");
				} else {
					System.out.println("FAIL for FB button on top left");
					fail(getDriver(), "FAIL for FB button on top left");
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				error(getDriver(), e.getMessage());
			} finally {
				if (child.size() > 1) {
					getDriver().close();
				}
				getDriver().switchTo().window(parent);
				Thread.sleep(2000);
			}

		} else {
			System.out.println("Facebook Like button on top right of page is NOT present");
			fail(getDriver(), "Facebook Like button on top right of page is NOT present");
		}

		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "Social share icons on article preview on home page, new FB preview and old previews")
	public void b_SocialShareArticlePreview() throws Exception {
		System.out.println("------------------------------------------------");
		System.out.println("****SocialShareArticlePreviewHomePage****");
		System.out.println("------------------------------------------------");
		// SocialShareObj page = new SocialShareObj(getDriver());
		getDriver().get(dataReader.getBaseUrl() + "/home/index.html");
		Thread.sleep(3000);
		JavascriptExecutor je = (JavascriptExecutor) getDriver();

		// New FB icon
		info(getDriver(), "new FB icon test");
		List<WebElement> newFBicon = getDriver().findElements(By.cssSelector("li.facebook>button"));
		System.out.println("Total New FB icons on Page are " + newFBicon.size());
		info(getDriver(), "Total New FB icons on Page are " + newFBicon.size());
		WebElement fbIcon = newFBicon.get(0);
		String parent = getDriver().getWindowHandle();
		fbIcon.click();
		Thread.sleep(2000);
		ArrayList<String> child = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(child.get(1));
		Thread.sleep(2000);
		try {
			if (getDriver().getCurrentUrl().contains("facebook")) {
				System.out.println("Pass for New FB icon");
				pass(getDriver(), "Pass for New FB icon");
			} else {
				System.out.println("Fail for New FB icon");
				fail(getDriver(), "Fail for New FB icon");
			}
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		} finally {
			if (child.size() > 1) {
				getDriver().close();
			}
			getDriver().switchTo().window(parent);
			Thread.sleep(2000);
		}

		// Share icon

		List<WebElement> allShareIcon = getDriver().findElements(By.cssSelector("li.gr3ox>a>span"));
		WebElement shareIcon = allShareIcon.get(0);
		Actions action = new Actions(getDriver());
		// action.moveToElement(shareIcon).build().perform();
		je.executeScript("arguments[0].scrollIntoView(true);", shareIcon);
		WaitObj.wait(getDriver(), shareIcon);
		je.executeScript("arguments[0].setAttribute('style', 'display: block;');", shareIcon);
		shareIcon.click();
		// je.executeScript("arguments[0].click()", shareIcon);
		Thread.sleep(2000);
		List<WebElement> slinkstop = getDriver().findElements(By.cssSelector("#dms-drawer>div>ul>li"));

		System.out.println("Top Social Share Icons are " + slinkstop.size());
		info(getDriver(), "Top Social Share Icons are " + slinkstop.size());

		for (WebElement social_icon : slinkstop) {
			String socialIconName = social_icon.getAttribute("class");
			System.out.println(socialIconName + "icon");
			info(getDriver(), socialIconName + "icon");
			if (socialIconName.contains("print")) {
				if (social_icon.isDisplayed()) {
					System.out.println(socialIconName + " is displayed");
					pass(getDriver(), socialIconName + " is displayed");
				} else {
					System.out.println(socialIconName + " is ***NOT*** displayed");
					fail(getDriver(), socialIconName + " is ***NOT*** displayed");
				}
				continue;
			}
			if (socialIconName.contains("email")) {
				if (social_icon.isDisplayed()) {
					System.out.println(socialIconName + " is displayed");
					pass(getDriver(), socialIconName + " is displayed");
				} else {
					System.out.println(socialIconName + " is ***NOT*** displayed");
					fail(getDriver(), socialIconName + " is ***NOT*** displayed");
				}
				continue;
			}
			String parent2 = getDriver().getWindowHandle();
			try {
				action.moveToElement(social_icon).build().perform();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.elementToBeClickable(social_icon));

			try {
				social_icon.click();
			} catch (Exception e) {
				e.printStackTrace();
				info(getDriver(), e.getMessage());
			}
			Thread.sleep(2000);
			ArrayList<String> child2 = new ArrayList<String>(getDriver().getWindowHandles());
			getDriver().switchTo().window(child2.get(1));
			Thread.sleep(2000);
			String currentUrl = getDriver().getCurrentUrl();

			try {
				if (currentUrl.toLowerCase().contains("dailymail")
						|| currentUrl.toLowerCase().contains(socialIconName)) {
					System.out.println("Test Pass for " + socialIconName);
					pass(getDriver(), "Test Pass for " + socialIconName);
				} else {
					System.out.println("Test Fail for " + socialIconName);
					fail(getDriver(), "Test Fail for " + socialIconName);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (child2.size() > 1) {
					getDriver().close();
				}
				getDriver().switchTo().window(parent2);
				Thread.sleep(2000);
			}

		}
		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "Social shares under search bar on homepage")
	public static void c_SocialShare_UnderSearch_HomePage() throws Exception {
		System.out.println("------------------------------------------------");
		System.out.println("****SocialShare_UnderSearch_HomePage****");
		System.out.println("------------------------------------------------");
		SocialShareObj page = new SocialShareObj(getDriver());

		// Social share module under search option
		getDriver().get(dataReader.getBaseUrl() + "/home/index.html");
		Thread.sleep(2000);
		info(getDriver(), getDriver().getCurrentUrl());
		try {
			((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,350);");
			Thread.sleep(2000);
			System.out.println("scrolled");
		} catch (Exception e) {
			System.out.println("***NOT*** scrolled");
			System.out.println(e.getMessage());
		}
		List<WebElement> searchSocialIcon = page.sociallinkshome;
		System.out.println("Total social icon under search module are " + searchSocialIcon.size());
		info(getDriver(), "Total social icon under search module are " + searchSocialIcon.size());
		config.SocialShareObj.socialShareArticle(getDriver(), searchSocialIcon);
		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "Social Shares under search on Article page")
	public void d_SocialShare_underSearch_Article() throws Exception {
		System.out.println("***Social Icons Under Search in Article ***");

		SocialShareObj page = new SocialShareObj(getDriver());
		getDriver().get(dataReader.getArticleUrl());
		// page.firstarticle.click();
		Thread.sleep(3000);
		info(getDriver(), getDriver().getCurrentUrl());
		try {
			((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,350);");
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Social share module under search option

		List<WebElement> sci = page.sociallinkshome;
		System.out.println("Article page Total social icon under search module are " + sci.size());
		info(getDriver(), "Article page Total social icon under search module are " + sci.size());
		config.SocialShareObj.socialShareArticle(getDriver(), sci);

		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "Social shares on top of article/ under headline")
	public void e_SocialShare_Top_Article() throws Exception {
		// Social icon under article heading
		System.out.println("------------------------------------------------");
		System.out.println("***Social Icons Under ArticleHeading***");
		System.out.println("------------------------------------------------");
		SocialShareObj page = new SocialShareObj(getDriver());
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		getDriver().get(dataReader.getArticleUrl());
		// page.firstarticle.click();
		Thread.sleep(3000);

		info(getDriver(), getDriver().getCurrentUrl());
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#shareLinkTop>a")));
		try {
			je.executeScript("arguments[0].scrollIntoView(true);",
					getDriver().findElement(By.cssSelector("#shareLinkTop>a")));
		} catch (Exception e) {
			e.printStackTrace();
			error(getDriver(), e.getMessage());
		}
		List<WebElement> slinkstop = page.articletopsociallinks;
		System.out.println("Top Social Share Icons are " + slinkstop.size());
		info(getDriver(), "Top Social Share Icons are " + slinkstop.size());
		config.SocialShareObj.socialShareArticle(getDriver(), slinkstop);

		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "Socialshares at bottom of Article")
	public void f_SocialShare_Bottom_Article() throws Exception {
		// Social icon bottom of article
		System.out.println("------------------------------------------------");
		System.out.println("***Social Icons at Bottom of Article***");
		System.out.println("------------------------------------------------");
		SocialShareObj page = new SocialShareObj(getDriver());
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		getDriver().get(dataReader.getArticleUrl());
		// page.firstarticle.click();

		Thread.sleep(3000);
		info(getDriver(), getDriver().getCurrentUrl());
		try {
			je.executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.id("shareLinkTop")));

			if ("Share or comment on this article".equalsIgnoreCase(page.social_links_title.getText())) {
				System.out.println(page.social_links_title.getText());
				info(getDriver(), page.social_links_title.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
			error(getDriver(), e.getMessage());
		}

		List<WebElement> social_link_bottom = page.social_links_bottom;
		System.out.println("Bottom Social Share Icons are " + social_link_bottom.size());
		info(getDriver(), "Bottom Social Share Icons are " + social_link_bottom.size());
		config.SocialShareObj.socialShareArticle(getDriver(), social_link_bottom);

		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "FB icon on flyout on Article page")
	public void g_FB_Icon_FlyOut_onArticle() throws Exception {
		System.out.println("------------------------------------------------");
		System.out.println("****FB_Icon_FlyOut_onArticle****");
		System.out.println("------------------------------------------------");
		// SocialShareObj page = new SocialShareObj(getDriver());
		getDriver().get(dataReader.getArticleUrl());
		Thread.sleep(3000);
		// page.firstarticle.click();
		String parent = getDriver().getWindowHandle();
		// je.executeScript("arguments[0].scrollIntoView(true);",
		// page.social_links_title);
		getDriver().findElement(By.cssSelector("a.comments-count.home")).click();
		Thread.sleep(3000);
		try {
			if (getDriver().findElement(By.cssSelector(".floater-19vuu")).isDisplayed()) {
				getDriver().findElement(By.cssSelector(".closeButton-1i2Y6")).click();

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			if (getDriver().findElement(By.cssSelector("span.vjs-flyout-close.vjs-flyout-button")).isDisplayed()) {
				getDriver().findElement(By.cssSelector("span.vjs-flyout-close.vjs-flyout-button")).click();
			}
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".button-share-facebook")));
			WebElement FBpopIcon = getDriver().findElement(By.cssSelector(".button-share-facebook"));
			if (FBpopIcon.isDisplayed()) {
				System.out.println("Flyout FB icon is displayed");
				pass(getDriver(), "Flyout FB icon is displayed");
				WaitObj.wait(getDriver(), FBpopIcon);
				Thread.sleep(2000);
				FBpopIcon.click();
				// je.executeScript("arguments[0].click();" , FBpopIcon );
			} else {
				System.out.println("Flyout FB icon is NOT displayed");
				pass(getDriver(), "Flyout FB icon is NOT displayed");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			error(getDriver(), e.getMessage());
		}
		Thread.sleep(3000);
		ArrayList<String> child = new ArrayList<String>(getDriver().getWindowHandles());
		if (child != null) {
			getDriver().switchTo().window(child.get(1));
			Thread.sleep(2000);
		}
		try {
			if (getDriver().getCurrentUrl().contains("facebook")) {
				System.out.println("FB on flyout is pass");
				pass(getDriver(), "FB on flyout is pass");
			} else {
				System.out.println("FB on flyout is FAIL");
				fail(getDriver(), "FB on flyout is FAIL");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			error(getDriver(), e.getMessage());
		} finally {
			if (child.size() > 1) {
				getDriver().close();
			}
			getDriver().switchTo().window(parent);
		}
		Thread.sleep(2000);
		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "FB icon on Related module")
	public void h_FB_Icon_on_RelatedAtrticle() throws Exception {
		System.out.println("------------------------------------------------");
		System.out.println("****FB_Icon_on_RelatedAtrticle****");
		System.out.println("------------------------------------------------");
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		// Actions action = new Actions(getDriver());

		SocialShareObj page = new SocialShareObj(getDriver());

		// page.firstarticle.click();
		getDriver().get(dataReader.getArticleUrl());
		Thread.sleep(3000);
		System.out.println(getDriver().getCurrentUrl());
		info(getDriver(), getDriver().getCurrentUrl());

		if (page.relatedarticle.isDisplayed()) {
			WebElement relatedmodule = getDriver()
					.findElement(By.cssSelector(".rotator-panels.link-bogr1.linkro-ccox"));
			try {
				je.executeScript("arguments[0].scrollIntoView(true);", relatedmodule);
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
			Thread.sleep(2000);
			System.out.println("Related article module is present");
			pass(getDriver(), "Related article module is present");

			// page.fbcount_onrelated.isDisplayed();
			String parent = getDriver().getWindowHandle();
			try {
				// action.moveToElement(page.fbicon_onrelated).build().perform();
				je.executeScript("arguments[0].click();", page.fbicon_onrelated);
				// page.fbicon_onrelated.click();
				Thread.sleep(2000);
				ArrayList<String> child = new ArrayList<String>(getDriver().getWindowHandles());
				getDriver().switchTo().window(child.get(1));
				if (getDriver().getCurrentUrl().contains("facebook.com")) {
					System.out.println("FB icon on Article is pass");
					pass(getDriver(), "FB icon on Article is pass");
					getDriver().close();
					getDriver().switchTo().window(parent);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error(getDriver(), e.getMessage());
			}

		} else {
			System.out.println("Related article module is NOT present");
			fail(getDriver(), "Related article module is NOT present");
		}
		System.out.println("Related finished");
		System.out.println(line);

	}

	// comment share
	@Test(retryAnalyzer = Retry.class, description = "Social share on comments")
	public void i_SocialShareOn_Comments() throws Exception {

		System.out.println("------------------------------------------------");
		System.out.println("****socialOnComments****");
		System.out.println("------------------------------------------------");
		// SocialShareObj page = new SocialShareObj(getDriver());

		Actions action = new Actions(getDriver());
		getDriver().get(dataReader.getArticleUrl());
		Thread.sleep(2000);
		// page.firstarticle.click();
		getDriver().findElement(By.cssSelector(".count-number")).click();
		WebElement comment = getDriver()
				.findElement(By.xpath("//*[@class='rc-title']/following::div[2]/div[2]/div[1]/div[2]"));
		try {
			((JavascriptExecutor) getDriver())
					.executeScript("arguments[0].setAttribute('style','visibility:visible;');", comment);
			Thread.sleep(2000);
		} catch (Exception e1) {
			System.out.println("Comment icons not visible");
		}
		try {
			action.moveToElement(comment).build().perform();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<WebElement> commentshare = getDriver().findElements(By.xpath(
				"//*[@class='rc-title']/following::div[2]/div[2]/div[1]/div[2]//*[contains(@class, 'ShareComment')]"));
		System.out.println("CommentShare module is " + commentshare.size());
		info(getDriver(), "CommentShare module is " + commentshare.size());
		socialiconclick(getDriver(), commentshare);
		System.out.println(line);

	}

	public static void socialiconclick(WebDriver driver, List<WebElement> eleList) throws Exception {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		Actions action = new Actions(getDriver());
		for (WebElement shareicon : eleList) {
			String socialname = shareicon.getAttribute("class");
			System.out.println(socialname);
			info(getDriver(), socialname);

			String parent = getDriver().getWindowHandle();
			try {
				je.executeScript("arguments[0].style.visibility='visible';", shareicon);
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
			try {
				action.moveToElement(shareicon).build().perform();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
			try {
				shareicon.click();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error(getDriver(), e.getMessage());
			}

			Thread.sleep(2000);
			ArrayList<String> child = new ArrayList<String>(getDriver().getWindowHandles());
			try {

				getDriver().switchTo().window(child.get(1));
				Thread.sleep(4000);
				if (socialname.equals("fShareComment")) {
					String facebook = socialname.replaceAll(socialname, "facebook");
					// System.out.println(facebook);
					if (getDriver().getCurrentUrl().contains(facebook)) {
						System.out.println("pass for " + facebook);
						pass(getDriver(), "pass for " + facebook);
					}
				} else if (socialname.equals("tShareComment")) {
					String twitter = socialname.replaceAll(socialname, "twitter");
					if (getDriver().getCurrentUrl().contains(twitter)) {
						System.out.println("pass for " + twitter);
						pass(getDriver(), "pass for " + twitter);
					}
				} else if (socialname.equals("pShareComment")) {
					String pinterest = socialname.replaceAll(socialname, "pinterest");
					if (getDriver().getCurrentUrl().contains(pinterest)) {
						System.out.println("pass for " + pinterest);
						pass(getDriver(), "pass for " + pinterest);
					}
				} else if (socialname.equals("gShareComment")) {
					String google = socialname.replaceAll(socialname, "google");
					if (getDriver().getCurrentUrl().contains(google)) {
						System.out.println("pass for " + google);
						pass(getDriver(), "pass for " + google);
					}
				} else {
					System.out.println("Fail for " + socialname);
					fail(getDriver(), "Fail for " + socialname);
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				fail(getDriver(), e.getMessage());
			}

			if (child.size() > 1) {
				getDriver().close();
			}
			getDriver().switchTo().window(parent);
			Thread.sleep(2000);
		}
	}
}
