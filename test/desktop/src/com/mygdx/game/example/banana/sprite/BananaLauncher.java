package com.mygdx.game.example.banana.sprite;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.example.box2d.world.WorldExample;

public class BananaLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("My GDX Game");
		new Lwjgl3Application(new BananaSprite(), config);
	}
}
