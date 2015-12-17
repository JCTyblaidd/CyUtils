package com.oly.threading;

import com.oly.ui.DecryptTextUI;

public class TryEverythingAndScrewTheChallengeTask implements Runnable{
	
	public DecryptTextUI linked;
	public String text;
	
	public TryEverythingAndScrewTheChallengeTask(DecryptTextUI link) {
		this.linked = link;
		text = link.cypher.substring(0);
	}

	//TODO add support to the gui for this crazyness
	@Override
	public void run() {
		
		//List<String> brutes = BRUTEFORCEEVERYTHING.RUN(text);
	}
	
	
	
	
}
