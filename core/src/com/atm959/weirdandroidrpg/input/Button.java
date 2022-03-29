package com.atm959.weirdandroidrpg.input;

import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/25/2022.
 */
public class Button {
    public int xPos, yPos;
    public int width, height;
    public boolean isPressed;

    private Texture texture;
    private SpriteBatch sb;

    public Button(){
        texture = new Texture("button.png");
        sb = new SpriteBatch();
    }

    public void Update(){
        if(TouchInput.touched){
            int relativeX = TouchInput.touchX - xPos;
            int relativeY = TouchInput.touchY - yPos;

            if((relativeX < 0) || (relativeX > width) || (relativeY < 0) || (relativeY > height)) return;

            isPressed = true;
        } else {
            isPressed = false;
        }
    }

    public void Render(){
        sb.begin();
        sb.draw(texture, xPos, Util.ConvertY(yPos, height), width, height);
        sb.end();
    }

    public void Dispose(){
        texture.dispose();
        sb.dispose();
    }
}
