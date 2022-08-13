package com.atm959.weirdandroidrpg.time;

/**
 * Created by atm959 on 3/24/2022.
 */
public class Time {
    public static int frameCount = 0;

    public static int fps = 0;
    public static int fpsTemp = 0;
    public static long currentTimeForFPS;
    public static long lastTimeForFPS;
    public static boolean isFirstFrameForFPS = true;

    public static float deltaTime = 0.0f;
    public static long currentTimeForDeltaTime;
    public static long lastTimeForDeltaTime;
    public static boolean isFirstFrameForDeltaTime = true;

    public static void calculateFPSAndDeltaTime(){
        if(isFirstFrameForFPS){
            isFirstFrameForFPS = false;
            currentTimeForFPS = System.currentTimeMillis();
            lastTimeForFPS = System.currentTimeMillis();
        } else {
            currentTimeForFPS = System.currentTimeMillis();
            if((currentTimeForFPS - 1000) > lastTimeForFPS){
                lastTimeForFPS = currentTimeForFPS;
                fps = fpsTemp;
                fpsTemp = 0;
            } else {
                fpsTemp++;
            }
        }

        if(isFirstFrameForDeltaTime){
            isFirstFrameForDeltaTime = false;
            currentTimeForDeltaTime = System.nanoTime();
            lastTimeForDeltaTime = System.nanoTime();
        } else {
            currentTimeForDeltaTime = System.nanoTime();
            deltaTime = (float) ((currentTimeForDeltaTime - lastTimeForDeltaTime) / 1E6);
            lastTimeForDeltaTime = currentTimeForDeltaTime;
        }
    }
}