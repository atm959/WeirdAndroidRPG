package com.atm959.weirdandroidrpg.items.items;

/**
 * Created by atm959 on 3/24/2022.
 */
public class CrystalItem extends Item {
    public CrystalItem(){
        this.atlasID = 2;
    }

    public CrystalItem(CrystalItem item){
        super(item);
    }
    @Override
    public CrystalItem copy(){
        return new CrystalItem(this);
    }
}
