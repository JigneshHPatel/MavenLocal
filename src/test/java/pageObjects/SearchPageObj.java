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

import util.Urls;

/**
 * @author jigneshkumarpatel
 * elements for Search page
 */
public class SearchPageObj {

	public SearchPageObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = "input[name='searchPhrase']")
	public WebElement searchField;
	
	@FindBy(how = How.ID, using = "searchPhrase")
	public WebElement searchFieldOnSearchPage;
	
	@FindBy(how = How.CSS, using = "span.bing-logo")
	public WebElement searchButtonOnHomePage;
	
	@FindBy(how = How.CSS, using = "button[type='submit']")
	public WebElement searchButtonOnCanonicalPage;
	
	@FindBy(how = How.TAG_NAME, using = "h1")
	public WebElement searchHeading;
	
	@FindBy(how = How.CSS, using = ".sch-res-title")
	public List<WebElement> searchItemsInResult;
	
	@FindBy(how = How.CSS, using = ".sch-results")
	public WebElement searchPageModule;
	
	@FindBy(how = How.CSS, using = "input[value='article']")
	public WebElement ArticleFilterCheckbox;
	
	@FindBy(how = How.CSS, using = "input[value='video']")
	public WebElement VideoFilterCheckbox;
	
	@FindBy(how = How.XPATH, using = "//*[@class='searchNavigators cleared xogr1 ccox']/span[1]/button/span[1]")
	public WebElement FilterGoButton;
	
	@FindBy(how = How.CSS, using = "div.sch-res-content>span")
	public List<WebElement> searchItemsType;
	
	@FindBy(how = How.CSS, using = "input[value='Associated Press']")
	public WebElement AuthorAssociatedPress;
	
	@FindBy(how = How.CSS, using = "div.sch-result.home.cleared>div>h4>a")
	public List<WebElement> searchItemsAuthor;
	
	@FindBy(how = How.CSS, using = "input[value='news']")
	public WebElement NewsChannelCheckbox;
	
	@FindBy(how = How.CSS, using = "input[value='Instagram']")
	public WebElement InstagramTopicCheckbox;
	
	@FindBy(how = How.XPATH, using = "//h2[@class='sch-res-section cleared link-wocc']/a")
	public List<WebElement> searchItemsChannel;
	
	@FindBy(how = How.CSS, using = "input[value='today']")
	public WebElement TodayCheckbox;
	
	@FindBy(how = How.XPATH, using = "//*[text()=' ADVANCED SEARCH FILTERS']")
	public WebElement AdvanceSearchHeading;
	
	@FindBy(how = How.CSS, using = ".sch-res-content>h4")
	public List<WebElement> searchItemsDate;
	
	@FindBy(how = How.CSS, using = "div.searchnavigator.searchnavigator-first.cleared")
	public WebElement ChannelModule;
	
	@FindBy(how = How.CSS, using = "#Channel>li>input")
	public List<WebElement> allChannelCheckBoxes;

	
	public String SortBy(int i) {
		return "//ul[@class='sch-sort']/li["+i+"]/input";
	}
	
	public boolean isAdvanceSearchHeadingPresent() {
		return AdvanceSearchHeading.getText().equalsIgnoreCase("ADVANCED SEARCH FILTERS");
	}
	
	public boolean isChannelModuleDisplayed() {
		return ChannelModule.isDisplayed();
	}
	
	public boolean searchFieldIsContainsSearchWord() {
		return Urls.search.equals(searchField.getAttribute("value"));
	}
	
	public boolean searchHeadingIsDisplayed() {
		return searchHeading.isDisplayed();
	}
	
	public boolean searchPageModuleIsDisplayed() {
		return searchPageModule.isDisplayed();
	}
	
}
