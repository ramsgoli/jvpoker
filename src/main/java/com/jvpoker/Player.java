package com.jvpoker;

import com.jvpoker.Hand;

public class Player {
	
	private Hand hand;
	private int totalMoney;
	private int decision; //1 = check; 2 = raise; 3 = fold
	private static int numPlayers;
	private int id;

	public Player() {
		numPlayers++;
		id = numPlayers;
		hand = new Hand();
	}

	public void startWithMoney(int amount) {
		totalMoney = amount;
	}

	public void winMoney(int amount) {
		totalMoney += amount;
	}


}

