package com.oly.util;

import com.oly.ui.TextWindow;

public interface ITextWindowCommand {
	
	
	public String getName();
	
	public void run(String[] args,TextWindow link);
}
