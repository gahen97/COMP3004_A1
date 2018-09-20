// Student Name: Thanabalasingam Gahen
// Student ID: 101021537

import junit.framework.TestCase;

public class CardTest extends TestCase{
	
	// test if getSuit works
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
	
	// test if getRank works
	public void testGetRank() {		
		Card card1 = new Card("S2");
		assertEquals("Two", card1.getRank());
		
		Card card2 = new Card("H3");
		assertEquals("Three", card2.getRank());
		
		Card card3 = new Card("C4");
		assertEquals("Four", card3.getRank());
		
		Card card4 = new Card("D5");
		assertEquals("Five", card4.getRank());
		
		Card card5 = new Card("S6");
		assertEquals("Six", card5.getRank());
		
		Card card6 = new Card("H7");
		assertEquals("Seven", card6.getRank());
		
		Card card7 = new Card("C8");
		assertEquals("Eight", card7.getRank());
		
		Card card8 = new Card("D9");
		assertEquals("Nine", card8.getRank());
		
		Card card9 = new Card("S10");
		assertEquals("Ten", card9.getRank());
		
		Card card10 = new Card("HJ");
		assertEquals("Jack", card10.getRank());
		
		Card card11 = new Card("CQ");
		assertEquals("Queen", card11.getRank());
		
		Card card12 = new Card("DK");
		assertEquals("King", card12.getRank());
		
		Card card13 = new Card("SA");
		assertEquals("Ace", card13.getRank());
	}
	
	//test if toString works
	public void testToString() {
		Card card0 = new Card("SK");
		assertEquals("King of Spades", card0.toString());
		
		Card card1 = new Card("HA");
		assertEquals("Ace of Hearts", card1.toString());
		
		Card card2 = new Card("DQ");
		assertEquals("Queen of Diamonds", card2.toString());
		
		Card card3 = new Card("C2");
		assertEquals("Two of Clubs", card3.toString());
	}
	
	//tests if faceUp() works
	public void testFaceUp() {
		Card card0 = new Card("SK");
		assertEquals(false, card0.isVisible());
		
		Card card1 = new Card("HA");
		assertEquals(false, card1.isVisible());
		card1.faceUp();
		assertEquals(true, card1.isVisible());
	}

}
