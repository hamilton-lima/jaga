package com.athanazio.jaga.desktop;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.luaj.compiler.LuaC;
import org.luaj.platform.J2sePlatform;
import org.luaj.vm.LClosure;
import org.luaj.vm.LPrototype;
import org.luaj.vm.LValue;
import org.luaj.vm.LoadState;
import org.luaj.vm.LuaState;
import org.luaj.vm.Platform;

import com.athanazio.jaga.core.EnvironmentProvider;

public class LuaHelper {
	private LuaState vm;
	private EnvironmentProvider provider;

	public LuaHelper(EnvironmentProvider provider) {
		Platform.setInstance(new J2sePlatform());
		vm = Platform.newLuaState();
		LuaC.install();
		this.provider = provider;
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
			provider.error("error calling function : " + functionName, e);
		}
	}

	public void load(String name) {
		try {
			provider.debug("loading lua :" + name);
			URL url = getClass().getResource("/scripts/" + name + ".lua");
			InputStream input = url.openStream();

			LPrototype p = LoadState.undump(vm, input, name);
			LClosure c = p.newClosure(vm._G);
			vm.doCall(c, new LValue[0]);
			input.close();
		} catch (IOException e) {
			provider.error("error loading the lua file : " + name, e);
		}
	}

}
