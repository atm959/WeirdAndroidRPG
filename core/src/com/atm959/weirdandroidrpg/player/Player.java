package com.atm959.weirdandroidrpg.player;

import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/24/2022.
 */
public class Player {
    public int xPos, yPos;

    private Texture texture;
    private SpriteBatch sb;

    public Player(){
        texture = new Texture("player.png");
        sb = new SpriteBatch();
    }

    public void Update(){

    }

    public void Render(Level level){
        sb.begin();
        sb.draw(texture, (xPos * Level.TILE_SIZE) - level.scrollX, Util.ConvertY((yPos * Level.TILE_SIZE) - level.scrollY, Level.TILE_SIZE), Level.TILE_SIZE, Level.TILE_SIZE);
        sb.end();
    }

    public void Dispose(){
        texture.dispose();
        sb.dispose();
    }
}
