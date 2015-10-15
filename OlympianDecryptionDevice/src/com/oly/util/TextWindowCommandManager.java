package com.oly.util;

import java.util.ArrayList;
import java.util.List;

import com.oly.threading.ISmartThreadable;
import com.oly.ui.TextWindow;



public class TextWindowCommandManager implements ISmartThreadable{
	
	
	public volatile List<ITextWindowCommand> commands = new ArrayList<ITextWindowCommand>();
	public TextWindow linked;
	public TextWindowCommandManager(TextWindow link) {
		linked = link;
	}

	@Override
	public boolean Execute(String[] params) {
		String str = linked.out_temp;
		if(str != null && str != "" & str != "") {
			linked.writeLine("TEMP");
			for(ITextWindowCommand command : commands) {
				String init_command = str.substring(0, str.indexOf(" ")).trim();
				if(str.indexOf(" ") == -1) {
					init_command = str;
				}
				linked.write("==DEBUG: found command");
				if(command.getName() == init_command) {
					command.run(str.substring(str.indexOf(" "),str.length()-1).split(" "), linked);
				}
			}
		}
		linked.out_temp = null;
		return true;
	}
	
	
	
	//IGNORE///////////////////////////////////////////////////////////////
	@Override
	public int get_tick_rate() {
		return 0;
	}

	@Override
	public boolean is_rate_limited() {
		return false;
	}
	
	
}
