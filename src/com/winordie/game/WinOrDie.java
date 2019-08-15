package com.winordie.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import screen.GameView;
import screen.MainMenuView;
import tools.ScrollingBackground;

public class WinOrDie extends Game {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 640;

	public ScrollingBackground bg;
	public SpriteBatch batch;

	public OrthographicCamera camera;
	public StretchViewport viewPort;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewPort = new StretchViewport(WIDTH, HEIGHT, camera);
		viewPort.apply();
		camera.position.set(WIDTH/2, HEIGHT/2, 0);
		camera.update();
		bg = new ScrollingBackground();
		this.setScreen(new MainMenuView(this));
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		viewPort.update(WIDTH, HEIGHT);
		super.resize(width, height);
	}
}
