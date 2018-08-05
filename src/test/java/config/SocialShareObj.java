/**
 * 
 */
package config;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


/**
 * @author jigneshkumarpatel
 *
 *
 *Elements for social share icons
 */
public class SocialShareObj extends browsers.BeforeAfter {
//static WebDriver driver;

//public static ExtentTest logger;

public static void socialShareArticle(WebDriver driver, List<WebElement>socialLinks) throws Exception {
	Actions action = new Actions(driver);
	//WebDriverWait wait=new WebDriverWait(driver, 30);
	JavascriptExecutor je= (JavascriptExecutor)driver;
	je.executeScript("window.scrollBy(0,800);");
	Thread.sleep(2000);
	
	for(WebElement social_icon : socialLinks) {
		String socialIconName = social_icon.getAttribute("class");
		System.out.println("Icon name is "+socialIconName);
		if(socialIconName.equals("share-email")) {
			if(social_icon.isEnabled()) {
				System.out.println(socialIconName+" is Enabled");
				pass(driver, socialIconName+" is Enabled");
			}
			continue;
		}
		if(socialIconName.equals("share-icons")) {
			//driver.findElement(By.id("shareLinkTop")).click();
			social_icon.click();
			List<WebElement>sharebutton_icon=driver.findElements(By.cssSelector(".wrapper>ul>li"));
			System.out.println("Icons in share button are "+sharebutton_icon.size());	
			info(driver, "Icons in share button are "+sharebutton_icon.size());
			//socialShareArticle(driver, sharebutton_icon);			
			continue;
		}
		if(socialIconName.equals("print")) {
			if(social_icon.isEnabled()) {
				System.out.println(socialIconName+" is Enabled");
				pass(driver, socialIconName+" is Enabled");
			}
			break;				
		}
		if(socialIconName.equals("social-stats social-stats-pipe")) {
			break;
		}
		if(socialIconName.equals("add-comment")) {
			break;
		}
		String parent = driver.getWindowHandle();
		String parentUrl = driver.getCurrentUrl();
		try {
			action.moveToElement(social_icon).build().perform();
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			WaitObj.wait(driver,social_icon);
			Thread.sleep(2000);
			social_icon.click();			
			//je.executeScript("arguments[0].click();", social_icon);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			warning(driver, e.getMessage());
			je.executeScript("arguments[0].click();", social_icon);
		}
			Thread.sleep(2000);
			ArrayList<String>child = new ArrayList<String>(driver.getWindowHandles());
			if(child.isEmpty()) {
				System.out.println("child window is not opened for "+social_icon);
				fail(driver,"child window is not opened for "+social_icon);
				continue;
			}
			driver.switchTo().window(child.get(1));
			Thread.sleep(2000);
			String currentUrl = driver.getCurrentUrl();
			if(!parentUrl.equals(currentUrl)) {
			if(socialIconName.equals("share-twitter")) {
				if(currentUrl.toLowerCase().contains("twitter")) {
					System.out.println("Test Pass for "+socialIconName);
					pass(driver,"Test Pass for "+socialIconName);
					pass(driver, "Test Pass for twitter");
					if(child.size()>1) {
						driver.close();
						}
					driver.switchTo().window(parent);
					Thread.sleep(2000);
					continue;
				}else {
					System.out.println("Test ***FAIL*** for "+socialIconName);
					fail(driver, "Test ***FAIL*** for "+socialIconName);
					if(child.size()>1) {
						driver.close();
						}
					driver.switchTo().window(parent);
					Thread.sleep(2000);
					continue;
				}
			}
			if(currentUrl.toLowerCase().contains("dailymail")||currentUrl.toLowerCase().contains(socialIconName)||currentUrl.toLowerCase().contains("google")) {
				System.out.println("Test Pass for "+socialIconName);
				pass(driver, "Test Pass for "+socialIconName);
				
			}else {
				System.out.println("Test ***FAIL*** for "+socialIconName);
				fail(driver, "Test ***FAIL*** for "+socialIconName);
				if(child.size()>1) {
					driver.close();
					}
				driver.switchTo().window(parent);
				Thread.sleep(2000);
				continue;
			}
		
		
		if(child.size()>1) {
			driver.close();
			}
			}
		driver.switchTo().window(parent);
		Thread.sleep(2000);
		System.out.println("..........");
	}
}
	public SocialShareObj(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
	
	//Share module under search
	@FindBy(css="ul.dms-puff>li")
	public List<WebElement> sociallinkshome;
	
	//Facebook share button on top left of page
	@FindBy(css=".facebook-like-static")
	public WebElement facebookbutton;
	
	//All social links in article preview on Home page
	@FindBy(how=How.CSS,using=".gr3ox>a")
	public List<WebElement> allshareinpage;
	
	//After clicking share icon - Social share module (icons )on article preview on home page
	@FindBy(how=How.CSS,using="#dms-drawer>div")
	public WebElement shareicon_module_arti_pre;
	
	@FindBy(how=How.CSS,using="#dms-drawer>div>ul>li>a")
	public List<WebElement> linksin_shareicon_module_arti_pre;
	
	
	//Articles links on homepage
	//@FindBy(how=How.XPATH,using="//h2[@class='linkro-darkred']/a[1]")
	@FindBy(how=How.CSS,using=".article.article-tri-headline>h2>a")
	public WebElement firstarticle;
	
	
	//@FindBy(how=How.XPATH,using="//*[@class='femail item']/div/ul/li[2]/a")
	@FindBy(how=How.CSS,using=".article.article-large cleared>h2>a")
	public WebElement secondarticle;
	
	//Social icon under Article title
	@FindBy(how=How.CSS,using="#shareLinkTop>a")
	public WebElement sharebuttonontop;
	
	@FindBy(how=How.XPATH,using="//*[@id='articleIconLinksContainer' or @class='wrapper']/ul/li")
	public List<WebElement> articletopsociallinks;
	
	//Social links at end of the article
	@FindBy(how=How.CSS,using=".social-links-title")
	public WebElement social_links_title;
	
	//Shareicon bottom
	@FindBy(how=How.CSS,using=".social-links.cleared>ul>li")
	public List<WebElement>social_links_bottom;
	
	@FindBy(how=How.CSS,using="#shareLinkBottom>")
	public WebElement sharelinkbottom;
	
	//Related Module
	@FindBy(how=How.CSS,using=".rotator-panels.link-bogr1.linkro-ccox")
	public WebElement relatedmodule;
	
	//FB  icon on related article
	@FindBy(how=How.XPATH,using="//*[@data-track-module='am-related_carousel^related_carousel']/div[2]")
	public WebElement relatedarticle;
	
	//share this article text on related article
	@FindBy(how=How.XPATH,using="//*[text()='Share this article']")
	public WebElement fbsharetext_onrelated;
	
	//FB share count on related 
	@FindBy(how=How.CSS,using=".share")
	public WebElement fbcount_onrelated;
	
	//FB icon on related
	@FindBy(how=How.CSS,using=".share>span")
	public WebElement fbicon_onrelated;
	
	//Related Article heading
	@FindBy(how=How.XPATH,using="//*[text()='RELATED ARTICLES']")
	public WebElement relatedarticleText;
	
	//Related Articles Links
	@FindBy(how=How.XPATH,using="///*[@data-track-module='am-related_carousel^related_carousel']/div[2]/ul[2]/li/a")
	public List<WebElement> relatedarticleLinks;
	
	//Social share module on comment section
	@FindBy(how=How.XPATH,using="//*[@class='rc-title']/following::div[2]/div[2]/div[1]/div[2]")
	public WebElement comment_socialshare;
	
	//Comments count under article header
	@FindBy(how=How.CSS,using=".count-text")
	public WebElement comments;
	
	//FB and Twitter links on comment
	@FindBy(how=How.XPATH,using="//*[@class='rc-title']/following::div[2]/div[2]/div[1]/div[2]/a")
	public List<WebElement>fbtw_comment;
	
	//Pinrest, g+ and sharable link on comment
	@FindBy(how=How.XPATH,using="//*[@class='rc-title']/following::div[2]/div[2]/div[1]/div[2]/span")
	public List<WebElement>pinrestˆgplus_comment;
	
	
	
}