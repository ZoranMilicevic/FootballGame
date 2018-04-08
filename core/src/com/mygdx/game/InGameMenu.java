package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Zoran Milicevic on 3/21/2018.
 */

public class InGameMenu implements Screen {

    private Football game;
    private TextButton resum;
    private TextButton backToMenu;
    private BitmapFont font;


    public InGameMenu(Football game){
        this.game=game;

        float w = Gdx.graphics.getWidth();
        float h=Gdx.graphics.getHeight();

        FreeTypeFontGenerator generator= new FreeTypeFontGenerator(Gdx.files.internal("helv.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter= new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=42;
        font= generator.generateFont(parameter);

        TextureAtlas buttonAtlas= new TextureAtlas("ui-blue.atlas");
        Skin skin= new Skin();
        skin.addRegions(buttonAtlas);

        TextButton.TextButtonStyle textbuttonStyle=new TextButton.TextButtonStyle();
        textbuttonStyle.font= font;
        textbuttonStyle.up= skin.getDrawable("button_06");
        textbuttonStyle.down=skin.getDrawable("button_06");
        textbuttonStyle.checked=skin.getDrawable("button_06");

        resum=new TextButton("Resume Game", textbuttonStyle);
        resum.setName("Resume");
        resum.setSize(w/3, h/6);
        resum.setPosition(w/2-resum.getWidth()/2, 9*h/12);
        game.ingame.addActor(resum);

        backToMenu=new TextButton("Back to Menu", textbuttonStyle);
        backToMenu.setName("back");
        backToMenu.setSize(w/3, h/6);
        backToMenu.setPosition(w/2-backToMenu.getWidth()/2, 5*h/12);
        game.ingame.addActor(backToMenu);

        ClickListener cl2= new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(backToMenu.isPressed()){
                    backToMenuPressed();
                }
                if(resum.isPressed()){
                    resumPressed();
                }
            }
        };

        resum.addListener(cl2);
        backToMenu.addListener(cl2);
    }

    private void resumPressed() {
        game.mg.resume();
    }

    private void backToMenuPressed() {
        game.mg.stop();
        game.secondGame=true;
        Gdx.input.setInputProcessor(game.stageMenu);
        game.setScreen(game.m);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.ingame.act();
        game.ingame.draw();

    }

    @Override
    public void dispose() {
        font.dispose();
    }




    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


}
