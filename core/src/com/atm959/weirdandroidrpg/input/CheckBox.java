package com.atm959.weirdandroidrpg.input;

import com.atm959.weirdandroidrpg.util.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by atm959 on 3/24/2022.
 */
public class CheckBox {
	public int xPos, yPos;
	public int size;
	public boolean isChecked;

	private final Texture texture;
	private final SpriteBatch sb;

	public CheckBox() {
		texture = new Texture("ui/checkbox.png");
		sb = new SpriteBatch();
	}

	public void update() {
		if (TouchInput.touched) {
			int relativeX = TouchInput.touchX - xPos;
			int relativeY = TouchInput.touchY - yPos;

			if ((relativeX < 0) || (relativeX > size) || (relativeY < 0) || (relativeY > size))
				return;

			isChecked = !isChecked;
		}
	}

	public void render() {
		int srcX = 0;
		if (isChecked) {
			srcX += 16;
		}

		sb.begin();
		sb.draw(texture, xPos, Util.convertY(yPos, size), size, size, srcX, 0, 16, 16, false, false);
		sb.end();
	}

	public void dispose() {
		texture.dispose();
		sb.dispose();
	}
}
