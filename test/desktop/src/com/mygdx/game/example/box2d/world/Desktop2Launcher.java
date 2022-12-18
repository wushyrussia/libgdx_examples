package com.mygdx.game.example.box2d.world;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Desktop2Launcher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("My GDX Game");
		new Lwjgl3Application(new WorldExample(), config);
	}
}
