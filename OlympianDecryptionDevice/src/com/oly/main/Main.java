package com.oly.main;

import com.oly.util.Logger;

public class Main implements Runnable{
	
	public static Main MASTER;
	private String[] params;
	private boolean isStopped = false;
	
	public static void main(String[] args) { //QUICK SPIN OFF FUNCTION
		MASTER = new Main(args);
		Logger.instance.init_logger();
		MASTER.run();
	}
	
	protected Main(String[] params) {
		this.params = params;
	}
	
	public void STOP() {
		isStopped = true;
	}
	
	@SuppressWarnings("unused")
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
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			if(!isStopped) {//Add end contitional - linked to window
				break;
			}
		}
		
	}
	
}
