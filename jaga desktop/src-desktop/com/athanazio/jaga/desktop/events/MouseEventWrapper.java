package com.athanazio.jaga.desktop.events;

import java.awt.event.MouseEvent;

public class MouseEventWrapper {

	private int eventType;
	private MouseEvent event;

	public MouseEventWrapper(int eventType, MouseEvent event) {
		this.eventType = eventType;
		this.event = event;
	}

	public MouseEvent getEvent() {
		return event;
	}

	public int getEventType() {
		return eventType;
	}
}
