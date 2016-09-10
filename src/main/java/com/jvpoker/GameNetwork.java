package com.jvpoker;

import java.util.ArrayList;

import com.jvpoker.*;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.AckRequest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameNetwork {


	private int numPlayers = 0;
	private ArrayList<String> idList = new ArrayList<String>();
	private Game game;
	private int gameState;
	private int port = 8080;
	private Player[] players; 
	private ObjectMapper mapper;

	public static void main(String[] args) throws Exception{
		GameNetwork g = new GameNetwork();
		g.startServer();
	}

	
	public void startServer() {
		Configuration config = new Configuration();
		config.setHostname("localhost");
		config.setPort(port);

		final SocketIOServer server = new SocketIOServer(config);
		System.out.println("server started at port " + port);

		server.addEventListener("newPlayer", String.class, new DataListener<String>() {

			@Override
			public void onData(SocketIOClient client, String id, AckRequest ackRequest) {
				System.out.println("ID received: " + id);
				idList.add(id);
				server.getBroadcastOperations().sendEvent("confirmed", id);
			}
		});
		server.addEventListener("startGame", Boolean.class, new DataListener<Boolean>() {

			@Override
			public void onData(SocketIOClient client, Boolean start, AckRequest ackRequest) {
				if (start) {
					System.out.println("Starting game with " + idList.size() + " players.");
					game = new Game(idList);
					System.out.println("New game initialized. startind hole cards.");
					startDealHoleCards();
				}

			}
		});
		server.start();
	
	}

	public void startBuyIn() {
		game.startBuyIn();	
	
	}

	

	public void startDealHoleCards() {
		game.dealHoleCards();
		System.out.println("Hole cards dealt");
		players = game.getPlayers();
		Player player1 = players[0];
		try {
			mapper = new ObjectMapper();
			String playerJsonString = mapper.writeValueAsString(player1);
			System.out.println("Players initialized as json:");
			System.out.println(playerJsonString);
		} catch  (JsonGenerationException e) {
	 		e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
