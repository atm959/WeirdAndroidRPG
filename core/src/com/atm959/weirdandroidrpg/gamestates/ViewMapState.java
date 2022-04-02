package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.global.Global;
import com.atm959.weirdandroidrpg.global.Time;
import com.atm959.weirdandroidrpg.input.TouchInput;
import com.atm959.weirdandroidrpg.items.items.NothingItem;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.level.tiles.FloorTile;
import com.atm959.weirdandroidrpg.level.tiles.Tile;
import com.atm959.weirdandroidrpg.level.tiles.WallTile;
import com.atm959.weirdandroidrpg.player.Player;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/31/2022.
 */
public class ViewMapState extends GameState {
    private Level level;
    private Player player;

    private Texture texture;
    private SpriteBatch sb;
    private Pixmap mapPixels;
    private float playerPixelTimer;
    private boolean playerPixelIsGreen = false;

    public ViewMapState(Level level, Player player){
        this.level = level;
        this.player = player;

        sb = new SpriteBatch();
        mapPixels = new Pixmap(64, 64, Pixmap.Format.RGBA8888);

        playerPixelTimer = 0.0f;
    }

    @Override
    public void run(){
        Global.textRenderer.renderString("VIEW MAP ", 0, 0, TextRenderer.TEXTSCALE_LARGE);
        Global.textRenderer.renderString("TAP TO EXIT", 0, TextRenderer.TEXTSCALE_LARGE, TextRenderer.TEXTSCALE_LARGE);

        int color;
        for(int x = 0; x < 64; x++){
            for(int y = 0; y < 64; y++){
                Tile tile = level.getTile(x, y);
                if(tile instanceof FloorTile) {
                    color = 0x808080FF;
                } else if (tile instanceof WallTile){
                    color = 0xFFFFFFFF;
                } else {
                    color = 0x00000000;
                }
                mapPixels.drawPixel(x, y, color);
            }
        }

        for(int i = 0; i < 256; i++){
            if(!(level.itemsOnGround[i] instanceof NothingItem)){
                mapPixels.drawPixel(level.itemsOnGround[i].xPos, level.itemsOnGround[i].yPos, 0x0000FFFF);
            }
        }

        playerPixelTimer += 2.5f * Time.deltaTime;
        if(playerPixelTimer > 500.0f){
            playerPixelIsGreen = !playerPixelIsGreen;
            playerPixelTimer = 0.0f;
        }

        int playerPixelColor = 0xFF0000FF;
        if(playerPixelIsGreen){
            playerPixelColor = 0x00FF00FF;
        }
        mapPixels.drawPixel(player.xPos, player.yPos, playerPixelColor);

        if(texture != null) texture.dispose();
        texture = new Texture(mapPixels);

        sb.begin();
        int yPos = (Gdx.graphics.getHeight() / 2) - ((7 * Level.TILE_SIZE) / 2);
        sb.draw(texture, 0.5f * Level.TILE_SIZE, Util.convertY((int)yPos, 7 * Level.TILE_SIZE), 7 * Level.TILE_SIZE, 7 * Level.TILE_SIZE);
        sb.end();

        if(TouchInput.touched){
            StateManager.popState();
        }
    }

    @Override
    public void dispose(){
        texture.dispose();
        sb.dispose();
        mapPixels.dispose();
    }
}
