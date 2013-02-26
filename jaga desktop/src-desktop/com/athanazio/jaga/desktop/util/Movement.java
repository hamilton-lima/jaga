/*
 * Created on 19/02/2004
 */
package com.athanazio.jaga.desktop.util;

/**
 * represents a Movement
 * 
 * @author Hamilton Lima
 * @version 19/02/2004 10:38:59
 */
public class Movement {
	public int x;

	public int y;

	public int angle;

	public Movement(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "x=" + x + ",y=" + y + ",angle=" + angle;
	}

	/**
	 * inform if can move to the point
	 * 
	 * @param point
	 * @return
	 */
	public static boolean canMoveTo(int x, int y, int distance, int xLimit,
			int yLimit) {

		if (x < 0 || y < 0 || (x + distance) > xLimit
				|| (y + distance) > yLimit) {
			return false;
		} else {
			return true;
		}

	}
}
