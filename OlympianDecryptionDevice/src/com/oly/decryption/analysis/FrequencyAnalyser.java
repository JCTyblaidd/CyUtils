package com.oly.decryption.analysis;

import java.util.HashMap;

public class FrequencyAnalyser {

	public static void main(String[] args) {
		FrequencyAnalyser f = new FrequencyAnalyser("WT MCI KOBH HC YBCK HVS GSQFSH CT HVS FOHZWBSG W AOM PS OPZS HC VSZD PIH HVS DFWQS KWZZ PS VWUV OBR WG BCH BSUCHWOPZS ZWTS VSFS WB PSFZWB VOG ZCGH WHG ZIGHFS OBR W KOBH GOBQHIOFM WB O ACFS QCBUSBWOZ QZWAOHS KWHV GSQIFWHM TCF AM TIHIFS W QOB DFCJWRS RSHOWZG CT DSFGCBBSZ DCZWQM GSQIFWHM OBR FCIHSG OBR QOB TIFBWGV MCI KWHV RCQIASBHOFM SJWRSBQS CT HVS FSOQV CT HVS CFUOBWNOHWCB HVS FSWQVGRCYHCF");
		f.Analyse();
		System.out.println(f.asciiArt());
	}

	String data;
	HashMap<Character,Integer> results = new HashMap<Character,Integer>();

	public FrequencyAnalyser(String str) {
		data = str;
	}


	public void Analyse() {
		String alphabet = "abcddefghijklmnopqrstuvwxyz";
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

	public String asciiArt() {
		String str = "";
		int total = 0;
		for (Character c : results.keySet()) {
			total += results.get(c);
		}
		for (Character c : results.keySet()) {
			if (results.get(c) > 0) {
				str += c + " | " + new String(new char[(int)Math.ceil(((results.get(c)/((float)total))*100))]).replace("\0", "#") + "\n";
			}
		}
		return str;
	}

}
