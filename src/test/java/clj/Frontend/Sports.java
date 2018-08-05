package clj.Frontend;

import static org.testng.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import util.Urls;
import commonLibrary.WeatherWidget;
import pageObjects.AllPagesObj;
import pageObjects.SportPageObj;
import util.NewTabValue;
import util.Retry;
import commonLibrary.PageFooter;
import commonLibrary.PuffListMethod;
import commonLibrary.VerifyLinkTest;
import commonLibrary.WaitMethods;

/**
 * @author jigneshkumar.patel
 *
 *
 *         Sports Menu Nav Team pages, Matchzone
 *
 *
 *         New tab only works in windows for MAC need to change CONTROL to
 *         COMMAND for Newtab
 */

public class Sports extends browsers.BeforeAfter {

	public static void sportlink(WebDriver driver) throws Exception {
		WaitMethods.WaitUntilElementClickable(driver, driver.findElement(By.linkText("Sport")));
		driver.findElement(By.linkText("Sport")).click();
		Thread.sleep(3000);
		try {
			assertEquals(Urls.baseurl + "/sport/index.html", driver.getCurrentUrl());
			info(driver, driver.getCurrentUrl());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			error(driver, e.getMessage());
		}
	}

	// STARS OF SPORT Puff list
	@Test(retryAnalyzer = Retry.class, description = "START OF SPORT (1st) puff list on sport page")
	public void a_StartsOfSportPuff() throws Exception {
		SportPageObj sportObj = new SportPageObj(getDriver());
		sportlink(getDriver());

		String ExpectedPuffHeading = "STARS OF SPORT";

		PuffListMethod.puffListMethod(getDriver(), sportObj.startsOfSportPuffModule, sportObj.getstartsOfSportPuffHeading(),
				ExpectedPuffHeading, sportObj.startsOfSportPuffArticles);
	}

	// MORE START OF SPORTS Puff list
	@Test(retryAnalyzer = Retry.class, description = "MORE START OF SPORT (1st) puff list on sport page")
	public void b_MoreStartofSportsPuff() throws Exception {
		SportPageObj sportObj = new SportPageObj(getDriver());
		sportlink(getDriver());

		String ExpectedPuffHeading = "MORE STARS OF SPORT";

		PuffListMethod.puffListMethod(getDriver(), sportObj.MoreStartsOfSportPuffModule,
				sportObj.getMoreStartsOfSportPuffHeading(), ExpectedPuffHeading,
				sportObj.MoreStartsOfSportPuffArticles);
	}

	// Page Footer
	@Test(retryAnalyzer = Retry.class, description = "Page footer and bottom menu on sport page")
	public void c_Footer() throws Exception {

		sportlink(getDriver());
		PageFooter.footer(getDriver());

	}

	// Top Menu on page
	@Test(retryAnalyzer = Retry.class, description = "top menu (whole module .page-header.bdrgr2) and masthead of sport channel")
	public void d_TopMenuAndMastHead() throws Exception {
		AllPagesObj allObj = new AllPagesObj(getDriver());
		sportlink(getDriver());

		// TOPMENU
		System.out.println("====TOP MENU===");
		if (allObj.MastHeadAndMenuIsDisplayed()) {
			System.out.println("Page header and Menu is present");
			String infoMsg = "Page header and Menu is present";
			info(getDriver(), infoMsg);
		}

		// MastHEAD
		commonLibrary.MastHead.mastHead(getDriver());

	}

	// Weather
	@Test(retryAnalyzer = Retry.class, description = "Weather icons and info on sport page")
	public void e_weather() throws Exception {

		sportlink(getDriver());

		WeatherWidget.WeatherIcons(getDriver());

	}

	// sports sub-channel
	@Test(enabled = false, retryAnalyzer = Retry.class, description = "All sub channels e.g. football, premier league etc.")
	public void f_SubChannels() throws Exception {

		getDriver().manage().deleteAllCookies();
		Thread.sleep(3000);
		sportlink(getDriver());
		Thread.sleep(2000);

		AllMenuSubMenuNav.submenu(getDriver());

	}

