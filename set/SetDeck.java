package set;

public class SetDeck {

	private int cardsUsed;
	private Deck[] deck;

	
	public SetDeck() {
		deck = new SetDeck[81];
		int cardCount = 0; //Cards made thus far?

	}

	public static void main(String[] args) {
		String[] SHAPE = {
				"Diamonds", "Squiggles", "Ovals"
		};
		String[] FILL = {
				"Open", "Gradient", "Solid"
		};
		String [] NUMBER = {
				"Three", "Two","One"
		};
		String [] COLOR = {
				"Red", "Purple", "Green"
		};
	}

	
public void shuffle() {
	for(int i = 80; i > 0; i-- ) {
		int rand = (int)(Math.random()*(i+1));
		SetDeck temp = deck[i];
		deck[i] = deck[rand];
		deck[rand] = temp;
	}
	cardsUsed = 0;
}

public int cardsLeft() {
	return 81 - cardsUsed;

}

public SetDeck deal() {
	if (cardsUsed == 81)
		shuffle();
		cardsUsed++;
	return deck[cardsUsed - 1];
	
}
}
