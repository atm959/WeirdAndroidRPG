package com.atm959.weirdandroidrpg;

import com.atm959.weirdandroidrpg.gamestates.StateManager;
import com.atm959.weirdandroidrpg.gamestates.TitleState;
import com.atm959.weirdandroidrpg.global.Global;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Created by atm959 on 3/22/2022.
 */
public class Game extends ApplicationAdapter {
	@Override
	public void create () {
		StateManager.InitStack();
		StateManager.PushState(new TitleState());
		Global.textRenderer = new TextRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1.0f);
		StateManager.RunCurrentState();
		Global.frameCount++;
	}
	
	@Override
	public void dispose () {
		Global.textRenderer.Dispose();
		StateManager.DisposeAll();
	}
}
