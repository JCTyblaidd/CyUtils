package com.oly.web;

import java.net.URI;
import java.net.URISyntaxException;

import com.oly.util.Logger;

public class WebScraper {
	
	public String URL;
	public URI uri;
	
	
	public WebScraper(String url) {
		URL = url;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			Logger.instance.SEVERE("Failed URL Connection => " + url);
		}
	}
	
	public void init_connection() {
		
	}
	
	public String read_page() {
		//TODO ADD shizzle
		
		return null;
	}
	
}