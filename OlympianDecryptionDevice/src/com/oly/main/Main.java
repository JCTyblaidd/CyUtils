package com.oly.main;

public class Main implements Runnable{
	
	public static Main MASTER;
	private String[] params;
	
	public static void main(String[] args) { //QUICK SPIN OFF FUNCTION
		MASTER = new Main(args);
		MASTER.run();
	}
	
	protected Main(String[] params) {
		this.params = params;
	}
	
	
	private boolean arg(String arg) {
		for(String param : params) {
			if(param == arg) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void run() {
		while(true) {
			//TODO do stuff and spin off
			
			if(false) {//Add end contitional - linked to window
				break;
			}
		}
		
	}
	
}
