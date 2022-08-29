package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.audio.BGM;
import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.savedata.Options;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.atm959.weirdandroidrpg.time.Time;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/23/2022.
 */
public class TitleState extends GameState {
	private static final String WEIRD_TEXT = "WEIRD";
	private static final String ANDROID_TEXT = "ANDROID";
	private static final String RPG_TEXT = "RPG";
	private static final String CREATOR_TEXT = "BY ATM959";
	private static final String PLAY_TEXT = "PLAY";
	private static final String OPTIONS_TEXT = "OPTIONS";
	private static final String CREDITS_TEXT = "CREDITS";
	private static final String VERSION_TEXT = "ALPHA 1, 1ST VERSIONED BUILD";
	float bgOffsetX = 0.0f, bgOffsetY = 0.0f;
	private final Button startButton;
	private final Button optionsButton;
	private final Button creditsButton;
	private final Texture bgTex;
	private final SpriteBatch bgSB;

	public TitleState() {
		BGM.playSong(BGM.SONG_TITLE);

		startButton = new Button();
		startButton.xPos = (int) (0.5f * Level.tileSize);
		startButton.yPos = Gdx.graphics.getHeight() - (3 * Level.tileSize);
		startButton.width = (int) (7.0f * Level.tileSize);
		startButton.height = (int) (1.5f * Level.tileSize);
		startButton.label = "PLAY";

		optionsButton = new Button();
		optionsButton.xPos = (int) (0.5f * Level.tileSize);
		optionsButton.width = (int) (7.0f * Level.tileSize);
		optionsButton.height = (int) (1.5f * Level.tileSize);
		optionsButton.yPos = startButton.yPos - optionsButton.height;
		optionsButton.label = "OPTIONS";

		creditsButton = new Button();
		creditsButton.xPos = (int) (0.5f * Level.tileSize);
		creditsButton.width = (int) (7.0f * Level.tileSize);
		creditsButton.height = (int) (1.5f * Level.tileSize);
		creditsButton.yPos = optionsButton.yPos - creditsButton.height;
		creditsButton.label = "NET TEST";

		bgTex = new Texture("title/bg.png");
		bgSB = new SpriteBatch();
	}

	@Override
	public void run() {
		startButton.update();
		if (startButton.isPressed) {
			if (!Options.optionsHaveBeenSet) {
				StateManager.pushState(new OptionsScreenState(true));
			} else {
				StateManager.replaceCurrentState(new InGameState());
			}
		}
		optionsButton.update();
		if (optionsButton.isPressed) {
			StateManager.pushState(new OptionsScreenState(false));
		}
		creditsButton.update();
		if (creditsButton.isPressed) {
			StateManager.pushState(new ServerConnectionTestState());
		}

		bgOffsetX += 0.4f * Time.deltaTime;
		bgOffsetY += 0.3f * Time.deltaTime;
		int size = Gdx.graphics.getWidth() / 3;
		int numY = Gdx.graphics.getHeight() / size;
		bgSB.begin();
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < (numY + 2); y++) {
				bgSB.draw(bgTex, (x * size) - ((int) bgOffsetX % size), Util.convertY((y * size) - ((int) bgOffsetY % size), size), size, size);
			}
		}
		bgSB.end();

		startButton.render();
		optionsButton.render();
		creditsButton.render();

		//Text here
	}

	@Override
	public void dispose() {
		startButton.dispose();
		optionsButton.dispose();
		creditsButton.dispose();
		bgTex.dispose();
		bgSB.dispose();
	}
}
