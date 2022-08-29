package com.atm959.weirdandroidrpg.input;

import com.atm959.weirdandroidrpg.text.TextRenderer;
import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/25/2022.
 */
public class Button {
	public int xPos, yPos;
	public int width, height;
	public boolean isPressed;
	public String label;

	private final Texture texture;
	private final SpriteBatch sb;

	public Button() {
		texture = new Texture("ui/button.png");
		sb = new SpriteBatch();

		label = "";
	}

	public Button(String texPath) {
		texture = new Texture(texPath);
		sb = new SpriteBatch();

		label = "";
	}

	public void update() {
		if (TouchInput.touched) {
			int relativeX = TouchInput.touchX - xPos;
			int relativeY = TouchInput.touchY - yPos;

			if ((relativeX < 0) || (relativeX > width) || (relativeY < 0) || (relativeY > height))
				return;

			isPressed = true;
		} else {
			isPressed = false;
		}
	}

	public void render() {
		sb.begin();
		sb.draw(texture, xPos, Util.convertY(yPos, height), width, height);
		sb.end();
		TextRenderer.renderString(label, TextRenderer.calculateCenteredXPosition(label, TextRenderer.calculateFittingScale(label, width, false), xPos, width), TextRenderer.calculateCenteredYPosition(TextRenderer.calculateFittingScale(label, width, false), yPos, height), TextRenderer.calculateFittingScale(label, width, false));
	}

	public void dispose() {
		texture.dispose();
		sb.dispose();
	}
}
