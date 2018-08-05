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
 *         Elements on Article page
 */
public class ArticlePageObj {

	public ArticlePageObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Related articles
	@FindBy(how = How.CSS, using = ".rotator-panels.link-bogr1.linkro-ccox")
	public WebElement relatedArticleModule;

	@FindBy(how = How.CSS, using = ".fb>h2")
	public WebElement fbsharetext_onrelated;

	@FindBy(how = How.XPATH, using = "//*[@data-track-module='am-related_carousel^related_carousel']/div[2]/div")
	public WebElement fbicon_onrelated;

	@FindBy(how = How.XPATH, using = "//*[@class='rotator-panels link-bogr1 linkro-ccox']/li/a")
	public List<WebElement> totalRelatedArticles;

	// Next button
	@FindBy(how = How.CSS, using = ".nextBtn-3HPLH")
	public WebElement nextButton;

	// Previous button
	@FindBy(how = How.CSS, using = "div.previousBtn-2T0ps")
	public WebElement previousButton;

	// fly-outs
	@FindBy(how = How.CSS, using = ".closeButton-1i2Y6")
	public WebElement jwPlayerCloseButton;

	@FindBy(how = How.CSS, using = ".vjs-flyout-close.vjs-flyout-button")
	public WebElement videoPlayerCloseButton;

	@FindBy(how = How.CSS, using = "img.closeIcon-2qSy0")
	public WebElement newsletterCloseButton;

	// Comment section
	@FindBy(how = How.CSS, using = "a.comments-count.home")
	public WebElement CommentIconTop;

	@FindBy(how = How.CSS, using = "p.rc-title.debate")
	public WebElement CommentStatusMessage;

	@FindBy(how = How.CSS, using = "p.count-number")
	public WebElement CommentCounts;

	@FindBy(how = How.ID, using = "js-comments")
	public WebElement CommentModule;

	@FindBy(how = How.CSS, using = "#reader-comments>div.rc-content >div.rc-title>p")
	public WebElement moderationMessage;

	// newest, oldest tabs
	@FindBy(how = How.CSS, using = "#rc-tabs>li>a")
	public List<WebElement> CommentTabs;

	@FindBy(how = How.CSS, using = "#js-comments>div")
	public List<WebElement> allCommets;

	@FindBy(how = How.CSS, using = "button.btn.anchor-reply-comment.js-reply")
	public List<WebElement> allReply;

	@FindBy(how = How.XPATH, using = "//*[@id='js-comments']/div[1]/div[2]/p[1]")
	public WebElement UserInfoOnComment;

	@FindBy(how = How.XPATH, using = "//*[@id='js-comments']/div[1]/div[2]/p[1]/a")
	public WebElement UserGeoOnComment;

	@FindBy(how = How.ID, using = "message")
	public WebElement CommentMessageField;

	@FindBy(how = How.CSS, using = "div.reply-form.textarea-holder.field")
	public WebElement ReplyMessageField;

	@FindBy(how = How.CSS, using = ".btn.btn-submit")
	public WebElement commentSubmitButton;

	@FindBy(how = How.CSS, using = ".btn.js-reply-submit")
	public WebElement replySubmitButton;

	@FindBy(how = How.CSS, using = "div.submission-feedback-message")
	public WebElement commentSubmissionMessage;

	@FindBy(how = How.CSS, using = ".submission-feedback-fbmessage")
	public WebElement FacebookCommentSubmissionMessage;

	@FindBy(how = How.CSS, using = "div.rate-up")
	public List<WebElement> allVoteUpButtons;

	@FindBy(how = How.CSS, using = "div.rate-down")
	public List<WebElement> allVoteDownButtons;

	// [0]is vote down and [1] is vote up
	@FindBy(how = How.CSS, using = "div.rating-button-inline")
	public List<WebElement> allVoteCounts;

	@FindBy(how = How.ID, using = "handlePostToFacebookInput")
	public WebElement postCommentToFacebookCheckbox;

	@FindBy(how = How.CSS, using = ".btn.fbConfirm")
	public WebElement yesButtonOnFacebookAlert;

	@FindBy(how = How.CSS, using = "#js-comments>div:nth-child(1)>a")
	public WebElement ReportAbuseFlag;
	
	@FindBy(how = How.ID, using = "js-article-text")
	public WebElement articleTexts;
	
	public String getCommentStatusMessage() {
		return CommentStatusMessage.getText();
	}

	public String getCommentSubmissionMessage() {
		return commentSubmissionMessage.getText();
	}

	public String getUserInfoOnComment() {
		return UserInfoOnComment.getText();
	}

	public String getUserGeoOnComment() {
		return UserGeoOnComment.getText();
	}

	public int TotalComments() {
		return allCommets.size();
	}

	public String getModerationMessage() {
		return moderationMessage.getText();
	}

	public String getRateCount(String voteMethod) {
		if (voteMethod.equalsIgnoreCase("voteUp")) {
			return allVoteCounts.get(1).getText();
		} else if (voteMethod.equalsIgnoreCase("voteDown")) {
			return allVoteCounts.get(0).getText();
		} else {
			return null;
		}
	}

	public String getRateCountAfter(String voteMethod) {
		if (voteMethod.equalsIgnoreCase("voteUp")) {
			return allVoteCounts.get(1).getText();
		} else if (voteMethod.equalsIgnoreCase("voteDown")) {
			return allVoteCounts.get(0).getText();
		} else {
			return null;
		}
	}

	public WebElement rateButton(String voteMethod) {
		if (voteMethod.equalsIgnoreCase("voteUp")) {
			return allVoteUpButtons.get(0);
		} else if (voteMethod.equalsIgnoreCase("voteDown")) {
			return allVoteDownButtons.get(1);
		} else {
			return null;
		}
	}

	public boolean commentCountIsNotNull() {
		return CommentCounts.getText().length() > -1;
	}

	public boolean moderationMessageIsDisplayed() {
		return moderationMessage.isDisplayed();
	}

	public static String getRelatedArticle(int i) {
		return "//*[@class='rotator-panels link-bogr1 linkro-ccox']/li/a[" + i + "]";
	}

	public boolean isMessagePresentInCommentField() {
		return CommentMessageField.getAttribute("value").contains("comment");
	}

	public boolean commentSubmissionMessageIsPresent() {
		return getCommentSubmissionMessage().contains("Thanks for sharing your comment");
	}
	
	public boolean articleTextsAreDisplayed() {
		return articleTexts.isDisplayed();
	}

}
