package com.oly.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextWindow implements ITextUI{

	public JFrame frame;
	public JTextArea  output;
	public JFrame input_fram;
	public JTextField input;
	public JButton exec;
	
	
	public TextWindow(String name) {
		frame = new JFrame(name);
	}
	
	
	
	@Override
	public void isClosed() {
		
	}

	@Override
	public void write(String str) {
		
	}

	@Override
	public void writeLine(String str) {
		
	}

	@Override
	public String getInput() {
		return null;
	}

}
