package com.oly.decryption.analysis;

import java.util.Map;

import com.oly.util.Logger;
import com.oly.util.Maths;

public class ShiftAnalyse {
	
	private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
									 
	//RETURNS THE DECRYPT SHIFT DO 26 - to get encrypt shift
	public static int analyseShift(String str) {
		
		FrequencyAnalyser analysis = new FrequencyAnalyser(str);
		analysis.Analyse();
		
		Map<Character,Float> result = analysis.frequencies();
		Map<Character,Float> standard = analysis.standard;
		
		double bestscore = 10000;//LOWER IS BETTER
		int bestshift = 0;
		
		for(int i = 0; i < 26; i++) {
			double score = 0;//SCORE IS TOTAL OF FREQUENCY OFFSETS
			for(int c = 0; c < 26; c++) {
				char from = alphabet.charAt(c);
				char to = alphabet.charAt(Maths.Mod_Clamp(c + i,26));
				//NOW ANALYSE DIFFERENCES
				float score1 = result.get(Character.toUpperCase(to));
				float score2 = standard.get(Character.toUpperCase(from));
				score += Math.abs(score1 - score2);
			}
			if(score < bestscore) {
				bestscore = score;
				bestshift = i;
			}
		}
		Logger.instance.LOG("INFO: bestshift = " + bestshift + "w/" + bestscore);
		return bestshift;
	}
	
	
}
