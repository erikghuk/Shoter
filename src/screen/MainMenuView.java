package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.winordie.game.WinOrDie;

public class MainMenuView implements Screen {

    public static final int BUTTON_WIDTH = 155;
    public static final int BUTTON_HEIGHT = 57;

    private WinOrDie game;
    private Texture buttonPlayInactiveTxt;
    private Texture buttonPlayActiveTxt;
    private Texture buttonExitInactiveTxt;
    private Texture buttonExitActiveTxt;
    private SpriteBatch batch;

    public MainMenuView(WinOrDie game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        buttonPlayInactiveTxt = new Texture("MenuButtons/buttonPlayInactive.png");
        buttonExitInactiveTxt = new Texture("MenuButtons/buttonExitInactive.png");
        buttonPlayActiveTxt = new Texture("MenuButtons/buttonPlayActive.png");
        buttonExitActiveTxt = new Texture("MenuButtons/buttonExitActive.png");
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        float x1 = (WinOrDie.WIDTH >> 1) - (BUTTON_WIDTH >> 1);
        float x2 = (WinOrDie.WIDTH >> 1) + (BUTTON_WIDTH >> 1);




        if(Gdx.input.getX() >= x1 && Gdx.input.getX() <= x2 && Gdx.input.getY() >= WinOrDie.HEIGHT - BUTTON_HEIGHT - 170
                && Gdx.input.getY() <= WinOrDie.HEIGHT - 170) {
            batch.draw(buttonPlayActiveTxt, (WinOrDie.WIDTH >> 1) - (BUTTON_WIDTH >> 1), 170, BUTTON_WIDTH, BUTTON_HEIGHT);
            batch.draw(buttonExitInactiveTxt, (WinOrDie.WIDTH >> 1) - (BUTTON_WIDTH >> 1), 80, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                game.setScreen(new GameView(game));
            }
        }
        else if(Gdx.input.getX() >= x1 && Gdx.input.getX() <= x2 &&
                Gdx.input.getY() >= WinOrDie.HEIGHT - BUTTON_HEIGHT - 80 && Gdx.input.getY() <= WinOrDie.HEIGHT - 80) {
            batch.draw(buttonPlayInactiveTxt, (WinOrDie.WIDTH >> 1) - (BUTTON_WIDTH >> 1), 170, BUTTON_WIDTH, BUTTON_HEIGHT);
            batch.draw(buttonExitActiveTxt, (WinOrDie.WIDTH >> 1) - (BUTTON_WIDTH >> 1), 80, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }
        else {
            batch.draw(buttonPlayInactiveTxt, (WinOrDie.WIDTH >> 1) - (BUTTON_WIDTH >> 1), 170, BUTTON_WIDTH, BUTTON_HEIGHT);
            batch.draw(buttonExitInactiveTxt, (WinOrDie.WIDTH >> 1) - (BUTTON_WIDTH >> 1), 80, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
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

    }

    @Override
    public void dispose() {
        batch.dispose();
        buttonPlayInactiveTxt.dispose();
        buttonExitInactiveTxt.dispose();
    }
}
