package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Zoran Milicevic on 3/5/2018.
 */

public class Ball extends Thread {
    private float x;
    private float y;
    private Sprite spr;
    private float vX;
    private float vY;
    private float delta=20;
    private boolean pause;


    public Ball(){
        x= Gdx.graphics.getWidth()/2;
        y=Gdx.graphics.getHeight()/2;

        pause =false;

        Texture b=new Texture("ball.png");
        spr=new Sprite(b);
        spr.setSize(60,60);

        spr.setPosition(x,y);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    if(pause)wait();
                    x += vX;
                    y += vY;
                    spr.setPosition(x, y);

                    if (y <= 0) vY = -vY; //hit down
                    if (y + spr.getHeight() >= Gdx.graphics.getHeight()) vY = -vY; //hit up
                }
                Thread.sleep(100);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public synchronized Sprite getSprite(){
        return spr;
    }

    public synchronized float[] getInfo(){
        float [] niz= new float[6];
        niz[0]=x;
        niz[1]=y;
        niz[2]=vX;
        niz[3]=vY;
        niz[4]=spr.getWidth();
        niz[5]=spr.getHeight();
        return niz;
    }

    public void setDelta(float delta){

        this.delta=delta;
        double i=Math.random();
        if(i<0.5)vX=-delta;
        else vX=delta;

        i=Math.random();
        if(i<0.5)vY=-delta;
        else vY=delta;
    }

    public synchronized void resetPosition(){
        x=Gdx.graphics.getWidth()/2;
        y=Gdx.graphics.getHeight()/2;
        spr.setPosition(x,y);

        double i=Math.random();
        if(i<0.5)vX=-delta;
        else vX=delta;

        i=Math.random();
        if(i<0.5)vY=-delta;
        else vY=delta;
    }

    public synchronized void hit(){
        vX=-vX;
        x+=vX;
        y+=vY;
    }

    public synchronized void _pause(){
        pause=true;
    }

    public  synchronized void _resume(){
        pause=false;
        notifyAll();
    }

    public void _stop(){
        interrupt();
    }

}
