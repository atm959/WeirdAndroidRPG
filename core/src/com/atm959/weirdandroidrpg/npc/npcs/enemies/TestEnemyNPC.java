package com.atm959.weirdandroidrpg.npc.npcs.enemies;

import com.atm959.weirdandroidrpg.npc.npcs.NPC;

/**
 * Created by atm959 on 4/6/2022.
 */
public class TestEnemyNPC extends NPC {
    public TestEnemyNPC(){
        this.atlasID = 2;
        this.isEnemy = true;
    }

    public TestEnemyNPC(TestEnemyNPC npc){
        super(npc);
    }
    @Override
    public TestEnemyNPC copy(){
        return new TestEnemyNPC(this);
    }
}
