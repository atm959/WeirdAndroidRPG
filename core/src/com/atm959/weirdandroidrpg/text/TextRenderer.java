package com.atm959.weirdandroidrpg.text;

import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/23/2022.
 */
public class TextRenderer {
    public static final int TEXTSCALE_SMALL = Gdx.graphics.getWidth() / 20;
    public static final int TEXTSCALE_MEDIUM = Gdx.graphics.getWidth() / 16;
    public static final int TEXTSCALE_LARGE = Gdx.graphics.getWidth() / 12;

    private static SpriteBatch sb;
    private static Texture fontTex;

    public static void init(){
        sb = new SpriteBatch();
        fontTex = new Texture("ui/font.png");
    }

    public static void renderString(String s, float x, float y, float scale){
        sb.begin();
        for(int i = 0; i < s.length(); i++){
            float xPos = (i * (int)scale) + x;
            int c = s.charAt(i) - 32;
            sb.draw(fontTex, xPos, Util.convertY((int)y, (int)scale), scale, scale, (c % 16) * 10, (c / 16) * 10, 10, 10, false, false);
        }
        sb.end();
    }

	public static void renderString(String s, float x, float y, float scale, float r, float g, float b, float a){
		sb.begin();
		sb.setColor(r, g, b, a);
		for(int i = 0; i < s.length(); i++){
			float xPos = (i * (int)scale) + x;
			int c = s.charAt(i) - 32;
			sb.draw(fontTex, xPos, Util.convertY((int)y, (int)scale), scale, scale, (c % 16) * 10, (c / 16) * 10, 10, 10, false, false);
		}
		sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		sb.end();
	}

	public static float calculateCenteredXPosition(String s, float scale, float containerX, float containerWidth){
		return containerX + ((containerWidth / 2.0f) - ((s.length() * scale) / 2.0f));
	}
	public static float calculateCenteredYPosition(float scale, float containerY, float containerHeight){
		return containerY + ((containerHeight / 2.0f) - (scale / 2.0f));
	}

	public static float calculateFittingScale(String s, float containerWidth){
		return containerWidth / (s.length() + 2);
	}

    public static void dispose(){
        sb.dispose();
        fontTex.dispose();
    }
}
