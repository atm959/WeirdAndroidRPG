package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.global.Global;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.level.tiles.Tile;
import com.atm959.weirdandroidrpg.player.Player;
import com.atm959.weirdandroidrpg.text.TextRenderer;

/**
 * Created by atm959 on 3/23/2022.
 */
public class InGameState extends GameState {
    private static final String IN_GAME_TEXT = "IN GAME";
    private Level level;
    private Player player;

    public InGameState(){
        Tile.InitTileTypes();
        level = new Level();
        player = new Player();
        player.xPos = 4;
        player.yPos = 8;
    }

    @Override
    public void Run(){
        level.Update();
        level.Render();
        player.Render(level);
        Global.textRenderer.RenderString(IN_GAME_TEXT, 0, 0, TextRenderer.TEXTSCALE_LARGE);
    }

    @Override
    public void Dispose(){
        level.Dispose();
    }
}
