package com.oly.threading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;

import com.oly.decryption.analysis.FrequencyAnalyser;
import com.oly.decryption.key.KeyDecrypter;
import com.oly.ui.DecryptTextUI;
import com.oly.util.Logger;




public class FrequencyAnalysisTask implements Runnable{
	
	
	public DecryptTextUI linked;
	public FrequencyAnalyser freq;
	public String text;
	public Map<Character,String> guesses;
	
	public FrequencyAnalysisTask(DecryptTextUI link) {
		this.linked = link;
		text = link.cypher.substring(0);
		freq = new FrequencyAnalyser(text);
		
		guesses = new HashMap<Character,String>();
		JTable table = linked.freq_analysis;
		for(int i = 0; i < 26; i++) {
			try{
			guesses.put((Character)table.getValueAt(i,0),(String)table.getValueAt(i, 1));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void run() {
		//NO OP
		//int v = 0 / (1-1); //DELIBERATE CRASH {lazy exit}
		//freq.Analyse();
		//Map<Character,String> guesses = freq.getReasonableGuesses(0.3f);
		//Logger.instance.INFO("Freq Analysis Guesses = " + guesses);
		//List<String> results = KeyDecrypter.getDecryptions(guesses, text);
		//Logger.instance.INFO("Frequency Analysis Task found [" + results.size() + "] options");
		//linked.possibilities_freq.addAll(results);
		//linked.poss_dirty_freq = true;
		//Logger.instance.INFO("Frequency Analysis Task finished");
		//FINISHED
		Logger.instance.INFO("==> INITTING FREQ ANALYSIS DECRYPTION");
		//NOPE
		//List<String> results = KeyDecrypter.getAllowedDecryptions(guesses, text, 0.19f);
		List<String> results = KeyDecrypter.bruteAffine(text);
		//#List<String> screwu = KeyDecrypter.getAllowedDecryptions(guesses, text,0.1f);
		//System.out.println("SCREWU: " + screwu.size());
		linked.possibilities_freq.addAll(results);
		//linked.possibilities_freq.addAll(screwu);
		linked.poss_dirty_freq = true;
		List<String> results2 = KeyDecrypter.smart_brute_polyalphabet(text);
		List<String> temp = KeyDecrypter.brutePolyOfSizeX(text, 2);
		linked.possibilities_poly.addAll(results2);
		linked.possibilities_poly.addAll(temp);
		linked.poss_dirty_poly = true;
	}
	
}
