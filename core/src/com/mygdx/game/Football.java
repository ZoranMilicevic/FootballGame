package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Zoran Milicevic on 3/17/2018.
 */

public class Football extends Game {
    Stage stageMenu;
    SpriteBatch batch;
    BitmapFont font;

    public void create() {
        stageMenu= new Stage();
        batch= new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new Menu(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        stageMenu.dispose();
        batch.dispose();
        font.dispose();
    }
}
