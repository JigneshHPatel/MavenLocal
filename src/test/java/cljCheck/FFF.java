/**
 * 
 */
package cljCheck;

import java.util.List;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import browsers.DataFileReader;
import config.FFFconfig;
import config.Retry;

/**
 * @author jigneshkumar.patel
 *
 *         Femail Fashion Finder on HomePage and FFF Channel
 *
 */

public class FFF extends browsers.BeforeAfter {

	private static DataFileReader dataReader = new DataFileReader();

	@Test(retryAnalyzer = Retry.class, description = "Femail Fashion Finder carousel on HomePage")
	public void a_FFFcasouselOnHomepage() throws Exception {
		System.out.println("-----------------------------------");
		System.out.println("****FFFcasouselOnHomepage****");
		System.out.println("-----------------------------------");

		getDriver().get(dataReader.getBaseUrl() + "/home/index.html");
		Thread.sleep(3000);
		System.out.println(getDriver().getCurrentUrl());
		info(getDriver(), getDriver().getCurrentUrl());

		FFFconfig.fffCarosel(getDriver());
	}

	@Test(retryAnalyzer = Retry.class, description = "Femail Fashion Finder carousel on US HomePage")
	public void b_FFFCarouselOnUSHomepage() throws Exception {
		System.out.println("--------------------------------------");
		System.out.println("****FFFCarouselOnUSHomepage****");
		System.out.println("--------------------------------------");

		getDriver().get(dataReader.getBaseUrl() + "/ushome/index.html");
		Thread.sleep(3000);
		System.out.println(getDriver().getCurrentUrl());
		info(getDriver(), getDriver().getCurrentUrl());

		FFFconfig.fffCarosel(getDriver());
	}

