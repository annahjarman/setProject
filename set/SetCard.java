package set;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

// This is a test

public class SetCard {
	
	private int number; // can be {1,2,3}
	private String color; // can be {"red","blue","green"}
	private String fill; // can be {"empty","solid","gradient"}
	private String shape; // can be {"rectangle","circle","squiggle"}
	private boolean selected;
	protected JFrame gameFrame;
	protected JLabel gameLabel;
	
	public SetCard(int number,String color,String fill,String shape)
	{
		this.number = number;
		this.color = color;
		this.fill = fill;
		this.shape = shape;
		this.selected = false;
	}
	
	public String picFileName()
	{
		String fileName = "/cardPics/"+this.shape+"/"+this.color+"/"+this.fill+"/"+Integer.toString(this.number);
		return(fileName);
	}
	
	public void displayCard(JFrame gameFrame,int xCoord,int yCoord)
	{
		// display picture of card to gameFrame using gameLabel
		ImageIcon picture = new ImageIcon(picFileName());
	}
	
	public void cardPressed()
	{
		if(this.selected)
		{
			deselectCard();
		}
		else
		{
			selectCard();
		}
	}
	
	public void selectCard()
	{
		this.selected = true;
		// if card is visible, highlight()
	}
	
	public void deselectCard()
	{
		this.selected = false;
		// if card visible, removeHighlight()
	}
	
	public void highlight()
	{
		// highlight selected card
	}
	
	public void removeHighlight()
	{
		// remove highlight on card
	}
	
	public boolean isSelected()
	{
		return this.selected;
	}

}
