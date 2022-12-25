package com.mygdx.game.example.shapes.first;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class ShapesFirst extends ApplicationAdapter {
    //base
    SpriteBatch batch;
    ExtendViewport viewport;
    OrthographicCamera camera;

    Box2DDebugRenderer debugRenderer;

    World world;
    Body ground;

    //step world
    static final float STEP_TIME = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 2;
    float accumulator = 0;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();

        //масштабирование окна
        viewport = new ExtendViewport(50, 50, camera);//размер мира 50х50 МЕТРОВ

        Box2D.init();//обязательная, однократная инициализация Box2D при старте приложения
        debugRenderer = new Box2DDebugRenderer();
//        debugRenderer.setDrawAABBs(true);

        Vector2 gravity = new Vector2(0, -10);//направление гравитации. здесь давим на Y c силой -10(вниз)
        world = new World(gravity, true);

        Vector2 firstTrianglePosition = new Vector2(35, 25);
        Vector2 secondTrianglePosition = new Vector2(35, 36);
        Vector2 thirdTrianglePosition = new Vector2(35, 47);
        MyCustomShapes.createTriangle(world, firstTrianglePosition);
        MyCustomShapes.createTriangle(world, secondTrianglePosition);
        MyCustomShapes.createTriangle(world, thirdTrianglePosition);

        Vector2 platformPosition = new Vector2(5, 25);//координаты расположения платформы(! 0 координата от центра объекта)
        MyCustomShapes.createPlatform(world, 20, 5, platformPosition);


        Vector2 circlePosition = new Vector2(35, 57);
        MyCustomShapes.createCircle(world, circlePosition, 5);
    }


    @Override
    public void render() {
        stepWorld();
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, camera.combined);

    }

    private void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();

        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;

            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }

    @Override
    public void resize(int width, int height) {
        //масштабирование окна
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
        createGround();
    }


    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }

    private void createGround() {
        if (ground != null) world.destroyBody(ground);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(camera.viewportWidth, 1);
        fixtureDef.shape = shape;
        ground = world.createBody(bodyDef);
        ground.createFixture(fixtureDef);
        ground.setTransform(0, 0, 0);
        shape.dispose();
    }

}