	@Test(retryAnalyzer = Retry.class, description = "Femail Fashion Finder carousel on FFF Channel Page")
	public void c_FFFCarouselOnFFFChannel() throws Exception {
		System.out.println("--------------------------------");
		System.out.println("****FFFCarouselOnFFFChannel****");
		System.out.println("--------------------------------");

		getDriver().get(dataReader.getBaseUrl() + "/home/index.html");
		getDriver().findElement(By.linkText("Fashion Finder")).click();
		Thread.sleep(3000);
		try {
			Assert.assertEquals(getDriver().getCurrentUrl(), dataReader.getBaseUrl() + "/femail/fashionfinder/index.html");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("**** " + getDriver().getCurrentUrl() + " ****");
		FFFconfig.fffCarosel(getDriver());

		System.out.println(line);
	}

	// Style news under FFF carousel
	@Test(retryAnalyzer = Retry.class, description = "Style news module under fffCarousel on FFF Channel Page")
	public void d_StyleNewsOnFFFChannel() throws Exception {
		System.out.println("-----------------------------------");
		System.out.println("****StyleNewsOnFFFChannel****");
		System.out.println("-----------------------------------");

		getDriver().get(dataReader.getBaseUrl() + "/femail/fashionfinder/index.html");
		Thread.sleep(3000);
		System.out.println(getDriver().getCurrentUrl());
		info(getDriver(), getDriver().getCurrentUrl());

		if (getDriver().findElement(By.cssSelector("h3.fff-style-news-header")).getText().equalsIgnoreCase("Stylenews")) {
			System.out.println("Stylenews heading is present");
			pass(getDriver(), "Stylenews heading is present");
			List<WebElement> styleNewsArticles = getDriver()
					.findElements(By.xpath("//h3[@class='fff-style-news-header']/following ::h2/a"));
			System.out.println("Total Articles in Style news are: " + styleNewsArticles.size());
			info(getDriver(), "Total Articles in Style news are: " + styleNewsArticles.size());
			for (WebElement styleArticle : styleNewsArticles) {
				if (styleArticle.isDisplayed()) {
					System.out.println((styleNewsArticles.indexOf(styleArticle) + 1) + " Article URL is Displayed");
					pass(getDriver(), (styleNewsArticles.indexOf(styleArticle) + 1) + " Article URL is Displayed");
				} else {
					System.out.println(
							(styleNewsArticles.indexOf(styleArticle) + 1) + " Article URL is ***NOT*** Displayed");
					fail(getDriver(), (styleNewsArticles.indexOf(styleArticle) + 1) + " Article URL is ***NOT*** Displayed");
				}
			}

		} else {
			System.out.println("Stylenews heading is ***NOT*** present");
			// warning(getDriver(), "Stylenews heading is ***NOT*** present");
		}
		System.out.println(line);

	}

	// More fashion finds
	@Test(retryAnalyzer = Retry.class, description = "More fashion finds module (infinite list) under fffCarousel after style module on FFF Channel Page")
	public void e_MoreFashionFindOnFFFChannel() throws Exception {
		System.out.println("-----------------------------------------");
		System.out.println("****MoreFashionFindOnFFFChannel****");
		System.out.println("-----------------------------------------");

		getDriver().get(dataReader.getBaseUrl() + "/femail/fashionfinder/index.html");
		Thread.sleep(3000);
		System.out.println(getDriver().getCurrentUrl());
		info(getDriver(), getDriver().getCurrentUrl());

		if (getDriver().findElement(By.cssSelector("div.fff-more-fashion-finds")).isDisplayed()) {
			System.out.println("FFF More Fashion Finds module is present");
			pass(getDriver(), "FFF More Fashion Finds module is present");
			try {
				Assert.assertTrue(getDriver().findElement(By.cssSelector("h3.fff-more-fashion-finds-header")).getText()
						.equalsIgnoreCase("Morefashion finds"));
				System.out.println("FFF More Fashion Finds heading is present");
				pass(getDriver(), "FFF More Fashion Finds heading is present");

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(getDriver().findElement(By.cssSelector("h3.fff-more-fashion-finds-header")).getText());
			}
			List<WebElement> moreFFFArticles = getDriver()
					.findElements(By.cssSelector("div.fff-more-fashion-finds>ul>li>div"));
			System.out.println("Total More FFF Articles are: " + moreFFFArticles.size());
			info(getDriver(), "Total More FFF Articles are: " + moreFFFArticles.size());
			for (WebElement moreFFFArticle : moreFFFArticles) {
				if (moreFFFArticle.isDisplayed()) {
					System.out.println((moreFFFArticles.indexOf(moreFFFArticle) + 1) + " is displayed");
					pass(getDriver(), (moreFFFArticles.indexOf(moreFFFArticle) + 1) + " is displayed");
				} else {
					System.out.println((moreFFFArticles.indexOf(moreFFFArticle) + 1) + " is ***NOT*** displayed");
					pass(getDriver(), (moreFFFArticles.indexOf(moreFFFArticle) + 1) + " is ***NOT*** displayed");
				}
			}

		} else {
			System.out.println("FFF More Fashion Finds module is ***NOT*** present");
			pass(getDriver(), "FFF More Fashion Finds module is ***NOT*** present");
		}
		System.out.println(line);

	}

	// SEARCH, FIND AND BUY
	@Test(retryAnalyzer = Retry.class, description = "SEARCH, FIND AND BUY module on FFF Channel Page")
	public void f_SearchOnFFFChannel() throws Exception {
		System.out.println("-----------------------------------");
		System.out.println("****SearchOnFFFChannel****");
		System.out.println("-----------------------------------");

		getDriver().get(dataReader.getBaseUrl() + "/femail/fashionfinder/index.html");
		Thread.sleep(3000);
		System.out.println(getDriver().getCurrentUrl());
		info(getDriver(), getDriver().getCurrentUrl());

		if (getDriver().findElement(By.cssSelector("div.fff-find-your-look")).isDisplayed()) {
			System.out.println("SEARCH, FIND AND BUY module is displayed");
			pass(getDriver(), "SEARCH, FIND AND BUY module is displayed");
			try {
				Assert.assertTrue(getDriver().findElement(By.cssSelector("div.fff-find-your-look>img")).getAttribute("alt")
						.equalsIgnoreCase("SEARCH, FIND AND BUY"));
				System.out.println("SEARCH, FIND AND BUY heading is present");
				pass(getDriver(), "SEARCH, FIND AND BUY heading is present");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Actual heading is "
						+ getDriver().findElement(By.cssSelector("div.fff-find-your-look>img")).getAttribute("alt"));
			}

		} else {
			System.out.println("SEARCH, FIND AND BUY module is ***NOT*** displayed");
			fail(getDriver(), "SEARCH, FIND AND BUY module is ***NOT*** displayed");
		}

		System.out.println(line);

	}

