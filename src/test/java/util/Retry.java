package util;

import org.testng.IRetryAnalyzer;

import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

// implement IRetryAnalyzer interface

public class Retry extends browsers.BeforeAfter implements IRetryAnalyzer {
	// set counter to 0

	int minretryCount = 0;
	int maxretryCount = 1;

	public boolean retry(ITestResult result) {
		try {
			if (minretryCount <= maxretryCount) {
				System.out.println("following test is failing " + result.getName());
				logger.log(LogStatus.SKIP, " is failing " + result.getName(), "Retry is " + (minretryCount + 1));
				System.out.println("Retry test count is " + (minretryCount + 1));
				System.out.println("˜˜˜˜˜");

				minretryCount++;
				return true;

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.FAIL,
					"following test is failing " + result.getName() + "Retry is " + (minretryCount + 1));
		}
		return false;
	}

}
