package set;
import javax.swing.JFrame; // for JFrame
import javax.swing.JLabel;// for JLabel
import javax.swing.JButton;
import javax.swing.JOptionPane; // messages are displayed using JOptionPane
import javax.swing.ImageIcon; // messages have an icon
import java.awt.*; // for graphics & MouseListener 
import java.awt.event.*; // need for events and MouseListener
import java.util.Timer;
import java.util.TimerTask;
import java.awt.font.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SetController extends TimerTask implements MouseListener {
	
	final int BEGINNER = 0;
	final int INTERMEDIATE = 1;
	final int EXPERT = 2;
	final int[] levels = {BEGINNER,INTERMEDIATE,EXPERT};
	final String[] levelName = {"Beginner","Moderate","Expert"};
	private int level;
	private String filename;
	private String playerName;
	private SetCard[] hintSet = new SetCard[3];
	private SetPlayer[] savedPlayers = new SetPlayer[1000];
	private int numPlayers;
	final int[] TIME_TO_FIND_SET = {30,20,10}; //30 sec for beginner, 20 sec for moderate, 10 for expert
	private JFrame gameJFrame;
    private boolean gameIsReady = false;
    private Timer gameTimer = new java.util.Timer();
    private int secondsLeft;
    //private TimerTask timerLeft = new java.util.TimerTask();
    private SetDeck myDeck;
    final int NUMBER_OF_CARDS = 12;
	final int MAX_NUMBER_OF_CARDS = 21;
    private boolean isCardOnTable[] = new boolean[MAX_NUMBER_OF_CARDS];
    private SetCard cardOnTable[] = new SetCard[MAX_NUMBER_OF_CARDS];
    private int currentCardsOnTable;
	private int cardXPosition[] = new int[MAX_NUMBER_OF_CARDS];
	private int cardYPosition[] = new int[MAX_NUMBER_OF_CARDS];
	private int cardMargin;
	private int cardWidth;
	private int cardHeight;
	private int noSetWidth;
	private int noSetHeight;
	private int noSetXPosition;
	private int noSetYPosition;
	private JButton noSet;
	private int score = 0;
	final int[] numberOfPointsToDeduct = {5,5,10};
	final int[] numberOfPointsToAdd = {10,5,5};
	private int selectedCards = 0;
	private SetCard[] selection = new SetCard[3];
	private JLabel scoreLabel = new JLabel();
	private JLabel timerLabel = new JLabel();

	public SetController() {
		gameJFrame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		gameJFrame.setSize(screenSize.width,screenSize.height);
		cardWidth = gameJFrame.getWidth()/12;
		cardMargin = cardWidth/6;
		cardWidth = cardWidth - cardMargin;
		cardHeight = 2*gameJFrame.getHeight()/9;
		gameJFrame.setBackground(Color.DARK_GRAY);
		gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameJFrame.getContentPane().setLayout(null);
		gameJFrame.setVisible(true);
		//timer label
		//label.setFont(new Font("Monospaced", Font.PLAIN, 14));
		//score label
		
		//Set up display cards
		cardYPosition[0] = cardYPosition[3] = cardYPosition[6] = cardYPosition[9] = cardYPosition[12] = cardYPosition[15] = cardYPosition[18] = 0+cardMargin; //1st column
		cardYPosition[1] = cardYPosition[4] = cardYPosition[7] = cardYPosition[10] = cardYPosition[13] = cardYPosition[16] = cardYPosition[19] = cardHeight + 2*cardMargin; //2nd column
		cardYPosition[2] = cardYPosition[5] = cardYPosition[8] = cardYPosition[11] = cardYPosition[14] = cardYPosition[17] = cardYPosition[20] = cardHeight* 2 + 3*cardMargin; //3rd column
		cardXPosition[0] = cardXPosition[1] = cardXPosition[2] = gameJFrame.getWidth()/2 - cardMargin/2 - cardWidth; //1st row
		cardXPosition[3] = cardXPosition[4] = cardXPosition[5] = gameJFrame.getWidth()/2 + cardMargin/2; //2nd row
		cardXPosition[6] = cardXPosition[7] = cardXPosition[8] = gameJFrame.getWidth()/2 - (3*cardMargin)/2 - 2*cardWidth; //3rd row
		cardXPosition[9] = cardXPosition[10] = cardXPosition[11] = gameJFrame.getWidth()/2 + (3*cardMargin)/2 + cardWidth; // 4th row
		cardXPosition[12] = cardXPosition[13] = cardXPosition[14] = gameJFrame.getWidth()/2 - (5*cardMargin)/2 - 3*cardWidth; // 5th row (for additional cards)
		cardXPosition[15] = cardXPosition[16] = cardXPosition[17] = gameJFrame.getWidth()/2 + (5*cardMargin)/2 + 2*cardWidth; // 6th row (for additional cards)
		cardXPosition[18] = cardXPosition[19] = cardXPosition[20] = gameJFrame.getWidth()/2 - (7*cardMargin)/2 - 4*cardWidth; // 7th row (for additional cards)
		
		noSetWidth = 2*cardWidth + cardMargin;
		noSetHeight = cardHeight/4;
		noSetYPosition = 3*cardHeight + 4*cardMargin;
		noSetXPosition = cardXPosition[0];
		
        scoreLabel.setBounds(noSetXPosition,noSetYPosition+noSetHeight/2+cardMargin,noSetWidth,cardHeight/4);
        timerLabel.setBounds(noSetXPosition,noSetYPosition+noSetHeight+2*cardMargin,noSetWidth,cardHeight/4);
		
		noSet = new JButton("No Set On Table");
		noSet.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
		noSet.setBounds(noSetXPosition,noSetYPosition,noSetWidth,noSetHeight);
		noSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button pressed");
				noSetButtonPushed();
			}
		});
		
		for(int i = 0; i < MAX_NUMBER_OF_CARDS; i++) {    //max cards on table
			cardOnTable[i] = null;
			isCardOnTable[i] = false;
		
		}
		startGame();
		
		
		}
		
	
	public void resetGame(){  
		gameIsReady = false;
		currentCardsOnTable = 0;
		score = 0;
		gameIsReady = true; 
		int x = JOptionPane.showConfirmDialog(gameJFrame, "Play again?", null, JOptionPane.YES_NO_OPTION);
		if(x==JOptionPane.YES_OPTION)
		{
			startGame();
		}
		else
		{
			System.exit(0);
		}
		}

	public boolean gameIsReady() {
		return gameIsReady;

	}

	public boolean cardsOnTable() {
		return currentCardsOnTable > 0;

	}
	
	
	public void run() {
		secondsLeft--;
		gameJFrame.getContentPane().add(timerLabel);
		timerLabel.setText("Timer remaining:   " + secondsLeft);
		timerLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
		timerLabel.setVisible(true);
		if (secondsLeft <= 0) {
			JOptionPane.showMessageDialog(gameJFrame,"BUMMER! You lost points!","Timer went off!",JOptionPane.WARNING_MESSAGE);	
			System.out.println("Timer went off!");
			System.out.println("BUMMER! You lost points!");
			deductPoints();
			displayScore();
			restartTimer();
			timerLabel.setText("Timer remaining:   " + secondsLeft);
		}
		
}
	
	public void restartTimer() {
			secondsLeft = TIME_TO_FIND_SET[level];
		    System.out.println("Time left = " + secondsLeft);
	    }

	//class List implements ActionListener {
	    //@Override
	    //public void actionPerformed(ActionEvent e)
			

	//is the game ready?
	//user has to click message/button 
	//time keeps going 
	//once user accpets message 
	//game is now ready with remaining time
	
	public void startGame() {
		gameIsReady = false;

		// Player name entry
		String thisName = JOptionPane.showInputDialog(gameJFrame,"Enter your name:");
		if(thisName != null)
		{
			 //if(!thisName.isBlank())
				 if(thisName.length() > 0)
			{
				System.out.println("Player name:"+thisName);
				playerName = thisName;
			}
			else
			{
				JOptionPane.showMessageDialog(gameJFrame,"Please try again with non-blank name!","Error",JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
		else
		{
			System.exit(0);
		}
			
		// Level selection
		int x = JOptionPane.showOptionDialog(gameJFrame, "Pick your level!", "Level Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, levelName, levelName[0]);
		if(x >= 0 && x <= 2)
		{
			level = this.levels[x];
			secondsLeft = TIME_TO_FIND_SET[level];
			filename = "src/set/scores/"+levelName[level]+".txt";
			//System.out.println(getHighScore().toString());
			//saveScore();
			System.out.println("My level: "+level);
		}
		else
		{
			System.exit(0);
		}
		
		// Good luck message
		int y = JOptionPane.showConfirmDialog(gameJFrame, "Off we go!", "Ready?", JOptionPane.OK_CANCEL_OPTION);
		
		if(y==JOptionPane.OK_OPTION)
		{
			myDeck = new SetDeck(gameJFrame,cardWidth,cardHeight); //creates instance of class, go run const.
			System.out.println(gameJFrame);
			myDeck.shuffle();
			System.out.println("Shuffled");
			currentCardsOnTable = 0;
			for(int i = 0; i < NUMBER_OF_CARDS; i++) {
				cardOnTable[i] = myDeck.deal();
				System.out.println("Dealing "+cardOnTable[i].getNumber()+" of "+cardOnTable[i].getFill());
			}
			System.out.println("All Dealt");
			drawDisplayCard();
			System.out.println("Cards Displayed");
			displayScore();
			gameTimer.schedule(this,(long)0,1000);
			gameIsReady = true;
			gameJFrame.addMouseListener(this);
		}
		else
		{
			System.exit(0);
		}
			
	}
	
	public void dealCards() {
		// deal cards to open spaces on table
		reconfigureTable();
		for(int i = 0; i < currentCardsOnTable; i++)
		{
			if(cardOnTable[i] == null)
			{
				if(myDeck.cardsLeft() > 0)
				{
					cardOnTable[i] = myDeck.deal();
				}
			}
		}
		drawDisplayCard();
	}
	
	public void displayScore() {
        gameJFrame.getContentPane().add(scoreLabel);
       	scoreLabel.setText("Here is your score: " + score);
        scoreLabel.setVisible(true);
		scoreLabel.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
		
        // Find window size to allow more cards
        // window listener
        // hard code positions
	}
	
	public void displayFinalScore()
	{
		SetPlayer highScore = getHighScore();
		if(highScore != null)
		{
			if(score > highScore.getScore())
			{
				// "New high score!"
				// "Your score: "
				// "Previous high score: "
				int x = JOptionPane.showConfirmDialog(gameJFrame, "New high score!\nYour score: "+score+"\nPrevious high score: "+highScore.toString(), "Final score", JOptionPane.OK_CANCEL_OPTION);
				if(x==JOptionPane.CANCEL_OPTION)
				{
					System.exit(0);
				}
			}
			else if(score < highScore.getScore())
			{
				// "Your score: "
				// "High score: "
				int x = JOptionPane.showConfirmDialog(gameJFrame, "Your score: "+score+"\nHigh score: "+highScore.toString(), "Final score", JOptionPane.OK_CANCEL_OPTION);
				if(x==JOptionPane.CANCEL_OPTION)
				{
					System.exit(0);
				}
			}
			else
			{
				// "High score!"
				// "Your score: "
				// "Previous high score: "
				int x = JOptionPane.showConfirmDialog(gameJFrame, "High score!\nYour score: "+score+"\nPrevious high score: "+highScore.toString(), "Final score", JOptionPane.OK_CANCEL_OPTION);
				if(x==JOptionPane.CANCEL_OPTION)
				{
					System.exit(0);
				}
			}
		}
		else
		{
			int x = JOptionPane.showConfirmDialog(gameJFrame, "New high score!\nYour score: "+score, "Final score", JOptionPane.OK_CANCEL_OPTION);
			if(x==JOptionPane.CANCEL_OPTION)
			{
				System.exit(0);
			}
		}
		saveScore();
	}
	
	public void saveScore()
	{
		// make player object with name and score
		SetPlayer thisPlayer = new SetPlayer(score,playerName);
		// append player name and score to existing text file
		try
		{
			FileWriter myWriter = new FileWriter(filename,true);
			myWriter.write("\n"+thisPlayer.toString());
			myWriter.close();
			System.out.println("Output to file successful.");
		}
		catch(IOException e)
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public SetPlayer getHighScore()
	{
		numPlayers = 0;
		SetPlayer highScore;
		try
		{
			File playerScores = new File(filename);
			Scanner myScanner = new Scanner(playerScores);
			while(myScanner.hasNextLine())
			{
				String playerInfo = myScanner.nextLine();
				 if(!playerInfo.isBlank())
					 //if(thisName.length() > 0)
				{
					String[] info = playerInfo.split(": ");
					SetPlayer myPlayer = new SetPlayer(Integer.parseInt(info[1]),info[0]);
					savedPlayers[numPlayers%savedPlayers.length] = myPlayer;
					numPlayers++;
				}
			}
			myScanner.close();
			highScore = SetPlayer.highScore(savedPlayers, numPlayers);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
			highScore = null;
		}
		return highScore;
	}
	
	public void deductPoints()
	{
		score = score - numberOfPointsToDeduct[level];
		checkScoreLevel();
	}
	
	public void checkScoreLevel()
	{
		if(score<0)
		{
			if(level != EXPERT)
			{
				score = 0;
			}
		}
	}
	
	public void addPoints()
	{
		score = score + numberOfPointsToDeduct[level];
	}
	
	public boolean isThereASetOnTable() {
		// return true if set on table, false if not
		boolean set = false;
		for(int i = 0; i < MAX_NUMBER_OF_CARDS-2; i++)
		{
			if(cardOnTable[i] != null)
			{
				for(int j = i+1; j < MAX_NUMBER_OF_CARDS-1; j++)
				{
					if(cardOnTable[j] != null)
					{
						for(int k = j+1; k < MAX_NUMBER_OF_CARDS; k++)
						{
							if(cardOnTable[k] != null)
							{
								SetCard[] theseCards = new SetCard[3];
								theseCards[0] = cardOnTable[i];
								theseCards[1] = cardOnTable[j];
								theseCards[2] = cardOnTable[k];
								if(areTheseASet(theseCards))
								{
									set = true;
								}
							}
						}
					}
				}
			}
		}
		return set;
	
	}
	
	public void getHint()
	{
		for(int i = 0; i < currentCardsOnTable-5; i++)
		{
			if(cardOnTable[i] != null)
			{
				for(int j = i+1; j < currentCardsOnTable-4; j++)
				{
					if(cardOnTable[j] != null)
					{
						for(int k = j+1; k < currentCardsOnTable-3; k++)
						{
							if(cardOnTable[k] != null)
							{
								SetCard[] theseCards = new SetCard[3];
								theseCards[0] = cardOnTable[i];
								theseCards[1] = cardOnTable[j];
								theseCards[2] = cardOnTable[k];
								if(areTheseASet(theseCards))
								{
									hintSet[0] = theseCards[0];
									theseCards[0].highlight();
									theseCards[0].redrawCard();
									if(level == BEGINNER)
									{
										hintSet[1] = theseCards[1];
										theseCards[1].highlight();
										theseCards[1].redrawCard();										
									}
									i = MAX_NUMBER_OF_CARDS;
									j = MAX_NUMBER_OF_CARDS;
									k = MAX_NUMBER_OF_CARDS;
								}
							}
						}
					}
				}
			}
		}
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
	
	
	public void noSetButtonPushed()
	{
		// runs if user selected no set button
		if(isThereASetOnTable())
		{
			deductPoints();
			System.out.println("Score: "+score);
			dealLevel();
		}
		else
		{
			if(myDeck.cardsLeft() > 0)
			{
				addCards();
			}
			else
			{
				displayFinalScore();
				resetGame();
			}
			// reset timer when added
		}
		restartTimer();
	}
	
	public void dealLevel()
	{
		if(level != EXPERT)
		{
			addCards();
			getHint();
		}
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
					cardOnTable[currentCardsOnTable+i] = myDeck.deal();
				}
			}
			drawDisplayCard();
		}
		displayScore();
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
		selectedCards--;
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
			selection[i] = null;
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
					i = cardOnTable.length;
				}
			}
		}
	}
	
	public void reconfigureTable()
	{
		// reconfigure the table once, after new cards have been added, a set is taken away
		if(currentCardsOnTable > NUMBER_OF_CARDS)
		{
			SetCard[] cardsToAdd = {null,null,null};
			int beg = 0;
			for(int i = currentCardsOnTable-3; i < currentCardsOnTable; i++)
			{
				if(cardOnTable[i] != null)
				{
					cardsToAdd[beg] = cardOnTable[i];
					beg++;
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
		currentCardsOnTable = 0;
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
		System.out.println("Cards on table: "+currentCardsOnTable);
		
		gameJFrame.add(noSet);
		
		gameJFrame.setVisible(false);
		gameJFrame.setVisible(true);
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(gameIsReady)
		{
			if(hintSet[0]!=null)
			{
				for(int i = 0; i < hintSet.length; i++)
				{
					if(hintSet[i] != null)
					{
						hintSet[i].deselectCard();
						hintSet[i].redrawCard();
						hintSet[i] = null;
					}
				}
			}
			SetCard selected = getSelectedCard(e.getX(),e.getY());
			System.out.println(selected);
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
						System.out.println(areTheseASet(selection));
						//deselectTheseCards();
						if(areTheseASet(selection))
						{
							addPoints();
							System.out.println(score);
							userFoundASet();
							System.out.println("Cards left? "+myDeck.cardsLeft());
							if(myDeck.cardsLeft() > 0)
							{
								dealCards();
								restartTimer();
							}
							else
							{
								System.out.println("Set?"+isThereASetOnTable());
								if(!isThereASetOnTable())
								{
									// displayScore(); Find another name for displaying/recording at the end of the game
									// check if user wants to play again with JOptionPane
									displayFinalScore();
									resetGame();
								}
							}
						}
						else
						{
							deductPoints();
							System.out.println("Score: "+score);
							deselectTheseCards();
							restartTimer();
						}
					}
				}
				else
				{
					selected.deselectCard();
					removeThisCard(selected);
				}
			}
		}
		displayScore();
	}

	
	public static void main(String[] args) {
		System.out.println("Starting Main");
		SetController myGame = new SetController();
	
		
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
		gameJFrame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		gameJFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

	
	}
	
}

