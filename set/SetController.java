package set;

import javax.swing.JFrame; // for JFrame
import javax.swing.JOptionPane; // messages are displayed using JOptionPane
import javax.swing.ImageIcon; // messages have an icon
import java.awt.*; // for graphics & MouseListener 
import java.awt.event.*; // need for events and MouseListener
import java.util.Timer;

public class SetController {
	
    private JFrame gameJFrame;
    private Container gameContentPane;
    private boolean gameIsReady = false;
    private Timer gameTimer = new java.util.Timer();
    private int xMouseOffsetToContentPaneFromJFrame = 0;
    private int yMouseOffsetToContentPaneFromJFrame = 0;
    private int missCounter = 0;
    private int timerCounter = 0;
	
    
public SetController() {
		

}
	public static void main(String[] args) {
		

	}

}
