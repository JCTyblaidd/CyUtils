package com.jct.settings;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class CipherSettings {
	
	public static Map<Integer,Integer> cardTranslation = new HashMap<Integer,Integer>();
	
	public static void Load() {
		LoadCardValueTranslations();
		//LoadSuitValueTranslations();
	}
	
	
	private static void LoadCardValueTranslations() {
		final int baseShift = 1;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++) {
				int SolitaireIndex = (i * 13) + j;
				int Shift = j;
				cardTranslation.put(SolitaireIndex, Shift);
			}
		}
		//TODO WTF DO JOKERS DO
	}
	
	private static void LoadSuitCardValueTranslations() {
		final int baseShift = 1;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++) {
				int SolitaireIndex = (i * 13) + j;
				int Shift = j + ((i % 2) == 1 ? 13 : 0);//Premodulated Shift
				cardTranslation.put(SolitaireIndex, Shift);
			}
		}
		//TODO WTF DO JOKERS DO
	}
	
	
	
	
}
