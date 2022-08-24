package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.net.Server;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.badlogic.gdx.Gdx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class ServerConnectionTestState extends GameState {
	private Button backButton;
	private Server server;

	private String internalIP;
	private String externalIP;

	public ServerConnectionTestState(){
		backButton = new Button("ui/menuButton.png");
		backButton.xPos = 0;
		backButton.yPos = 0;
		backButton.width = Level.tileSize * 2;
		backButton.height = Level.tileSize * 2;

		server = new Server("3.18.105.5", 8087);
		server.connect();

		try {
			internalIP = InetAddress.getLocalHost().getHostAddress().trim();

			URL url = new URL("https://api.ipify.org");
			BufferedReader sc = new BufferedReader(new InputStreamReader(url.openStream()));
			externalIP = sc.readLine().trim();
			sc.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run(){
		backButton.update();
		if(backButton.isPressed){
			server.disconnect();
			StateManager.popState();
		}

		backButton.render();

		TextRenderer.renderString("INTERNAL IP: " + internalIP, 0, backButton.height, TextRenderer.calculateFittingScale("INTERNAL IP: " + internalIP, Gdx.graphics.getWidth()));
		TextRenderer.renderString("EXTERNAL IP: " + externalIP, 0, TextRenderer.getNextLineY(), TextRenderer.calculateFittingScale("EXTERNAL IP: " + externalIP, Gdx.graphics.getWidth()));
		TextRenderer.renderString("CLIENT ID: " + server.clientID, 0, TextRenderer.getNextLineY(), TextRenderer.calculateFittingScale("CLIENT ID: " + server.clientID, Gdx.graphics.getWidth()));
		TextRenderer.renderString("CAN JOIN: " + Boolean.toString(server.ableToJoin).toUpperCase(), 0, TextRenderer.getNextLineY(), TextRenderer.calculateFittingScale("CAN JOIN: " + Boolean.toString(server.ableToJoin).toUpperCase(), Gdx.graphics.getWidth()));
	}

	public void dispose(){
		backButton.dispose();
	}
}
