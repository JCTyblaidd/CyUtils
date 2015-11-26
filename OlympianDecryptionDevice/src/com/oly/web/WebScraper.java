	package com.oly.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import com.oly.util.Logger;

public class WebScraper {
	
	public String interwebs;
	public URL url;
	
	public static String not_here = "challenge not here yet!";
	
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
	
	public static String read_page(String string) {
		try {
			ReadableByteChannel channel=Channels.newChannel(new URL(string).openStream());
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
	
	public static String kill_html_tags(String string) {
		string = string.replace("â€™","");
		/////STYLE REMOVAL
		int style1 = string.indexOf("<style type=\"text/css\">");
		int style2 = string.indexOf("</style>");
		if(style2 != -1) {
			if(style1 == -1) {
				style1 = 0;
			}
			String temp_style = string.substring(style1, style2);
			string = string.replace(temp_style, "");
		}
		////
		String output = string.replace("",""); //LAZY TERRIBLE COPY
		List<Integer> dat1 = new ArrayList<Integer>();
		List<Integer> dat2 = new ArrayList<Integer>();
		for(int v = 0; v < string.length(); v++) {
			if(string.charAt(v) == '<') {
				dat1.add(v);
			}else if(string.charAt(v) == '>') {
				dat2.add(v);
			}
		}
		//Logger.instance.LOG("SIZE " + dat1.size());
		if(dat1.size() != dat2.size()) {
			Logger.instance.SEVERE("HTML REMOVEAL: incomplete tags!");
			return string;
		}
		for(int v = 0; v < dat1.size(); v++) {
			String temp = string.substring(dat1.get(v), dat2.get(v)+1);
			//Logger.instance.LOG("REMOVED:" + temp);
			output = output.replace(temp, "");
		}
		
		//FIXES HTML SCRAPING FORMAT BUGS
		output = output.replace("â€œ","\"");
		output = output.replace("â€�","\"");
		
		return output.trim();
	}
	
	//Type must be uppercase
	public static String getChallenge(int number,char type) {
		String address = "http://www.cipher.maths.soton.ac.uk/media/com_cipher/scripts/"
				+ "challenge.php?number=" + number + "&part=" + type + "&ml=1";
		return kill_html_tags(read_page(address));
	}
	
	
	@Deprecated  /* KEEP BECAUSE IT MIGHT BE USEFUL**/
	public String[] ExperimentalGetText() {//REQUIRES THE CONNECTION TO BE TO THE CHALLENGES PAGE
		String data = read_page();
		final char sp = '"';
		int init = data.indexOf("<div class="+sp+"text"+sp+">");
		int end = data.indexOf("<!-- End Content -->");
		String searchable = data.substring(init, end);
		//TRIM USELESSNESS
		searchable = searchable.replace("<br/>","");
		searchable = searchable.replace("â€™","");
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
		poss = strs_replace(poss,"â€¦br","");
		poss = strs_replace(poss,"â€¦","");
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
