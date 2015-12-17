package com.oly.threading;

import java.util.List;

import com.oly.decryption.rail.RailDecrypter;
import com.oly.ui.DecryptTextUI;
import com.oly.util.Logger;

public class TranspositionBruteTask implements Runnable{
	
	public DecryptTextUI linked;
	String text;
	
	
	public TranspositionBruteTask(DecryptTextUI link) {
		linked = link;
		text = link.cypher.substring(0);
	}


	@Override
	public void run() {
	//	List<String> results = RailDecrypter.bruteForceTransposition(text);
		List<String> results = RailDecrypter.bruteForceTranspositionLimited(text,8);
		linked.possibilities_trans.addAll(results);
		linked.poss_dirty_trans = true;
		Logger.instance.INFO("Transpotition Decryption Task finished");
	}
	
	
	
}
