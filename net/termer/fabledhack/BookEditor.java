package net.termer.fabledhack;

import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.termer.fabledhack.api.ButtonHandler;
import net.termer.utils.Utils;
import flands.FLApp;

public class BookEditor {
    private JFrame frame = new JFrame("FabledHack Book Editor");
    private JTextArea textField = new JTextArea(40, 40);
    private File file = null;
    private JLabel actionText = new JLabel("");
    private JFileChooser jfc = new JFileChooser();
    
    public BookEditor() {
        // Setup GUI
        textField.setMargin(new Insets(4, 4, 4, 4));
        frame.getContentPane().add(new JScrollPane(textField), "Center");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(false);
        
        frame.requestFocus();
        textField.requestFocus();
        textField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        frame.setJMenuBar(new JMenuBar());
        frame.addWindowListener(new WindowListener() {
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
        frame.getJMenuBar().setVisible(true);
        
        jfc.setDialogTitle("Select File to Edit");
        jfc.setCurrentDirectory(new File("book1"));
        
        ButtonHandler.addButton("Open", new Runnable() {
        	public void run() {
        		try {
        			jfc.showOpenDialog(FLApp.getSingle());
        			if(jfc.getSelectedFile().exists()) {
        				file = jfc.getSelectedFile();
        				String tmp = "";
        				String[] lns = null;
        				try {
        					actionText.setText("Loading page...");
        					lns = Utils.getFile(file);
        					actionText.setText("Page loaded");
        				} catch (IOException e) {
        					actionText.setText("Error loading page");
        					new FabledHack().log("Error loading page");
        				}
        				for(int i = 0; i < lns.length; i++) {
        					tmp+=lns[i]+"\n";
        				}
        				textField.setText(tmp);
        			} else {
        				actionText.setText("File does not exist");
        			}
        		} catch(NullPointerException ex) {
        			actionText.setText("No file selected");
        		}
        	}
        }, frame.getJMenuBar());
        ButtonHandler.addButton("Save", new Runnable() {
        	public void run() {
        		try {
        			if(file.exists()) {
        				try {
        					Utils.writeFile(file.getAbsolutePath(), textField.getText().split("\n"), false);
        					actionText.setText("Saved");
        				} catch(Exception e) {
        					actionText.setText("Error saving file");
        				}
        			} else {
        				actionText.setText("No file selected");
        			}
        		} catch(Exception ex) {
        			actionText.setText("No file selected");
        		}
        	}
        }, frame.getJMenuBar());
        frame.getJMenuBar().add(actionText);
    }
    public void open() {
    	frame.setVisible(true);
    }
    public void close() {
    	frame.setVisible(false);
    }
}