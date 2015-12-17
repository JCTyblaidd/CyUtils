package com.oly.decryption.analysis;

import java.util.ArrayList;
import java.util.List;

import com.oly.decryption.key.KeyDecrypter;
import com.oly.util.Logger;
import com.oly.util.Maths;

public class SmartDecrypter {
	
	//EXPERIMENTAL REALLY CLEVER STAT ANALYSIS SHIZZLE
	private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	public static List<String> statisticallyAnalysePoly(String text) {
		//REPEATS ANALYSIS
		int maxsize = 10;//(int) (Math.floor(Math.sqrt(text.length())) / 2);
		List<Integer> goodsizes = new ArrayList<Integer>();
		for(int i = 2; i < maxsize; i++) {
			
			int rate = rateValidSize(text, i);
			System.out.println("SIZE w/" + i + ">" + rate);
			if( rate > 1) {
				goodsizes.add(i);
			}
		}
		//TODO act upon this
		
		List<String> results = new ArrayList<String>();
		for(Integer attempt : goodsizes) {
			results.add(decryptFromLetterRepeats(text, attempt));
		}
		return null;
	}
	
	private static int rateValidSize(String text, int size) {
		String[] strs = new String[size];
		for(int i = 0; i < text.length(); i++) {
			int num = Maths.Mod_Clamp(i, size);
			strs[num] = strs[num] + text.charAt(i);
		}
		int rate = 0;
		for(String str : strs) {
			int repeat = getMaxLetterRepetition(str);
			if(repeat > 12) {
				rate += 3;
			}else if(repeat > 11) {
				rate += 2;
			}else if(repeat > 9) {
				rate ++;
			}else if(repeat < 7) {
				rate --;
			}
		}
		return rate;
	}
	
	private static int getMaxLetterRepetition(String text) {
		int[] repeats = new int[26];
		for(char c : text.toLowerCase().toCharArray()) {
			try{
				repeats[alphabet.indexOf(c)] ++;
			}catch(Exception e) {
				
			}
		}
		//FIND MAX
		int maxVal = -100000;
		for(int i = 0; i < 26; i++) {
			if(repeats[i] > maxVal) {
				maxVal = repeats[i];
			}
		}
		return maxVal;
	}
	
	//SHOULD BE PRIVATE
	@Deprecated
	public static int getShiftFromLetterRepetition(String text) {
		int[] repeats = new int[26];
		for(char c : text.toLowerCase().replace(" ","").toCharArray()) {
			try{
				repeats[alphabet.indexOf(c)] ++;
			}catch(Exception e) {
				System.out.println("SYMBOL!");
			}
		}
		int maxVal = -100;
		int maxChar = 0;//E or T or A //MOST LIKELY T
		for(int i = 0; i < 26; i++) {
			if(repeats[i] > maxVal) {
				maxVal = repeats[i];
				maxChar = i;
			}
		}
		int maxVal2 = -100;
		int maxChar2 = 0;//E or T or A //MOST LIKELY T
		for(int  i = 0; i < 26; i++) {
			if(repeats[i] > maxVal2 && i != maxChar) {
				maxVal2 = repeats[i];
				maxChar2 = i;
			}
		}
		int maxVal3 = -100;
		int maxChar3 = 0;
		for(int i = 0; i < 26; i++) {
			if(repeats[i] > maxVal3 && i != maxChar && i != maxChar2) {
				maxVal3 = repeats[i];
				maxChar3 = i;
			}
		}
		//FROM THIS ATTEMPT TO DETERMINE THE SHIFT THAT WAS USED
		int shift1_2 = Math.abs(maxChar - maxChar2);
		int shift2_3 = Math.abs(maxChar3 - maxChar2);
		int shift3_1 = Math.abs(maxChar3 - maxChar);
		
		//FROM THE SHIFTS ORDER BIGGEST = 
		Logger.instance.LOG("shifts => " + shift1_2 + ", " + shift2_3 + ", " + shift3_1);
		
		return Maths.Mod_Clamp(maxChar - 4, 26);//26 - maxChar if you want to decrypt
	}
	
	private static String decryptFromLetterRepeats(String text, int size) {
		int[] shifts = new int[size];
		String[] strs = new String[size];
		for(int i = 0; i < text.length(); i++) {
			int num = Maths.Mod_Clamp(i, size);
			strs[num] = strs[num] + text.charAt(i);
		}
		for(int i = 0; i < size; i++) {
			//shifts[i] = Maths.Mod_Clamp(26 - ShiftAnalyse.analyseShift(strs[i]),26);
			shifts[i] = ShiftAnalyse.analyseShift(strs[i]);
		}
		//DECRYPT
		String key = "";
		for(int i = 0; i < size; i++) {
			key = key + alphabet.charAt(shifts[i]);
		}
		Logger.instance.LOG("FOUND SMART KEY w/ size = " + size + " => " + key);
		//for(int i = 0; i < size; i++) {
		//	Logger.instance.LOG("text" + i,strs[i]);
		//}
		return KeyDecrypter.decrypt_poly(text, key);
	}
	
	
}
