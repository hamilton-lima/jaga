package com.athanazio.jaga.android;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Window;

public class AndroidLoader extends Activity {
	private GameLoop gameLoop;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		// the package name will be used to load the images and audio
		String packageName = getClass().getPackage().getName();
		
		// delegate the control to the game loop
		gameLoop = new GameLoop(this, getResources(), packageName);
		setContentView(gameLoop);
	}

	protected void onDestroy() {
		super.onDestroy();
		gameLoop.release();
	}

}