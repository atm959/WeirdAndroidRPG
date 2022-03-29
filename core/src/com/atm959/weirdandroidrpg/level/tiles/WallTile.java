package com.atm959.weirdandroidrpg.level.tiles;

/**
 * Created by atm959 on 3/24/2022.
 */
public class WallTile extends Tile {
    public WallTile(){
        this.atlasID = 1;
        this.isSolid = true;
    }

    public WallTile(WallTile tile){
        super(tile);
    }
    @Override
    public WallTile copy(){
        return new WallTile(this);
    }
}
