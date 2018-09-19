import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class BlackJack {

	Hand dealer;
	Hand player;
	
	Deck deck;
	LinkedList<String> file;
	
	boolean consoleMode;
	boolean playerEnd;
	
	public BlackJack() {
		dealer = new Hand();
		player = new Hand();
		playerEnd = false;
	}
	
	public void consoleMode() {
		deck = new Deck();
		deck.shuffle();
		consoleMode = true;
	}
	
	public void fileMode(File input) {
	
		Scanner in;
		String line = "";
		try {
			in = new Scanner(input);
			line = in.nextLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		file = new LinkedList<String> (Arrays.asList(line.split("\\s+")));
		consoleMode = false;
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public LinkedList<String> getFile(){
		return file;
	}
	
	public Hand getPlayerHand(){
		return player;
	}
	
	public Hand getDealerHand(){
		return dealer;
	}
	
	public void begin() {
		if (deck != null) {
			Card card1 = deck.getTopCard();
			card1.faceUp();
			player.add(card1);
			
			Card card2 = deck.getTopCard();
			card2.faceUp();
			player.add(card2);
			
			Card card3 = deck.getTopCard();
			dealer.add(card3);
			
			Card card4 = deck.getTopCard();
			card4.faceUp();
			dealer.add(card4);
		}
		else if (file != null) {
			Card card1 = new Card(file.removeFirst());
			card1.faceUp();
			player.add(card1);
			
			Card card2 = new Card(file.removeFirst());
			card2.faceUp();
			player.add(card2);
			
			Card card3 = new Card(file.removeFirst());
			dealer.add(card3);
			
			Card card4 = new Card(file.removeFirst());
			card4.faceUp();
			dealer.add(card4);
		}
		
		System.out.println("The first round of cards have been dealt");
	}
	
	public void next(String str) {
		
		if (str.equals("H")) {
			Card card;
			if (consoleMode)
				card = deck.getTopCard();
			else 
				card = new Card (file.removeFirst());
			addToHand(player, card);
		}
		
		else if (str.equals("S")) {
			playerEnd(0);
		}
		
		else if (str.equals("N")) {
			
			String next = file.removeFirst();
			
			if (next.equals("H") || next.equals("S")) {
				next(next);
			}
		}
	}
	
	private void addToHand(Hand player2, Card card) {
		player2.add(card);
		if (player2.getValue() == 21) {
			playerEnd(1);
		}
		else if (player2.getValue() > 21) {
			playerEnd(2);
		}
		// TODO Auto-generated method stub
		
	}

	private void playerEnd(int i) {
		if (i == 0) {
			System.out.println("The player stands with value " + player.getValue());
			System.out.println("The player's hand is " + player.getCards());
			nextDealer();
		}
		
		else if (i == 1) {
			System.out.println("The player has BlackJack with value " + player.getValue());
			System.out.println("The player's hand is " + player.getCards());
			nextDealer();
		}
		
		else if (i == 2) {
			System.out.println("The player busts with value " + player.getValue());
			System.out.println("The player's hand is " + player.getCards());
			dealerWin();
		}
		
		playerEnd = true;
	}
	
	public boolean isPlayerDone() {
		return playerEnd;
	}
	
	public void nextDealer() {
		System.out.println("Dealers turn");
		
	}
	
	public void dealerWin() {
		System.out.println("Dealer wins");
	}

	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		Scanner user = new Scanner(System.in);
		
		System.out.println("Would you like to continue in console (c) mode or file (f) mode?");
		while (true) {
			if (user.nextLine() == "f") {
				game.fileMode(game.promptForFile(user));
				break;
			}
			else if (user.nextLine() == "c") {
				game.consoleMode();
				break;
			}
			else {
				System.out.println("Invalid input, please type in c or f");
			}
		}
		
		game.begin();
		
		if(game.consoleMode) {
			while (!game.isPlayerDone()) {
				System.out.println("Would you like to Hit (H) or Stand (S)");
				if (user.nextLine() == "H" || user.nextLine() == "S") {
					game.next(user.nextLine());
				}
			}
		} else {
			while (!game.isPlayerDone()) {
				game.next("N");
			}
		}
		
		user.close();
	}
	
	private File promptForFile(Scanner user) {
		System.out.println("Please enter in the path to the file");
		File file;
		while (true) {
			file = new File(user.nextLine());
			if (file.exists())
				return file;
			else {
				System.out.println("Invalid file, please enter in your file path again");
			}
		}
	}
}
