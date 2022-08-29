package com.atm959.weirdandroidrpg.input;

import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/24/2022.
 */
public class Slider {
	public int xPos, yPos;
	public int width, height;
	public float value;

	private final Texture texture;
	private final SpriteBatch sb;

	public Slider() {
		texture = new Texture("ui/slider.png");
		sb = new SpriteBatch();
	}

	public void update() {
		if (TouchInput.held) {
			int relativeX = TouchInput.touchX - xPos;
			int relativeY = TouchInput.touchY - yPos;

			if ((relativeX < 0) || (relativeX > width) || (relativeY < 0) || (relativeY > height))
				return;

			int scaleFactor = width / 32;
			int normalizedX = relativeX / scaleFactor;
			value = normalizedX / 31.0f;
			if (value < 0.0f) value = 0.0f;
			if (value > 1.0f) value = 1.0f;
		}
	}

	public void render() {
		sb.begin();
		sb.draw(texture, xPos, Util.convertY(yPos, height), width, height, 0, 0, 32, 16, false, false);

		int sliderPieceX = xPos + (int) (value * (width - ((width / 32) * 4)));
		sb.draw(texture, sliderPieceX, Util.convertY(yPos, height), width / 8.0f, height, 0, 16, 8, 16, false, false);
		sb.end();
	}

	public void dispose() {
		texture.dispose();
		sb.dispose();
	}
}
