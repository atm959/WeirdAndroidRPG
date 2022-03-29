package com.atm959.weirdandroidrpg.items;

/**
 * Created by atm959 on 3/24/2022.
 */
public class Item {
    public int atlasID = 0;
    public int xPos = 0, yPos = 0;
    public boolean isRendered = true;

    public boolean isWeapon = false;
    public int weaponLevel = 0;
    public float durability = 0.0f;

    public Item(){}
    public void update(){}
    public void dispose(){}
    public void onPickup(){}
    public void onUse(){}
}
