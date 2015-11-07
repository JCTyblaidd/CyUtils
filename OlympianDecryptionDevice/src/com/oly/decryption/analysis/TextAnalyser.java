package com.oly.decryption.analysis;

public class TextAnalyser {
	
	
	
	public static boolean isTextDisguised(String text) {
		String[] data = text.split(" ");
		int guess = 0;
		for(String dat : data) {
			guess += dat.length();
		}
		guess = (int)Math.round(((float)guess) / ((float)data.length));
		///////NOW COUNT ERROR MARGIN {SHOULD BE END}///////////
		int errors = 0;
		for(String dat : data) {
			if(dat.length() != guess) {
				errors ++;
			}
		}
		float margin = errors / data.length;//FROM 1-0
		margin = Math.abs(margin);
		if(margin < 0.15) {//TODO refine error margin
			return true;
		}
		
		return false;
	}
	
	
	
	
	
}
