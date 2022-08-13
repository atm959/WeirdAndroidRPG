package com.atm959.weirdandroidrpg.npc;

import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.npc.npcs.NPC;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 4/6/2022.
 */
public class NPCRenderer {
    private Texture npcAtlas;
    private SpriteBatch sb;
    private boolean npcsAreFlipped;

    public NPCRenderer(){
        npcAtlas = new Texture("npc/npcs.png");
        sb = new SpriteBatch();
    }

    public void setNpcsAreFlipped(boolean npcsAreFlipped){
        this.npcsAreFlipped = npcsAreFlipped;
    }

    public void renderLevelNPCs(int scrollX, int scrollY, NPC[] loadedNPCs){
        sb.begin();
        for(int i = 0; i < loadedNPCs.length; i++){
            NPC npc = loadedNPCs[i];
            if(npc.isRendered) {
                int srcX = (npc.atlasID % 16) * 16;
                int srcY = (npc.atlasID / 16) * 16;
                sb.draw(npcAtlas, (npc.xPos * Level.TILE_SIZE) - scrollX, Util.convertY((npc.yPos * Level.TILE_SIZE) - scrollY, Level.TILE_SIZE), Level.TILE_SIZE, Level.TILE_SIZE, srcX, srcY, 16, 16, npcsAreFlipped, false);
            }
        }
        sb.end();
    }
}
