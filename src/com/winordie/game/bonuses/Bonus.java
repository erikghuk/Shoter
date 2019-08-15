package com.winordie.game.bonuses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.winordie.game.WinOrDie;

import tools.Collisions;

public class Bonus {
    private final int SPEED = 70;

    private float x;
    private float y;

    public final static int WIDTH = 30;
    public final static int HEIGHT = 30;
    private Collisions rect;
    private Texture bonusTxt;

    public static boolean removeBox = false;


    public Bonus(float x, float y) {
        this.x = x;
        this.y = y;
        rect = new Collisions(x, y, WIDTH, HEIGHT);


    }

    public void setBonusTxt(String path) {
        bonusTxt = new Texture(path);
    }

    public void updateBonus(float deltaTime) {
        y -= SPEED * deltaTime;



        if(y < 0) {
            removeBox = true;
        }
        else
            removeBox = false;

        rect.move(x, y);
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public Texture getBonusTxt() {
        return bonusTxt;
    }

    public Collisions getCollisionRect() {
        return rect;
    }

}
