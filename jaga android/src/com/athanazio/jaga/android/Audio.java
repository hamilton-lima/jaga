package com.athanazio.jaga.android;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Audio resources abstraction
 * 
 * @author athanazio
 * @see "http://developer.android.com/intl/fr/guide/topics/media/index.html"
 * @see "http://developer.android.com/intl/fr/reference/android/media/MediaPlayer.html#Valid_and_Invalid_States"
 */
public class Audio {

	private static final String TAG = "GAME AUDIO";
	private MediaPlayer player;
	private boolean ready;
	private int id;
	private AssetFileDescriptor afd;

	/**
	 * 
	 * @param resources
	 * @param context
	 * @param packageName
	 * @param name
	 * 
	 */
	public Audio(Resources resources, String packageName, String name) {
		String logFileName = packageName + " " + name;
		Log.i(TAG, "loading audio : " + logFileName);

		ready = false;
		player = new MediaPlayer();

		try {
			this.id = resources.getIdentifier(name, "raw", packageName);
			this.afd = resources.openRawResourceFd(id);

			Log.i(TAG, "loading audio : " + logFileName + " id:" + id);

			player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			player.setLooping(false);
			player.prepare();

			// reset the player after completion
			player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					try {
						player.reset();
						player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
						player.prepare();
					} catch (Exception e) {
						Log.e(TAG, "error resetting the media player");
						e.printStackTrace();
					}
				}
			});

			ready = true;

		} catch (Exception e) {
			Log.e(TAG, "ERROR loading audio : " + logFileName);
			e.printStackTrace();
		}
	}

	public void play() {
		if (ready) {
			player.start();
		} else {
			Log.i(TAG, "the player is not ready.");
		}
	}

	public void setLooping(boolean b) {
		player.setLooping(b);
	}

	public void release() {
		player.stop();
		player.release();
	}

}
