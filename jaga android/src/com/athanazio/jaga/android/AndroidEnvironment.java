package com.athanazio.jaga.android;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;

import com.athanazio.jaga.core.EnvironmentProvider;

public class AndroidEnvironment implements EnvironmentProvider {

	private static final String TAG = "JAGA ENVIRONMENT PROVIDER";

	private static final long THRESHOLD_CLEAR_KEYEVENT = 300;

	private Paint cPaint;

	private Canvas canvas;

	private String packageName;

	private Resources resources;

	private HashMap sprites;

	private HashSet keyPressed;

	private long lastKeyEvent;

	private HashMap audioList;

	private LuaHelper lua;

	public AndroidEnvironment(Resources resources, String packageName) {
		this.resources = resources;
		this.packageName = packageName;

		sprites = new HashMap();
		audioList = new HashMap();
		keyPressed = new HashSet();

		cPaint = new Paint();
		cPaint.setColor(Color.WHITE);
	}

	public void draw(String id, float drawX, float drawY) {
		AndroidSprite sprite = getSprite(id);
		sprite.draw(canvas, drawX, drawY);
	}

	public float getHeight(String id) {
		AndroidSprite sprite = getSprite(id);
		return sprite.getBitmap().getHeight();
	}

	public float getWidth(String id) {
		AndroidSprite sprite = getSprite(id);
		return sprite.getBitmap().getWidth();
	}

	public AndroidSprite getSprite(String id) {
		AndroidSprite sprite = (AndroidSprite) sprites.get(id);
		if (sprite == null) {
			sprite = new AndroidSprite(resources, packageName, id);
			sprites.put(id, sprite);
		}
		return sprite;
	}

	public AndroidAudio getAudio(String id) {
		AndroidAudio audio = (AndroidAudio) audioList.get(id);
		if (audio == null) {
			audio = new AndroidAudio(resources, packageName, id);
			audioList.put(id, audio);
		}
		return audio;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public void release() {
		Iterator iterator = sprites.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			AndroidSprite sprite = (AndroidSprite) sprites.get(key);
			sprite.release();
		}

		iterator = audioList.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			AndroidAudio audio = (AndroidAudio) audioList.get(key);
			audio.release();
		}

	}

	public void debug(String msg) {
		Log.d(TAG, msg);
	}

	public void error(String msg, Exception e) {
		Log.e(TAG, msg, e);
	}

	public boolean isKeyPressed(String key) {
		if (keyPressed.contains(key)) {
			return true;
		}
		return false;
	}

	public void onKeyDown(String key) {
		lastKeyEvent = SystemClock.uptimeMillis();
		Log.i(TAG, "key down : " + key);
		keyPressed.add(key);
	}

	public boolean onKeyDown(int keyCode) {
		Log.i(TAG, "MAP key down : " + keyCode);
		String key = AndroidKeyMapper.getInstance().translate(keyCode);
		onKeyDown(key);
		return key != null;
	}

	public void onKeyUp(String key) {
		keyPressed.remove(key);
		Log.i(TAG, "key up : " + key);
	}

	public boolean onKeyUp(int keyCode) {
		Log.i(TAG, "MAP key up : " + keyCode);
		String key = AndroidKeyMapper.getInstance().translate(keyCode);
		onKeyUp(key);
		return key != null;
	}

	// remove all the keys
	public void clearKeys() {
		keyPressed.clear();
	}

	/**
	 * clear the trackball keys as we dont have a event in the trackball to
	 * inform that the track ball was released, this is basic a timeout of the
	 * last keydown event.
	 */
	public void checkKeyEventsTimeOut() {
		if (keyPressed.size() > 0) {
			if (SystemClock.uptimeMillis() - lastKeyEvent > THRESHOLD_CLEAR_KEYEVENT) {
				clearKeys();
			}
		}
	}

	public void loop(String id) {
		AndroidAudio audio = getAudio(id);
		audio.setLooping(true);
		audio.play();
	}

	public void play(String id) {
		AndroidAudio audio = getAudio(id);
		audio.setLooping(false);
		audio.play();
	}

	public void drawString(String id, float x, float y, String text) {
		canvas.drawText(text, x, y, cPaint);
	}

	public void setLuaHelper(LuaHelper lua) {
		this.lua = lua;
	}

	public void require(String luaFileName) {
		lua.load(luaFileName);
	}

}
