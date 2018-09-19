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
		Hand hand0 = new Hand([card0, card1, card2]);

		assertEquals(19, hand0.getValue());

	}

}
