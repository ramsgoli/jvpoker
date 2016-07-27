package com.jvpoker;

import com.jvpoker.Hand;
import com.jvpoker.Card;
import com.jvpoker.Deck;
import com.jvpoker.CommCards;
import com.jvpoker.Betting;
import com.jvpoker.Player;

public class Game {

	private int numPlayers;
	private Player[] players;
	private Hand[] hands;
	private Deck gd;
	private CommCards commCards;
	private Betting betManager;

	public void start(int numPlayers) {
		gd = new Deck();
		gd.fillDeck();
		gd.shuffle();
		this.numPlayers = numPlayers;
		hands = new Hand[numPlayers];
		commCards = new CommCards();
		for (int idx = 0; idx < numPlayers; idx++)
		{
			hands[idx] = new Hand();
			players[idx] = new Player();
		}
		betManager = new Betting(hands);


	}

	public void dealHoleCards() {
		for (int idx = 0; idx < 2; idx++) {
			for (Hand hand : hands) { 
				Card card = gd.drawCard();
				hand.deal(card);
			}
		}
	}

	public void dealCards() {

		for (int idx = 0; idx < 5; idx++)
		{
			for (Hand hand : hands)
			{
				Card card = gd.drawCard();
				hand.deal(card);
			}
		}

	}

	public Hand[] getHands() {
		return this.hands;
	}

	public void dealFlop() {
		for (int idx = 0; idx < 3; idx++) {
			Card card = gd.drawCard();
			commCards.deal(card);
		}
	}
	public void dealTurn() {
		Card card = gd.drawCard();
		commCards.deal(card);
	}

	public void dealRiver() {
		Card card = gd.drawCard();
		commCards.deal(card);
	}

	public void printHands() {

		for (int idx = 0; idx < numPlayers; idx++) {
				hands[idx].printHand();
				System.out.println(hands[idx].getCategory());
				System.out.println("-----------------\n");
			}
	}

	public static void main(String[] args) {
		int numPlayers = 2;
		Game game = new Game();
		game.start(numPlayers);
		game.dealCards();
		game.printHands();
	
	}


}
