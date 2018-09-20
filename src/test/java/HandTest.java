import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

public class HandTest extends TestCase{

	public void testAdd() {
		
		Hand hand0 = new Hand();
		
		Card card0 = new Card("SK");
		hand0.add(card0);
		assertEquals(card0, hand0.getCard(0));
		
		Card card1 = new Card("H3");
		hand0.add(card1);
		assertEquals(card1, hand0.getCard(1));
	
		Card card2 = new Card("D6");
		hand0.add(card2);
		assertEquals(card2, hand0.getCard(2));
		
	}

	public void testGetValue() {

		Card card0 = new Card("SK");
		Card card1 = new Card("H3");
		Card card2 = new Card("D6");
		ArrayList<Card> cards = new ArrayList<Card>(Arrays.asList(card0, card1, card2));
		Hand hand0 = new Hand(cards);
		assertEquals(19, hand0.getValue());
		
		card0 = new Card("H4");
		card1 = new Card("D2");
		card2 = new Card("CA");
		cards = new ArrayList<Card>(Arrays.asList(card0, card1, card2));
		hand0 = new Hand(cards);
		assertEquals(17, hand0.getValue());
		
		//Ace counts as 1
		card0 = new Card("H6");
		card1 = new Card("D8");
		card2 = new Card("CA");
		cards = new ArrayList<Card>(Arrays.asList(card0, card1, card2));
		hand0 = new Hand(cards);
		assertEquals(15, hand0.getValue());

		// Ace counts as 11 
		card0 = new Card("H3");
		card1 = new Card("D4");
		card2 = new Card("CA");
		cards = new ArrayList<Card>(Arrays.asList(card0, card1, card2));
		hand0 = new Hand(cards);
		assertEquals(18, hand0.getValue());

		// Ace counts as 11 (blackjack)
		card0 = new Card("H6");
		card1 = new Card("D4");
		card2 = new Card("CA");
		cards = new ArrayList<Card>(Arrays.asList(card0, card1, card2));
		hand0 = new Hand(cards);
		assertEquals(21, hand0.getValue());

		//Ace counts as 1 and 11 in the same hand
		card0 = new Card("HA");
		card1 = new Card("D8");
		card2 = new Card("CA");
		cards = new ArrayList<Card>(Arrays.asList(card0, card1, card2));
		hand0 = new Hand(cards);
		assertEquals(20, hand0.getValue());
		
		// Ace counts as 1 and 1 in the same hand
		card0 = new Card("HA");
		card1 = new Card("D10");
		card2 = new Card("S2");
		Card card3 = new Card("CA");
		cards = new ArrayList<Card>(Arrays.asList(card0, card1, card2, card3));
		hand0 = new Hand(cards);
		assertEquals(14, hand0.getValue());

		// K counts as 10
		card0 = new Card("HK");
		hand0 = new Hand();
		hand0.add(card0);
		assertEquals(10, hand0.getValue());
		
		// Q counts as 10
		card0 = new Card("HQ");
		hand0 = new Hand();
		hand0.add(card0);
		assertEquals(10, hand0.getValue());

		// J counts as 10
		card0 = new Card("HJ");
		hand0 = new Hand();
		hand0.add(card0);
		assertEquals(10, hand0.getValue());


	}

}
