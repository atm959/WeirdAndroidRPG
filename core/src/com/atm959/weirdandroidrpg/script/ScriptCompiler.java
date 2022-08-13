package com.atm959.weirdandroidrpg.script;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by atm959 on 4/9/2022.
 */
public class ScriptCompiler {

    public ScriptCompiler(){

    }

    public void compileScript(String scriptPath){
        FileHandle scriptFileHandle = Gdx.files.internal(scriptPath);
        String[] lines = scriptFileHandle.readString().split("\n");
        for(int i = 0; i < lines.length; i++) {
            Gdx.app.log("COMPILE_SCRIPT", lines[i]);
        }
    }

}
