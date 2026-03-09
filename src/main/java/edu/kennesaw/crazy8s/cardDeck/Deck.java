package edu.kennesaw.crazy8s.cardDeck;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.cards.StandardCard;
import edu.kennesaw.crazy8s.domain.Rank;
import edu.kennesaw.crazy8s.domain.Suit;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> currentDeck;

    public Deck(){
        // Creates all 52 cards by cycling through all the ranks & suits
        currentDeck = new ArrayList<>();
        Suit[] suits = Suit.values();
        Rank[] ranks = Rank.values();

        for (Suit suit: suits){
            for (Rank rank: ranks){
                currentDeck.add(new StandardCard(rank,suit));
            }
        }

        // shuffles the deck to make it randomized
        shuffle();
    }

    public void shuffle(){
        Collections.shuffle(currentDeck);
    }

    public Card drawCard(){

        if (!currentDeck.isEmpty()) {
            return currentDeck.removeFirst();
        }
        else{
            return null;
        }

    }

    public boolean isEmpty(){
        return currentDeck.isEmpty();
    }

    public int size(){
        return currentDeck.size();
    }

}
