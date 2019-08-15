package com.winordie.game.bonuses;

public class ShieldBonus extends Bonus {
    private static final String PATH = "shieldBox.png";
    public ShieldBonus(float x, float y) {
        super(x, y);
        super.setBonusTxt(PATH);
    }
}
