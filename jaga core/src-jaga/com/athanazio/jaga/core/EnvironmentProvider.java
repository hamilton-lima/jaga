package com.athanazio.jaga.core;

/**
 * Define what each environment should provide as implementation to the core game abstraction
 * 
 * @author hlima
 *
 */
public interface EnvironmentProvider {

	public void draw(String id, float drawX, float drawY);

	public float getWidth(String id);

	public float getHeight(String id);

	public void debug(String string);
	
	public boolean isKeyPressed(String key);

	public void play(String id);

	public void loop(String id);

	public void drawString(String id, float x, float y, String text);

	public void require(String luaFileName);

	public void error(String string, Exception e);
}
