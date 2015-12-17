package com.oly.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;

import com.oly.decryption.analysis.FrequencyAnalyser;
import com.oly.threading.FrequencyAnalysisTask;
import com.oly.threading.LaunchAnalysisAutoTask;
import com.oly.threading.LexicalAnalysisTask;
import com.oly.threading.SmartThread;
import com.oly.threading.TranspositionBruteTask;
import com.oly.util.Logger;
import com.oly.web.WebScraper;


/**
 * @author James Clifton
 * 
 * Complete UI designed for breaking the cyphers {to prevent the 30000 different windows}
 *
 */
public class DecryptTextUI {
	
	public final String cypher;
	private static final float leyway_lex = 0.55f;
	
	public volatile List<String> possibilities_freq = new ArrayList<String>();
	public volatile List<String> lexical_accepted_freq = new ArrayList<String>();
	public volatile boolean poss_dirty_freq = true;
	///
	public volatile List<String> possibilities_trans = new ArrayList<String>();
	public volatile List<String> lexical_accepted_trans = new ArrayList<String>();
	public volatile boolean poss_dirty_trans;
	///
	public volatile List<String> possibilities_poly = new ArrayList<String>();
	public volatile List<String> lexical_accepted_poly = new ArrayList<String>();
	public volatile boolean poss_dirty_poly;
	
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
	public JButton Lexical_Analyse;
	
	//TAB6 Results
	public JPanel tab6;
	public JSpinner results_tab;
	public JSpinner results_num;
	public JCheckBox results_use_filtered;
	public JTextArea results_data;
	
