package com.athanazio.jaga.core;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class GameObject {

	protected static EnvironmentProvider provider;

	public static void setEnvironmentProvider(EnvironmentProvider provider) {
		GameObject.provider = provider;
	}

	protected String id;
	private ArrayList objects = new ArrayList();

	public void load() {
		Iterator iterator = objects.iterator();
		while (iterator.hasNext()) {
			GameObject one = (GameObject) iterator.next();
			one.load();
		}
	}

	public void update() {
		//GameObject.provider.debug("GameObject update() id: " + id);

		Iterator iterator = objects.iterator();
		while (iterator.hasNext()) {
			GameObject one = (GameObject) iterator.next();
			one.update();
		}
	}

	public void draw() {
		//GameObject.provider.debug("GameObject draw() id: " + id);

		Iterator iterator = objects.iterator();
		while (iterator.hasNext()) {
			GameObject one = (GameObject) iterator.next();
			one.draw();
		}
	}

	public void add(GameObject object) {
		objects.add(object);
	}

	public void removeAll() {
		objects.clear();
	}

	public void remove(String id) {

		Iterator iterator = objects.iterator();
		while (iterator.hasNext()) {
			GameObject one = (GameObject) iterator.next();
			if (one.getId().equals(id)) {
				iterator.remove();
				break;
			}

		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
