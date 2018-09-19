import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

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
		assertEquals("H2", game.getFile().get(1));
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
		ByteArrayOutputStream console = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(console));
	     
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest3.txt");
		game.fileMode(test);
		game.begin();
		
		while (!game.isPlayerDone()) {
			game.next("N");
		}
		
		Hand hand = new Hand();
		Card card1 = new Card("SK");
		Card card2 = new Card("H2");
		card1.faceUp();
		card2.faceUp();
		hand.add(card1);
		hand.add(card2);
		
		assertTrue(game.isPlayerDone());
		assertEquals(hand.toString(), console.toString());
		assertTrue(hand.isEqualTo(game.getPlayerHand()));

		//assertEquals(hand.getCards(), game.getPlayerHand().getCards());
	}
	
	public void testPlayerBust() {
		BlackJack game = new BlackJack();
		
		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest4.txt");
		game.fileMode(test);
		game.begin();
		
		while (!game.isPlayerDone()) {
			game.next("N");
		}
		
		Hand hand = new Hand();
		Card card1 = new Card("SK");
		Card card2 = new Card("H5");
		Card card3 = new Card("C8");
		card1.faceUp();
		card2.faceUp();
		card3.faceUp();
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		
		assertTrue(game.isPlayerDone());
		assertTrue(game.getPlayerHand().getValue() > 21);
		assertTrue(hand.isEqualTo(game.getPlayerHand()));
		//assertEquals(hand.getCards(), game.getPlayerHand().getCards());
	}

	public void testDealerHits() {

		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest5.txt");
		game.fileMode(test);
		game.begin();

		while (!game.isPlayerDone()) {
			game.next("N");
		}
		
		Hand hand = new Hand();
		Card card1 = new Card("HQ");
		Card card2 = new Card("C2");
		Card card3 = new Card("D7");
		card1.faceUp();
		card2.faceUp();
		card3.faceUp();
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		
		assertTrue(game.isPlayerDone());
		assertTrue(hand.isEqualTo(game.getDealerHand()));


		game = new BlackJack();

		test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest6.txt");
		game.fileMode(test);
		game.begin();

		while (!game.isPlayerDone()) {
			game.next("N");
		}

		hand = new Hand();
		card1 = new Card("HA");
		card2 = new Card("C2");
		card3 = new Card("D4");
		Card card4 = new Card("C3");
		card1.faceUp();
		card2.faceUp();
		card3.faceUp();
		card4.faceUp();
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		
		assertTrue(game.isPlayerDone());
		assertTrue(hand.isEqualTo(game.getDealerHand()));
		
		
		game = new BlackJack();

		test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest7.txt");
		game.fileMode(test);
		game.begin();

		while (!game.isPlayerDone()) {
			game.next("N");
		}

		hand = new Hand();
		card1 = new Card("HA");
		card2 = new Card("C2");
		card3 = new Card("D4");
		card4 = new Card("C7");
		card1.faceUp();
		card2.faceUp();
		card3.faceUp();
		card4.faceUp();
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		
		assertTrue(game.isPlayerDone());
		assertTrue(hand.isEqualTo(game.getDealerHand()));
		assertTrue(game.playerWins());
	}
}

