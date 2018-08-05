/**
 * 
 */
package clj.Frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import util.Urls;
import commonLibrary.CloseFlyouts;
import commonLibrary.SocialLoginsMethods;
import commonLibrary.WaitMethods;
import pageObjects.ArticlePageObj;
import pageObjects.SocialshareObj;
import util.Retry;

/**
 * @author jigneshkumarpatel
 *
 *
 *         Social share icon on homepage,article preview, and article page.
 * 
 */

public class SocialShare extends browsers.BeforeAfter {

	@Test(retryAnalyzer = Retry.class, description = "FB icon on top left of the page")
	public void a_FacebookLike_TopLeft_HomePage() throws Exception {
		SocialshareObj socialObj = new SocialshareObj(getDriver());
		// Facebook Like button on top left of page

		SocialLoginsMethods.CLickSocialSahreIconAndAssert(getDriver(), socialObj.facebookbutton, "facebook", true);

	}

	@Test(retryAnalyzer = Retry.class, description = "Social share icons on article preview on home page, new FB preview and old previews")
	public void b_NewFbIconOnArticlePreview() throws Exception {

		SocialshareObj socialObj = new SocialshareObj(getDriver());

		// New FB icon
		info(getDriver(), "new FB icon test");
		System.out.println("Total New FB icons on Page are " + socialObj.newFbPreviewIcons.size());
		info(getDriver(), "Total New FB icons on Page are " + socialObj.newFbPreviewIcons.size());

		SocialLoginsMethods.CLickSocialSahreIconAndAssert(getDriver(), socialObj.newFbPreviewIcons.get(0), "facebook", true);

	}

	@Test(retryAnalyzer = Retry.class, description = "Social share icons on article preview on home page, new FB preview and old previews")
	public void c_SocialShareArticlePreview() throws Exception {

		SocialshareObj socialObj = new SocialshareObj(getDriver());
		JavascriptExecutor je = (JavascriptExecutor) getDriver();

		// Old Share icons

		WaitMethods.WaitUntilElementClickable(getDriver(), socialObj.ShareIconsOnArticlePreview.get(0));
		je.executeScript("arguments[0].setAttribute('style', 'display: block;');",
				socialObj.ShareIconsOnArticlePreview.get(0));
		je.executeScript("arguments[0].click();", socialObj.ShareIconsOnArticlePreview.get(0));
		Thread.sleep(2000);
		System.out.println("Top Social Share Icons are " + socialObj.linksin_shareicon_module_arti_pre.size());
		for (int i = 1; i <= socialObj.linksin_shareicon_module_arti_pre.size(); i++) {
			WebElement socialIcon = getDriver().findElement(By.xpath(socialObj.SocialIconsOnArticlePreview(i)));
			String socialName = socialIcon.getAttribute("class").toLowerCase();
			if (socialName.contains("google")) {
				socialName = "google";
			}
			System.out.println(socialName);
			SocialLoginsMethods.CLickSocialSahreIconAndAssert(getDriver(), socialIcon, socialName, true);
			System.out.println("-----------");

		}
	}

