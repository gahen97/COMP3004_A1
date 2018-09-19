import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	ArrayList<Card> deck;
	
	public Deck() {
		
		deck = new ArrayList<Card>(52);
		
		String suit = "";
		String card;
		
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				suit = "S";
				break;
			case 1:
				suit = "H";
				break;
			case 2:
				suit = "D";
				break;
			case 3:
				suit = "C";
				break;
			}
			for (int j = 2; j < 15; j++) {
				if (j == 11)
					card = suit + "J";
				else if (j == 12)
					card = suit + "Q";
				else if (j == 13)
					card = suit + "K";
				else if (j == 14)
					card = suit + "A";
				else
					card = suit + j;
				
				deck.add(new Card(card));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public int getSize() {
		return deck.size();
	}
	
	public Card getTopCard() {
		return deck.remove(getSize()-1);
	}
}
