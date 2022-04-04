package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.time.Time;
import com.atm959.weirdandroidrpg.input.TouchInput;
import com.atm959.weirdandroidrpg.items.items.NothingItem;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.level.tiles.DoorTile;
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

    private Texture mapBG;

    private Texture bgTex;
    private SpriteBatch bgSB;
    float bgOffsetX = 0.0f, bgOffsetY = 0.0f;

    public ViewMapState(Level level, Player player){
        this.level = level;
        this.player = player;

        sb = new SpriteBatch();
        mapPixels = new Pixmap(64, 64, Pixmap.Format.RGBA8888);

        playerPixelTimer = 0.0f;

        mapBG = new Texture("ui/mapBG.png");

        bgTex = new Texture("title/optionsBg.png");
        bgSB = new SpriteBatch();
    }

    @Override
    public void run(){
        bgOffsetX += 0.4f * Time.deltaTime;
        bgOffsetY += 0.3f * Time.deltaTime;
        int width = Gdx.graphics.getWidth() / 3;
        int numY = Gdx.graphics.getHeight() / width;
        bgSB.begin();
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < (numY + 2); y++){
                //noinspection SuspiciousNameCombination
                bgSB.draw(bgTex, (x * width) - ((int)bgOffsetX % width), Util.convertY((y * width) - ((int)bgOffsetY % width), width), width, width);
            }
        }
        bgSB.end();

        TextRenderer.renderString("VIEW MAP ", 0, 0, TextRenderer.TEXTSCALE_LARGE);
        TextRenderer.renderString("TAP TO EXIT", 0, TextRenderer.TEXTSCALE_LARGE, TextRenderer.TEXTSCALE_LARGE);

        int color;
        for(int x = 0; x < 64; x++){
            for(int y = 0; y < 64; y++){
                Tile tile = level.getTile(x, y);
                if(tile instanceof FloorTile) {
                    if(tile.atlasID == 255){
                        color = 0x606060FF;
                    } else {
                        color = 0x808080FF;
                    }
                } else if (tile instanceof WallTile){
                    color = 0xFFFFFFFF;
                } else if(tile instanceof DoorTile){
                    color = 0x00FF00FF;
                }
                else {
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
        int bgYPos = (Gdx.graphics.getHeight() / 2) - ((8 * Level.TILE_SIZE) / 2);
        sb.draw(mapBG, 0, Util.convertY((int)bgYPos, 8 * Level.TILE_SIZE), 8 * Level.TILE_SIZE, 8 * Level.TILE_SIZE);
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
        mapBG.dispose();
        bgTex.dispose();
        bgSB.dispose();
    }
}