	//WRAPPED SHIZZLE
	public DecryptTextUI(int num,char type) {
		this("Challenge " + num +type,WebScraper.getChallenge(num,type));
	}
	
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
		Lexical_Analyse = new JButton("Lexically Analyse");
		Lexical_Analyse.setBounds(300,300,175,30);
		Lexical_Analyse.addActionListener(new ReflectionActionHandler(this, "init_lexical_analysis"));
		tab5.add(Lexical_Analyse);
		//TAB6
		tab6 = new JPanel();
		tab6.setBounds(0,0,840,510);
		tab6.setLayout(null);
		main_tabs.addTab("Results",tab6);
		results_tab = new JSpinner(new SpinnerListModel(new String[]{"Frequency Analysis","Transposition","Polyaphabetic"}));
		results_num = new JSpinner();
		results_use_filtered = new JCheckBox();
		results_tab.setBounds(10, 10, 150, 20);
		results_num.setBounds(10, 35, 150, 20);
		results_use_filtered.setBounds(10,60,150,20);
		results_use_filtered.setText("Use filtered? ");
		results_tab.addChangeListener(new ReflectionChangeHandler(this, "refresh_results_page"));
		results_num.addChangeListener(new ReflectionChangeHandler(this, "refresh_results_page"));
		results_use_filtered.addChangeListener(new ReflectionChangeHandler(this, "refresh_results_page"));
		tab6.add(results_tab,BorderLayout.WEST);
		tab6.add(results_num,BorderLayout.WEST);
		tab6.add(results_use_filtered,BorderLayout.WEST);
		results_data = new JTextArea();
		results_data.setBounds(170, 3, 657, 480);
		results_data.setLineWrap(true);
		results_data.setWrapStyleWord(true);
		results_data.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLACK,Color.GRAY));
		tab6.add(results_data);
		
		//
		populate_table();
		
		//////AUTOMATICALLY DO SOME ANALYSIS////
		
		init_all_tasks();
	}
	
	
	
	private void size(Component c,int width,int height) {
		c.setMaximumSize(new Dimension(width,height));
		c.setPreferredSize(new Dimension(width,height));
		c.setMinimumSize(new Dimension(width,height));
	}
	
	private void populate_table() {
		FrequencyAnalyser analyse = new FrequencyAnalyser(cypher);
		analyse.Analyse();
		Map<Character,String> data = analyse.getReasonableGuesses(leyway_lex);
		Map<Character,Float> freqs = analyse.frequencies();
		Map<Character,Float> normal = analyse.standard;
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Logger.instance.INFO("DEBUG: freqdata = " + data);
		//REMOVAL OF *certainty*
		String certs = "";
		for(char c : data.keySet()) {
			if(data.get(c).length() == 1) {
				certs = certs + data.get(c).charAt(0);
			}
		}
		for(char c : data.keySet()) {
			String info = data.get(c);
			for(char q : certs.toCharArray()) {
				info.replace(new String(new char[] {q}),"");
			}
			if(info.length() > 0) {
				data.put(c, info);
			}
		}
		Logger.instance.INFO("DEBUG: freqdata goes to => " + data);
		Logger.instance.INFO("DEBUG: " + data.get(alphabet.charAt(1)));
		//PUSH TO TABLE
		for(int i = 0; i < alphabet.length(); i++) {
			freq_analysis.setValueAt(alphabet.charAt(i),i, 0);
			freq_analysis.setValueAt(data.get(alphabet.charAt(i)), i, 1);
			freq_analysis.setValueAt(freqs.get(alphabet.charAt(i)),i, 2);
			/////////////////////////////////////////////////////////////
			freq_analysis.setValueAt(alphabet.charAt(i),i, 4);
			freq_analysis.setValueAt(normal.get(alphabet.charAt(i)),i, 5);
		}
	}
	
	/////////////////////////////////UPDATE FUNCTIONALITY//////////////////////////////
	
	
	
	
	///////////////////////////////SPIN OFF FUNCTIONALITY////////////////////////////
	
	//Called on creation
	public void init_all_tasks() {
		SmartThread.runRunnable(new LaunchAnalysisAutoTask(this));
	}
	
	
	public void init_freq_analysis() {
		SmartThread.runRunnable(new FrequencyAnalysisTask(this));
	}
	
	
	public void init_brute_transposition_cypher() {
		SmartThread.runRunnable(new TranspositionBruteTask(this));
	}
	
	public void init_lexical_analysis() {
		SmartThread.runRunnable(new LexicalAnalysisTask(this));
	}
	
	//ANTI CRASH
	boolean refreshing_data = false;
	
	public void refresh_results_page() {
		if(refreshing_data) {
			//Logger.instance.LOG("DEBUG: REFRESH SKIP");
			return;
		}
		refreshing_data = true;
		try{
			int index = (int)results_num.getValue();
			String option = (String)results_tab.getValue();
			boolean isFiltered = results_use_filtered.isSelected();
			
			//MIN CLAMPING
			if(index < 0) {
				index = 0;
				results_num.setValue(0);
			}
			//END MIN CLAMPING
			
			//System.out.println("DEGBUG :: " + possibilities_freq.size());
			
			if(option == "Frequency Analysis") {
				if(isFiltered) {
					if(index > lexical_accepted_freq.size()-1) {
						index = lexical_accepted_freq.size()-1;
						results_num.setValue(index);
					}
					results_data.setText(lexical_accepted_freq.get(index));
				}else {
					if(index > possibilities_freq.size()-1) {
						index = possibilities_freq.size()-1;
						results_num.setValue(index);
					}
					results_data.setText(possibilities_freq.get(index));
				}
			}else if(option == "Transposition") {
				if(isFiltered) {
					if(index > lexical_accepted_trans.size()-1) {
						index = lexical_accepted_trans.size()-1;
						results_num.setValue(index);
					}
					results_data.setText(lexical_accepted_trans.get(index));
				}else{
					if(index > possibilities_trans.size()-1) {
						index = possibilities_trans.size()-1;
						results_num.setValue(index);
					}
					results_data.setText(possibilities_trans.get(index));
				}
			}else if(option == "Polyaphabetic") {
				if(isFiltered) {
					if(index > lexical_accepted_poly.size()-1) {
						index = lexical_accepted_poly.size()-1;
						results_num.setValue(index);
					}
					results_data.setText(lexical_accepted_poly.get(index));
				}else {
					if(index > possibilities_poly.size()-1) {
						index = possibilities_poly.size()-1;
						results_num.setValue(index);
					}
					index %= possibilities_poly.size();
					results_data.setText(possibilities_poly.get(index));
				}
			}
			refreshing_data = false;
		}catch(Exception e){
			if(e instanceof ArrayIndexOutOfBoundsException) {
				results_data.setText(" =>> NO DATA HERE <<=");
				results_num.setValue(0);//FIX
			}else{
				results_data.setText(" ===== ERROR ===== \n\n"+e.getMessage());
				e.printStackTrace();
			}
		}
		refreshing_data = false;
	}
	
	public void export_unfiltered() {
		
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
	
	protected class ReflectionChangeHandler implements ChangeListener {
		
		public DecryptTextUI link;
		public String linked_function;
		private Method function;
		
		public ReflectionChangeHandler(DecryptTextUI link,String funct) {
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
		public void stateChanged(ChangeEvent event) {
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
