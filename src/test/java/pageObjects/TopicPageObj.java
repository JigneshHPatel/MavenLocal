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
 * @author jigneshkumarpatel Topic page elements
 */
public class TopicPageObj {

	public TopicPageObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.CSS, using = "#js-article-text>div>h2")
	public WebElement topicHeading;
	
	//TopicIndexA-ZLetters Module
	@FindBy(how = How.CSS, using = "#js-article-text > div > div:nth-child(3) > div")
	public WebElement topicIndexLetters;
	
	//All Topic Names/links on page
	@FindBy(how = How.CSS, using = ".column>a")
	public List<WebElement> TotalTopicNames;

	@FindBy(how = How.CSS, using = ".ccolcc.topicLetter>a")
	public List<WebElement> TotalTopicIndexA_Z;
	
	@FindBy(how = How.CSS, using = "div.ccolcc.topicTeam > a")
	public WebElement topicIndexTeamPages;
	
	public boolean topicHeadingIsPresent() {
		return topicHeading.getText().equalsIgnoreCase("opics Index - A to Z");
	}

	public boolean isAtoZLettersDisplayed() {
		return topicIndexLetters.isDisplayed();
	}
}
