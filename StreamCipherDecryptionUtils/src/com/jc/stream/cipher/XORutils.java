package com.jc.stream.cipher;

public class XORutils {
	
	//NB a => 65 => 010[0 0001] <NUMBERS USED
	//BN A => 97 => 01100 0001
	
	/** OUTPUTS AS CAPITAL VIA BITWISE MANIPULATION
	**  IGNORES *  and ? AS A CHARACTER IN EITHER STREAM       
	* POSS INVALIDS: @ or [ or ] or \ or ^ or _   */
	public static String XOR_streams(String a,String b) {
		char[] acs = a.toCharArray();
		char[] bcs = b.toCharArray();
		int len = Math.min(acs.length, bcs.length);
		//if(len !=  54) {
		//	System.out.println("XOR => NOT 54 + >>" + len);
		//}
		String output = "";
		for(int i = 0; i < len; i++) {
			if(acs[i] == '*' || bcs[i] == '*') {
				output = output + "*";
			}else if(acs[i] == '?' || bcs[i] == '?'){
				output = output + "?";
			}else {
				//char resultant =(char)(((acs[i] & 0b11111) ^ (bcs[i] & 0b11111)) | 0b01000000);
				//output = output + resultant;
				output = output + XOR_char(acs[i],bcs[i]);
			}
		}
		return output;
	}
	
	//private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**UTIL ANALYSIS FUNCTIONALITY*/
	public static char XOR_char(char a,char b) {
		if(a == '*' || b == '*') {
			return '*';
		}else if(a == '?' || b == '?'){
			return  '?';
		}else {
			char resultant =(char)(((a & 0b11111) ^ (b & 0b11111)) | 0b01000000);
			return resultant;//TERRIBLE TODO HIJACK
			//NEW HIJACKED ADDATIVE CIPHER SHIZZLE
			
			//////////////////////////////////////////
			//int loc1 = alphabet.indexOf(Character.toUpperCase(a));
			////int loc2 = alphabet.indexOf(Character.toUpperCase(b));
			//if(a == -1 || b == -1) {
			//	return '#';//NEW ERROR CODE
			///}
			//int loc3 = loc1 - loc2;
			//if(loc3 <= 0) loc3 += 26;
			//if(loc3 > 26) loc3 -= 26;
			//
			//return alphabet.charAt(loc3);
		}
	}
	
	
	
}
