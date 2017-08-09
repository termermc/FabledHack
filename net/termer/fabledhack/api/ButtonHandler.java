package net.termer.fabledhack.api;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import net.termer.fabledhack.Main;
import net.termer.fabledhack.Runnable;

public class ButtonHandler {
	private static ArrayList<JButton> btns = new ArrayList<JButton>();
	
	public static void addButton(String name, final Runnable action) {
		JButton btn = new JButton(name);
		btns.add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action.run();
			}
		});
		Main.fh.getModsWindow().getContentPane().add(btn);
		Main.fh.getModsWindow().setSize(700, 75);
	}
	public static void addButton(String name, final Runnable action, JMenu menu) {
		JButton btn = new JButton(name);
		btns.add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action.run();
			}
		});
		menu.add(btn);
	}
	public static void addButton(String name, final Runnable action, JMenuBar menu) {
		JButton btn = new JButton(name);
		btns.add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action.run();
			}
		});
		menu.add(btn);
	}
	public static JButton[] getButtons() {
		return btns.toArray(new JButton[0]);
	}
	public static JButton getButton(String name) {
		JButton tmp = null;
		for(int i = 0; i < btns.size(); i++) {
			if(btns.get(i).getText().equalsIgnoreCase(name)) {
				tmp = btns.get(i);
			}
		}
		return tmp;
	}
}
