package com.jc.mod26.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Modulo26GUI {
	
	
	public JFrame window;
	public JTabbedPane tabs;
	private JPanel tab1;
	private JPanel tab2;
	private JPanel tab3;
	
	//TAB1
	public JTextArea input1;
	public JTextArea input2;
	
	//TAB2
	public JTextArea cancelstream;
	
	//TAB3
	public JTextArea output1;
	public JTextArea output2;
	
	
	
	public Modulo26GUI() {
		window = new JFrame("Mod26 Stream Cancellation");
		tabs = new JTabbedPane();
		input1 = createTextArea();
		input2 = createTextArea();
		output1 = createTextArea();
		output2 = createTextArea();
		cancelstream = createTextArea();
		cancelstream.setEditable(false);//NO EDITS
		window.getContentPane().add(tabs);
		////////////////////////////
		tab1 = new JPanel();
		tab2 = new JPanel();
		tab3 = new JPanel();
		tab1.add(input1,BorderLayout.WEST);
		tab1.add(input2,BorderLayout.EAST);
		tabs.addTab("Inputs", tab1);
		tab2.add(cancelstream);
		tabs.addTab("Cancelstream", tab2);
		tab3.add(output1,BorderLayout.WEST);
		tab3.add(output2,BorderLayout.EAST);
		tabs.addTab("Outputs",tab3);
		
		///SHIZZLE
		tab1.setLayout(null);
		tab2.setLayout(null);
		tab3.setLayout(null);
		
		//////////////
		tabs.addComponentListener(new guiResizeHandler(this));
		
		input1.addKeyListener(new inputUpdateListener(this));
		input2.addKeyListener(new inputUpdateListener(this));
		///OUTPUT HANDLING
		output1.addKeyListener(new outputUpdateListener(this, true));
		output2.addKeyListener(new outputUpdateListener(this, false));
		
		///////////////FINALIZE
		window.setMinimumSize(new Dimension(100,100));
		window.setMaximumSize(new Dimension(4000,4000));
		window.setPreferredSize(new Dimension(720,480));
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void updateInputs() {
		String i1 = input1.getText();
		String i2 = input2.getText();	//KEY IS 1 - 2;
		i1 = Modulo26Utils.formatString(i1,false);
		i2 = Modulo26Utils.formatString(i2,false);
		input1.setText(i1);
		input2.setText(i2);
		String key = Modulo26Utils.handleStreams(i1, i2, false);
		//NEXT
		cancelstream.setText(key);
		//OUTPUTS ARE NOW WRONG
		updateOutputs(true);
	}
	
	//TRUE 2iswrong, FALSE 1iswrong
	public void updateOutputs(boolean from1) {
		String o1 = output1.getText();//IS A
		String o2 = output2.getText();//IS   -B
		o1 = Modulo26Utils.formatString(o1,true);
		o2 = Modulo26Utils.formatString(o2,true);
		output1.setText(o1);
		output2.setText(o2);
		String key = cancelstream.getText();
		
		if(from1) {			//A - K = B
			o2 = Modulo26Utils.handleStreams(o1, key, false);
			output2.setText(o2);
		}else {				//B + K = A
			o1 = Modulo26Utils.handleStreams(o2, key, true);
			output1.setText(o1);
		}
	}
	
	private static JTextArea createTextArea() {
		JTextArea txt = new JTextArea();
		txt.setEditable(true);
		txt.setLineWrap(true);
		txt.setWrapStyleWord(true);
		txt.setBorder(BorderFactory.createLoweredBevelBorder());
		return txt;
	}
	
	
	
	private class inputUpdateListener implements KeyListener {
		private Modulo26GUI ref;

		@Override
		public void keyPressed(KeyEvent arg0) {
			ref.updateInputs();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			ref.updateInputs();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			ref.updateInputs();
		}
		public inputUpdateListener(Modulo26GUI gui) {
			this.ref = gui;
		}
		
		
	}
	
	private class outputUpdateListener implements KeyListener {
		private Modulo26GUI ref;
		private boolean is1correct;
		public outputUpdateListener(Modulo26GUI gui, boolean is1) {
			ref=gui;is1correct=is1;
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			ref.updateOutputs(is1correct);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			ref.updateOutputs(is1correct);
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			ref.updateOutputs(is1correct);
		}
	}
	
	private static class guiResizeHandler implements ComponentListener {
		private Modulo26GUI ref;
		private guiResizeHandler(Modulo26GUI r) {
			ref=r;
		}
		@Override
		public void componentHidden(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentMoved(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentResized(ComponentEvent resizeEvent) {
			//HANDLE THIS
			int width = Math.max(ref.tab1.getWidth(), Math.max(ref.tab2.getWidth(),ref.tab3.getWidth()));
			int height= Math.max(ref.tab1.getHeight(),Math.max(ref.tab2.getHeight(),ref.tab3.getHeight()));
			int whf = width / 2;
			ref.input1.setBounds(0,0,whf,height);
			ref.input2.setBounds(whf,0,whf,height);
			ref.cancelstream.setBounds(0,0,width,height);
			ref.output1.setBounds(0,0,whf,height);
			ref.output2.setBounds(whf,0,whf,height);
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
