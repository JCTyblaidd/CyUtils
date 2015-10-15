package com.oly.ui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.oly.decryption.analysis.FrequencyAnalyser;

public class FrequencyAnalysisUI implements IUI {

	public JFrame frame;
	public JTable table;
	public JButton button1;
	public JButton button2;
	public JButton button3;
	public JButton button4;
	public JScrollPane scroll;
	public FrequencyAnalyser analyser;
	
	public FrequencyAnalysisUI(String name,FrequencyAnalyser analysis) {
		analyser = analysis;
		//
		frame = new JFrame(name);
		table = new JTable(26,8); //TODO get data loading
		scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		button1 = new JButton("BUTTON_1");
		button2 = new JButton("BUTTON_2");
		button3 = new JButton("BUTTON_3");
		button4 = new JButton("BUTTON_4");
		//
		frame.setPreferredSize(new Dimension(725,600));
		frame.setMaximumSize(new Dimension(750,575));
		frame.setMinimumSize(new Dimension(750,575));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(scroll);
		frame.getContentPane().add(button1);
		frame.getContentPane().add(button2);
		frame.getContentPane().add(button3);
		frame.getContentPane().add(button4);
		//
		scroll.setBounds(0, 0, 745, 500);
		button1.setBounds(25,505,150,30);
		button2.setBounds(200,505,150,30);
		button3.setBounds(375,505,150,30);
		button4.setBounds(550,505,150,30);
		//
		frame.setVisible(true);
		table.setEnabled(false); //MAKE BETTER LAZY JOB
		
		frame.setResizable(false);
		///
		table.setRowHeight(50);
		
		//TODO add action button stuff (so that they do something)
		
		
	}
	
	



	@Override
	public boolean isClosed() {
		return true; //SHOULDN@T BE CALLED YET
	}

}
