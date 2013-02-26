package com.athanazio.jaga.desktop.events;


public interface MouseEventHandler {

	int MOUSE_CLICKED = 0;
	int MOUSE_ENTERED = 1;
	int MOUSE_EXITED = 2;
	int MOUSE_PRESSED = 3;
	int MOUSE_RELEASED = 4;
	int MOUSE_DRAGGED = 5;
	int MOUSE_MOVED = 6;
	
	public void onMouseEvent(MouseEventWrapper event);
	
}
