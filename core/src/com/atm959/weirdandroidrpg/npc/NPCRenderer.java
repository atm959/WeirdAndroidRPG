package com.atm959.weirdandroidrpg.npc;

import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.npc.npcs.NPC;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 4/6/2022.
 */

//The NPC renderer
//Renders all of the NPCs
public class NPCRenderer {
    private Texture npcAtlas; //The NPC texture atlas
    private SpriteBatch sb; //The sprite batch that will be used to render the NPCs
    private boolean npcsAreFlipped; //Are the NPCs flipped horizontally?

    public NPCRenderer(){
        npcAtlas = new Texture("npc/npcs.png"); //Load the NPC texture atlas
        sb = new SpriteBatch(); //Initialize the sprite batch
    }

	//Set the horizontal flipping of the NPCs
    public void setNpcsAreFlipped(boolean npcsAreFlipped){
        this.npcsAreFlipped = npcsAreFlipped;
    }

	//Render all of the NPCs
    public void renderLevelNPCs(int scrollX, int scrollY, NPC[] loadedNPCs){
        sb.begin(); //Begin the sprite batch

		//Loop through all of the NPCs
        for(int i = 0; i < loadedNPCs.length; i++){
            NPC npc = loadedNPCs[i]; //Grab a reference to the NPC

			//Is the NPC render flag set?
            if(npc.isRendered) {
				//If so, find the position of its graphic in the texture atlas
                int srcX = (npc.atlasID % 16) * 16;
                int srcY = (npc.atlasID / 16) * 16;

				//Draw the NPC
                sb.draw(npcAtlas, (npc.xPos * Level.tileSize) - scrollX, Util.convertY((npc.yPos * Level.tileSize) - scrollY, Level.tileSize), Level.tileSize, Level.tileSize, srcX, srcY, 16, 16, npcsAreFlipped, false);
            }
        }

        sb.end(); //End the sprite batch
    }

	public void dispose(){
		npcAtlas.dispose(); //Dispose of the NPC texture atlas
		sb.dispose(); //Dispose of the sprite batch
	}
}
