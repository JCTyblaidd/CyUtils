package com.oly.lexical.analysis;

import java.util.HashMap;

import com.oly.decryption.key.KeyDecrypter;

public class LexicalAnalyser {
	
	public static HashMap<String,Float> cribs;
	
	/**
	 * 
	 * @param str
	 * @return 0.0 - 1.0 depending on how suitable the translation is
	 */
	public static float rate(String str,Language lang) {
		float rate = 0.0f;
		String temp = str.replace(" ", "").toLowerCase();
		//CRIB RATING
		for(String r : cribs.keySet()) {
			if(temp.contains(r.toLowerCase())) {
				rate += cribs.get(r);
			}
		}
		//if(rate > 1.0f) {//NOPPE HUGE VALUES NOW
		//	rate = 1.0f;
		//}
		
		//KEY CONTENT
		if(KeyDecrypter.isEMax(temp.toUpperCase())) {		//E MAX RATE
			rate += 0.3f;
		}
		
		
		//STARTING WORD
		if(temp.startsWith("the")) {
			rate += 0.5f;
		}
		
		
		
		return rate; 
	}
	
	
	
	
	
	public static void INIT() {
		cribs = new HashMap<String,Float>();
		
		//////KEY WORDS
		cribs.put("Reichsdoktor",2.0f);
		cribs.put("Harry", 2.0f);
		cribs.put("Charlie", 2.0f);
		
		//COUNTARIES
		cribs.put("Europe",0.5f);
		cribs.put("British",0.5f);
		cribs.put("French",0.5f);
		cribs.put("Russian",0.5f);
		cribs.put("Britian",0.5f);
		cribs.put("France",0.5f);
		cribs.put("Russia",0.5f);
		cribs.put("America",0.5f);
		cribs.put("Berlin",0.5f);
		//
		cribs.put("UK",0.1f);//GOOD BUT TOO LIKELY TO BE AN ERROR
		
		//LONGER ENGLISH WORDS
		cribs.put("Investigation",0.75f);//LONG WORDS = :D
		cribs.put("Negotiations",0.75f);//LONG WORDS = :D
		cribs.put("Convenience",0.3f);
		
		//NUMERATIONS
		cribs.put("hundred",0.3f);
		cribs.put("thousand",0.3f);
		cribs.put("seven",0.2f);
		cribs.put("several",0.2f);
		
		
		
		///USEFUL WORDS
		cribs.put("intelligence",0.5f);//END OF COUNTRIES
		cribs.put("forgive",0.1f);
		cribs.put("arrogance", 0.5f);
		cribs.put("coincidence",0.5f);
		cribs.put("hidden",0.5f);
		cribs.put("message", 0.35f);
		cribs.put("eye", 0.15f);
		cribs.put("extraordinary",0.5f);
		cribs.put("delivering",0.5f);
		cribs.put("stable",0.35f);
		cribs.put("perhaps",0.3f);
		cribs.put("peace",0.5f);
		cribs.put("nazi",1.0f);
		cribs.put("marine", 1.0f);
		cribs.put("safe", 0.1f);
		cribs.put("house",0.15f);
		cribs.put("auction", 0.2f);
		cribs.put("location",0.3f);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////////USELESSNESS////////////////////////////////////
	
	private static enum Language {
		
		
		ENGLISH; //TODO ADD SUPPORT AND LINK TO DICTIONARIES
		//OR DO THE AMAZINF GOOGLE TRANSLATE (IT WORKS HNST GUV)
		// {YES THAT IS A POSSIBILITY, WE COULD TRY SE HOW MUCH IT ACTUALLY TRANSLATES
		//SAY INTO SPANISH, AND DIFF THE TWO, MORE DIFFERENCES = BETTER
		
	}
	
}


