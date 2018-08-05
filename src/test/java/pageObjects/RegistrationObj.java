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
 * @author jigneshkumarpatel elements for login and my profile page
 */
public class RegistrationObj {
	public RegistrationObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = "login")
	public WebElement LoginButtonOnHomepage;

	@FindBy(how = How.ID, using = "logout")
	public WebElement LogoutButton;

	@FindBy(how = How.CSS, using = "button[type='submit']")
	public WebElement LoginButtonOnLoginpage;

	@FindBy(how = How.CSS, using = ".lcolumn>h3")
	public WebElement DirectLoginHeading;

	@FindBy(how = How.ID, using = "reg-lbx-email-page")
	public WebElement DirectLoginEmailField;

	@FindBy(how = How.ID, using = "reg-lbx-password-page")
	public WebElement DirectLoginPasswordField;

	@FindBy(how = How.CSS, using = "input[value='facebook']")
	public WebElement FBLoginButtonOnLoginpage;

	@FindBy(how = How.CSS, using = "#header_block>span")
	public WebElement FacebookLoginHeading;

	@FindBy(how = How.ID, using = "email")
	public WebElement FacebookLoginEmailField;

	@FindBy(how = How.ID, using = "pass")
	public WebElement FacebookLoginPasswordField;

	@FindBy(how = How.ID, using = "loginbutton")
	public WebElement FacebookLoginSubmitButton;

	@FindBy(how = How.CSS, using = "input[value='twitter']")
	public WebElement TwitterLoginButtonOnLoginpage;

	@FindBy(how = How.CSS, using = ".auth>h2")
	public WebElement TwitterLoginHeading;

	@FindBy(how = How.ID, using = "username_or_email")
	public WebElement TwitterLoginEmailField;

	@FindBy(how = How.ID, using = "password")
	public WebElement TwitterLoginPasswordField;

	@FindBy(how = How.ID, using = "allow")
	public WebElement TwitterLoginSubmitButton;

	@FindBy(how = How.CSS, using = "div.TopNav-title.u-pullLeft")
	public WebElement TwitterAuthorisation;

	@FindBy(how = How.CSS, using = "#logout_comp>li:nth-child(1)>a")
	public WebElement MyProfileButton;

	@FindBy(how = How.XPATH, using = "//h3[text()='New to MailOnline?']")
	public WebElement RegistrationHeading;

	@FindBy(how = How.XPATH, using = "//div[@class='media']/div[2]/h4")
	public List<WebElement> RegistrationSubHeadings;

	@FindBy(how = How.CSS, using = ".reg-btn.wocc.reg-btn-join")
	public WebElement JoinNowButton;

	@FindBy(how = How.TAG_NAME, using = "h1")
	public WebElement RegistrationPageTitle;

	@FindBy(how = How.XPATH, using = "//li[contains(@class,'reg-fld')]/label")
	public List<WebElement> RegistrationFormLabels;

	@FindBy(how = How.ID, using = "reg-email")
	public WebElement RegistrationEmailField;

	@FindBy(how = How.ID, using = "reg-name")
	public WebElement RegistrationUserNameField;

	@FindBy(how = How.ID, using = "reg-password")
	public WebElement RegistrationPasswordField;

	@FindBy(how = How.ID, using = "reg-password-conf")
	public WebElement RegistrationConfirmPasswordField;

	@FindBy(how = How.ID, using = "reg-city")
	public WebElement RegistrationCityField;

	@FindBy(how = How.XPATH, using = "//label[@for='regTerms']")
	public WebElement RegistrationTermsField;

	@FindBy(how = How.CSS, using = "button.reg-btn.wocc")
	public WebElement RegistrationSubmitButton;

	@FindBy(how = How.CSS, using = "li.reg-fld-recaptcha.reg-fld.fail")
	public WebElement RegistrationCaptchaVerificationFail;

	@FindBy(how = How.CSS, using = ".gr-btn.m-t-5")
	public WebElement EditMyProfileButton;

	@FindBy(how = How.TAG_NAME, using = "h1")
	public WebElement EditProfileHeading;

	@FindBy(how = How.ID, using = "reg-about-me")
	public WebElement MyProfileAboutme;

	@FindBy(how = How.CSS, using = "button.reg-btn.wocc")
	public WebElement EditMyProfileConfirmChangeButton;

	@FindBy(how = How.CSS, using = "a.reg-btn")
	public WebElement BackToMyProfile;

	@FindBy(how = How.CSS, using = "div.js-in")
	public WebElement MyProfileAboutmeDescription;

	@FindBy(how = How.CSS, using = "h2.debate-page-header.box")
	public WebElement ReportAbuseHeading;

	@FindBy(how = How.ID, using = "complaint")
	public WebElement ReportAbuseComplaintField;

	@FindBy(how = How.CSS, using = ".reg-btn.reverse-wocc")
	public WebElement ReportAbuseBackButton;

	public String getDirectLoginHeadingText() {
		return DirectLoginHeading.getText();
	}

	public String getFacebookLoginHeadingText() {
		return FacebookLoginHeading.getText();
	}

	public String getTwitterLoginHeadingText() {
		return TwitterLoginHeading.getText();
	}

	public String getMyProfileAboutmeText() {
		return MyProfileAboutme.getAttribute("value");
	}

	public String getMyProfileAboutmeDescription() {
		return MyProfileAboutmeDescription.getText();
	}

	public String ReportAbuseFormLabels(int i) {
		return "//*[@id='abuse-details']/div[" + i + "]/label";
	}

	public String ReportAbuseFormFields(int i) {
		return "//*[@id='abuse-details']/div[" + i + "]/div";
	}

	public boolean DirectLoginHeadingIsPresent() {
		return getDirectLoginHeadingText().equals("Login");
	}

	public boolean TwitterAuthorisationIsPresent() {
		return TwitterAuthorisation.getText().contains("Verify your identity");
	}

	public boolean NewToRegistrationHeadingIsPresent() {
		return RegistrationHeading.getText().equalsIgnoreCase("New to MailOnline?");
	}

	public boolean RegistrationPageTitleIsPresent() {
		return RegistrationPageTitle.getText().equalsIgnoreCase("Thank you for choosing to join");
	}

	public boolean RegistrationCaptchaVerificationFailIsDisplayed() {
		return RegistrationCaptchaVerificationFail.isDisplayed();
	}

	public boolean EditProfileHeadingIsPresent() {
		return EditProfileHeading.getText().equals("Edit public profile");
	}

	public boolean ReportAbuseHeadingIsPresent() {
		return ReportAbuseHeading.getText().equalsIgnoreCase("Report abuse");
	}

}
