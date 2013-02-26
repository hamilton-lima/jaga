package com.athanazio.jaga.desktop.util;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import com.athanazio.jaga.desktop.ColisionHandler;
import com.athanazio.jaga.desktop.DesktopMainLoop;
import com.athanazio.jaga.desktop.GameComponent;

/**
 * Handle colision within GameComponents. In order to create groups of colision
 * use more than one instance
 * 
 * @author athanazio
 */
public class ColisionManager {

	private ArrayList gameComponents;
	private ArrayList colisionHandlers;

	public ColisionManager() {
		this.gameComponents = new ArrayList();
		this.colisionHandlers = new ArrayList();
	}

	public void add(GameComponent component) {
		gameComponents.add(component);
	}

	public void add(ColisionHandler handler) {
		colisionHandlers.add(handler);
	}

	public void remove(GameComponent component) {
		gameComponents.remove(component);
	}

	public void update(DesktopMainLoop game) {
		ArrayList temp = (ArrayList) gameComponents.clone();
		while (temp.size() > 1) {
			GameComponent a = (GameComponent) temp.get(0);
			temp.remove(0);

			Iterator iterator = temp.iterator();
			while (iterator.hasNext()) {
				GameComponent b = (GameComponent) iterator.next();
				if (b.isVisible() && !b.isDirty()) {
					if (a.getBounds().intersects(b.getBounds())) {
						colide(a, b);
					}
				}
			}
		}
	}

	public boolean move(GameComponent a, int x, int y) {
		Rectangle rectangle = a.getBounds();
		rectangle.setLocation(x, y);

		Iterator iterator = gameComponents.iterator();
		while (iterator.hasNext()) {
			GameComponent b = (GameComponent) iterator.next();
			if (b.isVisible() && !b.isDirty()) {
				if (rectangle.intersects(b.getBounds())) {
					colide(a, b);
					return false;
				}
			}
		}
		a.setPosition(x, y);
		return true;
	}

	public void checkColision(GameComponent a) {
		Rectangle rectangle = a.getBounds();

		Iterator iterator = gameComponents.iterator();
		while (iterator.hasNext()) {
			GameComponent b = (GameComponent) iterator.next();
			if (b.isVisible() && !b.isDirty()) {
				if (rectangle.intersects(b.getBounds())) {
					colide(a, b);
				}
			}
		}

	}

	private void colide(GameComponent a, GameComponent b) {
		ColisionHandler handler;
		for (int i = 0; i < colisionHandlers.size(); i++) {
			handler = (ColisionHandler) colisionHandlers.get(i);
			handler.onColide(a, b);
		}
	}

}
