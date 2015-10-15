package com.oly.ui;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class SystemWrappedTextWindow extends TextWindow{

	public SystemWrappedTextWindow(String name) {
		super(name);
		//Wrap The Stream
		System.setOut(new PrintStream(new WrappedStream(this)));
	}
	
	
	private class WrappedStream extends OutputStream {
		
		public SystemWrappedTextWindow link;
		
		public WrappedStream(SystemWrappedTextWindow l){
			link = l;
		}
		
		
		@Override
		public void write(int b) throws IOException {
			
			
		}
		@Override
		public void write(byte[] b) throws IOException {
			String str = new String(b);
			link.write_col(str, Color.ORANGE);
		}
		
		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			byte[] temp = new byte[len];
			for(int v = 0; v < len; v++) {
				temp[v] = b[v + off];
			}
			write(temp);
		}

	}
	
	
	
	
}
