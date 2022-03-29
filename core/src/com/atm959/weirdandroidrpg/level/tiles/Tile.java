package com.atm959.weirdandroidrpg.level.tiles;

import com.badlogic.gdx.Gdx;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by atm959 on 3/24/2022.
 */
public class Tile implements Serializable {
    public static ArrayList<Tile> TILE_TYPES;
    public static void InitTileTypes(){
        TILE_TYPES = new ArrayList<Tile>();
        TILE_TYPES.add(new AirTile());
        TILE_TYPES.add(new FloorTile());
        TILE_TYPES.add(new WallTile());
        TILE_TYPES.add(new DoorTile());
        TILE_TYPES.add(new DownLadderTile());
        TILE_TYPES.add(new UpLadderTile());
    }

    public int atlasID = 0;
    public boolean isSolid = false;
    public boolean isRendered = true;

    public Tile(){}
    public void update(){}
    public void dispose(){}
    public void onPlayerWalkInto(){}
    public void onPlayerWalkOutOf(){}

    public Tile(Tile tile){
        this.atlasID = tile.atlasID;
        this.isSolid = tile.isSolid;
        this.isRendered = tile.isRendered;
    }
    public Tile copy(){
        return new Tile(this);
    }
}
