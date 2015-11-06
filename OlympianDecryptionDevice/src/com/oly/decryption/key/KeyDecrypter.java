package com.oly.decryption.key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oly.lexical.analysis.LexicalAnalyser;
import com.oly.util.Logger;

public class KeyDecrypter {

	private static final String Alphabet =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String decrypt(String input,Map<Character,Character> map) {
		String output = "";
		/**
		for(Character c : input.toCharArray()) {//TODO fix wrong way around
			if(map.containsKey(c)) {
				output = output +map.get(c);
			}else if(map.containsKey(Character.toLowerCase(c))) {
				output = output + Character.toLowerCase(map.get(Character.toLowerCase(c)));
			}else if(map.containsKey(Character.toUpperCase(c))) {
				output = output + Character.toUpperCase(map.get(Character.toUpperCase(c)));
			}
			else {
				output = output + c;
			}
		}
		**/
		for(Character c : input.toCharArray()) {
			for(char q : map.keySet()) {
				if(map.get(q) == c) {
					output = output + q;
					break;
				}else if(Character.toLowerCase(map.get(q)) == Character.toLowerCase(c)) {
					output = output + Character.toLowerCase(q);
				}else if(Character.toUpperCase(map.get(q)) == Character.toUpperCase(c)) {
					output = output + Character.toUpperCase(q);
				}
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
		if(amounts > Integer.MAX_VALUE) {
			Logger.instance.SEVERE("[DecryptAll] Too many perms");
			Logger.instance.SEVERE(amounts + ">" + Integer.MAX_VALUE);
			return outputs;
		}
		Logger.instance.INFO("Decrypting Aim of:" + amounts + " possibilities.");
		///ITERATE
		for(long i = 0; i < amounts; i++) {
			outputs.add(decrypt(cypher,getNthMap(map, i)));
		}
		return outputs;
	}
	
	public static List<String> getAllowedDecryptions(Map<Character,String> map,String cypher,float minscore) {
		List<String> outputs = new ArrayList<String>();
		//W
		long amounts =1;
		for(char c : map.keySet()) {
			amounts *= map.get(c).length();
		}
		if(amounts > Integer.MAX_VALUE) {
			Logger.instance.SEVERE("[DecryptAll] Too many perms");
			Logger.instance.SEVERE(amounts + ">" + Integer.MAX_VALUE);
			return outputs;
		}
		Logger.instance.INFO("Decrypting Aim of:" + amounts + " possibilities.");
		String temp = "";
		for(long i = 0; i < amounts; i++) {
			temp = decrypt(cypher,getNthMap(map, i));
			if(LexicalAnalyser.rate(cypher, null) > minscore) {
				outputs.add(temp);
			}
		}
		return outputs;
	}
	
	
	private static Map<Character,Character> getNthMap(Map<Character,String> map, long num) {
		Map<Character,Character> result = new HashMap<Character,Character>();
		int temp = 1;//WHAT IS USED FOR THE MODULUS
		for(char c : map.keySet()) {
			result.put(c, map.get(c).charAt((int)((Math.floorDiv(num, temp)) % (map.get(c).length()))));
			temp *= map.get(c).length();
		}
		return result;
	}
	
	///////////////////////BORING OPTIONS///////////////////////////////////
	
	//ax + b (make shift1 = 1 for ceasar
	public static Map<Character,Character> getConversionAffine(int shift1,int shift2) {
		Map<Character,Character> output = new HashMap<Character,Character>();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i = 0; i < alphabet.length();i++) {
			output.put(alphabet.charAt((i + 1) % alphabet.length()),
					alphabet.charAt((((i + 1) * shift1) + shift2) % alphabet.length()));
		}
		return output;
	}
	
	public static String shiftAffine(String input, int shift1, int shift2) {
		return "AFF="+shift1+"x+"+shift2+" \n " + decrypt(input, getConversionAffine(shift1,shift2));
	}
	
	public static List<String> bruteAffine(String input) {
		List<String> output = new ArrayList<String>();
		for(int i = 0; i < 26; i++) {
			for(int j = 0; j < 26; j++) {
				output.add(shiftAffine(input,i,j));
			}
		}
		return output;
	}
	
	public static String decrypt_poly(String input,String key) {
		String output = "";
		int i = 0;
		int q = 0;
		int z = 0;
		for(char c : input.toCharArray()) {
			q = Alphabet.indexOf(key.charAt(i));
			z = Alphabet.indexOf(c);
			output = output + Alphabet.charAt((q + z + 1) % 26);
		}
		output = "POLY="+key+" \n " + output; //ADD KEY INFORMATION
		return output;
	}
	
	
	public static List<String> smart_brute_polyalphabet(String input) {
		//Smart Repetition Based analysis to work out keyword length
		String temp = input;
		int best_shift = 0;
		int best_count = -100;
		int temp_count = 0;
		for(int i = 1; i < input.length(); i++) {
			temp_count = 0;
			temp = shift(temp,1);
			for(int q = 0; q < input.length(); q++) {
				if(input.charAt(q) == temp.charAt(q)) {
					temp_count++;
				}
			}
			if(temp_count > best_count) {
				best_count = temp_count;
				best_shift = i;
			}
		}
		List<String> results = new ArrayList<String>();
		Logger.instance.LOG("FOUND KEY LENGTH {POLY} = " + best_shift + "w/ " + best_count + "/" + input.length() + "repeats");
		if(best_shift < 5) {//BRUTE FORCEABLE //wmax 500000 possibilities :D  =ish 87mbs
			for(int j = 0; j <Math.pow(26, best_shift); j++) {
				String key = getKey(best_shift,j);
				results.add(decrypt_poly(input, key));
			}
		}else {//TRY BE SMARTER ABOUT THE WHOLE THING
			//CEASAR SHIFT FREQUENTIAL ANALYSIS //TERRIBLE e-max option but will work probably
			String[] splits = new String[best_shift];
			int[] shifts = new int[best_shift];
			for(int i = 0; i < best_shift; i++) {
				splits[i] = getSubShiftString(input, i, best_shift);
			}
			//TODO have e = top 3 instead
			for(int i = 0; i < best_shift; i++) {
				shifts[i] = -1;
				for(int j = 0; j < 26; j++) {
					if(isEMax(shiftAffine(input, 0, j))) {
						shifts[i] = j;
						break;
					}
				}
			}
			/////////////////////////DECRYPT BEST GUESS//////////////////////
			String key = "";
			Logger.instance.LOG("Smart key=" + key);
			for(int i = 0; i < best_shift; i++) {
				key = key + Alphabet.charAt(shifts[i]);
			}
			results.add(decrypt_poly(input, key));
		}
		
		return results;//TODO shift ferquential analysis till one makes sense
	}
	
	
	private static String getSubShiftString(String text, int shift, int size) {
		String temp = "";
		int t = 0;
		for(int v = 0; v < (Math.floorDiv(text.length(), size) + 1); v++) {
			t = (v *size) + shift;
			if(t < text.length()) {
				temp = temp + text.charAt(t);
			}
		}
		return temp;
	}
	
	
	public static boolean isEMax(String text) {
		int[] counts = new int[26];
		for(int i = 0; i < 26; i++) {
			counts[i] = 0;
		}
		for(char c : text.toCharArray()) {
			counts[Alphabet.indexOf(Character.toUpperCase(c))] ++;
		}
		int max = -100;
		for(int i : counts) {
			if(i > max) {
				max = i;
			}
		}
		if(max == counts[Alphabet.indexOf('E')]) {
			return true;
		}
		return false;
	}
	
	public static String shift(String text,int shifts) {
		String temp  = text;
		for(int i = 0; i < shifts; i++) {
			temp = temp.charAt(temp.length()-1) + temp.substring(1);
		}
		return temp;
	}
	
	public static List<String> brute_polyalphabetic(String input) {
		int len = input.length() / 8;//MAX poly_size();
		List<String> results = new ArrayList<String>();
		//Calc count
		if(len > 5) {
			len = 5;//OTHERWISE TO MUCH CALCULATIONNESS
		}
		
		for(int i = 1; i < len; i++) {
			//FOR EACH POSSIBLE KEY OF EACH SIZE
			for(int j = 0; j <Math.pow(26, len); j++) {
				String key = getKey(i,j);
				results.add(decrypt_poly(input, key));
			}
		}
		
		return results;
	}
	
	private static String getKey(int size,int num) {
		String output = "";
		int temp = 26;
		for(int i = 0; i < size; i++) {
			output = output + Alphabet.charAt(num % temp);
			temp *= 26;
		}
		return output;
	}
}
