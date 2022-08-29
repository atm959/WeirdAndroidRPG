package com.atm959.weirdandroidrpg.util;

import com.badlogic.gdx.Gdx;

/**
 * Created by atm959 on 3/22/2022.
 */

//Misc. utility functions
public class Util {

	//Convert a normal Y coordinate to the "flipped" LibGDX Y coordinate
	public static int convertY(int y, int spriteHeight) {
		return (Gdx.graphics.getHeight() - spriteHeight) - y;
	}
}
