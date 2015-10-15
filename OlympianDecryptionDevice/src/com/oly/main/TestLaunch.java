package com.oly.main;

import com.oly.util.Logger;
import com.oly.web.WebScraper;

public class TestLaunch {
	
	
	
	public static void main(String[] args) {
		
		Logger.instance.init_logger();
		WebScraper scraper = new WebScraper("http://www.cipher.maths.soton.ac.uk/code-breaking");
		Logger.instance.INFO(scraper.read_page());
		while(true) {
			
		}
		
	}
}
