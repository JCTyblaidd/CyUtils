package com.oly.decryption.rail;

import java.util.ArrayList;
import java.util.List;

import com.oly.util.Permutator;

public class RailDecrypter {
	
	
	
	//WE HAVE DA POWER {Computers FTW}
	public static List<String> bruteForceTransposition(String cypher) {
		int limit = (int) Math.floor(Math.sqrt(cypher.length()));
		List<String> results = new ArrayList<String>();
		for(int i = 0; i < limit; i++) {
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
	
	
	
	
	
}
