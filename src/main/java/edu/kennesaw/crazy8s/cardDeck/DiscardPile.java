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

    public void addCard(Card card, Suit suit){
        discardPile.add(card);

        if (card.getRank() != Rank.EIGHT) {
            updateSuit(card.getSuit());
        }
        else{
            updateSuit(suit);
        }
    }

    public Card getTopCard(){
        return discardPile.getLast();
    }

    public void updateSuit(Suit suit){
        this.currentSuit = suit;
    }

    public Suit getCurrentSuit(){
        return this.getCurrentSuit();
    }
}
