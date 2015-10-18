package com.oly.main;

import java.awt.Color;

import com.oly.ui.FrequencyAnalysisUI;
import com.oly.util.Logger;
import com.oly.web.WebScraper;

public class TestLaunch {
	
	
	
	public static void main(String[] args) {
		
		Logger.instance.init_logger();
		new FrequencyAnalysisUI("Freq Analy Test", null);
		Logger.instance.LOG(WebScraper.getChallenge(1, 'A'));
		Logger.instance.LOG(Color.GREEN, "TEXT #2 [TESTING]");
		Logger.instance.LOG(WebScraper.getChallenge(1, 'B'));
		while(true) {
			
		}
		
	}
}
