package com.atm959.weirdandroidrpg.text;

import com.atm959.weirdandroidrpg.time.Time;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;

/**
 * Created by atm959 on 9/7/2022.
 * An implementation of Minecraft-like splash text.
 */
public class SplashText {
	String text; //The text string itself
	Matrix4 matrix; //The matrix that will be used to rotate and scale the text
	float t; //Used for the bobbing effect, as a "counter"
	float scale; //The scale of the text, for the bobbing effect

	public SplashText(){
		text = "SPLASH TEXT!";
		matrix = new Matrix4();
		t = 0.0f;
		scale = 1.0f;
	}

	public void update(){
		matrix = new Matrix4(); //Identity matrix
		matrix.translate(Gdx.graphics.getWidth() - 650.0f, Gdx.graphics.getHeight() - 600.0f, 0.0f);

		matrix.rotate(0.0f, 0.0f, 1.0f, 35.0f);

		t += 0.01f * Time.deltaTime;
		float scale = (float) (1.0f + (0.02f * Math.sin(t)));
		matrix.scale(scale, scale, 1.0f);
	}

	public void render(){
		TextRenderer.setSpritebatchMatrix(matrix);
		TextRenderer.renderStringWithoutSpritebatchTranslation(text, TextRenderer.TEXTSCALE_MEDIUM, 1.0f, 1.0f, 0.0f);
		TextRenderer.resetSpritebatchMatrix();
	}

	public void dispose(){
	}
}
