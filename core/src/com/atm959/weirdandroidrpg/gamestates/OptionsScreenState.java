package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.audio.BGM;
import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.input.CheckBox;
import com.atm959.weirdandroidrpg.input.Slider;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.savedata.Options;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.atm959.weirdandroidrpg.time.Time;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/24/2022.
 */
public class OptionsScreenState extends GameState {
    private static final String OPTIONS_STRING = "OPTIONS";
    private static final String RIGHT_HANDED_D_PAD_STRING = "RIGHT-HANDED D-PAD";
    private static final String DPAD_OPACITY_STRING = "D-PAD OPACITY";
    private static final String ABORT_STRING = "ABORT";
    private static final String SHOW_DEBUG_INFO_STRING = "SHOW DEBUG INFO";
	private static final String PLAY_MUSIC_STRING = "PLAY MUSIC";

    private CheckBox rightHandedCheckbox;
    private Slider dpadOpacitySlider;
    private Button saveButton;
    private Button abortButton;
    private CheckBox showFPSAndDeltaCheckbox;
	private CheckBox playMusicCheckbox;
	private Button soundTestButton;

    private boolean rightHanded;
    private float dpadOpacity;
    private boolean showDebugInfo;
	private boolean playMusic;

    private boolean startGameOnExit;

    private Texture bgTex;
    private SpriteBatch bgSB;
    float bgOffsetX = 0.0f, bgOffsetY = 0.0f;

    public OptionsScreenState(boolean startGameOnExit){
        this.startGameOnExit = startGameOnExit;
        rightHandedCheckbox = new CheckBox();
        rightHandedCheckbox.xPos = (int)(0.5f * Level.tileSize);
        rightHandedCheckbox.yPos = (int)(1.5f * Level.tileSize);
		rightHandedCheckbox.boundingBoxWidth = (int) (7.0f * Level.tileSize);
        rightHandedCheckbox.boundingBoxHeight = (int)(1.5f * Level.tileSize);
        rightHandedCheckbox.isChecked = Options.rightHandedDPad;
		rightHandedCheckbox.label = RIGHT_HANDED_D_PAD_STRING;

        dpadOpacitySlider = new Slider();
        dpadOpacitySlider.xPos = (int)(0.5f * Level.tileSize);
        dpadOpacitySlider.yPos = (int)(4.0f * Level.tileSize);
        dpadOpacitySlider.width = (int)(7.0f * Level.tileSize);
        dpadOpacitySlider.height = (int)(1.5f * Level.tileSize);
        dpadOpacitySlider.value = Options.dpadOpacity;

        saveButton = new Button();
        saveButton.xPos = (int)(0.5f * Level.tileSize);
        saveButton.yPos = Gdx.graphics.getHeight() - (3 * Level.tileSize);
        saveButton.width = (int)(7.0f * Level.tileSize);
        saveButton.height = (int)(1.5f * Level.tileSize);

        abortButton = new Button();
        abortButton.xPos = (int) (0.5f * Level.tileSize);
        abortButton.width = (int) (7.0f * Level.tileSize);
        abortButton.height = (int) (1.5f * Level.tileSize);
        abortButton.yPos = saveButton.yPos - abortButton.height;
		abortButton.label = ABORT_STRING;

		soundTestButton = new Button();
		soundTestButton.xPos = abortButton.xPos;
		soundTestButton.yPos = abortButton.yPos - abortButton.height;
		soundTestButton.width = abortButton.width;
		soundTestButton.height = abortButton.height;
		soundTestButton.label = "SOUND TEST";

        showFPSAndDeltaCheckbox = new CheckBox();
        showFPSAndDeltaCheckbox.xPos = (int)(0.5f * Level.tileSize);
        showFPSAndDeltaCheckbox.yPos = (int)(6.0f * Level.tileSize);
		showFPSAndDeltaCheckbox.boundingBoxWidth = (int) (7.0f * Level.tileSize);
        showFPSAndDeltaCheckbox.boundingBoxHeight = (int)(1.5f * Level.tileSize);
        showFPSAndDeltaCheckbox.isChecked = Options.showDebugInfo;
		showFPSAndDeltaCheckbox.label = SHOW_DEBUG_INFO_STRING;

		playMusicCheckbox = new CheckBox();
		playMusicCheckbox.xPos = (int)(0.5f * Level.tileSize);
		playMusicCheckbox.yPos = showFPSAndDeltaCheckbox.yPos + (int)(1.5f * Level.tileSize);
		playMusicCheckbox.boundingBoxWidth = (int) (7.0f * Level.tileSize);
		playMusicCheckbox.boundingBoxHeight = (int)(1.5f * Level.tileSize);
		playMusicCheckbox.isChecked = Options.playMusic;
		playMusicCheckbox.label = PLAY_MUSIC_STRING;

        rightHanded = Options.rightHandedDPad;
        dpadOpacity = Options.dpadOpacity;
        showDebugInfo = Options.showDebugInfo;
		playMusic = Options.playMusic;

        bgTex = new Texture("title/optionsBg.png");
        bgSB = new SpriteBatch();
    }

