package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class MyGdxGame implements Screen {
	private Football game;
	private Ball b;
	private PlayerHuman ph;
	private PlayerAI pa;
	private Result r;
	private BitmapFont btf;


	public MyGdxGame(Football game) {
		super();
		this.game=game;
		btf= new BitmapFont();


		b= new Ball();
		b.start();

		r= new Result();

		ph=new PlayerHuman(b, r);
		ph.start();

		pa=new PlayerAI(b, r);
		pa.start();

		Gdx.input.setInputProcessor(ph);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		String res=r.getResult();
		btf.draw(game.batch, res, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-250 );
		Sprite ball=b.getSprite();
		ball.draw(game.batch);
		Sprite plh=ph.getSprite();
		plh.draw(game.batch);
		Sprite pla=pa.getSprite();
		pla.draw(game.batch);
		game.batch.end();
	}

	@Override
	public void dispose () {

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

