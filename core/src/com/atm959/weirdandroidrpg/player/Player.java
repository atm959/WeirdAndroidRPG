package com.atm959.weirdandroidrpg.player;

import com.atm959.weirdandroidrpg.time.Time;
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
        texture = new Texture("player/player.png");
        sb = new SpriteBatch();
    }

    public void takeStep(Level level, DPad dpad){
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
        int velX = 0;
        int velY = 0;
        boolean playerMovedX = false;
        boolean playerMovedY = false;
        for(int i = 0; i < 8; i++){
            if(dpad.touched[i]){
                velX = (int)dpadDirections[i].x;
                velY = (int)dpadDirections[i].y;
                if(velX != 0) playerMovedX = true;
                if(velY != 0) playerMovedY = true;
            }
        }
        if(level.getTile(xPos + velX, yPos).isSolid){
            velX = 0;
            playerMovedX = false;
        }
        if(level.getTile(xPos, yPos + velY).isSolid){
            velY = 0;
            playerMovedY = false;
        }
        xPos += velX;
        yPos += velY;
        if(level.getTile(xPos, yPos).isSolid){
            xPos = oldX;
            yPos = oldY;
            playerMovedX = false;
            playerMovedY = false;
        }

        if(playerMovedX || playerMovedY) {
            level.getTile(oldX, oldY).onPlayerWalkOutOf();
            level.getTile(xPos, yPos).onPlayerWalkInto();
        }
    }

    public void update(Level level){
        int onScreenX = (xPos * Level.TILE_SIZE) - level.scrollX;
        int onScreenY = (yPos * Level.TILE_SIZE) - level.scrollY;
        int numTilesY = Gdx.graphics.getHeight() / Level.TILE_SIZE;

        if(onScreenX < (3 * Level.TILE_SIZE)) level.scrollX -= (SCROLL_SPEED * Time.deltaTime);
        if((onScreenX + Level.TILE_SIZE) > (5 * Level.TILE_SIZE)) level.scrollX += (SCROLL_SPEED * Time.deltaTime);
        if(onScreenY < (((numTilesY / 2) - 2) * Level.TILE_SIZE)) level.scrollY -= (SCROLL_SPEED * Time.deltaTime);
        if((onScreenY + Level.TILE_SIZE) > (((numTilesY / 2) + 2) * Level.TILE_SIZE)) level.scrollY += (SCROLL_SPEED * Time.deltaTime);
    }

    public void render(Level level){
        sb.begin();
        sb.draw(texture, (xPos * Level.TILE_SIZE) - level.scrollX, Util.convertY((yPos * Level.TILE_SIZE) - level.scrollY, Level.TILE_SIZE), Level.TILE_SIZE, Level.TILE_SIZE);
        sb.end();
    }

    public void dispose(){
        texture.dispose();
        sb.dispose();
    }
}
