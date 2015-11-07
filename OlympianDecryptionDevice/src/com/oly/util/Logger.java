package com.oly.util;

import java.awt.Color;

import com.oly.ui.CommandedWrappedTextWindow;
import com.oly.ui.TextWindow;
import com.oly.util.command.DecryptAffineCommand;
import com.oly.util.command.DecryptCeasarCommand;
import com.oly.util.command.StopProgramCommand;

public class Logger {
	
	
	public static final Logger instance = new Logger();
	public volatile TextWindow console;
	public volatile TextWindowCommandManager commands;
	
	private Logger() {	
		//Add window constructer and stuff
		console = null;
	}
	
	public void init_logger() {
		commands = new TextWindowCommandManager();
		console = new CommandedWrappedTextWindow("Debug Console",commands);
		
		
		
		//Register All Commands Here
		registerLogger(new StopProgramCommand());
		registerLogger(new DecryptCeasarCommand());
		registerLogger(new DecryptAffineCommand());
	}
	
	public void registerLogger(ITextWindowCommand command) {
		commands.commands.add(command);
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
