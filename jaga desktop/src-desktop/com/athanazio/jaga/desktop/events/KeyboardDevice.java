package com.athanazio.jaga.desktop.events;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;

public class KeyboardDevice {

	private HashMap keys;
	
	private final int ADD = 1;
	private final int READ = 0;
	private final int REMOVE = -1;
	
	public KeyboardDevice(){
		keys = new HashMap();
	}
	
	public void addKeyPressed(KeyEvent event){
		managePressed(event,0,ADD);
	}

	public void removeKeyPressed(KeyEvent event){
		managePressed(event,0,REMOVE);
	}

	public boolean isKeyPressed(int key){
		return managePressed(null,key,READ) != null;
	}

	public int getModifiersEx(int key){
		KeyEvent event = managePressed(null,key,READ);
		if( event != null ){
			return event.getModifiersEx();
		} 
		return 0;
	}
	
	/**
	 * this is the single point of entry for the keys hash
	 * 
	 * @param event
	 * @param key
	 * @param operation
	 * @return
	 */
	private synchronized KeyEvent managePressed(KeyEvent event, int key, int operation) {
		if( operation == ADD ){
			keys.put(Integer.toString(event.getKeyCode()), event);
			return event;
		} else {
			if( operation == REMOVE ){
				keys.remove(Integer.toString(event.getKeyCode()));
				return event;
			} else {
				if( operation == READ){
					return (KeyEvent) keys.get(Integer.toString(key));
				} 
			}
		}
		return null;
	}
	
}
