package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.audio.BGM;
import com.atm959.weirdandroidrpg.time.Time;
import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.savedata.Options;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/23/2022.
 */
public class TitleState extends GameState {
    private static final String TITLESCREEN_TITLE = "WEIRD RPG";
    private static final String TITLESCREEN_AUTHOR = "BY ATM959";
    private static final String PLAY_TEXT = "PLAY";
    private static final String OPTIONS_TEXT = "OPTIONS";
    private static final String CREDITS_TEXT = "CREDITS";

    private Button startButton;
    private Button optionsButton;
    private Button creditsButton;

    private Texture bgTex;
    private SpriteBatch bgSB;
    float bgOffsetX = 0.0f, bgOffsetY = 0.0f;

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

        creditsButton = new Button();
        creditsButton.xPos = (int)(0.5f * Level.TILE_SIZE);
        creditsButton.width = (int)(7.0f * Level.TILE_SIZE);
        creditsButton.height = (int)(1.5f * Level.TILE_SIZE);
        creditsButton.yPos = optionsButton.yPos - creditsButton.height;

        bgTex = new Texture("title/bg.png");
        bgSB = new SpriteBatch();
    }

    @Override
    public void run(){
        startButton.update();
        if(startButton.isPressed){
            if(!Options.optionsHaveBeenSet){
                StateManager.pushState(new OptionsScreenState(true));
            } else {
                StateManager.replaceCurrentState(new InGameState());
            }
        }
        optionsButton.update();
        if(optionsButton.isPressed){
            StateManager.pushState(new OptionsScreenState(false));
        }
        creditsButton.update();
        //if(creditsButton.isPressed){ }

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

        startButton.render();
        optionsButton.render();
        creditsButton.render();

        TextRenderer.renderString(TITLESCREEN_TITLE, 0, Gdx.graphics.getHeight() / 2, TextRenderer.TEXTSCALE_LARGE);
        TextRenderer.renderString(TITLESCREEN_AUTHOR, 0, (Gdx.graphics.getHeight() / 2) + TextRenderer.TEXTSCALE_LARGE, TextRenderer.TEXTSCALE_MEDIUM);

        TextRenderer.renderString(PLAY_TEXT, startButton.xPos + (startButton.width / 2) - ((PLAY_TEXT.length() * TextRenderer.TEXTSCALE_LARGE) / 2), (int)(startButton.yPos + 0.35f * Level.TILE_SIZE), TextRenderer.TEXTSCALE_LARGE);
        TextRenderer.renderString(OPTIONS_TEXT, optionsButton.xPos + (optionsButton.width / 2) - ((OPTIONS_TEXT.length() * TextRenderer.TEXTSCALE_LARGE) / 2), (int)(optionsButton.yPos + 0.35f * Level.TILE_SIZE), TextRenderer.TEXTSCALE_LARGE);
        TextRenderer.renderString(CREDITS_TEXT, creditsButton.xPos + (creditsButton.width / 2) - ((CREDITS_TEXT.length() * TextRenderer.TEXTSCALE_LARGE) / 2), (int)(creditsButton.yPos + 0.35f * Level.TILE_SIZE), TextRenderer.TEXTSCALE_LARGE);
    }

    @Override
    public void dispose(){
        startButton.dispose();
        optionsButton.dispose();
        creditsButton.dispose();
        bgTex.dispose();
        bgSB.dispose();
    }
}
