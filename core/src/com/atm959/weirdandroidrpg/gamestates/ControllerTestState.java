package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ControllerTestState extends GameState {
	private ArrayList<Integer> presentButtons;
	private Button backButton;

	public ControllerTestState(){
		presentButtons = new ArrayList<>();

		backButton = new Button("ui/menuButton.png");
		backButton.xPos = Gdx.graphics.getWidth() - (2 * Level.tileSize);
		backButton.yPos = Gdx.graphics.getHeight() - (2 * Level.tileSize);
		backButton.width = 2 * Level.tileSize;
		backButton.height = 2 * Level.tileSize;
	}

	public void run(){
		Controller c = Controllers.getCurrent();
		if(c != null) {
			TextRenderer.renderStringFitting(c.getName().toUpperCase(), 0, 0, Gdx.graphics.getWidth());
			for(int i = c.getMinButtonIndex(); i < c.getMaxButtonIndex(); i++){
				if(c.getButton(i)){
					boolean isAlreadyInArray = false;
					for(int j = 0; j < presentButtons.size(); j++){
						if(presentButtons.get(j) == i) isAlreadyInArray = true;
					}
					if(!isAlreadyInArray){
						presentButtons.add(i);
						Collections.sort(presentButtons);
					}
				}
			}
			TextRenderer.renderString("BUTTONS: ", 0, (0.5f * Level.tileSize), TextRenderer.TEXTSCALE_SMALL);
			TextRenderer.renderString("AXES: ", Gdx.graphics.getWidth() / 2.0f, (0.5f * Level.tileSize), TextRenderer.TEXTSCALE_SMALL);
			for(int i = 0; i < presentButtons.size(); i++){
				TextRenderer.renderString(presentButtons.get(i) + ": " + (c.getButton(presentButtons.get(i)) ? "1" : "0"), 0, ((0.5f * Level.tileSize) + TextRenderer.TEXTSCALE_SMALL) + (TextRenderer.TEXTSCALE_SMALL * i), TextRenderer.TEXTSCALE_SMALL);
			}
			for(int i = 0; i < c.getAxisCount(); i++){
				TextRenderer.renderString(i + ": " + c.getAxis(i), Gdx.graphics.getWidth() / 2.0f, ((0.5f * Level.tileSize) + TextRenderer.TEXTSCALE_SMALL) + (TextRenderer.TEXTSCALE_SMALL * i), TextRenderer.TEXTSCALE_SMALL);
			}
		} else {
			presentButtons.clear();
		}

		backButton.update();
		backButton.render();
		if(backButton.isPressed){
			StateManager.popState();
		}
	}

	public void dispose(){
		backButton.dispose();
	}
}
