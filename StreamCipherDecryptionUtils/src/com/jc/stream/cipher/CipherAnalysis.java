package com.jc.stream.cipher;

import java.util.ArrayList;
import java.util.List;

public class CipherAnalysis {
	
	
	//TODO attack based upon the a ^ b => c where they are limited to numbers 1-26 
	//but in the 0-31 search-space THEREFORE IF THIS IS VALID MANY PERMUTATIONS CAN BE ELIMITATED
	/**TEXT IS CIPHERTEXT,LEN is length of key,int is the max value of the key from 1 -> max*/
	public static List<List<Integer>> getPermutations(String text, int len,int maxkey) {
		List<List<Integer>> toreturn= new ArrayList<List<Integer>>();
		//SPLIT INTO LEN BASED SEARCHSPACE
		List<List<Character>> searchspaces = new ArrayList<List<Character>>();
		//INITIALISE
		for(int i = 0; i < len; i++) searchspaces.add(new ArrayList<Character>());	
		for(int i = 0; i < len; i++) toreturn.add(new ArrayList<Integer>());
		int current = 0;
		for(char c : text.toCharArray()) {
			searchspaces.get(current).add(c);
			current++;
			if(current >= len) {
				current = 0;
			}
		}
		
		//WORK OUT VALIDS
		for(int index = 0; index < len; index++) {
			List<Character> searches = searchspaces.get(index);
			boolean valid = true;
			for(int offset = 1; offset < maxkey; offset++) {
				valid = true;
				for(char c : searches) {
					char result = XORutils.XOR_char(c, (char)offset);
					if(result == '@' || result == '[' || result == ']' || result == '_' || result == '\\') {
						valid = false;
						break;//STOP TRYING ONCE WE FIND A COUNTEREXAMPLE
					}
				}
				if(valid) {
					toreturn.get(index).add(offset);
				}
			}	
		}
		//DEBUG ANNOUNCE PERMUTATIONS (whatever is better than 26!2!2!)
		//long perms = 1;
		double dperms = 1;
		for(int i = 0; i < len; i++) {
			//perms *= toreturn.get(i).size();
			dperms *= toreturn.get(i).size();
		}
		System.out.println("Calculated w/ " + dperms + "possible permutations");
		double minutes = dperms / 60000;//MAYBE x10?
		System.out.println("Coming est. to " + minutes + " mins of processing time");
		return toreturn;
	}
	
	
	
}
