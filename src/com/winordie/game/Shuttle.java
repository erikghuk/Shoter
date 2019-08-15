package com.winordie.game;

import com.badlogic.gdx.graphics.Texture;

import tools.Collisions;

public class Shuttle {

    public final static int SHATTLE_HEIGHT = 86;
    public final static int SHATTLE_WIDTH = 65;
    private float healthValue;
    private int weaponLevel;
    public static final int MAX_WEAPON_LEVEL = 3;
    private Texture img;


    private float x;
    private float y;
    private float shuttleDir;

    private float speed = 240;

    public Shuttle() {
        weaponLevel = 0;
        healthValue = 1;
        img = new Texture("Ship.png");
    }

    public static float getShattleWidth() {
        return SHATTLE_WIDTH;
    }

    public Texture getImg() {
        return img;
    }

    public float getHealthValue() {
        return healthValue;
    }

    public void setHealthValue(float healthValue) {
        this.healthValue = healthValue;
    }

    public int getWeaponLevel() {
        return weaponLevel;
    }

    public void setWeaponLevel(int weaponLevel) {
        this.weaponLevel = weaponLevel;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getShuttleDir() {
        return shuttleDir;
    }

    public void setShuttleDir(int shuttleDir) {
        this.shuttleDir = shuttleDir;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
