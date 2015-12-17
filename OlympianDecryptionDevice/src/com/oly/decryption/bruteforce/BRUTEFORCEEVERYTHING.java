package com.oly.decryption.bruteforce;

import java.util.ArrayList;
import java.util.List;

import com.oly.decryption.key.KeyDecrypter;
import com.oly.decryption.key.KeyDecryptionInformation;
import com.oly.decryption.rail.RailDecrypter;
import com.oly.util.Maths;


/**Hate The Challenge
 * Show it who is BOSS
 * WITH RAW COMPUTATIONAL POWA!!!!
 * HECK YEAH!
 * POWER, POWER, POWER
 * -----------
 * Disclaimer: may take a long time and use a lot of computer power
 * **/
public class BRUTEFORCEEVERYTHING {
	
	/**TODO convert to multi-threaded awesomeness
	 * with some optimization**/
	public static List<String> RUN(String input,boolean affine,boolean key, boolean trans, boolean poly) {
		List<String> acceptedResults = new ArrayList<String>();
		//FIRST AFFINES [ADDALL]
		if(affine) {
			acceptedResults.addAll(KeyDecrypter.bruteAffine(input));
		}
		//SECOND KEYS [ADALL] [1 -> 10 keylength]
		if(key) {
			for(int i = 0; i < Maths.nthPow(10); i++) {
				//acceptedResults.add(KeyDecrypter.decrypt(input, KeyDecrypter.))
				acceptedResults.add(new KeyDecryptionInformation(KeyDecrypter.getKey(10, i)).decrypt(input));
			}
		}
		//THIRD TRANSPOSITIONS [ADALL]
		if(trans) {
			acceptedResults.addAll(RailDecrypter.bruteForceTranspositionLimited(input,8));
		}
		//FORTH POLYALPHABETICS [1 -> 10 keylength]
		if(poly) {
			for(int i = 0; i < Maths.nthPow(10); i++) {
				acceptedResults.add(KeyDecrypter.decrypt_poly(input, KeyDecrypter.getKey(10, i)));
			}
		}
		return acceptedResults;
	}
	
	
	
}
