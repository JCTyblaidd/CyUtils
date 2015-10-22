package com.oly.decryption.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class FrequencyAnalyser {

	public HashMap<Character, Float> standard = new HashMap<Character, Float>();

	public static void main(String[] args) {
		FrequencyAnalyser f = new FrequencyAnalyser("TMDDK, EADDK FA PDMS KAG NMOW UZ , IQ IQDQ TABUZS FA SUHQ KAG EAYQ FUYQ ARR MRFQD FTQ XMEF OMEQ, NGF EAYQFTUZS OMYQ GB MZP IQ ZQQP KAGD TQXB. MF M YQQFUZS AR FTQ RAGD BAIQDE MXXUQP OAZFDAX OAGZOUX FIA IQQWE MSA FTQ RDQZOT MOOGEQP FTQ DGEEUMZE AR ETQXFQDUZS M ZMLU YQPUO WZAIZ ME FTQ DQUOTEPAWFAD. MBBMDQZFXK FTQK UZFQDOQBFQP M YADEQ OAPQ DMPUA NDAMPOMEF RDAY FTQ DGEEUMZ EQOFAD AR NQDXUZ UZ ITUOT FTQ PAOFAD IME ARRQDUZS UZFQXXUSQZOQ MNAGF FTQ DMFXUZQE UZ QJOTMZSQ RAD MEKXGY. FTQ DGEEUMZE OXMUYQP ZAF FA WZAI MZKFTUZS MNAGF UF, MZP YMKNQ FTQK MDQ FQXXUZS FTQ FDGFT, NGF FTUZSE TMHQ NQQZ M XUFFXQ RDAEFK EUZOQ FDGYMZ'E EBQQOT AZ YMDOT FIQXRFT MZP IQ DQMXXK PAZ'F ZQQP YADQ OAZRXUOF DUSTF ZAI. IQ RUSGDQ IUFT KAGD OAZFMOFE AHQD TQDQ KAG YUSTF NQ MNXQ FA RUZP AGF UR FTQ DGEEUMZE MDQ FQXXUZS FTQ FDGFT. U TMHQ MFFMOTQP FTQ QZODKBFQP FDMZEODUBF AR FTQ NDAMPOMEF. OTMDXUQ");
		f.Analyse();
		System.out.println(f.standard.toString());
		f.getPotentials(f.standard, f.frequencies());
	}

	public String data;
	public HashMap<Character,Integer> results = new HashMap<Character,Integer>();

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
			if (alphabet.toLowerCase().contains(String.valueOf(c)) || alphabet.contains(String.valueOf(c))) {
				if(results.containsKey(c)) {
					results.put(c, results.get(c) + 1);
				}else {
					results.put(c,1);
				}
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
		return percentage;
	}

	public HashMap<Character,Character> getPotentials(HashMap<Character, Float> expected, HashMap<Character, Float> acquired) {
		HashMap<Character, Character> potentialValues = new HashMap<Character, Character>();
		ArrayList<Float> numbers = new ArrayList<Float>();
		for (float f : acquired.values()) {
			numbers.add(f);
		}
		ArrayList<Float> std = new ArrayList<Float>();
		for (float n : expected.values()) {
			std.add(n);
		}
		System.out.println(std.toString());
		System.out.println(numbers.toString());
		for (; 0 < numbers.size();) {
			float f = Collections.max((Collection<Float>) numbers);
			float n = Collections.max((Collection<Float>) std);
			potentialValues.put((char)getKey(acquired, f), (char)getKey(expected, n));
			numbers.remove(f);
			std.remove(n);
		}
		for (char c : data.toCharArray()) {
			System.out.print((potentialValues.get(c) == null ? "-" : potentialValues.get(c)));
		}
		System.out.println(potentialValues.toString());
		return potentialValues;
	}
	
	public HashMap<Character,Character> guess_potentials() {
		return getPotentials(this.standard,this.frequencies());
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

	private Object getKey(HashMap<Character, Float> map, Float value) {
		for(Object key : map.keySet()){
			if(map.get(key).equals(value)){
				return key;
			}
		}
		return null;
	}
}
