package com.oly.threading;

import com.oly.ui.DecryptTextUI;

public class LaunchAnalysisAutoTask implements Runnable{

	
	protected FrequencyAnalysisTask freq;
	protected TranspositionBruteTask trans;
	protected LexicalAnalysisTask lex;
	
	public LaunchAnalysisAutoTask(DecryptTextUI link) {
		freq = new FrequencyAnalysisTask(link);
		trans = new TranspositionBruteTask(link);
		lex = new LexicalAnalysisTask(link);
	}
	
	@Override
	public void run() {
		freq.run();
		lex.run();
		trans.run();
		lex.run();
	}
	
	
	
	
	
	
}
