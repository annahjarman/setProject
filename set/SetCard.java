package set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SetCard {
	
	private String number; // can be {"One","Two","Three"}
	private String color; // can be {"Red","Purple","Green"}
	private String fill; // can be {"Open","Solid","Gradient"}
	private String shape; // can be {"Diamonds","Ovals","Squiggles"}
	private boolean selected;
	private ImageIcon picture;
	private int width;
	private int height;
	private int xPosition;
	private int yPosition;
	protected JFrame frameToDraw;
	protected JLabel labelToDraw;
	final private Border normalBorder = BorderFactory.createEmptyBorder(1,1,1,1);
	final private Border highlightBorder = BorderFactory.createLineBorder(Color.RED);
	
	public SetCard(String number,String color,String fill,String shape,int width,int height,JFrame gameFrame)
	{
		this.number = number;
		this.color = color;
		this.fill = fill;
		this.shape = shape;
		this.selected = false;
		this.frameToDraw = gameFrame;
		this.labelToDraw = new JLabel();
		this.width = width;
		this.height = height;
	}
	
	public String picFileName()
	{
		String fileName = "src/cardPics/"+this.shape+this.color+this.fill+"_"+this.number+".jpg";
		return(fileName);
	}
	
	public void setCardPic()
	{
		this.picture = new ImageIcon(picFileName());
		Image tempPic = picture.getImage();
		Image newPic = tempPic.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		picture = new ImageIcon(newPic);
	}
	
	public void displayCard(int xCoord,int yCoord)
	{
		// display picture of card to gameFrame using gameLabel
		this.xPosition = xCoord;
		this.yPosition = yCoord;
		setCardPic();
		labelToDraw.setIcon(this.picture);
		labelToDraw.setBounds(xPosition,yPosition,width,height);
		labelToDraw.setBorder(normalBorder);
		frameToDraw.getContentPane().add(labelToDraw);
		labelToDraw.setVisible(true);
	}
	
	public void redrawCard()
	{
		//this.visible = false;
		frameToDraw.getContentPane().add(labelToDraw);
		labelToDraw.setVisible(true);
		//frameToDraw.setVisible(false);
		//frameToDraw.setVisible(true);
		//this.visible = true;
	}
	
	public void undrawCard()
	{
		labelToDraw.setVisible(false);
		this.selected = false;
	}
	
	public void selectCard()
	{
		this.selected = true;
		highlight();
		//redrawCard();
	}
	
	public void deselectCard()
	{
		this.selected = false;
		removeHighlight();
		//redrawCard();
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
	
	public boolean equals(SetCard toCompare)
	{
		if(this.color == toCompare.color)
			if(this.fill == toCompare.fill)
				if(this.shape == toCompare.shape)
					if(this.number == toCompare.number)
						return true;
		return false;
	}
	
	public boolean isCardPushed(int xMouse,int yMouse)
	{
		if((xPosition <= xMouse) && (xMouse <= xPosition+width) && (yPosition <= yMouse) && (yMouse <= yPosition+height))
			return true;
		else
			return false;
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	public String getFill()
	{
		return this.fill;
	}
	
	public String getShape()
	{
		return this.shape;
	}
	
	public String getNumber()
	{
		return this.number;
	}
	
	public JFrame getFrame()
	{
		return this.frameToDraw;
	}

}
