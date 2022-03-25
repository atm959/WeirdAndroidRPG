package com.atm959.weirdandroidrpg.input;

import com.atm959.weirdandroidrpg.level.Level;
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
    private int xPos, yPos;
    private int size;

    private int[] touchTimer;
    public boolean[] touched;

    public DPad(){
        texture = new Texture("dpad.png");
        sb = new SpriteBatch();

        touchTimer = new int[8];
        touched = new boolean[8];
    }

    public void Update(){
        size = 4 * Level.TILE_SIZE;
        xPos = (Gdx.graphics.getWidth() / 2) - (size / 2);
        yPos = Gdx.graphics.getHeight() - (size + Level.TILE_SIZE);

        if(Gdx.input.isTouched()) {
            int relativeX = Gdx.input.getX() - xPos;
            int relativeY = Gdx.input.getY() - yPos;

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
                        touchTimer[i]++;
                        if(touchTimer[i] > 1) touched[i] = false;
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                touchTimer[i] = 0;
                touched[i] = false;
            }
        }
    }

    public void Render(){
        int size = 4 * Level.TILE_SIZE;
        sb.begin();
        sb.draw(texture, xPos, Util.ConvertY(yPos, size), size, size);
        sb.end();
    }

    public void Dispose(){
        texture.dispose();
        sb.dispose();
    }
}
