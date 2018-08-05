package clj.Frontend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import pageObjects.SearchPageObj;
import util.Urls;
import util.Retry;

public class SearchPage extends browsers.BeforeAfter {

	public static void searchPage(WebDriver driver) throws Exception {
		SearchPageObj searchObj = new SearchPageObj(driver);

		// Typing search word in search box
		searchObj.searchField.clear();
		searchObj.searchField.sendKeys(Urls.search);
		try {
			searchObj.searchFieldIsContainsSearchWord();
		} catch (Exception e) {
			System.out.println("Search word is not typed in search field");
			System.out.println(e.getMessage());
			fail(driver, "Search word is not typed in search field");
		}

		// Click on search button
		searchObj.searchButtonOnHomePage.click();

		// Assertion
		try {
			searchObj.searchHeadingIsDisplayed();
			System.out.println("Search heading is present");
			pass(driver, "Search heading is present");
		} catch (Exception e) {
			System.out.println("Search heading is NOT present");
			fail(getDriver(), "Search heading is NOT present");
			System.out.println(e.getMessage());
		}

	}

	public static void searchResult(WebDriver driver, String msg) {
		SearchPageObj searchObj = new SearchPageObj(getDriver());
		System.out.println(msg + searchObj.searchItemsInResult.size());
		System.out.println("-----------");
	}

	@Test(retryAnalyzer = Retry.class, description = "Searching word from home page and assert visibility of articles in SearchPage")
	public void a_SearchResult() throws Exception {
		SearchPageObj searchObj = new SearchPageObj(getDriver());

		searchPage(getDriver());

		for (int i = 2; i <= 4; i++) {

			WebElement sortByBox = getDriver().findElement(By.xpath(searchObj.SortBy(i)));
			String sortBy = sortByBox.getAttribute("value");
			sortByBox.click();
			Thread.sleep(6000);

			try {
				searchObj.searchPageModuleIsDisplayed();
				System.out.println("search result is displayed in: " + sortBy);
				pass(getDriver(), "search result is displayed in: " + sortBy);
				searchResult(getDriver(), "Total articles in " + sortBy + " are: ");
			} catch (Exception e) {
				System.out.println("search result is NOT displayed in " + sortBy);
				fail(getDriver(), "search result is NOT displayed in " + sortBy);
				System.out.println(e.getMessage());
			}
		}
	}

	@Test(retryAnalyzer = Retry.class, description = "Advance search options by channel in search page")
	public void b_AdvanceSearchByChannel() throws Exception {
		SearchPageObj searchObj = new SearchPageObj(getDriver());
		searchPage(getDriver());

		// Advance search heading
		try {
			searchObj.isAdvanceSearchHeadingPresent();
			System.out.println("Advance search heading is present");
			pass(getDriver(), "Advance search heading is present");
		} catch (Exception e) {
			System.out.println("Advance search heading is ***NOT*** present");
			fail(getDriver(), "Advance search heading is ***NOT*** present");
		}

		// Channel
		if (searchObj.isChannelModuleDisplayed()) {
			System.out.println("Channel options module is present");
			pass(getDriver(), "Channel options module is present");
			List<WebElement> allChannels = searchObj.allChannelCheckBoxes;
			System.out.println("Total channel filters are " + allChannels.size());
			info(getDriver(), "Total channel filters are " + allChannels.size());
			for (WebElement channel : allChannels) {
				if (channel.isDisplayed()) {
					System.out.println(channel.getAttribute("value") + " is present");
					pass(getDriver(), channel.getAttribute("value") + " is present");
				}
			}
			// Select news channel checkbox
			searchObj.NewsChannelCheckbox.click();
			// Click on GO
			searchObj.FilterGoButton.click();
			Thread.sleep(3000);
			// Verify result contains news channel only
			List<WebElement> allArticles = searchObj.searchItemsChannel;
			int totalArticles = allArticles.size();
			System.out.println("Total articles are " + totalArticles);
			info(getDriver(), "Total articles are " + totalArticles);
			for (WebElement article : allArticles) {
				if (!article.getText().equalsIgnoreCase("News")) {
					System.out.println("Article " + (allArticles.indexOf(article) + 1) + " ***FAIL***");
					fail(getDriver(), "Article " + (allArticles.indexOf(article) + 1) + " ***FAIL***");
				}
			}
		}
	}

