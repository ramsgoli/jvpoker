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

	private static int numTiedHands;
	private static int handRank;

	public static void breakTie(ArrayList<Player> wl) {
		numTiedHands = wl.size();
		int handRank = wl.get(0).getHandRank();


	}
	private void checkOnePairs(ArrayList<Player> wl) {
		Card highCard = wl.get(0).getCommonValues().get(2);
		for (int idx = 1; idx< wl.size(); idx++) {
			if (wl.get(idx).getCommonValues().get(2).compareTo(highCard) == 1) {
				highCard = wl.get(idx).getCommonValues().get(2);
			}
		}
		for (int idx = 0; idx < wl.size(); idx++) {
			if (wl.get(idx).getCommonValues().get(2).compareTo(highCard) == -1) {
				wl.remove(idx);
				idx--;
			}
		}
	}



	private void checkStraightOrFlush(ArrayList<Player> wl) {
		Card highCard = wl.get(0).getHighCard();
		
		for (int idx = 1; idx < wl.size(); idx++) {
			if (wl.get(idx).getHighCard().compareTo(highCard) == 1) {
				highCard = wl.get(idx).getHighCard();
			}
		}

		for (int idx = 0; idx < wl.size(); idx++) {
			if (wl.get(idx).getHighCard().compareTo(highCard) == -1) {
				wl.remove(idx);
				idx--;
			}

		}

	}
	
	private void checkFourOfAKind(ArrayList<Player> wl) {
		Card highCard = wl.get(0).getCommonValues().get(0);
		for (int idx = 1; idx< wl.size(); idx++) {
			if (wl.get(idx).getCommonValues().get(0).compareTo(highCard) == 1) {
				highCard = wl.get(idx).getCommonValues().get(0);
			}
		}
		for (int idx = 0; idx < wl.size(); idx++) {
			if (wl.get(idx).getCommonValues().get(0).compareTo(highCard) == -1) {
				wl.remove(idx);
				idx--;
			}
		}

	}
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


