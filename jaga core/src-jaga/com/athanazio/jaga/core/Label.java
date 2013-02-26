package com.athanazio.jaga.core;

public class Label extends GameObject {
	public float x;
	public float y;
	public String text;

	public Label() {
		this.x = 0.0f;
		this.y = 0.0f;
		this.text = "";
	}

	public void draw() {
		super.draw();
		GameObject.provider.drawString(id, x, y, text);
	}

	public void move(float x, float y) {
		setX(this.x + x);
		setY(this.y + y);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setX(float x) {
		this.x = x;
	}


}
