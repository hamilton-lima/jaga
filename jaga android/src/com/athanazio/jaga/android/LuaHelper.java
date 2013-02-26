package com.athanazio.jaga.android;

import java.io.IOException;
import java.io.InputStream;

import org.luaj.compiler.LuaC;
import org.luaj.platform.J2sePlatform;
import org.luaj.vm.LClosure;
import org.luaj.vm.LPrototype;
import org.luaj.vm.LValue;
import org.luaj.vm.LoadState;
import org.luaj.vm.LuaState;
import org.luaj.vm.Platform;

import android.content.res.Resources;
import android.util.Log;

public class LuaHelper {
	private static final String TAG = "JAGA LUA HELPER";

	private LuaState vm;
	private Resources resources;
	private String packageName;

	public LuaHelper(Resources resources, String packageName) {
		Platform.setInstance(new J2sePlatform());
		vm = Platform.newLuaState();
		LuaC.install();
		this.packageName = packageName;
		this.resources = resources;
	}

	/**
	 * execute a call to a lua function
	 * as this call can be used for more than one lua function,
	 * and the LuaState is shared for all the function calls,
	 * we are forcing the synchronization of the call to avoid
	 * the corruption of the data in the LuaState stacks from one 
	 * call to other.
	 *  
	 * @param functionName
	 * @author hlima
	 */
	public synchronized void call(String functionName) {
		try {
			vm.getglobal(functionName);
			vm.call(0, 0);
		} catch (Exception e) {
			Log.e(TAG, "error calling function : " + functionName, e);
		}
	}

	public void load(String name) {
		try {
			int id = resources.getIdentifier(name, "raw", packageName);
			Log.i(TAG, "name : "+ name + " id : " + id);
			InputStream input = resources.openRawResource(id);

			LPrototype p = LoadState.undump(vm, input, name);
			LClosure c = p.newClosure(vm._G);
			vm.doCall(c, new LValue[0]);
			input.close();
		} catch (IOException e) {
			Log.e(TAG, "error loading the lua file : " + name, e);
		}
	}

}
