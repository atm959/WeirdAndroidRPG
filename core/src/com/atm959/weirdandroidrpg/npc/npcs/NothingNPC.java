package com.atm959.weirdandroidrpg.npc.npcs;

/**
 * Created by atm959 on 4/8/2022.
 */

//A "nothing NPC", an NPC that is invisible and doesn't do anything
public class NothingNPC extends NPC {
	//Initialize it
	public NothingNPC() {
		this.atlasID = 0;
		this.isRendered = false;
	}

	//Copy constructor
	public NothingNPC(NothingNPC npc) {
		super(npc);
	}

	@Override
	public NothingNPC copy() {
		return new NothingNPC(this);
	}
}
