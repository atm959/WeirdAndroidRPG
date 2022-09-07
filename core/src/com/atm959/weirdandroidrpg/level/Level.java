package com.atm959.weirdandroidrpg.level;

import com.atm959.weirdandroidrpg.level.tiles.AirTile;
import com.atm959.weirdandroidrpg.level.tiles.FloorTile;
import com.atm959.weirdandroidrpg.level.tiles.Tile;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by atm959 on 3/23/2022.
 */

//The level class
public class Level {
	public static int tileSize; //The size of the tiles on screen, in pixels

	public int scrollX = 0, scrollY = 0; //The level scroll position
	public Tile[][] tiles; //The tiles that make up the level

	private final Texture tileset; //The level tileset texture
	private final SpriteBatch sb; //The sprite batch that will be used to render the level

	//Initialize the level
	public Level() {
		//Load the level from a Tiled level file; will be replaced with something else later
		TiledMap tiledMap = new TmxMapLoader().load("level/testmap.tmx");
		MapLayers mapLayers = tiledMap.getLayers();
		TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) mapLayers.get(0);
		tiles = new Tile[64][64];
		for (int x = 0; x < 64; x++) {
			for (int y = 0; y < 64; y++) {
				TiledMapTileLayer.Cell cell = tiledMapTileLayer.getCell(x, 63 - y);
				if (cell != null) {
					TiledMapTile tile = cell.getTile();
					int tileID = tile.getId();
					tiles[x][y] = Tile.TILE_TYPES.get(tileID).copy();
					if(tiles[x][y] instanceof FloorTile){
						double r = Math.random();
						if(r > 0.5d) tiles[x][y].atlasID = 7;
					}
				} else {
					tiles[x][y] = new AirTile();
				}
			}
		}

		tileset = new Texture("level/tileset.png"); //Load the tileset texture
		sb = new SpriteBatch(); //Initialize the sprite batch
	}

	//Called every frame
	public void update() {
	}

	//Get a tile from the level
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	//Set a level tile
	public void setTile(int x, int y, Tile tile) {
		tiles[x][y] = tile;
	}

	//Render the level
	public void render() {
		sb.begin(); //Begin the sprite batch

		//Loop through all of the tiles
		for (int x = 0; x < 64; x++) {
			for (int y = 0; y < 64; y++) {
				Tile tile = tiles[x][y]; //Get a reference to the tile

				//Check if its render flag is set
				if (tile.isRendered) {
					//If so:
					//Get its graphic's position in the texture atlas
					int srcX = (tile.atlasID % 16) * 16;
					int srcY = (tile.atlasID / 16) * 16;

					//Draw it based on that
					sb.draw(tileset, (x * tileSize) - scrollX, Util.convertY((y * tileSize) - scrollY, tileSize), tileSize, tileSize, srcX, srcY, 16, 16, false, false);
				}
			}
		}

		sb.end(); //End the sprite batch
	}

	public void dispose() {
		tileset.dispose(); //Dispose of the tileset texture
		sb.dispose(); //Dispose of the sprite batch
	}
}
