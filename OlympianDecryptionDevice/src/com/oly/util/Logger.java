package com.oly.util;

import java.awt.Color;

import com.oly.threading.SmartThread;
import com.oly.ui.SystemWrappedTextWindow;
import com.oly.ui.TextWindow;
import com.oly.util.command.StopProgramCommand;

public class Logger {
	
	
	public static final Logger instance = new Logger();
	public TextWindow console;
	public TextWindowCommandManager commands;
	public SmartThread command_thread;
	
	private Logger() {	
		//Add window constructer and stuff
		console = null;
	}
	
	public void init_logger() {
		console = new SystemWrappedTextWindow("Debug Console");
		commands = new TextWindowCommandManager(console);
		command_thread = new SmartThread(commands);
		command_thread.Start();
		//Register All Commands Here
		registerLogger(new StopProgramCommand());
		
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
