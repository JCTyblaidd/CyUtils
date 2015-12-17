package com.jc.stream.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import com.jc.stream.cipher.XORutils;

public class StreamCipherGUI {
	
	public String cipher;
	public List<String> splits;
	
	public int CipherStreamRepeatOffset = 54;
	
	/////////////////////////////////////
	public JFrame window;
	public JTabbedPane tabs;
	
	public JPanel inputTab;
	public JPanel outputTab;
	public JPanel featuresTab;
	public JPanel analysisTab;
	
	public JTextArea inputCipher;
	
	public JTextArea outputCipher;
	public JTextArea outputKEY;
	public JTextArea outputFirst;
	
	/////////////////////////////////////
	
	public StreamCipherGUI() {
		cipher = "NULL";
		splits = new ArrayList<String>();
		stripCipherText();
		INIT_GUI();
	}
	
	private void stripCipherText() {
		splits.clear();
		int curr = 0;
		String temp = "";
		for(char c : cipher.toCharArray()) {
			if(curr >= CipherStreamRepeatOffset) {
				splits.add(temp);
				temp = "" + c;
				curr = 1;
			}else{
				temp = temp + c;
				curr++;
			}
		}
		//
		if(curr != 1) {
			splits.add(temp);
		}
		//System.out.println("DEBUG: " + splits.get(0).length() + " :: " + splits.size() + " from " + cipher.length());
	}
	
