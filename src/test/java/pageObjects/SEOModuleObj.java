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
 * @author jigneshkumarpatel Elements for SEO modules
 */
public class SEOModuleObj {
	public SEOModuleObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[@data-track-module='seo-articles^article-list-module']")
	public WebElement SEOModule;

	@FindBy(how = How.CSS, using = ".heading-3-j4x")
	public WebElement SEOHeading;

	@FindBy(how = How.CSS, using = ".article-1HIOK>a")
	public List<WebElement> SEOArticles;

	@FindBy(how = How.CSS, using = ".article-1HIOK>a>img")
	public List<WebElement> SEOArticleImages;

	public boolean SEOModuleIsDisplayed() {
		return SEOModule.isDisplayed();
	}

	public boolean AssertSEOHeading() {
		return SEOHeading.getText().contains("IN OTHER NEWS...");
	}

	public int TotalSEOArticles() {
		return SEOArticles.size();
	}
	
	public int TotalSEOImages() {
		return SEOArticleImages.size();
	}
}
