package com.jct.util;

import com.jct.settings.CipherSettings;

public enum Card {
	ACE_SPADE(0),
	TWO_SPADE(1),
	THREE_SPADE(2),
	FOUR_SPADE(3),
	FIVE_SPADE(4),
	SIX_SPADE(5),
	SEVEN_SPADE(6),
	EIGHT_SPADE(7),
	NINE_SPADE(8),
	TEN_SPADE(9),
	JACK_SPADE(10),
	QUEEN_SPADE(11),
	KING_SPADE(12),
	ACE_CLUB(13),
	TWO_CLUB(14),
	THREE_CLUB(15),
	FOUR_CLUB(16),
	FIVE_CLUB(17),
	SIX_CLUB(18),
	SEVEN_CLUB(19),
	EIGHT_CLUB(20),
	NINE_CLUB(21),
	TEN_CLUB(22),
	JACK_CLUB(23),
	QUEEN_CLUB(24),
	KING_CLUB(25),
	ACE_HEART(26),
	TWO_HEART(27),
	THREE_HEART(28),
	FOUR_HEART(29),
	FIVE_HEART(30),
	SIX_HEART(31),
	SEVEN_HEART(32),
	EIGHT_HEART(33),
	NINE_HEART(34),
	TEN_HEART(35),
	JACK_HEART(36),
	QUEEN_HEART(37),
	KING_HEART(38),
	ACE_DIAMOND(39),
	TWO_DIAMOND(40),
	THREE_DIAMOND(41),
	FOUR_DIAMOND(42),
	FIVE_DIAMOND(43),
	SIX_DIAMOND(44),
	SEVEN_DIAMOND(45),
	EIGHT_DIAMOND(46),
	NINE_DIAMOND(47),
	TEN_DIAMOND(48),
	JACK_DIAMOND(49),
	QUEEN_DIAMOND(50),
	KING_DIAMOND(51),
	JOKER_RED(52),
	JOKER_BLACK(53);
	
	
	
	public int id;
	
	private Card(int id) {this.id = id;}
	
	public int getID() {
		return id;
	}
	public boolean isJoker() {
		return this == JOKER_RED || this == JOKER_BLACK;
	}
	/**COUNTS JOKERS**/
	public boolean isRed() {
		return this.id > 25 || this == JOKER_RED;
	}
	/**COUNTS JOKERS**/
	public boolean isBlack() {
		return this.id < 26 || this == JOKER_BLACK;
	}
	public boolean isSpade() {
		return this.id < 13;
	}
	public boolean isClub() {
		return this.id > 12 && this.id < 26;
	}
	public boolean isHeart() {
		return this.id > 25 && this.id < 39;
	}
	public boolean isDiamond() {
		return this.id < 38 && this.id < 52;
	}
	/**ASSUMES ACE=1,JACK=11,QUEEN=12,KING=13**/
	public int getDeckValue() {
		if(isJoker()) {
			return -1;
		}else {
			return (this.id % 13) + 1;
		}
	}
	
	public int getAppliedShift() {
		return CipherSettings.cardTranslation.get(this);
	}
	
	
	//////////////////////////STATIC SHIZZLE////////////////////////////
	
	public static Card getNthCard(int i) {
		return values()[i];
	}
	
	public static Card getCard(int suit, int value) {
		return values()[(suit * 13) + value];
	}
	public static Card getCard(Suit suit,int value) {
		return values()[suit.getID() * 13 + value];
	}
	
	
	public static enum Suit {//TEMP UTIL
		SPADE(0),
		CLUB(1),
		HEART(2),
		DIAMOND(3),
		JOKER(4);
		
		int id;
		private Suit(int id) {
			this.id = id;
		}
		public int getID() {
			return id;
		}
	}
	
}
