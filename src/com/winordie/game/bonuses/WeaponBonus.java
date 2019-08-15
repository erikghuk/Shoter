package com.winordie.game.bonuses;

public class WeaponBonus extends Bonus {
    private static final String PATH = "weaponBox.png";
    public WeaponBonus(float x, float y) {
        super(x, y);
        super.setBonusTxt(PATH);
    }
}
