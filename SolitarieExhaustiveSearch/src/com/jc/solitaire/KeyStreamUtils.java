package com.jc.solitaire;

import java.util.ArrayList;
import java.util.List;

public class KeyStreamUtils {
	
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static List<Byte> toList(byte[] bytes) {
		List<Byte> deck = new ArrayList<Byte>();
		for(int i = 0; i < 54; i++) deck.add(bytes[i]);
		return deck;
	}
	
	public static byte[] toArray(List<Byte> deck) {
		byte[] result = new byte[54];
		for(int i = 0; i < 54; i++) result[i] = deck.get(i);
		return result;
	}
	
	//APPLYS SOLITAIRE TO THE DATA
	public static byte[] performSolitaire(byte[] key) {
		if(key.length != 54) System.err.println("[ERR] WONKY DECK!!!");
		List<Byte> deck = toList(key);
		applySolitaireToList(deck);
		//CONVERT BACK
		return toArray(deck);
	}
	
	public static void applySolitaireToList(List<Byte> list) {
		Runsolitaire(list);//AHA
	}
	
	private static int indexOf(List<Byte> deck, int search) {
		for(int i = 0; i < deck.size(); i++) {
			if(deck.get(i) == search) {
				return i;
			}
		}
		return -1;
	}
	
	//EXTRACTED FROM WEB IMPLEMENTATION
	//AND CONVERTED FROM JS >> JAVA
	
	public static void jo1shift(List<Byte> deck){
		int jo1pos = indexOf(deck, 53);
		byte[] stor = new byte[2];
		if(jo1pos == 53){
			stor[0] = deck.get(0);
			stor[1] = deck.get(0);
			for(int i = 1; i < 54; i++) {
				stor[i%2] = deck.get(i);
				deck.set(i, stor[(i+1)%2]);
			}
			deck.set(0, (byte)53);
		}else{
		//if(jo1pos != 53){
			stor[0] = deck.get(jo1pos+1);
			deck.set(jo1pos+1,(byte)53);
			deck.set(jo1pos,stor[0]);
		}
		//System.out.println("J1  =>  " + deck.toString());
	}

	public static void jo2shift(List<Byte> deck){
		int jo2pos = indexOf(deck, 54);
		byte[] stor = new byte[2];

		if(jo2pos == 52){
			stor[0] = deck.get(jo2pos-52);
			stor[1] = deck.get(jo2pos-52);
			for(int i = (jo2pos-51); i < 53; i++) {
				stor[i % 2] =  deck.get(i);
				deck.set(i, stor[(i + 1)%2]);
			}
			deck.set(jo2pos - 51, (byte)54);
		}else
		if(jo2pos==53){
			stor[0] =  deck.get(jo2pos-52);
			stor[1] = deck.get(jo2pos-52);
			for(int i = (jo2pos-51);i<54;i++) {
				stor[i % 2] = deck.get(i);
				deck.set(i, stor[(i + 1)%2]);
			}
			deck.set(jo2pos - 51, (byte)54);
		}else
		if(jo2pos<52){
			stor[0] = deck.get(jo2pos+1);
			stor[1] = deck.get(jo2pos+2);
			deck.set(jo2pos+2, (byte)54);
			deck.set(jo2pos, stor[0]);
			deck.set(jo2pos+1, stor[1]);
		}
		//System.out.println("J2  =>  " + deck.toString());
	}

	public static void triplecut(List<Byte> deck){
		int jo1pos = indexOf(deck, 53);
		int jo2pos = indexOf(deck, 54);

		if(jo1pos<jo2pos){
			List<Byte> topdeck = new ArrayList<>();
			List<Byte> middeck = new ArrayList<>();
			List<Byte> botdeck = new ArrayList<>();
			for(int i = 0; i < jo1pos; i++){topdeck.add(deck.get(i));}
			for(int i = 0; i < (jo2pos-jo1pos+1);i++){middeck.add(deck.get(i+jo1pos));}
			for(int i = 0; i < (53 - jo2pos); i++){botdeck.add(deck.get(i+jo2pos+1));}
			//SET
			deck.clear();
			deck.addAll(botdeck);
			deck.addAll(middeck);
			deck.addAll(topdeck);
		}

		if(jo2pos<jo1pos){
			List<Byte> topdeck = new ArrayList<>();
			List<Byte> middeck = new ArrayList<>();
			List<Byte> botdeck = new ArrayList<>();
			for(int i = 0; i < jo2pos; i++){topdeck.add(deck.get(i));}
			for(int i = 0; i < (jo1pos-jo2pos+1); i++){middeck.add(deck.get(i+jo2pos));}
			for(int i = 0; i < (53-jo1pos); i++){botdeck.add(deck.get(i+jo1pos+1));}
			deck.clear();
			deck.addAll(botdeck);
			deck.addAll(middeck);
			deck.addAll(topdeck);
		}
		//System.out.println("TRIP => " + deck.toString());
	}

	public static void countcut(int count,List<Byte> deck){
		if(count==54){count=53;}
		List<Byte> botd = new ArrayList<Byte>();
		List<Byte> topd = new ArrayList<Byte>();
		for(int i = 0; i < count;i++){topd.add(deck.get(i));}
		for(int i = 0; i < (53-count);i++){botd.add(deck.get(i+count));}
		for(int i = 0; i < botd.size(); i++){deck.set(i,botd.get(i));}
		for(int i = 0; i < topd.size(); i++){deck.set(i+botd.size(),topd.get(i));}
		//System.out.println("CUT =>  " + deck.toString() + " @ " + count);
	}


	private static void Runsolitaire(List<Byte> deck){
		jo1shift(deck);
		jo2shift(deck);
		triplecut(deck);//WHY COUNTCUT?
		countcut(deck.get(53),deck);
	}
	
	public static byte outputVal(List<Byte> deck){
		int index = deck.get(0);//
		if(index==54){index=53;}//
		return deck.get(index);///
	}
	
}
