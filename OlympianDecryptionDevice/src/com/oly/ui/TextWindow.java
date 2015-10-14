package com.oly.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class TextWindow implements ITextUI{

	public JFrame frame;
	public JTextPane  output;
	public JTextField input;
	public JButton exec;
	public JScrollPane scroll;
	//
	public String out_temp = "";
	
	
	public TextWindow(String name) {
		frame = new JFrame(name);
		output = new JTextPane();
		input = new JTextField();
		exec = new JButton("Submit");
		scroll = new JScrollPane (output, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//
		size(frame,680,430);
		scroll.setBounds(0, 0, 675, 365);
		input.setBounds(5,370,570,25);
		exec.setBounds(580,370,80,25);
		//
		frame.getContentPane().add(input,BorderLayout.WEST);
		frame.getContentPane().add(exec,BorderLayout.EAST);
		//
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(scroll,BorderLayout.NORTH);
		//
		exec.addActionListener(new TextWindowHandler(this,input));
		input.addActionListener(new TextWindowHandler(this,input));
		//
		frame.setVisible(true);
		output.setEditable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(new Dimension(680,430));
		frame.setMinimumSize(new Dimension(680,430));
		frame.setResizable(false);
		//
		
		output.setBackground(Color.WHITE);
		
	}
	
	private void size(Component comp,int h, int w) {
		comp.setMaximumSize(new Dimension(h,w));
		comp.setMinimumSize(new Dimension(h,w));
		comp.setPreferredSize(new Dimension(h,w));
	}
	
	private void writeWithColor(String str, Color c) {
		StyledDocument doc = output.getStyledDocument();
		Style style = output.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, c);	
        try { doc.insertString(doc.getLength(), str,style); }
        catch (BadLocationException e){
        	//LOLOLOLOLOL
        }
	}
	
	
	
	@Override
	public boolean isClosed() {
		//TODO fix
		return frame.isActive(); //TODO check if correct
	}

	@Override
	public void write(String str) {
		//output.setText(output.getText() + str);
		writeWithColor(str,Color.BLACK);
	}

	@Override
	public void writeLine(String str) {
		write(str + '\n');
	}
	
	public void write_col(String str,Color c) {
		writeWithColor(str, c);
	}
	
	public void writeLine_col(String str, Color c) {
		write_col(str + '\n',c);
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
			//link_window.writeLine("         " + temp);
			writeWithColor("        "+temp+"\n",Color.RED);
		}
		
	}
	

}
