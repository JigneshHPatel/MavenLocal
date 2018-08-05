/**
 * 
 */
package commonLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.ArticlePageObj;
import pageObjects.PuffListObj;

/**
 * @author jigneshkumarpatel Open puff articles and save article url if the
 *         article is wide and reader comment is enabled, and commets are not null in Article.property
 *         file
 */
public class GetArticleUrl extends browsers.BeforeAfter {
	private static Properties prop = new Properties();

	public static void getReaderCommentArticle(WebDriver driver) throws InterruptedException {

		ArticlePageObj articleObj = new ArticlePageObj(driver);
		PuffListObj puffObj = new PuffListObj(driver);

		JavascriptExecutor je = (JavascriptExecutor) driver;
		for (int i = 1; i < 10; i++) {
			WebElement puffArticle = driver.findElement(By.xpath(puffObj.AllPuffArticlesXpath(i)));
			WaitMethods.WaitUntilElementClickable(driver, puffArticle);
			puffArticle.click();
			Thread.sleep(2000);

			String rcStatus = (String) je.executeScript("return PageCriteria.readerComments");
			String articleType = (String) je.executeScript("return PageCriteria.articleStyle");
			if (rcStatus.equalsIgnoreCase("W") && articleType.equalsIgnoreCase("wide")
					&& articleObj.commentCountIsNotNull()) {
				File file = new File(System.getProperty("user.dir") + "/RCarticle.properties");
				if (file.exists()) {
					file.delete();
				}
				try {
					file.createNewFile();
					FileInputStream fin = new FileInputStream(file);
					FileOutputStream fos = new FileOutputStream(file, true);
					prop.load(fin);
					prop.setProperty("RcArticleUrl", driver.getCurrentUrl());
					prop.store(fos, "article url for reader comment");
					fos.close();
					fin.close();

				} catch (Exception e) {
					System.out.println(e);
				}
				System.out.println(driver.getCurrentUrl());
				info(driver, driver.getCurrentUrl());
				break;
			} else {
				driver.navigate().back();
			}
		}

	}

}
