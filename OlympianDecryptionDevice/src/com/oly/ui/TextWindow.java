package com.oly.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextWindow implements ITextUI{

	public JFrame frame;
	public JTextArea  output;
	public JFrame input_frame;
	public JTextField input;
	public JButton exec;
	//
	public String out_temp = "";
	
	
	public TextWindow(String name) {
		frame = new JFrame(name);
		output = new JTextArea();
		input_frame = new JFrame();
		input = new JTextField();
		exec = new JButton();
		//
		input_frame.add(input,BorderLayout.WEST);
		input_frame.add(exec,BorderLayout.EAST);
		frame.add(output,BorderLayout.CENTER);
		frame.add(input_frame,BorderLayout.SOUTH);
		//
		frame.setSize(480,320);
		output.setSize(470,280);
		input_frame.setSize(470,20);
		input.setSize(450,20);
		exec.setSize(20,20);
		//
		exec.addActionListener(new TextWindowHandler(this,input));
		//
		frame.setVisible(true);
		input_frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
	}
	
	
	
	@Override
	public boolean isClosed() {
		//TODO fix
		return frame.isActive(); //TODO check if correct
	}

	@Override
	public void write(String str) {
		output.setText(output.getText() + str);
	}

	@Override
	public void writeLine(String str) {
		write(str + '\n');
	}

	@Override
	public String getInput() {
		if((out_temp == "" )|| (out_temp == null) || (out_temp.trim() == "")) {
			return null;
		}
		return out_temp;
	}
	
	
	private class TextWindowHandler implements ActionListener {

		public TextWindow link_window;
		public JTextField input_box;
		
		public TextWindowHandler(TextWindow wind, JTextField link) {
			link_window = wind;
			input_box = link;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String temp = input_box.getText();
			input_box.setText("");
			link_window.out_temp = temp;
			link_window.writeLine(":USER: " + temp);
		}
		
	}

}
