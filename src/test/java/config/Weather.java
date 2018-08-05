/**
 * 
 */
package config;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * @author jigneshkumar.patel
 *
 *
 *Weather displayed or not on page
 */
public class Weather extends browsers.BeforeAfter{
public static void Weathericon(WebDriver driver){
    if(driver.findElement(By.cssSelector("#weather-wrapper")).isDisplayed()){
        System.out.println("Weather banner is present");
        String passMsg= "Weather banner is present";
        pass(driver, passMsg);
        if(driver.findElement(By.cssSelector("#weather-wrapper>strong")).isDisplayed()){
        	String date ="Day and Date is Present  "+ driver.findElement(By.cssSelector("#weather-wrapper>strong")).getText();
        System.out.println(date);
        pass(driver,date);
        }else {
        	String nodate = "Day and Date is  not Present ";
        	System.out.println(nodate);
        	fail(driver,nodate);
        	}
        /*if(driver.findElement(By.xpath("//*[@id='weather-wrapper']/a/span[1]")).isDisplayed()){
            System.out.println("Time is present");
        }*/
        List<WebElement>we=driver.findElements(By.cssSelector("#weather-wrapper>a>span"));
        for(int i=0;i<we.size();i++){
            WebElement wele=we.get(i);
            if(wele.isDisplayed()){
            	String weather = wele.getText()+ "Weather icon is displayed";
                System.out.println(wele.getText()+ " is displayed");
                pass(driver, weather);
                continue;
            }else {
            	String noweather = "Weather icon is NOT displayed";
            	System.out.println(noweather);
            	fail(driver,noweather);
            }
        List<WebElement>we1=driver.findElements(By.cssSelector("#weather-wrapper>a>strong"));
        for(int l=0;l<we1.size();l++){
            WebElement wele1=we.get(l);
            if(wele1.isDisplayed()){
            	String temp = wele.getText()+ "Temp is displayed";
                System.out.println(wele.getText()+ " is displayed");
                pass(driver,temp);
                continue;
            }else {
            	String temp = "Temp is NOT displayed";
            	fail(driver, temp);
            }
            }
        }
        }
    else{
    	String failMsg = "Weather banner is NOT present";
        System.out.println("Weather banner is NOT present");
        fail(driver,failMsg);
        }
    
}
    
}