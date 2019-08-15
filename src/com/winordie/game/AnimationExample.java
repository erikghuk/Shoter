package com.winordie.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationExample implements Screen {

    private static final int FRAME_COLS = 5; // #1
    private static final int FRAME_ROWS = 1; // #2
    Animation<TextureRegion> scaleAnimation;

    Texture circleSheet; // #4
    TextureRegion[] walkFrames; // #5
    SpriteBatch spriteBatch; // #6
    TextureRegion currentFrame; // #7

    float stateTime; // #8



    @Override
    public void show() {
        circleSheet = new Texture(Gdx.files.internal("animTest/testGun.png")); // #9
        TextureRegion[][] tmp = TextureRegion.split(circleSheet, circleSheet.getWidth()/FRAME_COLS, circleSheet.getHeight()/FRAME_ROWS); // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        scaleAnimation = new Animation<TextureRegion>(0.05f, walkFrames); // #11
        spriteBatch = new SpriteBatch(); // #12
        stateTime = 0f; // #13
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); // #14
        stateTime += Gdx.graphics.getDeltaTime(); // #15
        currentFrame = scaleAnimation.getKeyFrame(stateTime, true); // #16
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 100, 100); // #17
        spriteBatch.end();
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
}