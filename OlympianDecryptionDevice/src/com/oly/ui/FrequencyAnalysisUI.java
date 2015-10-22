package com.oly.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.oly.decryption.analysis.FrequencyAnalyser;
import com.oly.decryption.key.KeyDecrypter;

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
		button1 = new JButton("Decrypt [NADA]");
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
		table.setEnabled(true); //MAKE BETTER LAZY JOB //ALLOW EDITING
		
		frame.setResizable(false);
		///
		table.setRowHeight(18);
		
		//TODO add action button stuff (so that they do something)
		button1.addActionListener(new DecryptButtonListener(this));
		
		//TABLE
		analysis.Analyse();
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		HashMap<Character,Character> potents = analysis.guess_potentials();
		HashMap<Character,Float> freq = analysis.frequencies();
		System.out.println(potents);
		//table.setValueAt("Char", 0, 0);
		//table.setValueAt("TO", 0, 1);
		for(int v = 0; v < alphabet.length(); v++) {
			table.setValueAt(alphabet.charAt(v), v, 0);
			table.setValueAt(potents.get(alphabet.charAt(v)),v,1);
			table.setValueAt(Integer.toString(Math.round(freq.get(alphabet.charAt(v)))) + "%", v, 2);
		}
		table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Char");
		table.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("TO");
		table.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("FREQ");
		
		//Analyser.getPotentials(analyser.standard,analyser.frequencies());
		
	}
	
	



	@Override
	public boolean isClosed() {
		return true; //SHOULDN@T BE CALLED YET
	}
	
	protected class DecryptButtonListener implements ActionListener {
		
		public FrequencyAnalysisUI link;
		
		public DecryptButtonListener(FrequencyAnalysisUI ui) {
			link = ui;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			
			Map<Character,Character> info = new HashMap<Character,Character>();
			for(int v = 0; v < 26; v++) {
				info.put((char)link.table.getValueAt(v, 0), 
						(char)link.table.getValueAt(v, 1));
				
				String output = KeyDecrypter.decrypt(link.analyser.data, info);
				
				new TranslationUI(arg0.getActionCommand() + " :: " + output);
				
			}
			
			
			
		}
		
		
		
		
		
		
	}
	
	
	
	
	
}
