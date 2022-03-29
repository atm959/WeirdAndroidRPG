package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.audio.BGM;
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
        BGM.playSong(1);

        Tile.InitTileTypes();
        level = new Level();
        player = new Player();
        player.xPos = 4;
        player.yPos = 8;
        dPad = new DPad();
    }

    @Override
    public void run(){
        dPad.update();
        level.update();
        if(dPad.directionIsPressed) player.takeStep(level, dPad);
        player.update(level);

        level.render();
        player.render(level);

        dPad.render();

        Global.textRenderer.renderString(IN_GAME_TEXT, 0, 0, TextRenderer.TEXTSCALE_LARGE);
    }

    @Override
    public void dispose(){
        level.dispose();
        player.dispose();
        dPad.dispose();
    }
}
