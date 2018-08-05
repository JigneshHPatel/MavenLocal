/**
 * 
 */
package commonLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pageObjects.AllPagesObj;

/**
 * @author jigneshkumarpatel page footer menu, back to top button on footer,
 *         sitemaps, contact us
 *
 */
public class PageFooter extends browsers.BeforeAfter {
	public static void footer(WebDriver driver) {

		AllPagesObj footerObj = new AllPagesObj(driver);

		// Back to Top button on footer menu
		if (footerObj.footerBackToTop.isDisplayed()) {
			System.out.println("Back to top button is displayed");
			String passMsg = "Back to top button is displayed";
			pass(driver, passMsg);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~");

		// Footer menu channel, sub-channel
		int footer = footerObj.footerMenuLinks.size();
		if (footer > 0) {
			System.out.println("Total menu in footer bar is " + footer);
			String infoMsg = "Total menu in footer bar is " + footer;
			info(driver, infoMsg);
			for (WebElement ftElement : footerObj.footerMenuLinks) {
				if (ftElement.isDisplayed() && ftElement.isEnabled()) {
					System.out.println(ftElement.getAttribute("class") + " is displayed and clickable");
					pass(driver, ftElement.getAttribute("class") + " is displayed and clickable");
				} else {
					System.out.println(ftElement.getAttribute("class") + " is ***NOT*** displayed and clickable");
					fail(driver, ftElement.getAttribute("class") + " is ***NOT*** displayed and clickable");
				}
			}

		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~");

		// Footer links e.g. Site map, topics, RSS etc.
		int ftdw = footerObj.footerDownMenuLinks.size();
		if (ftdw > 0) {
			System.out.println("Total Down footer menu *i.e. sitemape,rss etc.* are " + ftdw);
			String infoMsg = "Total Down footer menu *i.e. sitemape,rss etc.* are " + ftdw;
			info(driver, infoMsg);
			for (WebElement ftdElement : footerObj.footerDownMenuLinks) {
				if (ftdElement.isDisplayed() && ftdElement.isEnabled()) {
					System.out.println(ftdElement.getText() + " is displayed and clickable");
					pass(driver, ftdElement.getText() + " is displayed and clickable");
				} else {
					System.out.println(ftdElement.getText() + " is ***NOT*** displayed and clickable");
					fail(driver, ftdElement.getText() + " is ***NOT*** displayed and clickable");
				}
			}
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~");

		// Footer Legal links e.g contact us etc.
		String publishedBy = footerObj.footerPublishedBy.getText();
		if (driver.getCurrentUrl().contains("auhome")) {
			Assert.assertTrue(publishedBy.equalsIgnoreCase("Published by Daily Mail Australia"));
			System.out.println(publishedBy + " :text is present");
			pass(driver, publishedBy + " :text is present");
		} else {
			Assert.assertTrue(publishedBy.equalsIgnoreCase("Published by Associated Newspapers Ltd"));
			System.out.println(publishedBy + " :text is present");
			pass(driver, publishedBy + " :text is present");
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~");

		String dmgt = footerObj.footerPartofDMText.getText();
		if (driver.getCurrentUrl().contains("auhome")) {
			Assert.assertTrue(dmgt.equalsIgnoreCase("© Dailymail.com Australia Pty Ltd"));
			System.out.println(dmgt + "  :text is present");
			pass(driver, dmgt + "  :text is present");
		} else {
			Assert.assertTrue(dmgt.equalsIgnoreCase("Part of the Daily Mail, The Mail on Sunday & Metro Media Group"));
			System.out.println(dmgt + "  :text is present");
			pass(driver, dmgt + "  :text is present");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~");

		// Legal links at Footer
		for (WebElement legalLink : footerObj.footerLegalLinks) {
			if (legalLink.isDisplayed() && legalLink.isEnabled()) {
				System.out.println(legalLink.getText() + " is displayed and clickable");
				pass(driver, "pass for " + legalLink.getText() + " is displayed and clickable");
			}

		}

	}

}
