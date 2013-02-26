package com.athanazio.jaga.desktop.sound;

public class Soundtrack extends Sound implements Runnable {

	public Soundtrack(String name) {
		super(name, true);
	}

	public void run() {
		super.play();
	}

	public void play() {
		(new Thread(this)).start();
	}

}
