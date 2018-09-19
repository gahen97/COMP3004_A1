import junit.framework.TestCase;
import static org.junit.Assert.*;

public class DeckTest extends TestCase {
	
	public void testShuffle() {
		Deck deck0 = new Deck();
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		deck2.shuffle();
		
		assertEquals(false, deck2.isEqualTo(deck0));
		assertEquals(true, deck1.isEqualTo(deck0));
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
		assertEquals(49, deck2.getSize());
	}

}
