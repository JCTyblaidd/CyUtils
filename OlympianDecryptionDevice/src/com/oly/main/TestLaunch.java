package com.oly.main;

import java.awt.Color;

import com.oly.decryption.analysis.FrequencyAnalyser;
import com.oly.ui.DecryptTextUI;
import com.oly.ui.FrequencyAnalysisUI;
import com.oly.util.Logger;
import com.oly.web.WebScraper;

public class TestLaunch {
	
	
	
	public static void main(String[] args) {
		
		
		
		Logger.instance.init_logger();
		Logger.instance.LOG(WebScraper.getChallenge(3, 'A'));
		Logger.instance.LOG(WebScraper.getChallenge(2, 'A'));
		Logger.instance.LOG(Color.GREEN, "TEXT #2 [TESTING]");
		Logger.instance.LOG(WebScraper.getChallenge(2, 'B'));
		
		new DecryptTextUI("Challenge 2A", WebScraper.getChallenge(2, 'A'));
		
		//requencyAnalyser analyser = new FrequencyAnalyser(WebScraper.getChallenge(2, 'B'));
		//analyser.Analyse();
		//analyser.getPotentials(analyser.standard,analyser.frequencies());
		//ew FrequencyAnalysisUI("Freq Analy Test", analyser);
		while(true) {
			
		}
		
	}
}
