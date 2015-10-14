package com.oly.util;

import java.awt.Color;

public class Logger {
	
	
	public static final Logger instance = new Logger();
	
	private Logger() {	
		//Add window constructer and stuff
		
	}
	
	public void LOG(Color color,String str,Object... objs) {
		
	}
	
	public void LOG(String str,Object... objs) {
		LOG(Color.BLACK,str,objs);
	}
	
	public void SEVERE(Object... objs) {
		LOG(Color.RED,"[SEVERE]",objs);
	}
	
	public void INFO(Object... objs) {
		LOG(Color.BLACK,"[INFO]",objs);
	}
	
	public void WARNING(Object... objs) {
		LOG(Color.YELLOW,"[WARNING]",objs);
	}
	
	
	
}
