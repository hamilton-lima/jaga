package com.athanazio.jaga.desktop.events;

import java.awt.event.KeyEvent;

public class KeyEventWrapper {

	private int keyPressed;

	private KeyEvent keyEvent;

	public KeyEventWrapper(int eventType, KeyEvent keyEvent) {
		this.keyPressed = eventType;
		this.keyEvent = keyEvent;
	}

	public KeyEvent getKeyEvent() {
		return keyEvent;
	}

	public int getEventType() {
		return keyPressed;
	}

}
