package net.termer.fabledhack.scripting;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import net.termer.fabledhack.FabledHack;
import net.termer.fabledhack.api.ButtonHandler;
import net.termer.fabledhack.scripting.exception.InvalidSyntaxException;
import net.termer.fabledhack.scripting.exception.NullParamException;
import net.termer.fabledhack.scripting.exception.UnknownCommandException;
import net.termer.fabledhack.Runnable;

public class Script {
	private String[] lns;
	private String name = "Unknown";
	private ArrayList<MetaTag> meta = new ArrayList<MetaTag>();
	
	public static String EXTRA = null;
	
	public Script(String[] code) {
		lns = code;
		for(int i = 0; i < lns.length; i++) {
			if(lns[i].startsWith("@")) {
				String ln = lns[i].substring(1);
					if(ln.split(" ")[0] == "name") {
						meta.add(new MetaTag(ln.split(" ")[0], ln.substring(ln.split(" ")[0].length()+1)));
					} else {
						name = ln.substring(ln.split(" ")[0].length()+1);
					}
			}
		}
		new FabledHack().log("Successfully loaded script: "+name);
	}
	
	public String getLine(int line) {
		return lns[line];
	}
	public String[] getLines() {
		return lns;
	}
	
	public void run() throws InvalidSyntaxException, NullParamException, UnknownCommandException {
		for(int i = 0; i < lns.length; i++) {
			String ln = lns[i];
			if(!ln.isEmpty() || !ln.startsWith("//") || !ln.startsWith("@")) {
				parse(ln, i);
			}
		}
	}
	
	public Object parse(String line, int num) throws InvalidSyntaxException, NullParamException, UnknownCommandException {
		Object result = null;
		if(!line.contains(":")) {
			throw new InvalidSyntaxException("Syntax error in script: "+name+", line: "+num);
		} else {
			if(line.split(":")[0] != null && line.split(":")[1] != null) {
				try {
					String cmd = line.split(":")[0].split(" ")[0].trim();
					String param = line.split(":")[0].substring(cmd.length()+1);
					String extra = line.substring(line.split(":")[0].length()+1);
					
					if(cmd.equalsIgnoreCase("addBtn")) {
						EXTRA = extra;
						ButtonHandler.addButton(param, new Runnable() {
							public void run() {
								String[] cmds = EXTRA.split("|");
								EXTRA = null;
								try {
									for(int i = 0; i < cmds.length; i++) {
										new Script(new String[] {"@name ButtonScript"}).parse(cmds[i], i);
									}
								} catch(Exception e) {}
							}
						});
					} else if(cmd.equalsIgnoreCase("dialog")) {
						JOptionPane.showMessageDialog(null, param);
					} else if(cmd.equalsIgnoreCase("log")) {
						System.out.println("["+param+"] "+extra);
					} else {
						throw new UnknownCommandException("Unknown command \""+cmd+"\" called in script: "+name+", line: "+num);
					}
				} catch(NullPointerException e) {
					throw new NullParamException("Param or command null in script: "+name+", line: "+num);
				}
			} else {
				throw new NullParamException("Param or command null in script: "+name+", line: "+num);
			}
		}
		return result;
	}
	
}
