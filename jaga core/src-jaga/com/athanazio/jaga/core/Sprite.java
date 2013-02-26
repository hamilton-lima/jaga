package com.athanazio.jaga.core;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Hashtable;

public class Sprite extends GameObject {
	private static final String DEFAULT_STATE = "default-state";

	private float x;
	private float y;
	private float centerX;
	private float centerY;
	private float drawX;
	private float drawY;

	private String state;

	private int current;

	private Hashtable states;

	private long step;

	private long start;

	private boolean visible;

	public Sprite() {
		centerX = 0;
		centerY = 0;

		setX(0);
		setY(0);

		this.state = DEFAULT_STATE;
		this.current = 0;
		this.states = new Hashtable();
		this.step = 60;
		this.start = System.currentTimeMillis();
		this.visible = true;
	}

	public void setState(String state) {
		if (!this.state.equals(state)) {
			this.state = state;
			current = 0;
			start = System.currentTimeMillis();
		}
	}

	public String getState() {
		return this.state;
	}

	/**
	 * only draws if is visible
	 */
	public void draw() {
		if (visible) {
			super.draw();

			String currentImage = getCurrent();
			System.out.println("draw " + currentImage);
			if (currentImage!= null) {
				GameObject.provider.draw(currentImage, drawX, drawY);
			}
		}
	}

	// CODE To Handle camera
	// int localX = getX() - game.getCamera().getX();
	// int localY = getY() - game.getCamera().getY();
	//
	// if (localX + getWidth() >= 0 && localY + getHeight() >= 0
	// && localX < game.getWidth() && localY < game.getHeight()) {
	//
	// Image image = getCurrent();
	// g.drawImage(image, localX, localY, null);
	//
	// }

	public void move(float x, float y) {
		setX(this.x + x);
		setY(this.y + y);
	}

	public void setCenterAtMiddle() {
		centerX = GameObject.provider.getWidth(getCurrent()) / 2F;
		centerY = GameObject.provider.getHeight(getCurrent()) / 2F;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		drawX = this.x - centerX;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		drawY = this.y - centerY;
	}

	public void add(String state, String imageName) {
		ArrayList list = getImageList(state);
		list.add(imageName);
	}

	public void add(String imageName) {
		add(this.state, imageName);
	}

	protected ArrayList getImageList() {
		return getImageList(this.state);
	}

	private ArrayList getImageList(String state) {
		ArrayList list = (ArrayList) states.get(state);
		if (list == null) {
			list = new ArrayList();
			states.put(state, list);
		}
		return list;
	}

	public void update() {
		super.update();
		
		long elapsed = System.currentTimeMillis() - start;
		if (elapsed > step) {
			current++;
			if (current >= getImageList().size()) {
				current = 0;
			}
			start = System.currentTimeMillis();
		}
	}

	public String getCurrent() {
		if (getImageList().size() > 0) {
			return (String) getImageList().get(current);
		} else {
			return null;
		}
	}

	public long getStep() {
		return step;
	}

	public void setStep(long step) {
		this.step = step;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
