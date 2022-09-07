package com.atm959.weirdandroidrpg.items.items;

import java.util.ArrayList;

/**
 * Created by atm959 on 3/24/2022.
 */
public class Item {
    public static ArrayList<Item> ITEM_TYPES;
    public static void InitItemTypes(){
        ITEM_TYPES = new ArrayList<>();
        ITEM_TYPES.add(new NothingItem());
        ITEM_TYPES.add(new SwordItem());
        ITEM_TYPES.add(new LevelPlusItem());
        ITEM_TYPES.add(new CrystalItem());
    }

    public int atlasID = 0;
    public int xPos = 0, yPos = 0;
    public boolean isRendered = true;

<<<<<<< HEAD
	public static void initItemTypes() {
		ITEM_TYPES = new ArrayList<>();
		ITEM_TYPES.add(new NothingItem());
		ITEM_TYPES.add(new SwordItem());
		ITEM_TYPES.add(new LevelPlusItem());
		ITEM_TYPES.add(new CrystalItem());
	}
=======
    public boolean isWeapon = false;
    public int weaponLevel = 0;
    public float durability = 0.0f;
>>>>>>> parent of e187758 (Add a heartbeat system to the server connection)

    public Item(){}
    public void update(){}
    public void dispose(){}
    public void onPickup(){}
    public void onUse(){}

    public Item(Item item){
        this.atlasID = item.atlasID;
        this.xPos = item.xPos;
        this.yPos = item.yPos;
        this.isRendered = item.isRendered;
        this.isWeapon = item.isWeapon;
        this.weaponLevel = item.weaponLevel;
        this.durability = item.durability;
    }
    public Item copy(){
        return new Item(this);
    }
}
