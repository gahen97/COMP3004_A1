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
	boolean dealerWins;
	boolean playerWins;
	boolean gameEnded;
	
	public BlackJack() {
		dealer = new Hand();
		player = new Hand();
		playerEnd = false;
		dealerWins = false;
		playerWins = false;
		gameEnded = false;
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
	
	public boolean isPlayerDone() {
		return playerEnd;
	}

	public boolean didPlayerWin() {
		return playerWins;
	}
	
	public boolean didDealerWin() {
		return dealerWins;
	}
	
	public void consoleMode() {
		deck = new Deck();
		deck.shuffle();
		consoleMode = true;
	}
	
	public void fileMode(File input) {
		
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
			
			if (i < 4) {
				if ((str.get(i).length() < 2) || (str.get(i).length() > 3)) {
					System.out.println("Incorrect format");
					return false;
				} else if ((str.get(i).length() == 2) || (str.get(i).length() == 3)) {
					if ((str.get(i).charAt(0) != 'S') && (str.get(i).charAt(0) != 'H') && (str.get(i).charAt(0) != 'C')
							&& (str.get(i).charAt(0) != 'D')) {
						return false;
					}
					if ((str.get(i).length() == 2) && ((str.get(i).charAt(1) != 'A') && (str.get(i).charAt(1) != '2')
							&& (str.get(i).charAt(1) != '3') && (str.get(i).charAt(1) != '4')
							&& (str.get(i).charAt(1) != '5') && (str.get(i).charAt(1) != '6')
							&& (str.get(i).charAt(1) != '7') && (str.get(i).charAt(1) != '8')
							&& (str.get(i).charAt(1) != '9') && (str.get(i).charAt(1) != 'J')
							&& (str.get(i).charAt(1) != 'Q') && (str.get(i).charAt(1) != 'K'))) {

						System.out.println("Reached2");
						return false;
					}
					else if ((str.get(i).length() == 3)
							&& ((str.get(i).charAt(1) != '1') && (str.get(i).charAt(1) != '0'))) {

						System.out.println("Reached3");
						return false;
					}
				}
			}
			if (str.get(i).length() > 1) {
				if (!temp.remove(str.get(i))) {
					System.out.println("Invalid Card, or card has already been played");
					return false;
				}
			}
		}
		
		return true;
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
	
	public void nextDealer() {
		
		if (!isGameOver()) {
			if (!dealer.getCard(0).isVisible())
				dealer.makeVisible();

			int value = dealer.getValue();

			if (value == 21) {
				System.out.println("The dealer has Black Jack with value " + dealer.getValue());
				System.out.println("The dealer's hand is " + dealer.getCards());
				dealerWins();
			}

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

			else if (value > 21) {
				System.out.println("The player busts with value " + dealer.getValue());
				System.out.println("The dealer's hand is " + dealer.getCards());
				playerWins();
			}

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
	
	public void dealerWins() {
		playerWins = false;
		dealerWins = true;
		System.out.println("\nDealer wins. Hand: " + dealer.toString() + "\nScore: " + dealer.getValue());
		System.out.println("\nPlayer loses. Hand: " + player.toString() + "\nScore: " + player.getValue());
		endGame();
	}
	
	public void playerWins() {
		dealerWins = false;
		playerWins = true;
		System.out.println("Player wins! Hand: " + player.toString() + "\nScore: " + player.getValue());
		System.out.println("Dealer loses. Hand: " + dealer.toString() + "\nScore: " + dealer.getValue());
		endGame();
	}

	public void endGame() {
		System.out.println("You have finished playing the game, thanks!\n");
		gameEnded = true;
		//System.exit(0);
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

	public boolean isGameOver() {
		return gameEnded;
	}
	
}