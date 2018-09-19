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
}
