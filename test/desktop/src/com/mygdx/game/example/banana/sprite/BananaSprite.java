package com.mygdx.game.example.banana.sprite;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.physicseditor.PhysicsShapeCache;


import java.util.HashMap;

public class BananaSprite extends ApplicationAdapter {
    HashMap<String, Sprite> sprites = new HashMap<>();
    TextureAtlas textureAtlas;

    SpriteBatch batch;
    OrthographicCamera camera;
    ExtendViewport viewport;

    Box2DDebugRenderer debugRenderer;

    World world;
    PhysicsShapeCache physicsBodies;
    Body banana;
    Body ground;

    //step world
    static final float STEP_TIME = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 2;
    float accumulator = 0;
    //**********
    static final float SCALE = 0.05f;


    @Override
    public void create() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();

        //масштабирование окна
        viewport = new ExtendViewport(50, 50, camera);//размер мира 50х50 МЕТРОВ
        //********************


        Box2D.init();//обязательная, однократная инициализация Box2D при старте приложения
        debugRenderer = new Box2DDebugRenderer();
        Vector2 gravity = new Vector2(0, -10);//направление гравитации. здесь давим на Y c силой -10(вниз)
        world = new World(gravity, true);



        //загрузка полигонов столкновений для спрайтов
        physicsBodies = new PhysicsShapeCache("physics.xml");

        //импорт текстуры
        textureAtlas = new TextureAtlas("bb.txt");

        addSprites();//добавляем все спрайты из нашего ассета bb.txt

        banana = createBody("banana", 10, 50, 0);

    }


    @Override
    public void render() {
        stepWorld();
        Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        Vector2 bananaPos = banana.getPosition();
        float degrees = (float) Math.toDegrees(banana.getAngle());
        drawSprite("banana", bananaPos.x, bananaPos.y, degrees);


        batch.end();
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
        //********************
        createGround();
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

    @Override
    public void dispose() {
        textureAtlas.dispose();
        sprites.clear();
        world.dispose();
        debugRenderer.dispose();
    }

    private void drawSprite(String name, float x, float y, float degrees) {
        Sprite sprite = sprites.get(name);
        sprite.setPosition(x, y);
        sprite.setRotation(degrees);
        sprite.setOrigin(0f,0f);
        sprite.draw(batch);
    }

    private void addSprites() {
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();
        for (TextureAtlas.AtlasRegion region : regions) {
            Sprite sprite = textureAtlas.createSprite(region.name);

            //масштабируем наши спрайты, что бы они помещались в границах мира
            float width = sprite.getWidth() * SCALE;
            float height = sprite.getHeight() * SCALE;

            sprite.setSize(width, height);
            sprite.setOrigin(0, 0);
            //***********


            sprites.put(region.name, sprite);
        }
    }

    private Body createBody(String name, float x, float y, float rotation) {
        Body body = physicsBodies.createBody(name, world, SCALE, SCALE);
        body.setTransform(x, y, rotation);
        return body;
    }

}
//todo В LibGDX достаточно просто повернуть спрайт.
//https://www.codeandweb.com/physicseditor/tutorials/libgdx-physics