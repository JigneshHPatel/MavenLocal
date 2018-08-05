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
 * @author jigneshkumarpatel elements for puff list
 */
public class PuffListObj {

	public PuffListObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class='femail item']//div[@class='puff cleared']")
	public WebElement DontMissPuffModule;
	
	@FindBy(how = How.XPATH, using = "//div[@class='femail item']//div[@class='puff cleared']/h3")
	public WebElement DontMissPuffHeading;
	
	@FindBy(how = How.XPATH, using = "//div[@class='femail item']//div[@class='puff cleared']/ul/li/a")
	public List<WebElement> TotalDontMissPuffArticles;
	
	private static String TotalDontMissPuffArticlesXpath = "//div[@class='femail item']//div[@class='puff cleared']/ul/li";
	
	public String DontMissPuffArticleXpath(int i) {
		return TotalDontMissPuffArticlesXpath +"["+i+"]/a";
	}
	
	public String AllPuffArticlesXpath(int i) {
		return "//div[@class='femail item']/div/ul/li["+i+"]/a ";
	}
	
	public String getDontMissHeading() {
		return DontMissPuffHeading.getText();
	}
	
	public boolean isDontMissHeadingPresent(String puffHeading) {
		return DontMissPuffHeading.getText().equalsIgnoreCase(puffHeading);
	}
}
