package com.athanazio.jaga.desktop;


public abstract class Action {

	protected DesktopMainLoop game;
	
	public Action(DesktopMainLoop game) {
		this.game = game;
	}

	public abstract void doIt();
}
