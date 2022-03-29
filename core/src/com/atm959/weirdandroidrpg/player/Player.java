package com.atm959.weirdandroidrpg.player;

import com.atm959.weirdandroidrpg.global.Time;
import com.atm959.weirdandroidrpg.input.DPad;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by atm959 on 3/24/2022.
 */
public class Player {
    private static final float SCROLL_SPEED = 1.0f;

    public int xPos, yPos;

    private Texture texture;
    private SpriteBatch sb;

    public Player(){
        texture = new Texture("player.png");
        sb = new SpriteBatch();
    }

    public void Update(Level level, DPad dpad){
        final Vector2[] dpadDirections = {
            new Vector2(0, -1),
            new Vector2(0, 1),
            new Vector2(-1, 0),
            new Vector2(1, 0),
            new Vector2(-1, -1),
            new Vector2(1, -1),
            new Vector2(-1, 1),
            new Vector2(1, 1)
        };
        int oldX = xPos;
        int oldY = yPos;
        for(int i = 0; i < 8; i++){
            if(dpad.touched[i]){
                xPos += dpadDirections[i].x;
                yPos += dpadDirections[i].y;
            }
        }
        if(level.GetTile(xPos, yPos).isSolid){
            xPos = oldX;
            yPos = oldY;
        }

        int onScreenX = (xPos * Level.TILE_SIZE) - level.scrollX;
        int onScreenY = (yPos * Level.TILE_SIZE) - level.scrollY;
        int numTilesY = Gdx.graphics.getHeight() / Level.TILE_SIZE;

        if(onScreenX < (3 * Level.TILE_SIZE)) level.scrollX -= (SCROLL_SPEED * Time.deltaTime);
        if((onScreenX + Level.TILE_SIZE) > (5 * Level.TILE_SIZE)) level.scrollX += (SCROLL_SPEED * Time.deltaTime);
        if(onScreenY < (((numTilesY / 2) - 2) * Level.TILE_SIZE)) level.scrollY -= (SCROLL_SPEED * Time.deltaTime);
        if((onScreenY + Level.TILE_SIZE) > (((numTilesY / 2) + 2) * Level.TILE_SIZE)) level.scrollY += (SCROLL_SPEED * Time.deltaTime);
    }

    public void TakeStep(int dirX, int dirY){
        xPos += dirX;
        yPos += dirY;
    }

    public void Render(Level level){
        sb.begin();
        sb.draw(texture, (xPos * Level.TILE_SIZE) - level.scrollX, Util.ConvertY((yPos * Level.TILE_SIZE) - level.scrollY, Level.TILE_SIZE), Level.TILE_SIZE, Level.TILE_SIZE);
        sb.end();
    }

    public void Dispose(){
        texture.dispose();
        sb.dispose();
    }
}
