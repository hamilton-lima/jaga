package com.athanazio.jaga.desktop;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * 
 * FastCanvas
 * 
 * uses a buffedImage to draw the visual content of the component and then draws
 * the hole image on the screen, just rewrite the paint method
 * <p>
 * TODO create methods to draw using ImageConsumer/ImageProducer - setPixel para
 * desenhas figuras geometricas baseado em operacoes com inteiros e desenhar
 * imagens com copias de array de pixels
 * 
 * 
 * @author Hamilton Lima
 * @version 28/06/2003
 * @version Oct-12-2003 optional background clear
 * 
 */
public class FastCanvas extends Canvas {

	private static final long serialVersionUID = 1L;
	
	private Graphics2D bufferGraphics = null;
	private BufferedImage buffer = null;
	private boolean clear = false;
	
	public void init() {

		if (getBounds() != null) {

			if (getBounds().width > 0 && getBounds().height > 0) {
				buffer = new BufferedImage(getBounds().width, getBounds().height,
						BufferedImage.TYPE_INT_ARGB);
				buffer.setAccelerationPriority(1);

				bufferGraphics = buffer.createGraphics();
				bufferGraphics.setRenderingHint(
						RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

				if (clear) {
					clearBackground();
				}
			}

		}

	}

	protected void clearBackground() {
		clearBackground(Color.BLACK);
	}

	protected void clearBackground(Color color) {
		if (bufferGraphics != null) {
			bufferGraphics.setBackground(color);
			bufferGraphics.setColor(color);
			bufferGraphics
					.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
		}
	}

	public final void paint(Graphics g) {

		if (buffer != null) {
			if (clear) {
				clearBackground();
			}
			g.drawImage(buffer, 0, 0, null);
		} else {
			init();
		}

	}

	public void update(Graphics g) {
		paint(g);
	}


	public Graphics2D getBufferGraphics() {
		if (bufferGraphics == null) {
			init();
		}
		return bufferGraphics;
	}

	public Image getBufferImage() {
		return buffer;
	}

}
