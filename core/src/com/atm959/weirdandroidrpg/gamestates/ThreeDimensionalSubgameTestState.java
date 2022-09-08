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

	private Button backButton;

	public ThreeDimensionalSubgameTestState(){
		modelBatch = new ModelBatch();

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(10f, 10f, 10f);
		cam.lookAt(0,0,0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

		ModelBuilder modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(5.0f, 5.0f, 5.0f, new Material(ColorAttribute.createDiffuse(Color.GREEN)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
		instance = new ModelInstance(model);

		backButton = new Button("ui/menuButton.png");
		backButton.xPos = 0;
		backButton.yPos = 0;
		backButton.width = (3 * Level.tileSize);
		backButton.height = (3 * Level.tileSize);
	}

	public void run(){
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cam);
		modelBatch.render(instance);
		modelBatch.end();

		backButton.update();
		backButton.render();

		if(backButton.isPressed){
			StateManager.popState();
		}
	}

	public void dispose(){
		modelBatch.dispose();
		model.dispose();
	}
}
