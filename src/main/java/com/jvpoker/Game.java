package com.jvpoker;

import com.jvpoker.Hand;
import com.jvpoker.Card;
import com.jvpoker.Deck;
import com.jvpoker.CommCards;
import com.jvpoker.Betting;
import com.jvpoker.Player;
import com.jvpoker.MyFormatter;
import java.util.logging.ConsoleHandler;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.net.ServerSocket;
import java.net.Server;
import java.io.PrintWriter;



public class Game {
	private int numPlayers = 0;
	private double tableValue = 1000;
	private Player[] players;
	private Deck gd;
	private CommCards commCards;
	private Betting betManager;
	private ArrayList playerStreams;
	private static Logger logger = Logger.getLogger(Game.class.getName());
	static {
		logger.setUseParentHandlers(false);
		MyFormatter formatter = new MyFormatter();
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(formatter);
		logger.addHandler(handler);
	}

	public void go() {

		playerStreams = new ArrayList();

		try {

			ServerSocket serverSocket = new ServerSocket(4242);
			while (numPlayers < 4) {
				Socket commSocket = serverSocket.accept();
				PrintWriter writer = new PrintWriter(serverSocket.getOutputStream());
				playerStreams.add(writer);

		} catch(IOException ex) {
			ex.printStackTrace();
		}
		



	}
	public void initialize(int numPlayers) {
		this.numPlayers = numPlayers;
		commCards = new CommCards();
		initPlayers();
		betManager = new Betting(players);
	}

	public void playRounds(int num) {
		for (int idx=0; idx<num; idx++) {
			startBuyIn();
			dealCards();
			getWinners();
			clearHands();
		}
	}

	public void dealHoleCards() {
		for (int idx = 0; idx < 2; idx++) {
			for (Player player : players) { 
				Card card = gd.drawCard();
				player.deal(card);
			}
		}
	}

	public void initPlayers() {
		players = new Player[numPlayers];

		for (int idx = 0; idx < numPlayers; idx++) {
			players[idx] = new Player(tableValue);
		}
		//testing
		logger.info("Initialize players: ");
		printPlayers();
	}

	public void dealCards() {
		gd = new Deck();

		for (int idx = 0; idx < 5; idx++)
		{
			for (Player player : players)
			{
				Card card = gd.drawCard();
				player.deal(card);
			}
		}
		printHands();

	}

	public void clearHands()
	{
		for (Player player : players)
		{
			player.clearHand();
		}

		//Testing
		logger.info("----Next Round---- ");
	}
	/*
	public Hand[] getHands() {
		return this.hands;
	}
	*/
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

	public void startBuyIn() {
		betManager.startBuyIn();
		//Testing
		logger.info("After Buy in:");
		printPlayers();
	}

	public void getWinners() {
		Arrays.sort(players);
		ArrayList<Player> wl = new ArrayList<Player>();
		wl.add(players[0]);
		int winningRank = players[0].getHandRank();

		for (int idx = 1; idx < players.length; idx++)
		{
			if (players[idx].getHandRank() == winningRank) {
				wl.add(players[idx]);
			}
		}
		
		if (wl.size() != 1) {
			TieBreaker.breakTie(wl);
		}

		betManager.winner(wl);

		//Testing
		for (Player player: wl) {
			logger.info("Winner: " + player.getID());
		}
		printPlayers();
	}

	public void printHands() {

		for (int idx = 0; idx < numPlayers; idx++) {
				logger.info(Integer.toString(players[idx].getID()));
				players[idx].printHand();
				logger.info(" Rank: " + players[idx].getHandRank());
				logger.info("---------");
			}
	}

	public void printPlayers()
	{
		for (int idx = 0; idx < players.length; idx++)
		{
			logger.info(players[idx].toString());
		}
	}

	public static void main(String[] args) {

		int numPlayers = 5;
		int numRounds = 0;
		Game game = new Game();
		game.initialize(numPlayers);
		if (args != null && args.length > 0)
		{
			numRounds = Integer.parseInt(args[0]);
		}
		else
		{
			numRounds = 1;
		}

		game.playRounds(numRounds);
	}
}


