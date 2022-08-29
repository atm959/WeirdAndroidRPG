package com.atm959.weirdandroidrpg.input;

import com.badlogic.gdx.Gdx;

/**
 * Created by atm959 on 3/24/2022.
 */
public class TouchInput {
	public static int touchTime = 0;
	public static boolean touched = false;
	public static boolean held = false;
	public static boolean released = false;
	public static int touchX = 0;
	public static int touchY = 0;

	public static void update() {
		if (released) released = false;

		if (Gdx.input.isTouched()) {
			touched = true;
			touchTime++;
			if (touchTime > 1) touched = false;
			held = true;
		} else {
			if (touchTime > 1) released = true;
			touchTime = 0;
			touched = false;
			held = false;
		}

		if (held) {
			touchX = Gdx.input.getX();
			touchY = Gdx.input.getY();
		}
	}
}
