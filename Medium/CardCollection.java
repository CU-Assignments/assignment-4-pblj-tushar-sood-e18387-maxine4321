import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + symbol;
    }
}

public class CardCollection {

    private static HashMap<String, List<Card>> cardCollection = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCards(); 
        
        boolean exit = false;

        while (!exit) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Find cards by symbol");
            System.out.println("2. Display all cards");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    searchCardsBySymbol();
                    break;
                case 2:
                    displayAllCards();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the Card Collection System.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void initializeCards() {
        addCard(new Card("Hearts", "Ace"));
        addCard(new Card("Hearts", "King"));
        addCard(new Card("Spades", "Queen"));
        addCard(new Card("Spades", "Jack"));
        addCard(new Card("Diamonds", "10"));
        addCard(new Card("Clubs", "9"));
    }

    private static void addCard(Card card) {
        String symbol = card.getSymbol();

        cardCollection.putIfAbsent(symbol, new ArrayList<>());
        cardCollection.get(symbol).add(card);
    }

    private static void searchCardsBySymbol() {
        System.out.print("Enter card symbol (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();

        if (cardCollection.containsKey(symbol)) {
            List<Card> cards = cardCollection.get(symbol);
            System.out.println("\nCards of symbol " + symbol + ":");
            for (Card card : cards) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found for the symbol: " + symbol);
        }
    }

    private static void displayAllCards() {
        if (cardCollection.isEmpty()) {
            System.out.println("The card collection is empty.");
            return;
        }

        System.out.println("\nAll Cards in the Collection:");
        for (String symbol : cardCollection.keySet()) {
            System.out.println(symbol + ":");
            for (Card card : cardCollection.get(symbol)) {
                System.out.println("  " + card);
            }
        }
    }
}
