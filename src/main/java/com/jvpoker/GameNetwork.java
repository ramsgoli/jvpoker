package com.jvpoker;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStreamWriter;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class GameNetwork {


	private int numPlayers = 0;
	private Socket socket;

	public static void main(String[] args) throws Exception{
		GameNetwork g = new GameNetwork();
		g.sendPut();
		g.listenForNode();
	}

	public void sendPut() throws Exception {

		System.out.println("connecting to url 'http://localhost:4000");
		String url = "http://localhost:4000/";
		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("PUT");
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");


		String payload = "{\"name\": \"Ram Goli\"}";
		OutputStreamWriter ows = new OutputStreamWriter(con.getOutputStream());
		ows.write(payload);
		ows.flush();
		ows.close();
		

		int responseCode = con.getResponseCode();
		System.out.println("put sent. Response Code: " + responseCode);
		
	}

	public void listenForNode() {
		try {
			socket = IO.socket("http://localhost:4040");
			socket.on("initialize", new Emitter.Listener() {
				public void call(Object... args) {
					numPlayers = (Integer) args[0];
					System.out.println(numPlayers + " are initialized.");
				}
			
			}); 
			socket.connect();
		} catch(Exception e) {
			System.out.println(e);
		}
	
	}




}
