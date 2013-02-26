package com.athanazio.jaga.desktop.util;

import java.awt.Font;
import java.util.Hashtable;

public class ResourceManager {

	public static final Hashtable resources = new Hashtable();
	private static Font ARIAL = new Font("Arial", Font.BOLD, 20);

	public static void add(String key, Object resource) {
		resources.put(key, resource);
	}

	public static Object get(String key,  Object defaultResource) {
		Object result = resources.get(key);
		if( result == null ){
			return defaultResource;
		} else {
			return result;
		}
	}

	public static Font getFont(String key) {
		return (Font) get(key, ARIAL);
	}
}
