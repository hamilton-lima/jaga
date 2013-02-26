package com.athanazio.jaga.android;

import com.athanazio.jaga.core.Game;
import com.athanazio.jaga.core.GameObject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class GameLoop extends View implements Runnable {

	private static final String TAG = "JAGA GAME VIEW";

	private static final int INTERVAL = 10;

	private boolean running = true;

	private AndroidEnvironment environment;

	private LuaHelper lua;

	private FPSCounter fps;

	public GameLoop(Context context, Resources resources, String packageName) {
		super(context);
		this.environment = new AndroidEnvironment(resources, packageName);
		GameObject.setEnvironmentProvider(environment);

		this.lua = new LuaHelper(resources, packageName);
		environment.setLuaHelper(lua);
		
		lua.load("setup");
		lua.load("main");

		lua.call("load");

		this.fps = new FPSCounter();

		setFocusable(true);
		setClickable(true);
		setLongClickable(true);

		// Set the background
		this.setBackgroundColor(Color.WHITE);

		Log.i(TAG, "game view created");

		Thread monitorThread = new Thread(this);
		monitorThread.setPriority(Thread.MIN_PRIORITY);
		monitorThread.start();
	}

	private void update() {
		environment.checkKeyEventsTimeOut();
		fps.update();
		Game.getInstance().update();
		lua.call("update");
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);
		environment.setCanvas(canvas);
		Game.getInstance().draw();
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return environment.onKeyUp(keyCode);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return environment.onKeyDown(keyCode);
	}

	public boolean onTrackballEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			environment.clearKeys();
			return true;
		case MotionEvent.ACTION_UP:
			environment.clearKeys();
			return true;
		case MotionEvent.ACTION_CANCEL:
			environment.clearKeys();
			return true;
		case MotionEvent.ACTION_MOVE:

			if (event.getX() == 0) {
				environment.onKeyUp(Game.LEFT);
				environment.onKeyUp(Game.RIGHT);
			} else {
				if (event.getX() > 0) {
					environment.onKeyUp(Game.LEFT);
					environment.onKeyDown(Game.RIGHT);
				} else {
					environment.onKeyUp(Game.RIGHT);
					environment.onKeyDown(Game.LEFT);
				}
			}

			if (event.getY() == 0) {
				environment.onKeyUp(Game.UP);
				environment.onKeyUp(Game.DOWN);
			} else {
				if (event.getY() > 0) {
					environment.onKeyUp(Game.UP);
					environment.onKeyDown(Game.DOWN);
				} else {
					environment.onKeyUp(Game.DOWN);
					environment.onKeyDown(Game.UP);
				}
			}
			return true;
		}

		return super.onTrackballEvent(event);
	}

	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Game.getInstance().updatePointer(event.getRawX(),event.getRawY());
			lua.call("pointerDown");

			return true;
		case MotionEvent.ACTION_UP:
			Game.getInstance().updatePointer(event.getRawX(),event.getRawY());
			lua.call("pointerUp");
			return true;

		}
		return super.onTouchEvent(event);
	}

	public void run() {
		while (running) {
			try {
				Thread.sleep(INTERVAL);
			} catch (InterruptedException e) {
				Log.e(TAG, "main loop finished");
			}
			update();
			postInvalidate();
		}
	}

	public void release() {
		running = false;
		environment.release();
	}

}
