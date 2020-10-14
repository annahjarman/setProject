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

		SetCard myCard = new SetCard("1","Purple","Open","Diamonds",myFrame);
		System.out.println(myCard.picFileName());
				
		myCard.displayCard(10, 10, 100, 200);
		JOptionPane.showMessageDialog(myFrame,"That was the unhighlighted");
		myCard.highlight();
		JOptionPane.showMessageDialog(myFrame,"That was the highlighted");
		myCard.removeHighlight();
	}

}
