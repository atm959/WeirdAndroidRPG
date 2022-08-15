package com.atm959.weirdandroidrpg.player;

import com.atm959.weirdandroidrpg.input.DPad;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.npc.npcs.NPC;
import com.atm959.weirdandroidrpg.time.Time;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by atm959 on 3/24/2022.
 */

//The player
public class Player {
    private static final float SCROLL_SPEED = 1.0f; //Camera scroll speed

    public int xPos, yPos; //Position in the level

    private Texture texture; //The player texture
    private SpriteBatch sb; //The sprite batch used to render the player

	//Initialize the player
    public Player(){
        texture = new Texture("player/player.png");
        sb = new SpriteBatch();
    }

	//Make the player take a step
    public void takeStep(Level level, DPad dpad, NPC[] loadedNPCs){
		//D-Pad button directions
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

		//Copy the current position
        int oldX = xPos;
        int oldY = yPos;

		//The velocity of the player
        int velX = 0;
        int velY = 0;

		//Has the player moved in a specific direction?
        boolean playerMovedX = false;
        boolean playerMovedY = false;

		//Loop through the D-Pad buttons
		for(int i = 0; i < 8; i++){
            if(dpad.touched[i]){
				//If a button was touched, set the velocity based on it
                velX = (int)dpadDirections[i].x;
                velY = (int)dpadDirections[i].y;

				//If the player will probably move in the X or Y direction, set it here
                if(velX != 0) playerMovedX = true;
                if(velY != 0) playerMovedY = true;
            }
        }

		//TODO: Resume commenting from this point tomorrow
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

            for(int i = 0; i < loadedNPCs.length; i++){
                loadedNPCs[i].onPlayerTakeStep(this, loadedNPCs, level);
            }
        }
    }

    public void update(Level level){
        int onScreenX = (xPos * Level.tileSize) - level.scrollX;
        int onScreenY = (yPos * Level.tileSize) - level.scrollY;
        int numTilesY = Gdx.graphics.getHeight() / Level.tileSize;

        if(onScreenX < (3 * Level.tileSize)) level.scrollX -= (SCROLL_SPEED * Time.deltaTime);
        if((onScreenX + Level.tileSize) > (5 * Level.tileSize)) level.scrollX += (SCROLL_SPEED * Time.deltaTime);
        if(onScreenY < (((numTilesY / 2) - 2) * Level.tileSize)) level.scrollY -= (SCROLL_SPEED * Time.deltaTime);
        if((onScreenY + Level.tileSize) > (((numTilesY / 2) + 2) * Level.tileSize)) level.scrollY += (SCROLL_SPEED * Time.deltaTime);
    }

    public void render(Level level){
        sb.begin();
        sb.draw(texture, (xPos * Level.tileSize) - level.scrollX, Util.convertY((yPos * Level.tileSize) - level.scrollY, Level.tileSize), Level.tileSize, Level.tileSize);
        sb.end();
    }

    public void dispose(){
        texture.dispose();
        sb.dispose();
    }
}
