package com.jct.cipher;

import com.jct.settings.CipherSettings;
import com.jct.util.DeckOrder;

@SuppressWarnings("all")//DO NOT CARE WHATSOEVER
public class SolitareCipherInstance {
	
	
	private String text;
	public static SolitareCipherInstance instance;
	private DeckOrder deck;
	
	public SolitareCipherInstance(String text) {
		this(text,new DeckOrder());//RANDOM ORDER - NOT GOOD FOR DECRYPTION
	}
	
	public SolitareCipherInstance(String text,DeckOrder order) {
		instance = this;
		this.text = text;
		CipherSettings.Load();
		deck = order;
		Decrypt();
	}
	
	
	
	private void Decrypt() {
		//SPIN OFF FUNCTIONALITY
		
		//CAKE
		//ME LIKES CAKE
		//YOU - YOU LIKE CAKE
		
		
	}
	
	
}
