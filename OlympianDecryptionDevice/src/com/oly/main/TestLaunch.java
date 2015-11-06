package com.oly.main;

import com.oly.decryption.key.KeyDecrypter;
import com.oly.lexical.analysis.LexicalAnalyser;
import com.oly.ui.DecryptTextUI;
import com.oly.util.Logger;
import com.oly.web.WebScraper;

public class TestLaunch {
	
	
	
	public static void main(String[] args) {
		LexicalAnalyser.INIT();
		Logger.instance.init_logger();
		Logger.instance.LOG(" === INITING ===");
		
		//Logger.instance.init_logger();
		//Logger.instance.LOG(WebScraper.getChallenge(3, 'A'));
		//Logger.instance.LOG(WebScraper.getChallenge(2, 'A'));
		//Logger.instance.LOG(Color.GREEN, "TEXT #2 [TESTING]");
		//Logger.instance.LOG(WebScraper.getChallenge(2, 'B'));
		//String ctemp = WebScraper.getChallenge(3, 'B');
		System.out.println(KeyDecrypter.shift("abcde", 1));
		new DecryptTextUI("Challenge 3B", WebScraper.getChallenge(3, 'B'));
		//List<String> all = KeyDecrypter.bruteAffine(WebScraper.getChallenge(3,'B'));
		
		
		//requencyAnalyser analyser = new FrequencyAnalyser(WebScraper.getChallenge(2, 'B'));
		//analyser.Analyse();
		//analyser.getPotentials(analyser.standard,analyser.frequencies());
		//ew FrequencyAnalysisUI("Freq Analy Test", analyser);
		while(true) {
			
		}
		
	}
}
