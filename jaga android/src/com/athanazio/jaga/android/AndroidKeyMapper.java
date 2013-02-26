package com.athanazio.jaga.android;

import java.util.HashMap;

import android.view.KeyEvent;

import com.athanazio.jaga.core.Game;

public class AndroidKeyMapper {

	public static HashMap mapper;
	public static AndroidKeyMapper instance;

	public static AndroidKeyMapper getInstance() {
		if (instance == null) {
			instance = new AndroidKeyMapper();
		}
		return instance;
	}

	private AndroidKeyMapper() {
		mapper = new HashMap();
		mapper.put(KeyEvent.KEYCODE_DPAD_UP, Game.UP);
		mapper.put(KeyEvent.KEYCODE_DPAD_DOWN, Game.DOWN);
		mapper.put(KeyEvent.KEYCODE_DPAD_LEFT, Game.LEFT);
		mapper.put(KeyEvent.KEYCODE_DPAD_RIGHT, Game.RIGHT);
		mapper.put(KeyEvent.KEYCODE_DPAD_CENTER, Game.FIRE);
	}

	public static String translate(int androidKey) {
		return (String) mapper.get(androidKey);
	}

}
