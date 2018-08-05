package cljCheck;



public class strinmanipulation {

	public static void main(String[] args) {
		String url = "https://www4.dailymailint.co.uk/news/article-5846887/Daytona-Beach-Boardwalk-rollercoaster-derailed-two-people-fall-34-feet-ground.html";
		//String ampurls [] = url.split("(?=/article-[0-9{7}])-(.*)");
		String ampurls [] = url.split("(?=[/article-[0-9{7}]]+)/(?=[A-Z]+)");
		
		System.out.println(ampurls[0] + "/amp/" + ampurls[1]);
		/*for (String amp : ampurls) {
			String amp1 = ampurls[0];
			String amp2 = ampurls[1];
			System.out.println(amp1 + "/amp/" + amp2);
			
		}*/
	}

}
