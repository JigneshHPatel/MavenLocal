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
public class adNamesFileReader {

	private static Properties adProp;

	public adNamesFileReader() {

		File file = new File(System.getProperty("user.dir") + "/AdsNameFile.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			adProp = new Properties();
			adProp.load(fis);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public String[] ads() {
		String adNames = adProp.getProperty("adNames");
		String ads[] = adNames.split(",");
		return ads;
	}
}
