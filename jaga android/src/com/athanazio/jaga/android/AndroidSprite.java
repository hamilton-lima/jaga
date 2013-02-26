package com.athanazio.jaga.android;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class AndroidSprite {

	private static final String TAG = "JAGA SPRITE";
	private Bitmap bitmap;
	private Paint paint;

	public AndroidSprite(Resources resources, String packageName, String name) {
		String logFileName = packageName + " " + name;
		Log.i(TAG, "loading image : " + logFileName);

		int id = resources.getIdentifier(name, "drawable", packageName);
		bitmap = BitmapFactory.decodeResource(resources, id);
		this.paint = new Paint();
	}

	public void draw(Canvas canvas, float drawX, float drawY) {
		canvas.drawBitmap(bitmap, drawX, drawY, paint);
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void release() {
		bitmap.recycle();
	}
	
}
