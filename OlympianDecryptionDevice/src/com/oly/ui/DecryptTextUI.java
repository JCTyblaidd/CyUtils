package com.oly.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerListModel;
import javax.swing.plaf.ColorUIResource;

import com.oly.util.Logger;


/**
 * @author James Clifton
 * 
 * Complete UI designed for breaking the cyphers {to prevent the 30000 different windows}
 *
 */
public class DecryptTextUI {
	
	public final String cypher;
	
	//UI elements
	public JFrame main_window;
	public JTabbedPane main_tabs;
	
	//TAB1 {"Commands!!! i.e. BUTTONS"}
	public JPanel tab1;
	
	
	//TAB2 plain_text
	public JPanel tab2;
	public JTextArea plain_cypher;
	
	//Tab3 freq_analysis
	public JPanel tab3;
	
	//Tab4 alt_cypher cracking
	public JPanel tab4;
	
	//Tab5 Lexical Option
	public JPanel tab5;
	public JPanel options_section;
	public JCheckBox isDisguised;//WORD STRUCTRE
	public JTextArea text_isDisguised;
	
	//TAB6 Results
	public JPanel tab6;
	public JSpinner results_tab;
	public JSpinner results_num;
	
	
	public DecryptTextUI(String title,String cypher) {
		this.cypher = cypher;
		
		//INIT
		main_window = new JFrame(title);
		size(main_window,840,540);
		main_window.setVisible(true);
		main_window.setResizable(false);
		main_window.setLayout(null);//CUSTOM becuase JLayout is annoying
		main_tabs = new JTabbedPane();
		main_tabs.setBounds(0, 0, 840, 530);
		//main_tabs.setLayout(null);
		main_window.getContentPane().add(main_tabs);
		
		//TAB1
		tab1 = new JPanel();
		tab1.setBounds(0, 0, 840, 510);
		//tab1.setLayout(null);
		main_tabs.addTab("Control", tab1);
		
		//TAB2
		tab2 = new JPanel();
		tab2.setBounds(0, 0, 840, 510);
		//tab2.setLayout(null);
		main_tabs.addTab("Plain Text",tab2);
		plain_cypher = new JTextArea("");
		//plain_cypher.setBounds(0,0,840,500);
		size(plain_cypher,830,510);
		plain_cypher.setLineWrap(true);
		plain_cypher.setWrapStyleWord(false);
		plain_cypher.setText(this.cypher);
		plain_cypher.setEditable(false);
		tab2.add(plain_cypher);
		
		//TAB3
		tab3 = new JPanel();
		tab3.setBounds(0, 0, 840, 510);
		main_tabs.addTab("FrequencyAnalysis",tab3);
		
		//TAB4
		tab4 = new JPanel();
		tab4.setBounds(0,0,840,510);
		main_tabs.addTab("AlterateAnalysis",tab4);
		
		//TAB5
		tab5 = new JPanel();
		tab5.setBounds(0,0,840,510);
		tab5.setLayout(null);
		main_tabs.addTab("LexicalAnalysis",tab5);
		options_section = new JPanel();
		options_section.setLayout(null);
		options_section.setBorder(BorderFactory.createTitledBorder("analysis options"));
		isDisguised = new JCheckBox();
		text_isDisguised= new JTextArea("Is Text Disguised");
		isDisguised.setBounds(5,15,20,20);
		isDisguised.setBackground(new ColorUIResource(new Color(1, 1, 1, 0)));
		text_isDisguised.setBackground(new ColorUIResource(new Color(1, 1, 1, 0)));
		text_isDisguised.setBounds(35,17,100,20);
		options_section.add(isDisguised);
		options_section.add(text_isDisguised);
		//size(options_section,150,150);
		options_section.setBounds(0,0,150,250);
		options_section.setLocation(0, 0);
		tab5.add(options_section);
		
		//TAB6
		tab6 = new JPanel();
		tab6.setBounds(0,0,840,510);
		tab6.setLayout(null);
		main_tabs.addTab("Results",tab6);
		results_tab = new JSpinner(new SpinnerListModel(new String[]{"Frequency Analysis","Transposition","Polyaphabetic"}));
		results_num = new JSpinner();
		results_tab.setBounds(10, 10, 150, 20);
		results_num.setBounds(10, 35, 150, 20);
		tab6.add(results_tab,BorderLayout.WEST);
		tab6.add(results_num,BorderLayout.WEST);
	}
	
	
	
	private void size(Component c,int width,int height) {
		c.setMaximumSize(new Dimension(width,height));
		c.setPreferredSize(new Dimension(width,height));
		c.setMinimumSize(new Dimension(width,height));
	}
	
	
	
	///////////////////////////////SPIN OFF FUNCTIONALITY////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Maybe Make Public or a seperate entity
	protected class ReflectionActionHandler implements ActionListener{
		
		public DecryptTextUI link;
		public String linked_function;
		private Method function;
		
		public ReflectionActionHandler(DecryptTextUI link,String funct) {
			this.link = link;
			this.linked_function = funct;
			try{
			function = link.getClass().getMethod(funct);
			}catch(Exception e) {
				Logger.instance.SEVERE("Error Wrapping DecryptTextUI");
				e.printStackTrace();
				function = null;
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			if(function != null) {
				try{
				function.invoke(link);
				}catch(Exception e) {
					Logger.instance.SEVERE("Error instigating Function");
					e.printStackTrace();
				}
				
			}
			
		}
		
		
		
	}
	
	
	
}
