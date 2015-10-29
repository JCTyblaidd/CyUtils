package com.oly.decryption.key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oly.util.Logger;

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
	
	
	public static List<String> getDecryptions(Map<Character,String> map,String cypher) {
		List<String> outputs = new ArrayList<String>();
		//Work out possibilities
		long amounts = 1;
		for(char c : map.keySet()) {
			amounts *= map.get(c).length();
			
		}
		Logger.instance.INFO("Decrypting Aim of:" + amounts + " possibilities.");
		///ITERATE
		for(long i = 0; i < amounts; i++) {
			outputs.add(decrypt(cypher,getNthMap(map, i)));
		}
		return outputs;
	}
	
	
	private static Map<Character,Character> getNthMap(Map<Character,String> map, long num) {
		Map<Character,Character> result = new HashMap<Character,Character>();
		int temp = 1;//WHAT IS USED FOR THE MODULUS
		for(char c : map.keySet()) {
			temp *= map.get(c).length();
			result.put(c, map.get(c).charAt((int)((Math.floorDiv(num, temp)) % (map.get(c).length()))));
		}
		return result;
	}
	
	
}
