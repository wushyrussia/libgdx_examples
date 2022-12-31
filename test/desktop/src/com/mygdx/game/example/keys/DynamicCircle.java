package com.mygdx.game.example.keys;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.example.shapes.first.MyCustomShapes;

//todo http://www.libgdx.ru/2013/09/mouse-touch-keyboard.html

public class DynamicCircle {
    Body circle;
    boolean leftMove;
    boolean rightMove;


    public DynamicCircle(World world, Vector2 circlePosition, int radius) {
        this.circle = MyCustomShapes.createCircle(world, circlePosition, radius);
    }

    public void moveRight() {
        Transform transform = this.circle.getTransform();
        float storedX = transform.getPosition().x;
        float y = transform.getPosition().y;
        this.circle.setTransform(storedX + 1, y, 0);
    }

    public void moveLeft() {
        Transform transform = this.circle.getTransform();
        float storedX = transform.getPosition().x;
        float y = transform.getPosition().y;
        this.circle.setTransform(storedX - 0.5f, y, 0);
    }

    public void updateMovement() {
        if (leftMove) {
            moveLeft();
        }
        if (rightMove) {
            moveRight();
        }
    }

    public void setLeftMove(boolean leftMove) {
        this.leftMove = leftMove;
    }

    public void setRightMove(boolean rightMove) {
        this.rightMove = rightMove;
    }
}
