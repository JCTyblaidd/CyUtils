package com.oly.decryption.rail;

import java.util.ArrayList;
import java.util.List;

import com.oly.util.Permutator;

public class RailDecrypter {
	
	
	
	//WE HAVE DA POWER {Computers FTW}
	public static List<String> bruteForceTransposition(String cypher) {
		int limit = (int) Math.floor(Math.sqrt(cypher.length()));
		List<String> results = new ArrayList<String>();
		for(int i = 1; i < limit; i++) {
			//SPLIT
			String[] data = new String[i];
			int s_lim = (int)Math.ceil(cypher.length() / i);
			for(int v = 0; v < s_lim; v++) {//THE LENGTH
				for(int q = 0; q < i; q++) {//THE WIDTH
					data[q] = data[q] + cypher.charAt((v * i) + q);
				}
			}
			//FOR EACH POSSIBLE ARRANGEMENT REJOIN
			
			Permutator<String> perms = new Permutator<String>(data);
			
			while(perms.hasNext()) {
				String[] temp = perms.next();
				String tout = "";
				//Calculate rejoined string
				for(int x = 0; x < temp[0].length(); x++) {
					for(int y = 0; y < temp.length; y++) {
						tout = tout + temp[y].charAt(x);
					}
				}
				results.add(tout);
			}
		}
		return results;
	}
	
	//WE HAVE DA POWER {Computers FTW}
		public static List<String> bruteForceTranspositionLimited(String cypher,int limit) {
			List<String> results = new ArrayList<String>();
			for(int i = 1; i < limit; i++) {
				//SPLIT
				String[] data = new String[i];
				int s_lim = (int)Math.ceil(cypher.length() / i);
				for(int v = 0; v < s_lim; v++) {//THE LENGTH
					for(int q = 0; q < i; q++) {//THE WIDTH
						if(((v * i) + q) > cypher.length()) {
							data[q] = data[q] + "*";
						}else {
							data[q] = data[q] + cypher.charAt((v * i) + q);
						}
					}
				}
				//FOR EACH POSSIBLE ARRANGEMENT REJOIN
				
				Permutator<String> perms = new Permutator<String>(data);
				
				while(perms.hasNext()) {
					String[] temp = perms.next();
					String tout = "TRANS: " + i + "len \n\n";
					//Calculate rejoined string
					for(int x = 0; x < temp[0].length(); x++) {//THE HEIGHT
						for(int y = 0; y < temp.length; y++) {//THE WIDTH
							if(x < temp[y].length()) {
								tout = tout + temp[y].charAt(x);
							}else {
								tout = tout + "*";
							}
						}
					}
					results.add(tout);
				}
			}
			return results;
		}
		
		
		
		public static List<String> experimentalTranspositionDecrypter(String text,int len) {
			List<Permutator<Character>> perms = new ArrayList<Permutator<Character>>();
			//GENERATE THE PERMS
			for(int i = 0; i < (Math.ceil(text.length() / len)); i+= len) {
				//I indicates char
				
				
			}
			
			
			//EXECUTE
			List<String> results = new ArrayList<String>();
			
			//STIP
			while(perms.get(0).hasNext()) {
				String tout = "";
				
				
				
				results.add(tout);
			}
			
			return results;
		}
	 
	
}
