package com.atm959.weirdandroidrpg;

import com.atm959.weirdandroidrpg.audio.BGM;
import com.atm959.weirdandroidrpg.gamestates.StateManager;
import com.atm959.weirdandroidrpg.gamestates.TitleState;
import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.input.TouchInput;
import com.atm959.weirdandroidrpg.items.items.Item;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.level.tiles.Tile;
import com.atm959.weirdandroidrpg.net.packets.serverpacket.ServerPacket;
import com.atm959.weirdandroidrpg.npc.npcs.NPC;
import com.atm959.weirdandroidrpg.savedata.Options;
import com.atm959.weirdandroidrpg.sharingimage.ImageSharingAPI;
import com.atm959.weirdandroidrpg.sharingimage.SharingImage;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.atm959.weirdandroidrpg.time.Time;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.nio.ByteBuffer;

/**
 * Created by atm959 on 3/22/2022.
 */

//The main game class
public class Game extends ApplicationAdapter {
	public static ImageSharingAPI imageSharingAPI; //The image sharing API

	//Take the current platform's image sharing API in and set the local copy
	public Game(ImageSharingAPI imageShareAPI){
		imageSharingAPI = imageShareAPI;
	}

	@Override
	public void create () {
		//Calculate the level tile size so the title state will have correctly-size graphical elements
		Level.tileSize = (Gdx.graphics.getWidth() / 8);

		Options.load(); //Load the options
		Tile.initTileTypes(); //Init the default tile types
		Item.initItemTypes(); //Init the default item types
		NPC.initNPCTypes(); //Init the default NPC types
		StateManager.initStack(); //Init the state manager
		StateManager.pushState(new TitleState()); //Push the title screen state onto the state stack
		TextRenderer.init(); //Init the text renderer
		SharingImage.init(); //Init the sharing image
		ServerPacket.initServerPacketTypes(); //Init the default server packet types
	}

	@Override
	public void render () {
		//Re-calculate the tile size in case the screen resolution changed
		Level.tileSize = (Gdx.graphics.getWidth() / 8);

		//Clear the screen
		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1.0f);

		TouchInput.update(); //Update the touch input
		StateManager.runCurrentState(); //Execute the last state on the state stack

		Time.frameCount++; //Increment the frame count
		Time.calculateFPSAndDeltaTime(); //Calculate the FPS and delta time
		if (Options.showDebugInfo) {
			//If the option to do so is enabled, render debug info at the bottom of the screen
			TextRenderer.renderString("FPS: " + Time.fps, 0, Gdx.graphics.getHeight() - TextRenderer.TEXTSCALE_LARGE, TextRenderer.TEXTSCALE_LARGE);
			TextRenderer.renderString("DELTA: " + Time.deltaTime, 0, Gdx.graphics.getHeight() - TextRenderer.TEXTSCALE_LARGE - TextRenderer.TEXTSCALE_MEDIUM, TextRenderer.TEXTSCALE_MEDIUM);
			TextRenderer.renderString("STATES: " + StateManager.stateStack.size(), 0, Gdx.graphics.getHeight() - TextRenderer.TEXTSCALE_LARGE - TextRenderer.TEXTSCALE_MEDIUM - TextRenderer.TEXTSCALE_LARGE, TextRenderer.TEXTSCALE_LARGE);
		}
	}

	@Override
	public void dispose () {
		TextRenderer.dispose();//Dispose of the text renderer
		StateManager.disposeAll(); //Dispose of all of the states on the state stack

		SharingImage.dispose(); //Dispose of the sharing image

		BGM.dispose();
	}
}
