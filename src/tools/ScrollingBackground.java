package tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.winordie.game.WinOrDie;

public class ScrollingBackground {

    public static final int DEFAULT_SPEED = 80;
    public static final int ACCELARATION = 50;
    public static final int GOAL_REACH_ACCELARATION = 200;
    private Texture bgTxt;
    float y1, y2;

    private int speed;
    private int goalSpeed;

    public ScrollingBackground() {
        bgTxt = new Texture("Grid3.png");

        y1 = 0;
        y2 = bgTxt.getHeight();

        speed = 0;
        goalSpeed = DEFAULT_SPEED;


    }

    public void update(float deltaTime, SpriteBatch batch) {
        if(speed < goalSpeed) {
            speed += GOAL_REACH_ACCELARATION * deltaTime;
            if(speed > goalSpeed) {
                speed = goalSpeed;
            }
        }
        else if(speed > goalSpeed) {
            speed -= GOAL_REACH_ACCELARATION * deltaTime;
            if(speed < goalSpeed) {
                speed = goalSpeed;
            }
        }
        y1 -= speed * deltaTime;
        y2 -= speed * deltaTime;

        if(y1 + bgTxt.getHeight() <= 0) {
            y1 = y2 + bgTxt.getHeight();
        }
        if(y2 + bgTxt.getHeight() <= 0) {
            y2 = y1 + bgTxt.getHeight();
        }

        batch.draw(bgTxt, 0, y1, WinOrDie.WIDTH, bgTxt.getHeight());
        batch.draw(bgTxt, 0, y2, WinOrDie.WIDTH, bgTxt.getHeight());
    }
}
