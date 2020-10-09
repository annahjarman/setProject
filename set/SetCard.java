package set;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;

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
	final private Border normalBorder = BorderFactory.createEmptyBorder(1,1,1,1);
	final private Border highlightBorder = BorderFactory.createLineBorder(Color.blue);
	
	public SetCard(String number,String color,String fill,String shape,JFrame gameFrame)
	{
		this.number = number;
		this.color = color;
		this.fill = fill;
		this.shape = shape;
		this.selected = false;
		this.visible = false;
		this.frameToDraw = gameFrame;
		this.labelToDraw = new JLabel();
		setCardPic();
	}
	
	public String picFileName()
	{
		String fileName = "src/cardPics/"+this.number+this.shape+this.color+this.fill+".jpg";
		return(fileName);
	}
	
	public void setCardPic()
	{
		this.picture = new ImageIcon(picFileName());
	}
	
	public void displayCard(JFrame gameFrame,int xCoord,int yCoord,int width,int height)
	{
		// display picture of card to gameFrame using gameLabel
		labelToDraw.setIcon(this.picture);
		labelToDraw.setText("Hello world");
		labelToDraw.setBounds(xCoord,yCoord,width,height);
		labelToDraw.setBorder(normalBorder);
		gameFrame.getContentPane().add(labelToDraw);
		labelToDraw.setVisible(true);
		this.visible = true;
		gameFrame.setVisible(false);
		gameFrame.setVisible(true);
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
		if(this.visible)
			highlight();
	}
	
	public void deselectCard()
	{
		this.selected = false;
		if(this.visible)
			removeHighlight();
	}
	
	public void highlight()
	{
		labelToDraw.setBorder(highlightBorder);
	}
	
	public void removeHighlight()
	{
		labelToDraw.setBorder(normalBorder);
	}
	
	public boolean isSelected()
	{
		return this.selected;
	}

}
