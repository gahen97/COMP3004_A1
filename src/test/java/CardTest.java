import junit.framework.TestCase;

public class CardTest extends TestCase{
	
	public void testGetSuit() {
		Card card0 = new Card("S1");
		assertEquals("Spades", card0.getSuit());
		
		Card card1 = new Card("H1");
		assertEquals("Hearts", card1.getSuit());
		
		Card card2 = new Card("D1");
		assertEquals("Diamonds", card2.getSuit());
		
		Card card3 = new Card("C1");
		assertEquals("Clubs", card3.getSuit());
	}
	
	public void testGetRank() {
		Card card0 = new Card("S1");
		assertEquals("One", card0.getRank());
		
		Card card1 = new Card("S2");
		assertEquals("Two", card1.getRank());
		
		Card card2 = new Card("S3");
		assertEquals("Three", card2.getRank());
		
		Card card3 = new Card("S4");
		assertEquals("Four", card3.getRank());
		
		Card card4 = new Card("S5");
		assertEquals("Five", card4.getRank());
		
		Card card5 = new Card("S6");
		assertEquals("Six", card5.getRank());
		
		Card card6 = new Card("S7");
		assertEquals("Seven", card6.getRank());
		
		Card card7 = new Card("S8");
		assertEquals("Eight", card7.getRank());
		
		Card card8 = new Card("S9");
		assertEquals("Nine", card8.getRank());
		
		Card card9 = new Card("SJ");
		assertEquals("Jack", card9.getRank());
		
		Card card10 = new Card("SQ");
		assertEquals("Queen", card10.getRank());
		
		Card card11 = new Card("SK");
		assertEquals("King", card11.getRank());
		
		Card card12 = new Card("SA");
		assertEquals("Ace", card12.getRank());
	}
	
	public void testToString() {
		Card card0 = new Card("SK");
		assertEquals("King of Spades", card0.toString());
		
		Card card1 = new Card("HA");
		assertEquals("Ace of Hearts", card1.toString());
		
		Card card2 = new Card("DQ");
		assertEquals("Queen of Diamonds", card2.toString());
		
		Card card3 = new Card("C1");
		assertEquals("One of Clubs", card3.toString());
	}
	
	public void testFaceUp() {
		Card card0 = new Card("SK");
		assertEquals(false, card0.isVisible());
		
		Card card1 = new Card("HA");
		assertEquals(false, card1.toString());
		card1.faceUp();
		assertEquals(true, card1.toString());
	}

}
