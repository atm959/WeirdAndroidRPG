package com.atm959.weirdandroidrpg;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		//The Android image sharing API has to be passed in so the game can share the Sharing Image
		initialize(new Game(new AndroidImageSharingAPI(getContext())), config);
	}
}
