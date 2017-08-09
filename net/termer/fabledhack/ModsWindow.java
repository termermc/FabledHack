package net.termer.fabledhack;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import flands.FLApp;

public class ModsWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public ModsWindow() {
		super("Mods");
		addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
				
			}
			public void windowClosing(WindowEvent e) {
				FLApp.getSingle().requestFocus();
			}
			public void windowClosed(WindowEvent e) {
				
			}
			public void windowIconified(WindowEvent e) {
				
			}
			public void windowDeiconified(WindowEvent e) {
				
			}
			public void windowActivated(WindowEvent e) {
				
			}
			public void windowDeactivated(WindowEvent e) {
				
			}
			
		});
		setResizable(false);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		requestFocus();
		pack();
	}
}
