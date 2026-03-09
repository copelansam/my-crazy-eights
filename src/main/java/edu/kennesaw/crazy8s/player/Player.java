package edu.kennesaw.crazy8s.player;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Suit;

import java.util.List;


public interface Player {

    Card playCard(Card topDiscard, Suit currentSuit);
    void drawCard(Card card);
    List<Card> getPlayerHand();
    Suit chooseSuit();
}
