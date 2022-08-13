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
public class NPC {
    public static ArrayList<NPC> NPC_TYPES;
    public static void InitNPCTypes(){
        NPC_TYPES = new ArrayList<>();
        NPC_TYPES.add(new NothingNPC());
        NPC_TYPES.add(new SmileFriendlyNPC());
        NPC_TYPES.add(new FrownEnemyNPC());
        NPC_TYPES.add(new TestEnemyNPC());
    }

    public int atlasID = 0;
    public int xPos = 0, yPos = 0;
    public boolean isRendered = true;

    public boolean isEnemy = false;

    public NPC(){}
    public void update(){}
    public void dispose(){}

    public void onPlayerTakeStep(Player player, NPC[] loadedNPCs, Level level){
        int oldX = xPos;
        int oldY = yPos;

        if(xPos < player.xPos) xPos++;
        if(xPos > player.xPos) xPos--;
        if(yPos < player.yPos) yPos++;
        if(yPos > player.yPos) yPos--;

        for(int i = 0; i < loadedNPCs.length; i++){
            NPC npc = loadedNPCs[i];
            if((npc != this) && !(npc instanceof NothingNPC)) {
                if ((npc.xPos == xPos) && (npc.yPos == yPos)) {
                    xPos = oldX;
                    yPos = oldY;
                }
            }
        }

		//Crude collision with solid tiles
		if(level.getTile(xPos, oldY).isSolid){
			xPos = oldX;
		}
		if(level.getTile(oldX, yPos).isSolid){
			yPos = oldY;
		}
		if(level.getTile(xPos, yPos).isSolid){
			xPos = oldX;
			yPos = oldY;
		}
    }

    public NPC(NPC npc){
        this.atlasID = npc.atlasID;
        this.xPos = npc.xPos;
        this.yPos = npc.yPos;
        this.isRendered = npc.isRendered;
        this.isEnemy = npc.isEnemy;
    }
    public NPC copy(){
        return new NPC(this);
    }
}
