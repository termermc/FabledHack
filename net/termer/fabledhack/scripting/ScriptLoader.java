package net.termer.fabledhack.scripting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.termer.fabledhack.FabledHack;
import net.termer.utils.Utils;

public class ScriptLoader {
	private File sf = null;
	private ArrayList<Script> scripts = new ArrayList<Script>();
	
	public ScriptLoader(File scriptFolder) {
		sf = scriptFolder;
		for(int i = 0; i < sf.listFiles().length; i++) {
			if(sf.listFiles()[i].getName().endsWith(".fhscript")) {
				try {
					scripts.add(new Script(Utils.getFile(sf.listFiles()[i])));
				} catch (IOException e) {
					new FabledHack().log("Unexpected error while loading script: "+sf.listFiles()[i].getName());
					e.printStackTrace();
				}
			}
		}
	}
	
	public File getScriptFolder() {
		return sf;
	}
	public Script[] getScripts() {
		return scripts.toArray(new Script[0]);
	}
}
