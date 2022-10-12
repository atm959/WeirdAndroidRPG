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
	private static final String SUB_GAME_TEST_TEXT = "3D SUB-GAME TEST";
	private static final String SHARING_IMAGE_TEST_TEXT = "SHARING IMAGE TEST";
	private static final String NET_TEST_TEXT = "NET TEST";
	private static final String CONTROLLER_TEST_TEXT = "CONTROLLER TEST";
	private static final String VERSION_TEXT = "ALPHA 1, 1ST VERSIONED BUILD";

    private Button startButton;
    private Button optionsButton;
    private Button subgameTestButton;
	private Button sharingImageButton;
	private Button netTestButton;
	private Button controllerTestButton;

    private Texture bgTex;
    private SpriteBatch bgSB;
    float bgOffsetX = 0.0f, bgOffsetY = 0.0f;

    public TitleState(){
        BGM.playSong(BGM.SONG_TITLE);

		startButton = new Button();
		startButton.xPos = (int) (0.5f * Level.tileSize);
		startButton.yPos = Gdx.graphics.getHeight() - (3 * Level.tileSize);
		startButton.width = (int) (7.0f * Level.tileSize);
		startButton.height = (int) (1.5f * Level.tileSize);
		startButton.label = PLAY_TEXT;

		optionsButton = new Button();
		optionsButton.xPos = (int) (0.5f * Level.tileSize);
		optionsButton.width = (int) (7.0f * Level.tileSize);
		optionsButton.height = (int) (1.5f * Level.tileSize);
		optionsButton.yPos = startButton.yPos - optionsButton.height;
		optionsButton.label = OPTIONS_TEXT;

		subgameTestButton = new Button();
		subgameTestButton.xPos = (int) (0.5f * Level.tileSize);
		subgameTestButton.width = (int) (7.0f * Level.tileSize);
		subgameTestButton.height = (int) (1.5f * Level.tileSize);
		subgameTestButton.yPos = optionsButton.yPos - subgameTestButton.height;
		subgameTestButton.label = SUB_GAME_TEST_TEXT;

		sharingImageButton = new Button();
		sharingImageButton.xPos = (int) (0.5f * Level.tileSize);
		sharingImageButton.width = (int) (7.0f * Level.tileSize);
		sharingImageButton.height = (int) (1.5f * Level.tileSize);
		sharingImageButton.yPos = subgameTestButton.yPos - sharingImageButton.height;
		sharingImageButton.label = SHARING_IMAGE_TEST_TEXT;

		netTestButton = new Button();
		netTestButton.xPos = (int) (0.5f * Level.tileSize);
		netTestButton.width = (int) (7.0f * Level.tileSize);
		netTestButton.height = (int) (1.5f * Level.tileSize);
		netTestButton.yPos = sharingImageButton.yPos - netTestButton.height;
		netTestButton.label = NET_TEST_TEXT;

		controllerTestButton = new Button();
		controllerTestButton.xPos = (int) (0.5f * Level.tileSize);
		controllerTestButton.width = (int) (7.0f * Level.tileSize);
		controllerTestButton.height = (int) (1.5f * Level.tileSize);
		controllerTestButton.yPos = netTestButton.yPos - controllerTestButton.height;
		controllerTestButton.label = CONTROLLER_TEST_TEXT;

        bgTex = new Texture("title/bg.png");
        bgSB = new SpriteBatch();
    }

    @Override
    public void run(){
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
		subgameTestButton.update();
		if (subgameTestButton.isPressed) {
			StateManager.pushState(new ThreeDimensionalSubgameTestState());
		}
		sharingImageButton.update();
		if(sharingImageButton.isPressed){
			StateManager.pushState(new SharingImageTestState());
		}
		netTestButton.update();
		if(netTestButton.isPressed){
			StateManager.pushState(new ServerConnectionTestState());
		}
		controllerTestButton.update();
		if(controllerTestButton.isPressed){
			StateManager.pushState(new ControllerTestState());
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
		subgameTestButton.render();
		sharingImageButton.render();
		netTestButton.render();
		controllerTestButton.render();

		//Render the text here
		TextRenderer.renderStringFitting(WEIRD_TEXT, 0, 0, Gdx.graphics.getWidth());
		TextRenderer.renderStringFitting(ANDROID_TEXT, 0, TextRenderer.getNextLineY(), Gdx.graphics.getWidth());
		TextRenderer.renderStringFitting(RPG_TEXT, 0, TextRenderer.getNextLineY(), Gdx.graphics.getWidth());
		TextRenderer.renderStringFitting(CREATOR_TEXT, 0, TextRenderer.getNextLineY(), Gdx.graphics.getWidth());

		float verTxtScl = TextRenderer.calculateFittingScale(VERSION_TEXT, Gdx.graphics.getWidth(), false);
		float verTxtXPos = TextRenderer.calculateCenteredXPosition(VERSION_TEXT, verTxtScl, 0, Gdx.graphics.getWidth());
		TextRenderer.renderString(VERSION_TEXT, verTxtXPos, Gdx.graphics.getHeight() - verTxtScl, verTxtScl);
	}

    @Override
    public void dispose(){
		startButton.dispose();
		optionsButton.dispose();
		subgameTestButton.dispose();
		sharingImageButton.dispose();
		netTestButton.dispose();
		controllerTestButton.dispose();
		bgTex.dispose();
		bgSB.dispose();
    }
}
