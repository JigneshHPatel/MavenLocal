/**
 * 
 */
package browsers;

import org.openqa.selenium.JavascriptExecutor;


/**
 * @author jigneshkumarpatel
 *
 */



public class ArticleUrl extends browsers.BeforeAfter {
	public static   String articleUrl1;
	public  void getArticleUrl1(String articleUrl1) throws Exception {
		//this.articleUrl1=articleUrl1;
		
		getDriver().get("http://www.dailymail.co.uk/tvshowbiz/article-5628573/Victoria-Beckham-pink-celebrates-44th-birthday-hubby-David-kids.html");
		 Thread.sleep(4000);
		 System.out.println(getDriver().getCurrentUrl());
		
		 JavascriptExecutor je =(JavascriptExecutor)getDriver();
		 System.out.println( je.executeScript("return PageCriteria.readerComments"));
		 //String rc=(String) je.executeScript("return PageCriteria.readerComments");
		 articleUrl1="this is it";
	}
	
/*	@Test
	public void getArticleUrl() throws Exception {
		driver.get("http://www.dailymail.co.uk/tvshowbiz/article-5628573/Victoria-Beckham-pink-celebrates-44th-birthday-hubby-David-kids.html");
		 Thread.sleep(4000);
		 System.out.println(driver.getCurrentUrl());
		
		 JavascriptExecutor je =(JavascriptExecutor)driver;
		 System.out.println( je.executeScript("return PageCriteria.readerComments"));
		
	}*/

}
