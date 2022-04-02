package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.global.Global;
import com.atm959.weirdandroidrpg.global.Time;
import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.input.CheckBox;
import com.atm959.weirdandroidrpg.input.Slider;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.savedata.Options;
import com.atm959.weirdandroidrpg.text.TextRenderer;
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
    private boolean showFPSAndDelta;

    private boolean startGameOnExit;

    private Texture bgTex;
    private SpriteBatch bgSB;
    float bgOffsetX = 0.0f, bgOffsetY = 0.0f;

    public OptionsScreenState(boolean startGameOnExit){
        this.startGameOnExit = startGameOnExit;
        rightHandedCheckbox = new CheckBox();
        rightHandedCheckbox.xPos = (int)(0.5f * Level.TILE_SIZE);
        rightHandedCheckbox.yPos = (int)(1.5f * Level.TILE_SIZE);
        rightHandedCheckbox.size = (int)(1.5f * Level.TILE_SIZE);
        rightHandedCheckbox.isChecked = Options.rightHandedDPad;

        dpadOpacitySlider = new Slider();
        dpadOpacitySlider.xPos = (int)(0.5f * Level.TILE_SIZE);
        dpadOpacitySlider.yPos = (int)(4.0f * Level.TILE_SIZE);
        dpadOpacitySlider.width = (int)(7.0f * Level.TILE_SIZE);
        dpadOpacitySlider.height = (int)(1.5f * Level.TILE_SIZE);
        dpadOpacitySlider.value = Options.dpadOpacity;

        saveButton = new Button();
        saveButton.xPos = (int)(0.5f * Level.TILE_SIZE);
        saveButton.yPos = Gdx.graphics.getHeight() - (3 * Level.TILE_SIZE);
        saveButton.width = (int)(7.0f * Level.TILE_SIZE);
        saveButton.height = (int)(1.5f * Level.TILE_SIZE);

        abortButton = new Button();
        abortButton.xPos = (int) (0.5f * Level.TILE_SIZE);
        abortButton.width = (int) (7.0f * Level.TILE_SIZE);
        abortButton.height = (int) (1.5f * Level.TILE_SIZE);
        abortButton.yPos = saveButton.yPos - abortButton.height;

        showFPSAndDeltaCheckbox = new CheckBox();
        showFPSAndDeltaCheckbox.xPos = (int)(0.5f * Level.TILE_SIZE);
        showFPSAndDeltaCheckbox.yPos = (int)(6.0f * Level.TILE_SIZE);
        showFPSAndDeltaCheckbox.size = (int)(1.5f * Level.TILE_SIZE);
        showFPSAndDeltaCheckbox.isChecked = Options.showFPSAndDelta;

        rightHanded = Options.rightHandedDPad;
        dpadOpacity = Options.dpadOpacity;
        showFPSAndDelta = Options.showFPSAndDelta;

        bgTex = new Texture("title/optionsBg.png");
        bgSB = new SpriteBatch();
    }

    @Override
    public void run(){
        bgOffsetX += 0.4f * Time.deltaTime;
        bgOffsetY += 0.3f * Time.deltaTime;
        int width = Gdx.graphics.getWidth() / 3;
        int numY = Gdx.graphics.getHeight() / width;
        bgSB.begin();
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < (numY + 2); y++){
                //noinspection SuspiciousNameCombination
                bgSB.draw(bgTex, (x * width) - ((int)bgOffsetX % width), Util.convertY((y * width) - ((int)bgOffsetY % width), width), width, width);
            }
        }
        bgSB.end();

        Global.textRenderer.renderString(OPTIONS_STRING, (int)(0.5f * Level.TILE_SIZE), 0, Level.TILE_SIZE);
        Global.textRenderer.renderString(LEFT_HANDED_STRING, rightHandedCheckbox.xPos + rightHandedCheckbox.size + (int)(0.5f * Level.TILE_SIZE), rightHandedCheckbox.yPos + (int)(0.25 * Level.TILE_SIZE), TextRenderer.TEXTSCALE_SMALL);
        Global.textRenderer.renderString(DPAD_STRING, rightHandedCheckbox.xPos + rightHandedCheckbox.size + (int)(0.5f * Level.TILE_SIZE) + (3 * TextRenderer.TEXTSCALE_SMALL), rightHandedCheckbox.yPos + (int)(0.75 * Level.TILE_SIZE), TextRenderer.TEXTSCALE_SMALL);
        Global.textRenderer.renderString(DPAD_OPACITY_STRING, dpadOpacitySlider.xPos + (int)(0.5f * TextRenderer.TEXTSCALE_MEDIUM), dpadOpacitySlider.yPos - TextRenderer.TEXTSCALE_MEDIUM, TextRenderer.TEXTSCALE_MEDIUM);
        Global.textRenderer.renderString(SHOW_FPS_STRING, (int)(showFPSAndDeltaCheckbox.xPos + showFPSAndDeltaCheckbox.size + (int)(0.5f * Level.TILE_SIZE) + (1.5f * TextRenderer.TEXTSCALE_SMALL)), showFPSAndDeltaCheckbox.yPos + (int)(0.25 * Level.TILE_SIZE), TextRenderer.TEXTSCALE_SMALL);
        Global.textRenderer.renderString(AND_DELTA_STRING, (int)(showFPSAndDeltaCheckbox.xPos + showFPSAndDeltaCheckbox.size + (int)(0.5f * Level.TILE_SIZE) + TextRenderer.TEXTSCALE_SMALL), showFPSAndDeltaCheckbox.yPos + (int)(0.75 * Level.TILE_SIZE), TextRenderer.TEXTSCALE_SMALL);

        rightHandedCheckbox.update();
        rightHanded = rightHandedCheckbox.isChecked;
        dpadOpacitySlider.update();
        dpadOpacity = dpadOpacitySlider.value;
        saveButton.update();
        if(saveButton.isPressed){
            Options.rightHandedDPad = rightHanded;
            Options.dpadOpacity = dpadOpacity;
            Options.showFPSAndDelta = showFPSAndDelta;
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
        showFPSAndDelta = showFPSAndDeltaCheckbox.isChecked;

        rightHandedCheckbox.render();
        dpadOpacitySlider.render();
        saveButton.render();
        abortButton.render();
        showFPSAndDeltaCheckbox.render();

        String dpadOpacityPercentageString = (int)(dpadOpacitySlider.value * 100.0f) + "%";
        Global.textRenderer.renderString(dpadOpacityPercentageString, dpadOpacitySlider.xPos + (dpadOpacitySlider.width / 2) - ((dpadOpacityPercentageString.length() * TextRenderer.TEXTSCALE_LARGE) / 2), (int)(dpadOpacitySlider.yPos + 0.35f * Level.TILE_SIZE), TextRenderer.TEXTSCALE_LARGE);
        String saveString = "SAVE";
        if(startGameOnExit) saveString += " & PLAY";
        int saveScale = TextRenderer.TEXTSCALE_LARGE;
        if(startGameOnExit) saveScale = TextRenderer.TEXTSCALE_MEDIUM;
        Global.textRenderer.renderString(saveString, saveButton.xPos + (saveButton.width / 2) - ((saveString.length() * saveScale) / 2), (int)(saveButton.yPos + 0.35f * Level.TILE_SIZE), saveScale);
        Global.textRenderer.renderString("ABORT", abortButton.xPos + (abortButton.width / 2) - ((ABORT_STRING.length() * TextRenderer.TEXTSCALE_LARGE) / 2), (int)(abortButton.yPos + 0.35f * Level.TILE_SIZE), TextRenderer.TEXTSCALE_LARGE);
    }

    @Override
    public void dispose(){
        rightHandedCheckbox.dispose();
        dpadOpacitySlider.dispose();
        saveButton.dispose();
        if(!startGameOnExit) abortButton.dispose();
        showFPSAndDeltaCheckbox.dispose();
        bgTex.dispose();
        bgSB.dispose();
    }
}
