package config;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author jigneshkumar.patel
 *
 *Config file Most Shared Right Now on UK US home page
 */
public class MostSharedObj {
	public MostSharedObj(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
	@FindBy(xpath="//*[text()='Most Shared Right Now']") 
	public WebElement mostsharedtitle;
	@FindBy(how=How.XPATH,using="//*[@data-track-module='am-most_shared_articles^most_shared_articles']")
	public WebElement mostsharedmodule;
	@FindBy(css=".listHolder-3b2tc>a")
	public List<WebElement> linksinmsr;
	//@FindBy(how=How.XPATH,using="//*[@data-track-module='am-most_shared_articles^most_shared_articles']//a")
	
	
}