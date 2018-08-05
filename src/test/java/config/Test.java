package config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
	/*	System.out.println("generic method");
		String testText = "test1";
		String section = "home";
		System.out.println(System.getProperty("os.name").toLowerCase());
		if(section == "home")
			ElementAssert(testText,true);
		else
			ElementAssert(testText,false);

		switch(section)
		{
			case "home":
			case "news":
				ElementAssert(testText,true);
				break;
			case "footer" :
				ElementAssert(testText,false);
				break;
			default:
				ElementAssert(testText,true);
				break;
		}*/
		
		
		DateFormat dateformat = new SimpleDateFormat("MMMdd");
		String currentDate = dateformat.format((new Date())); 
		System.out.println(currentDate);
		
		if(isTrue()) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
	}
	
	public static boolean isTrue() {
		return 2 ==3;
	}
	
	
	//CommonLibrary > className
	
	public static void ElementAssert(String testText, boolean isLoginandShared) {
		 System.out.println(testText + "step 1 always run");
		 
		// Process LoginAndShared
		 if(isLoginandShared)
		 {
			 System.out.println("if true, step 2 login and share");
		 }

		 System.out.println("step 3 always run");
		 //process element checks
		 
		 
	}
	
	

	public  static void LoginandShared(String test2Text) {
		System.out.println(test2Text);
	}
	
	enum type{
		home,
		news;
	}
	
	
}
