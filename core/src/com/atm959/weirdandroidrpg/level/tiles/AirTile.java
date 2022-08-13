package com.atm959.weirdandroidrpg.level.tiles;

/**
 * Created by atm959 on 3/24/2022.
 */
public class AirTile extends Tile {
    public AirTile(){
        this.isSolid = true;
        this.isRendered = false;
    }

    public AirTile(AirTile tile){
        super(tile);
    }
    @Override
    public AirTile copy(){
        return new AirTile(this);
    }
}
