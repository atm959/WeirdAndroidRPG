package com.atm959.weirdandroidrpg.items;

import com.atm959.weirdandroidrpg.items.items.Item;
import com.atm959.weirdandroidrpg.level.Level;
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

    public void renderLevelItems(int scrollX, int scrollY, Item[] loadedItems){
        sb.begin();
        for(int i = 0; i < loadedItems.length; i++){
            Item item = loadedItems[i];
            if(item.isRendered) {
                int srcX = (item.atlasID % 16) * 16;
                int srcY = (item.atlasID / 16) * 16;
                sb.draw(itemAtlas, (item.xPos * Level.tileSize) - scrollX, Util.convertY((item.yPos * Level.tileSize) - scrollY, Level.tileSize), Level.tileSize, Level.tileSize, srcX, srcY, 16, 16, false, false);
            }
        }
        sb.end();
    }
}
