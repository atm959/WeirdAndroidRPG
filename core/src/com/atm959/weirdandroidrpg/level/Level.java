package com.atm959.weirdandroidrpg.level;

import com.atm959.weirdandroidrpg.level.tiles.AirTile;
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

//TODO: Continue commenting from this point tomorrow
public class Level {
    public static int tileSize;

    public int scrollX = 0, scrollY = 0;
    public Tile[][] tiles;

    private Texture tileset;
    private SpriteBatch sb;

    public Level(){
        TiledMap tiledMap = new TmxMapLoader().load("level/testmap.tmx");
        MapLayers mapLayers =  tiledMap.getLayers();
        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) mapLayers.get(0);

        tiles = new Tile[64][64];
        for(int x = 0; x < 64; x++){
            for(int y = 0; y < 64; y++){
                TiledMapTileLayer.Cell cell = tiledMapTileLayer.getCell(x, 63 - y);
                if(cell != null) {
                    TiledMapTile tile = cell.getTile();
                    int tileID = tile.getId();
                    tiles[x][y] = Tile.TILE_TYPES.get(tileID).copy();
                } else {
                    tiles[x][y] = new AirTile();
                }
            }
        }
        tileset = new Texture("level/tileset.png");
        sb = new SpriteBatch();
    }

    public void update(){}

    public Tile getTile(int x, int y){
        return tiles[x][y];
    }

    public void setTile(int x, int y, Tile tile){
        tiles[x][y] = tile;
    }

    public void render(){
        sb.begin();
        for(int x = 0; x < 64; x++){
            for(int y = 0; y < 64; y++){
                Tile tile = tiles[x][y];
                if(tile.isRendered) {
                    int srcX = (tile.atlasID % 16) * 16;
                    int srcY = (tile.atlasID / 16) * 16;
                    sb.draw(tileset, (x * tileSize) - scrollX, Util.convertY((y * tileSize) - scrollY, tileSize), tileSize, tileSize, srcX, srcY, 16, 16, false, false);
                }
            }
        }
        sb.end();
    }

    public void dispose(){
        tileset.dispose();
        sb.dispose();
    }
}