	/**GUI INITIALISATIOn**/
	private void INIT_GUI() {
		window = new JFrame();
		window.setLayout(null);
		window.setMinimumSize(new Dimension(700,500));
		window.setPreferredSize(new Dimension(700,500));
		///ADD SHIZZLE
		tabs = new JTabbedPane();
		//tabs.setLayout(null);
		tabs.setBounds(2, 2, 690, 465);
		window.getContentPane().add(tabs);
		
		///TABS///
		inputTab = new JPanel(null);
		outputTab = new JPanel(null);
		featuresTab = new JPanel(null);
		analysisTab = new JPanel(null);
		tabs.addTab("Input", inputTab);
		tabs.addTab("Output",outputTab);
		tabs.addTab("Analysis",analysisTab);
		tabs.addTab("Features",featuresTab);
		
		//INPUT//
		inputCipher = new JTextArea();
		inputCipher.setToolTipText("Input The Encrypted Cipher");
		//inputCipher.addComponentListener(null);//TODO
		inputCipher.setBounds(5,5,675,430);
		inputCipher.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.GRAY));
		inputTab.add(inputCipher);
		//OUTPUT//
		outputCipher = new JTextArea();
		outputCipher.setToolTipText("The Whole Cipher Output");
		outputCipher.setBounds(5,50,675,370);
		outputCipher.setEditable(false);
		outputCipher.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.GRAY));
		outputTab.add(outputCipher);
		outputFirst = new JTextArea();
		outputFirst.setToolTipText("The First " + CipherStreamRepeatOffset + " letter of the cipher");
		outputFirst.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		outputFirst.setBounds(5,5,340,40);
		outputTab.add(outputFirst);
		outputKEY = new JTextArea();
		outputKEY.setToolTipText("The KEY => [MAY BE GIBBERISH -> DEPENDS]");
		outputKEY.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		outputKEY.setBounds(345,5,340,40);
		outputTab.add(outputKEY);
		//WORD WRAP
		inputCipher.setLineWrap(true);
		inputCipher.setWrapStyleWord(true);
		outputCipher.setLineWrap(true);
		outputCipher.setWrapStyleWord(true);
		outputFirst.setLineWrap(true);
		outputFirst.setWrapStyleWord(true);
		outputKEY.setLineWrap(true);
		outputKEY.setWrapStyleWord(true);
		
		//HANDLERS
		inputCipher.addKeyListener(new ReflectionHandler(this, "OnInputChanged"));
		outputFirst.addKeyListener(new ReflectionHandler(this, "OnFirstChanged"));
		outputKEY.addKeyListener(new ReflectionHandler(this,"OnKeyChanged"));
		////
		window.setResizable(false);
		window.setVisible(true);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
			
	
	
	//LISTENER METHODS
	
	public void OnInputChanged() {
		this.cipher = inputCipher.getText().replace(" ", "").toUpperCase();
		this.inputCipher.setText(cipher);
		this.stripCipherText();//SPLIT INTO THE ARRAYS
		OnFirstChanged();//REFLECT AND ASSUME THAT IS CORRECT
		//STILL SILLY NUMBER IN MOST CASES
		//CipherAnalysis.getPermutations(cipher, CipherStreamRepeatOffset, 26);
	}
	public void OnFirstChanged() {
		String first = this.outputFirst.getText();
		if(first.length() >= (CipherStreamRepeatOffset - 0)) {
			//TRIM
			outputFirst.setText(first.substring(0, CipherStreamRepeatOffset - 0));
			first = outputFirst.getText();
		}
		//HANDLE OUTPUTS
		//GET REFFED SHIZZLE
		String saltedFirst = first;
		while(saltedFirst.length() < (CipherStreamRepeatOffset - 0)) {
			saltedFirst = saltedFirst + "*";
		}
		String relatedFirst = "";
		try{
			relatedFirst = this.splits.get(0);
		}catch(Exception e) {
			System.err.println("INDEX EXCEPTION -> IS CIPHER SPLIT?");
			relatedFirst = "???????????????????????";
		}
		String key = XORutils.XOR_streams(saltedFirst, relatedFirst);
		//KEY
		outputKEY.setText(key);//TODO UNSALT???
		//MAIN OUTPUT
		genOutputFromKEY(key);
	}
	public void OnKeyChanged() {
		String key = this.outputKEY.getText();
		if(key.length() >= (CipherStreamRepeatOffset - 0)) {
			//TRIM
			outputFirst.setText(key.substring(0, CipherStreamRepeatOffset - 0));
			key = outputFirst.getText();
		}
		//HANDLE OUTPUTS
		//GET REFFED SHIZZLE
		String saltedKey = key;
		while(saltedKey.length() < (CipherStreamRepeatOffset - 0)) {
			saltedKey = saltedKey + 1;
		}
		String relatedFirst = this.splits.get(0);
		String first = XORutils.XOR_streams(saltedKey, relatedFirst);
		outputFirst.setText(first);
		//MAIN OUTPUT
		genOutputFromKEY(saltedKey);
	}
	
	protected void genOutputFromKEY(String key) {
		//System.out.println("GENNING FROM KEY LEN = " + key.length());
		String output = "";
		for(String str : splits) {
			String undo = XORutils.XOR_streams(str, key);
			output = output + undo;
		}
		outputCipher.setText(output);
	}
	
	//TODO implement red highlighting of symbols
	protected void highlightInvalids() {
		
	}
	
	
	//LISTENER CLASS
	
	protected class ReflectionHandler implements InputMethodListener,KeyListener{
		
		public StreamCipherGUI link;
		public String linked_function;
		private Method function;
		
		public ReflectionHandler(StreamCipherGUI link,String funct) {
			this.link = link;
			this.linked_function = funct;
			try{
			function = link.getClass().getMethod(funct);
			}catch(Exception e) {
				System.out.println("Error Wrapping StreamCipherGUI");
				e.printStackTrace();
				function = null;
			}
		}
		
		public void actionPerformed() {
			if(function != null) {
				try{
					function.invoke(link);
				}catch(Exception e) {
					System.out.println("Error instigating Function");
					System.out.println(function.getName());
					e.printStackTrace();
				}
				
			}
			
		}

		@Override
		public void inputMethodTextChanged(InputMethodEvent arg0) {
			actionPerformed();
		}

		@Override
		public void caretPositionChanged(InputMethodEvent arg0) {
			//NO OP
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			actionPerformed();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			actionPerformed();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			actionPerformed();
		}
		
		
		
	}
	
}
