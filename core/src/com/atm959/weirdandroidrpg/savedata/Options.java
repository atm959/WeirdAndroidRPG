package com.atm959.weirdandroidrpg.savedata;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by atm959 on 3/24/2022.
 */

//Different game options
public class Options {
	//The name of the options preferences file
	private static final String OPTIONS_PREF_NAME = "ATM959_WEIRD_RPG_GAME_OPTIONS";

	//The options format version
	private static final String OPTIONS_FILE_FORMAT_VERSION_KEY = "OPTIONS_FILE_FORMAT_VERSION";

	//Have the options been set?
	private static final String OPTIONS_HAVE_BEEN_SET_KEY = "OPTIONS_HAVE_BEEN_SET";
	public static boolean optionsHaveBeenSet;
	//Left-handed D-Pad
	private static final String RIGHT_HANDED_DPAD_KEY = "LEFT_HANDED_DPAD";
	public static boolean rightHandedDPad;
	//D-Pad opacity
	private static final String DPAD_OPACITY_KEY = "DPAD_OPACITY";
	public static float dpadOpacity;
	//Show debug info at the bottom of the screen?
	private static final String SHOW_DEBUG_INFO_KEY = "SHOW_DEBUG_INFO";
	public static boolean showDebugInfo;
	//Play music?
	private static final String PLAY_MUSIC_KEY = "PLAY_MUSIC";
	public static boolean playMusic;

	//Load the options
	public static void load() {
		Preferences pref = Gdx.app.getPreferences(OPTIONS_PREF_NAME);
		optionsHaveBeenSet = pref.getBoolean(OPTIONS_HAVE_BEEN_SET_KEY, false);
		if (pref.getString(OPTIONS_FILE_FORMAT_VERSION_KEY).equals("v1")) {
			rightHandedDPad = pref.getBoolean(RIGHT_HANDED_DPAD_KEY, true);
			dpadOpacity = pref.getFloat(DPAD_OPACITY_KEY, 0.75f);
			showDebugInfo = pref.getBoolean(SHOW_DEBUG_INFO_KEY, true);
			playMusic = pref.getBoolean(PLAY_MUSIC_KEY, true);
		}
	}

	//Save the options
	public static void save() {
		Preferences pref = Gdx.app.getPreferences(OPTIONS_PREF_NAME);
		pref.putString(OPTIONS_FILE_FORMAT_VERSION_KEY, "v1");
		pref.putBoolean(OPTIONS_HAVE_BEEN_SET_KEY, optionsHaveBeenSet);
		pref.putBoolean(RIGHT_HANDED_DPAD_KEY, rightHandedDPad);
		pref.putFloat(DPAD_OPACITY_KEY, dpadOpacity);
		pref.putBoolean(SHOW_DEBUG_INFO_KEY, showDebugInfo);
		pref.putBoolean(PLAY_MUSIC_KEY, playMusic);
		pref.flush();
	}
}