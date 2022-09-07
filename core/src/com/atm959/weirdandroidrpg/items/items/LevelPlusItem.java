package com.atm959.weirdandroidrpg.items.items;

/**
 * Created by atm959 on 3/24/2022.
 */
public class LevelPlusItem extends Item {
    public LevelPlusItem(){
        this.atlasID = 1;
    }

    public LevelPlusItem(LevelPlusItem item){
        super(item);
    }
    @Override
    public LevelPlusItem copy(){
        return new LevelPlusItem(this);
    }
}
