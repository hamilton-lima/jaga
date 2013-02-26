package com.athanazio.jaga.desktop;

import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;

import com.athanazio.jaga.core.EnvironmentProvider;
import com.athanazio.jaga.desktop.events.KeyEventHandler;
import com.athanazio.jaga.desktop.events.KeyEventWrapper;
import com.athanazio.jaga.desktop.events.MouseEventHandler;
import com.athanazio.jaga.desktop.events.MouseEventWrapper;
import com.athanazio.jaga.desktop.events.MouseWheelEventHandler;
import com.athanazio.jaga.desktop.util.Log;

public class DesktopEnvironment implements EnvironmentProvider, KeyEventHandler, MouseEventHandler,
		MouseWheelEventHandler {

	private Graphics2D g;
	private HashMap sprites;
	private com.athanazio.jaga.desktop.LuaHelper lua;

	public DesktopEnvironment() {
		sprites = new HashMap();
	}

	public void setGraphics(Graphics2D g) {
		this.g = g;
	}

	public void debug(String string) {
		System.out.println(string);
		Log.debug(string);
	}

	public void error(String string, Exception e) {
		Log.error(e, string);
	}

	public DesktopSprite getSprite(String id) {
		DesktopSprite sprite = (DesktopSprite) sprites.get(id);
		if (sprite == null) {
			sprite = new DesktopSprite(id);
			sprites.put(id, sprite);
		}
		return sprite;
	}

	public void draw(String id, float drawX, float drawY) {
		DesktopSprite sprite = getSprite(id);
		sprite.draw(g, (int) drawX, (int) drawY);
	}

	public void drawString(String id, float x, float y, String text) {

	}

	public float getHeight(String id) {
		DesktopSprite sprite = getSprite(id);
		if (sprite != null) {
			return sprite.getImage().getIconHeight();
		}
		return 0;
	}

	public float getWidth(String id) {
		System.out.println("getWidth " + id);

		DesktopSprite sprite = getSprite(id);
		if (sprite != null) {
			System.out.println("not null sgetWidth " + id + "width " + sprite.getImage().getIconWidth());

			return sprite.getImage().getIconWidth();
		}
		return 0;
	}

	public void setLuaHelper(LuaHelper lua) {
		this.lua = lua;
	}

	public void require(String luaFileName) {
		System.out.println("require : " + luaFileName);
		lua.load(luaFileName);
	}

	public boolean isKeyPressed(String key) {
		return false;
	}

	public void loop(String id) {

	}

	public void play(String id) {

	}

	public void onKeyEvent(KeyEventWrapper e) {

	}

	public void onMouseEvent(MouseEventWrapper event) {

	}

	public void onMouseWheel(MouseWheelEvent event) {

	}

}
