package com.oly.threading;

public interface ISmartThreadable {
	
	
	public boolean Execute(String[] params);
	
	public int get_tick_rate();
	public boolean is_rate_limited();
	
}
