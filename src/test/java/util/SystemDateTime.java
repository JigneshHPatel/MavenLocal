/**
 * 
 */
package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jigneshkumarpatel
 *
 */
public class SystemDateTime {
	
	private static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm");
	private static Date date = new Date();
	
	public static  String currentDateTime () {
		return dateFormat.format(date);
	}

}
