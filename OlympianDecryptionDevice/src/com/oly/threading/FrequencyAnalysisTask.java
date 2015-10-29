package com.oly.threading;

import java.util.List;
import java.util.Map;

import com.oly.decryption.analysis.FrequencyAnalyser;
import com.oly.decryption.key.KeyDecrypter;
import com.oly.ui.DecryptTextUI;
import com.oly.util.Logger;




public class FrequencyAnalysisTask implements Runnable{
	
	
	public DecryptTextUI linked;
	public FrequencyAnalyser freq;
	public String text;
	
	public FrequencyAnalysisTask(DecryptTextUI link) {
		this.linked = link;
		text = link.cypher.substring(0);
		freq = new FrequencyAnalyser(text);
	}


	@Override
	public void run() {
		freq.Analyse();
		Map<Character,String> guesses = freq.getReasonableGuesses(1.5f);
		Logger.instance.INFO("Freq Analysis Guesses = " + guesses);
		List<String> results = KeyDecrypter.getDecryptions(guesses, text);
		Logger.instance.INFO("Frequency Analysis Task found [" + results.size() + "] options");
		linked.possibilities_freq.addAll(results);
		linked.poss_dirty_freq = true;
		Logger.instance.INFO("Frequency Analysis Task finished");
		//FINISHED
	}
	
}
