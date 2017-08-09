package net.termer.fabledhack;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import flands.Adventurer;
import flands.FLApp;
import net.termer.fabledhack.api.PlayerStat;
import net.termer.fabledhack.api.StatType;

public class StatWindow {
	private JFrame w = new JFrame("Change Stat");
	private JComboBox<String> ss = new JComboBox<String>();
	private JTextField sn = new JTextField(12);
	private JButton ab = new JButton("Apply");
	public StatWindow() {
		ss.addItem("Charisma");
		ss.addItem("Combat");
		ss.addItem("Magic");
		ss.addItem("Sanctity");
		ss.addItem("Scouting");
		ss.addItem("Thievery");
		ss.addItem("Stamina");
		
		ss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerStat stat = PlayerStat.getStat((String)ss.getSelectedItem());
				if(stat.getStatType() == StatType.ABILITY_STAT) {
					sn.setText(Integer.toString(stat.getAbility().affected));
				} else if(stat.getStatType() == StatType.RANK) {
					sn.setText(Integer.toString(stat.getRank().natural));
				} else if(stat.getStatType() == StatType.STAMINA) {
					sn.setText(Integer.toString(stat.getStamina().current));
				}
			}
		});
		
		ab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int stat = PlayerStat.getStatId((String)ss.getSelectedItem());
				PlayerStat st = PlayerStat.getStat((String)ss.getSelectedItem());
				Adventurer p = FLApp.getSingle().getAdventurer();
				try {
					if(st.getStatType() == StatType.ABILITY_STAT) {
						p.adjustAbility(stat, p.adjustAbility(stat, Integer.parseInt(sn.getText()))-st.getAbility().natural);
					} else {
						p.adjustAbility(stat, p.adjustAbility(stat, Integer.parseInt(sn.getText()))-st.getStamina().current);
					}
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(w, "Must be a number!");
				}
			}
		});
		
		w.setResizable(false);
		w.setLayout(new FlowLayout());
		w.getContentPane().add(ss);
		w.getContentPane().add(sn);
		w.getContentPane().add(ab);
		
		w.setSize(200, 200);
		w.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	public void open() {
		w.setVisible(true);
		PlayerStat stat = PlayerStat.getStat((String)ss.getSelectedItem());
		sn.setText(Integer.toString(stat.getAbility().affected));
	}
	public void close() {
		w.setVisible(false);
	}
}