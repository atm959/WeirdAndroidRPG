package com.atm959.weirdandroidrpg.input;

import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.savedata.Options;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/24/2022.
 */
public class DPad {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int UP_LEFT = 4;
    public static final int UP_RIGHT = 5;
    public static final int DOWN_LEFT = 6;
    public static final int DOWN_RIGHT = 7;

    private Texture texture;
    private SpriteBatch sb;
    public int xPos, yPos;
    public int size;

    public boolean directionIsPressed = false;
    public boolean[] touched;

    public DPad(){
        texture = new Texture("ui/dpad.png");
        sb = new SpriteBatch();

        touched = new boolean[8];
    }

    public void update(){
        size = 4 * Level.tileSize;
        int xOffset = 0;
        if(Options.rightHandedDPad){
            xOffset += (1.5f * Level.tileSize);
        } else {
            xOffset -= (1.5f * Level.tileSize);
        }
        xPos = ((Gdx.graphics.getWidth() / 2) - (size / 2)) + xOffset;
        yPos = Gdx.graphics.getHeight() - (size + Level.tileSize);

        if(TouchInput.touched) {
            int relativeX = TouchInput.touchX - xPos;
            int relativeY = TouchInput.touchY - yPos;

            //If the touch is not inside the D-Pad area, abort
            if((relativeX < 0) || (relativeX > size) || (relativeY < 0) || (relativeY > size)) return;

            int scaleFactor = size / 48;
            int normalizedX = relativeX / scaleFactor;
            int normalizedY = relativeY / scaleFactor;

            final int[] buttonXPoses = {
                    16, 16, 0, 32, 0, 32, 0, 32
            };
            final int[] buttonYPoses = {
                    0, 32, 16, 16, 0, 0, 32, 32
            };
            for (int i = 0; i < 8; i++) {
                int leftX = buttonXPoses[i];
                int rightX = buttonXPoses[i] + 16;
                int topX = buttonYPoses[i];
                int bottomX = buttonYPoses[i] + 16;

                if((normalizedX >= leftX) && (normalizedX < rightX)){
                    if((normalizedY >= topX) && (normalizedY < bottomX)){
                        touched[i] = true;
                        directionIsPressed = true;
                    }
                }
            }
        } else {
            directionIsPressed = false;
            for (int i = 0; i < 8; i++) {
                touched[i] = false;
            }
        }
    }

    public void render(){
        int size = 4 * Level.tileSize;
        sb.begin();
        sb.setColor(1.0f, 1.0f, 1.0f, Options.dpadOpacity);
        sb.draw(texture, xPos, Util.convertY(yPos, size), size, size);
        sb.end();
    }

    public void dispose(){
        texture.dispose();
        sb.dispose();
    }
}
