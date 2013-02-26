/*
 * Created on 19/02/2004
 */
package com.athanazio.jaga.desktop.util;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.StringTokenizer;

/**
 * represents a Visual
 * 
 * @author Hamilton Lima
 * @version 19/02/2004 09:47:36
 */
public class Visual {

	/**
	 * based on the rectangle information, generate a random point inside of it
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static Point getRandomValidPoint(int x, int y, int width, int height) {
		return new Point(getPointFrom(x, width), getPointFrom(y, height));
	}

	/**
	 * given one value, return an value between this value and itself plus the
	 * distance
	 * 
	 * @param n
	 * @param distance
	 * @return
	 */
	private static int getPointFrom(int n, int distance) {
		int point = (int) (Math.random() * (distance - n)) + 1 + n;
		while (point < n || point > (n + distance)) {
			point = (int) (Math.random() * (distance - n)) + 1 + n;
		}
		return point;
	}

	/**
	 * based on limit area, returns the quadrant of the informed point
	 * 
	 * @param limitX
	 * @param limitY
	 * @param pointX
	 * @param pointY
	 * @return
	 */
	public static int getQuadrant(int limitX, int limitY, int pointX, int pointY) {

		int halfX = limitX / 2;
		int halfY = limitY / 2;

		if (pointX > halfX && pointY <= halfY) {
			return 1;
		}

		if (pointX > halfX && pointY > halfY) {
			return 2;
		}

		if (pointX <= halfX && pointY > halfY) {
			return 3;
		}

		if (pointX <= halfX && pointY <= halfY) {
			return 4;
		}

		return 0;
	}

	public static void markPoint(Graphics g, int x, int y) {
		g.drawLine(x - 5, y - 5, x + 5, y + 5);
		g.drawLine(x + 5, y - 5, x - 5, y + 5);
	}

	public static void markSquare(Graphics g, int x, int y) {
		g.fillRect(x - 2, y - 2, 4, 4);
	}

	public static void drawStringInTheMiddle(Graphics2D g, String s, int x,
			int y, int w, int h) {
		FontMetrics metrics = g.getFontMetrics();
		Rectangle2D mBounds = metrics.getStringBounds("M", g);

		StringTokenizer tokenizer = new StringTokenizer(s, "\n");
		int height = (int) (tokenizer.countTokens() * mBounds.getHeight());
		int firstY = ((h - y) / 2) - height;
		int counter = 0;
		y = y + firstY;
		
		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken();
			Rectangle2D bounds = metrics.getStringBounds(line, g);
			int localX = x + (int) ((w - x) / 2 - (bounds.getWidth() / 2));
			int localY = (int) (y + (counter * bounds.getHeight()));
			drawStringFromTheTop(g, line, localX, localY);
			counter ++;
		}

	}

	public static void drawStringFromTheTop(Graphics2D g, String s, int x, int y) {
		FontMetrics metrics = g.getFontMetrics();
		Rectangle2D bounds = metrics.getStringBounds(s, g);
		int localX = x;
		int localY = (int) (y + bounds.getHeight());
		g.drawString(s, localX, localY);

	}

}
