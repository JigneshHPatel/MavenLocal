/**
 * 
 */
package clj.Frontend;

import org.testng.annotations.Test;
import commonLibrary.RelatedArticleMethod;
import util.Retry;
import util.Urls;

/**
 * @author jigneshkumarpatel
 *
 */

public class RelatedArticle extends browsers.BeforeAfter {

	@Test(retryAnalyzer = Retry.class, description = "check related module, FB icon and related articles")
	public static void RelatedArticles() throws Exception {

		getDriver().get(Urls.articleUrl);
		Thread.sleep(2000);
		String articleurl = getDriver().getCurrentUrl();
		System.out.println(articleurl);
		info(getDriver(), articleurl);
		System.out.println("---___---___---");
		RelatedArticleMethod.relatedArticle(getDriver());

	}
}
