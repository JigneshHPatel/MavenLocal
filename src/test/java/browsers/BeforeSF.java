/**
 * 
 */
package browsers;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * @author jigneshkumarpatel
 *
 *This calss is before after annotation for SAFARI
 */
public class BeforeSF {
	public static WebDriver driver;	
	public static String line="<<==============================>>";
	
    @BeforeTest(alwaysRun=true)
	
	public void before() throws Exception{
		
		driver = new SafariDriver();
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		driver.manage().window().maximize();
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

