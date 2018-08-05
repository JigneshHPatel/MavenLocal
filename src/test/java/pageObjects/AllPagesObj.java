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
 * @author jigneshkumarpatel
 * 
 *         Common Elements for all pages
 *
 */
public class AllPagesObj {

	public AllPagesObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Weather widget

	@FindBy(how = How.ID, using = "weather-wrapper")
	public WebElement weatherWidget;

	@FindBy(how = How.CSS, using = "#weather-wrapper>strong")
	public WebElement weatherDayandDate;

	@FindBy(how = How.XPATH, using = "//div[@id='weather-wrapper']/a/span")
	public List<WebElement> AllElementsOnWidget;

	@FindBy(how = How.XPATH, using = "//div[@id='weather-wrapper']/a/strong[1]")
	public WebElement currentTemp;

	@FindBy(how = How.XPATH, using = "//div[@id='weather-wrapper']/a/strong[2]")
	public WebElement futureTemp;

	// Mast Head

	@FindBy(how = How.CSS, using = "div.masthead ")
	public WebElement mastHead;

	@FindBy(how = How.CSS, using = "div.masthead>a>img")
	public WebElement mastHeadImgText;

	@FindBy(how = How.CSS, using = ".page-header.bdrgr2")
	public WebElement mastHeadAndMenu;

	// Page footer

	@FindBy(how = How.CSS, using = ".cnr-5.link-wocc>a")
	public WebElement footerBackToTop;

	@FindBy(how = How.CSS, using = "#footer-1>ul>li")
	public List<WebElement> footerMenuLinks;

	// Footer links e.g. Site map, topics, RSS etc.
	@FindBy(how = How.CSS, using = ".page-footer>a")
	public List<WebElement> footerDownMenuLinks;

	// Footer Legal links e.g contact us etc.
	@FindBy(how = How.CSS, using = "div.and-footer > p:nth-child(1)")
	public WebElement footerPublishedBy;

	@FindBy(how = How.CSS, using = "div.and-footer > p:nth-child(2)")
	public WebElement footerPartofDMText;

	@FindBy(how = How.CSS, using = "div.and-footer > a")
	public List<WebElement> footerLegalLinks;

	@FindBy(how = How.TAG_NAME, using = "h1")
	public WebElement canonicalHeading;

	@FindBy(how = How.XPATH, using = "//div[@class='news page-not-found cleared']/p[1]")
	public WebElement canonicalMessage;
	
	@FindBy(how = How.XPATH, using = "//div[@class='news page-not-found cleared']/p[1]/a")
	public WebElement canonicalGoBackLink;
	
	@FindBy(how = How.XPATH, using = "//div[@class='news page-not-found cleared']/p[2]/a")
	public WebElement canonicalSitemapLink;

	public boolean MastHeadIsDisplayed() {
		return mastHead.isDisplayed();
	}

	public boolean MastHeadImgTextIsPresent() {
		return mastHeadImgText.getAttribute("alt").contains("MailOnline");
	}

	public boolean MastHeadAndMenuIsDisplayed() {
		return mastHeadAndMenu.isDisplayed();
	}

	public boolean canonicalHeadingIsPresent() {
		return canonicalHeading.getText().equalsIgnoreCase("Let's try again ");
	}

	public boolean canonicalMessageIsPresent() {
		return canonicalMessage.getText().toLowerCase()
				.contains("Sorry, we can't find the page you are looking for. Would you like to go ");
	}
}
