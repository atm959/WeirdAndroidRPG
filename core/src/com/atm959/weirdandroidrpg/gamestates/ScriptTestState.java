package com.atm959.weirdandroidrpg.gamestates;

import com.atm959.weirdandroidrpg.audio.BGM;
import com.atm959.weirdandroidrpg.script.ScriptCompiler;
import com.atm959.weirdandroidrpg.script.ScriptExecutor;
import com.atm959.weirdandroidrpg.text.TextRenderer;

/**
 * Created by atm959 on 4/10/2022.
 */
public class ScriptTestState extends GameState {
	private static final byte[] script = {
		0x02, 0x00, 0x02, 0x00,
		0x02, 0x00, 0x01, 0x00,
		0x00, 0x01,
		0x01, 0x00, 0x00, 0x00, 0x00, 0x00
	};
	private final ScriptCompiler scriptCompiler;
	private final ScriptExecutor scriptExecutor;

	public ScriptTestState() {
		BGM.playSong(BGM.SONG_TITLE);

		scriptCompiler = new ScriptCompiler();
		scriptCompiler.compileScript("scripts/testScript.txt");
		scriptExecutor = new ScriptExecutor(script);
	}

	@Override
	public void run() {
		TextRenderer.renderString("SCRIPT TEST", 0, 0, TextRenderer.TEXT_SCALE_LARGE);
		TextRenderer.renderString("PC: " + Integer.toHexString(scriptExecutor.getPC()).toUpperCase(), 0, TextRenderer.TEXT_SCALE_LARGE, TextRenderer.TEXT_SCALE_MEDIUM);
		TextRenderer.renderString("R00: " + Integer.toHexString(scriptExecutor.getRegisterValue((byte) 0x00)).toUpperCase(), 0, TextRenderer.TEXT_SCALE_LARGE + TextRenderer.TEXT_SCALE_MEDIUM, TextRenderer.TEXT_SCALE_MEDIUM);
		TextRenderer.renderString("M000: " + Integer.toHexString(scriptExecutor.getMemoryValue((byte) 0x00)).toUpperCase(), 0, TextRenderer.TEXT_SCALE_LARGE + (TextRenderer.TEXT_SCALE_MEDIUM * 2), TextRenderer.TEXT_SCALE_MEDIUM);

		scriptExecutor.executeNext();
	}

	@Override
	public void dispose() {

	}
}
