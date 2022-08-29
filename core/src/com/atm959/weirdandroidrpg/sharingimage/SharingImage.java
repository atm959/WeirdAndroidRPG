package com.atm959.weirdandroidrpg.sharingimage;

import com.atm959.weirdandroidrpg.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.nio.ByteBuffer;

/**
 * Created by atm959 on 8/10/2022.
 */

//The sharing image; can have graphics rendered into it and be shared to other apps
public class SharingImage {
	public static FrameBuffer m_fbo; //The framebuffer object that can be rendered to
	public static byte[] pixelData; //The pixels from the framebuffer

	//Only used if you want to render the sharing image to the screen
	public static TextureRegion m_fboRegion;

	public static void init() {
		m_fbo = new FrameBuffer(Pixmap.Format.RGB565, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		m_fboRegion = new TextureRegion(m_fbo.getColorBufferTexture());
		m_fboRegion.flip(false, true);
	}

	//Bind the framebuffer and start rendering to it
	public static void beginRender() {
		m_fbo.begin();
	}

	//Unbind the framebuffer and stop rendering to it
	public static void endRender() {
		m_fbo.end();
	}

	//Get the framebuffer's texture region, if you want to render it to the screen
	public static TextureRegion getTextureRegion() {
		return m_fboRegion;
	}

	//Share the image
	public static void share(String text) {
		m_fbo.begin(); //Bind the framebuffer
		pixelData = ScreenUtils.getFrameBufferPixels(true); //Copy its pixels into pixelData
		m_fbo.end(); //Unbind the framebuffer

		//Create a Pixmap from the framebuffer's pixels
		Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
		ByteBuffer pixels = pixmap.getPixels();
		pixels.clear();
		pixels.put(pixelData);
		pixels.position(0);

		//Share the image using the image sharing API for the current platform
		Game.imageSharingAPI.shareImage(text, pixmap);

		//Dispose of the Pixmap
		pixmap.dispose();
	}

	public static void dispose() {
		m_fbo.dispose(); //Dispose of the framebuffer
	}
}
