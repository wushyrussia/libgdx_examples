package com.mygdx.game.example.box2d.world;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class WorldExample extends ApplicationAdapter {
    World world;
    CircleShape circle;
    PolygonShape groundBox;
//    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    public OrthographicCamera camera;

    public void create () {
        Box2D.init();
        world = new World(new Vector2(0, -10), true);

        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
// Set our body's starting position in the world
        bodyDef.position.set(5, 10);

// Create our body in the world using our body definition
        Body body = world.createBody(bodyDef);

// Create a circle shape and set its radius to 6
        circle = new CircleShape();
        circle.setRadius(6f);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

// Create our body definition
        BodyDef groundBodyDef = new BodyDef();
// Set its world position
        groundBodyDef.position.set(new Vector2(0, 10));

// Create a body from the definition and add it to the world
        Body groundBody = world.createBody(groundBodyDef);

// Create a polygon shape
        groundBox = new PolygonShape();
// Set the polygon shape as a box which is twice the size of our view port and 20 high
// (setAsBox takes half-width and half-height as arguments)
//        groundBox.setAsBox(camera.viewportWidth, 10.0f);
// Create a fixture from our polygon shape and add it to our ground body
        groundBody.createFixture(groundBox, 0.0f);
// Clean up after ourselves
    }

    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        debugRenderer.render(world, cam.combined);
        world.step(1/60f, 6, 2);
    }

    public void resize (int width, int height) {
    }

    public void pause () {
    }

    public void resume () {
    }

    public void dispose () {
// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();
        // Clean up after ourselves
        groundBox.dispose();

    }
}