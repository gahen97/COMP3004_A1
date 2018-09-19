import junit.framework.TestCase;

public class BlackJackTest extends TestCase{

	public void testConsoleMode() {
		BlackJack game = new BlackJack();
		game.consoleMode();
		assertNotNull(game.getDeck());
		
	}
	
	public void testFileMode() {
		BlackJack game = new BlackJack();
		game.fileMode();
		assertNull(game.getDeck());
	}
	
}