	@Test(retryAnalyzer = Retry.class, description = "Advance search options by Content Type in search page")
	public void c_AdvanceSearchByContentType() throws Exception {
		SearchPageObj searchObj = new SearchPageObj(getDriver());
		searchPage(getDriver());

		// Select Fact Box checkbox (Untick article and video)
		searchObj.ArticleFilterCheckbox.click();
		searchObj.VideoFilterCheckbox.click();

		// Click on GO
		searchObj.FilterGoButton.click();
		Thread.sleep(3000);

		// Verify result contains factboxes only
		int totalArticles = searchObj.searchItemsType.size();
		System.out.println("Total articles are " + totalArticles);
		info(getDriver(), "Total articles are " + totalArticles);
		for (WebElement article : searchObj.searchItemsType) {
			if (!article.getText().equalsIgnoreCase("Fact Box")) {
				System.out.println((searchObj.searchItemsType.indexOf(article) + 1) + "***FAIL***");
				fail(getDriver(), (searchObj.searchItemsType.indexOf(article) + 1) + "***FAIL***");
			}
		}
	}

	@Test(retryAnalyzer = Retry.class, description = "Advance search options by Author in search page")
	public void d_AdvanceSearchByAuthor() throws Exception {
		SearchPageObj searchObj = new SearchPageObj(getDriver());
		searchPage(getDriver());

		// Select Author
		searchObj.AuthorAssociatedPress.click();
		// Click on GO
		searchObj.FilterGoButton.click();
		Thread.sleep(3000);
		// Verify result contains Author
		int totalArticles = searchObj.searchItemsAuthor.size();
		System.out.println("Total articles are " + totalArticles);
		info(getDriver(), "Total articles are " + totalArticles);
		for (WebElement article : searchObj.searchItemsAuthor) {
			if (!article.getText().contains("Associated Press")) {
				System.out.println((searchObj.searchItemsAuthor.indexOf(article) + 1) + " ***FAIL***");
				fail(getDriver(), "Article " + (searchObj.searchItemsAuthor.indexOf(article) + 1) + " ***FAIL***");
			}
		}
	}

	@Test(retryAnalyzer = Retry.class, description = "Advance search options by Topic e.g. factbox in search page")
	public void e_AdvanceSearchByTopic() throws Exception {
		SearchPageObj searchObj = new SearchPageObj(getDriver());
		searchPage(getDriver());

		// Select Instagram checkbox
		searchObj.InstagramTopicCheckbox.click();

		// Click on GO
		searchObj.FilterGoButton.click();
		Thread.sleep(3000);

		// Verify result contains science channel only
		searchResult(getDriver(), "Total article displayed in Instagram are ");

	}

	@Test(retryAnalyzer = Retry.class, description = "Advance search options by Day in search page")
	public void f_AdvanceSearchByDay() throws Exception {
		SearchPageObj searchObj = new SearchPageObj(getDriver());
		searchPage(getDriver());
		DateFormat dateFormat = new SimpleDateFormat("MMMM d");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		String th;
		DateFormat dateFormat1 = new SimpleDateFormat(" yyyy");
		Date date2 = new Date();
		String currentDate2 = dateFormat1.format(date2);

		if (currentDate.endsWith("11")) {
			th = "th";
		}

		else if (currentDate.endsWith("12")) {
			th = "th";
		} else if (currentDate.endsWith("13")) {
			th = "th";
		} else if (currentDate.endsWith("1")) {
			th = "st";
		} else if (currentDate.endsWith("2")) {
			th = "nd";
		} else if (currentDate.endsWith("3")) {
			th = "rd";
		} else {
			th = "th";
		}
		String expectedDate = currentDate + th + currentDate2;
		// System.out.println("Expected date is: "+expectedDate);

		// clear search box
		searchObj.searchFieldOnSearchPage.clear();

		// Select Today checkbox
		searchObj.TodayCheckbox.click();

		// Click on GO
		searchObj.FilterGoButton.click();
		Thread.sleep(3000);

		// Verify result articles are Today's

		List<WebElement> allArticles = searchObj.searchItemsDate;
		int totalArticles = allArticles.size();
		System.out.println("Total articles are " + totalArticles);
		info(getDriver(), "Total articles are " + totalArticles);
		for (WebElement article : allArticles) {
			if (article.getText().contains(" - ")) {
				String[] dateString = article.getText().split(" - ");
				String rawDate = dateString[1];
				String[] formated = rawDate.split(",");
				String actualDate = formated[0];
				try {
					if (!actualDate.equalsIgnoreCase(expectedDate)) {
						System.out.println("Date not matching for  " + (allArticles.indexOf(article) + 1));
						System.out.println("expected: " + expectedDate + " actual: " + actualDate);
						warning(getDriver(), "Date not matching for  " + (allArticles.indexOf(article) + 1));
						info(getDriver(), "expected: " + expectedDate + " actual: " + actualDate);
					}
				} catch (Exception e) {
				}
			} else {
				continue;
			}
		}

	}

}
