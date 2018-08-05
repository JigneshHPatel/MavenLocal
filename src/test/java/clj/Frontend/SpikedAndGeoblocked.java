/**
 * 
 */
package clj.Frontend;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonLibrary.WaitMethods;
import config.Retry;
import pageObjects.AllPagesObj;
import pageObjects.ArticlePageObj;
import pageObjects.SearchPageObj;
import util.DataFileReader;
import util.Urls;

/**
 * @author jigneshkumarpatel
 *
 */
public class SpikedAndGeoblocked extends browsers.BeforeAfter {

	@Test(retryAnalyzer = Retry.class, description = "Check spiked article does not open and redirect to canonical")
	public void a_SpikedArticle() throws InterruptedException {

		DataFileReader dataReader = new DataFileReader();
		AllPagesObj pageObj = new AllPagesObj(getDriver());
		SearchPageObj searchObj = new SearchPageObj(getDriver());

		String parentUrl = getDriver().getCurrentUrl();
		getDriver().get(Urls.baseurl + dataReader.getSpikedArticle());
		Thread.sleep(2000);
		WaitMethods.WaitUntilElementVisible(getDriver(), pageObj.canonicalHeading);
		pageObj.canonicalHeadingIsPresent();
		pageObj.canonicalMessageIsPresent();
		pass(getDriver(), "Canonical heading and message is present");
		pageObj.canonicalGoBackLink.click();
		Thread.sleep(2000);
		Assert.assertTrue(getDriver().getCurrentUrl().equals(parentUrl), " assertion failed for go back link");
		pass(getDriver(), "Go back link is passed");

		getDriver().get(Urls.baseurl + dataReader.getSpikedArticle());
		Thread.sleep(2000);
		WaitMethods.WaitUntilElementVisible(getDriver(), pageObj.canonicalHeading);
		pageObj.canonicalSitemapLink.click();
		Thread.sleep(2000);
		Assert.assertTrue(getDriver().getCurrentUrl().equals(Urls.baseurl + "/home/sitemap.html"),
				" assertion failed for sitemap link");
		pass(getDriver(), "Sitemap link is passed");

		Assert.assertTrue(searchObj.searchField.isDisplayed());
		Assert.assertTrue(searchObj.searchButtonOnCanonicalPage.isDisplayed(), "assertion failed for search button");
		pass(getDriver(), "search module is displayed");
	}

	@Test(retryAnalyzer = Retry.class, description = "Check geoblocked article does open in geo where its not blocked and redirect to canonical in geoblocked region")
	public void b_GeoblockedArticle() throws InterruptedException {

		DataFileReader dataReader = new DataFileReader();
		AllPagesObj pageObj = new AllPagesObj(getDriver());
		SearchPageObj searchObj = new SearchPageObj(getDriver());
		ArticlePageObj articleObj = new ArticlePageObj(getDriver());
		
		String parentUrl = getDriver().getCurrentUrl();
		getDriver().get(Urls.baseurl + dataReader.getUKGeoblockedArticle());
		Thread.sleep(2000);
		WaitMethods.WaitUntilElementVisible(getDriver(), pageObj.canonicalHeading);
		pageObj.canonicalHeadingIsPresent();
		pageObj.canonicalMessageIsPresent();
		pass(getDriver(), "Canonical heading and message is present");
		pageObj.canonicalGoBackLink.click();
		Thread.sleep(2000);
		Assert.assertTrue(getDriver().getCurrentUrl().equals(parentUrl), " assertion failed for go back link");
		pass(getDriver(), "Go back link is passed");

		getDriver().get(Urls.baseurl + dataReader.getSpikedArticle());
		Thread.sleep(2000);
		WaitMethods.WaitUntilElementVisible(getDriver(), pageObj.canonicalHeading);
		pageObj.canonicalSitemapLink.click();
		Thread.sleep(2000);
		Assert.assertTrue(getDriver().getCurrentUrl().equals(Urls.baseurl + "/home/sitemap.html"),
				" assertion failed for sitemap link");
		pass(getDriver(), "Sitemap link is passed");

		Assert.assertTrue(searchObj.searchField.isDisplayed());
		Assert.assertTrue(searchObj.searchButtonOnCanonicalPage.isDisplayed(), "assertion failed for search button");
		pass(getDriver(), "search module is displayed");
		
		//Geo blocked article for US
		getDriver().get(Urls.baseurl + dataReader.getUSGeoblockedArticle());
		Thread.sleep(3000);
		Assert.assertTrue(articleObj.articleTextsAreDisplayed());
	}

}
