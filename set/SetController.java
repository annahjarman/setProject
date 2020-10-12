package setGame;

import javax.swing.JFrame; // for JFrame
import javax.swing.JOptionPane; // messages are displayed using JOptionPane
import javax.swing.ImageIcon; // messages have an icon
import java.awt.*; // for graphics & MouseListener 
import java.awt.event.*; // need for events and MouseListener
import java.util.Timer;

public class SetController extends JFrame implements MouseListener {
	
	private JFrame gameJFrame;
    private Container gameContentPane;
    private boolean gameIsReady = false;
    private Timer gameTimer = new java.util.Timer();
    private int xMouseOffsetToContentPaneFromJFrame = 0;
    private int yMouseOffsetToContentPaneFromJFrame = 0;
    private int missCounter = 0;
    private int timerCounter = 0;
	final public int NUMBER_OF_CARDS = 12;
	//Card[] testCard;
	int cardXPosition[] = new int[NUMBER_OF_CARDS];
	int cardYPosition[] = new int[NUMBER_OF_CARDS];
	int cardMargin = 20;
	int titleBarOffset = 20;
	int cardWidth;
	int cardHeight;
	
    
public SetController() {
	setSize(800,800);//Steve's code for controller*****
	setBackground(Color.DARK_GRAY);
	cardWidth =(getSize().width-4*cardMargin)/3;
	cardHeight = (getSize().width-4*cardMargin-titleBarOffset)/3;
	//Set up display cards
	cardXPosition[0] = cardXPosition[3] = cardXPosition[6] = 0+cardMargin; //1st column
	cardXPosition[1] = cardXPosition[4] = cardXPosition[7] = cardWidth + 2*cardMargin; //2nd column
	cardXPosition[2] = cardXPosition[5] = cardXPosition[8] = cardWidth* 2 + 3*cardMargin; //3rd column
	cardYPosition[0] = cardYPosition[1] = cardYPosition[2] = 0+cardMargin + titleBarOffset; //1st row
	cardYPosition[3] = cardXPosition[4] = cardXPosition[5] = cardHeight + 2*cardMargin + titleBarOffset; //2nd row
	cardYPosition[6] = cardXPosition[7] = cardXPosition[8] = cardHeight *2 +3*cardMargin + titleBarOffset; //3rd column\
	
	//testCard = new SetController[NUMBER_OF_CARDS];
}

public void timer() { //not the timer class
	int delay = 10000; //milliseconds
	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			repaint(); //calls function below
		}
	}
}

public void startGame() {
	
	
}
public void drawDisplayCard(Graphics g) {
	for (int i = 0; i < NUMBER_OF_CARDS; i++) {
		//testCard[i].draw(g, cardXPosition[i], cardYPosition[i], cardWidth, cardHeight);
		
	}
	g.drawString("Try and not click here!", getSize().width/2-40, getSize().height/2+20);
}
	public static void main(String[] args) {
		SetController myGame = new SetController();

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
