package com.winordie.game.bonuses;

public class HealthBonus extends Bonus {
    private static final String PATH = "healthBox.png";
    public HealthBonus(float x, float y) {
        super(x, y);
        super.setBonusTxt(PATH);
    }
}
