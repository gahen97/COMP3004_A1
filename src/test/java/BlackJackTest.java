import junit.framework.TestCase;

public class BlackJackTest extends TestCase{

	public void testConsoleMode() {
		BlackJack game = new BlackJack();
		game.ConsoleMode();
		assertNotNull(game.getDeck());
		
	}
	
	public void testFileMode() {
		BlackJack game = new BlackJack();
		game.ConsoleMode();
		assertNull(game.getDeck());
	}
	
}
