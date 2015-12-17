package com.jc.main.test;

import com.jc.solitaire.DeckOrder;
import com.jc.solitaire.experimental.KeyStreamAnalysis;


public class TestLaunch {
	
	public static void main(String[] args) {
		//TESTING
		DeckOrder order = new DeckOrder("PONTIFEX");
		String k = order.generateKeystream(30);
		KeyStreamAnalysis.printStreamDiffs(k);
		
		//TEST LAUNCH
		//ExhaustiveSearchLaunch.txt = "WHEAWCEAIWUEH";
		//new ExhaustiveSearchThread(3);
	}
	
}
