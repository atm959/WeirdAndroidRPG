package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.net.Server;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.badlogic.gdx.Gdx;

public class ServerConnectionTestState extends GameState {
	private final Button backButton;
	private final Server server;

	public ServerConnectionTestState() {
		backButton = new Button("ui/menuButton.png");
		backButton.xPos = 0;
		backButton.yPos = 0;
		backButton.width = Level.tileSize * 2;
		backButton.height = Level.tileSize * 2;

		server = new Server("3.18.105.5", 8087);
		server.connect();
	}

	public void run() {
		if(server.isConnected) {
			backButton.update();
			if (backButton.isPressed) {
				server.disconnect();
				StateManager.popState();
			}

			backButton.render();

			TextRenderer.renderString("CLIENT ID: " + server.clientID, 0, backButton.height, TextRenderer.calculateFittingScale("CLIENT ID: " + server.clientID, Gdx.graphics.getWidth(), false));
			TextRenderer.renderString("CAN JOIN: " + Boolean.toString(server.ableToJoin).toUpperCase(), 0, TextRenderer.getNextLineY(), TextRenderer.calculateFittingScale("CAN JOIN: " + Boolean.toString(server.ableToJoin).toUpperCase(), Gdx.graphics.getWidth(), false));
		} else {
			if (server.isConnecting) {
				TextRenderer.renderString("CONNECTING...", 0, 0, TextRenderer.calculateFittingScale("CONNECTING...", Gdx.graphics.getWidth(), false));
			} else {
				TextRenderer.renderString("COULDN'T CONNECT", 0, 0, TextRenderer.calculateFittingScale("COULDN'T CONNECT", Gdx.graphics.getWidth(), false));
			}
		}
	}

	public void dispose() {
		backButton.dispose();
	}
}