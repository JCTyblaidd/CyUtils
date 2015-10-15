	package com.oly.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

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
	
}
