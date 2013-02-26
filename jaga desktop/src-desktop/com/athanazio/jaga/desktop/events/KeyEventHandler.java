package com.athanazio.jaga.desktop.events;


public interface KeyEventHandler {
	
	int KEY_PRESSED = 0;
	int KEY_RELEASED = 1;
	int KEY_TYPED = 2;
	
	public void onKeyEvent(KeyEventWrapper e);

}
