package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.input.Button;
import com.atm959.weirdandroidrpg.level.Level;
import com.atm959.weirdandroidrpg.sharingimage.SharingImage;
import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.swing.text.AttributeSet;

/**
 * Created by atm959 on 9/7/2022.
 * A 3D sub-game test.
 */
public class ThreeDimensionalSubgameTestState extends GameState {
	private PerspectiveCamera cam;
	private ModelBatch modelBatch;
	private Model model;
	private ModelInstance instance;
	private Texture tex;

	private Button backButton;
	private Button optionsButton;

	public ThreeDimensionalSubgameTestState(){
		modelBatch = new ModelBatch();

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(50.0f, 70.0f, 50.0f);
		cam.lookAt(0,35.0f,0);
		cam.near = 1f;
		cam.far = 3000f;
		cam.update();

		ModelBuilder modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(5.0f, 5.0f, 5.0f, new Material(ColorAttribute.createDiffuse(Color.WHITE)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
		instance = new ModelInstance(model);

		tex = new Texture("ui/smile.png");

		backButton = new Button("ui/menuButton.png");
		backButton.xPos = Gdx.graphics.getWidth() - (Level.tileSize * 2);
		backButton.yPos = 0;
		backButton.width = (2 * Level.tileSize);
		backButton.height = (2 * Level.tileSize);

		optionsButton = new Button("ui/optionsButton.png");
		optionsButton.xPos = Gdx.graphics.getWidth() - (Level.tileSize * 2);
		optionsButton.yPos = (Level.tileSize * 2);
		optionsButton.width = (Level.tileSize * 2);
		optionsButton.height = (Level.tileSize * 2);
	}

	public void run(){
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0.5f, 0.5f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		instance.materials.get(0).set(TextureAttribute.createDiffuse(tex));

		for(int x = 0; x < 10; x++){
			for(int z = 0; z < 10; z++){
				for(int y = 0; y < 10; y++) {
					modelBatch.begin(cam);
					instance.materials.get(0).set(ColorAttribute.createDiffuse(new Color(x / 10.0f, y / 10.0f, z / 10.0f, 1.0f)));
					instance.transform.idt();
					instance.transform.translate(-x * 5.0f, y * 5.0f, -z * 5.0f);
					modelBatch.render(instance);
					modelBatch.end();
				}
			}
		}

		backButton.update();
		optionsButton.update();
		backButton.render();
		optionsButton.render();

		if(backButton.isPressed){
			StateManager.popState();
		}
		if(optionsButton.isPressed){
			StateManager.pushState(new OptionsScreenState(false));
		}
	}

	public void dispose(){
		modelBatch.dispose();
		model.dispose();
		tex.dispose();
	}
}
