package cljCheck;

import org.testng.annotations.Test;

import browsers.adNamesFileReader;

public class PrintAdNames {
	
	private static adNamesFileReader reader = new adNamesFileReader();
	
	@Test
	public void AdsName() {
		
		for (String ad : reader.ads()) {
			System.out.println(ad);
		}
		
	}

}
