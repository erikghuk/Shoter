package tools;

import com.winordie.game.bonuses.Bonus;
import com.winordie.game.bonuses.HealthBonus;
import com.winordie.game.bonuses.ShieldBonus;
import com.winordie.game.bonuses.WeaponBonus;

import java.util.Random;

public class RandomChooser {
    private Random random;


    public RandomChooser() {
        random = new Random();
    }

    public int choseRandomly(int bound) {
        return random.nextInt(bound) + 1;
    }

    public Bonus choseRandomBonus(float x, float y) {
        HealthBonus healthBonus = new HealthBonus(x, y);
        WeaponBonus weaponBonus = new WeaponBonus(x, y);
        ShieldBonus shieldBonus = new ShieldBonus(x, y);
        Bonus[] bonusesClasses = new Bonus[]{healthBonus, weaponBonus, shieldBonus};

        return bonusesClasses[random.nextInt(bonusesClasses.length)];
    }
}
