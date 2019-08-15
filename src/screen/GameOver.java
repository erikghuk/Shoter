package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.winordie.game.WinOrDie;

public class GameOver implements Screen {

    WinOrDie game;

    public static final int PLAY_AGAIN_BUTTON_WIDTH = 180;
    public static final int PLAY_AGAIN_BUTTON_HEIGHT = 44;

    public static final int HEIGHT = 51;
    public static final int WIDTH = 361;

    private Texture gameOverTxt;
    private Texture playAgain;
    private SpriteBatch batch;

    public GameOver(WinOrDie game) {
        this.game = game;
    }

    @Override
    public void show() {
        gameOverTxt = new Texture("gameover.png");
        playAgain = new Texture("playAgain.png");
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        float x1 = (WinOrDie.WIDTH >> 1) - (PLAY_AGAIN_BUTTON_WIDTH >> 1);
        float x2 = (WinOrDie.WIDTH >> 1) + (PLAY_AGAIN_BUTTON_WIDTH >> 1);


        batch.draw(gameOverTxt, WinOrDie.WIDTH/2 - WIDTH/2, WinOrDie.HEIGHT/2 - HEIGHT/2);
        batch.draw(playAgain, WinOrDie.WIDTH / 2 - PLAY_AGAIN_BUTTON_WIDTH / 2, WinOrDie.HEIGHT / 2 - (2 * HEIGHT));
        if(Gdx.input.getX() > x1 && Gdx.input.getX() < x2 &&
                Gdx.input.getY() < WinOrDie.HEIGHT - 218 && Gdx.input.getY() > WinOrDie.HEIGHT - 218 - PLAY_AGAIN_BUTTON_HEIGHT) {
            if(Gdx.input.isTouched()) {
                game.setScreen(new GameView(game));
            }
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

    }
}
