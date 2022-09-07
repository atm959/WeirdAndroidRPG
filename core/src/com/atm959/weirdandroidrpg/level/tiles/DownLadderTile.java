package com.atm959.weirdandroidrpg.level.tiles;

/**
 * Created by atm959 on 3/24/2022.
 */
public class DownLadderTile extends Tile {
    public DownLadderTile(){
        this.atlasID = 3;
    }

    public DownLadderTile(DownLadderTile tile){
        super(tile);
    }
    @Override
    public DownLadderTile copy(){
        return new DownLadderTile(this);
    }
}
