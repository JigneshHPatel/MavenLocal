/**
 * 
 */
package commonLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.AllPagesObj;

/**
 * @author jigneshkumarpatel Check weather widget and icons on top of page.
 */
public class WeatherWidget extends browsers.BeforeAfter {
	public static void WeatherIcons(WebDriver driver) {
		AllPagesObj weatherPageObj = new AllPagesObj(driver);
		if (weatherPageObj.weatherWidget.isDisplayed()) {
			System.out.println("Weather banner is present");
			String passMsg = "Weather banner is present";
			pass(driver, passMsg);
			if (weatherPageObj.weatherDayandDate.isDisplayed()) {
				String date = "Day and Date is Present  " + weatherPageObj.weatherDayandDate.getText();
				System.out.println(date);
				pass(driver, date);
			} else {
				String nodate = "Day and Date is  not Present ";
				System.out.println(nodate);
				fail(driver, nodate);
			}

			for (int i = 0; i < weatherPageObj.AllElementsOnWidget.size(); i++) {
				WebElement widgetIcon = weatherPageObj.AllElementsOnWidget.get(i);
				if (widgetIcon.isDisplayed()) {
					String weatherIconName = widgetIcon.getText();
					String iconName;
					if (weatherIconName.length()>2) {
						iconName = weatherIconName;
					} else {
						iconName = "Weather symbol " + (i + 1);
					}
					String weather = iconName + " icon is displayed";
					System.out.println(weather);
					pass(driver, weather);
					continue;
				} else {
					String noweather = "Weather icon is NOT displayed";
					System.out.println(noweather);
					fail(driver, noweather);
				}
				if (weatherPageObj.currentTemp.isDisplayed()) {
					System.out.println(
							"Current temperature is " + weatherPageObj.currentTemp.getText() + " is displayed");
					pass(driver, "Current temperature is " + weatherPageObj.currentTemp.getText() + " is displayed");
				} else {
					System.out.println("Current temperature is ***NOT*** displayed");
					fail(driver, "Current temperature is ***NOT*** displayed");
				}
				if (weatherPageObj.futureTemp.isDisplayed()) {
					System.out
							.println("Future temperature is " + weatherPageObj.futureTemp.getText() + " is displayed");
					pass(driver, "Future temperature is " + weatherPageObj.futureTemp.getText() + " is displayed");
				} else {
					System.out.println("Future temperature is ***NOT*** displayed");
					fail(driver, "Future temperature is ***NOT*** displayed");
				}
			}
		} else {
			String failMsg = "Weather banner is NOT present";
			System.out.println("Weather banner is NOT present");
			fail(driver, failMsg);
		}

	}

}