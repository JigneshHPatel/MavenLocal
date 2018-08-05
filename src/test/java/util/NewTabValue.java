/**
 * 
 */
package util;

import org.openqa.selenium.Keys;

/**
 * @author jigneshkumarpatel Get NewTab As String according to system. if system
 *         is mac then NewTab is command + return else control + return
 */
public class NewTabValue {

	public static String NewTab() {
		if (SystemOSName.isMac()) {
			return Keys.chord(Keys.COMMAND, Keys.RETURN);
		} else {
			return Keys.chord(Keys.CONTROL, Keys.RETURN);
		}
	}

}
