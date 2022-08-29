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

	private final Texture texture; //The player texture
	private final SpriteBatch sb; //The sprite batch used to render the player

	//Initialize the player
	public Player() {
		texture = new Texture("player/player.png");
		sb = new SpriteBatch();
	}

	//Make the player take a step
	public void takeStep(Level level, DPad dpad, NPC[] loadedNPCs) {
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
		for (int i = 0; i < 8; i++) {
			if (dpad.touched[i]) {
				//If a button was touched, set the velocity based on it
				velX = (int) dpadDirections[i].x;
				velY = (int) dpadDirections[i].y;

				//If the player will probably move in the X or Y direction, set it here
				if (velX != 0) playerMovedX = true;
				if (velY != 0) playerMovedY = true;
			}
		}

		//Check for collision with a solid tile in the X-velocity direction
		if (level.getTile(xPos + velX, yPos).isSolid) {
			//If so, reset the X velocity to 0. The player won't move in the X direction.
			velX = 0;
			playerMovedX = false;
		}

		//Check for collision with a solid tile in the Y-velocity direction
		if (level.getTile(xPos, yPos + velY).isSolid) {
			//If so, reset the Y velocity to 0. The player won't move in the Y direction.
			velY = 0;
			playerMovedY = false;
		}

		//Add the velocity to the player's position
		xPos += velX;
		yPos += velY;

		//Check for collision with a solid tile where the player is
		if (level.getTile(xPos, yPos).isSolid) {
			//If so, reset the player's position to the old position. The player won't move.
			xPos = oldX;
			yPos = oldY;
			playerMovedX = false;
			playerMovedY = false;
		}

		//Has the player moved?
		if (playerMovedX || playerMovedY) {
			//If so:
			level.getTile(oldX, oldY).onPlayerWalkOutOf(); //Tell the tile they were in that it was stepped out of
			level.getTile(xPos, yPos).onPlayerWalkInto(); //Tell the tile they are in now that it was stepped into

			//Loop through the NPCs and tell them that the player moved
			for (int i = 0; i < loadedNPCs.length; i++) {
				loadedNPCs[i].onPlayerTakeStep(this, loadedNPCs, level);
			}
		}
	}

	//Executed every frame
	public void update(Level level) {
		//Calculate the player's on-screen position
		int onScreenX = (xPos * Level.tileSize) - level.scrollX;
		int onScreenY = (yPos * Level.tileSize) - level.scrollY;

		//Calculate the amount of tiles that fit in the height of the screen
		int numTilesY = Gdx.graphics.getHeight() / Level.tileSize;

		//If the player is too close to the edge of the screen, scroll the level
		if (onScreenX < (3 * Level.tileSize)) level.scrollX -= (SCROLL_SPEED * Time.deltaTime);
		if ((onScreenX + Level.tileSize) > (5 * Level.tileSize))
			level.scrollX += (SCROLL_SPEED * Time.deltaTime);
		if (onScreenY < (((numTilesY / 2) - 2) * Level.tileSize))
			level.scrollY -= (SCROLL_SPEED * Time.deltaTime);
		if ((onScreenY + Level.tileSize) > (((numTilesY / 2) + 2) * Level.tileSize))
			level.scrollY += (SCROLL_SPEED * Time.deltaTime);
	}

	//Render the player
	public void render(Level level) {
		sb.begin(); //Begin the sprite batch

		//Draw the player
		sb.draw(texture, (xPos * Level.tileSize) - level.scrollX, Util.convertY((yPos * Level.tileSize) - level.scrollY, Level.tileSize), Level.tileSize, Level.tileSize);

		sb.end(); //End the sprite batch
	}

	public void dispose() {
		texture.dispose(); //Dispose of the player texture
		sb.dispose(); //Dispose of the sprite batch
	}
}
