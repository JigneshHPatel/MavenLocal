/**
 * 
 */
package config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * @author jigneshkumar.patel
 * wait until elemet clickable
 */
public class WaitObj {
    static WebDriver driver;
    public static void wait(WebDriver driver, WebElement element){
        WebDriverWait wait=new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        
    }
}