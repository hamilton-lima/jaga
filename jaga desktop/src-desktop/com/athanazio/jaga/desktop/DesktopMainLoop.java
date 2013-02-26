/*
 * Created on Oct 10, 2003
 */
package com.athanazio.jaga.desktop;

import java.awt.Graphics2D;
import java.awt.Point;

import com.athanazio.jaga.core.Game;
import com.athanazio.jaga.core.GameObject;
import com.athanazio.jaga.desktop.events.EventManager;

/**
 * @author hamilton
 * @version Oct 10, 2003
 * 
 */
public class DesktopMainLoop implements Runnable {

	private int SLEEP_DEFAULT = 10;

	private EventManager eventManager;
	private FastCanvas drawCanvas;

	private boolean keepDoing = true;
	private int height;
	private int width;
	private GameCamera camera;
	private DesktopFPSCounter fps;
	private DesktopEnvironment environment;
	private LuaHelper lua;
	private GameVisualContainer container;
	private Point locationOnScreen;
	
	Action action;

	public DesktopMainLoop(int x, int y, int width, int height) {

		this.drawCanvas = new FastCanvas();
		drawCanvas.setBounds(0, 0, width, height);
		this.width = width;
		this.height = height;
		eventManager = new EventManager(this);
		camera = new GameCamera(0, 0);
		fps = new DesktopFPSCounter();
		environment = new DesktopEnvironment();
		
		// notify the core game classes about the current implementation
		GameObject.setEnvironmentProvider(environment);
		
		// create the lua interpreter
		lua = new LuaHelper(environment);

		// this enables the callback require()
		environment.setLuaHelper(lua);

		lua.load("core/setup");
		lua.load("main");
		lua.call("load");
		
		action = new Action(this) {
			public void doIt() {

				while (keepDoing) {

					update();

					// consume the events
					eventManager.consumeKeyEvents(environment);
					eventManager.consumeMouseEvents(environment);
					eventManager.consumeMouseWheelEvents(environment);

					// give the scene the Graphics to draw on
					draw(drawCanvas.getBufferGraphics());

					// notify the container to update it self
					container.update();

					try {
						Thread.sleep(SLEEP_DEFAULT);
					} catch (InterruptedException e) {
					}

				}

			}

			private void update() {
				fps.update();
				Game.getInstance().update();
				lua.call("update");
			}

			public void draw(Graphics2D g) {
				environment.setGraphics(g);
				Game.getInstance().draw();
			}			
			
		};
	}


	public void run() {
		action.doIt();
	}

	public void stop() {
		keepDoing = false;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public FastCanvas getDrawCanvas() {
		return drawCanvas;
	}

	public int getWidth() {
		return drawCanvas.getWidth();
	}

	public int getHeight() {
		return drawCanvas.getHeight();
	}

	public int getOriginalWidth() {
		return this.width;
	}

	public int getOriginalHeight() {
		return this.height;
	}

	public void setContainer(GameVisualContainer container) {
		this.container = container;
	}

	public boolean isKeyPressed(int key) {
		return getEventManager().getKeyboard().isKeyPressed(key);
	}

	public Point getLocationOnScreen() {
		return locationOnScreen;
	}

	public void setLocationOnScreen(Point locationOnScreen) {
		this.locationOnScreen = locationOnScreen;
	}

	public GameVisualContainer getContainer() {
		return container;
	}

	public GameCamera getCamera() {
		return camera;
	}

	public void setCamera(GameCamera camera) {
		this.camera = camera;
	}

}
