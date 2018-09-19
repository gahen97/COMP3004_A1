import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {

	Hand dealer;
	Hand player;
	
	Deck deck;
	ArrayList<String> file;
	
	public BlackJack() {
		dealer = new Hand();
		player = new Hand();
	}
	
	public void consoleMode() {
		deck = new Deck();
		deck.shuffle();
	}
	
	public void fileMode() {
		file = new ArrayList<String>();
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		Scanner user = new Scanner(System.in);
		
		System.out.println("Would you like to continue in console (c) mode or file (f) mode?");
		if (user.nextLine() == "f")
			game.fileMode();
		else
			game.consoleMode();
	}
}
