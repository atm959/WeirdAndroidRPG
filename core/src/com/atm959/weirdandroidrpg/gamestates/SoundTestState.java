package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.audio.BGM;
import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.badlogic.gdx.Gdx;

public class SoundTestState extends GameState {
	private Button decBGMButton;
	private Button incBGMButton;
	private Button playButton;
	private Button stopButton;
	private int bgmID;
	private Button backButton;

	public SoundTestState(){
		decBGMButton = new Button();
		decBGMButton.xPos = (int)(0.5f * Level.tileSize);
		decBGMButton.yPos = (int)(2.0f * Level.tileSize);
		decBGMButton.width = (int)(Level.tileSize * 2.0f);
		decBGMButton.height = (int)(Level.tileSize * 2.0f);
		decBGMButton.label = "-";

		incBGMButton = new Button();
		incBGMButton.xPos = (int)(0.5f * Level.tileSize) + decBGMButton.width;
		incBGMButton.yPos = (int)(2.0f * Level.tileSize);
		incBGMButton.width = (int)(Level.tileSize * 2.0f);
		incBGMButton.height = (int)(Level.tileSize * 2.0f);
		incBGMButton.label = "+";

		playButton = new Button();
		playButton.xPos = (int)(0.5f * Level.tileSize);
		playButton.yPos = (int)(2.0f * Level.tileSize) + decBGMButton.height;
		playButton.width = (int)(Level.tileSize * 4.0f);
		playButton.height = (int)(Level.tileSize * 2.0f);
		playButton.label = "PLAY";

		stopButton = new Button();
		stopButton.xPos = (int)(0.5f * Level.tileSize);
		stopButton.yPos = (int)(2.0f * Level.tileSize) + decBGMButton.height + playButton.height;
		stopButton.width = (int)(Level.tileSize * 4.0f);
		stopButton.height = (int)(Level.tileSize * 2.0f);
		stopButton.label = "STOP";

		backButton = new Button("ui/menuButton.png");
		backButton.xPos = Gdx.graphics.getWidth() - (2 * Level.tileSize);
		backButton.yPos = Gdx.graphics.getHeight() - (2 * Level.tileSize);
		backButton.width = 2 * Level.tileSize;
		backButton.height = 2 * Level.tileSize;

		bgmID = 0;
	}

	public void run(){
		decBGMButton.update();
		incBGMButton.update();
		playButton.update();
		stopButton.update();
		decBGMButton.render();
		incBGMButton.render();
		playButton.render();
		stopButton.render();
		if(decBGMButton.isPressed){
			bgmID--;
			if(bgmID < 0) bgmID = 0;
		}
		if(incBGMButton.isPressed){
			bgmID++;
			if(bgmID > BGM.MAX_ID){
				bgmID = BGM.MAX_ID;
			}
		}
		if(playButton.isPressed){
			BGM.playSongRegardlessOfSetting(bgmID);
		}
		if(stopButton.isPressed){
			BGM.stopBGM();
		}

		TextRenderer.renderStringFitting("SOUND TEST", 0, 0, Gdx.graphics.getWidth());
		TextRenderer.renderStringFitting("BGM", 0, TextRenderer.getNextLineY(), Gdx.graphics.getWidth());
		float tY = TextRenderer.getNextLineY() * 5.0f;
		TextRenderer.renderStringFitting("ID: " + bgmID, 0, tY, Gdx.graphics.getWidth());

		backButton.update();
		backButton.render();
		if(backButton.isPressed){
			StateManager.popState();
		}
	}

	public void dispose(){
		decBGMButton.dispose();
		incBGMButton.dispose();
		playButton.dispose();
		stopButton.dispose();
		backButton.dispose();
	}
}
