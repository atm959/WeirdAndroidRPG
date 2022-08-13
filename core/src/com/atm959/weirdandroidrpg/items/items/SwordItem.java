package com.atm959.weirdandroidrpg.items.items;

/**
 * Created by atm959 on 3/24/2022.
 */
public class SwordItem extends Item {
    public SwordItem(){
        this.atlasID = 0;
        this.isWeapon = true;
    }

    @Override
    public void onPickup(){

    }

    @Override
    public void onUse(){

    }

    public SwordItem(SwordItem item){
        super(item);
    }
    @Override
    public SwordItem copy(){
        return new SwordItem(this);
    }
}
