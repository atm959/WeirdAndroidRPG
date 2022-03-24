package com.atm959.weirdandroidrpg.level;

import com.atm959.weirdandroidrpg.global.Global;
import com.atm959.weirdandroidrpg.items.Item;
import com.atm959.weirdandroidrpg.level.tiles.AirTile;
import com.atm959.weirdandroidrpg.level.tiles.FloorTile;
import com.atm959.weirdandroidrpg.level.tiles.Tile;
import com.atm959.weirdandroidrpg.level.tiles.WallTile;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by atm959 on 3/23/2022.
 */
public class Level {
    public static final int TILE_SIZE = (Gdx.graphics.getWidth() / 8);

    public int scrollX, scrollY;
    public Tile[][] tiles;
    public Item[] itemsOnGround;

    private Texture tileset;
    private SpriteBatch sb;

    public Level(){
        tiles = new Tile[64][64];
        for(int x = 0; x < 64; x++){
            for(int y = 0; y < 64; y++){
                int t = new Random().nextInt(6);
                tiles[x][y] = Tile.TILE_TYPES.get(t);
            }
        }
        tileset = new Texture("tileset.png");
        sb = new SpriteBatch();
    }

    public void Update(){
        //scrollX++;
    }

    public void Render(){
        sb.begin();
        for(int x = 0; x < 64; x++){
            for(int y = 0; y < 64; y++){
                Tile tile = tiles[x][y];
                if(tile.isRendered) {
                    int srcX = (tile.atlasID % 16) * 16;
                    int srcY = (tile.atlasID / 16) * 16;
                    sb.draw(tileset, (x * TILE_SIZE) - scrollX, Util.ConvertY((y * TILE_SIZE) - scrollY, TILE_SIZE), TILE_SIZE, TILE_SIZE, srcX, srcY, 16, 16, false, false);
                }
            }
        }
        sb.end();
    }

    public void Dispose(){
        tileset.dispose();
        sb.dispose();
    }
}
