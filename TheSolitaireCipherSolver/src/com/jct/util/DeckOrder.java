package com.jct.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DeckOrder implements Iterable<Card>{
	
	private List<Card> DeckOrder;
	
	//NEW RANDOM DECK ORDER
	public DeckOrder() {
		Random rand = new Random();
		List<Integer> validDeckValues = new ArrayList<Integer>();
		for(int i = 1; i <= 54; i++) {
			validDeckValues.add(i);
		}
		//PUSH TO DECKORDER
		DeckOrder = new ArrayList<Card>();
		for(int q = 0; q < 54; q++) {
			int strip = rand.nextInt(validDeckValues.size());
			int removed = validDeckValues.remove(strip);
			DeckOrder.add(Card.getNthCard(removed));
		}
	}
	
	//VALUE SEEDED LAUNCH
	public DeckOrder(int... Values) {
		if(Values.length == 54) {
			//RANDOMLY ASSUME CORRECTNESS EVEN  WHEN IT IS LACKING
			for(int Value : Values) {
				DeckOrder.add(Card.getNthCard(Value));
			}
		}else{
			System.out.println("YOU SUCK!");
		}
	}
	
	public DeckOrder(Card... Values) {
		if(Values.length == 54) {
			//RANDOMLY ASSUME CORRECTNESS EVEN  WHEN IT IS LACKING
			for(Card Value : Values) {
				DeckOrder.add(Value);
			}
		}else{
			System.out.println("YOU SUCK!");
		}
	}

	@Override
	public Iterator<Card> iterator() {
		return new DeckIterator(this);
	}

	private class DeckIterator implements Iterator<Card>{
		private DeckOrder ref;
		private int current;
		
		private DeckIterator(DeckOrder d) {
			ref = d;current = 0;
		}
		
		@Override
		public boolean hasNext() {
			return current < ref.DeckOrder.size();
		}

		@Override
		public Card next() {
			current++;
			return ref.DeckOrder.get(current - 1);
		}
		
	}
	
}
