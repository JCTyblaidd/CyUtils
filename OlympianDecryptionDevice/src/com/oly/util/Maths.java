package com.oly.util;

public class Maths {
	
	
	public static int Mod_Clamp(int i,int max) {
		int t = i;
		while(t < 0) {
			t += max;
		}
		return t % max;
	}
	
	public static int clamp_int(int i, int max, int min) {
		return i > max ? max : i < min ? min : i;
	}
	
	public static double clamp_double(double i, double max, double min) {
		return i > max ? max : i < min ? min : i;
	}
	
	public static float clamp_float(float i, float max, float min) {
		return i > max ? max : i < min ? min : i;
	}
	
	public static long camp_long(long i, long max, long min) {
		return i > max ? max : i < min ? min : i;
	}
	
	
	
}
