/**
 * 
 */
package browsers;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * @author jigneshkumarpatel
 *
 *This class is before after annotaion for FIREFOX
 */
public class BeforeFF {
	public static WebDriver driver;	
	public static String line="<<==============================================>>";
    @BeforeTest(alwaysRun=true)
	
	public void before() throws Exception{
    	
    	System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
		if(Url.os.contains("win")) {
			System.setProperty("webdriver.gecko.driver","c:\\jigneshkumar.patel\\Desktop\\Automation\\Jar\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(Url.os.contains("mac")){
		driver = new FirefoxDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//driver.get("http://www.dailymail.co.uk/home/index.html");
		Url.URL(driver);
		try{
			assertEquals("Home",driver.findElement(By.cssSelector(".h1-page-last-updated>h1")).getText());
			}catch(Exception e){
				System.out.println(e.getMessage());
				}
		System.out.println("<=======================>");
	}
	@AfterTest(alwaysRun=true)
	public void after(){
		driver.quit();
		}
	
	}

