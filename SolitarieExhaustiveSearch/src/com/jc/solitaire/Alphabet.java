package com.jc.solitaire;

public class Alphabet {
	
	
	public static char charAt(int index) {
		return (char)(index | 0b01000000);
	}
	
	public static int indexOf(char c) {
		return (int) (c & 0b11111);
	}
	
}
