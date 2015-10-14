package com.oly.threading;

public class SmartThread implements Runnable{
	
	public ISmartThreadable wrapper;
	public Thread thread;
	public String[] paramaters;
	private boolean isStopped = false;
	private boolean rate_lim = false;
	private int rate = 0;
	
	public SmartThread(ISmartThreadable thread,String[] params) {
		wrapper = thread;
		paramaters = params;
		this.thread = new Thread(this);
		rate_lim = wrapper.is_rate_limited();
		rate = wrapper.get_tick_rate();
	}
	
	public SmartThread(ISmartThreadable thread) {
		this(thread,new String[]{"NULL"});
	}
	
	////////////////// THREAD CONTROL OPTIONS /////////////////////////////
	
	public void Start() {
		thread.run();
	}
	
	public void Pause() {
		try {
			thread.wait();
		} catch (InterruptedException e) {
			thread.run(); //NEEDED?
		}
	}
	
	/** 
	 *  @info Time is in milliseconds
	 **/
	public void Pause(long time) {
		try {
			thread.wait(time);
		} catch (InterruptedException e) {
			thread.run(); //NEEDED?
		}
	}
	
	public void Resume() {
		thread.interrupt();
	}
	
	public void Stop() {
		isStopped = true;
	}
	
	/**
	 * Performes the operations each loop
	 */
	@Override
	public void run() { 
		while(!isStopped) {
			wrapper.Execute(paramaters);
			if(rate_lim) {
				this.Pause(rate);
			}
		}
	}
	
	
}