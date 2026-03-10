package edu.kennesaw.crazy8s.player;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Suit;
import edu.kennesaw.crazy8s.game.TurnContext;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends PlayerBase {

    private final Scanner humanScan = new Scanner(System.in);

    public HumanPlayer(String name) {

        super(name);
    }

    @Override
    public Card playCard(TurnContext turnContext) {

        List<Card> playableCards = filterPlayableCards(turnContext);

        // Handles if the user has no playable cards.
        if (playableCards.isEmpty()) {
            System.out.println("You have no playable cards. You will have to draw a card");
            return null;
        }

        // Show all playable cards
        showPlayableCards(turnContext, playableCards);

        // Have the user input a card to play, handle invalid inputs
        while (true) {

            try {
                System.out.println();
                System.out.print("Please select a card to play (input 0 to draw a card instead): ");
                String userString = humanScan.nextLine();
                int userChoice = Integer.parseInt(userString);

                // Check that the use input a valid option
                if (userChoice < 0 || userChoice > playableCards.size()) {
                    System.out.println("Input a number within the playable cards! Try again");
                }
                else if (userChoice == 0){
                    System.out.println("You have chosen to draw a card instead of playing a card.");
                    return null;
                }
                else {
                    return playableCards.get(userChoice - 1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input! Try again");
            }
        }
    }

    public Card playDrawnCard(Card drawnCard, TurnContext turnContext){

        // Let the user decide if they want to play the card that they just drew

        if (drawnCard.matches(turnContext)) {
            String userChoice;
            while (true) {

                try {

                    System.out.print("Would you like to use the: " + drawnCard.toString() + " that you just pulled (y/n): ");
                    userChoice = humanScan.nextLine().toLowerCase();
                    if (userChoice.charAt(0) == 'y') {
                        return drawnCard;
                    } else if (userChoice.charAt(0) == 'n') {
                        return null;
                    } else {
                        System.out.println("Invalid input! Try again.");
                    }
                }
                catch (Exception e){
                    System.out.println("Invalid Input! Try again");
                }
            }
        }
        return null;
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
