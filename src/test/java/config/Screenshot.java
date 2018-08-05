/**
 * 
 */
package config;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;


import browsers.BeforeAfter;
import browsers.Url;

/**
 * @author jigneshkumarpatel
 *takes screenshot of page and save in test report folder
 */

public class Screenshot extends browsers.BeforeAfter{
	//public static WebDriver driver;

	
//public static void takeimg(WebDriver driver, Method method, ITestResult result, ITestContext ctx) {
	public static void takeimg(Method method, ITestResult result, ITestContext ctx) {
		
	String suitTest = ctx.getCurrentXmlTest().getName();
	//screenshot
	File src= ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	try {
		String imgName = suitTest+method.getName()+Url.currentDate;
		File screenshotName =new File(System.getProperty("user.dir") +"/testReport/"+Url.currentDate+"/"+imgName+".png");
		FileUtils.copyFile(src, screenshotName);
		BeforeAfter.info(getDriver(),"<br><p>"+imgName+"<br><img src= '"+screenshotName+"' width='600' height='340'></p><br>");
		String imageNCaption ="<br><p>"+imgName+"<br><img src= '"+Url.currentDate+"/"+imgName+".png"+"' width='600' height='340'></p>"+"<font color='#ffffcc';>"+result.getThrowable().getMessage()+"</font>"+"<br>";
		info(getDriver(),imageNCaption);
		//BeforeAfter.logger.log(LogStatus.UNKNOWN, imageNCaption);
	}catch (IOException e) {
	  System.out.println(e.getMessage());
	  
	 }
	}
}
