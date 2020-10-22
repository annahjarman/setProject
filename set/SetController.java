package set;

import javax.swing.JFrame; // for JFrame
import javax.swing.JLabel;
import javax.swing.JOptionPane; // messages are displayed using JOptionPane
import javax.swing.ImageIcon; // messages have an icon
import java.awt.*; // for graphics & MouseListener 
import java.awt.event.*; // need for events and MouseListener
import java.util.Timer;

public class SetController extends JFrame implements MouseListener {
	
	private JFrame gameJFrame;
	private JLabel gameJLabel;
    private Container gameContentPane;
    private boolean gameIsReady = false;
    private boolean cardsOnTable = false;
    private Timer gameTimer = new java.util.Timer();
    private int xMouseOffsetToContentPaneFromJFrame = 0;
    private int yMouseOffsetToContentPaneFromJFrame = 0;
    private int missCounter = 0;
    private int timerCounter = 0;
	final public int NUMBER_OF_CARDS = 12;
	final public int MAX_NUMBER_OF_CARDS = 21;
	//Card[] testCard;
	private int[] cardXPosition = new int[MAX_NUMBER_OF_CARDS];
	private int[] cardYPosition = new int[MAX_NUMBER_OF_CARDS];
	private SetCard[] cardsDealt = new SetCard[MAX_NUMBER_OF_CARDS];
	final int cardMargin = 20;
	final int titleBarOffset = 20;
	final int cardWidth = 100;
	final int cardHeight = 200;
	private int score;
	final int numberOfPointsToDeduct = 5;
	final int numberOfPointsToAdd = 10;
	private int selectedCards;
	private SetCard[] selection = new SetCard[3];
	private SetDeck thisDeck;


	public SetController() {
			setSize(800,800);//Steve's code for controller*****
			setBackground(Color.DARK_GRAY);
			//cardWidth =(getSize().width-4*cardMargin)/3;
			//cardHeight = (getSize().width-4*cardMargin-titleBarOffset)/3;
			//Set up display cards
			cardYPosition[0] = cardYPosition[3] = cardYPosition[6] = cardYPosition[9] = cardYPosition[12] = cardYPosition[15] = cardYPosition[18] = 0+cardMargin; //1st column
			cardYPosition[1] = cardYPosition[4] = cardYPosition[7] = cardYPosition[10] = cardYPosition[13] = cardYPosition[16] = cardYPosition[19] = cardWidth + 2*cardMargin; //2nd column
			cardYPosition[2] = cardYPosition[5] = cardYPosition[8] = cardYPosition[11] = cardYPosition[14] = cardYPosition[17] = cardYPosition[20] = cardWidth* 2 + 3*cardMargin; //3rd column
			cardXPosition[0] = cardXPosition[1] = cardXPosition[2] = 0+cardMargin + titleBarOffset; //1st row
			cardXPosition[3] = cardXPosition[4] = cardXPosition[5] = cardHeight + 2*cardMargin + titleBarOffset; //2nd row
			cardXPosition[6] = cardXPosition[7] = cardXPosition[8] = cardHeight *2 +3*cardMargin + titleBarOffset; //3rd row
			cardXPosition[9] = cardXPosition[10] = cardXPosition[11] = cardHeight*3 + 4*cardMargin + titleBarOffset; // 4th row
			cardXPosition[12] = cardXPosition[13] = cardXPosition[14] = cardHeight*4 + 5*cardMargin + titleBarOffset; // 5th row (for additional cards)
			cardXPosition[15] = cardXPosition[16] = cardXPosition[17] = cardHeight*5 + 6*cardMargin + titleBarOffset; // 6th row (for additional cards)
			cardXPosition[18] = cardXPosition[19] = cardXPosition[20] = cardHeight*6 + 7*cardMargin + titleBarOffset; // 7th row (for additional cards)
			// create card
		}
	
	//testCard = new SetController[NUMBER_OF_CARDS];


