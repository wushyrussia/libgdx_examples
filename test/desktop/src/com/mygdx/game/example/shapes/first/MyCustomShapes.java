package com.mygdx.game.example.shapes.first;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class MyCustomShapes {

    public static void createPlatform(World world, float width, float height, Vector2 position) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        Body platform = world.createBody(def);
        platform.setTransform(position, 0);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(width, height);
        platform.createFixture(polygonShape, 1);
        polygonShape.dispose();

    }

    public static void createTriangle(World world, Vector2 position) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        Body platform = world.createBody(def);
        platform.setTransform(position, 90);

        Vector2 first = new Vector2(5, 0);
        Vector2 second = new Vector2(0, 10);
        Vector2 third = new Vector2(10, 10);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.set(new Vector2[] {first, second, third});
        platform.createFixture(polygonShape, 1);
        polygonShape.dispose();
    }

    public static Body createCircle(World world, Vector2 position, float radius) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        Body platform = world.createBody(def);
        platform.setTransform(position, 0);


        CircleShape shape = new CircleShape ();
        shape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 1;
        fixtureDef.density = 2;
        fixtureDef.restitution = 1;
        fixtureDef.shape = shape;
        platform.createFixture(fixtureDef);

        shape.dispose();

        return platform;
    }
}
