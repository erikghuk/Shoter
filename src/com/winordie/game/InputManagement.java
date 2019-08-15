package com.winordie.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InputManagement {
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean moveUp = false;
    private boolean moveDown = false;

    private boolean isLeftButtonPressed = false;
    private boolean isRightButtonPressed = false;

    private Texture buttonUnpressedTxt;
    private Texture buttonPressedTxt;
    public static final int BUTTON_HEIGHT = 77;
    public static final int BUTTON_WIDTH = 77;

    private int LeftbuttonCoordX1;
    private int LeftbuttonCoordX2;
    private int LeftbuttonCoordY1;
    private int LeftbuttonCoordY2;

    private int RightbuttonCoordX1;
    private int RightbuttonCoordX2;
    private int RightbuttonCoordY1;
    private int RightbuttonCoordY2;

    public InputManagement(){
        LeftbuttonCoordX1 = 10;
        LeftbuttonCoordX2 = LeftbuttonCoordX1 + BUTTON_WIDTH;
        LeftbuttonCoordY1 = 100;
        LeftbuttonCoordY2 = LeftbuttonCoordY1 + BUTTON_HEIGHT;

        RightbuttonCoordX1 = Gdx.graphics.getWidth() - 10 - BUTTON_WIDTH;
        RightbuttonCoordX2 = RightbuttonCoordX1 + BUTTON_WIDTH;
        RightbuttonCoordY1 = 100;
        RightbuttonCoordY2 = RightbuttonCoordY1 + BUTTON_HEIGHT;

        buttonUnpressedTxt = new Texture("buttonUnpressed.png");
        buttonPressedTxt = new Texture("buttonPressed.png");
    }

    public void updateStatus() {
        //KeyBoard listener
        moveLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        moveRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        moveUp = Gdx.input.isKeyPressed(Input.Keys.UP);
        moveDown = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if(Gdx.input.isTouched() && Gdx.input.getX() >= WinOrDie.WIDTH / 2) {
            moveRight = true;
        }

        if(Gdx.input.isTouched() && Gdx.input.getX() <= WinOrDie.WIDTH / 2) {
            moveLeft = true;
        }

        //Mouse Listener
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if(x > LeftbuttonCoordX1 && x < LeftbuttonCoordX2 && y > LeftbuttonCoordY1 && y < LeftbuttonCoordY2) {
                isLeftButtonPressed = true;
                moveRight = false;
                moveLeft = true;
            }
            if(x > RightbuttonCoordX1 && x < RightbuttonCoordX2 && y > RightbuttonCoordY1 && y < RightbuttonCoordY2) {
                isRightButtonPressed = true;
                moveRight = true;
                moveLeft = false;
            }

        }
        else if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            isLeftButtonPressed = false;
            isRightButtonPressed = false;
        }
    }

    public void renderButton(SpriteBatch buttonBatch) {
        if(isLeftButtonPressed ) {
            buttonBatch.draw(buttonPressedTxt, LeftbuttonCoordX1, Gdx.graphics.getHeight() - LeftbuttonCoordY1 - BUTTON_HEIGHT, buttonPressedTxt.getWidth(), buttonPressedTxt.getHeight());
        }
        else {
            buttonBatch.draw(buttonUnpressedTxt, LeftbuttonCoordX1, Gdx.graphics.getHeight() - LeftbuttonCoordY1 - BUTTON_HEIGHT, buttonUnpressedTxt.getWidth(), buttonUnpressedTxt.getHeight());
        }
        if(isRightButtonPressed ) {
            buttonBatch.draw(buttonPressedTxt, RightbuttonCoordX1, Gdx.graphics.getHeight() - RightbuttonCoordY1 - BUTTON_HEIGHT, buttonPressedTxt.getWidth(), buttonPressedTxt.getHeight());
        }
        else {
            buttonBatch.draw(buttonUnpressedTxt, RightbuttonCoordX1, Gdx.graphics.getHeight() - RightbuttonCoordY1 - BUTTON_HEIGHT, buttonUnpressedTxt.getWidth(), buttonUnpressedTxt.getHeight());
        }
    }



    public boolean isMoveLeft() {
        return moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }
}

