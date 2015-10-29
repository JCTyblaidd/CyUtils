package com.oly.threading;

import java.util.List;

import com.oly.decryption.rail.RailDecrypter;
import com.oly.ui.DecryptTextUI;
import com.oly.util.Logger;

public class TranspositionBruteTask implements Runnable{
	
	public DecryptTextUI linked;
	
	
	public TranspositionBruteTask(DecryptTextUI link) {
		linked = link;
	}


	@Override
	public void run() {
		List<String> results = RailDecrypter.bruteForceTransposition(linked.cypher);
		linked.possibilities_trans.addAll(results);
		linked.poss_dirty_trans = true;
		Logger.instance.INFO("Transpotition Decryption Task finished");
	}
	
	
	
}
