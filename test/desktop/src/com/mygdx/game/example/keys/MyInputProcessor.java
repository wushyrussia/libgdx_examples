package com.mygdx.game.example.keys;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {
    DynamicCircle dynamicCircle;

    @Override
    public boolean keyDown(int keycode) {
        if (dynamicCircle == null) {
            return false;
        }

        switch (keycode) {
            case Input.Keys.A:
                dynamicCircle.setLeftMove(true);
                break;
            case Input.Keys.D:
                dynamicCircle.setRightMove(true);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (dynamicCircle == null) {
            return false;
        }

        switch (keycode) {
            case Input.Keys.A:
                dynamicCircle.setLeftMove(false);
                break;
            case Input.Keys.D:
                dynamicCircle.setRightMove(false);
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public void setDynamicCircle(DynamicCircle dynamicCircle) {
        this.dynamicCircle = dynamicCircle;
    }
}
