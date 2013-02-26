package com.athanazio.jaga.desktop;

import java.awt.Rectangle;

public abstract class GameComponent {

	private int x;
	private int y;

	private int width;
	private int height;
	private Rectangle bounds;
	private Object id;
	private boolean dirty;
	private boolean visible;

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public GameComponent(int x, int y, Object id) {
		this.x = x;
		this.y = y;

		this.id = id;
		this.bounds = new Rectangle(x, y, width, height);
		dirty = false;
		visible = true;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		this.bounds = new Rectangle(x, y, width, height);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		this.bounds = new Rectangle(x, y, width, height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		this.bounds = new Rectangle(x, y, width, height);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public boolean isDirty() {
		return dirty;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
