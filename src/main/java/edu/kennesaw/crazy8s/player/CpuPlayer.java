package edu.kennesaw.crazy8s.player;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// The CPU Player will randomly select cards and suits anytime an option is available
// The CPU isn't the brightest, but they don't let it get to them.

public class CpuPlayer extends PlayerBase{

    private Random rand;

    public CpuPlayer(){

        super("(Hopefully) Friendly CPU");
        rand = new Random();
    }

    @Override
    public Card playCard(Card topDiscard, Suit currentSuit){

        System.out.println("These are the cards that " + getName() + " can play:");

        List<Card> currentHand = getPlayerHand();
        List<Card> playable = new ArrayList<Card>();

        // Filters out the unplayable cards based on the current card on top of the discard pile & current suit
        for (Card card : currentHand) {

            if (card.matches(topDiscard, currentSuit)) {

                playable.add(card);
            }

        }

        // Handles if the CPU has no playable cards. The engine will make it draw a card
        if (playable.isEmpty()) {

            System.out.println(getName() + " has no playable cards! They'll draw a card");
            return null;
        }

        // Displays the CPUs playable cards
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

        int randInt = rand.nextInt(playable.size());
        return playable.get(randInt);

    }

    @Override
    public Suit chooseSuit(){

        Suit[] suits = Suit.values();

        int randInt = rand.nextInt(suits.length);
        return suits[randInt];

    }
}
