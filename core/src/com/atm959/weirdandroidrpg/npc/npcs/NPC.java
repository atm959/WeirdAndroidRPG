package com.atm959.weirdandroidrpg.npc.npcs;

import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.npc.npcs.enemies.FrownEnemyNPC;
import com.atm959.weirdandroidrpg.npc.npcs.enemies.TestEnemyNPC;
import com.atm959.weirdandroidrpg.npc.npcs.friendly.SmileFriendlyNPC;
import com.atm959.weirdandroidrpg.player.Player;

import java.util.ArrayList;

/**
 * Created by atm959 on 4/6/2022.
 */

//The NPC base class; NPCs are defined as subclasses of this
public class NPC {
	public static ArrayList<NPC> NPC_TYPES; //Default NPC definitions, for an ID-based initialization
	public int atlasID = 0; //The "atlas ID" of the NPC's graphic on the NPC texture atlas
	public int xPos = 0, yPos = 0; //The NPC's position in the level
	public boolean isRendered = true; //Is this NPC rendered?
	public boolean isEnemy = false; //Is this NPC an enemy? If false, then it's friendly

	public NPC() {
	} //Default constructor

	//Copy constructor, used to copy it from the default NPC list
	public NPC(NPC npc) {
		this.atlasID = npc.atlasID;
		this.xPos = npc.xPos;
		this.yPos = npc.yPos;
		this.isRendered = npc.isRendered;
		this.isEnemy = npc.isEnemy;
	}

	public static void initNPCTypes() {
		//Initialize the default NPC ID-lookup table
		NPC_TYPES = new ArrayList<>();
		NPC_TYPES.add(new NothingNPC());
		NPC_TYPES.add(new SmileFriendlyNPC());
		NPC_TYPES.add(new FrownEnemyNPC());
		NPC_TYPES.add(new TestEnemyNPC());
	}

	public void update() {
	} //Default update method, called every frame

	public void dispose() {
	} //Default dispose method, called when the NPC is destroyed

	//Default onPlayerTakeStep method, called when the player takes a step
	public void onPlayerTakeStep(Player player, NPC[] loadedNPCs, Level level) {
		//Store the current position for later
		int oldX = xPos;
		int oldY = yPos;

		//Move towards the player
		if (xPos < player.xPos) xPos++;
		if (xPos > player.xPos) xPos--;
		if (yPos < player.yPos) yPos++;
		if (yPos > player.yPos) yPos--;

		//Loop through all of the NPCs
		for (int i = 0; i < loadedNPCs.length; i++) {
			NPC npc = loadedNPCs[i];
			if ((npc != this) && !(npc instanceof NothingNPC)) {
				//If the NPC isn't this one, and it isn't a "null" NPC, check for collision with this other NPC
				if ((npc.xPos == xPos) && (npc.yPos == yPos)) {
					//If collided, reset the position to the old position
					xPos = oldX;
					yPos = oldY;
				}
			}
		}

		//Crude collision with solid tiles
		if (level.getTile(xPos, oldY).isSolid) { //X direction
			xPos = oldX;
		}
		if (level.getTile(oldX, yPos).isSolid) { //Y direction
			yPos = oldY;
		}
		if (level.getTile(xPos, yPos).isSolid) { //Both directions
			xPos = oldX;
			yPos = oldY;
		}
	}

	public NPC copy() {
		return new NPC(this);
	}
}
