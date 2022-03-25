package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.global.Global;
import com.atm959.weirdandroidrpg.input.DPad;
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
    private DPad dPad;

    public InGameState(){
        Tile.InitTileTypes();
        level = new Level();
        player = new Player();
        player.xPos = 4;
        player.yPos = 8;
        dPad = new DPad();
    }

    @Override
    public void Run(){
        dPad.Update();
        level.Update();
        player.Update(level, dPad);

        level.Render();
        player.Render(level);

        dPad.Render();

        Global.textRenderer.RenderString(IN_GAME_TEXT, 0, 0, TextRenderer.TEXTSCALE_LARGE);
    }

    @Override
    public void Dispose(){
        level.Dispose();
    }
}
