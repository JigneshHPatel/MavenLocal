/**
 * 
 */
package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author jigneshkumarpatel Sport Page elements
 */
public class SportPageObj {
	public SportPageObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[@data-track-module='llg-1001803^puff']")
	public WebElement startsOfSportPuffModule;

	@FindBy(how = How.XPATH, using = "//*[text()='STARS OF SPORT']")
	public WebElement startsOfSportPuffHeading;

	@FindBy(how = How.XPATH, using = "//*[@data-track-module='llg-1001803^puff']/ul/li/a")
	public List<WebElement> startsOfSportPuffArticles;
	
	@FindBy(how = How.XPATH, using = "//*[@data-track-module='llg-62197409^puff']")
	public WebElement MoreStartsOfSportPuffModule;
	
	@FindBy(how = How.XPATH, using = "//*[text()='MORE STARS OF SPORT']")
	public WebElement MoreStartsOfSportPuffHeading;
	
	@FindBy(how = How.XPATH, using = "//*[@data-track-module='llg-62197409^puff']/ul/li/a")
	public List<WebElement> MoreStartsOfSportPuffArticles;
	
	@FindBy(how = How.XPATH, using = "//ul[@class='club-badges club-badges-big club-badges-2018-big cleared']")
	public WebElement TeamModule;
	
	@FindBy(how = How.XPATH, using = "//ul[@class='club-badges club-badges-big club-badges-2018-big cleared']/li/a")
	public List<WebElement> TotalTeamIcons;
	
	@FindBy(how = How.CSS, using = ".football-team-news")
	public WebElement TeamTransferNewsModule;
	
	@FindBy(how = How.CSS, using = ".team-blogs")
	public WebElement TeamBlog;
	
	@FindBy(how = How.CSS, using = ".item.football_team_web_news_wrapper")
	public WebElement NewsFromAroundtheWorld;

	@FindBy(how = How.XPATH, using = "//*[@ class='fixtures-tables']/ul/li[1]/a")
	public WebElement FixtureTab;
	
	@FindBy(how = How.XPATH, using = "//*[@ class='fixtures-tables']/ul/li[2]/a")
	public WebElement ResultTab;
	
	@FindBy(how = How.XPATH, using = "//*[@ class='fixtures-tables']/ul/li[3]/a")
	public WebElement TablesTab;
	
	@FindBy(how = How.XPATH, using = "//*[@ class='fixtures-tables']/ul/li[4]/a")
	public WebElement MatchZoneTab;
	
	@FindBy(how = How.XPATH, using = "//*[@id='dd']/div/div")
	public WebElement MatchZoneDropdownArrow;
	
	@FindBy(how = How.CSS, using = "div.fixtures-tables-main-panel")
	public WebElement MatchZoneTable;
	
	public String MatchLink(int i) {
		return "//*[@id='dd']/ul/li[" + i + "]/a";
	}

	public String FixtureTabs(int i) {
		return "//*[@ class='fixtures-tables']/ul/li["+i+"]/a";
	}
	
	public String getTeamIconLink(int i) {
		return "//ul[@class='club-badges club-badges-big club-badges-2018-big cleared']/li["+i+"]/a";
	}
	
	public String getstartsOfSportPuffHeading() {
		return startsOfSportPuffHeading.getText();
	}
	
	public String getMoreStartsOfSportPuffHeading() {
		return MoreStartsOfSportPuffHeading.getText();
	}
	
	public boolean TeamModuleIsDisplayed() {
		return TeamModule.isDisplayed();
	}
	
	public boolean TeamTransferNewsModuleIsDisplayed() {
		return TeamTransferNewsModule.isDisplayed();
	}
	
	public boolean TeamBlogIsDisplayed() {
		return TeamBlog.isDisplayed();
	}
	
	public boolean NewsFromAroundtheWorldIsDisplayed() {
		return NewsFromAroundtheWorld.isDisplayed();
	}
	
	public boolean MatchZoneTableIsDisplayed() {
		return MatchZoneTable.isDisplayed();
	}
}
