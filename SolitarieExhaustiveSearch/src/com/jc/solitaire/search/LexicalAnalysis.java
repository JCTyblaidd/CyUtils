package com.jc.solitaire.search;

public class LexicalAnalysis {
	
	
	//TODO HANDLE IT A LOT BETTER
	//HOWEVER IT WORKS KINDA
	//WOOOISH
	public static boolean isPossible(String solution) {
		if(solution == null) {//THREADING ERROR I THINK
			//System.err.println("> NULL????");//ERROR WTF?
			return false;
		}
		solution = solution.toLowerCase().replace(" ","");
		if(solution.contains("reichsdoktor")) {
			return true;
		}
		if(solution.contains("nazi")) {
			return true;
		}
		if(solution.contains("russia")) {
			return true;
		}
		if(solution.contains("charlie")) {
			return true;
		}
		if(solution.contains("harry")) {
			return true;
		}
		if(solution.contains("ratline")) {
			return true;
		}
		if(solution.contains("moscow")) {
			return true;
		}
		if(solution.contains("solitaire")) {
			return true;
		}
		if(solution.contains("intelligence")) {
			return true;
		}
		if(solution.contains("archive")) {
			return true;
		}
		if(solution.contains("europe")) {
			return true;
		}
		if(solution.contains("british")) {
			return true;
		}
		if(solution.contains("britain")) {
			return true;
		}
		if(solution.contains("motherland")) {
			return true;
		}
		if(solution.contains("america")) {
			return true;
		}
		if(solution.contains("record")) {
			return true;
		}
		if(solution.contains("negotiation")) {
			return true;
		}
		if(solution.contains("intelligence")) {
			return true;
		}
		if(solution.contains("marine")) {
			return true;
		}
		if(solution.contains("extraordinary")) {
			return true;
		}
		if(solution.contains("coincidence")) {
			return true;
		}
		if(solution.contains("hidden")) {
			return true;
		}
		if(solution.contains("message")) {
			return true;
		}
		if(solution.contains("delivering")) {
			return true;
		}
		if(solution.contains("location")) {
			return true;
		}
		
		
		
		return false;
	}
	
}
