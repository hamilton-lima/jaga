package com.athanazio.jaga.desktop.util;

import junit.framework.TestCase;

public class LogTest extends TestCase {

	public void testDebug() {
		Log.debug("bla");
		Log.DEBUG = false;
		Log.debug("no-bla");
		Log.DEBUG = true;
		Log.debug("yes-bla");
	}

	public void testWarning() {
		Log.warning("bla");
	}

	public void testError() {
		try {
			throw new Exception("foo");
		} catch (Exception e) {
			Log.error(e, "error in foo implementation");
		}
	}

}
