package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



/**
 * Created by Zoran Milicevic on 3/22/2018.
 */

public class setUpMenu implements Screen {
    private Football game;
    private TextButton submit;
    private TextField name;
    private BitmapFont font;
    private TextField dificulty;

    public setUpMenu(Football game){
        this.game=game;

        float w = Gdx.graphics.getWidth();
        float h=Gdx.graphics.getHeight();

        FreeTypeFontGenerator generator= new FreeTypeFontGenerator(Gdx.files.internal("helv.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter= new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=42;
        font= generator.generateFont(parameter);


        TextureAtlas atlas= new TextureAtlas("ui-blue.atlas");
        Skin skin= new Skin();
        skin.addRegions(atlas);
        TextButton.TextButtonStyle style= new TextButton.TextButtonStyle();
        style.up= skin.getDrawable("button_06");
        style.down=skin.getDrawable("button_06");
        style.checked=skin.getDrawable("button_06");
        style.font=font;

        submit= new TextButton("Submit", style);
        submit.setSize(w/3,h/6);
        submit.setPosition(w/2-submit.getWidth()/2,4*h/12);
        game.info.addActor(submit);

        ClickListener cl3= new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pressedSubmit();
            }
        };
        submit.addListener(cl3);

        TextField.TextFieldStyle tfstyle= new TextField.TextFieldStyle();
        tfstyle.font=font;
        tfstyle.fontColor= Color.BLACK;

        name= new TextField("", tfstyle);
        name.setSize(w/3, h/12);
        name.setPosition(w/2 ,10*h/12);
        game.info.addActor(name);

        dificulty= new TextField("", tfstyle);
        dificulty.setSize(w/3, h/12);
        dificulty.setPosition(w/2, 7*h/12);
        game.info.addActor(dificulty);

        TextField text1= new TextField("Enter your name here:", tfstyle);
        text1.setSize(w/3, h/12);
        text1.setPosition(w/6, 10*h/12);
        text1.setDisabled(true);
        game.info.addActor(text1);

        TextField text2= new TextField("Enter dificulty here (1-5):", tfstyle);
        text2.setSize(w/3, h/12);
        text2.setPosition(w/6, 7*h/12);
        text2.setDisabled(true);
        game.info.addActor(text2);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.info.act();
        game.info.draw();
    }

    private void pressedSubmit(){
        String n=name.getText();
        game.r.setNameHuman(n);
        int dif=Integer.parseInt(dificulty.getText());
        if(dif<1)dif=1;
        if(dif>5)dif=5;
        game.mg.setDificulty(dif);



        if(game.secondGame) {
            game.r.reset();
            game.mg= new MyGdxGame(game);
            game.batch= new SpriteBatch();
        }
        MyGdxGame k = game.getMygdxGame();
        game.setScreen(k);
        k.start();
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

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }
}
