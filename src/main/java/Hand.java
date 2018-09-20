import java.util.ArrayList;

public class Hand {
	
	ArrayList<Card> cards;
	int value;
	
	public Hand() {
		cards = new ArrayList<Card>();
		value = 0;
	}
	
	public Hand(ArrayList<Card> input) {
		cards = input;
		value = getValue();
	}
	
	public int getSize() {
		return cards.size();
	}
	
	public boolean containsCard(Card card) {
		return cards.contains(card);
	}
	
	public boolean containsRank(String str) {		
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getRank().equals(str))
				return true;
		}
		return false;
	}

	public Card getCard(int index) {
		return cards.get(index);
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
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
	
	
	public void add(Card card) {
		cards.add(card);
		value = getValue();
	}
	
	public int getValue() {
		String rank;
		int total = 0;
		for (int i = 0; i < cards.size(); i ++) {
			rank = getCard(i).getRank();
			total += calculateRankValue(rank, total);
		}
		return total;
	}
	
	private int calculateRankValue(String rank, int total) {
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
			if (total <= 10)
				val += 11;
			else 
				val += 1;
		}
		return val;
	}

}
