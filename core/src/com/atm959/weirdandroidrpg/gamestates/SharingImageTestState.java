package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.sharingimage.SharingImage;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class SharingImageTestState extends GameState {
	private SpriteBatch sb;
	private Button backButton;
	private Button shareButton;
	private Texture bgTex;
	private Texture logoTex;
	private Texture pointsTex;

	public SharingImageTestState(){
		sb = new SpriteBatch();

		backButton = new Button("ui/menuButton.png");
		backButton.xPos = 0;
		backButton.yPos = 0;
		backButton.width = Level.tileSize * 2;
		backButton.height = Level.tileSize * 2;

		shareButton = new Button("ui/share.png");
		shareButton.xPos = 0;
		shareButton.yPos = Level.tileSize * 2;
		shareButton.width = Level.tileSize * 2;
		shareButton.height = Level.tileSize * 2;

		bgTex = new Texture("ui/buttons.png");
		logoTex = new Texture("ui/slider.png");
		pointsTex = new Texture("level/tileset.png");
	}

	public void run(){
		SharingImage.beginRender();
		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1.0f);
		sb.begin();
		sb.draw(bgTex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sb.draw(logoTex, 0, Util.convertY((Gdx.graphics.getHeight() / 2) - (Gdx.graphics.getWidth() / 2), Gdx.graphics.getWidth()), Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		sb.draw(pointsTex, (Gdx.graphics.getWidth() / 2) - ((4 * Level.tileSize) / 2), Util.convertY((Gdx.graphics.getHeight() - (2 * Level.tileSize)) - (int)(0.5f * Level.tileSize), Level.tileSize * 2), 4 * Level.tileSize, 2 * Level.tileSize);
		sb.end();
		TextRenderer.renderString("UWU OWO X3 XD", 0, 0, TextRenderer.calculateFittingScale("UWU OWO X3 XD", Gdx.graphics.getWidth(), true));
		SharingImage.endRender();

		TextureRegion t = SharingImage.getTextureRegion();
		sb.begin();
		sb.draw(t, Gdx.graphics.getWidth() / 2, Util.convertY(0, Gdx.graphics.getHeight() / 2), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		sb.end();

		backButton.update();
		shareButton.update();
		if(backButton.isPressed){
			StateManager.popState();
		}
		if(shareButton.isPressed){
			SharingImage.share("atm959 is a big dum-dum");
		}

		backButton.render();
		shareButton.render();
	}

	public void dispose(){
		sb.dispose();
		backButton.dispose();
		shareButton.dispose();
		bgTex.dispose();
		logoTex.dispose();
		pointsTex.dispose();
	}
}
