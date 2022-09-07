package com.atm959.weirdandroidrpg.level.tiles;

/**
 * Created by atm959 on 3/24/2022.
 */
public class DoorTile extends Tile {
    public DoorTile(){
        this.atlasID = 2;
        this.isSolid = true;
    }

    public DoorTile(DoorTile tile){
        super(tile);
    }
    @Override
    public DoorTile copy(){
        return new DoorTile(this);
    }
}
