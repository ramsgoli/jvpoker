package com.jvpoker;

import com.jvpoker.Hand;
import com.jvpoker.Card;
import java.util.HashMap;
import java.util.Map;

public class Betting {

	private HashMap<Hand, Integer> bm = new HashMap<Hand, Integer>();	
	private int betAmount = 0;
	private int pot;
	private int buyIn;

	public Betting(Hand[] hands) {
		for (Hand hand : hands) {
			bm.put(hand, betAmount);
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

	public void startRound() {
	}

}
