package com.atm959.weirdandroidrpg.player;

import com.atm959.weirdandroidrpg.items.items.Item;

/**
 * Created by atm959 on 3/24/2022.
 */
public class Inventory {
    public Item[] mainSlots;
    public Item[] weaponSlots;
    public Item[] armorSlots;

    public Inventory(){
        mainSlots = new Item[4*6];
        weaponSlots = new Item[2];
        armorSlots = new Item[4];
    }

    public void dispose(){

    }
}
