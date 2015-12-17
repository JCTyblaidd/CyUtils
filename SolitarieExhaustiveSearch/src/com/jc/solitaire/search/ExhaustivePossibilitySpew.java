package com.jc.solitaire.search;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

public class ExhaustivePossibilitySpew implements Runnable{

	private static volatile File output;
	
	//private static volatile FileOutputStream out;
	//private static volatile BufferedWriter write;
	private Thread thread;
	
	static{//CONFIGURE
		output = new File("POSSIBILE-"+UUID.randomUUID().toString()+".txt");
	}
	
	public ExhaustivePossibilitySpew() {
		thread = new Thread(this);
		thread.start();
	}
	
	public static void write(String str) {
		String prev = "";
		try{
			FileInputStream fin = new FileInputStream(output);
			byte[] bytes = new byte[fin.available()];
			fin.read(bytes);
			fin.close();
			prev = new String(bytes);
		}catch(Exception e) {
			//NON EXIST?
		}
		//WRITE
		prev = prev + str;
		try{
			FileOutputStream fout = new FileOutputStream(output);
			fout.write(prev.getBytes());
			fout.close();
			System.out.println("SOLUTION: ");
		}catch(Exception e) {
			System.out.println("SOL ERROR: " + str);
		}
	}
	
	@Override
	public void run() {
		while(true) {
			if(ExhaustiveSearchLaunch.solutions.size() > 0) {
				String solution = ExhaustiveSearchLaunch.solutions.get(0);
				ExhaustiveSearchLaunch.solutions.remove(solution);
				if(LexicalAnalysis.isPossible(solution)) {
					write(solution + "\n");
				}
			}
			
			
		}
		
	}
	
	
}
