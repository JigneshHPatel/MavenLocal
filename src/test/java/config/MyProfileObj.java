package config;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MyProfileObj {
	
	public MyProfileObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id = "login")
	public WebElement login;
	
	@FindBy(id = "reg-lbx-email-page")
	public WebElement username;
	
	@FindBy(id = "reg-lbx-password-page")
	public WebElement password;	
	
	@FindBy(css = "button.reg-btn")
	public WebElement loginButton;
	
	@FindBy(xpath = "/html/body/div[3]/div[2]/div[2]/div[2]/div/ul[2]/li[1]/a")
	public WebElement myProfilebtn;
	
	@FindBy(css = ".gr-btn")
	public WebElement editmyProfilebtn;
	
	@FindBy(id = "reg-about-me")
	public WebElement aboutMe;
	
	@FindBy(css = "button.reg-btn.wocc")
	public WebElement confirmChange;
	
	@FindBy(css = "a.reg-btn")
	public WebElement backtoMyprofile;
	
	@FindBy(id="arrow-factor")
	public WebElement arrowFactorModule;
	
	@FindBy(css="#arrow-factor>h2")
	public WebElement arrowFactorHeading;
	
	@FindBy(css="#arrow-factor>div>ul>li>a>span")
	public List<WebElement>arrowFactorTabs;
	
	@FindBy(css="h2.bogr2.commenter-name")
	public WebElement commentTitle;
	
	@FindBy(css="div.comments-wrapper>div>h4>a")
	public List<WebElement>commentWrapper;
	
	@FindBy(xpath="//*[@class='comments-user']/div[3]/div/p/a")
	public List<WebElement>totalComments;
	
}
