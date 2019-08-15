package com.winordie.game;

import com.badlogic.gdx.graphics.Texture;

import tools.Collisions;

public class Enemy {
    private final int SPEED = 200;

    private float x;
    private float y;

    public final static int WIDTH = 31;
    public final static int HEIGHT = 27;

    private Texture enemyTxt;

    public static boolean removeEnemy = false;

    private Collisions rect;

    public Enemy(float x, float y) {
        this.x = x;
        this.y = y;
        enemyTxt = new Texture("Asteroids/asteroid1.png");

        rect = new Collisions(x, y, WIDTH, HEIGHT);
    }

    public void updateEnemy(float deltaTime) {
        y -= SPEED * deltaTime;

        if(y < 0) {
            removeEnemy = true;
        }
        else
            removeEnemy = false;

        rect.move(x, y);
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public Texture getEnemyTxt() {
        return enemyTxt;
    }

    public Collisions getCollisionRect() {
        return rect;
    }

}
