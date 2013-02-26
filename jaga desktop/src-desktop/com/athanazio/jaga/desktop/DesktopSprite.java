package com.athanazio.jaga.desktop;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class DesktopSprite {

	private ImageIcon i;

	public DesktopSprite(String id) {
		i = new ImageIcon(getClass().getResource("/images/" + id + ".png"));
	}

	public void draw(Graphics2D g, int x, int y) {
		g.drawImage(i.getImage(), x, y, null);
	}

	public ImageIcon getImage() {
		return i;
	}

}
