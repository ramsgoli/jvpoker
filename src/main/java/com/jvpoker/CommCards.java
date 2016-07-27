package com.jvpoker;

import com.jvpoker.Card;
import com.jvpoker.Hand;
import java.util.ArrayList;

public class CommCards {
	private Card[] commCards;

	public CommCards() {
		commCards = new Card[5];
	}

	public void deal(Card card) {
		for (int idx = 0; idx < 5; idx++)
		{
			if (commCards[idx] == null)
			{
				commCards[idx] = card;

				return;
			}
		}
	}

	public Card getCard(int idx) {
		return commCards[idx];
	}

	public Card[] getAllCards() {
		return commCards;
	}

	public void printCards()
	{
		for (int idx = 0; idx < 5; idx++)
		{
			System.out.println("Card " + (idx+1) + " = " + commCards[idx] + "\n");
		}
	}
}

