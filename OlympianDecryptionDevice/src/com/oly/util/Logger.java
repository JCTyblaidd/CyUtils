package com.oly.util;

import java.awt.Color;

import com.oly.ui.TextWindow;

public class Logger {
	
	
	public static final Logger instance = new Logger();
	public TextWindow console;
	
	private Logger() {	
		//Add window constructer and stuff
		console = null;
	}
	
	public void init_logger() {
		console = new TextWindow("Debug Console");
	}
	
	public void LOG(Color color,String str,Object... objs) {
		String temp = str;
		for(Object obj : objs) {
			temp = temp + obj.toString();
		}
		console.writeLine_col(temp, color);
	}
	
	public void LOG(String str,Object... objs) {
		LOG(Color.BLACK,str,objs);
	}
	
	public void SEVERE(Object... objs) {
		LOG(Color.RED,"[SEVERE] ",objs);
	}
	
	public void INFO(Object... objs) {
		LOG(Color.BLACK,"[INFO] ",objs);
	}
	
	public void WARNING(Object... objs) {
		LOG(Color.YELLOW,"[WARNING] ",objs);
	}
	
	
	
}
