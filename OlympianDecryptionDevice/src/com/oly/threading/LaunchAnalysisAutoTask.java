package com.oly.threading;

import java.util.List;

import com.oly.decryption.key.KeyDecrypter;
import com.oly.ui.DecryptTextUI;
import com.oly.util.Logger;

public class LaunchAnalysisAutoTask implements Runnable{

	
	protected FrequencyAnalysisTask freq;
	protected TranspositionBruteTask trans;
	protected LexicalAnalysisTask lex;
	protected String text;
	protected DecryptTextUI linked;
	
	public LaunchAnalysisAutoTask(DecryptTextUI link) {
		text = link.cypher;
		linked = link;
		freq = new FrequencyAnalysisTask(link);
		trans = new TranspositionBruteTask(link);
		lex = new LexicalAnalysisTask(link);
	}
	
	@Override
	public void run() {
		freq.run();
		lex.run();
		trans.run();
		lex.run();
		Logger.instance.INFO("== Initiating bulkier brute polyalphabetic == ");
		//SHOVED IN BRUTING POLY ALPHABETICS TASK
		for(int i = 2; i < 10; i++) {
			List<String> results = KeyDecrypter.brutePolyOfSizeX(text, i);
			linked.possibilities_poly.addAll(results);
			linked.poss_dirty_poly = true;
			lex.run();
		}
	}
	
	
	
	
	
	
}
