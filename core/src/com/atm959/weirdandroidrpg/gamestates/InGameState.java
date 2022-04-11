package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.audio.BGM;
import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.input.DPad;
import com.atm959.weirdandroidrpg.items.ItemRenderer;
import com.atm959.weirdandroidrpg.items.items.Item;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.npc.NPCRenderer;
import com.atm959.weirdandroidrpg.npc.npcs.NPC;
import com.atm959.weirdandroidrpg.player.Player;
import com.atm959.weirdandroidrpg.time.Time;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

/**
 * Created by atm959 on 3/23/2022.
 */
public class InGameState extends GameState {
    private Level level;
    private Item[] itemsOnGround;
    private NPC[] npcsInLevel;
    private float npcFlippedTimer;
    private boolean npcsAreFlipped;
    private ItemRenderer itemRenderer;
    private NPCRenderer npcRenderer;
    private Player player;
    private DPad dPad;
    private Button mapButton;
    private Button menuButton;

    private float m_fboScaler = 1.5f;
    private FrameBuffer m_fbo;
    private TextureRegion m_fboRegion;
    private SpriteBatch sb;

    private ShaderProgram shader;
    private Texture dudvMap;
    private float time;

    private static final String VERT =
            "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
            "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" +
            "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" +
            "uniform mat4 u_projTrans;\n" +
            " \n" +
            "varying vec4 vColor;\n" +
            "varying vec2 vTexCoord;\n" +
            "void main() {\n" +
            "	vColor = "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
            "	vTexCoord = "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
            "	gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
            "}";

    private static final String FRAG =
            //GL ES specific stuff
            "#ifdef GL_ES\n" //
            + "#define LOWP lowp\n" //
            + "precision mediump float;\n" //
            + "#else\n" //
            + "#define LOWP \n" //
            + "#endif\n" + //
            "varying LOWP vec4 vColor;\n" +
            "varying vec2 vTexCoord;\n" +
            "uniform sampler2D u_texture;\n" +
            "uniform sampler2D u_dudvMap;\n" +
            "uniform lowp float u_time;\n" +
            "void main(void) {\n" +
            "   vec4 dudvColor = texture2D(u_dudvMap, vec2(vTexCoord.x + mod(u_time, 1.0), vTexCoord.y + mod(u_time, 1.0)));\n" +
            "   vec4 dudvColor2 = texture2D(u_dudvMap, vec2(vTexCoord.x + mod(u_time, 1.0), vTexCoord.y));\n" +
            "   vec4 finalDudv = mix(dudvColor, dudvColor2, 0.5);\n" +
            "   vec2 distortion = vec2((finalDudv.r * 2.0) - 1.0, (finalDudv.g * 2.0) - 1.0) * 0.01;\n" +
            "   vec2 newTexCoord = vec2(vTexCoord.x + distortion.x, vTexCoord.y + distortion.y);\n" +
            "   vec4 newColor = texture2D(u_texture, newTexCoord);\n" +
            "   newColor *= vec4(1.0, 1.0, 2.0, 1.0);\n" +
            "	gl_FragColor = vColor * newColor;\n" +
            "}";

    public InGameState(){
        BGM.playSong(1);

        level = new Level();
        itemsOnGround = new Item[256];
        for(int i = 0; i < 256; i++){
            int random = new Random().nextInt(4);
            itemsOnGround[i] = Item.ITEM_TYPES.get(random).copy();
            while(level.getTile(itemsOnGround[i].xPos, itemsOnGround[i].yPos).isSolid){
                itemsOnGround[i].xPos = new Random().nextInt(64);
                itemsOnGround[i].yPos = new Random().nextInt(64);
            }
        }
        npcsInLevel = new NPC[256];
        for(int i = 0; i < 256; i++){
            int random = new Random().nextInt(4);
            npcsInLevel[i] = NPC.NPC_TYPES.get(random).copy();
            while(level.getTile(npcsInLevel[i].xPos, npcsInLevel[i].yPos).isSolid){
                npcsInLevel[i].xPos = new Random().nextInt(64);
                npcsInLevel[i].yPos = new Random().nextInt(64);
            }
        }
        itemRenderer = new ItemRenderer();
        npcRenderer = new NPCRenderer();
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

        m_fbo = new FrameBuffer(Pixmap.Format.RGB565, (int)(Gdx.graphics.getWidth() * m_fboScaler), (int)(Gdx.graphics.getHeight() * m_fboScaler), false);
        m_fboRegion = new TextureRegion(m_fbo.getColorBufferTexture());
        m_fboRegion.flip(false, true);
        sb = new SpriteBatch();

        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(VERT, FRAG);
        if (!shader.isCompiled()) {
            System.err.println(shader.getLog());
            System.exit(0);
        }
        if (shader.getLog().length() != 0) System.out.println(shader.getLog());

        dudvMap = new Texture("level/dudvMap.png");
        dudvMap.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        dudvMap.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void run(){
        dPad.update();
        level.update();
        if(dPad.directionIsPressed) player.takeStep(level, dPad, npcsInLevel);
        player.update(level);

        mapButton.update();
        menuButton.update();
        if(mapButton.isPressed){
            StateManager.pushState(new ViewMapState(level, player, itemsOnGround, npcsInLevel));
        }
        if(menuButton.isPressed){
            StateManager.replaceCurrentState(new TitleState());
        }

        m_fbo.begin();
        ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1.0f);
        level.render();
        itemRenderer.renderLevelItems(level.scrollX, level.scrollY, itemsOnGround);
        npcFlippedTimer += 2.5f * Time.deltaTime;
        if(npcFlippedTimer > 500.0f){
            npcsAreFlipped = !npcsAreFlipped;
            npcFlippedTimer = 0.0f;
        }
        npcRenderer.setNpcsAreFlipped(npcsAreFlipped);
        npcRenderer.renderLevelNPCs(level.scrollX, level.scrollY, npcsInLevel);
        player.render(level);
        m_fbo.end();

        dudvMap.bind(1);
        Gdx.gl.glActiveTexture(Gdx.gl.GL_TEXTURE0);

        sb.setShader(shader);
        sb.begin();
        shader.setUniformi("u_dudvMap", 1);
        shader.setUniformf("u_time", time);
        sb.draw(m_fboRegion, 0, Util.convertY(0, Gdx.graphics.getHeight()), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();

        dPad.render();
        mapButton.render();
        menuButton.render();

        time += 0.00005f * Time.deltaTime;
        if(time > 1.0f) time = 0.0f;
    }

    @Override
    public void dispose(){
        level.dispose();
        player.dispose();
        dPad.dispose();
        m_fbo.dispose();
        sb.dispose();
    }
}
