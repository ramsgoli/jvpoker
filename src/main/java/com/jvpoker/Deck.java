package com.jvpoker;

import com.jvpoker.Card;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private ArrayList<Card> dk;
	private final static int DECK_SIZE = 52;
	private final static int TOP_CARD = 0;
	
	Deck() {
		dk = new ArrayList<Card>(DECK_SIZE);
		fillDeck();
		shuffle();
	}
	public int getSize() {
		return dk.size();
	}
	
	public void addCard(int idx, Card card) {
		dk.add(idx, card);
	}

	public void shuffle()
	{
		Collections.shuffle(dk);
	}

	public void fillDeck() {

		dk.clear();

		int deckIdx = 0;
		for (int suiteIdx = 1; suiteIdx < 5; suiteIdx++ ) {
			for (int valIdx = 2; valIdx < 15; valIdx++) {
				addCard(deckIdx, new Card(suiteIdx, valIdx));
				deckIdx++;
			}
		}
	
	}

	public Card drawCard() {
	
		return dk.remove(TOP_CARD);
	}


	public void printDeck() {
		int deckIdx = 0;
		for (int suiteIdx = 1; suiteIdx < 5; suiteIdx++ ) {
			for (int valIdx = 2; valIdx < 15; valIdx++) {
				System.out.print(dk.get(deckIdx));
				deckIdx++;
			}
		}
		System.out.print("\n");
		



	}
}

