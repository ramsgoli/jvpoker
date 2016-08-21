package com.jvpoker;

import com.jvpoker.Hand;
import com.jvpoker.Card;
import com.jvpoker.Deck;
import com.jvpoker.Player;
import com.jvpoker.MyFormatter;
import java.util.HashMap;
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
		switch (handRank) {
			case 1: break;
			case 2: checkStraight(wl);
					break;
			case 3: checkFourOfAKind(wl);
					break;
			case 4: checkThreeOfAKind(wl);
					break;
			case 5: checkFlush(wl);
					break;
			case 6: checkStraight(wl);
					break;
			case 7: checkThreeOfAKind(wl);
					break;
			case 8: checkTwoPairs(wl);
					break;
			case 9: checkOnePairs(wl);
					break;
			case 10: checkHighCard(wl);
					 break;
		}

	}

	private static void checkHighCard(ArrayList<Player> wl) {
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



	private static void checkOnePairs(ArrayList<Player> wl) {
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

	private static void checkTwoPairs(ArrayList<Player> wl) {
		for (int val = 2; val < 4; val++) {
			Card highCard = wl.get(0).getCommonValues().get(val);
			for (int idx = 1; idx< wl.size(); idx++) {
				if (wl.get(idx).getCommonValues().get(val).compareTo(highCard) == 1) {
					highCard = wl.get(idx).getCommonValues().get(val);
				}
			}
			for (int idx = 0; idx < wl.size(); idx++) {
				if (wl.get(idx).getCommonValues().get(val).compareTo(highCard) == -1) {
					wl.remove(idx);
					idx--;
				}
			}
		}
	}

	private static void checkFlush(ArrayList<Player> wl) {
		
		for (int card = 4; card >= 0; card--) {

			Card highCard = wl.get(0).getCard(card);
		
			for (int idx = 1; idx < wl.size(); idx++) {
				if (wl.get(idx).getCard(card).compareTo(highCard) == 1) {
					highCard = wl.get(idx).getCard(card);
				}
			}
			for (int idx = 0; idx < wl.size(); idx++) {
				if (wl.get(idx).getCard(card).compareTo(highCard) == -1) {
					wl.remove(idx);
					idx--;
				}
			}

			if (wl.size() == 1) {
				return;
			}


		}
	}

	private static void checkThreeOfAKind(ArrayList<Player> wl) {
		Card highCard = wl.get(0).getCommonValues().get(1);
		for (int idx = 1; idx< wl.size(); idx++) {
			if (wl.get(idx).getCommonValues().get(1).compareTo(highCard) == 1) {
				highCard = wl.get(idx).getCommonValues().get(1);
			}
		}
		for (int idx = 0; idx < wl.size(); idx++) {
			if (wl.get(idx).getCommonValues().get(1).compareTo(highCard) == -1) {
				wl.remove(idx);
				idx--;
			}
		}
	}

	private static void checkStraight(ArrayList<Player> wl) {
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
	
	private static void checkFourOfAKind(ArrayList<Player> wl) {
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
			 		

}


