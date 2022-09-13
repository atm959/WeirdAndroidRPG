package com.atm959.weirdandroidrpg.input;

import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/24/2022.
 */
public class CheckBox {
    public int xPos, yPos;
    public int boundingBoxWidth, boundingBoxHeight;
    public boolean isChecked;
	public String label;

    private Texture texture;
    private SpriteBatch sb;

    public CheckBox(){
        texture = new Texture("ui/checkbox.png");
        sb = new SpriteBatch();
		label = "CHECKBOX";
    }

    public void update(){
        if(TouchInput.touched){
            int relativeX = TouchInput.touchX - xPos;
            int relativeY = TouchInput.touchY - yPos;

            if((relativeX < 0) || (relativeX > boundingBoxHeight) || (relativeY < 0) || (relativeY > boundingBoxHeight)) return;

            isChecked = !isChecked;
        }
    }

    public void render(){
        int srcX = 0;
        if(isChecked){
            srcX += 16;
        }

        sb.begin();
        sb.draw(texture, xPos, Util.convertY(yPos, boundingBoxHeight), boundingBoxHeight, boundingBoxHeight, srcX, 0, 16, 16, false, false);
        sb.end();

		TextRenderer.renderStringFitting(label, xPos + boundingBoxHeight, yPos, boundingBoxWidth - boundingBoxHeight, boundingBoxHeight);
    }

    public void dispose(){
        texture.dispose();
        sb.dispose();
    }
}
