package set;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JOptionPane;

public class SetDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame myFrame = new JFrame("This is a test");
		myFrame.setLocation(50,50);
		myFrame.setSize(800,600);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container myPane = myFrame.getContentPane();
		myPane.setLayout(null);
		myFrame.setVisible(true);

		SetCard myCard = new SetCard("1","Purple","Open","Diamonds",100,200,myFrame);
		System.out.println(myCard.picFileName());
				
		myCard.displayCard(10, 10);
		myFrame.setVisible(false);
		myFrame.setVisible(true);
		JOptionPane.showMessageDialog(myFrame,"That was the unhighlighted");
		myCard.highlight();
		JOptionPane.showMessageDialog(myFrame,"That was the highlighted");
		myCard.removeHighlight();
		
		SetDeck myDeck = new SetDeck(myFrame,100,200);
		myDeck.shuffle();
		SetCard myOtherCard = myDeck.deal();
		System.out.println(myOtherCard.getNumber()+" "+myOtherCard.getFill());
		myOtherCard.displayCard(100, 10);
		myFrame.setVisible(false);
		myFrame.setVisible(true);
		
		SetCard anotherCard = new SetCard("3","Green","Gradient","Diamonds",100,200,myFrame);
		anotherCard.displayCard(200, 10);
		myFrame.setVisible(false);
		myFrame.setVisible(true);
	}

}
