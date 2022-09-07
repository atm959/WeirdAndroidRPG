package com.atm959.weirdandroidrpg.level.tiles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by atm959 on 3/24/2022.
 */
public class Tile implements Serializable {
	public static ArrayList<Tile> TILE_TYPES;
	public int atlasID = 0;
	public boolean isSolid = false;
	public boolean isRendered = true;
	public Tile() {
	}

	public Tile(Tile tile) {
		this.atlasID = tile.atlasID;
		this.isSolid = tile.isSolid;
		this.isRendered = tile.isRendered;
	}

	public static void initTileTypes() {
		TILE_TYPES = new ArrayList<>();
		TILE_TYPES.add(new AirTile());
		TILE_TYPES.add(new FloorTile());
		TILE_TYPES.add(new WallTile());
		TILE_TYPES.add(new DoorTile());
		TILE_TYPES.add(new DownLadderTile());
		TILE_TYPES.add(new UpLadderTile());
		TILE_TYPES.add(new FloorTile());
		TILE_TYPES.get(6).atlasID = 5;
	}

	public void update() {
	}

	public void dispose() {
	}

	public void onPlayerWalkInto() {
	}

	public void onPlayerWalkOutOf() {
	}

	public Tile copy() {
		return new Tile(this);
	}
}
