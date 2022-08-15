package com.atm959.weirdandroidrpg.sharingimage;

import com.atm959.weirdandroidrpg.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.nio.ByteBuffer;

/**
 * Created by atm959 on 8/10/2022.
 */
public class SharingImage {
	public static FrameBuffer m_fbo;
	public static TextureRegion m_fboRegion;
	public static SpriteBatch sb;
	public static byte[] pixelData;

	public static void init(){
		m_fbo = new FrameBuffer(Pixmap.Format.RGB565, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		m_fboRegion = new TextureRegion(m_fbo.getColorBufferTexture());
		m_fboRegion.flip(false, true);
	}

	public static void beginRender(){
		m_fbo.begin();
	}

	public static void endRender(){
		m_fbo.end();
	}

	public static Texture getTexture(){
		return m_fboRegion.getTexture();
	}

	public static TextureRegion getTextureRegion(){
		return m_fboRegion;
	}

	public static void share(String text){
		m_fbo.begin();
		pixelData = ScreenUtils.getFrameBufferPixels(true);
		m_fbo.end();

		Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
		ByteBuffer pixels = pixmap.getPixels();
		pixels.clear();
		pixels.put(pixelData);
		pixels.position(0);

		Game.imageSharingAPI.shareImage(text, pixmap);
		pixmap.dispose();
	}

	public static void dispose(){
		m_fbo.dispose();
	}
}
