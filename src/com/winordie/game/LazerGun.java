package com.winordie.game;

import com.badlogic.gdx.graphics.Texture;

import tools.Collisions;

public class LazerGun {
    private  final int SPEED = 400;

    private float x;
    private float y;

    public final static int HEIGHT = 20;
    public final static int WIDTH= 11;

    private Texture lazerTxt;

    public static boolean removeLazer = false;

    Collisions rect;

    public LazerGun(float x, float y) {
        this.x = x;
        this.y = y;
        lazerTxt = new Texture("lazer.png");

        rect = new Collisions(x, y, WIDTH, HEIGHT);
    }

    public void updateLazer(float deltaTime) {
        y += SPEED * deltaTime;

        if(y > WinOrDie.HEIGHT) {
            removeLazer = true;
        }
        else
            removeLazer = false;

        rect.move(x, y);
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Collisions getCollisionRect() {
        return rect;
    }

    public Texture getLazerTxt() {
        return lazerTxt;
    }

}
