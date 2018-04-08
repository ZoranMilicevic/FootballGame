package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Zoran Milicevic on 3/17/2018.
 */

public class Football extends Game {
    Stage stageMenu;
    Stage ingame;
    Stage info;
    SpriteBatch batch;
    Result r;

    Menu m;
    InGameMenu im;
    MyGdxGame mg;
    setUpMenu sum;

    boolean secondGame=false;

    public Football(Result r){
        this.r=r;
    }

    public void create() {
        stageMenu= new Stage();
        ingame= new Stage();
        info= new Stage();
        batch= new SpriteBatch();
        m= new Menu(this);
        im= new InGameMenu(this);
        mg= new MyGdxGame(this);
        sum= new setUpMenu(this);

        this.setScreen(m);
    }

    public void render() {
        super.render(); // important!
    }

    public Menu getMenu() {
        return m;
    }

    public InGameMenu getIngameMenu() {
        return im;
    }

    public MyGdxGame getMygdxGame() {
        return mg;
    }

    public void dispose() {
        stageMenu.dispose();
        batch.dispose();
        m.dispose();
        im.dispose();
        mg.dispose();

        r.writeToDB();
    }

}
