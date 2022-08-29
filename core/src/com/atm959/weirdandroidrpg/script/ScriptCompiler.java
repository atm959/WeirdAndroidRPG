package com.atm959.weirdandroidrpg.script;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by atm959 on 4/9/2022.
 */

//The script compiler for compiling scripts into their respective bytecode
public class ScriptCompiler {

	public ScriptCompiler() {

	}

	//INCOMPLETE: Compile the script
	public void compileScript(String scriptPath) {
		//For now, all of the text lines of the script are logged
		FileHandle scriptFileHandle = Gdx.files.internal(scriptPath);
		String[] lines = scriptFileHandle.readString().split("\n");
		for (int i = 0; i < lines.length; i++) {
			Gdx.app.log("COMPILE_SCRIPT", lines[i]);
		}
	}

}
