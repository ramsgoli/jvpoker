package com.jvpoker;

import com.jvpoker.Player;
import com.jvpoker.Card;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Betting {

	private HashMap<Player, Double> bm = new HashMap<Player, Double>();	
	private double betAmount = 0;
	private double pot;
	private double buyInVal = 10;
	private Player[] players;

	public Betting(Player[] players) {
		this.players = players;
		for (Player player : players) {
			bm.put(player, betAmount);
		}
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		for (Map.Entry m: bm.entrySet()) {
			buf.append(m.getKey() + " : " + m.getValue() + " , ");
		}

		return buf.toString();
	}
	
	public void startBuyIn() {
		for (Player player : players) {
			double buyIn = player.getMoney(buyInVal);
			bm.put(player, buyIn);
		}
		for (Double val : bm.values()) {
			pot += val.doubleValue();
		}
	}

	public void winner(ArrayList<Player> winningPlayers) {
		for (Player player: winningPlayers) {
			//System.out.println("Winner : " + player);
			player.winMoney((double) pot/winningPlayers.size());
		}

		pot = 0.0;
		for (Double val : bm.values()) {
			val = 0.0;
		}
	}
		
	public void startRound() {
	}

}
