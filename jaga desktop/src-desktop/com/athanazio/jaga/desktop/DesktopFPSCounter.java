package com.athanazio.jaga.desktop;

import java.text.DecimalFormat;

import com.athanazio.jaga.core.Game;

public class DesktopFPSCounter {

	public static final DecimalFormat format = new DecimalFormat("###0.00");

	private float frames;
	private String fps;
	private long start;
	private long current;

	public DesktopFPSCounter() {
		start = System.currentTimeMillis();
		frames = 0;
		fps = "???";
		Game.getInstance().setFps(fps);
	}

	public void update() {
		frames ++;
		current = System.currentTimeMillis();
		if( current - start > 1000 ){
			fps = format.format(frames);
			frames = 0;
			start = current;
			Game.getInstance().setFps(fps);
		}
	}

	public String getFPS() {
		return fps;
	}
}
