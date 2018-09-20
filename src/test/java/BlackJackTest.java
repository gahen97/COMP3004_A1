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

	public void testDealerHitSoftSeventeen() {
		
		// Dealer hits if it is a soft 17
		// Dealer also hits multiple times
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest6.txt");
		game.fileMode(test);
		game.begin();

		while (!game.isPlayerDone()) {
			game.next("N");
		}

		Hand hand = new Hand();
		Card card1 = new Card("HA");
		Card card2 = new Card("C2");
		Card card3 = new Card("D4");
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
		
	}
	
	public void testDealerHitLessThatSixteen() {
		
		// Dealer hits if dealer hand is less than 16
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest5.txt");
		game.fileMode(test);
		game.begin();

		while (!game.isPlayerDone()) {
			game.next("N");
		}
		
		Hand hand = new Hand();
		Card card1 = new Card("H2");
		Card card2 = new Card("C9");
		Card card3 = new Card("D7");
		card1.faceUp();
		card2.faceUp();
		card3.faceUp();
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		
		assertTrue(game.isPlayerDone());
		assertTrue(hand.isEqualTo(game.getDealerHand()));
		
	}

	public void testDealerHitsDealerBust() {
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest7.txt");
		game.fileMode(test);
		game.begin();

		while (!game.isPlayerDone()) {
			game.next("N");
		}

		Hand hand = new Hand();
		Card card1 = new Card("HA");
		Card card2 = new Card("C2");
		Card card3 = new Card("D4");
		Card card4 = new Card("C7");
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
		assertTrue(game.didPlayerWin());
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
		assertTrue(console.toString().contains(hand.toString()));
		assertTrue(hand.isEqualTo(game.getPlayerHand()));
	}
	
	public void testDisplayDealer() {
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
		Card card1 = new Card("H9");
		Card card2 = new Card("CA");
		card1.faceUp();
		card2.faceUp();
		hand.add(card1);
		hand.add(card2);
		
		assertTrue(game.isGameOver());
		assertTrue(console.toString().contains(hand.toString()));
		assertTrue(hand.isEqualTo(game.getDealerHand()));
	}
	
	public void testDisplayScoreDealerWins() {
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
		Card card1 = new Card("H9");
		Card card2 = new Card("CA");
		card1.faceUp();
		card2.faceUp();
		hand.add(card1);
		hand.add(card2);
		
		assertTrue(game.isGameOver());
		assertTrue(game.didDealerWin());
		assertTrue(console.toString().contains(hand.toString()));
		assertTrue(console.toString().contains("Dealer wins. Hand: [Nine of Hearts, Ace of Clubs]"));
		assertTrue(console.toString().contains("Score: 20"));
		assertTrue(console.toString().contains("Player loses. Hand: [King of Spades, Two of Hearts]"));
		assertTrue(console.toString().contains("Score: 12"));
	}
	
	public void testDisplayScorePlayerWins() {
		ByteArrayOutputStream console = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(console));
	     
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest12.txt");
		game.fileMode(test);
		game.begin();
		
		while (!game.isPlayerDone()) {
			game.next("N");
		}
		
		Hand hand = new Hand();
		Card card1 = new Card("HQ");
		Card card2 = new Card("SK");
		card1.faceUp();
		card2.faceUp();
		hand.add(card1);
		hand.add(card2);
		
		assertTrue(game.isGameOver());
		assertTrue(game.didPlayerWin());
		assertTrue(console.toString().contains(hand.toString()));
		assertTrue(console.toString().contains("Player wins! Hand: [Queen of Hearts, King of Spades]"));
		assertTrue(console.toString().contains("Score: 20"));
		assertTrue(console.toString().contains("Dealer loses! Hand: [Six of Clubs, Four of Diamonds, Five of Clubs, Two of Diamonds]"));
		assertTrue(console.toString().contains("Score: 12"));
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

	public void testInitialBlackJackPlayer() {
		//Player wins at start
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest8.txt");
		game.fileMode(test);
		game.begin();
		
		Hand player = game.getPlayerHand();
		
		assertEquals(2, player.getSize());
		assertTrue(game.didPlayerWin());
		assertTrue(!game.didDealerWin());
		assertTrue(game.isGameOver());
	}
	
	public void testInitialBlackJackDealer() {
		//Dealer wins at start
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest9.txt");
		game.fileMode(test);
		game.begin();
		
		Hand dealer = game.getDealerHand();
		
		assertEquals(2, dealer.getSize());
		assertTrue(game.didDealerWin());
		assertTrue(!game.didPlayerWin());
		assertTrue(game.isGameOver());
	}
	
	public void testInitialBlackJackDealerAndPlayer() {
		//Dealer wins at start
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest10.txt");
		game.fileMode(test);
		game.begin();
		
		Hand dealer = game.getDealerHand();
		
		assertEquals(2, dealer.getSize());
		assertTrue(game.didDealerWin());
		assertTrue(!game.didPlayerWin());
		assertTrue(game.isGameOver());
	}
	
	public void testDealerWins() {
		//Dealer wins because of better score
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest11.txt");
		game.fileMode(test);
		game.begin();
		
		assertTrue(game.didDealerWin());
		assertTrue(!game.didPlayerWin());
		assertTrue(game.isGameOver());
	}
	
	public void testPlayerWins() {
		//Player wins because of better score
		BlackJack game = new BlackJack();

		File test = new File("C:\\Users\\Gahen\\eclipse-workspace\\COMP3004_A1\\src\\test\\resources\\inputTest12.txt");
		game.fileMode(test);
		game.begin();
	
		
		assertTrue(!game.didDealerWin());
		assertTrue(game.didPlayerWin());
		assertTrue(game.isGameOver());
	}
}

