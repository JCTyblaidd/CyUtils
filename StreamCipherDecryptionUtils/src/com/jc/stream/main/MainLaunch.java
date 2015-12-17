package com.jc.stream.main;

import com.jc.stream.cipher.XORutils;
import com.jc.stream.gui.StreamCipherGUI;

public class MainLaunch {
	
	
	
	
	
	public static void main(String[] args) {
		//SOME TEXT YOU COULD DEBUG WITH
		String text1 = "HELLO MY NAME IS FRED AND I LIKE CHEESE WHICH IS GOOD CONSIDERING STUFF IS TERRIBLE AND I HAVE NO IDEA ABOUT BANANAS HOWEVER I LIKE CAKE IS ALL YOU NEED TO KNOW REALLY IF YOU WANT TO KNOW SOMETHING ELSE YOU ARE CLEARLY A REALLY CRAZY INSANE PERSON WHO IS INSANE - DID I SAY YOU WERE INSANE";
		String key = "WEIUHOMAYGEONUCGAWUEONAWUEGCNOAWUEGYWEAWEBIUHODAYSGWER";
		System.out.println(key.length());
		for(int i = 0; i < 3; i++) {
			key = key + key;
		}
		System.out.println(key.length());
		System.out.println(XORutils.XOR_streams(text1.replace(" ",""), key));
		new StreamCipherGUI();
	}
	
}
