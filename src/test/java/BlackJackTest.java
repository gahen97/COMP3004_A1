import java.io.File;

import junit.framework.TestCase;

public class BlackJackTest extends TestCase{

	public void testConsoleMode() {
		BlackJack game = new BlackJack();
		game.consoleMode();
		
		assertNull(game.getFile());
		assertNotNull(game.getDeck());
		
	}
	
	public void testFileMode() {
		BlackJack game = new BlackJack();
		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest0.txt");
		game.fileMode(test);

		assertNull(game.getDeck());
		assertNotNull(game.getFile());
		assertEquals("SK", game.getFile().get(0));
		assertEquals("HA", game.getFile().get(1));
		assertEquals("HQ", game.getFile().get(2));
		assertEquals("CA", game.getFile().get(3));
	}
	
	public void testInitialVisibilityPlayer() {
		
		BlackJack game = new BlackJack();
		
		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest0.txt");
		game.fileMode(test);
		game.begin();
		
		Hand player = game.getPlayerHand();
		
		assertEquals(2, player.getSize());
		assertEquals(true, player.getCard(0).isVisible());
		assertEquals(true, player.getCard(1).isVisible());
		
	}
		
	public void testInitialVisibilityDealer() {
		
		BlackJack game = new BlackJack();
		
		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest0.txt");
		game.fileMode(test);
		game.begin();
		
		Hand dealer = game.getDealerHand();
		
		assertEquals(2, dealer.getSize());
		assertEquals(false, dealer.getCard(0).isVisible());
		assertEquals(true, dealer.getCard(1).isVisible());
		
	}

	public void testSingleHit() {
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest1.txt");
		game.fileMode(test);
		game.begin();
		
		Hand player = game.getPlayerHand();
		
		assertEquals(2, player.getSize());
		Card card1 = player.getCard(0);
		Card card2 = player.getCard(1);
		
		game.next("N");
		assertEquals(3, player.getSize());
		assertEquals(card1, player.getCard(0));
		assertEquals(card2, player.getCard(1));
		
	}
	
	public void testRepeatedHits() {
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest2.txt");
		game.fileMode(test);
		game.begin();
		
		Hand player = game.getPlayerHand();
		
		assertEquals(2, player.getSize());
		Card card1 = player.getCard(0);
		Card card2 = player.getCard(1);
		
		game.next("N");
		assertEquals(3, player.getSize());
		assertEquals(card1, player.getCard(0));
		assertEquals(card2, player.getCard(1));
		Card card3 = player.getCard(2);
		
		game.next("N");
		assertEquals(4, player.getSize());
		assertEquals(card1, player.getCard(0));
		assertEquals(card2, player.getCard(1));
		assertEquals(card3, player.getCard(2));
		Card card4 = player.getCard(3);
				
		game.next("N");
		assertEquals(5, player.getSize());
		assertEquals(card1, player.getCard(0));
		assertEquals(card2, player.getCard(1));
		assertEquals(card3, player.getCard(2));
		assertEquals(card4, player.getCard(3));
		
	}
	
	public void testStand() {
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest3.txt");
		game.fileMode(test);
		game.begin();
		
		Hand player = game.getPlayerHand();
		
		assertEquals(2, player.getSize());
		Card card1 = player.getCard(0);
		Card card2 = player.getCard(1);
		int value = player.getValue();
		
		game.next("N");
		assertEquals(2, player.getSize());
		assertEquals(card1, player.getCard(0));
		assertEquals(card2, player.getCard(1));
		assertEquals(value, player.getValue());

	}

	public void testDisplayPlayer() {
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest3.txt");
		game.fileMode(test);
		game.begin();
		
		Hand hand = new Hand();
		hand.add(new Card("SK"));
		hand.add(new Card("HA"));
		
		assertEquals(hand, game.playerEnd());
	}
	
	public void testPlayerBust() {
		BlackJack game = new BlackJack();
		
		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest4.txt");
		game.fileMode(test);
		game.begin();
		
		game.next("N");
		
		assertEquals(true, game.playerBust());
	}
}

