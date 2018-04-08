package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Zoran Milicevic on 3/13/2018.
 */

public class Menu implements Screen {
    private Football game;
    private TextButton start;
    private TextButton exit;
    private TextButton scores;
    private BitmapFont font;


    public Menu(Football game){
        this.game=game;

        float w = Gdx.graphics.getWidth();
        float h=Gdx.graphics.getHeight();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("helv.ttf"));
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


        start=new TextButton("Start", textbuttonStyle);
        start.setName("start");
        start.setSize(w/3, h/6);
        start.setPosition(w/2-start.getWidth()/2, 9*h/12);
        game.stageMenu.addActor(start);

        scores= new TextButton("Mathes Log", textbuttonStyle);
        scores.setName("scores");
        scores.setSize(w/3, h/6);
        scores.setPosition(w/2-scores.getWidth()/2,5*h/12);
        game.stageMenu.addActor(scores);


        exit= new TextButton("Exit", textbuttonStyle);
        exit.setName("exit");
        exit.setSize(w/3,h/6);
        exit.setPosition(w/2-exit.getWidth()/2,h/12);
        game.stageMenu.addActor(exit);

        ClickListener cl= new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (start.isPressed()) {
                    pressedStart();
                }
                if(scores.isPressed());{
                    pressedScores();
                }
                if(exit.isPressed()){
                    pressedExit();
                }
            }
        };
        start.addListener(cl);
        scores.addListener(cl);
        exit.addListener(cl);

        Gdx.input.setInputProcessor(game.stageMenu);
    }

    private void pressedStart(){
        game.setScreen(game.sum);
        Gdx.input.setInputProcessor(game.info);
    }

    private void pressedScores(){

    }
    private void pressedExit(){

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.stageMenu.act();
        game.stageMenu.draw();
    }

    @Override
    public void dispose() {
        font.dispose();
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
    public void show() {

    }
}
