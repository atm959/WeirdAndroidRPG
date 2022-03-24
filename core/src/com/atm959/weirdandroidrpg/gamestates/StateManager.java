package com.atm959.weirdandroidrpg.gamestates;

import java.util.ArrayList;

/**
 * Created by atm959 on 3/23/2022.
 */
public class StateManager {
    public static ArrayList<GameState> stateStack;
    public static boolean deleteCurrentState = false;

    public static void InitStack(){
        stateStack = new ArrayList<GameState>();
    }

    public static void PushState(GameState gameState){
        stateStack.add(gameState);
    }

    public static void PopState(){
        stateStack.get(stateStack.size() - 1).Dispose();
        stateStack.remove(stateStack.size() - 1);
    }

    public static GameState GetCurrentState(){
        return stateStack.get(stateStack.size() - 1);
    }

    public static void RunCurrentState(){
        if(deleteCurrentState){
            deleteCurrentState = false;
            PopState();
        }
        stateStack.get(stateStack.size() - 1).Run();
    }

    public static void DisposeAll(){
        for(int i = stateStack.size() - 1; i >= 0; i--){
            stateStack.get(i).Dispose();
            stateStack.remove(i);
        }
    }

    public static void MarkDeleteCurrState(){
        deleteCurrentState = true;
    }

    public static void CancelDeleteCurrState(){
        deleteCurrentState = false;
    }
}
