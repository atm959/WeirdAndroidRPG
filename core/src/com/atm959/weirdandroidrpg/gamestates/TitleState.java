package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.global.Global;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.badlogic.gdx.Gdx;

/**
 * Created by atm959 on 3/23/2022.
 */
public class TitleState extends GameState {
    private static final String TITLESCREEN_TITLE = "WEIRD RPG";
    private static final String TITLESCREEN_AUTHOR = "BY ATM959";
    private static final String TAPTOSTART_TEXT = "TAP TO START";

    public TitleState(){}

    @Override
    public void Run(){
        if(Gdx.input.isTouched()){
            StateManager.PushState(new OptionsScreenState(true));
        }

        Global.textRenderer.RenderString(TAPTOSTART_TEXT, 0, 0, TextRenderer.TEXTSCALE_SMALL);
        Global.textRenderer.RenderString(TAPTOSTART_TEXT, 0, TextRenderer.TEXTSCALE_SMALL, TextRenderer.TEXTSCALE_MEDIUM);
        Global.textRenderer.RenderString(TAPTOSTART_TEXT, 0, TextRenderer.TEXTSCALE_SMALL + TextRenderer.TEXTSCALE_MEDIUM, TextRenderer.TEXTSCALE_LARGE);

        Global.textRenderer.RenderString(TITLESCREEN_TITLE, 0, Gdx.graphics.getHeight() / 2, TextRenderer.TEXTSCALE_LARGE);
        Global.textRenderer.RenderString(TITLESCREEN_AUTHOR, 0, (Gdx.graphics.getHeight() / 2) + TextRenderer.TEXTSCALE_LARGE, TextRenderer.TEXTSCALE_MEDIUM);
    }

    @Override
    public void Dispose(){

    }
}