	// Trending Now
	@Test(retryAnalyzer = Retry.class, description = "TrendingNowOnFFFChannel module on FFF Channel Page")
	public void g_TrendingNowOnFFFChannel() throws Exception {
		System.out.println("-------------------------------------");
		System.out.println("****TrendingNowOnFFFChannel****");
		System.out.println("-------------------------------------");

		getDriver().get(dataReader.getBaseUrl() + "/femail/fashionfinder/index.html");
		Thread.sleep(3000);
		System.out.println(getDriver().getCurrentUrl());
		info(getDriver(), getDriver().getCurrentUrl());

		if (getDriver().findElement(By.cssSelector("div.fff-trending")).isDisplayed()) {
			System.out.println("Trending Now module is present");
			info(getDriver(), "Trending Now module is present");
			try {
				Assert.assertTrue(getDriver().findElement(By.cssSelector("div.fff-trending>h5")).getText()
						.equalsIgnoreCase("TRENDING NOW"));
				System.out.println("TRENDING NOW heading is present");
				pass(getDriver(), "TRENDING NOW heading is present");

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(getDriver().findElement(By.cssSelector("div.fff-trending>h5")).getText());
			}
			List<WebElement> trendingProducts = getDriver().findElements(By.cssSelector("div.fff-trending>ol>li"));
			System.out.println("Total Products in Trending Now are: " + trendingProducts.size());
			info(getDriver(), "Total Products in Trending Now are: " + trendingProducts.size());
			for (WebElement trendingProduct : trendingProducts) {
				if (trendingProduct.isDisplayed()) {
					System.out.println("Product " + (trendingProducts.indexOf(trendingProduct) + 1) + " is displayed");
					pass(getDriver(), "Product " + (trendingProducts.indexOf(trendingProduct) + 1) + " is displayed");
				} else {
					System.out.println(
							"Product " + (trendingProducts.indexOf(trendingProduct) + 1) + " is ***NOT*** displayed");
					fail(getDriver(),
							"Product " + (trendingProducts.indexOf(trendingProduct) + 1) + " is ***NOT*** displayed");
				}
			}

		} else {
			System.out.println("Trending Now module is ***NOT*** present");
			fail(getDriver(), "Trending Now module is ***NOT*** present");
		}

		System.out.println(line);
	}

	@Test(retryAnalyzer = Retry.class, description = "Femail Fashion Finder carousel on ArticlePage")
	public void h_FFFCarouselInArticle() throws Exception {
		System.out.println("---------------------------------");
		System.out.println("****FFFCarouselInArticle****");
		System.out.println("---------------------------------");

		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		getDriver().get(dataReader.getBaseUrl()
				+ "/tvshowbiz/article-5401363/Mia-Wasikowska-turns-heads-plunging-black-gown.html");
		Thread.sleep(3000);
		System.out.println(getDriver().getCurrentUrl());
		info(getDriver(), getDriver().getCurrentUrl());

		je.executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.cssSelector("#fff-inline")));
		if (getDriver().findElement(By.cssSelector("#fff-inline")).isDisplayed()) {
			System.out.println("FFF inline is present on article");
			pass(getDriver(), "FFF inline is present on article");
			if (getDriver().findElement(By.cssSelector("#fff-popup-crop>img")).isDisplayed()) {
				System.out.println("Big Preview Image is present");
				pass(getDriver(), "Big Preview Image is present");
			}
			if (getDriver().findElement(By.cssSelector("#fff-inline-image")).isDisplayed()
					&& getDriver().findElement(By.cssSelector("#fff-inline-image")).isEnabled()) {
				System.out.println("Small Preview Image is present AND clickable");
				pass(getDriver(), "Small Preview Image is present AND clickable");
			}
			if (getDriver().findElement(By.cssSelector(".fff-social-links")).isDisplayed()) {
				List<WebElement> socials = getDriver().findElements(By.cssSelector(".fff-social-links>ul>li"));
				for (WebElement socialEle : socials) {
					if (socialEle.isEnabled()) {
						System.out.println("Pass for " + socialEle.getAttribute("class"));
						pass(getDriver(), "Pass for " + socialEle.getAttribute("class"));
					}
				}
			} else {
				System.out.println("Social icons are not displayed");
				fail(getDriver(), "Social icons are not displayed");
			}
			if (getDriver().findElement(By.cssSelector(".fff-buybtn.fff-main-product")).isDisplayed()
					&& getDriver().findElement(By.cssSelector(".fff-buybtn.fff-main-product")).isEnabled()) {
				System.out.println("Buy button on main product is displayed and clikable");
				pass(getDriver(), "Buy button on main product is displayed and clikable");
			}

			if (getDriver().findElement(By.cssSelector(".fff-related-product-container>img")).isDisplayed()) {
				List<WebElement> moreProduct = getDriver()
						.findElements(By.cssSelector(".fff-related-product-container>img"));
				System.out.println("Total Products are " + moreProduct.size());
				info(getDriver(), "Total Products are " + moreProduct.size());
				for (WebElement moreProdImg : moreProduct) {
					if (moreProdImg.isDisplayed() && moreProdImg.isEnabled()) {
						System.out.println("More Products are displayed");
						pass(getDriver(), "More Products are displayed");
					}
				}
			}
		} else {
			System.out.println("FFF Inline is ***NOT*** present on article");
			fail(getDriver(), "FFF Inline is ***NOT*** present on article");
		}

		System.out.println(line);
	}

}