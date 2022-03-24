package com.atm959.weirdandroidrpg.util;

import com.badlogic.gdx.Gdx;

/**
 * Created by atm959 on 3/22/2022.
 */
public class Util {
    public static int ConvertY(int y, int spriteHeight){
        return (Gdx.graphics.getHeight() - spriteHeight) - y;
    }
}
