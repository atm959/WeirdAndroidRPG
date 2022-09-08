package com.atm959.weirdandroidrpg.text;

import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

/**
 * Created by atm959 on 3/23/2022.
 */
public class TextRenderer {
	//Different predefined text scales based on the screen size, will be removed in the future
	//   because these won't change size when the resolution changes
    public static final int TEXTSCALE_SMALL = Gdx.graphics.getWidth() / 20;
    public static final int TEXTSCALE_MEDIUM = Gdx.graphics.getWidth() / 16;
    public static final int TEXTSCALE_LARGE = Gdx.graphics.getWidth() / 12;

    private static SpriteBatch sb; //The SpriteBatch that will be used to render the text
    private static Texture fontTex; //The font texture atlas

	public static float lastScale;
	public static float lastY;

    public static void init(){
        sb = new SpriteBatch(); //Initialize the SpriteBatch
        fontTex = new Texture("ui/font.png"); //Load the font texture
    }

    public static void renderString(String s, float x, float y, float scale){
        sb.begin(); //Begin the sprite batch's composition

		//Loop through the characters in the string
        for(int i = 0; i < s.length(); i++){
            float xPos = (i * (int)scale) + x; //The X position on the screen of this character
            int c = s.charAt(i) - 32; //The character code

			//Draw the character
            sb.draw(fontTex, xPos, Util.convertY((int)y, (int)scale), scale, scale, (c % 16) * 10, (c / 16) * 10, 10, 10, false, false);
        }

        sb.end(); //End the sprite batch

		lastScale = scale;
		lastY = y;
    }

	public static void renderStringWithoutSpritebatchTranslation(String s, float scale){
		sb.begin(); //Begin the sprite batch's composition

		//Loop through the characters in the string
		for(int i = 0; i < s.length(); i++){
			float xPos = (i * (int)scale); //The X position on the screen of this character
			int c = s.charAt(i) - 32; //The character code

			//Draw the character
			sb.draw(fontTex, xPos, 0, scale, scale, (c % 16) * 10, (c / 16) * 10, 10, 10, false, false);
		}

		sb.end(); //End the sprite batch
	}

	public static void renderStringWithoutSpritebatchTranslation(String s, float scale, float r, float g, float b){
		sb.begin(); //Begin the sprite batch's composition
		sb.setColor(r, g, b, 1.0f);

		//Loop through the characters in the string
		for(int i = 0; i < s.length(); i++){
			float xPos = (i * (int)scale); //The X position on the screen of this character
			int c = s.charAt(i) - 32; //The character code

			//Draw the character
			sb.draw(fontTex, xPos, 0, scale, scale, (c % 16) * 10, (c / 16) * 10, 10, 10, false, false);
		}

		sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		sb.end(); //End the sprite batch
	}

	public static void renderString(String s, float x, float y, float scale, float r, float g, float b, float a){
		sb.begin(); //Begin the sprite batch's composition

		//Set the color of the sprite batch to the supplied color
		sb.setColor(r, g, b, a);

		for(int i = 0; i < s.length(); i++){
			float xPos = (i * (int)scale) + x;
			int c = s.charAt(i) - 32;

			//Draw the character
			sb.draw(fontTex, xPos, Util.convertY((int)y, (int)scale), scale, scale, (c % 16) * 10, (c / 16) * 10, 10, 10, false, false);
		}

		//Set the color of the sprite batch back to white
		sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);

		sb.end(); //End the sprite batch

		lastScale = scale;
		lastY = y;
	}

	public static void renderStringFitting(String s, float x, float y, float containerWidth){
		float scale = calculateFittingScale(s, containerWidth, false);
		float xP = calculateCenteredXPosition(s, scale, x, containerWidth);
		renderString(s, xP, y, scale);
	}

	//Calculate a centered X position inside a container based on the scale and string length
	public static float calculateCenteredXPosition(String s, float scale, float containerX, float containerWidth) {
		return containerX + ((containerWidth / 2.0f) - ((s.length() * scale) / 2.0f));
	}

	//Calculate a centered Y position inside a container based on the scale
	public static float calculateCenteredYPosition(float scale, float containerY, float containerHeight) {
		return containerY + ((containerHeight / 2.0f) - (scale / 2.0f));
	}

	//Calculate the largest scale that can neatly fit inside a container based on the string length and container width
	public static float calculateFittingScale(String s, float containerWidth, boolean withoutExtraPadding) {
		return containerWidth / (s.length() + (withoutExtraPadding ? 0 : 2));
	}

	//Get the width of the text at a particular scale
	public static float getTextWidth(String s, float scale){
		return s.length() * scale;
	}

	public static float getLastScale() {
		return lastScale;
	}

	public static float getNextLineY() {
		return lastY + lastScale;
	}

	public static void setSpritebatchMatrix(Matrix4 mat){
		sb.setTransformMatrix(mat);
	}

	public static void resetSpritebatchMatrix(){
		sb.setTransformMatrix(new Matrix4());
	}

    public static void dispose(){
        sb.dispose(); //Dispose of the sprite batch
        fontTex.dispose(); //Dispose of the font texture
    }
}
