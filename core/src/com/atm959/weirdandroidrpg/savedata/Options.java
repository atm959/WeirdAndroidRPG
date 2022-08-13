package com.atm959.weirdandroidrpg.savedata;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by atm959 on 3/24/2022.
 */
public class Options {
    private static final String OPTIONS_PREF_NAME = "ATM959_WEIRD_RPG_GAME_OPTIONS";

    private static final String OPTIONS_HAVE_BEEN_SET_KEY = "OPTIONS_HAVE_BEEN_SET";
    public static boolean optionsHaveBeenSet;

    private static final String RIGHT_HANDED_DPAD_KEY = "LEFT_HANDED_DPAD";
    public static boolean rightHandedDPad;

    private static final String DPAD_OPACITY_KEY = "DPAD_OPACITY";
    public static float dpadOpacity;

    private static final String SHOW_FPS_AND_DELTA_KEY = "SHOW_FPS_AND_DELTA";
    public static boolean showFPSAndDelta;

    public static void load(){
        Preferences pref = Gdx.app.getPreferences(OPTIONS_PREF_NAME);
        optionsHaveBeenSet = pref.getBoolean(OPTIONS_HAVE_BEEN_SET_KEY, false);
        rightHandedDPad = pref.getBoolean(RIGHT_HANDED_DPAD_KEY, true);
        dpadOpacity = pref.getFloat(DPAD_OPACITY_KEY, 0.75f);
        showFPSAndDelta = pref.getBoolean(SHOW_FPS_AND_DELTA_KEY, true);
    }

    public static void save(){
        Preferences pref = Gdx.app.getPreferences(OPTIONS_PREF_NAME);
        pref.putBoolean(OPTIONS_HAVE_BEEN_SET_KEY, optionsHaveBeenSet);
        pref.putBoolean(RIGHT_HANDED_DPAD_KEY, rightHandedDPad);
        pref.putFloat(DPAD_OPACITY_KEY, dpadOpacity);
        pref.putBoolean(SHOW_FPS_AND_DELTA_KEY, showFPSAndDelta);
        pref.flush();
    }
}
