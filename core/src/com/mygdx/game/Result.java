package com.mygdx.game;

/**
 * Created by Zoran Milicevic on 3/5/2018.
 */

public class Result {
    private int AI;
    private int human;
    private String result;

    public Result(){
        AI=0;
        human=0;
        result= "AI     " + AI + " : " + human + "     Zoran";
    }

    public synchronized void updateResultAI(){
        AI++;
        result= "AI     " + AI + " : " + human + "     Zoran";
    }

    public synchronized void updateResultHuman(){
        human++;
        result= "AI     " + AI + " : " + human + "     Zoran";
    }

    public synchronized String getResult(){
        return result;
    }

}
