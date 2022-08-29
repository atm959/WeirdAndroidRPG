package com.atm959.weirdandroidrpg.level.tiles;

/**
 * Created by atm959 on 3/24/2022.
 */
public class UpLadderTile extends Tile {
	public UpLadderTile() {
		this.atlasID = 4;
	}

	public UpLadderTile(UpLadderTile tile) {
		super(tile);
	}

	@Override
	public UpLadderTile copy() {
		return new UpLadderTile(this);
	}
}
