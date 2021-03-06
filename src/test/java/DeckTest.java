// Student Name: Thanabalasingam Gahen
// Student ID: 101021537

import junit.framework.TestCase;

public class DeckTest extends TestCase {
	
	//tests Shuffle
	public void testShuffle() {
		Deck deck0 = new Deck();
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		deck2.shuffle();
		
		assertEquals(false, deck2.isEqualTo(deck0));
		assertEquals(true, deck1.isEqualTo(deck0));
	}

	//tests getSize()
	public void testGetSize() {
		Deck deck0 = new Deck();
		assertEquals(52, deck0.getSize());
		
		//shuffled deck
		Deck deck1 = new Deck();
		deck1.shuffle();
		assertEquals(52, deck1.getSize());
		
		//getTopCard
		Deck deck2 = new Deck();
		deck2.getTopCard();
		deck2.getTopCard();
		deck2.getTopCard();
		assertEquals(49, deck2.getSize());
	}

}
