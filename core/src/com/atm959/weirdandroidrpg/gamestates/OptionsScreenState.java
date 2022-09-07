package com.atm959.weirdandroidrpg.gamestates;

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
    private static final String LEFT_HANDED_STRING = "RIGHT-HANDED";
    private static final String DPAD_STRING = "D-PAD";
    private static final String DPAD_OPACITY_STRING = "D-PAD OPACITY";
    private static final String ABORT_STRING = "ABORT";
    private static final String SHOW_FPS_STRING = "SHOW FPS";
    private static final String AND_DELTA_STRING = "AND DELTA";

    private CheckBox rightHandedCheckbox;
    private Slider dpadOpacitySlider;
    private Button saveButton;
    private Button abortButton;
    private CheckBox showFPSAndDeltaCheckbox;

    private boolean rightHanded;
    private float dpadOpacity;
    private boolean showDebugInfo;

    private boolean startGameOnExit;

    private Texture bgTex;
    private SpriteBatch bgSB;
    float bgOffsetX = 0.0f, bgOffsetY = 0.0f;

    public OptionsScreenState(boolean startGameOnExit){
        this.startGameOnExit = startGameOnExit;
        rightHandedCheckbox = new CheckBox();
        rightHandedCheckbox.xPos = (int)(0.5f * Level.tileSize);
        rightHandedCheckbox.yPos = (int)(1.5f * Level.tileSize);
        rightHandedCheckbox.size = (int)(1.5f * Level.tileSize);
        rightHandedCheckbox.isChecked = Options.rightHandedDPad;

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
		saveButton.label = "SAVE";

        abortButton = new Button();
        abortButton.xPos = (int) (0.5f * Level.tileSize);
        abortButton.width = (int) (7.0f * Level.tileSize);
        abortButton.height = (int) (1.5f * Level.tileSize);
        abortButton.yPos = saveButton.yPos - abortButton.height;
		abortButton.label = "ABORT";

        showFPSAndDeltaCheckbox = new CheckBox();
        showFPSAndDeltaCheckbox.xPos = (int)(0.5f * Level.tileSize);
        showFPSAndDeltaCheckbox.yPos = (int)(6.0f * Level.tileSize);
        showFPSAndDeltaCheckbox.size = (int)(1.5f * Level.tileSize);
        showFPSAndDeltaCheckbox.isChecked = Options.showDebugInfo;

        rightHanded = Options.rightHandedDPad;
        dpadOpacity = Options.dpadOpacity;
        showDebugInfo = Options.showDebugInfo;

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
        TextRenderer.renderString(LEFT_HANDED_STRING, rightHandedCheckbox.xPos + rightHandedCheckbox.size + (int)(0.5f * Level.tileSize), rightHandedCheckbox.yPos + (int)(0.25 * Level.tileSize), TextRenderer.TEXTSCALE_SMALL);
        TextRenderer.renderString(DPAD_STRING, rightHandedCheckbox.xPos + rightHandedCheckbox.size + (int)(0.5f * Level.tileSize) + (3 * TextRenderer.TEXTSCALE_SMALL), rightHandedCheckbox.yPos + (int)(0.75 * Level.tileSize), TextRenderer.TEXTSCALE_SMALL);
        TextRenderer.renderString(DPAD_OPACITY_STRING, dpadOpacitySlider.xPos + (int)(0.5f * TextRenderer.TEXTSCALE_MEDIUM), dpadOpacitySlider.yPos - TextRenderer.TEXTSCALE_MEDIUM, TextRenderer.TEXTSCALE_MEDIUM);
        TextRenderer.renderString(SHOW_FPS_STRING, (int)(showFPSAndDeltaCheckbox.xPos + showFPSAndDeltaCheckbox.size + (int)(0.5f * Level.tileSize) + (1.5f * TextRenderer.TEXTSCALE_SMALL)), showFPSAndDeltaCheckbox.yPos + (int)(0.25 * Level.tileSize), TextRenderer.TEXTSCALE_SMALL);
        TextRenderer.renderString(AND_DELTA_STRING, (int)(showFPSAndDeltaCheckbox.xPos + showFPSAndDeltaCheckbox.size + (int)(0.5f * Level.tileSize) + TextRenderer.TEXTSCALE_SMALL), showFPSAndDeltaCheckbox.yPos + (int)(0.75 * Level.tileSize), TextRenderer.TEXTSCALE_SMALL);

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
        showFPSAndDeltaCheckbox.update();
		showDebugInfo = showFPSAndDeltaCheckbox.isChecked;

        rightHandedCheckbox.render();
        dpadOpacitySlider.render();
        showFPSAndDeltaCheckbox.render();

        String dpadOpacityPercentageString = (int)(dpadOpacitySlider.value * 100.0f) + "%";
        TextRenderer.renderString(dpadOpacityPercentageString, dpadOpacitySlider.xPos + (dpadOpacitySlider.width / 2) - ((dpadOpacityPercentageString.length() * TextRenderer.TEXTSCALE_LARGE) / 2), (int)(dpadOpacitySlider.yPos + 0.35f * Level.tileSize), TextRenderer.TEXTSCALE_LARGE);
        String saveString = "SAVE";
        if(startGameOnExit) saveString += " & PLAY";
        saveButton.label = saveString;
		saveButton.render();
		abortButton.render();
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
    }
}
