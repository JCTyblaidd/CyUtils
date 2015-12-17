package com.jc.solitaire.search;

import java.math.BigInteger;

import com.jc.solitaire.DeckOrder;

public class ExhaustiveSearchThread implements Runnable{
	
	private int num;
	private Thread thread;
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public ExhaustiveSearchThread(int searchnum) {
		this.num=searchnum;
		thread = new Thread(this);
		thread.start();
	}
	
	
	@Override
	public void run() {
		BigInteger max = getPerms();
		BigInteger one = new BigInteger("1");
		for(BigInteger i = new BigInteger("0"); i.compareTo(max) == -1; i=i.add(one)) {
			String str = getNthString(i);
			//System.out.println("DBG: "+i.intValue()+"th str = " + str);
			DeckOrder deck = new DeckOrder(str);	
			String res = deck.decrypt(ExhaustiveSearchLaunch.txt);
			if(res == null) {
				System.out.println("WTF?");
			}
			String toadd = str + " :>> \n" + res;
			ExhaustiveSearchLaunch.solutions.add(toadd);//PUSH ANSWER
		}
		System.out.println("ExhaustiveSearchFinish: "+num);
	}
	private  BigInteger getPerms() {
		BigInteger result = new BigInteger("26");
		return result.pow(num);//26 ^ n
	}
	
	private String getNthString(BigInteger n) {
		StringBuilder res = new StringBuilder();
		BigInteger t6 = new BigInteger("26");
		for(int i = 0; i < num; i++) {
			BigInteger[] qqq = n.divideAndRemainder(t6);
			res.append(ALPHABET.charAt(qqq[1].intValue()));
			n = qqq[0];
		}
		return res.toString();
	}

}
