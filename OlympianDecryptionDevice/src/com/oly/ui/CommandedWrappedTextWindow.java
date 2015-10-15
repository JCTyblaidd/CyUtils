package com.oly.ui;

import com.oly.util.TextWindowCommandManager;

public class CommandedWrappedTextWindow extends SystemWrappedTextWindow{
	
	
	public TextWindowCommandManager comman_manager;
	
	public CommandedWrappedTextWindow(String name,TextWindowCommandManager commands) {
		super(name);
		comman_manager = commands;
		comman_manager.linked = this;
	}
	
	
	
	@Override
	public boolean handleNewInput(String str) {
		comman_manager.Handle_Command(str);
		return true;
	}
	
	

}
