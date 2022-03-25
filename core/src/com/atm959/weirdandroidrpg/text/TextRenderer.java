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

    SpriteBatch sb;
    Texture fontTex;

    public TextRenderer(){
        this.sb = new SpriteBatch();
        this.fontTex = new Texture("font.png");
    }

    public void RenderString(String s, float x, float y, float scale){
        this.sb.begin();
        for(int i = 0; i < s.length(); i++){
            float xPos = (i * (int)scale) + x;
            int c = s.charAt(i) - 32;
            this.sb.draw(this.fontTex, xPos, Util.ConvertY((int)y, (int)scale), scale, scale, (c % 16) * 10, (c / 16) * 10, 10, 10, false, false);
        }
        this.sb.end();
    }

    public void Dispose(){
        this.sb.dispose();
        this.fontTex.dispose();
    }
}
