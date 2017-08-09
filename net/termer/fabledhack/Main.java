package net.termer.fabledhack;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import flands.FLApp;

public class Main {
	public static FabledHack fh = new FabledHack();
	
	public static void main(String[] args) {
		fh.log("FabledHack starting up...");
		try {
			fh.log("Loading Look and Feels...");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			fh.log("Starting game...");
			FLApp.main(new String[] {});
			fh.log("Loading mods...");
			fh.init();
			FLApp.unblockOutput();
			fh.log("Done");
			FLApp.blockOutput();
			fh.getGame().setTitle("FabledHack v"+Double.toString(fh.getVersion()));
		} catch(Exception e) {
			fh.log("FabledHack has detected a fatal error:");
			e.printStackTrace();
			if(JOptionPane.showConfirmDialog(null, "A fatal error was detected.\nDo you wish to exit?") == 0) {
				System.exit(1);
			}
		}
	}
}
