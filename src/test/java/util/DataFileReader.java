/**
 * 
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author jigneshkumarpatel
 *
 *         Read data of baseURL, article and Username /passwords from
 *         DataFile.properies
 *
 */
public class DataFileReader {

	private static Properties dataProp;

	public DataFileReader() {

		File file = new File(System.getProperty("user.dir") + "/DataFile.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			dataProp = new Properties();
			dataProp.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// get values from DataFile

	public String getBaseUrl() {
		return dataProp.getProperty("baseUrl");
	}

	public String getArticleUrl() {
		String base = dataProp.getProperty("baseUrl");
		String arti = dataProp.getProperty("articleUrl");
		return (base + arti);
	}

	public String getPollArticle() {
		String base = dataProp.getProperty("baseUrl");
		String Article = dataProp.getProperty("pollArticle");
		return (base + Article);
	}

	public String getSearchWord() {
		return dataProp.getProperty("searchWord");
	}

	public String getBrowserstackUsername() {
		return dataProp.getProperty("browserStackUsername");

	}

	public String getBrowserstackKey() {
		return dataProp.getProperty("browserStackKey");
	}

	public String getDirectloginUsername() {
		return dataProp.getProperty("mailonlineUsername");
	}

	public String getDirectloginPassword() {
		return dataProp.getProperty("mailonlinePassword");
	}

	public String getFacebookUsername() {
		return dataProp.getProperty("facebookUsername");
	}

	public String getFacebookPassword() {
		return dataProp.getProperty("facebookPassword");
	}

	public String getTwitterUsername() {
		return dataProp.getProperty("twitterUsername");
	}

	public String getTwitterPassword() {
		return dataProp.getProperty("twitterPassword");
	}

	public String[] getTopicNames() {
		return dataProp.getProperty("TopicNames").split(",");
	}
	
	public String getSpikedArticle() {
		return dataProp.getProperty("spikedArticle");
	}
	
	public String getUKGeoblockedArticle() {
		return dataProp.getProperty("geoblockedArticleUK");
	}
	
	public String getUSGeoblockedArticle() {
		return dataProp.getProperty("geoblockedArticleUS");
	}
	
	public String getSponsoredArticle() {
		return dataProp.getProperty("sponsoredArticle");
	}
	
	public String getProductArticle() {
		return dataProp.getProperty("productArticle");
	}

}
