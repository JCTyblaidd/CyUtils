package com.jc.mod26.gui;

public class Modulo26Utils {
	
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	public static String formatString(String str,boolean allowspecials) {
		String output = "";
		for(char c : str.toUpperCase().toCharArray()) {
			if(ALPHABET.indexOf(c) != -1) {
				output = output + c;
			}else
			if(allowspecials && (c == '?' || c == '*')) {
				output = output + c;
			}
		}
		return output;
	}
	
	
	public static String handleStreams(String stream1,String stream2,boolean add) {
		int minsize = Math.min(stream1.length(), stream2.length());
		char[] a = stream1.toCharArray();
		char[] b = stream2.toCharArray();
		String output = "";
		for(int i = 0; i < minsize; i++) {
			if(add) {
				output = output + AddChars(a[i],b[i]);
			}else {
				output = output + SubChars(a[i],b[i]);
			}
		}
		return output;
	}
	
	
	public static char AddChars(char a, char b) {
		int index1 = ALPHABET.indexOf(a)+1;
		int index2 = ALPHABET.indexOf(b)+1;
		if(a == '*' || b == '*') {
			return '*';
		}
		if(a == '?' || b == '?') {
			return '?';
		}
		if(index1 == -1 || index2 == -1) {
			return '#';//ERROR
		}
		int index3 = index1+index2-1;
		while(index3 >= 26) index3 -=26;
		while(index3 < 0 ) index3 +=26;
		return ALPHABET.charAt(index3);
	}
	
	public static char SubChars(char a, char b) {
		int index1 = ALPHABET.indexOf(a)+1;
		int index2 = ALPHABET.indexOf(b)+1;
		if(a == '*' || b == '*') {
			return '*';
		}
		if(a == '?' || b == '?') {
			return '?';
		}
		if(index1 == -1 || index2 == -1) {
			return '#';//ERROR
		}
		int index3 = index1-index2-1;
		while(index3 >= 26) index3 -=26;
		while(index3 < 0 ) index3 +=26;
		return ALPHABET.charAt(index3);
	}
	
	
	
	
	
}
