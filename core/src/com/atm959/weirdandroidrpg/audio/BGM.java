package com.atm959.weirdandroidrpg.audio;

import com.atm959.weirdandroidrpg.savedata.Options;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by atm959 on 3/29/2022.
 */
public class BGM {
	public static int SONG_TITLE = 0;
	public static int SONG_IN_GAME = 1;

    public static final String[] songFiles = {
        "title/bgm.mp3",
        "level/bgm.mp3"
    };
    public static Music music;
	private static int lastID;

    public static void playSong(int id){
		if(Options.playMusic) {
			if (music != null) if (music.isPlaying()) music.stop();
			music = Gdx.audio.newMusic(Gdx.files.internal(songFiles[id]));
			music.setLooping(true);
			music.play();
		}
		lastID = id;
    }

	public static void onEnablePlayMusic(){
		playSong(lastID);
	}

	public static void onDisablePlayMusic(){
		if(music != null) {
			music.stop();
			music.dispose();
		}
	}

	public static void dispose(){
		music.dispose();
	}
}
