/**
 * 
 */
package util;

/**
 * @author jigneshkumarpatel
 *
 *         base url and article urls and search word from datafile
 */
public class Urls {

	private static DataFileReader dataReader = new DataFileReader();

	public static final String baseurl = dataReader.getBaseUrl();
	public static final String articleUrl = dataReader.getArticleUrl();
	public static final String pollArticleUrl = dataReader.getPollArticle();
	public static final String search = dataReader.getSearchWord();
}
