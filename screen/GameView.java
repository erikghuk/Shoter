package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.winordie.game.bonuses.Bonus;
import com.winordie.game.Enemy;
import com.winordie.game.InputManagement;
import com.winordie.game.LazerGun;
import com.winordie.game.Shuttle;
import com.winordie.game.WinOrDie;
import com.winordie.game.bonuses.HealthBonus;
import com.winordie.game.bonuses.ShieldBonus;
import com.winordie.game.bonuses.WeaponBonus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import tools.Collisions;
import tools.RandomChooser;
import tools.ScrollingBackground;

public class GameView implements Screen {

    private WinOrDie game;
    private ScrollingBackground bg;
    private Collisions shuttleCollisionRect;
    private Shuttle shuttle;
    private ArrayList<LazerGun> bullets;
    private ArrayList<Bonus> bonuses;
    private ArrayList<Enemy> enemies;
    private InputManagement userInput;

    private SpriteBatch batch;
    private Texture shuttleImg;
    private Texture healthBar;


    private static final float MIN_ENEMY_SPAWN_TIME = 0.3f;
    private static final float MAX_ENEMY_SPAWN_TIME = 0.6f;
    private float enemySpawnTime;

    private int intervalOfBonusBoxAppearing;
    private boolean bonusReady;
    private int removedEnemiesCount;
    private Random random;


    public GameView(WinOrDie myGame) {

        this.game = myGame;

    }

    @Override
    public void show() {
        intervalOfBonusBoxAppearing = 10;
        bonusReady = false;
        removedEnemiesCount = 0;
        bg = new ScrollingBackground();
        batch = new SpriteBatch();
        shuttle = new Shuttle();
        bullets = new ArrayList<LazerGun>();
        enemies = new ArrayList<Enemy>();
        bonuses = new ArrayList<Bonus>();
        random = new Random();
        enemySpawnTime = random.nextFloat() * (MAX_ENEMY_SPAWN_TIME - MIN_ENEMY_SPAWN_TIME) + MIN_ENEMY_SPAWN_TIME;
        userInput = new InputManagement();
        shuttleCollisionRect = new Collisions(shuttle.getX(), shuttle.getY(), Shuttle.SHATTLE_WIDTH, Shuttle.SHATTLE_HEIGHT);
        shuttleImg = shuttle.getImg();
        healthBar = new Texture("blank2.png");
        shuttle.setShuttleDir(1);
        shuttle.setX((float)WinOrDie.WIDTH/2 - Shuttle.getShattleWidth()/2);
        shuttle.setY(5);

    }

    private void shuttleMove() {

        if(userInput.isMoveLeft() && shuttle.getX() > 0) {
            shuttle.setShuttleDir(-1);
            shuttle.setX(shuttle.getX() + shuttle.getSpeed() * Gdx.graphics.getDeltaTime() * shuttle.getShuttleDir());
        }
        if(userInput.isMoveRight() && shuttle.getX() < Gdx.graphics.getWidth()-Shuttle.getShattleWidth()) {
            shuttle.setShuttleDir(1);
            shuttle.setX(shuttle.getX() + shuttle.getSpeed() * Gdx.graphics.getDeltaTime() * shuttle.getShuttleDir());
        }
        if (!userInput.isMoveDown() && userInput.isMoveUp() && shuttle.getY() < WinOrDie.HEIGHT) {
            shuttle.setShuttleDir(1);
            shuttle.setY(shuttle.getY() + shuttle.getSpeed() * Gdx.graphics.getDeltaTime() * shuttle.getShuttleDir());
        }
        if(!userInput.isMoveUp() && userInput.isMoveDown() && shuttle.getY() > 2) {
            shuttle.setShuttleDir(-1);
            shuttle.setY(shuttle.getY() + shuttle.getSpeed() * Gdx.graphics.getDeltaTime() * shuttle.getShuttleDir());
        }






    }

    private void bonusBoxesAppearing(Enemy enemy) {
        RandomChooser randomChooser = new RandomChooser();
        int randomNum = randomChooser.choseRandomly(intervalOfBonusBoxAppearing);
        if(removedEnemiesCount == randomNum)  {
            bonusReady = true;
            bonuses.add(randomChooser.choseRandomBonus(enemy.getX(), enemy.getY()));
            removedEnemiesCount = 0;
        }
        else
        if(removedEnemiesCount > intervalOfBonusBoxAppearing)
            removedEnemiesCount = 0;
    }

