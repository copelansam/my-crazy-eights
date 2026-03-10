package edu.kennesaw.crazy8s.cardDeck;

import edu.kennesaw.crazy8s.cards.Card;

import java.util.*;

public class Deck {

    private final Queue<Card> currentDeck;

    Deck(List<Card> cards){
        currentDeck = new ArrayDeque<>();
        currentDeck.addAll(cards);
    }


    public Card drawCard(){

        if (!currentDeck.isEmpty()) {
            return currentDeck.remove();
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
