package set;

import javax.swing.JFrame; // for JFrame
import javax.swing.JLabel;
import javax.swing.JOptionPane; // messages are displayed using JOptionPane
import javax.swing.ImageIcon; // messages have an icon
import java.awt.*; // for graphics & MouseListener 
import java.awt.event.*; // need for events and MouseListener
import java.util.Timer;
import java.util.TimerTask;

public class SetController extends TimerTask implements MouseListener {
	
	private int TIME_TO_FIND_SET = 10000; //Arbitrary 10 seconds
	private JFrame gameJFrame;
	private JLabel gameJLabel;
    private Container gameContentPane;
    private boolean gameIsReady = false;
    private Timer gameTimer = new java.util.Timer();
    private int xMouseOffsetToContentPaneFromJFrame = 0;
    private int yMouseOffsetToContentPaneFromJFrame = 0;
    private SetDeck myDeck;
    private int missCounter = 0;
    private int timerCounter = 0;
	final public int NUMBER_OF_CARDS = 12;
	final public int MAX_NUMBER_OF_CARDS = 21;
    private boolean isCardOnTable[] = new boolean[MAX_NUMBER_OF_CARDS];
    private SetCard cardOnTable[] = new SetCard[MAX_NUMBER_OF_CARDS];
    private int currentCardsOnTable;
	//Card[] testCard;
	private int cardXPosition[] = new int[MAX_NUMBER_OF_CARDS];
	private int cardYPosition[] = new int[MAX_NUMBER_OF_CARDS];
	final int cardMargin = 20;
	int titleBarOffset = 20;
	final int cardWidth = 100;
	final int cardHeight = 200;
	private int score;
	final int numberOfPointsToDeduct = 5;
	final int numberOfPointsToAdd = 10;
	private int selectedCards = 0;
	private SetCard[] selection = new SetCard[3];



	public SetController() {
		gameJFrame = new JFrame();
		gameJFrame.setSize(800,800);//Steve's code for controller*****
		gameJFrame.setBackground(Color.DARK_GRAY);
		gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameJFrame.getContentPane().setLayout(null);
		gameJFrame.setVisible(true);
		//cardWidth =(gameJFrame.getSize().width-4*cardMargin)/3;
		//cardHeight = (gameJFrame.getSize().width-4*cardMargin-titleBarOffset)/3;
		//Set up display cards
		cardYPosition[0] = cardYPosition[3] = cardYPosition[6] = cardYPosition[9] = cardYPosition[12] = cardYPosition[15] = cardYPosition[18] = 0+cardMargin; //1st column
		cardYPosition[1] = cardYPosition[4] = cardYPosition[7] = cardYPosition[10] = cardYPosition[13] = cardYPosition[16] = cardYPosition[19] = cardHeight + 2*cardMargin; //2nd column
		cardYPosition[2] = cardYPosition[5] = cardYPosition[8] = cardYPosition[11] = cardYPosition[14] = cardYPosition[17] = cardYPosition[20] = cardHeight* 2 + 3*cardMargin; //3rd column
		cardXPosition[0] = cardXPosition[1] = cardXPosition[2] = 0+cardMargin + titleBarOffset; //1st row
		cardXPosition[3] = cardXPosition[4] = cardXPosition[5] = cardWidth + 2*cardMargin + titleBarOffset; //2nd row
		cardXPosition[6] = cardXPosition[7] = cardXPosition[8] = cardWidth *2 +3*cardMargin + titleBarOffset; //3rd row
		cardXPosition[9] = cardXPosition[10] = cardXPosition[11] = cardWidth*3 + 4*cardMargin + titleBarOffset; // 4th row
		cardXPosition[12] = cardXPosition[13] = cardXPosition[14] = cardWidth*4 + 5*cardMargin + titleBarOffset; // 5th row (for additional cards)
		cardXPosition[15] = cardXPosition[16] = cardXPosition[17] = cardWidth*5 + 6*cardMargin + titleBarOffset; // 6th row (for additional cards)
		cardXPosition[18] = cardXPosition[19] = cardXPosition[20] = cardWidth*6 + 7*cardMargin + titleBarOffset; // 7th row (for additional cards)
		
		
		for(int i = 0; i < MAX_NUMBER_OF_CARDS; i++) {    //max cards on table
			cardOnTable[i] = null;
			isCardOnTable[i] = false;
		
		}
		startGame();
		
		
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
		System.out.println("Timer went off!");
	}
	
	public void startGame() {
		gameIsReady = false;
		myDeck = new SetDeck(gameJFrame); //creates instance of class, go run const.
		System.out.println(gameJFrame);
		myDeck.shuffle();
		System.out.println("Shuffled");
		currentCardsOnTable = 0;
		for(int i = 0; i < 9; i++) {
			cardOnTable[i] = myDeck.deal();
			System.out.println("Dealing "+cardOnTable[i].getNumber()+" of "+cardOnTable[i].getFill());
		}
		System.out.println("All Dealt");
		drawDisplayCard();
		System.out.println("Cards Displayed");
		//displayScore();
		//gameTimer.schedule(this, (long)0, (long)TIME_TO_FIND_SET);
		gameIsReady = true;
		gameJFrame.addMouseListener(this);
			
	}
	
	public void dealCards() {
		// deal cards to open spaces on table
		reconfigureTable();
		for(int i = 0; i < currentCardsOnTable-1; i++)
		{
			if(cardOnTable[i] == null)
			{
				if(myDeck.cardsLeft() > 0)
				{
					cardOnTable[i] = myDeck.deal();
				}
			}
		}
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
		boolean set = false;
		for(int i = 0; i < currentCardsOnTable-2; i++)
		{
			for(int j = i+1; j < currentCardsOnTable-1; j++)
			{
				for(int k = j+1; k < currentCardsOnTable; k++)
				{
					SetCard[] theseCards = {cardOnTable[i],cardOnTable[j],cardOnTable[k]};
					set = areTheseASet(theseCards);
				}
			}
		}
		return set;
	
	}
	
