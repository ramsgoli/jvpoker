package com.jvpoker;

import com.jvpoker.Card;
import com.jvpoker.MyFormatter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;

public class Hand
{
	private static int numHands;
	private Card[] cards;
	private int numCardsInHand = 5;
	private int currNumCards = 0;
	private int rank;
	private ArrayList<Card> commonValues = new ArrayList<Card>(4); //idx 0 = common 4 of a kind card,
																   //idx 1 = common 3 of a kind card,
																   //idx 2 and 3 = common 2 of a kind cards
	private int count = 2;

	private static Logger logger = Logger.getLogger(Hand.class.getName());
	private int handId;
	static {
		logger.setUseParentHandlers(false);
		MyFormatter formatter = new MyFormatter();
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(formatter);
		logger.addHandler(handler);
	}


	public Hand()
	{
		this.cards = new Card[numCardsInHand];
		numHands++;
		handId = numHands; 
	}

	public void clearHand()
	{
		for (int idx = 0; idx < cards.length; idx++)
		{
			cards[idx] = null;
		}
		currNumCards = 0; 
	}


	public void deal(Card card)
	{
		for (int idx = 0; idx < numCardsInHand; idx++)
		{
			if (cards[idx] == null)
			{
				cards[idx] = card;
				currNumCards++;
				
				if (currNumCards == 5) {
					Arrays.sort(cards);
				}
				return;
			}
		}
	}

	public void sortHand()
	{
		Arrays.sort(cards);
	}


	public Card[] getHand()
	{
		return cards;
	}

	public ArrayList<Card> getCommonValues() {
		return commonValues;
	}

	public void printHand()
	{
		//Arrays.sort(cards);

		for (int idx = 0; idx < numCardsInHand; idx++)
		{
			logger.info( cards[idx]  + ", ");
		}

	}

	public Card getHighCard()
	{
		return cards[4]; 
	} 

	public Card getSecondHighCard() {
		return cards[3];
	}

	public Card getThirdHighCard() {
		return cards[2];
	}

	public Card getFourthHighCard() {
		return cards[1];
	}

	public Card getLowestCard() {
		return cards[0];
	}
	
	public int getCategory() {
		//sortHand();
		if (isRoyalFlush()) {return 1; }  
		else if (isStraightFlush()) {return 2;} 
		else if (isFourOfAKind()) {return 3;} 
		else if (isFullHouse()) {return 4;} 
		else if (isFlush()) {return 5;}
		else if (isStraight()) {return 6;} 
		else if (isThreeOfAKind()) {return 7;}
		else if (isTwoPair()) {return 8;}
		else if (isOnePair()) {return 9;}
		else {return 10;} //high card
	}

	
		
	private boolean isFlush() {
		int suite = cards[0].getSuite();

		for (int idx = 1; idx < numCardsInHand; idx++) {
			if (cards[idx].getSuite() != suite) {
				return false;
			}
		}
		return true;
	}

	private boolean isRoyalFlush() {
		if (!isStraightFlush()) {return false;}
		if (cards[0].getNumber() != 10) { return false;}

		return true;

	}

	private boolean isStraightFlush() {
		if (!isStraight()) {return false;}
		if (!isFlush()) {return false;}

		return true;
	}

	private boolean isStraight() {
		int cardNumber = cards[numCardsInHand-1].getNumber();
		for (int idx = numCardsInHand-1; idx >=0; idx--) {
			if (cards[idx].getNumber() != cardNumber) {
				return false;
			}
			cardNumber--;
		}
		return true;

	}

	private boolean isFullHouse() {
		if (isThisManyOfAKind(2, 0, 1) && isThisManyOfAKind(3, 2, 4)) {return true;} 
		else if (isThisManyOfAKind(3, 0, 2) && isThisManyOfAKind(2, 3, 4)) {return true;}
		return false;

	}

	private boolean isTwoPair() {
		if (isThisManyOfAKind(2, 0, 1) && isThisManyOfAKind(2, 2, 4)) {return true;} 
		else if (isThisManyOfAKind(2, 0, 2) && isThisManyOfAKind(2, 3, 4)) {return true;}
		return false;
	}


	private boolean isFourOfAKind() {
		if (isThisManyOfAKind(4)) {
			return true;
		}
		return false;
	}
	
	private boolean isThreeOfAKind() {
		if (isThisManyOfAKind(3)) {
			return true;
		}
		return false;
	}

	private boolean isOnePair() {
		if (isThisManyOfAKind(2)) {
			return true;
		}
		return false;
	}

	private boolean isThisManyOfAKind(int num) {
		return isThisManyOfAKind(num, 0, 4); //0 = start index, 4 = end index
	}

	private boolean isThisManyOfAKind(int num, int startIdx, int endIdx) {
		for (int idx = startIdx; idx <= endIdx + 1 - num; idx++) {
			if (cards[idx].getNumber() == cards[idx + num-1].getNumber()) {
				if (idx == endIdx + 1 - num) {
					switch (num) {
						case 4: commonValues.set(0, cards[idx]);
								break;
						case 3: commonValues.set(1, cards[idx]);
								break;
						case 2: commonValues.set(count, cards[idx]);
								count++;
								break;
					}
					return true;
				}
				else if (cards[idx + num-1].getNumber() !=
						cards[idx+num].getNumber()) {
						switch (num) {
						case 4: commonValues.set(0, cards[idx]);
								break;
						case 3: commonValues.set(1, cards[idx]);
								break;
						case 2: commonValues.set(count, cards[idx]);
								count++;
								break;
						}
					return true;
					}
				else {
					return false;
				}
		
			}	
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return Integer.toString(handId);
	}

	/*
	public static void main(String[] args)
	{
		Hand hand = new Hand();

		hand.addCard( new Card(4, 2));
		hand.addCard( new Card(4, 3));
		hand.addCard( new Card(4, 4));
		hand.addCard( new Card(4, 5));
		hand.addCard( new Card(4, 6));


		System.out.println("card rank is: " + hand.getCategory());
		
	}
	*/

}

