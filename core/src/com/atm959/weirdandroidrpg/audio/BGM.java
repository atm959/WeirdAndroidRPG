package com.atm959.weirdandroidrpg.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by atm959 on 3/29/2022.
 */
public class BGM {
    public static final String[] songFiles = {
        "title/bgm.ogg",
        "level/bgm.mp4"
    };
    public static Music music;

    public static void playSong(int id){
        if(music != null) if(music.isPlaying()) music.stop();
        music = Gdx.audio.newMusic(Gdx.files.internal(songFiles[id]));
        music.setLooping(true);
        music.play();
    }
}
