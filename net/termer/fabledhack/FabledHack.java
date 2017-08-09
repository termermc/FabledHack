package net.termer.fabledhack;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import flands.Blessing;
import flands.FLApp;
import net.termer.fabledhack.api.ButtonHandler;
import net.termer.fabledhack.scripting.ScriptLoader;
import net.termer.fabledhack.scripting.exception.NullParamException;
import net.termer.fabledhack.scripting.exception.UnknownCommandException;
import net.termer.tconfig.TConfig;
import net.termer.tconfig.TConfigReader;
import net.termer.tconfig.TConfigWriter;
import net.termer.tconfig.exception.InvalidSyntaxException;
import net.termer.utils.Utils;

public class FabledHack {
	private static BookEditor bookEditor = new BookEditor();
	private static double version = 1.0;
	private static File settingsFile = new File("settings.fh");
	private static TConfigReader cr = null;
	private static TConfigWriter cw = null;
	private static FLApp game = FLApp.getSingle();
	private static File scriptsFolder = new File("scripts");
	private static ScriptLoader scriptLoader = null;
	private static StatWindow statWindow = null;
	private static ModsWindow modsWindow = null;
	
	public BookEditor getBookEditor() {
		return bookEditor;
	}
	public double getVersion() {
		return version;
	}
	public File getSettingsFile() {
		return settingsFile;
	}
	public TConfigReader getConfigReader() {
		return cr;
	}
	public TConfigWriter getConfigWriter() {
		return cw;
	}
	public FLApp getGame() {
		return game;
	}
	public File getScriptsFolder() {
		return scriptsFolder;
	}
	public ScriptLoader getScriptLoader() {
		return scriptLoader;
	}
	public StatWindow getStatWindow() {
		return statWindow;
	}
	public ModsWindow getModsWindow() {
		return modsWindow;
	}
	
	public void log(String msg) {
		System.out.println("[FH] "+msg);
	}
	public void init() throws IOException {
		boolean doesExist = true;
		if(!settingsFile.exists()) {
			doesExist = false;
			TConfig.initialize(settingsFile);
		}
		if(!scriptsFolder.exists()) {
			scriptsFolder.mkdir();
		}
		scriptLoader = new ScriptLoader(scriptsFolder);
		for(int i = 0; i < scriptLoader.getScripts().length; i++) {
			try {
				scriptLoader.getScripts()[i].run();
			} catch (net.termer.fabledhack.scripting.exception.InvalidSyntaxException | NullParamException | UnknownCommandException e) {
				log(e.getClass().getName()+": "+e.getMessage());
			}
		}
		cr = new TConfigReader(settingsFile, false);
		cw = new TConfigWriter(settingsFile, false);
		try {
			if(!doesExist) {
				cw.addField("enableBookEditor", "true");
				cw.addField("useSysLF", "?");
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
		statWindow = new StatWindow();
		modsWindow = new ModsWindow();
		Settings.enableBookEditor = Boolean.parseBoolean(cr.getField("enableBookEditor"));
		if(Settings.enableBookEditor) {
			bookEditor.open();
		}
		
		ButtonHandler.addButton("Add Title", new Runnable() {
			public void run() {
				try {
					game.getAdventurer().addTitle(JOptionPane.showInputDialog("Please enter a title to add to your adventurer:"));
					JOptionPane.showMessageDialog(game,"Title added!");
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(game, "Invalid title!");
				}
			}
		});
		ButtonHandler.addButton("Set God Name", new Runnable() {
			public void run() {
				try {
					game.getAdventurer().setGod(JOptionPane.showInputDialog("Please enter the name of your new god:"));
					JOptionPane.showMessageDialog(game,"God name set!");
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(game, "Invalid name!");
				}
			}
		});
		ButtonHandler.addButton("Set Money", new Runnable() {
			public void run() {
				try {
					game.getAdventurer().setMoney(Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of money you want:")));
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Must be a number!");
				}
			}
		});
		ButtonHandler.addButton("Stat Editor", new Runnable() {
			public void run() {
				statWindow.open();
			}
		});
		ButtonHandler.addButton("UltraBlessing", new Runnable() {
			public void run() {
				game.getAdventurer().getBlessings().addBlessing(new Blessing(7));
				game.getAdventurer().getBlessings().addBlessing(new Blessing(6));
				game.getAdventurer().getBlessings().addBlessing(new Blessing(5));
				game.getAdventurer().getBlessings().addBlessing(new Blessing(4));
				game.getAdventurer().getBlessings().addBlessing(new Blessing(3));
				game.getAdventurer().getBlessings().addBlessing(new Blessing(2));
				game.getAdventurer().getBlessings().addBlessing(new Blessing(1));
			}
		});
		ButtonHandler.addButton("Goto Section", new Runnable() {
			public void run() {
				try {
					game.gotoSection(JOptionPane.showInputDialog("Enter section name/page:"));
				} catch(Exception e) {
					JOptionPane.showMessageDialog(game, "Invalid section");
				}
			}
		});
	}
}
