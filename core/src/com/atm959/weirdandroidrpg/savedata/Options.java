package com.atm959.weirdandroidrpg.savedata;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by atm959 on 3/24/2022.
 */
public class Options {
    private static final String OPTIONS_PREF_NAME = "ATM959_WEIRD_RPG_GAME_OPTIONS";

    private static final String DEBUG_MODE_ENABLED_KEY = "DEBUG_MODE_ENABLED";
    public static boolean debugModeEnabled;

    private static final String LEFT_HANDED_DPAD_KEY = "LEFT_HANDED_DPAD";
    public static boolean leftHandedDPad;

    private static final String DPAD_OPACITY_KEY = "DPAD_OPACITY";
    public static float dpadOpacity;

    public static void Load(){
        Preferences pref = Gdx.app.getPreferences(OPTIONS_PREF_NAME);
        debugModeEnabled = pref.getBoolean(DEBUG_MODE_ENABLED_KEY, false);
        leftHandedDPad = pref.getBoolean(LEFT_HANDED_DPAD_KEY, false);
        dpadOpacity = pref.getFloat(DPAD_OPACITY_KEY, 0.75f);
    }

    public static void Save(){
        Preferences pref = Gdx.app.getPreferences(OPTIONS_PREF_NAME);
        pref.putBoolean(DEBUG_MODE_ENABLED_KEY, debugModeEnabled);
        pref.putBoolean(LEFT_HANDED_DPAD_KEY, leftHandedDPad);
        pref.putFloat(DPAD_OPACITY_KEY, dpadOpacity);
        pref.flush();
    }
}
