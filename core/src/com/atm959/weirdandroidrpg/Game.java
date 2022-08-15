package com.atm959.weirdandroidrpg;

import com.atm959.weirdandroidrpg.gamestates.StateManager;
import com.atm959.weirdandroidrpg.gamestates.TitleState;
import com.atm959.weirdandroidrpg.input.TouchInput;
import com.atm959.weirdandroidrpg.items.items.Item;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.level.tiles.Tile;
import com.atm959.weirdandroidrpg.npc.npcs.NPC;
import com.atm959.weirdandroidrpg.savedata.Options;
import com.atm959.weirdandroidrpg.sharingimage.SharingImage;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.atm959.weirdandroidrpg.time.Time;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Created by atm959 on 3/22/2022.
 */
public class Game extends ApplicationAdapter {
	public static ImageSharingAPI imageSharingAPI;

	public Game(ImageSharingAPI imageShareAPI){
		imageSharingAPI = imageShareAPI;
	}

	@Override
	public void create () {
		Level.tileSize = (Gdx.graphics.getWidth() / 8);

		Options.load();
		Tile.InitTileTypes();
		Item.InitItemTypes();
		NPC.InitNPCTypes();
		StateManager.initStack();
		StateManager.pushState(new TitleState());
		TextRenderer.init();
		SharingImage.init();
	}

	@Override
	public void render () {
		Level.tileSize = (Gdx.graphics.getWidth() / 8);

		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1.0f);

		TouchInput.update();
		StateManager.runCurrentState();

		Time.frameCount++;
		Time.calculateFPSAndDeltaTime();
		if (Options.showFPSAndDelta) {
			TextRenderer.renderString("FPS: " + Time.fps, 0, Gdx.graphics.getHeight() - TextRenderer.TEXTSCALE_LARGE, TextRenderer.TEXTSCALE_LARGE);
			TextRenderer.renderString("DELTA: " + Time.deltaTime, 0, Gdx.graphics.getHeight() - TextRenderer.TEXTSCALE_LARGE - TextRenderer.TEXTSCALE_MEDIUM, TextRenderer.TEXTSCALE_MEDIUM);
			TextRenderer.renderString("STATES: " + StateManager.stateStack.size(), 0, Gdx.graphics.getHeight() - TextRenderer.TEXTSCALE_LARGE - TextRenderer.TEXTSCALE_MEDIUM - TextRenderer.TEXTSCALE_LARGE, TextRenderer.TEXTSCALE_LARGE);
		}
	}

	@Override
	public void dispose () {
		TextRenderer.dispose();
		StateManager.disposeAll();

		SharingImage.dispose();
	}
}
