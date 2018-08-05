
/**
 * 
 */
package cljCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author jigneshkumarpatel
 *
 */
public class AdConsoleOutput {
	private static List<String> actualAds = new ArrayList<String>();

	public static void AdConsole(WebDriver driver) throws Exception {
		JavascriptExecutor je = (JavascriptExecutor) driver;

		// je.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// Thread.sleep(3000);

		// wait until page loaded
		for (int j = 0; j < 25; j++) {
			Thread.sleep(1000);
			if (je.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}

		}

		List<String> adNames = Arrays.asList("billboard", "jwplayer", "inread_player", "inread_player_top",
				"leader_bottom", "leader_lower_middle", "leader_middle", "leader_very_bottom", "mpu_home",
				"mpu_mobile_top", "mpu_puff_15", "mpu_puff_30", "mpu_puff_45", "mpu_puff_other_55", "mpu_puff_other_65",
				"mpu_puff_other_75", "mpu_puff_other_85", "mpu_puff_other_95", "mpu_puff_other_105",
				"mpu_puff_other_115", "mpu_puff_other_125", "mpu_puff_other_135", "mpu_puff_other_145", "mpu_top",
				"sky_left_top", "sky_right_top");

		Long a = (Long) je.executeScript("return adverts.performance.state.auctions.length");

		for (int i = 0; i < a; i++) {
			for (String adName : adNames) {

				try {
					String js = "return adverts.performance.state.auctions[" + i + "]['slots']['" + adName
							+ "']['ad rendered']";
					String result = je.executeScript(js).toString();
					if (result != null) {
						System.out.println(adName + " is Visible");
						System.out.println(adName + " = " + result);
						System.out.println("*************");
						actualAds.add(adName);

					} else {
						System.out.println(adName + " is ***NOT*** rendered");
					}

				} catch (Exception e) {

				}

			}
		}

		System.out.println("Total ads present are: " + actualAds.size());

		System.out.println(System.lineSeparator() + "======================" + System.lineSeparator());

		for (String ad : adNames) {
			if (actualAds.contains(ad)) {

			} else {
				System.out.println(ad + " is NOT Present");
			}
		}
	}

}
