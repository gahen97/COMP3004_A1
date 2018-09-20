// Student Name: Thanabalasingam Gahen
// Student ID: 101021537

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class BlackJack {

	Hand dealer;
	Hand player;
	
	Deck deck; 					// deck, only used in console mode
	LinkedList<String> file; 	//a list, thats only used in file input mode
	
	boolean consoleMode;	// boolean for console mode/file mode
	boolean playerEnd;		// boolean to check if player is finished with their turn
	boolean dealerWins;		// boolean for dealer winning
	boolean playerWins;		// boolean for player winning
	boolean gameEnded; 		// boolean for game over
	
	//constructor
	public BlackJack() {
		dealer = new Hand();
		player = new Hand();
		playerEnd = false;
		dealerWins = false;
		playerWins = false;
		gameEnded = false;
	}

	// returns deck
	public Deck getDeck() {
		return deck;
	}
	
	// returns file 
	public LinkedList<String> getFile(){
		return file;
	}
	
	// returns player hand 
	public Hand getPlayerHand(){
		return player;
	}
	
	// returns dealer hand
	public Hand getDealerHand(){
		return dealer;
	}
	
	// returns playerEnd boolean
	public boolean isPlayerDone() {
		return playerEnd;
	}

	// returns playerWins boolean
	public boolean didPlayerWin() {
		return playerWins;
	}
	
	// returns dealerWins boolean
	public boolean didDealerWin() {
		return dealerWins;
	}
	
	// Initialises deck and shuffles it
	public void consoleMode() {
		deck = new Deck();
		deck.shuffle();
		consoleMode = true;
	}
	
	// Initialises list
	public void fileMode(File input) {
		
		// First checks if file input is valid
		if (validFile(input)) {
	
			Scanner in;
			String line = "";

			try {
				in = new Scanner(input);
				line = in.nextLine();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			String[] str = line.split(" ");
			file = new LinkedList<String>(Arrays.asList(str));
			consoleMode = false;
		}
		
	}
	
	// checks if file input is valid, returns true if it is
	public boolean validFile(File input) {
		
		Deck temp = new Deck();
		
		Scanner in;
		String line = "";

		try {
			in = new Scanner(input);
			line = in.nextLine();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String[] test = line.split(" ");
		LinkedList<String> str = new LinkedList<String>(Arrays.asList(test));
		
		for (int i = 0; i < str.size(); i++) {
			// Checks if the first 4 entries in the file are cards
			if (i < 4) {
				// checks if the length is 2 or 3 returns false if not
				if ((str.get(i).length() < 2) || (str.get(i).length() > 3)) {
					System.out.println("Incorrect format");
					return false;
					
				}
				
				// checks if the Suit and Rank are proper characters
				else if ((str.get(i).length() == 2) || (str.get(i).length() == 3)) {
					if ((str.get(i).charAt(0) != 'S') && (str.get(i).charAt(0) != 'H') && (str.get(i).charAt(0) != 'C')
							&& (str.get(i).charAt(0) != 'D'))
						return false;
					if ((str.get(i).length() == 2) && ((str.get(i).charAt(1) != 'A') && (str.get(i).charAt(1) != '2')
							&& (str.get(i).charAt(1) != '3') && (str.get(i).charAt(1) != '4')
							&& (str.get(i).charAt(1) != '5') && (str.get(i).charAt(1) != '6')
							&& (str.get(i).charAt(1) != '7') && (str.get(i).charAt(1) != '8')
							&& (str.get(i).charAt(1) != '9') && (str.get(i).charAt(1) != 'J')
							&& (str.get(i).charAt(1) != 'Q') && (str.get(i).charAt(1) != 'K')))
						return false;
					else if ((str.get(i).length() == 3)
							&& ((str.get(i).charAt(1) != '1') && (str.get(i).charAt(1) != '0')))
						return false;
				}
			}
			// Checks if card has already been entered
			if (str.get(i).length() > 1) {
				if (!temp.remove(str.get(i))) {
					System.out.println("Invalid Card, or card has already been played");
					return false;
				}
			}
		}
		
		return true;
	}

	// Initialises the player and dealer hands, checks if either has won at this stage
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
		
		System.out.println ("Your hand is " + player.toString());
		
		if (player.getValue() > 21) {
			dealerWins();
		} else if (dealer.getValue() > 21) {
			playerWins();
		} else if (dealer.getValue() == 21) {
			dealerWins();
		} else if (player.getValue() == 21) {
			playerWins();
		}
	}
	
	// Continues the game for the player, allowing them to Hit or Stand
	public void next(String str) {
		if (!isGameOver()) {
			if (str.equals("H")) {
				Card card;
				if (consoleMode)
					card = deck.getTopCard();
				else
					card = new Card(file.removeFirst());
				System.out.println("You drew " + card.toString());
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
	}

	// add a card to a player, ends player turn if they got a blackjack or bust
	private void addToHand(Hand player2, Card card) {
		card.faceUp();
		player2.add(card);
		
		if (player2.getValue() == 21) {
			playerEnd(1);
		} else if (player2.getValue() > 21) {
			playerEnd(2);
		}
		
		System.out.println("Your hand is " + player2.toString());
	}

	// player ends turn, allowing the dealer to start playing
	private void playerEnd(int i) {
		if (i == 0) {
			System.out.println("The player stands with value " + player.getValue());
			System.out.println("The player's hand is " + player.getCards());
			System.out.println("Dealers turn");
			nextDealer();
		}

		else if (i == 1) {
			System.out.println("The player has BlackJack with value " + player.getValue());
			System.out.println("The player's hand is " + player.getCards());
			playerWins();
		}

		else if (i == 2) {
			System.out.println("The player busts with value " + player.getValue());
			System.out.println("The player's hand is " + player.getCards());
			dealerWins();
		}
		
		playerEnd = true;
	}
	
	// Allows the dealer to play till game is over
	public void nextDealer() {
		
		if (!isGameOver()) {
			if (!dealer.getCard(0).isVisible())
				dealer.makeVisible();

			int value = dealer.getValue();

			// dealer wins if it has blackjack
			if (value == 21) {
				System.out.println("The dealer has Black Jack with value " + dealer.getValue());
				System.out.println("The dealer's hand is " + dealer.getCards());
				dealerWins();
			}

			// dealer hits if value less than 16, or is equal to soft 17
			else if ((value <= 16) || ((value == 17) && (dealer.containsRank("Ace")))) {
				Card card;

				if (consoleMode) {
					card = deck.getTopCard();
					card.faceUp();
				} else {
					card = new Card(file.removeFirst());
					card.faceUp();
				}

				System.out.println("Dealer hits and draws " + card.toString());
				dealer.add(card);
				nextDealer();
			}

			// dealer busts if value greater than 21
			else if (value > 21) {
				System.out.println("The player busts with value " + dealer.getValue());
				System.out.println("The dealer's hand is " + dealer.getCards());
				playerWins();
			}

			// dealer stands, and higher score wins
			else {
				System.out.println(
						"The dealer's stands and has " + dealer.getCards() + " with a value of " + dealer.getValue());
				if (player.getValue() > dealer.getValue()) {
					System.out.println("The player has a greater value");
					playerWins();
				} else {
					if (player.getValue() == dealer.getValue())
						System.out.println("The player and dealer have same value");
					else
						System.out.println("The dealer has a greater value");
					dealerWins();
				}
			}
		}

	}
	
	// dealer wins, booleans are set, and score is printed
	public void dealerWins() {
		playerWins = false;
		dealerWins = true;
		System.out.println("\nDealer wins. Hand: " + dealer.toString() + "\nScore: " + dealer.getValue());
		System.out.println("\nPlayer loses. Hand: " + player.toString() + "\nScore: " + player.getValue());
		endGame();
	}
	
	// player wins, booleans are set, and score is printed
	public void playerWins() {
		dealerWins = false;
		playerWins = true;
		System.out.println("Player wins! Hand: " + player.toString() + "\nScore: " + player.getValue());
		System.out.println("Dealer loses. Hand: " + dealer.toString() + "\nScore: " + dealer.getValue());
		endGame();
	}

	// game ends
	public void endGame() {
		System.out.println("You have finished playing the game, thanks!\n");
		gameEnded = true;
		//System.exit(0);
	}
	
	// returns gameEnded
	public boolean isGameOver() {
		return gameEnded;
	}

	// prompt for file path and returns a file
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

	
	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		Scanner user = new Scanner(System.in);
		
		System.out.println("Would you like to continue in console (c) mode or file (f) mode?");
		while (true) {
			String choice = user.nextLine();
			if (choice.equals("f")) {
				game.fileMode(game.promptForFile(user));
				break;
			}
			else if (choice.equals("c")) {
				game.consoleMode();
				break;
			}
			else {
				System.out.println("Invalid input, please type in c or f");
			}
		}
		
		game.begin();

		if (game.consoleMode) {
			while ((!game.isPlayerDone()) && (!game.isGameOver())) {
				System.out.println("Would you like to Hit (H) or Stand (S)");
				String str = user.nextLine();
				if (str.equals("H") || str.equals("S")) {
					game.next(str);
				}
			}
		} else {
			while ((!game.isPlayerDone()) && (!game.isGameOver())) {
				game.next("N");
			}
		}

		user.close();
	}

	
}