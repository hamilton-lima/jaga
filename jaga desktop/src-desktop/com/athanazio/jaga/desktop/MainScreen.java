package com.athanazio.jaga.desktop;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.text.JTextComponent;

import com.athanazio.jaga.desktop.events.KeyEventHandler;
import com.athanazio.jaga.desktop.events.MouseEventHandler;

public class MainScreen extends JFrame implements KeyListener, MouseListener,
		MouseMotionListener, MouseWheelListener, ActionListener,
		WindowStateListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Container container;

	private DesktopMainLoop mainLoop;

	private GameVisualContainer desktop;

	public MainScreen(int width, int height) {

		mainLoop = new DesktopMainLoop(0, 0, width , height);

		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		container = getContentPane();

		// reduce the width and height by the border
		// to fit the image of the game in the screen
		Rectangle bounds = getBounds();
		bounds.height = bounds.height - 28;  
		bounds.width = bounds.width - 10;  
		mainLoop.getDrawCanvas().setBounds(bounds);
		mainLoop.getDrawCanvas().init();

		// canvas to draw the game
		desktop = new GameVisualContainer(mainLoop.getDrawCanvas());
		mainLoop.setContainer(desktop);
		container.add(desktop, BorderLayout.CENTER);

		// mouse events handler only for the desktop
		setComponentMouseEventHandlers(this);
		setEventHandlers(this);
		requestFocus();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				endGame();
			}
		});

		// start the game
		Thread thread = new Thread(mainLoop);
		thread.start();
	}

	/**
	 * this make the component to delegate the events to the game event manager
	 * so the events can be consumed by the UI and by the game at the same time
	 * 
	 * @param component
	 */
	public void setEventHandlers(Container container) {

		if (container instanceof JTextComponent) {
			setTextKeyEventHandlers(container);
		} else {
			setComponentKeyEventHandlers(container);
			container.setFocusable(true);
		}

		Component[] components = container.getComponents();
		for (int i = 0; i < components.length; i++) {

			if (components[i] instanceof Container) {
				setEventHandlers((Container) components[i]);
			} else {
				components[i].setFocusable(true);

				// text components dont propagate key events
				if (components[i] instanceof JTextComponent) {
					setTextKeyEventHandlers(components[i]);
				} else {
					setComponentKeyEventHandlers(components[i]);
				}
			}
		}
	}

	/**
	 * change the focus from the text editor to the desktop after pressing and
	 * enter
	 * 
	 * @param component
	 */
	private void setTextKeyEventHandlers(Component component) {
		component.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					desktop.requestFocus();
				}
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
			}

		});

	}

	private void setComponentKeyEventHandlers(Component component) {
		component.addKeyListener(this);
		component.setFocusTraversalKeysEnabled(false);
	}

	private void setComponentMouseEventHandlers(Component component) {
		component.addMouseListener(this);
		component.addMouseMotionListener(this);
		component.addMouseWheelListener(this);
	}

	public void endGame() {
		mainLoop.stop();
		System.exit(0);
	}

	public void keyPressed(KeyEvent e) {
		mainLoop.getEventManager().addKeyEvent(KeyEventHandler.KEY_PRESSED, e);
	}

	public void keyReleased(KeyEvent e) {
		mainLoop.getEventManager().addKeyEvent(KeyEventHandler.KEY_RELEASED, e);
	}

	public void keyTyped(KeyEvent e) {
		mainLoop.getEventManager().addKeyEvent(KeyEventHandler.KEY_TYPED, e);
	}

	public void mouseClicked(MouseEvent e) {
		mainLoop.getEventManager().addMouseEvent(MouseEventHandler.MOUSE_CLICKED,
				e, getLocationOnScreen());
	}

	public void mouseEntered(MouseEvent e) {
		mainLoop.getEventManager().addMouseEvent(MouseEventHandler.MOUSE_ENTERED,
				e, getLocationOnScreen());
	}

	public void mouseExited(MouseEvent e) {
		mainLoop.getEventManager().addMouseEvent(MouseEventHandler.MOUSE_EXITED, e,
				getLocationOnScreen());
	}

	public void mousePressed(MouseEvent e) {
		mainLoop.getEventManager().addMouseEvent(MouseEventHandler.MOUSE_PRESSED,
				e, getLocationOnScreen());
	}

	public void mouseReleased(MouseEvent e) {
		mainLoop.getEventManager().addMouseEvent(MouseEventHandler.MOUSE_RELEASED,
				e, getLocationOnScreen());
	}

	public void mouseDragged(MouseEvent e) {
		mainLoop.getEventManager().addMouseEvent(MouseEventHandler.MOUSE_DRAGGED,
				e, getLocationOnScreen());
	}

	public void mouseMoved(MouseEvent e) {
		mainLoop.getEventManager().addMouseEvent(MouseEventHandler.MOUSE_MOVED, e,
				getLocationOnScreen());
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		mainLoop.getEventManager().addMouseWheelEvent(e);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("book")) {
			// do something
		}
	}

	/**
	 * if something changes at the window reset the game draw area
	 */
	public void windowStateChanged(WindowEvent e) {
		mainLoop.getDrawCanvas().setBounds(getBounds());
	}
}
