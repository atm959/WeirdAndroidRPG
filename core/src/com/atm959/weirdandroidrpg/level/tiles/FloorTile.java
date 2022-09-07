package com.atm959.weirdandroidrpg.level.tiles;

/**
 * Created by atm959 on 3/24/2022.
 */
public class FloorTile extends Tile {
	public FloorTile() {
		this.atlasID = 6;
	}

	public FloorTile(FloorTile tile) {
		super(tile);
	}

	@Override
	public FloorTile copy() {
		return new FloorTile(this);
	}
}