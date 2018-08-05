/**
 * 
 */
package browsers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author jigneshkumarpatel
 *
 */
public class DataFileReader {

	private static Properties prop;

	public DataFileReader() {

		File file = new File(System.getProperty("user.dir") + "/dataFile.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);

			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getBaseUrl() {
		String getBaseUrl = prop.getProperty("baseUrl");
		return getBaseUrl;

	}

	public String getArticleUrl() {
		String base = prop.getProperty("baseUrl");
		String arti = prop.getProperty("articleUrl");
		String getArticle = base + arti;
		return getArticle;
	}

	public String getBrowserstackUsername() {
		String bsUsername = prop.getProperty("browserStackUsername");
		return bsUsername;

	}

	public String getBrowserstackKey() {
		String bsKey = prop.getProperty("browserStackKey");
		return bsKey;
	}

	public String getDirectloginUsername() {
		String dmUsername = prop.getProperty("mailonlineUsername");
		return dmUsername;
	}

	public String getDirectloginPassword() {
		String dmPassword = prop.getProperty("mailonlinePassword");
		return dmPassword;
	}

	public String getFacebookUsername() {
		String fbUsername = prop.getProperty("facebookUsername");
		return fbUsername;
	}

	public String getFacebookPassword() {
		String fbPassword = prop.getProperty("facebookPassword");
		return fbPassword;
	}

	public String getTwitterUsername() {
		String twUsername = prop.getProperty("twitterUsername");
		return twUsername;
	}

	public String getTwitterPassword() {
		String twPassword = prop.getProperty("twitterPassword");
		return twPassword;
	}

	public String getRcArticle() {
		String rcArt = prop.getProperty("RcArticleUrl");
		return rcArt;
	}

}
