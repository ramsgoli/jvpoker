package com.jvpoker;

import com.jvpoker.Hand;
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> b996c8fdc170ab2aec1a1090cfa26f6e159e5a15
import java.util.logging.Logger;

public class Player implements Comparable<Player> {

	private static Logger logger = Logger.getLogger(Player.class.getName());

	
	private Hand hand;
	private double totalMoney;
	private int decision; //1 = check; 2 = raise; 3 = fold
	private static int numPlayers;
	private int id;

	public Player(double money) {
		numPlayers++;
		id = numPlayers;
		totalMoney = money;
		hand = new Hand();
	}

	public int getID() {
		return id;
	}

	public void clearHand()
	{
		hand.clearHand();
	}

	public void deal(Card card) {
		hand.deal(card);
	}

	public void printHand() {
		//System.out.print("P " + id + ": ");
		hand.printHand();
	}

	public int getHandRank() {
		return hand.getCategory();
	}

	public Card getHighCard() {
		return hand.getHighCard();
	}

	public void startWithMoney(double amount) {
		totalMoney = amount;
	}

	public void winMoney(double amount) {
		totalMoney += amount;
	}

	public double getMoney(double amount) {
		totalMoney -= amount;
		return amount;
	}

<<<<<<< HEAD
	public ArrayList<Card> getCommonValues() {
		return hand.getCommonValues();
	}


=======
	public Card getHighCard() {
		return hand.getHighCard();
	}

>>>>>>> b996c8fdc170ab2aec1a1090cfa26f6e159e5a15
	@Override
	public String toString() {
		return "P: " + id + ", M: " + totalMoney;   
	}

	@Override
	public int compareTo(Player player) {
		if (this.getHandRank() < player.getHandRank())
		{
			return -1;
		}
		else if (this.getHandRank() == player.getHandRank())
		{
			return 0;
		}
		else
		{		return 1;
		}
	}

}

