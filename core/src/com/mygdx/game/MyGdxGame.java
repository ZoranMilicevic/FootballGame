package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


public class MyGdxGame implements Screen {
	private Football game;
	private Ball b;
	private PlayerHuman ph;
	private PlayerAI pa;
	private BitmapFont btf;


	public MyGdxGame(Football game) {
		super();
		this.game=game;
		FreeTypeFontGenerator generator= new FreeTypeFontGenerator(Gdx.files.internal("helv.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter= new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size=42;
		parameter.color= Color.BLACK;
		btf= generator.generateFont(parameter);

		b= new Ball();
		ph=new PlayerHuman(b, game.r, this);
		pa=new PlayerAI(b, game.r);

	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		String res=game.r.getResult();

		btf.draw(game.batch, res, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-250 );
		Sprite ball=b.getSprite();
		ball.draw(game.batch);
		Sprite plh=ph.getSprite();
		plh.draw(game.batch);
		Sprite pla=pa.getSprite();
		pla.draw(game.batch);
		TextButton button= ph.getButton();
		button.draw(game.batch, 1);
		game.batch.end();
	}

	public void setDificulty(int dificulty){
		b.setDelta(10*dificulty);
		ph.setDelta(10*dificulty);
		pa.setDelta(10*dificulty);
	}

	public void start(){
		b.start();
		ph.start();
		pa.start();
		Gdx.input.setInputProcessor(ph);
	}

	public void stop(){
		b._stop();
		ph._stop();
		pa._stop();
	}

	@Override
	public synchronized void pause() {
		b._pause();
		ph._pause();
		pa._pause();
		game.setScreen(game.getIngameMenu());
		Gdx.input.setInputProcessor(game.ingame);
	}

	@Override
	public synchronized void resume() {
		b._resume();
		ph._resume();
		pa._resume();
		Gdx.input.setInputProcessor(ph);
		game.setScreen(game.mg);

	}

	@Override
	public void dispose () {
		btf.dispose();
	}

	@Override
	public void hide() {

	}

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {

	}

}

