package edu.kennesaw.crazy8s.cardDeck;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Rank;
import edu.kennesaw.crazy8s.domain.Suit;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {

    private List<Card> discardPile;
    private Suit currentSuit;


    public DiscardPile(){
        discardPile = new ArrayList<>();
    }

    public void addCard(Card card){

        discardPile.add(card);
    }

    public Card getTopCard(){
        return discardPile.getLast();
    }

    public Suit getCurrentSuit(){
        return this.currentSuit;
    }

    public void setCurrentSuit(Suit currentSuit) {
        this.currentSuit = currentSuit;
    }
}