	public void timer() { //not the timer class
		int delay = 10000; //milliseconds
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				repaint(); //calls function below
			}
			
		};
	}
	
	public void resetGame()
	{
		// reset the game
	}
	
	public boolean gameIsReady() {
		return gameIsReady;
		
	}
	
	public boolean cardsOnTable() {
		return gameIsReady;
		
	}
	
	public void run() {
		
	}
	
	public void startGame() {
		
		
	}
	
	public void dealCards() 
	{
		// deal cards to open spaces on table
	}
	
	public void displayScore() {
		
	}
	
	public void deductPoints()
	{
		score = score - numberOfPointsToDeduct;
	}
	
	public void addPoints()
	{
		score = score + numberOfPointsToDeduct;
	}
	
	public boolean isThereASetOnTable() {
		// return true if set on table, false if not
		return false;
	}
	
	public boolean areTheseASet(SetCard[] myCards) {
		// return true if passed in array of cards is a set or not
		return false;
		
	}

	public boolean noSetSelected(int xCoord,int yCoord)
	{
		// function to return true if user selected no set button
		return false;
	}
	
	public void dealLevel()
	{
		// will either add more cards or not, depending on level
	}
	
	public void addCards()
	{
		// add 3 more cards to the table
	}
	
	public SetCard getSelectedCard(int xCoord,int yCoord)
	{
		// return either the Card that was selected or null for no card
		for(int i = 0; i < cardsDealt.length; i++)
		{
			if(cardsDealt[i] != null)
			{
				if(cardsDealt[i].isCardPushed(xCoord, yCoord))
				{
					return cardsDealt[i];
				}
			}
		}
		return null;
	}
	
	public void removeThisCard(SetCard toRemove)
	{
		// with for loop, remove the right card from array selection
		// make sure array is non-null values all at the front
		toRemove.undrawCard();
		if(selection[0].equals(toRemove))
		{
			if(selection.length==1)
			{
				selection[0] = null;
			}
			else
			{
				selection[0] = selection[1];
				selection[1] = null;
			}
		}
		else
		{
			selection[1] = null;
		}
	}
	
	public void undrawTheseCards()
	{
		// remove the three cards in the selection array from the table
		for(int i = 0; i < 3; i++)
		{
			selection[i].undrawCard();
			selection[i].deselectCard();
			undealThisCard(selection[i]);
			selection[i] = null;
		}
	}
	
	public void deselectTheseCards()
	{
		// deselect the three cards in the selection array
		for(int i = 0; i < 3; i++)
		{
			selection[i].deselectCard();
		}
	}
	
	public void undealThisCard(SetCard toUndeal)
	{
		// remove a card from the array cardsDealt
		for(int i = 0; i < cardsDealt.length; i++)
		{
			if(cardsDealt[i].equals(toUndeal))
			{
				cardsDealt[i] = null;
				i = cardsDealt.length;
			}
		}
	}
	
	public void reconfigureTable()
	{
		// reconfigure the table once, after new cards have been added, a set is taken away
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
		if(gameIsReady)
		{
			if(noSetSelected(e.getX(),e.getY())) // if user selected button to say there's no set on table
			{
				if(isThereASetOnTable())
				{
					deductPoints();
					dealLevel();
				}
				else
				{
					addCards();
					// reset timer when added
				}
			}
			else
			{
				SetCard selected = getSelectedCard(e.getX(),e.getY());
				if(selected != null)
				{
					if(!selected.isSelected())
					{
						selected.selectCard();
						selection[selectedCards] = selected;
						selectedCards++;
						if(selectedCards==3)
						{
							if(areTheseASet(selection))
							{
								addPoints();
								undrawTheseCards();
								if(thisDeck.cardsLeft() > 0)
								{
									dealCards();
								}
								else
								{
									if(!isThereASetOnTable())
									{
										displayScore();
										resetGame();
									}
								}
							}
							else
							{
								deductPoints();
								deselectTheseCards();
							}
						}
						// reset timer when added
					}
					else
					{
						selected.deselectCard();
						removeThisCard(selected);
					}
				}
			}
		}
		
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
