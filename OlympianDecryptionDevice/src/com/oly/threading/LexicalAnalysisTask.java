package com.oly.threading;

import com.oly.decryption.key.KeyDecrypter;
import com.oly.lexical.analysis.LexicalAnalyser;
import com.oly.ui.DecryptTextUI;
import com.oly.util.Logger;

public class LexicalAnalysisTask implements Runnable{
	
	public DecryptTextUI linked;
	
	public LexicalAnalysisTask(DecryptTextUI link) {
		this.linked = link;
	}

	@Override
	public void run() {
		//TODO make it do shizzle
		if(linked.poss_dirty_freq) {
			Logger.instance.LOG("== INIT FREQ LEXICAL ANALYSIS ==");
			for(String option : linked.possibilities_freq) {
				if(LexicalAnalyser.rate(option, null) > 0.75) {
					linked.lexical_accepted_freq.add(option);
				}
			}
			linked.poss_dirty_freq = false;
		}
		if(linked.poss_dirty_poly) {
			Logger.instance.LOG("== INIT POLY LEXICAL ANALYSIS ==");
			for(String option : linked.possibilities_poly) {
				if(LexicalAnalyser.rate(option, null) > 0.75) {		//TODO unchange
					linked.lexical_accepted_poly.add(option);
				}
			}
			linked.poss_dirty_poly = false;
		}
		if(linked.poss_dirty_trans) {
			Logger.instance.LOG("== INIT TRANS LEXICAL ANALYSIS ==");
			for(String option : linked.possibilities_trans) {
				if(LexicalAnalyser.rate(option, null) > 0.75) {
					linked.lexical_accepted_trans.add(option);
				}
			}
			linked.poss_dirty_trans = false;
		}
		
		Logger.instance.LOG("Finished Lexical Analysis");
	}
	
	
	
	
	
}
