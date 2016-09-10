package com.jvpoker;

import com.jvpoker.Hand;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.HashMap;

public class Player implements Comparable<Player> {

	private static Logger logger = Logger.getLogger(Player.class.getName());

	
	private Hand hand;
	private double totalMoney;
	private String id;

	public Player(String id, double money) {
		this.id = id;
		totalMoney = money;
		hand = new Hand();
	}

	public String getID() {
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

	public HashMap<Integer, Card> getCommonValues() {
		return hand.getCommonValues();
	}

	public Hand getHand() {
		return hand;
	}

	public Card getCard(int idx) {
		return hand.getCard(idx);
	}

	public Card getHighCard() {
		return hand.getHighCard();
	}

	/*
	@Override
	public String toString() {
		return "P: " + id + ", M: " + totalMoney;   
	}
	*/
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