	@Test(retryAnalyzer = Retry.class, description = "Social shares under search bar on homepage")
	public static void d_SocialShare_UnderSearch_HomePage() throws Exception {

		SocialshareObj socialObj = new SocialshareObj(getDriver());

		// Social share module under search option
		try {
			((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,350);");
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		for (int i = 1; i <= socialObj.socialIconsUnderSearch.size(); i++) {
			WebElement socialIcon = getDriver().findElement(By.xpath(socialObj.SocialIconsUnderSearch(i)));
			String socialName = socialIcon.getAttribute("class").toLowerCase();
			if (socialName.contains("google") || socialName.contains("gplus")) {
				socialName = "google";
			}
			System.out.println(socialName);
			SocialLoginsMethods.CLickSocialSahreIconAndAssert(getDriver(), socialIcon, socialName, true);
			System.out.println("-----------");
		}
	}

	@Test(retryAnalyzer = Retry.class, description = "Social Shares under search on Article page")
	public void e_SocialShare_underSearch_Article() throws Exception {
		SocialshareObj socialObj = new SocialshareObj(getDriver());

		getDriver().get(Urls.articleUrl);
		Thread.sleep(3000);
		try {
			((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,350);");
			Thread.sleep(2000);
		} catch (Exception e) {
		}

		// Social share module under search option on Article
		try {
			((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,350);");
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		for (int i = 1; i <= socialObj.socialIconsUnderSearch.size(); i++) {
			WebElement socialIcon = getDriver().findElement(By.xpath(socialObj.SocialIconsUnderSearch(i)));
			String socialName = socialIcon.getAttribute("class").toLowerCase();
			if (socialName.contains("google") || socialName.contains("gplus")) {
				socialName = "google";
			}
			System.out.println(socialName);
			SocialLoginsMethods.CLickSocialSahreIconAndAssert(getDriver(), socialIcon, socialName, false);
			System.out.println("-----------");
		}
	}

	@Test(retryAnalyzer = Retry.class, description = "Social shares on top of article/ under headline")
	public void f_SocialShare_Top_Article() throws Exception {

		SocialshareObj socialObj = new SocialshareObj(getDriver());
		JavascriptExecutor je = (JavascriptExecutor) getDriver();

		getDriver().get(Urls.articleUrl);
		Thread.sleep(3000);
		info(getDriver(), getDriver().getCurrentUrl());
		WaitMethods.WaitUntilElementVisible(getDriver(), socialObj.sharebuttonontop);
		try {
			je.executeScript("window.scrollBy(0,300)");
		} catch (Exception e) {
		}
		// Social icon under article heading
		// Icons outside drawer e.g. FB,TW
		for (WebElement socialIcon : socialObj.SocialIconsArticleTop) {
			String socialName = socialIcon.getAttribute("class").toLowerCase();
			if (socialName.equalsIgnoreCase("share-icons")) {
				continue;
			}
			String socialNames[] = socialName.split("-");
			String newSocialName = socialNames[1];

			if (newSocialName.contains("google") || newSocialName.contains("gplus")) {
				newSocialName = "google";
			}
			SocialLoginsMethods.CLickSocialSahreIconAndAssert(getDriver(), socialIcon, newSocialName, true);
		}

		// Icons in Drawer e.g.Digg
		SocialLoginsMethods.SocialIconInDrawerTest(getDriver(), socialObj.sharebuttonontop);
	}

	@Test(retryAnalyzer = Retry.class, description = "Socialshares at bottom of Article")
	public void g_SocialShare_Bottom_Article() throws Exception {

		SocialshareObj socialObj = new SocialshareObj(getDriver());
		JavascriptExecutor je = (JavascriptExecutor) getDriver();

		getDriver().get(Urls.articleUrl);
		Thread.sleep(3000);
		info(getDriver(), getDriver().getCurrentUrl());
		try {
			je.executeScript("arguments[0].scrollIntoView(true);", socialObj.sharelinkbottom);
			Thread.sleep(2000);
			je.executeScript("window.scrollBy(0, -200)");
		} catch (Exception e) {
		}
		CloseFlyouts.closeVideoPlayer(getDriver());
		// Social icon bottom of article
		for (int i = 1; i <= socialObj.social_links_bottom.size(); i++) {
			WebElement socialIcon = getDriver().findElement(By.xpath(socialObj.SocialIconsUnderArticleBottom(i)));
			String SocialName = socialIcon.getAttribute("class");
			if (SocialName.contains("share-icons") || SocialName.contains("social-stats social-stats-pipe")
					|| SocialName.contains("add-comment")) {
				continue;
			}
			String socialNames[] = SocialName.split("-");
			String newSocialName = socialNames[1];

			if (newSocialName.contains("google") || newSocialName.contains("gplus")) {
				newSocialName = "google";
			}
			SocialLoginsMethods.CLickSocialSahreIconAndAssert(getDriver(), socialIcon, newSocialName, false);
		}
		// Icons in Drawer e.g.Digg
		SocialLoginsMethods.SocialIconInDrawerTest(getDriver(), socialObj.sharebuttonontop);

	}

	@Test(retryAnalyzer = Retry.class, description = "FB icon on flyout on Article page")
	public void h_FB_Icon_FlyOut_onArticle() throws Exception {

		SocialshareObj socialObj = new SocialshareObj(getDriver());
		JavascriptExecutor je = (JavascriptExecutor) getDriver();

		getDriver().get(Urls.articleUrl);
		Thread.sleep(3000);

		je.executeScript("window.scrollBy(0,500);");
		Thread.sleep(3000);
		CloseFlyouts.closeVideoPlayer(getDriver());
		WaitMethods.WaitUntilElementClickable(getDriver(), socialObj.FbIconFlyout);
		SocialLoginsMethods.CLickSocialSahreIconAndAssert(getDriver(), socialObj.FbIconFlyout, "facebook", true);

	}

	@Test(retryAnalyzer = Retry.class, description = "FB icon on Related module")
	public void i_FB_Icon_on_RelatedAtrticle() throws Exception {

		SocialshareObj socialObj = new SocialshareObj(getDriver());
		JavascriptExecutor je = (JavascriptExecutor) getDriver();

		getDriver().get(Urls.articleUrl);
		Thread.sleep(2000);
		WaitMethods.WaitUntilElementVisible(getDriver(), socialObj.relatedmodule);
		je.executeScript("arguments[0].scrollIntoView(true);", socialObj.relatedmodule);
		Thread.sleep(1000);
		je.executeScript("window.scrollBy(null,-250);");
		WaitMethods.WaitUntilElementClickable(getDriver(), socialObj.fbicon_onrelated);
		SocialLoginsMethods.CLickSocialSahreIconAndAssert(getDriver(), socialObj.fbicon_onrelated, "facebook", false);

	}

	// comment share
	@Test(retryAnalyzer = Retry.class, description = "Social share on comments")
	public void j_SocialShareOn_Comments() throws Exception {

		SocialshareObj socialObj = new SocialshareObj(getDriver());
		ArticlePageObj articleObj = new ArticlePageObj(getDriver());
		JavascriptExecutor je = (JavascriptExecutor) getDriver();

		getDriver().get(Urls.articleUrl);
		Thread.sleep(2000);
		WaitMethods.WaitUntilElementClickable(getDriver(), articleObj.CommentIconTop);
		articleObj.CommentIconTop.click();
		Thread.sleep(4000);
		try {
			WaitMethods.WaitUntilElementVisible(getDriver(), articleObj.CommentModule);
		} catch (Exception e) {
		}
		je.executeScript("arguments[0].setAttribute('style','visibility:visible;');", socialObj.comment_socialshare);
		CloseFlyouts.closeVideoPlayer(getDriver());
		WaitMethods.WaitUntilElementVisible(getDriver(), socialObj.comment_socialshare);
		for (int i = 1; i <= socialObj.socialIconsOnComment.size(); i++) {
			WebElement socialIcon = getDriver().findElement(By.xpath(socialObj.SocialIconOnComment(i)));
			String socialName = socialIcon.getAttribute("class");
			if (socialName.equalsIgnoreCase("fShareComment")) {
				socialName = "facebook";
			} else if (socialName.equalsIgnoreCase("tShareComment")) {
				socialName = "twitter";
			} else if (socialName.equalsIgnoreCase("pShareComment")) {
				socialName = "pinterest";
			} else if (socialName.equalsIgnoreCase("gShareComment")) {
				socialName = "google";
			}
			SocialLoginsMethods.CLickSocialSahreIconAndAssert(getDriver(), socialIcon, socialName, true);
		}
	}
}
