package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.global.Global;
import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.input.CheckBox;
import com.atm959.weirdandroidrpg.input.Slider;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.savedata.Options;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.badlogic.gdx.Gdx;

import jdk.nashorn.internal.codegen.OptimisticTypesPersistence;

/**
 * Created by atm959 on 3/24/2022.
 */
public class OptionsScreenState extends GameState {
    private static final String OPTIONS_STRING = "OPTIONS";
    private static final String LEFT_HANDED_STRING = "RIGHT-HANDED";
    private static final String DPAD_STRING = "D-PAD";
    private static final String DPAD_OPACITY_STRING = "D-PAD OPACITY";
    private static final String SAVE_STRING = "SAVE";
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

        if(!startGameOnExit) {
            abortButton = new Button();
            abortButton.xPos = (int) (0.5f * Level.TILE_SIZE);
            abortButton.width = (int) (7.0f * Level.TILE_SIZE);
            abortButton.height = (int) (1.5f * Level.TILE_SIZE);
            abortButton.yPos = saveButton.yPos - abortButton.height;
        }

        showFPSAndDeltaCheckbox = new CheckBox();
        showFPSAndDeltaCheckbox.xPos = (int)(0.5f * Level.TILE_SIZE);
        showFPSAndDeltaCheckbox.yPos = (int)(6.0f * Level.TILE_SIZE);
        showFPSAndDeltaCheckbox.size = (int)(1.5f * Level.TILE_SIZE);
        showFPSAndDeltaCheckbox.isChecked = Options.showFPSAndDelta;

        rightHanded = Options.rightHandedDPad;
        dpadOpacity = Options.dpadOpacity;
        showFPSAndDelta = Options.showFPSAndDelta;
    }

    @Override
    public void Run(){
        Global.textRenderer.RenderString(OPTIONS_STRING, (int)(0.5f * Level.TILE_SIZE), 0, Level.TILE_SIZE);
        Global.textRenderer.RenderString(LEFT_HANDED_STRING, rightHandedCheckbox.xPos + rightHandedCheckbox.size + (int)(0.5f * Level.TILE_SIZE), rightHandedCheckbox.yPos + (int)(0.25 * Level.TILE_SIZE), TextRenderer.TEXTSCALE_SMALL);
        Global.textRenderer.RenderString(DPAD_STRING, rightHandedCheckbox.xPos + rightHandedCheckbox.size + (int)(0.5f * Level.TILE_SIZE) + (3 * TextRenderer.TEXTSCALE_SMALL), rightHandedCheckbox.yPos + (int)(0.75 * Level.TILE_SIZE), TextRenderer.TEXTSCALE_SMALL);
        Global.textRenderer.RenderString(DPAD_OPACITY_STRING, dpadOpacitySlider.xPos + (int)(0.5f * TextRenderer.TEXTSCALE_MEDIUM), dpadOpacitySlider.yPos - TextRenderer.TEXTSCALE_MEDIUM, TextRenderer.TEXTSCALE_MEDIUM);
        Global.textRenderer.RenderString(SHOW_FPS_STRING, showFPSAndDeltaCheckbox.xPos + showFPSAndDeltaCheckbox.size + (int)(0.5f * Level.TILE_SIZE) + (1.5f * TextRenderer.TEXTSCALE_SMALL), showFPSAndDeltaCheckbox.yPos + (int)(0.25 * Level.TILE_SIZE), TextRenderer.TEXTSCALE_SMALL);
        Global.textRenderer.RenderString(AND_DELTA_STRING, showFPSAndDeltaCheckbox.xPos + showFPSAndDeltaCheckbox.size + (int)(0.5f * Level.TILE_SIZE) + (1 * TextRenderer.TEXTSCALE_SMALL), showFPSAndDeltaCheckbox.yPos + (int)(0.75 * Level.TILE_SIZE), TextRenderer.TEXTSCALE_SMALL);

        rightHandedCheckbox.Update();
        rightHanded = rightHandedCheckbox.isChecked;
        dpadOpacitySlider.Update();
        dpadOpacity = dpadOpacitySlider.value;
        saveButton.Update();
        if(saveButton.isPressed){
            Options.rightHandedDPad = rightHanded;
            Options.dpadOpacity = dpadOpacity;
            Options.showFPSAndDelta = showFPSAndDelta;
            Options.Save();
            StateManager.PushState(new InGameState());
        }
        if(!startGameOnExit) {
            abortButton.Update();
            if (abortButton.isPressed) {
                StateManager.PopState();
            }
        }
        showFPSAndDeltaCheckbox.Update();
        showFPSAndDelta = showFPSAndDeltaCheckbox.isChecked;

        rightHandedCheckbox.Render();
        dpadOpacitySlider.Render();
        saveButton.Render();
        if(!startGameOnExit) abortButton.Render();
        showFPSAndDeltaCheckbox.Render();

        String dpadOpacityPercentageString = (int)(dpadOpacitySlider.value * 100.0f) + "%";
        Global.textRenderer.RenderString(dpadOpacityPercentageString, dpadOpacitySlider.xPos + (dpadOpacitySlider.width / 2) - ((dpadOpacityPercentageString.length() * TextRenderer.TEXTSCALE_LARGE) / 2), dpadOpacitySlider.yPos + 0.35f * Level.TILE_SIZE, TextRenderer.TEXTSCALE_LARGE);
        Global.textRenderer.RenderString(SAVE_STRING, saveButton.xPos + (saveButton.width / 2) - ((SAVE_STRING.length() * TextRenderer.TEXTSCALE_LARGE) / 2), saveButton.yPos + 0.35f * Level.TILE_SIZE, TextRenderer.TEXTSCALE_LARGE);
        if(!startGameOnExit) Global.textRenderer.RenderString(ABORT_STRING, abortButton.xPos + (abortButton.width / 2) - ((ABORT_STRING.length() * TextRenderer.TEXTSCALE_LARGE) / 2), abortButton.yPos + 0.35f * Level.TILE_SIZE, TextRenderer.TEXTSCALE_LARGE);
    }

    @Override
    public void Dispose(){
        rightHandedCheckbox.Dispose();
        dpadOpacitySlider.Dispose();
        saveButton.Dispose();
        if(!startGameOnExit) abortButton.Dispose();
        showFPSAndDeltaCheckbox.Dispose();
    }
}
