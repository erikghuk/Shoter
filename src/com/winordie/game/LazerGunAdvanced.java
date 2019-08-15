package com.winordie.game;

public class LazerGunAdvanced extends LazerGun {
    private  final int SPEED = 500;


    public LazerGunAdvanced(float x, float y) {
        super(x, y);
    }

    @Override
    public void updateLazer(float deltaTime) {
        super.updateLazer(deltaTime);
        setX(getX() + SPEED * deltaTime);


    }
}
