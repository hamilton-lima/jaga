package com.athanazio.jaga.core;

public class Game extends GameObject {

	public static final String FIRE = "fire";
	public static final String RIGHT = "right";
	public static final String LEFT = "left";
	public static final String DOWN = "down";
	public static final String UP = "up";
	private static Game instance;

	public static Game getInstance(){
		if( instance == null ){
			instance = new Game();
		}
		return instance;
	}
	
	public String fps;
	

	private Pointer pointer;

	public Pointer getPointer() {
		return pointer;
	}

	public Game() {
		super();
		this.pointer = new Pointer();
		this.id = "game";
	}

	public void update() {
		super.update();
	}

	public void draw() {
		super.draw();
	}

	public boolean isKeyPressed(String key) {
		return GameObject.provider.isKeyPressed(key);
	}

	public void debug(String message) {
		GameObject.provider.debug(message);
	}

	/**
	 * Load an lua file from the current one
	 * 
	 * @param luaFileName
	 */
	public void require(String luaFileName){
		GameObject.provider.require(luaFileName);
	}
	
	public void updatePointer(float x, float y) {
		pointer.setX(x);
		pointer.setY(y);
	}
	
	public String getFps() {
		return fps;
	}

	public void setFps(String fps) {
		this.fps = fps;
	}
	
}
