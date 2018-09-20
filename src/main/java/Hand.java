// Student Name: Thanabalasingam Gahen
// Student ID: 101021537

import java.util.ArrayList;

public class Hand {
	
	ArrayList<Card> cards;
	int value;
	int aceCount = 0; // keeps track of aces in a hand
	
	// constructor
	public Hand() {
		cards = new ArrayList<Card>();
		value = 0;
	}
	
	// constructor with an ArrayList input
	public Hand(ArrayList<Card> input) {
		cards = input;
		value = getValue();
	}
	
	// returns size
	public int getSize() {
		return cards.size();
	}
	
	// makes all the cards in the hand visible
	public void makeVisible() {
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).faceUp();
		}
	}
	
	// returns true of a hand contains the card
	public boolean containsCard(Card card) {
		return cards.contains(card);
	}
	
	// converts the hand into a string
	public String toString() {
		String str = "[";
		for (int i = 0; i < cards.size(); i++) {
			if (i != (cards.size() - 1))
				str = str + cards.get(i).toString() + ", ";
			else
				str = str + cards.get(i).toString();
		}
		str = str + "]";
		return str;
	}
	
	// checks of the hand has a certain rank
	public boolean containsRank(String str) {		
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getRank().equals(str))
				return true;
		}
		return false;
	}

	// returns a card at a certain index
	public Card getCard(int index) {
		return cards.get(index);
	}
	
	// returns the cards
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	// compares two hands, returns true if they're the same
	public boolean isEqualTo(Hand hand) {
		ArrayList<Card> hand0 = this.getCards();
		ArrayList<Card> hand1 = hand.getCards();
		
		if (hand0.size() != hand1.size()) {
			return false;
		}
		else {
			for (int i = 0; i < hand0.size(); i++) {
				if (hand0.get(i).getRank() != hand1.get(i).getRank()) {
					return false;
				}
				else if (hand0.get(i).getSuit() != hand1.get(i).getSuit()) {
					return false;
				}
				else if (hand0.get(i).isVisible() != hand1.get(i).isVisible()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	// adds a card to the hand
	public void add(Card card) {
		cards.add(card);
		value = getValue();
	}
	
	// gets the value of the card
	public int getValue() {
		aceCount = 0;
		int total = 0;
		for (int i = 0; i < cards.size(); i ++) {
			total += calculateRankValue(getCard(i), total);
		}
		total = getBestValue(total);
		return total;
	}
	
	// calculates the best value of a hand, by adjusting for different values of Ace
	private int getBestValue(int total) {
		if ((total > 21) && (aceCount > 0)) {
			total = total - 10;
			aceCount--;
			return getBestValue(total);
		}
		else
			return total;
	}

	// calculates the rank value
	private int calculateRankValue(Card card, int total) {
		String rank = card.getRank();
		int val = 0;
		if (rank == "Two") {
			val += 2;
		} else if (rank == "Three") {
			val += 3;
		} else if (rank == "Four") {
			val += 4;
		} else if (rank == "Five") {
			val += 5;
		} else if (rank == "Six") {
			val += 6;
		} else if (rank == "Seven") {
			val += 7;
		} else if (rank == "Eight") {
			val += 8;
		} else if (rank == "Nine") {
			val += 9;
		} else if ((rank == "Ten") || (rank == "Jack") || (rank == "Queen") || (rank == "King")) {
			val += 10;
		} else if (rank == "Ace") {
			val += 11;
			aceCount++;
		}
		return val;
	}

}
