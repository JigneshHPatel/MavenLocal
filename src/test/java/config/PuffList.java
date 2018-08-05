/**
 * 
 */
package config;


import static org.testng.Assert.*;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author jigneshkumarpatel
 * 
 * WebElement element = puff module(give xpath or css for whole module), 
 * String puffheading = expected puff heading text,
 * String puffhead = actual puff heading (retrieve by method),
 * List<WebElement> puffarticles = selector for all articles in module.
 *
 */
public class PuffList extends browsers.BeforeAfter{
//public static WebDriver driver;

public static void pufflist(WebDriver driver, WebElement element, String puffhead, List<WebElement> puffarticles,String puffheading) {
	String line="<<====**********====>>";
		try{
			if(element.isDisplayed()){
				try {
					assertEquals(puffheading,puffhead);	
					System.out.println(puffheading+" is Present");
					String passMsg = puffheading+" is Present";
					pass(driver,passMsg);
					System.out.println("Total "+puffheading+" "+puffarticles.size());
					String infoMsg="Total "+puffheading+" "+puffarticles.size();
					info(driver, infoMsg);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					String errorMsg = e.getMessage();
					error(driver, errorMsg);
					error(driver, e.getMessage());
				}
				for(int i=0;i<puffarticles.size();i++) {
					WebElement puffArticleLink =puffarticles.get(i);
					if(puffArticleLink.isDisplayed()&&puffArticleLink.isEnabled()) {
						if(i<2) {
							String expected = puffArticleLink.getAttribute("href");
							String printMsg= "Article "+(1+i);							
							NewTab.newtab(driver, puffArticleLink, expected, printMsg, 1);
							Thread.sleep(2000);
						}						
						
					}else {
						System.out.println(1+i+" Article is ***NOT*** displyed and clickable");
						fail(driver," Article is ***NOT*** displyed and clickable");
					}
				}
				}else {
					System.out.println(puffheading+" is NOT present");
					fail(driver, puffheading+" is NOT present");
				}
			}catch(Exception e){				
				System.out.println(e.getMessage());
				error(driver, e.getMessage());
				}	
		System.out.println(line);
		}



}
