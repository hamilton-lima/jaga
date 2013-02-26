package com.athanazio.jaga.core;

public class Audio extends GameFeature {
	
	public void play(){
		GameObject.provider.play(id);
	}

	public void loop(){
		GameObject.provider.loop(id);
	}
}
