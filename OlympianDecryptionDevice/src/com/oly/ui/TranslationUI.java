package com.oly.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TranslationUI {
	
	public JFrame frame;
	public JTextArea  output;
	public JScrollPane scroll;
	
	
	@SuppressWarnings("unused")
	public TranslationUI(String str) {
		frame = new JFrame("Translation");
		output = new JTextArea();
		scroll = new JScrollPane (output, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		String helper = str;
		int x = 0;
		for(int i = 0 ; i < Math.floor(str.length() / 5); i++) {
			
		}
		
		output.setText(str);
		
		size(frame,680,430);
		frame.getContentPane().add(scroll,BorderLayout.NORTH);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		output.setEditable(false);
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		scroll.setBounds(0, 0, 675, 420);
		frame.pack();
		frame.setMaximumSize(new Dimension(680,430));
		frame.setMinimumSize(new Dimension(680,430));
		frame.setResizable(false);
	}
	
	private void size(Component comp,int h, int w) {
		comp.setMaximumSize(new Dimension(h,w));
		comp.setMinimumSize(new Dimension(h,w));
		comp.setPreferredSize(new Dimension(h,w));
	}
	
}