    @Override
    public void run(){
        bgOffsetX += 0.4f * Time.deltaTime;
        bgOffsetY += 0.3f * Time.deltaTime;
        int size = Gdx.graphics.getWidth() / 3;
        int numY = Gdx.graphics.getHeight() / size;
        bgSB.begin();
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < (numY + 2); y++){
                bgSB.draw(bgTex, (x * size) - ((int)bgOffsetX % size), Util.convertY((y * size) - ((int)bgOffsetY % size), size), size, size);
            }
        }
        bgSB.end();

        TextRenderer.renderString(OPTIONS_STRING, (int)(0.5f * Level.tileSize), 0, Level.tileSize);
        TextRenderer.renderString(DPAD_OPACITY_STRING, dpadOpacitySlider.xPos + (int)(0.5f * TextRenderer.TEXTSCALE_MEDIUM), dpadOpacitySlider.yPos - TextRenderer.TEXTSCALE_MEDIUM, TextRenderer.TEXTSCALE_MEDIUM);

        rightHandedCheckbox.update();
        rightHanded = rightHandedCheckbox.isChecked;
        dpadOpacitySlider.update();
        dpadOpacity = dpadOpacitySlider.value;
        saveButton.update();
        if(saveButton.isPressed){
            Options.rightHandedDPad = rightHanded;
            Options.dpadOpacity = dpadOpacity;
            Options.showDebugInfo = showDebugInfo;
            Options.optionsHaveBeenSet = true;
			Options.playMusic = playMusic;

			if(playMusic){
				BGM.onEnablePlayMusic();
			} else {
				BGM.onDisablePlayMusic();
			}

            Options.save();
            if(startGameOnExit) {
                StateManager.popState();
                StateManager.pushState(new InGameState());
            } else {
                StateManager.popState();
            }
        }
        abortButton.update();
        if (abortButton.isPressed) {
            StateManager.popState();
        }
		soundTestButton.update();
		if(soundTestButton.isPressed){
			StateManager.pushState(new SoundTestState());
		}
        showFPSAndDeltaCheckbox.update();
		showDebugInfo = showFPSAndDeltaCheckbox.isChecked;
		playMusicCheckbox.update();
		playMusic = playMusicCheckbox.isChecked;

        rightHandedCheckbox.render();
        dpadOpacitySlider.render();
        showFPSAndDeltaCheckbox.render();
		playMusicCheckbox.render();

        String dpadOpacityPercentageString = (int)(dpadOpacitySlider.value * 100.0f) + "%";
        TextRenderer.renderString(dpadOpacityPercentageString, dpadOpacitySlider.xPos + (dpadOpacitySlider.width / 2) - ((dpadOpacityPercentageString.length() * TextRenderer.TEXTSCALE_LARGE) / 2), (int)(dpadOpacitySlider.yPos + 0.35f * Level.tileSize), TextRenderer.TEXTSCALE_LARGE);
        String saveString = "SAVE";
        if(startGameOnExit) saveString += " & PLAY";
        saveButton.label = saveString;
		saveButton.render();
		abortButton.render();
		soundTestButton.render();
    }

    @Override
    public void dispose(){
        rightHandedCheckbox.dispose();
        dpadOpacitySlider.dispose();
        saveButton.dispose();
        abortButton.dispose();
        showFPSAndDeltaCheckbox.dispose();
        bgTex.dispose();
        bgSB.dispose();
		soundTestButton.dispose();
    }
}
