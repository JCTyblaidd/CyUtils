package com.jc.solitaire;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.jc.solitaire.search.Modulo26Utils;

public class DeckOrder {
	
	private byte[] data;
	
	public DeckOrder(byte[] data) {
		this.data = data;
	}
	
	public static DeckOrder randomDeckOrder() {
		return new DeckOrder(System.currentTimeMillis());
	}
	
	public DeckOrder() {
		//BORING NON ORDERED
		data = new byte[54];//0pos = 1, 1pos = 2 etc...
		for(int i = 0; i < 54; i++)data[i] = (byte)(i+1);
	}
	
	public DeckOrder(long seed) {//RANDOM
		byte[] touse = new byte[54];
		for(int i = 0; i < 54; i++)touse[i] = (byte)(i+1);
		Random rand = new Random(seed);
		for(int i = 0; i < 54; i++) {
			//CHOOSE
			int choice = rand.nextInt(touse.length);
			data[i] = touse[choice];
			//REMOVE THE DATA
			byte[] replacement = new byte[touse.length - 1];
			byte count = 0;
			for(int q = 0; q < touse.length; q++) {
				byte b = touse[q];
				if(b != touse[choice]) {
					replacement[count] = b;
					count++;
				}
			}
			touse = replacement;
		}
	}
	
	//POSSIBLY TRY AND EXHAUSETIVE KEYBASED SEARCH?? {more realistic}
	public DeckOrder(String key) {
		this();
		key = key.toUpperCase();
		//APPLY THE KEY
		List<Byte> bytes = KeyStreamUtils.toList(data);
		for(char c : key.toCharArray()) {
			KeyStreamUtils.applySolitaireToList(bytes);
			KeyStreamUtils.countcut(KeyStreamUtils.ALPHABET.indexOf(c)+1, bytes);
		}
		this.data = KeyStreamUtils.toArray(bytes);
	}
	
	
	
	//LONG EXHAUSTIVE AND ANNOYING KEYSTREAM GENERATION
	public  String generateKeystream(int len) {
		String output = "";
		List<Byte> temp = KeyStreamUtils.toList(this.data);
		for(int i = 0; i < len; i++) {
			KeyStreamUtils.applySolitaireToList(temp);
			byte out = KeyStreamUtils.outputVal(temp);
			if(out 	< 53) {
				output = output + KeyStreamUtils.ALPHABET.charAt((out - 1) % 26);
			}else {
				i--;//TRY AGAIN
			}
		}
		return output;
	}
	
	public String decrypt(String shizzle) {
		int l = shizzle.length();
		String k = generateKeystream(l);
		return Modulo26Utils.handleStreams(shizzle, k, false);
	}
	public String encrypt(String shizzle) {
		int l = shizzle.length();
		String k = generateKeystream(l);
		return Modulo26Utils.handleStreams(shizzle, k, true);
	}
	
	@Override
	public String toString() {
		return "D:"+Arrays.toString(data);
	}
	@Deprecated
	public void toConsole() {
		System.out.print("D: [");
		for(int i = 0; i < data.length; i++) {
			byte v = data[i];
			if(v == 54 || v == 53) {
				System.out.print(" ");
				System.err.print(v);
			}else {
				System.out.print(" "+v);
			}
			if(i != (data.length - 1)) {
				System.out.print(",");
			}
		}
		System.out.println("]");
	}
	
}
