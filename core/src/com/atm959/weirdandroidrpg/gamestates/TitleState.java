package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.audio.BGM;
import com.atm959.weirdandroidrpg.global.Global;
import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.savedata.Options;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.badlogic.gdx.Gdx;

/**
 * Created by atm959 on 3/23/2022.
 */
public class TitleState extends GameState {
    private static final String TITLESCREEN_TITLE = "WEIRD RPG";
    private static final String TITLESCREEN_AUTHOR = "BY ATM959";
    private static final String TAPTOSTART_TEXT = "TAP TO START";

    private Button startButton;
    private Button optionsButton;
    private Button multiplayerButton;

    public TitleState(){
        BGM.playSong(0);

        startButton = new Button();
        startButton.xPos = (int)(0.5f * Level.TILE_SIZE);
        startButton.yPos = Gdx.graphics.getHeight() - (3 * Level.TILE_SIZE);
        startButton.width = (int)(7.0f * Level.TILE_SIZE);
        startButton.height = (int)(1.5f * Level.TILE_SIZE);

        optionsButton = new Button();
        optionsButton.xPos = (int)(0.5f * Level.TILE_SIZE);
        optionsButton.width = (int)(7.0f * Level.TILE_SIZE);
        optionsButton.height = (int)(1.5f * Level.TILE_SIZE);
        optionsButton.yPos = startButton.yPos - optionsButton.height;

        multiplayerButton = new Button();
        multiplayerButton.xPos = (int)(0.5f * Level.TILE_SIZE);
        multiplayerButton.width = (int)(7.0f * Level.TILE_SIZE);
        multiplayerButton.height = (int)(1.5f * Level.TILE_SIZE);
        multiplayerButton.yPos = optionsButton.yPos - multiplayerButton.height;
    }

    @Override
    public void run(){
        startButton.update();
        if(startButton.isPressed){
            if(!Options.optionsHaveBeenSet){
                StateManager.pushState(new OptionsScreenState(true));
            } else {
                StateManager.pushState(new InGameState());
            }
        }
        optionsButton.update();
        if(optionsButton.isPressed){
            StateManager.pushState(new OptionsScreenState(false));
        }
        multiplayerButton.update();
        if(multiplayerButton.isPressed){

        }

        startButton.render();
        optionsButton.render();
        multiplayerButton.render();

        Global.textRenderer.renderString(TAPTOSTART_TEXT, 0, 0, TextRenderer.TEXTSCALE_SMALL);
        Global.textRenderer.renderString(TAPTOSTART_TEXT, 0, TextRenderer.TEXTSCALE_SMALL, TextRenderer.TEXTSCALE_MEDIUM);
        Global.textRenderer.renderString(TAPTOSTART_TEXT, 0, TextRenderer.TEXTSCALE_SMALL + TextRenderer.TEXTSCALE_MEDIUM, TextRenderer.TEXTSCALE_LARGE);

        Global.textRenderer.renderString(TITLESCREEN_TITLE, 0, Gdx.graphics.getHeight() / 2, TextRenderer.TEXTSCALE_LARGE);
        Global.textRenderer.renderString(TITLESCREEN_AUTHOR, 0, (Gdx.graphics.getHeight() / 2) + TextRenderer.TEXTSCALE_LARGE, TextRenderer.TEXTSCALE_MEDIUM);
    }

    @Override
    public void dispose(){

    }
}