	public boolean areTheseASet(SetCard[] myCards) {
		// return true if passed in array of cards is a set or not
		boolean set = true;
		if(!myCards[0].getFill().equals(myCards[1].getFill()))
		{
			if(myCards[2].getFill().equals(myCards[0].getFill()) || myCards[2].getFill().equals(myCards[1].getFill()))
			{
				set = false;
			}
		}
		else
		{
			if(!myCards[2].getFill().equals(myCards[0].getFill()) || !myCards[2].getFill().equals(myCards[1].getFill()))
			{
				set = false;
			}
		}
		if(!myCards[0].getColor().equals(myCards[1].getColor()))
		{
			if(myCards[2].getColor().equals(myCards[0].getColor()) || myCards[2].getColor().equals(myCards[1].getColor()))
			{
				set = false;
			}
		}
		else
		{
			if(!myCards[2].getColor().equals(myCards[0].getColor()) || !myCards[2].getColor().equals(myCards[1].getColor()))
			{
				set = false;
			}
		}
		if(!myCards[0].getShape().equals(myCards[1].getShape()))
		{
			if(myCards[2].getShape().equals(myCards[0].getShape()) || myCards[2].getShape().equals(myCards[1].getShape()))
			{
				set = false;
			}
		}
		else
		{
			if(!myCards[2].getShape().equals(myCards[0].getShape()) || !myCards[2].getShape().equals(myCards[1].getShape()))
			{
				set = false;
			}
		}
		if(!myCards[0].getNumber().equals(myCards[1].getNumber()))
		{
			if(myCards[2].getNumber().equals(myCards[0].getNumber()) || myCards[2].getNumber().equals(myCards[1].getNumber()))
			{
				set = false;
			}
		}
		else
		{
			if(!myCards[2].getNumber().equals(myCards[0].getNumber()) || !myCards[2].getNumber().equals(myCards[1].getNumber()))
			{
				set = false;
			}
		}
		return set;
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
		if(currentCardsOnTable < 21)
		{
			for(int i = 0; i < 3; i++)
			{
				if(myDeck.cardsLeft() > 0)
				{
					cardOnTable[currentCardsOnTable-1+i] = myDeck.deal();
				}
			}
			drawDisplayCard();
		}
	}
	
	public SetCard getSelectedCard(int xCoord,int yCoord)
	{
		// return either the Card that was selected or null for no card
		for(int i = 0; i < cardOnTable.length; i++)
		{
			if(cardOnTable[i] != null)
			{
				if(cardOnTable[i].isCardPushed(xCoord, yCoord))
				{
					return cardOnTable[i];
				}
			}
		}
		return null;
	}
	
	public void removeThisCard(SetCard toRemove)
	{
		// with for loop, remove the right card from array selection
		// make sure array is non-null values all at the front
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
	
	public void userFoundASet()
	{
		// remove the three cards in the selection array from the table
		for(int i = 0; i < 3; i++)
		{
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
		// remove a card from the array cardOnTable
		// remove a card from the table
		for(int i = 0; i < cardOnTable.length; i++)
		{
			if(cardOnTable[i] != null)
			{
				if(cardOnTable[i].equals(toUndeal))
				{
					cardOnTable[i].undrawCard();
					cardOnTable[i] = null;
					currentCardsOnTable--;
					i = cardOnTable.length;
				}
			}
		}
	}
	
	public void reconfigureTable()
	{
		// reconfigure the table once, after new cards have been added, a set is taken away
		if(currentCardsOnTable>12)
		{
			SetCard[] cardsToAdd = {null,null,null};
			int beg = 0;
			for(int i = currentCardsOnTable-4; i < currentCardsOnTable; i++)
			{
				if(cardOnTable[i] != null)
				{
					cardsToAdd[beg] = cardOnTable[i];
					cardOnTable[i] = null;
				}
			}
			int index = 0;
			for(int k = 0; k < currentCardsOnTable-3; k++)
			{
				if(cardOnTable[k] == null)
				{
					cardOnTable[k] = cardsToAdd[index];
					index++;
				}
			}
			drawDisplayCard();
		}
	}
	
	
	public void drawDisplayCard() {
		gameJFrame.getContentPane().removeAll();
		for (int i = 0; i < MAX_NUMBER_OF_CARDS; i++) {
			if (cardOnTable[i] != null) {
				System.out.println("Diplaying Card 'i' " + i);
				System.out.println(cardXPosition[i]+","+cardYPosition[i]);
				//cardOnTable[i] = new SetCard("1","Purple","Solid","Diamonds",gameJFrame);
				System.out.println(cardOnTable[i].getFrame());
				cardOnTable[i].displayCard(cardXPosition[i], cardYPosition[i]);
				isCardOnTable[i] = true;
				currentCardsOnTable++;
			}
		}
		gameJFrame.setVisible(false);
		gameJFrame.setVisible(true);
	}


	@Override
	public void mousePressed(MouseEvent e) {
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
							selectedCards = 0;
							if(areTheseASet(selection))
							{
								addPoints();
								userFoundASet();
								if(myDeck.cardsLeft() > 0) // gives an error
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

	
	public static void main(String[] args) {
		System.out.println("Starting Main");
		//SetController myGame = new SetController();
		new SetController();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
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

