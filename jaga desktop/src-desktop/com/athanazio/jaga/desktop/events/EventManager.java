package com.athanazio.jaga.desktop.events;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import com.athanazio.jaga.desktop.DesktopMainLoop;
import com.athanazio.jaga.desktop.util.ProducerConsumerQueue;

public class EventManager {

	private static final int QUEUE_SIZE = 50;

	private ProducerConsumerQueue mouseEvents;

	private ProducerConsumerQueue mouseWheelEvents;

	private ProducerConsumerQueue keyEvents;

	private MouseDevice mouse;

	private KeyboardDevice keyboard;

	private DesktopMainLoop game;

	public void consumeKeyEvents(KeyEventHandler eventHandler) {
		KeyEventWrapper e = (KeyEventWrapper) keyEvents.pop();
		if (e != null) {
			eventHandler.onKeyEvent(e);
		}
	}

	public void consumeMouseEvents(MouseEventHandler eventHandler) {
		MouseEventWrapper e = (MouseEventWrapper) mouseEvents.pop();
		if (e != null) {
			eventHandler.onMouseEvent(e);
		}
	}

	public void consumeMouseWheelEvents(MouseWheelEventHandler eventHandler) {
		MouseWheelEvent e = (MouseWheelEvent) mouseWheelEvents.pop();
		if (e != null) {
			eventHandler.onMouseWheel(e);
		}
	}

	public KeyboardDevice getKeyboard() {
		return keyboard;
	}

	public EventManager(DesktopMainLoop game) {
		this.game = game;
		mouseEvents = new ProducerConsumerQueue(QUEUE_SIZE);
		mouseWheelEvents = new ProducerConsumerQueue(QUEUE_SIZE);
		keyEvents = new ProducerConsumerQueue(QUEUE_SIZE);
		mouse = new MouseDevice();
		keyboard = new KeyboardDevice();
	}

	public void addMouseEvent(int eventType, MouseEvent event, Point point) {

		double x = game.getOriginalWidth()
				/ (double) game.getContainer().getWidth();
		double y = game.getOriginalHeight()
				/ (double) game.getContainer().getHeight();

		Point p = new Point();
		p.x = (int) (event.getPoint().x * x);
		p.y = (int) (event.getPoint().y * y);

		mouse.setPoint(p);

		// we dont need to keep track of all the mouse moves
		// just store the mouse position
		if (eventType != MouseEventHandler.MOUSE_MOVED) {
			mouseEvents.push(new MouseEventWrapper(eventType, event));
		}

	}

	public void addMouseWheelEvent(MouseWheelEvent e) {
		mouseWheelEvents.push(e);
	}

	public void addKeyEvent(int eventType, KeyEvent e) {

		// keep track of the current pressed keys
		if (eventType == KeyEventHandler.KEY_PRESSED) {
			keyboard.addKeyPressed(e);
		} else {
			if (eventType == KeyEventHandler.KEY_RELEASED) {
				keyboard.removeKeyPressed(e);
			}
		}
		keyEvents.push(new KeyEventWrapper(eventType, e));
	}

	public ProducerConsumerQueue getKeyEvents() {
		return keyEvents;
	}

	public ProducerConsumerQueue getMouseEvents() {
		return mouseEvents;
	}

	public ProducerConsumerQueue getMouseWheelEvents() {
		return mouseWheelEvents;
	}

	public MouseDevice getMouse() {
		return mouse;
	}

}
