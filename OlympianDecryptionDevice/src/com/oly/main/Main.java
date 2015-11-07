package com.oly.main;

import com.oly.lexical.analysis.LexicalAnalyser;
import com.oly.ui.DecryptTextUI;
import com.oly.util.Logger;
import com.oly.web.WebScraper;

public class Main implements Runnable{
	
	public static Main MASTER;
	private String[] params;
	private boolean isStopped = false;
	
	public static void main(String[] args) { //QUICK SPIN OFF FUNCTION
		LexicalAnalyser.INIT();
		MASTER = new Main(args);
		Logger.instance.init_logger();
		
		//final String failure = WebScraper.getChallenge(10, 'B');
		//Logger.instance.LOG(Integer.toString(WebScraper.getChallenge(10, 'B').length()));
		int curr_chall = 1;
		for(int i = 1; i < 9; i++) {
			String chall = WebScraper.getChallenge(i, 'A');
			if(chall.length() > 27) {
				curr_chall = i;
			}else {
				break;
			}
		}
		Logger.instance.LOG("CURRENT CHALLENGE = " + curr_chall);
		
		new DecryptTextUI("Challenge " + curr_chall +"A",WebScraper.getChallenge(curr_chall, 'A'));
		new DecryptTextUI("Challenge " + curr_chall +"B",WebScraper.getChallenge(curr_chall, 'B'));
		
		
		//LAUNCH CHALLENGES
		//FrequencyAnalyser analyserA = new FrequencyAnalyser(WebScraper.getChallenge(curr_chall, 'A'));
		//FrequencyAnalyser analyserB = new FrequencyAnalyser(WebScraper.getChallenge(curr_chall, 'B'));
		
		//new FrequencyAnalysisUI("Current Challenge A",analyserA);
		//new FrequencyAnalysisUI("Current Challenge B",analyserB);
		//OPEN PLAIN TEXT WINDOWS
		//new TranslationUI(" === CURRENT CHALLENGE A TEXT === \n\n"+WebScraper.getChallenge(curr_chall, 'A'));
		//new TranslationUI(" === CURRENT CHALLENGE B TEXT === \n\n"+WebScraper.getChallenge(curr_chall, 'B'));
		
		
		//KILL _IGNORE
		MASTER.run();
	}
	
	protected Main(String[] params) {
		this.params = params;
	}
	
	public void STOP() {
		isStopped = true;
	}
	
	@SuppressWarnings("unused")
	private boolean arg(String arg) {
		for(String param : params) {
			if(param == arg) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void run() {
		while(true) {
			//TODO do stuff and spin off
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			if(!isStopped) {//Add end contitional - linked to window
				break;
			}
		}
		
	}
	
}
