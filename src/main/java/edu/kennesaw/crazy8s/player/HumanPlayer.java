package edu.kennesaw.crazy8s.player;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends PlayerBase {

    private Scanner humanScan = new Scanner(System.in);

    public HumanPlayer(String name) {

        super(name);
    }

    @Override
    public Card playCard(Card topDiscard, Suit currentSuit) {

        System.out.println("These are the cards you can play:");

        List<Card> currentHand = getPlayerHand();
        List<Card> playable = new ArrayList<Card>();

        // Filters out the unplayable cards based on the current card on top of the discard pile & current suit
        for (Card card : currentHand) {

            if (card.matches(topDiscard, currentSuit)) {

                playable.add(card);
            }

        }

        // Handles if the user has no playable cards. The engine will make the user draw a card
        if (playable.isEmpty()) {

            System.out.println("You have no playable cards! Please draw a card");
            return null;
        }

        // Displays the playable cards
        int counter = 1;
        for (Card card : playable) {

            System.out.print(counter + ": " + card.toString() + " ");

            if (card.getRank() == topDiscard.getRank()) {

                System.out.print("(Matching Rank)");
            } else if (card.getSuit() == currentSuit) {
                System.out.print("(Matching Suit)");
            } else {
                System.out.print("(CRAZY EIGHT! You'll get to pick the suit!)");
            }
            counter++;
            System.out.println();
        }

        // Have the user input a card to play, handle invalid inputs
        while (true) {

            try {
                System.out.println();
                System.out.print("Please select a card to play: ");
                String userString = humanScan.nextLine();
                int userChoice = Integer.parseInt(userString);

                if (userChoice < 1 || userChoice > playable.size()) {
                    System.out.println("Input a number within the playable cards! Try again");
                } else {
                    return playable.get(userChoice - 1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input! Try again");
            }
        }
    }

    @Override
    public Suit chooseSuit() {

        while (true) {

            // Ask user to input their desired suit
            System.out.print("Select a suit: [D] Diamond, [H] Heart, [C] Club, or [S] Spade: ");
            String userString = humanScan.nextLine().toUpperCase();

            // Check if the user input just the letter or the name of the suit & return appropriate suit
            switch (userString){
                case "D":
                case "DIAMOND":
                    return Suit.DIAMOND;
                case "H":
                case "HEART":
                    return Suit.HEART;
                case "C":
                case "CLUB":
                    return Suit.CLUB;
                case "S":
                case "SPADE":
                    return Suit.SPADE;
            }

            // Otherwise, print error message and have them try again
            System.out.println("Invalid Input! Try Again");
        }
    }
}
