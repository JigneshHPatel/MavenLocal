/**
 * 
 */
package browsers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import config.CmpConsent;

/**
 * @author jigneshkumarpatel
 *
 */
@Parameters("testbaseurl")
public class Url extends browsers.BeforeAfter {

	private static DataFileReader dataReader = new DataFileReader();

	// public static String baseurl = "http://www.dailymail.co.uk/";
	// public static String articleUrl = baseurl +
	// "/femail/article-5547517/Mother-reveals-hack-stopping-sliced-apples-turning-brown.html";
	public static String search = "Mars";

	public static WebDriver driver;
	public static String os = System.getProperty("os.name").toLowerCase();
	public static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm");
	public static Date date = new Date();
	public static String currentDate = dateFormat.format(date);

	public static void URL(WebDriver driver) throws Exception {

		getDriver().get(dataReader.getBaseUrl() + "/home/index.html");
		Thread.sleep(3000);
		CmpConsent.gdprConsent();

	}

	@Test
	public void getArticleUrl() throws Exception {
		// System.out.println(ArticleUrl.articleUrl1);
		System.out.println(dataReader.getBaseUrl());
	}

}
