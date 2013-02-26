package com.athanazio.jaga.desktop.events;

import java.awt.Point;

/**
 * calculated relative to the position 
 * where the image of the game is draw, 
 * so even if we resize the game image the mouse 
 * will still reproduce the correct place
 *  
 * @author athanazio
 *
 */
public class MouseDevice {
	private Point point;
	
	public MouseDevice() {
		point = new Point(0,0);
	}

	public int getX() {
		return point.x;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getY() {
		return point.y;
	}

}
