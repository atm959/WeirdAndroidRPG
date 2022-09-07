package com.atm959.weirdandroidrpg.audio;

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

    public static void playSong(int id){
        if(music != null) if(music.isPlaying()) music.stop();
        music = Gdx.audio.newMusic(Gdx.files.internal(songFiles[id]));
        music.setLooping(true);
        music.play();
    }
}
