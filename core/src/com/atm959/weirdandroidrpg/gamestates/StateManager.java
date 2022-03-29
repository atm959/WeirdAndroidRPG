package com.atm959.weirdandroidrpg.gamestates;

import java.util.ArrayList;

/**
 * Created by atm959 on 3/23/2022.
 */
public class StateManager {
    public static ArrayList<GameState> stateStack;

    public static void initStack(){
        stateStack = new ArrayList<GameState>();
    }

    public static void pushState(GameState gameState){
        stateStack.add(gameState);
    }

    public static void popState(){
        stateStack.get(stateStack.size() - 1).dispose();
        stateStack.remove(stateStack.size() - 1);
    }

    public static GameState getCurrentState(){
        return stateStack.get(stateStack.size() - 1);
    }

    public static void runCurrentState(){
        stateStack.get(stateStack.size() - 1).run();
    }

    public static void disposeAll(){
        for(int i = stateStack.size() - 1; i >= 0; i--){
            stateStack.get(i).dispose();
            stateStack.remove(i);
        }
    }
}
