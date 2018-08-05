package cljCheck;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.LogStatus;
import browsers.DataFileReader;
import config.SocialShareObj;
import java.util.ArrayList;

public class Sample extends browsers.BeforeAfter {
	private static DataFileReader dataReader = new DataFileReader();

	@Test
	public void test() throws InterruptedException {
		SocialShareObj page = new SocialShareObj(getDriver());
		
		// Facebook Like button on top left of page
		getDriver().get(dataReader.getBaseUrl() + "/home/index.html");
		Thread.sleep(2000);
		info(getDriver(), getDriver().getCurrentUrl());

		if (page.facebookbutton.isDisplayed()) {
			System.out.println("Facebook Like button on top right of page is present");
			pass(getDriver(), "Facebook Like button on top right of page is present");
			String name = "facebook"; // page.facebookbutton.getAttribute("data-href");
			System.out.println("Button url is " + name);
			String parent = getDriver().getWindowHandle();
			try {
				// je.executeScript("arguments[0].click();", page.facebookbutton);
				page.facebookbutton.click();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				logger.log(LogStatus.ERROR, e.getMessage());
			}
			Thread.sleep(2000);
			ArrayList<String> child = new ArrayList<String>(getDriver().getWindowHandles());
			getDriver().switchTo().window(child.get(1));
			Thread.sleep(2000);
			try {
				if (getDriver().getCurrentUrl().endsWith("facebook.com/DailyMail")) {
					System.out.println("Facebook tab opened");
					pass(getDriver(), "Facebook tab opened");
					getDriver().findElement(By.id("email")).clear();
					getDriver().findElement(By.id("email")).sendKeys("testmolfb6@gmail.com");
					getDriver().findElement(By.id("pass")).clear();
					getDriver().findElement(By.id("pass")).sendKeys("testmol");
					getDriver().findElement(By.xpath("//input[@value='Log In']")).click();
					Thread.sleep(2000);
					try {
						Assert.assertEquals("Don Joe",
								getDriver().findElement(By.xpath("//*[@title='Don Joe']")).getAttribute("title"));
						System.out.println("Logged into facebook");
						System.out.println("Pass for FB button on top left");
						pass(getDriver(), "Pass for FB button on top left");
					} catch (Exception e) {
						System.out.println("failed to logged into FB");
						System.out.println(e.getMessage());

					}

				} else {
					System.out.println("FAIL for FB button on top left");
					fail(getDriver(), "FAIL for FB button on top left");
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				error(getDriver(), e.getMessage());
			} finally {
				if (child.size() > 1) {
					getDriver().close();
				}
				getDriver().switchTo().window(parent);
				Thread.sleep(2000);
			}

		} else {
			System.out.println("Facebook Like button on top right of page is NOT present");
			fail(getDriver(), "Facebook Like button on top right of page is NOT present");
		}
	}
}