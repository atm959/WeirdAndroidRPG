package com.atm959.weirdandroidrpg.time;

/**
 * Created by atm959 on 3/24/2022.
 */
public class Time {
	public static int frameCount = 0; //The amount of executed frames

	public static int fps = 0; //The framerate
	public static int fpsTemp = 0; //A temporary counter for the framerate
	public static long currentTimeForFPS; //The current time in milliseconds
	public static long lastTimeForFPS; //The previous frame's time in milliseconds
	public static boolean isFirstFrameForFPS = true; //Is this the first frame?

	public static float deltaTime = 0.0f; //The delta time
	public static long currentTimeForDeltaTime; //The current time in milliseconds
	public static long lastTimeForDeltaTime; //The previous frame's time in milliseconds
	public static boolean isFirstFrameForDeltaTime = true; //Is this the first frame?

	//Calculate the FPS and delta time
	public static void calculateFPSAndDeltaTime() {
		if (isFirstFrameForFPS) {
			//If this is the first frame, set both the current time and the last frame's time to the current time
			isFirstFrameForFPS = false;
			currentTimeForFPS = System.currentTimeMillis();
			lastTimeForFPS = System.currentTimeMillis();
		} else {
			//Otherwise, check if a full second has passed
			currentTimeForFPS = System.currentTimeMillis();
			if ((currentTimeForFPS - 1000) > lastTimeForFPS) {
				//If so, copy the temporary FPS counter to the actual FPS counter and set "last time" to the current time
				lastTimeForFPS = currentTimeForFPS;
				fps = fpsTemp;
				fpsTemp = 0;
			} else {
				//Otherwise, increase the temporary FPS counter
				fpsTemp++;
			}
		}

		if (isFirstFrameForDeltaTime) {
			//If this is the first frame, set both the current time and the last frame's time to the current time
			isFirstFrameForDeltaTime = false;
			currentTimeForDeltaTime = System.nanoTime();
			lastTimeForDeltaTime = System.nanoTime();
		} else {
			//Otherwise, calculate the delta time
			currentTimeForDeltaTime = System.nanoTime();
			deltaTime = (float) ((currentTimeForDeltaTime - lastTimeForDeltaTime) / 1E6);
			lastTimeForDeltaTime = currentTimeForDeltaTime;
		}
	}
}