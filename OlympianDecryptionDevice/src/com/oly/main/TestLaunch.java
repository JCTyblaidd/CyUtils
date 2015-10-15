package com.oly.main;

import com.oly.ui.FrequencyAnalysisUI;
import com.oly.util.Logger;
import com.oly.web.WebScraper;

public class TestLaunch {
	
	
	
	public static void main(String[] args) {
		
		Logger.instance.init_logger();
		WebScraper scraper = new WebScraper("http://www.cipher.maths.soton.ac.uk/the-challenges");
		//Logger.instance.INFO(scraper.read_page());
		String[] outputs = scraper.ExperimentalGetText();
		new FrequencyAnalysisUI("Freq Analy Test", null);
		for(String output : outputs) {
			Logger.instance.WARNING("NEW OPTION");
			Logger.instance.LOG(output);
		}
		while(true) {
			
		}
		
	}
}
