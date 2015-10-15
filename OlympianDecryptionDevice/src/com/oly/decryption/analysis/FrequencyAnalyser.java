package com.oly.decryption.analysis;

import java.util.ArrayList;
import java.util.HashMap;

public class FrequencyAnalyser {
	
	HashMap<Character, Float> standard = new HashMap<Character, Float>();

	public static void main(String[] args) {
		FrequencyAnalyser f = new FrequencyAnalyser("HARRY, SORRY TO DRAG YOU BACK IN , WE WERE HOPING TO GIVE YOU SOME TIME OFF AFTER THE LAST CASE, BUT SOMETHING CAME UP AND WE NEED YOUR HELP.AT A MEETING OF THE FOUR POWERS ALLIED CONTROL COUNCIL TWO WEEKS AGO THE FRENCH ACCUSED THE RUSSIANS OF SHELTERING A NAZI MEDIC KNOWN AS THE REICHSDOKTOR. APPARENTLY THEY INTERCEPTED A MORSE CODE RADIO BROADCAST FROM THE RUSSIAN SECTOR OF BERLIN IN WHICH THE DOCTOR WAS OFFERING INTELLIGENCE ABOUT THE RATLINES IN EXCHANGE FOR ASYLUM. THE RUSSIANS CLAIMED NOT TO KNOW ANYTHING ABOUT IT, AND MAYBE THEY ARE TELLING THE TRUTH, BUT THINGS HAVE BEEN A LITTLE FROSTY SINCE TRUMAN'S SPEECH ON MARCH TWELFTH AND WE REALLY DON'T NEED MORE CONFLICT RIGHT NOW. WE FIGURE WITH YOUR CONTACTS OVER HERE YOU MIGHT BE ABLE TO FIND OUT IF THE RUSSIANS ARE TELLING THE TRUTH. I HAVE ATTACHED THE ENCRYPTED TRANSCRIPT OF THE BROADCAST. CHARLIE");
		f.Analyse();
		System.out.println(f.standard.toString());
		f.getPotentials(f.standard, f.frequencies());
	}

	String data;
	HashMap<Character,Integer> results = new HashMap<Character,Integer>();

	public FrequencyAnalyser(String str) {
		data = str;
		populateInefficiently();
	}


	public void Analyse() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(char c : alphabet.toCharArray()) { //TO ENSURE IT LOOKS NICER
			results.put(c, 0);
		}

		for(char c : data.toCharArray()) {
			if(results.containsKey(c)) {
				results.put(c, results.get(c) + 1);
			}else {
				results.put(c,1);
			}
		}
	}


	@Override
	public String toString() {
		return results.toString();
	}

	public HashMap<Character, Float> frequencies() {
		int total = 0;
		for (Character c : results.keySet()) {
			total += results.get(c);
		}
		HashMap<Character, Float> percentage = new HashMap<Character, Float>();
		for (Character c : results.keySet()) {
			percentage.put(c, ((results.get(c)/((float)total))*100));
		}
		System.out.println(percentage.toString());
		return percentage;
	}
	
	public void getPotentials(HashMap<Character, Float> expected, HashMap<Character, Float> acquired) {
		HashMap<Character, ArrayList<Character>> potentialValues = new HashMap<Character, ArrayList<Character>>();
		for (Character c : acquired.keySet()) {
			for (Character d : expected.keySet()) {
				if (Math.abs(acquired.get(c) - expected.get(d)) < 0.5) {
					if (potentialValues.get(c) != null) {
						potentialValues.get(c).add(d);
					} else {
						potentialValues.put(c, new ArrayList<Character>());
						potentialValues.get(c).add(d);
					}
				}
			}
		}
		System.out.println(potentialValues.toString());
	}
	
	private void populateInefficiently() {
		standard.put('A', 8.2f);
		standard.put('B', 1.5f);
		standard.put('C', 2.8f);
		standard.put('D', 4.3f);
		standard.put('E', 12.7f);
		standard.put('F', 7.2f);
		standard.put('G', 2.0f);
		standard.put('H', 6.1f);
		standard.put('I', 7.0f);
		standard.put('J', 0.2f);
		standard.put('K', 0.8f);
		standard.put('L', 4.0f);
		standard.put('M', 2.4f);
		standard.put('N', 6.7f);
		standard.put('O', 7.5f);
		standard.put('P', 1.9f);
		standard.put('Q', 0.1f);
		standard.put('R', 6.0f);
		standard.put('S', 6.3f);
		standard.put('T', 9.1f);
		standard.put('U', 2.8f);
		standard.put('V', 1.0f);
		standard.put('W', 2.4f);
		standard.put('X', 0.2f);
		standard.put('Y', 2.0f);
		standard.put('Z', 0.1f);
	}
	
}
