// Student Name: Thanabalasingam Gahen
// Student ID: 101021537

public class Card {

	char suit;
	char rank;
	boolean visible;

	// constructor
	public Card() {
		suit = 0;
		rank = 0;
		visible = false;
	}

	// constructor with String parameter
	public Card(String card) {
		suit = card.charAt(0);
		// converts a 10 into T, so it's easier to store;
		if (card.length() == 3) {
			if (card.charAt(1) == '1' && card.charAt(2) == '0')
				rank = 'T'; // 'T' acts as a substitute for 10, this allows the rank variable to be a char
		} else
			rank = card.charAt(1);
		visible = false;
	}

	// returns visibility
	public boolean isVisible() {
		return visible;
	}

	// makes card visible
	public void faceUp() {
		visible = true;
	}

	// returns a suit as a string
	public String getSuit() {

		String string;

		switch (suit) {
		case 'S':
			string = "Spades";
			break;
		case 'H':
			string = "Hearts";
			break;
		case 'D':
			string = "Diamonds";
			break;
		case 'C':
			string = "Clubs";
			break;
		default:
			string = "Invalid Suit";
			break;
		}

		return string;
	}
	
	// returns the Rank as a string
	public String getRank() {

		String string;
		
		switch (rank) {

		case '2':
			string = "Two";
			break;
		case '3':
			string = "Three";
			break;
		case '4':
			string = "Four";
			break;
		case '5':
			string = "Five";
			break;
		case '6':
			string = "Six";
			break;
		case '7':
			string = "Seven";
			break;
		case '8':
			string = "Eight";
			break;
		case '9':
			string = "Nine";
			break;
		case 'T':
			string = "Ten";
			break;
		case 'J':
			string = "Jack";
			break;
		case 'Q':
			string = "Queen";
			break;
		case 'K':
			string = "King";
			break;
		case 'A':
			string = "Ace";
			break;
		default:
			string = "Invalid Rank";
			break;
		}
		
		return string;
	}
	
	// converts to string
	public String toString() {
		return (this.getRank() + " of " + this.getSuit());
	}

}
