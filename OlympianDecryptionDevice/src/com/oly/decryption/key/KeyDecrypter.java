package com.oly.decryption.key;

import java.util.Map;

public class KeyDecrypter {

	
	
	public static String decrypt(String input,Map<Character,Character> map) {
		String output = "";
		for(Character c : input.toCharArray()) {
			if(map.containsKey(c)) {
				output = output +map.get(c);
			}else {
				output = output + c;
			}
		}
		return output;
	}
	
	
	
	
}
