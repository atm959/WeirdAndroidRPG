package com.atm959.weirdandroidrpg.npc.npcs.enemies;

import com.atm959.weirdandroidrpg.npc.npcs.NPC;

/**
 * Created by atm959 on 4/6/2022.
 */

//A frowning enemy test NPC
public class FrownEnemyNPC extends NPC {
	//Constructor
	public FrownEnemyNPC() {
		this.atlasID = 1;
		this.isEnemy = true;
	}

	//Copy constructor
	public FrownEnemyNPC(FrownEnemyNPC npc) {
		super(npc);
	}

	@Override
	public FrownEnemyNPC copy() {
		return new FrownEnemyNPC(this);
	}
}
