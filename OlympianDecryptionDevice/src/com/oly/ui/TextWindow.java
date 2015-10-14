package com.oly.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextWindow implements ITextUI{

	public JFrame frame;
	public JTextArea  output;
	//public JFrame input_frame;
	//public JPanel input_frame;
	public JTextField input;
	public JButton exec;
	public JScrollPane scroll;
	//
	public String out_temp = "";
	
	
	public TextWindow(String name) {
		frame = new JFrame(name);
		output = new JTextArea(1,15);
		//input_frame = new JPanel();
		input = new JTextField();
		exec = new JButton("Submit");
		scroll = new JScrollPane (output, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//
		size(frame,680,430);
		//size(output,680,350);
		//size(input_frame,680,80);
		//size(input,680,70);
		//size(exec,60,60);
		//size(scroll,30,350);]
		scroll.setBounds(0, 0, 675, 365);
		//input_frame.setBounds(0,350,680,80);
		input.setBounds(5,370,570,25);
		exec.setBounds(580,370,80,25);
		//
		//
		//scroll.add(output);
		frame.getContentPane().add(input,BorderLayout.WEST);
		frame.getContentPane().add(exec,BorderLayout.EAST);
		//
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(scroll,BorderLayout.NORTH);
		//frame.getContentPane().add(input_frame,BorderLayout.SOUTH);
		//
		exec.addActionListener(new TextWindowHandler(this,input));
		//
		frame.setVisible(true);
		//input_frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(new Dimension(680,430));
		frame.setMinimumSize(new Dimension(680,430));
		frame.setResizable(false);
		//
	}
	
	private void size(Component comp,int h, int w) {
		comp.setMaximumSize(new Dimension(h,w));
		comp.setMinimumSize(new Dimension(h,w));
		comp.setPreferredSize(new Dimension(h,w));
		//comp.setBounds(r);
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
