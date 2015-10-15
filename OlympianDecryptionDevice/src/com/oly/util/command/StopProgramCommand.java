package com.oly.util.command;

import com.oly.ui.TextWindow;
import com.oly.util.ITextWindowCommand;

public class StopProgramCommand implements ITextWindowCommand{

	@Override
	public String getName() {
		return "stop";
	}

	@Override
	public void run(String[] args, TextWindow link) {
		link.write("=====STOPPING PROGRAM=====");
		System.exit(0);
	}

}
