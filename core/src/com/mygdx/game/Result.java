package com.mygdx.game;

/**
 * Created by Zoran Milicevic on 3/5/2018.
 */

public class Result {
    private int AI;
    private int human;
    private String result;
    private String nameHuman;
    private String nameAI;


    public Result(){
        AI=0;
        human=0;
        result= "AI     " + AI + " : " + human + "     " + nameHuman;
    }

    public synchronized void updateResultAI(){
        AI++;
        result= "AI     " + AI + " : " + human + "   " + nameHuman;
    }

    public synchronized void updateResultHuman(){
        human++;
        result= "AI     " + AI + " : " + human + "    " + nameHuman;
    }

    public synchronized void reset(){
        AI=0;
        human=0;
    }

    public synchronized String getResult(){
        return result;
    }

    public void setNameHuman(String name){
        nameHuman=name;
        result= "AI     " + AI + " : " + human + "   " + nameHuman;
    }

    public String getNameHuman(){
        return nameHuman;
    }

    public String getNameAI(){
        return nameAI;
    }

    public int getHuman(){
        return human;
    }

    public int getAI(){
        return AI;
    }

    public void writeToDB(){

    }


}
