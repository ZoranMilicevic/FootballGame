package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Zoran Milicevic on 3/5/2018.
 */

public class PlayerAI extends Thread {
    public static final float x=200;

    private float y;
    private Sprite spr;
    private float delta=10;
    private float vY=0;
    private boolean flag;

    private Ball b;
    private Result r;

    public PlayerAI(Ball b, Result r){
        Texture t= new Texture("player.png");
        spr= new Sprite(t);
        spr.setSize(Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/5);
        y= Gdx.graphics.getHeight()/2-spr.getHeight()/2;
        spr.setPosition(x, y);
        this.b=b;
        this.r=r;
        flag=true;
    }

    @Override
    public void run(){
        float hittingPoint=-20;
        try{
            while(!Thread.interrupted()){
                synchronized (this) {
                    float[] info = b.getInfo(); //get data
                    hitAI(info[0], info[1], info[4], info[5]); //check if hit
                    humanScored(info[0], info[4]);  //check if human scored

                    if (info[2] < 0) {
                        hittingPoint=hittingPoint(info[0], info[1], info[2], info[3], info[4], info[5]);
                        if(hittingPoint > y+spr.getHeight())vY=delta;
                        else if(hittingPoint < y)vY=-delta;
                        flag=false;
                    }
                    else if(info[2]>0)vY=0;


                    if(vY!=0) {
                        y += vY;
                        Gdx.app.log("zoran", "y je " + y + " hitting point je " + hittingPoint + "brzina je " + vY);
                        if(y < hittingPoint && hittingPoint < y+spr.getHeight())vY=0;
                        Gdx.app.log("zoran", "brzina je " + vY);
                        if (y <= 0) {
                            y = 0;
                            vY = 0;
                        } else if (y + spr.getHeight() >= Gdx.graphics.getHeight()) {
                            y = Gdx.graphics.getHeight() - spr.getHeight();
                            vY = 0;
                        }
                        spr.setPosition(x, y);
                    }
                }
                sleep(100);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public float hittingPoint(float xx, float yy, float vX, float vY, float w, float h) {
        float hitp=0;

            while (true) {
                xx += vX;
                yy += vY;
                if(xx<x){
                    hitp=yy;
                    break;
                }
                if(yy<0){
                    yy=0;
                    vY=-vY;
                }
                else if(yy+h > Gdx.graphics.getHeight()){
                    yy=Gdx.graphics.getHeight()-spr.getHeight();
                    vY=-vY;
                }
            }
        return hitp;
    }

    public synchronized void setDelta(float delta){
        this.delta=delta;
    }

    public synchronized Sprite getSprite(){
        return spr;
    }

    public void humanScored(float xx, float width){  // behind AI
        if(xx+width< PlayerAI.x) {
            b.resetPosition();
            r.updateResultHuman();
            vY=0;
            flag=true;
            Gdx.app.log("zoran", "SCORED!!!");
        }

    }

    public void hitAI(float x1, float y1, float width1, float height1){
        if(x1>=x) {
            Rectangle r1=new Rectangle(x,y,spr.getWidth(), spr.getHeight());
            Rectangle r2= new Rectangle(x1, y1, width1, height1);
            if(r1.overlaps(r2)) {
                b.hit();
                vY=0;
                flag=true;
                Gdx.app.log("zoran", "HIT!!!");
            }
        }
    }

}
