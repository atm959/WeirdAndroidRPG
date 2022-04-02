package com.atm959.weirdandroidrpg.items;

import com.atm959.weirdandroidrpg.items.items.Item;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.level.tiles.Tile;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/24/2022.
 */
public class ItemRenderer {
    private Texture itemAtlas;
    private SpriteBatch sb;

    public ItemRenderer(){
        itemAtlas = new Texture("level/items.png");
        sb = new SpriteBatch();
    }

    public void renderLevelItems(Level level){
        sb.begin();
        for(int i = 0; i < 256; i++){
            Item item = level.itemsOnGround[i];
            if(item.isRendered) {
                int srcX = (item.atlasID % 16) * 16;
                int srcY = (item.atlasID / 16) * 16;
                sb.draw(itemAtlas, (item.xPos * Level.TILE_SIZE) - level.scrollX, Util.convertY((item.yPos * Level.TILE_SIZE) - level.scrollY, Level.TILE_SIZE), Level.TILE_SIZE, Level.TILE_SIZE, srcX, srcY, 16, 16, false, false);
            }
        }
        sb.end();
    }
}
