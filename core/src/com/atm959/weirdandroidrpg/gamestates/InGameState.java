package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.audio.BGM;
import com.atm959.weirdandroidrpg.global.Global;
import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.input.DPad;
import com.atm959.weirdandroidrpg.input.TouchInput;
import com.atm959.weirdandroidrpg.items.ItemRenderer;
import com.atm959.weirdandroidrpg.items.items.Item;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.level.tiles.Tile;
import com.atm959.weirdandroidrpg.player.Player;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.badlogic.gdx.Gdx;

/**
 * Created by atm959 on 3/23/2022.
 */
public class InGameState extends GameState {
    private Level level;
    private ItemRenderer itemRenderer;
    private Player player;
    private DPad dPad;
    private Button mapButton;
    private Button menuButton;

    public InGameState(){
        BGM.playSong(1);

        level = new Level();
        itemRenderer = new ItemRenderer();
        player = new Player();
        player.xPos = 4;
        player.yPos = 8;
        dPad = new DPad();

        mapButton = new Button("ui/mapButton.png");
        mapButton.xPos = 0;
        mapButton.yPos = 0;
        mapButton.width = 2 * Level.TILE_SIZE;
        mapButton.height = 2 * Level.TILE_SIZE;

        menuButton = new Button("ui/menuButton.png");
        menuButton.width = 2 * Level.TILE_SIZE;
        menuButton.xPos = Gdx.graphics.getWidth() - menuButton.width;
        menuButton.yPos = 0;
        menuButton.height = 2 * Level.TILE_SIZE;
    }

    @Override
    public void run(){
        dPad.update();
        level.update();
        if(dPad.directionIsPressed) player.takeStep(level, dPad);
        player.update(level);

        mapButton.update();
        menuButton.update();
        if(mapButton.isPressed){
            StateManager.pushState(new ViewMapState(level, player));
        }
        if(menuButton.isPressed){
            StateManager.replaceCurrentState(new TitleState());
        }

        level.render();
        itemRenderer.renderLevelItems(level);
        player.render(level);

        dPad.render();
        mapButton.render();
        menuButton.render();
    }

    @Override
    public void dispose(){
        level.dispose();
        player.dispose();
        dPad.dispose();
    }
}
