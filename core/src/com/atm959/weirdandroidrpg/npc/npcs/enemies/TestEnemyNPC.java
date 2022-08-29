package com.atm959.weirdandroidrpg.npc.npcs.enemies;

import com.atm959.weirdandroidrpg.npc.npcs.NPC;

/**
 * Created by atm959 on 4/6/2022.
 */

//An enemy test NPC
public class TestEnemyNPC extends NPC {
	//Constructor
	public TestEnemyNPC() {
		this.atlasID = 2;
		this.isEnemy = true;
	}

	//Copy constructor
	public TestEnemyNPC(TestEnemyNPC npc) {
		super(npc);
	}

	@Override
	public TestEnemyNPC copy() {
		return new TestEnemyNPC(this);
	}
}