    private void checkCollision() {
        Iterator<LazerGun> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            LazerGun bullet = bulletIterator.next();
            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                // if we have collisions
                if(bullet.getCollisionRect().collidesWith(enemy.getCollisionRect())) {
                    bulletIterator.remove();
                    enemyIterator.remove();
                    removedEnemiesCount++;
                    bonusBoxesAppearing(enemy);
                }
            }
        }
    }


    private void checkShuttleCollision() {
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            // if we have collisions with Shuttle
            if (enemy.getCollisionRect().collidesWith(shuttleCollisionRect)) {
                enemyIterator.remove();
                shuttle.setHealthValue(shuttle.getHealthValue() - 0.15f);
                if(shuttle.getHealthValue() <= 0) {
                    game.setScreen(new GameOver(game));
                }
            }
        }
    }

    private void catchingBonus() {
        if(bonusReady) {
            Iterator<Bonus> bonusIterator = bonuses.iterator();
            while (bonusIterator.hasNext()) {
                Bonus bonus = bonusIterator.next();
                // if we have collisions with Shuttle
                if (bonus.getCollisionRect().collidesWith(shuttleCollisionRect)) {
                    bonusIterator.remove();

                    if(bonus instanceof HealthBonus) {
                        if(shuttle.getHealthValue() < 1) {
                            shuttle.setHealthValue(shuttle.getHealthValue() + 0.15f);
                            if (shuttle.getHealthValue() > 1)
                                shuttle.setHealthValue(1);
                        }
                    }
                    else if(bonus instanceof ShieldBonus) {

                        System.out.println("SHIELD BONUS");
                    }
                    else if(bonus instanceof WeaponBonus) {
                        if(shuttle.getWeaponLevel() < Shuttle.MAX_WEAPON_LEVEL) {
                            shuttle.setWeaponLevel(shuttle.getWeaponLevel() + 1);
                        }
                    }
                }
            }
        }
    }

    private void shotLazer(int weaponLevel) {
        switch(weaponLevel) {
            case 0:
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth() / 2) - 5,  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                break;
            case 1:
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth()/2),  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth()/2)-15,  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                break;
            case 2:
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth()/2),  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth()/2)-15,  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth()/2)-35,  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth()/2)+15,  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                break;
            case 3:
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth()/2),  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth()/2)-15,  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth()/2)-35,  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth()/2)+15,  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth()/2)-55,  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                bullets.add(new LazerGun(shuttle.getX() + (Shuttle.getShattleWidth()/2)+35,  shuttle.getY() + Shuttle.SHATTLE_HEIGHT));
                break;

        }
    }

    private void shuttleSpeed() {

    }

    @Override
    public void render(float delta) {
        // Ship movement
        userInput.updateStatus();
        shuttleMove();

        // Enemies Spawn
        enemySpawnTime -= delta;
        if(enemySpawnTime <= 0) {
            enemySpawnTime = random.nextFloat() * (MAX_ENEMY_SPAWN_TIME - MIN_ENEMY_SPAWN_TIME) + MIN_ENEMY_SPAWN_TIME;
            enemies.add(new Enemy(random.nextInt(WinOrDie.WIDTH) + Enemy.WIDTH, WinOrDie.HEIGHT));
        }

        // Shot the Lazer
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            shotLazer(shuttle.getWeaponLevel());
        }

        //Enemies income and remove them on the floor
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.updateEnemy(delta);
            // Remove Enemy when they reach floor
            if (Enemy.removeEnemy) {
                enemyIterator.remove();
            }
        }

        //Bonus income and remove them on the floor
        Iterator<Bonus> bonusIterator = bonuses.iterator();
        while (bonusIterator.hasNext()) {
            Bonus bonus = bonusIterator.next();
            bonus.updateBonus(delta);
            // Remove Enemy when they reach floor
            if (Bonus.removeBox) {
                bonusIterator.remove();
            }
        }

        //---------------------------------------------
        ArrayList<LazerGun> removeBulletsArray = new ArrayList<LazerGun>();
        for (LazerGun bullet: bullets) {
            bullet.updateLazer(delta);
            if(LazerGun.removeLazer) {
                removeBulletsArray.add(bullet);
            }
        }
        bullets.removeAll(removeBulletsArray);

        //---------------------------------------------
        shuttleCollisionRect.move(shuttle.getX(), shuttle.getY());
        // After all updates we check collisions
        checkCollision();
        checkShuttleCollision();
        catchingBonus();


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        bg.update(delta, batch);
        for(Enemy enemy: enemies) {
            batch.draw(enemy.getEnemyTxt(), enemy.getX(), enemy.getY());
        }

        for(LazerGun bullet: bullets) {
            batch.draw(bullet.getLazerTxt(), bullet.getX(), bullet.getY());
        }


        batch.draw(shuttleImg, shuttle.getX(), shuttle.getY());


        if(bonusReady) {
            for(Bonus bonus: bonuses) {
                batch.draw(bonus.getBonusTxt(), bonus.getX(), bonus.getY());
            }
        }
        batch.draw(healthBar, shuttle.getX(), shuttle.getY(), Shuttle.SHATTLE_WIDTH * shuttle.getHealthValue(), 5);

        //userInput.renderButton(batch);

        batch.end();
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
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        shuttleImg.dispose();
    }
}
