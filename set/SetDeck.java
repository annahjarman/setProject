package set;

import javax.swing.JFrame;

public class SetDeck {

	
	private int cardCount = 0;
	private final int DECK_SIZE = 81;
	private SetCard[] deck = new SetCard[DECK_SIZE];
	final String SHAPE[] = {"Diamonds"};
	final String FILL[] = {"Gradient","Open","Solid"};
	final String NUMBER[] = {"1","2","3"};
	final String COLOR[] = {"Purple"};

	
	//declare all strings
	// x = 0;
	//++x;
	
	public SetDeck(JFrame gameFrame) {
			int x = 0;
			for(int i = 0; i < 1; i++) {
				String shape = SHAPE[i];
				for(int j = 0; j < 3; j++) {
					String fill = FILL[j];
					for(int k = 0; k < 3; k++) {
						String number = NUMBER[k];
						for(int m = 0; m < 1; m++) {
							String color = COLOR[m];
							deck[x] = new SetCard(number, color, fill, shape, gameFrame);
							x++;
							cardCount++;
						}
					}
				}
			}
		//int cardCount = 0; //Cards made thus far?
		
	}

	
	public void shuffle() {
		for(int i = cardCount - 1; i >= 0; i-- ) {
			int rand = (int)(Math.random()*(i+1));
			SetCard temp = deck[rand];
			deck[rand] = deck[i];
			deck[i] = temp;
			
		}
		
	}
	
	public int cardsLeft() {
		return cardCount;
	
	}
	
	public SetCard deal() {
		SetCard myCard = deck[cardCount-1];
		cardCount--;
		return myCard;
		
	}
}
