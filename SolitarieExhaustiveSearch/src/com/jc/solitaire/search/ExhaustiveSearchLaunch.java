package com.jc.solitaire.search;

import java.util.ArrayList;
import java.util.List;

public class ExhaustiveSearchLaunch {
	
	public static String txt;
	
	public static volatile List<String> solutions = new ArrayList<String>();
	
	public static void main(String[] args) {
		System.out.println(Modulo26Utils.AddChars('I', 'T'));
		
		txt = args[0].trim().replace(" ", "").toUpperCase();
		int lim = 10;
		if(args.length >= 2) {
			lim = Integer.parseInt(args[1]);
		}
		//START THE THREADS
		for(int i = 0; i <= lim; i++) {//MULTITHREADED JOY!
			new ExhaustiveSearchThread(i);
		}
		new ExhaustivePossibilitySpew();
		//WOLOLOL	//TESTING SHIZZLE => SEEMS AND ERROR ARRISED FROM MY ENCODING
		//DeckOrder test = new DeckOrder("CAKE");		//BUT IT IS FIXED
		//String cakify = test.decrypt(txt);
		//System.out.println("DBG CAKE: " + cakify);
		//String orig = "THELOCATIONOFOURSAFEHOUSEISASECRETHOWEVERIWOULDLIKEFORYOUTOCONSIDERTHENAZIEVILESHIZZLETOBEANIMPORTANTTHINGTOMESSAGEMEABOUTTHEIRLOCATION";
		//String recrypt = test.encrypt(orig);
		//String keystream = test.generateKeystream(orig.length());
		//System.out.println("DBG CAKEK: " + keystream);
		//System.out.println("DBG CAKE2: " + recrypt);
	}
	
	
	
}