	// Team pages
	@Test(retryAnalyzer = Retry.class, description = "click on each Team Pages and assert url")
	public void g_TeamPages() throws Exception {
		SportPageObj sportObj = new SportPageObj(getDriver());
		sportlink(getDriver());

		try {
			sportObj.TeamModuleIsDisplayed();
		} catch (Exception e) {
			System.out.println("Team Module is ***NOT*** displayed");
			fail(getDriver(), "Team Module is ***NOT*** displayed");
			return;
		}
		for (int i = 1; i <= sportObj.TotalTeamIcons.size(); i++) {
			WebElement teamIcon = getDriver().findElement(By.xpath(sportObj.getTeamIconLink(i)));
			String teamExpectedUrl = teamIcon.getAttribute("href");
			String teamName = teamIcon.getAttribute("title");

			System.out.println("===> " + teamName + "  " + (sportObj.TotalTeamIcons.indexOf(teamIcon) + 1) + "("
					+ sportObj.TotalTeamIcons.size() + ") <===");

			info(getDriver(), "===> " + teamName + " <===");

			WaitMethods.WaitUntilElementClickable(getDriver(), teamIcon);
			String tmparent = getDriver().getWindowHandle();

			teamIcon.sendKeys(NewTabValue.NewTab());
			Thread.sleep(2000);
			ArrayList<String> tmchild = new ArrayList<String>(getDriver().getWindowHandles());
			System.out.println("Total Window handles are " + tmchild.size());
			if (!tmchild.isEmpty()) {
				getDriver().switchTo().window(tmchild.get(1));
			} else {
				continue;
			}
			Thread.sleep(2000);

			String teamActualUrl = getDriver().getCurrentUrl();
			if (teamExpectedUrl.equalsIgnoreCase(teamActualUrl)) {
				System.out.println("Teampage is present for " + teamName);
				pass(getDriver(), "Teampage is present for " + teamName);

				try {
					sportObj.TeamTransferNewsModuleIsDisplayed();
					System.out.println("TEAM AND TRANSFER NEWS Module is present for " + teamName);
					pass(getDriver(), "TEAM AND TRANSFER NEWS Module is present for " + teamName);
				} catch (Exception e) {
					System.out.println("TEAM AND TRANSFER NEWS  Module is ***NOT*** present for " + teamName);
					warning(getDriver(), "TEAM AND TRANSFER NEWS Module is ***NOT*** present for " + teamName);
				}
				try {
					sportObj.TeamBlogIsDisplayed();
					System.out.println(teamName + " Team Blogs is present");
					pass(getDriver(), teamName + " Team Blogs is present");
				} catch (Exception e) {
					System.out.println(teamName + " Team Blogs is ***NOT*** present");
					warning(getDriver(), teamName + " Team Blogs is ***NOT*** present");
				}
				try {

					sportObj.NewsFromAroundtheWorldIsDisplayed();
					System.out.println(teamName + " News from around the world is present");
					pass(getDriver(), teamName + " News from around the world is present");
				} catch (Exception e) {
					System.out.println(teamName + " News from around the world is ***NOT*** present");
					warning(getDriver(), teamName + " News from around the world is ***NOT*** present");
				}
				System.out.println("-----------------------");
			} else {
				System.out.println("Team Page is ***NOT*** open(expected vs actual url not matching");
				System.out.println("Expected Url is " + teamExpectedUrl);
				System.out.println("Actual Url is " + teamActualUrl);
			}

			if (tmchild.size() > 1) {
				getDriver().close();
			}
			getDriver().switchTo().window(tmparent);
			Thread.sleep(2000);
		}

	}

	@Test(retryAnalyzer = Retry.class, description = "Match zone, Result module selecting first 2 options and check if renders")
	public void h_MatchZones() throws Exception {
		SportPageObj sportObj = new SportPageObj(getDriver());
		sportlink(getDriver());
		sportObj.FixtureTab.click();
		Thread.sleep(2000);
		for (int i = 1; i <= 18; i++) {
			List<String> matches = Arrays.asList("fixture", "results", "tables", "matchzone");
			if (i <= 2) {
				sportObj.MatchZoneDropdownArrow.click();
				WebElement linkText = getDriver().findElement(By.xpath(sportObj.MatchLink(i)));
				String link = linkText.getAttribute("href");
				String matchName = linkText.getText();
				getDriver().get(link);
				Thread.sleep(2000);
				int a = 1;
				for (String match : matches) {
					getDriver().findElement(By.xpath(sportObj.FixtureTabs(a))).click();
					Thread.sleep(2000);
					if (match.equals("fixture")) {
						Assert.assertTrue(getDriver().getCurrentUrl().contains(link));
					} else {
						Assert.assertEquals(getDriver().getCurrentUrl(), link + "#" + match);
					}
					try {
						sportObj.MatchZoneTableIsDisplayed();
						System.out.println("-->" + matchName + " is Pass for " + match.toUpperCase());
						pass(getDriver(), matchName + " is Pass for " + match.toUpperCase());
					} catch (Exception e) {
						System.out.println(matchName + "Fixture Table is not Displayed for " + match);
					}
					a++;
				}
				System.out.println("-----------------------");
			}
		}

	}

	// All elements and images
	@Test(enabled = false, retryAnalyzer = Retry.class, description = "http response of all images and links on sport page")
	public void i_AllElemets() throws Exception {

		sportlink(getDriver());

		Thread.sleep(3000);
		List<WebElement> wele = getDriver().findElements(By.tagName("a"));
		int we = wele.size();
		System.out.println("Total links are " + we);
		String infoMsg = "Total links are " + we;
		info(getDriver(), infoMsg);

		// images
		List<WebElement> weleim = getDriver().findElements(By.tagName("img"));
		int img = weleim.size();
		System.out.println("Total images are " + img);
		String imgInfo = "Total images are " + img;
		info(getDriver(), imgInfo);
		VerifyLinkTest.httpreq();

	}

}
