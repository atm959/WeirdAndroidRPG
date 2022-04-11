package com.atm959.weirdandroidrpg.npc.npcs;

/**
 * Created by atm959 on 4/8/2022.
 */
public class NothingNPC extends NPC {
    public NothingNPC(){
        this.atlasID = 0;
        this.isRendered = false;
    }

    public NothingNPC(NothingNPC npc){
        super(npc);
    }
    @Override
    public NothingNPC copy(){
        return new NothingNPC(this);
    }
}
