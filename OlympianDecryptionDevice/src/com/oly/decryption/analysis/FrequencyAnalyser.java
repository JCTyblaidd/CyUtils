package com.oly.decryption.analysis;

import java.util.HashMap;

public class FrequencyAnalyser {
	
	String data;
	HashMap<Character,Integer> results = new HashMap<Character,Integer>();
	
	public FrequencyAnalyser(String str) {
		data = str;
	}
	
	
	public void Analyse() {
		String alphabet = "abcddefghijklmnopqrstuvwxyz";
		for(char c : alphabet.toCharArray()) { //TO ENSURE IT LOOKS NICER
			results.put(c, 0);
		}
		
		for(char c : data.toCharArray()) {
			if(results.containsKey(c)) {
				results.put(c, results.get(c) + 1);
			}else {
				results.put(c,1);
			}
		}
	}
	
	
	@Override
	public String toString() {
		return results.toString();
	}
	
}
