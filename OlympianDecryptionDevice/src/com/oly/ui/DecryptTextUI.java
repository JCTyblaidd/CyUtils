package com.oly.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
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
	public JProgressBar control_init_progress;
	public JProgressBar control_freq_progress;
	public JProgressBar control_alt_progress;
	public JProgressBar control_lex_progress;
	
	
	//TAB2 plain_text
	public JPanel tab2;
	public JTextArea plain_cypher;
	
	//Tab3 freq_analysis
	public JPanel tab3;
	public JScrollPane freq_scroll;
	public JTable freq_analysis;
	public JButton freq_init;
	
	//Tab4 alt_cypher cracking
	public JPanel tab4;
	public JButton transposition_init;
	
	//Tab5 Lexical Option
	public JPanel tab5;
	public JPanel options_section;
	public JCheckBox isDisguised;//WORD STRUCTRE
	public JTextArea text_isDisguised;
	
	//TAB6 Results
	public JPanel tab6;
	public JSpinner results_tab;
	public JSpinner results_num;
	public JTextArea results_data;
	
	
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
		tab1.setLayout(null);
		main_tabs.addTab("Control", tab1);
		control_init_progress = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
		control_freq_progress = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
		control_alt_progress = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
		control_lex_progress = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
		control_init_progress.setBounds(500,100,200,30);
		control_freq_progress.setBounds(500,150,200,30);
		control_alt_progress.setBounds(500,200,200,30);
		control_lex_progress.setBounds(500,250,200,30);
		control_init_progress.setStringPainted(true);
		control_freq_progress.setStringPainted(true);
		control_alt_progress.setStringPainted(true);
		control_lex_progress.setStringPainted(true);
		control_init_progress.setString("Initial Analysis");
		control_freq_progress.setString("Frequential Analysis");
		control_alt_progress.setString("Alternate Analysis");
		control_lex_progress.setString("Lexical Analysis");
		tab1.add(control_init_progress);
		tab1.add(control_freq_progress);
		tab1.add(control_alt_progress);
		tab1.add(control_lex_progress);
		
		
		//TAB2
		tab2 = new JPanel();
		tab2.setBounds(0, 0, 840, 510);
		tab2.setLayout(null);
		main_tabs.addTab("Plain Text",tab2);
		plain_cypher = new JTextArea("");
		plain_cypher.setBounds(5,5,820,475);
		//size(plain_cypher,830,510);
		plain_cypher.setLineWrap(true);
		plain_cypher.setWrapStyleWord(false);
		plain_cypher.setText(this.cypher);
		plain_cypher.setEditable(false);
		plain_cypher.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLACK,Color.GRAY));
		tab2.add(plain_cypher);
		
		//TAB3
		tab3 = new JPanel();
		tab3.setLayout(null);
		tab3.setBounds(0, 0, 840, 510);
		main_tabs.addTab("FrequencyAnalysis",tab3);
		freq_analysis = new JTable(26,6);
		freq_analysis.getColumnModel().getColumn(0).setHeaderValue("Encrypt: Char");
		freq_analysis.getColumnModel().getColumn(1).setHeaderValue("Decrypt: Char");
		freq_analysis.getColumnModel().getColumn(2).setHeaderValue("Decrypt: Freq");
		freq_analysis.getColumnModel().getColumn(3).setHeaderValue("");
		freq_analysis.getColumnModel().getColumn(4).setHeaderValue("Norm:Aphabet");
		freq_analysis.getColumnModel().getColumn(5).setHeaderValue("Norm:Frequency");
		//freq_analysis.setBounds(200, 5, 500, 475);
		freq_scroll = new JScrollPane(freq_analysis);
		freq_scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLACK,Color.GRAY));
		freq_scroll.setBounds(230,5,600,440);
		tab3.add(freq_scroll);
		freq_init = new JButton("Analyse");
		freq_init.setBounds(230,450,100,30);
		freq_init.addActionListener(new ReflectionActionHandler(this, "init_freq_analysis"));
		tab3.add(freq_init);
		
		//TAB4
		tab4 = new JPanel();
		tab4.setLayout(null);
		tab4.setBounds(0,0,840,510);
		main_tabs.addTab("AlterateAnalysis",tab4);
		transposition_init = new JButton("Brute Force Transposition Cypher");
		transposition_init.addActionListener(new ReflectionActionHandler(this, "init_brute_transposition_cypher"));
		transposition_init.setBounds(5,100,250,30);
		tab4.add(transposition_init);
		
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
		results_data = new JTextArea();
		results_data.setBounds(170, 3, 657, 480);
		results_data.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLACK,Color.GRAY));
		tab6.add(results_data);
	}
	
	
	
	private void size(Component c,int width,int height) {
		c.setMaximumSize(new Dimension(width,height));
		c.setPreferredSize(new Dimension(width,height));
		c.setMinimumSize(new Dimension(width,height));
	}
	
	/////////////////////////////////UPDATE FUNCTIONALITY//////////////////////////////
	
	
	
	
	///////////////////////////////SPIN OFF FUNCTIONALITY////////////////////////////
	
	public void init_freq_analysis() {
		
		
	}
	
	
	public void init_brute_transposition_cypher() {
		
		
		
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
