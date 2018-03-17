package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Zoran Milicevic on 3/5/2018.
 */

public class PlayerHuman extends Thread implements InputProcessor{
    public static final float x=Gdx.graphics.getWidth()- PlayerAI.x;

    private float y;
    private Sprite spr;
    private float delta=10;
    private float vY=0;

    private Ball b;
    private Result r;

    public PlayerHuman(Ball b, Result r){
        Texture t= new Texture("player.png");
        spr= new Sprite(t);
        spr.setSize(Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/5);
        y= Gdx.graphics.getHeight()/2-spr.getHeight()/2;
        spr.setPosition(x,y);
        this.b=b;
        this.r=r;
    }

    @Override
    public void run() {
        super.run();
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    float[] info = b.getInfo(); //get data
                    hitHuman(info[0], info[1], info[4], info[5]);
                    AIScored(info[0]);

                    y += vY;
                    if(y<=0){
                        y=0;
                        vY=0;
                    }
                    else if(y+spr.getHeight() >= Gdx.graphics.getHeight()){
                        y=Gdx.graphics.getHeight() - spr.getHeight();
                        vY=0;
                    }
                    spr.setPosition(x, y);
                }
                sleep(100);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    public synchronized void setDelta(float delta){
        this.delta=delta;
    }

    public synchronized Sprite getSprite(){
        return spr;
    }

    public void AIScored(float xx){  //behind human
        if(xx> PlayerHuman.x+spr.getWidth()){
            b.resetPosition();
            r.updateResultAI();
            vY=0;
        }
    }

    public void hitHuman(float x1, float y1, float width1, float height1){
        if(x1<=x) {
            Rectangle r1=new Rectangle(x,y,spr.getWidth(), spr.getHeight());
            Rectangle r2= new Rectangle(x1, y1, width1, height1);
            if(r1.overlaps(r2)) {
                b.hit();
                vY=0;
            }
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenY > y+spr.getHeight()/2)vY=delta;
        else vY=-delta;
        return true;
    }












    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }
}
