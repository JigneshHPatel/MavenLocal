/**
 * 
 */
package util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;

/**
 * @author jigneshkumarpatel takes screenshot of page and save in test report
 *         folder
 */

public class Screenshot extends browsers.BeforeAfter {

	public static void takeimg(Method method, ITestResult result, ITestContext ctx) {

		// screenshot
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			String imgName = ctx.getCurrentXmlTest().getName() + method.getName() + SystemDateTime.currentDateTime();
			File screenshotName = new File(System.getProperty("user.dir") + "/testReport/"
					+ SystemDateTime.currentDateTime() + "/" + imgName + ".png");
			FileUtils.copyFile(src, screenshotName);
			String imageNCaption = "<br><p>" + imgName + "<br><img src= '" + "/testReport/"
					+ SystemDateTime.currentDateTime() + "/" + imgName + ".png" + "' width='100%' height='400'></p>"
					+ "<font color='#ffffcc';>" + result.getThrowable().getMessage() + "</font>" + "<br>";
			info(getDriver(), imageNCaption);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
