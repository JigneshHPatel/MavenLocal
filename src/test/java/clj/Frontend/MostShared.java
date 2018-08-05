/**
 * 
 */
package clj.Frontend;

import static org.testng.Assert.*;
import org.testng.annotations.*;
import util.Urls;
import commonLibrary.MostSharedModuleMethod;
import util.Retry;

/**
 * @author jigneshkumar.patel
 * 
 *         Assert if Most Shared article is displayed on home and ushome pages.
 *         Open top 2 articles and assert.
 */

public class MostShared extends browsers.BeforeAfter {

	@Test(retryAnalyzer = Retry.class)
	public void MostShared_UK_HomePage() throws Exception {

		MostSharedModuleMethod.MostShared(getDriver());

	}

	@Test(retryAnalyzer = Retry.class)
	public void MostShared_US_HomePage() throws Exception {
		// US Home Page

		getDriver().get(Urls.baseurl + "/ushome/index.html");
		Thread.sleep(3000);

		try {
			assertEquals(getDriver().getCurrentUrl(), Urls.baseurl + "/ushome/index.html");
			System.out.println(getDriver().getCurrentUrl());
			info(getDriver(), getDriver().getCurrentUrl());
		} catch (Exception e) {
			System.out.println(e);
			error(getDriver(), e.getMessage());
		}

		MostSharedModuleMethod.MostShared(getDriver());

	}

}
