package com.mygdx.game.example.circle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CircleExample extends ApplicationAdapter {
    ShapeRenderer renderer;
    float x;
    float r;

    Sprite front;

    public void create () {
        renderer = new ShapeRenderer();
        r = 30;
        x = r;
    }

    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.SKY);
        renderer.circle(x, 30, r);
        renderer.end();

        x++;
        if (x > Gdx.graphics.getWidth() - r) {
            x = r;
        }
    }

    public void resize (int width, int height) {
    }

    public void pause () {
    }

    public void resume () {
    }

    public void dispose () {
    }
}