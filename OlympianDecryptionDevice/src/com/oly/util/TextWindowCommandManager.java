package com.oly.util;

import java.util.ArrayList;
import java.util.List;

import com.oly.ui.TextWindow;



public class TextWindowCommandManager{
	
	
	public volatile List<ITextWindowCommand> commands = new ArrayList<ITextWindowCommand>();
	public TextWindow linked;
	public TextWindowCommandManager(TextWindow link) {
		linked = link;
	}
	public TextWindowCommandManager() {
		//NADA requires stuff
		linked = null;
	}

	
	public boolean Handle_Command(String str) {
		if(str != null && str != "" & str != "") {
			for(ITextWindowCommand command : commands) {
				String init_command = "";
				
				if(str.indexOf(" ") == -1) {
					init_command = str;
				}else {
					init_command = str.substring(0, str.indexOf(" ")).trim();
				}
				if(command.getName().toLowerCase().trim().equals(init_command.toLowerCase().trim())) {
					if(str.indexOf(" ") == -1) {
						command.run(new String[]{""},linked);
					}
					try {
					command.run(str.substring(str.indexOf(" "),str.length()-1).split(" "), linked);
					}catch(Exception e) {
						Logger.instance.SEVERE("COMMAND ERROR :> " + e.getClass().getName());
						e.printStackTrace();
						command.run(new String[]{""}, linked);
					}
				}
			}
		}
		return true;
	}
	
}
