import junit.framework.TestCase;

public class DeckTest extends TestCase {
	
	public void testShuffle() {
		Deck deck0 = new Deck();
		Deck deck1 = new Deck();
		deck1.shuffle();
		assertNotEquals(deck1, deck0);
	}

	public void testGetSize() {
		Deck deck0 = new Deck();
		assertEquals(52, deck0.getSize());
		
		Deck deck1 = new Deck();
		deck1.shuffle();
		assertEquals(52, deck1.getSize());
		
		Deck deck2 = new Deck();
		deck2.getTopCard();
		deck2.getTopCard();
		deck2.getTopCard();
		assertEquals(49, deck1.getSize());
	}

}
