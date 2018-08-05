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
 * @author jigneshkumarpatel SocialShare on all pages elements. Social site
 *         login elements.
 */
public class SocialshareObj {

	public SocialshareObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Login objects
	@FindBy(how = How.ID, using = "email")
	public WebElement facebookUsernameField;

	@FindBy(how = How.ID, using = "pass")
	public WebElement facebookPasswordField;

	@FindBy(how = How.CSS, using = "input[type='submit']")
	public WebElement facebookSubmitButton;

	@FindBy(how = How.CSS, using = "button[type='submit']>span")
	public WebElement facebookPostButton;

	@FindBy(how = How.CSS, using = "a.button.follow")
	public WebElement twitterFollowButton;

	@FindBy(how = How.ID, using = "status")
	public WebElement twitterShareArticleTitleField;

	@FindBy(how = How.ID, using = "username_or_email")
	public WebElement twitterUsernameField;

	@FindBy(how = How.ID, using = "password")
	public WebElement twitterPasswordField;

	@FindBy(how = How.CSS, using = "input[type='submit']") // "input[value='Log in and Tweet']")
	public WebElement TwitterSubmitButton;

	// Social share objects
	// Share module under search
	@FindBy(css = "ul.dms-puff>li")
	public List<WebElement> socialIconsUnderSearch;

	// Facebook share button on top left of page
	@FindBy(css = ".facebook-like-static")
	public WebElement facebookbutton;

	// New FB preview icons
	@FindBy(how = How.CSS, using = "li.facebook>button")
	public List<WebElement> newFbPreviewIcons;

	// All social icon symbol in article preview on Home page by clicking drawer
	// will expand and social icons will display
	@FindBy(how = How.CSS, using = ".gr3ox>a")
	public List<WebElement> ShareIconsOnArticlePreview;

	// After clicking share icon - Social share module (icons )on article preview on
	// home page
	@FindBy(how = How.CSS, using = "#dms-drawer>div")
	public WebElement shareicon_module_arti_pre;

	@FindBy(how = How.CSS, using = "#dms-drawer>div>ul>li")
	public List<WebElement> linksin_shareicon_module_arti_pre;

	// Social icon under Article title
	@FindBy(how = How.CSS, using = "#shareLinkTop>a")
	public WebElement sharebuttonontop;

	@FindBy(how = How.XPATH, using = "//*[@id='articleIconLinksContainer']/ul/li") // "//*[@id='articleIconLinksContainer'
																					// or @class='wrapper']/ul/li")
	public List<WebElement> SocialIconsArticleTop;

	// Shareicon bottom
	@FindBy(how = How.CSS, using = ".social-links.cleared>ul>li")
	public List<WebElement> social_links_bottom;

	@FindBy(how = How.CSS, using = "#shareLinkBottom>a")
	public WebElement sharelinkbottom;

	// Fb icon on fly-out
	@FindBy(how = How.CSS, using = "div.button-share-facebook")
	public WebElement FbIconFlyout;

	// Related Module
	@FindBy(how = How.CSS, using = ".rotator-panels.link-bogr1.linkro-ccox")
	public WebElement relatedmodule;

	// FB icon on related
	@FindBy(how = How.CSS, using = "div.fb>div.share")
	public WebElement fbicon_onrelated;

	// Social share module on comment section
	@FindBy(how = How.XPATH, using = "//*[@class='rc-title']/following::div[2]/div[2]/div[1]/div[2]")
	public WebElement comment_socialshare;

	@FindBy(how = How.XPATH, using = "//div[@id='js-comments']/div[1]//*[contains(@class,'ShareComment')]")
	public List<WebElement> socialIconsOnComment;

	public String SocialIconsOnArticlePreview(int i) {
		return "//div[@id='dms-drawer']/div/ul/li[" + i + "]";
	}

	public String SocialIconsUnderSearch(int i) {
		return "//ul[@class='dms-puff']/li[" + i + "]";
	}

	public String SocialIconsInDrawer(int i) {
		return "//div[@class='wrapper']/ul/li[" + i + "]";
	}

	public String SocialIconsUnderArticleHeading(int i) {
		return "//div[@id='articleIconLinksContainer']/ul/li[" + i + "]";
	}

	public String SocialIconsUnderArticleBottom(int i) {
		return "//ul[@class='bigLinks sc-icns cleared']/li[" + i + "]";
	}

	public String SocialIconOnComment(int i) {
		return "//div[@id='js-comments']/div[1]//*[contains(@class,'ShareComment')][" + i + "]";
	}

}
