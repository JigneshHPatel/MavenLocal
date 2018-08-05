/**
 * 
 */
package util;



/**
 * @author jigneshkumarpatel
 * 
 *         Get String value of system OS name
 *
 */
public class SystemOSName extends browsers.BeforeAfter {

	public static String OSName = System.getProperty("os.name").toLowerCase();
			//((RemoteWebDriver) getDriver()).getCapabilities().getPlatform().toString().toLowerCase();

	public static boolean isWindows() {
		return OSName.contains("win");
	}

	public static boolean isMac() {
		return OSName.contains("mac");
	}

}
