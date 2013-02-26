/**
 * 
 */
package com.athanazio.jaga.desktop;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;


public class GameVisualContainer extends JDesktopPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FastCanvas drawCanvas;

	GameVisualContainer(FastCanvas drawCanvas) {
		this.drawCanvas = drawCanvas;
		setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		setIgnoreRepaint(true);
	}

	private static Graphics g;

	public void update() {
		if (g != null) {
			// double buffer the children components
			paintChildren(drawCanvas.getBufferGraphics());
			g.drawImage(drawCanvas.getBufferImage(), 0, 0, null);

			Toolkit.getDefaultToolkit().sync();
		} else {
			g = getGraphics();
		}
	}

}