package set;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SetCard {
	
	private String number; // can be {"One","Two","Three"}
	private String color; // can be {"Red","Purple","Green"}
	private String fill; // can be {"Open","Solid","Gradient"}
	private String shape; // can be {"Diamonds","Ovals","Squiggles"}
	private boolean selected;
	private boolean visible;
	private ImageIcon picture;
	protected JFrame frameToDraw;
	protected JLabel labelToDraw;
	
	public SetCard(String number,String color,String fill,String shape,JFrame gameFrame,JLabel gameLabel)
	{
		this.number = number;
		this.color = color;
		this.fill = fill;
		this.shape = shape;
		this.selected = false;
		this.visible = false;
		this.frameToDraw = gameFrame;
		this.labelToDraw = gameLabel;
		setCardPic();
	}
	
	public String picFileName()
	{
		String fileName = "/cardPics/"+this.shape+"/"+this.color+"/"+this.fill+"/"+this.number;
		return(fileName);
	}
	
	public void setCardPic()
	{
		this.picture = new ImageIcon(picFileName());
	}
	
	public void displayCard(JFrame gameFrame,int xCoord,int yCoord)
	{
		// display picture of card to gameFrame using gameLabel
		this.visible = true;
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
