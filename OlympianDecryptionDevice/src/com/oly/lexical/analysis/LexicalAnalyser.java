package com.oly.lexical.analysis;

public class LexicalAnalyser {
	
	/**
	 * 
	 * @param str
	 * @return 0.0 - 1.0 depending on how suitable the translation is
	 */
	public float rate(String str,Language lang) {
		String temp = str.replace(" ", "");
		//NO SPACES BECAUSE THEY ARE EVIL;
		
		//TODO using a seperate thread and a **** TON of resources
		
		//GET A LOCAL DICTIONARY AND SEE IF IT IS ENGLISH
		
		return 0; 
	}
	
	
	@SuppressWarnings("unused")
	private enum Language {
		
		
		ENGLISH; //TODO ADD SUPPORT AND LINK TO DICTIONARIES
		//OR DO THE AMAZINF GOOGLE TRANSLATE (IT WORKS HNST GUV)
		// {YES THAT IS A POSSIBILITY, WE COULD TRY SE HOW MUCH IT ACTUALLY TRANSLATES
		//SAY INTO SPANISH, AND DIFF THE TWO, MORE DIFFERENCES = BETTER
		
	}
	
}


