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
 * @author jigneshkumarpatel Poll on Channel and Article elements
 */
public class PollObj {

	public PollObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[contains(@id,'poll-share')]")
	public WebElement Poll;

	@FindBy(how = How.CSS, using = ".bdrcc.poll-img")
	public WebElement PollImage;

	@FindBy(how = How.CSS, using = ".poll-question")
	public WebElement PollQuestion;

	@FindBy(how = How.CSS, using = "div.poll-answer>label")
	public List<WebElement> PollAnswers;

	@FindBy(how = How.XPATH, using = "//*[@method='post']/div[1]/label")
	public WebElement PollFirstQuestion;

	// Total Answers post present after voting on poll
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'poll-results cleared')]/ul/li")
	public List<WebElement> PollAnswersAfterVote;
	
	private static final String stringPollAnswersAfterVote = "//*[contains(@class,'poll-results cleared')]/ul/li";

	@FindBy(how = How.CSS, using = ".dms-poll")
	public WebElement PollSocialshareModule;

	@FindBy(how = How.CSS, using = ".dms-poll>p")
	public WebElement PollSocialshareHeading;

	@FindBy(how = How.CSS, using = ".dms-poll>ul>li")
	public List<WebElement> TotalSocialShares;

	public boolean PollIsDisplayed() {
		return Poll.isDisplayed();
	}

	public boolean PollImageIsDisplayed() {
		return PollImage.isDisplayed();
	}

	public String PollQuestionText() {
		return PollQuestion.getText();
	}

	public String PollAnswerAfterVote(int p) {
		return stringPollAnswersAfterVote+ "[" + p + "]/span[1]";
	}

	public String PollAnswerPercentage(int p) {
		return stringPollAnswersAfterVote + "[" + p + "]/span[3]/span[1]";
	}

	public String PollAnswerVotes(int p) {
		return stringPollAnswersAfterVote + "[" + p + "]/span[3]/span[2]";
	}

	public boolean PollSocialShareModuleIsDisplayed() {
		return PollSocialshareModule.isDisplayed();
	}

	public boolean AssertPollSocialshareHeading() {
		return PollSocialshareHeading.getText().equalsIgnoreCase("Now share your opinion");
	}

}
