package com.jc.solitaire.experimental;

public class KeyStreamAnalysis {
	
	private static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	//NO USE
	public static void printStreamDiffs(String keystream) {
		for(int i = 1; i < keystream.length(); i++) {
			char from = keystream.charAt(i - 1);
			char to = keystream.charAt(i);
			int i1 = ALPHABET.indexOf(from);
			int i2 = ALPHABET.indexOf(to);
			System.out.print((i2-i1) + ", ");
		}
		
		
	}
	
	
	
	
}
