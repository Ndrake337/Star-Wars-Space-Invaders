package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.SpaceInvaders;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "SpaceInvaders by Draken";
                config.height = 600;
                config.width = 800;
                config.resizable = false;
                config.foregroundFPS = 27;
                
		new LwjglApplication(new SpaceInvaders(), config);
	}
}