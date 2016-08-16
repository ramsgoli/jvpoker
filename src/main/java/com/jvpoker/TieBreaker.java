package com.jvpoker;

import com.jvpoker.Hand;
import com.jvpoker.Card;
import com.jvpoker.Deck;
import com.jvpoker.Player;
import com.jvpoker.MyFormatter;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TieBreaker {

	private int numTiedHands;
	private int handRank;

	public static void breakTie(ArrayList<Player> wl) {
		numTiedHands = wl.size();
		int handRank = wl[0].getHandRank();


	private checkPairs(ArrayList<Player> wl) {
	}

	private checkStraight(ArrayList<Player> wl)
		Card topCard = wl[0].getHighCard();
		for (int idx = 1; idx < wl.size(); idx++) {
			if (wl[idx].compareTo(topCard) == 1) {
				topCard = wl[idx];
			}
		}

			 		

}


