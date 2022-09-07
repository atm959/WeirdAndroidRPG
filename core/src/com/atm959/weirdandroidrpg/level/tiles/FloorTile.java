package com.atm959.weirdandroidrpg.level.tiles;

/**
 * Created by atm959 on 3/24/2022.
 */
public class FloorTile extends Tile {
<<<<<<< HEAD
	public FloorTile() {
		this.atlasID = 6;
	}
=======
    public FloorTile(){
        this.atlasID = 0;
    }
>>>>>>> parent of e187758 (Add a heartbeat system to the server connection)

    @Override
    public void onPlayerWalkInto(){
        this.atlasID = 255;
    }

<<<<<<< HEAD
	@Override
	public FloorTile copy() {
		return new FloorTile(this);
	}
=======
    public FloorTile(FloorTile tile){
        super(tile);
    }
    @Override
    public FloorTile copy(){
        return new FloorTile(this);
    }
>>>>>>> parent of e187758 (Add a heartbeat system to the server connection)
}
