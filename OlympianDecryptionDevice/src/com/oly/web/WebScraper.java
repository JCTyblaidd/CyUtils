	package com.oly.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.oly.util.Logger;

public class WebScraper {
	
	public String interwebs;
	public URL url;
	
	
	public WebScraper(String loc) {
		interwebs = loc;
		try {
			url = new URL(loc);
		} catch (MalformedURLException e) {
			Logger.instance.SEVERE("====ERROR ON URL===");
			e.printStackTrace();
		}
	}
	
	public void init_connection() {
		
	}
	
	public String read_page() {
		//TODO ADD shizzle
		try {
			ReadableByteChannel channel=Channels.newChannel(url.openStream());
			ByteBuffer buffer = ByteBuffer.allocate((int) Math.pow(2, 30));//ABOUT 1 MB
			channel.read(buffer);
			buffer.flip(); // flip the buffer for reading
			byte[] bytes = new byte[buffer.remaining()]; // create a byte array the length of the number of bytes written to the buffer
			buffer.get(bytes); // read the bytes that were written
			String output = new String(bytes);
			return output;
		} catch (IOException e) {
			Logger.instance.SEVERE("===ERROR READING PAGE====");
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String[] ExperimentalGetText() {//REQUIRES THE CONNECTION TO BE TO THE CHALLENGES PAGE
		String data = read_page();
		final char sp = '"';
		int init = data.indexOf("<div class="+sp+"text"+sp+">");
		int end = data.indexOf("<!-- End Content -->");
		String searchable = data.substring(init, end);
		//TRIM USELESSNESS
		searchable = searchable.replace("<br/>","");
		searchable = searchable.replace("’","");
		//TRIM USELESSNESS
		for(int v = 0; v < 100; v++) { //REMOVE EXCESSIVE SPACING
			searchable = searchable.replace("  "," ");
		}
		searchable = searchable.trim();
		String[] poss = searchable.split("<div class=");
		poss = strs_replace(poss,new String(new char[]{sp}),"");
		poss = strs_replace(poss,"text>",""); //CLIPPING
		poss = strs_replace(poss,"c_text>","");
		poss = strs_replace(poss,"c_textB>","");
		poss = strs_replace(poss,"<","");
		poss = strs_replace(poss,">","");
		//
		poss = strs_replace(poss,"/div","");
		poss = strs_replace(poss,"/br","");
		poss = strs_replace(poss,"…br","");
		poss = strs_replace(poss,"…","");
		poss = strs_replace(poss,"c_","");
		poss = strs_replace(poss,"/","");
		poss = strs_replace(poss,"Challenge 1 B","");
		poss = strs_replace(poss,"Challenge 1 A","");
		
		for(String str : poss) { //TRIMMING
			str = str.trim();
		}
		int valid_count = 0;
		for(String str : poss) {
			if(str.length() > 20 &&(str.indexOf("My name is Harry") == -1) && (str.indexOf("href=") == -1)) {
				valid_count++;
			}
		}
		String[] results = new String[valid_count];
		int count = 0;
		for(String str : poss) {
			if(str.length() > 20 &&(str.indexOf("My name is Harry") == -1) && (str.indexOf("href=") == -1)) {
				results[count] = str;
				count ++;
			}
		}
		return results;
	}
	
	private String[] strs_replace(String[] strs,String a,String b) {
		String[] output = new String[strs.length];
		for(int v = 0; v < strs.length; v++) {
			output[v] = strs[v].replace(a, b);
		}
		return output;
	}
	
	
}
