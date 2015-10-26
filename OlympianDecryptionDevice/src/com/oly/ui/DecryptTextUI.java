package com.oly.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

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
	public JRootPane tab1;
	
	//TAB2 plain_text
	public JRootPane tab2;
	public JTextArea plain_cypher;
	
	//Tab3 freq_analysis
	public JRootPane tab3;
	
	//Tab4 alt_cypher cracking
	public JRootPane tab4;
	
	//Tab5 Lexical Option
	public JRootPane tab5;
	
	//TAB6 Results
	public JRootPane tab6;
	
	
	public DecryptTextUI(String title,String cypher) {
		this.cypher = cypher;
		
		//INIT
		main_window = new JFrame(title);
		main_window.setPreferredSize(new Dimension(840,540));
		main_window.setMinimumSize(new Dimension(840,540));
		main_window.setMaximumSize(new Dimension(840,540));
		main_window.setVisible(true);
		main_window.setResizable(false);
		main_window.setLayout(null);//CUSTOM becuase JLayout is annoying
		main_tabs = new JTabbedPane();
		main_tabs.setBounds(0, 0, 840, 530);
		main_window.getContentPane().add(main_tabs);
		
		//TAB1
		tab1 = new JRootPane();
		tab1.setBounds(0, 0, 840, 510);
		//tab1.setLayout(null);
		main_tabs.addTab("Control", tab1);
		
		//TAB2
		tab2 = new JRootPane();
		tab2.setBounds(0, 0, 840, 510);
		//tab2.setLayout(null);
		main_tabs.addTab("Plain Text",tab2);
		plain_cypher = new JTextArea("");
		plain_cypher.setBounds(0,0,840,500);
		plain_cypher.setLineWrap(true);
		plain_cypher.setWrapStyleWord(false);
		plain_cypher.setText(this.cypher);
		plain_cypher.setEditable(false);
		tab2.getContentPane().add(plain_cypher);
		
		//TAB3
		tab3 = new JRootPane();
		tab3.setBounds(0, 0, 840, 510);
		main_tabs.addTab("FrequencyAnalysis",tab3);
		
		//TAB4
		tab4 = new JRootPane();
		tab4.setBounds(0,0,840,510);
		main_tabs.addTab("AlterateAnalysis",tab4);
		
		//TAB5
		tab5 = new JRootPane();
		tab5.setBounds(0,0,840,510);
		main_tabs.addTab("LexicalAnalysis",tab5);
		
		//TAB6
		tab6 = new JRootPane();
		tab6.setBounds(0,0,840,510);
		main_tabs.addTab("Results",tab6);
	}
	
	
	
	
	
	
	
	
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
