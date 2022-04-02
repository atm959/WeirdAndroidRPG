package com.atm959.weirdandroidrpg.level;

import com.atm959.weirdandroidrpg.items.items.Item;
import com.atm959.weirdandroidrpg.level.tiles.AirTile;
import com.atm959.weirdandroidrpg.level.tiles.Tile;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.Random;

/**
 * Created by atm959 on 3/23/2022.
 */
public class Level {
    public static final int TILE_SIZE = (Gdx.graphics.getWidth() / 8);

    public int scrollX = 0, scrollY = 0;
    public Tile[][] tiles;
    public Item[] itemsOnGround;

    private Texture tileset;
    private SpriteBatch sb;

    public Level(){
        TiledMap tiledMap = new TmxMapLoader().load("level/testmap.tmx");
        MapLayers mapLayers =  tiledMap.getLayers();
        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) mapLayers.get(0);

        tiles = new Tile[64][64];
        for(int x = 0; x < 64; x++){
            for(int y = 0; y < 64; y++){
                TiledMapTileLayer.Cell cell = tiledMapTileLayer.getCell(x, 64 - y);
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

        itemsOnGround = new Item[256];
        for(int i = 0; i < 256; i++){
            int random = new Random().nextInt(4);
            itemsOnGround[i] = Item.ITEM_TYPES.get(random).copy();
            while(getTile(itemsOnGround[i].xPos, itemsOnGround[i].yPos).isSolid){
                itemsOnGround[i].xPos = new Random().nextInt(64);
                itemsOnGround[i].yPos = new Random().nextInt(64);
            }
        }
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
                    sb.draw(tileset, (x * TILE_SIZE) - scrollX, Util.convertY((y * TILE_SIZE) - scrollY, TILE_SIZE), TILE_SIZE, TILE_SIZE, srcX, srcY, 16, 16, false, false);
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
