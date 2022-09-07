package com.atm959.weirdandroidrpg.npc.npcs.friendly;

import com.atm959.weirdandroidrpg.npc.npcs.NPC;

/**
 * Created by atm959 on 4/6/2022.
 */

//A friendly test NPC
public class SmileFriendlyNPC extends NPC {
	//Constructor
    public SmileFriendlyNPC(){
        this.atlasID = 0;
    }

	//Copy contructor
    public SmileFriendlyNPC(SmileFriendlyNPC npc){
        super(npc);
    }
    @Override
    public SmileFriendlyNPC copy(){
        return new SmileFriendlyNPC(this);
    }
}
