package com.mygdx.mc2d;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(1280,720);
		config.useVsync(true);
		config.setTitle("Minecraft 2D");
		new Lwjgl3Application(new MC2D(), config);
	}
}
