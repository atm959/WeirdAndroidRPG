package com.atm959.weirdandroidrpg;

import com.atm959.weirdandroidrpg.gamestates.StateManager;
import com.atm959.weirdandroidrpg.gamestates.TitleState;
import com.atm959.weirdandroidrpg.global.Global;
import com.atm959.weirdandroidrpg.global.Time;
import com.atm959.weirdandroidrpg.input.TouchInput;
import com.atm959.weirdandroidrpg.savedata.Options;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Created by atm959 on 3/22/2022.
 */
public class Game extends ApplicationAdapter {
	@Override
	public void create () {
		Options.load();
		StateManager.initStack();
		StateManager.pushState(new TitleState());
		Global.textRenderer = new TextRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1.0f);

		TouchInput.update();
		StateManager.runCurrentState();

		Time.frameCount++;
		Time.calculateFPSAndDeltaTime();
		if(Options.showFPSAndDelta) {
			Global.textRenderer.renderString("FPS: " + Time.fps, 0, Gdx.graphics.getHeight() - TextRenderer.TEXTSCALE_LARGE, TextRenderer.TEXTSCALE_LARGE);
			Global.textRenderer.renderString("DELTA: " + Time.deltaTime, 0, Gdx.graphics.getHeight() - TextRenderer.TEXTSCALE_LARGE - TextRenderer.TEXTSCALE_MEDIUM, TextRenderer.TEXTSCALE_MEDIUM);
		}
	}
	
	@Override
	public void dispose () {
		Global.textRenderer.dispose();
		StateManager.disposeAll();
	}
}
