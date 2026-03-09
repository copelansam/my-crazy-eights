package edu.kennesaw.crazy8s.cards;

import edu.kennesaw.crazy8s.domain.Rank;
import edu.kennesaw.crazy8s.domain.Suit;

public interface Card {

    Rank getRank();
    Suit getSuit();

    // Checks that a card matches the top card's rank, current suit, or is a crazy eight
    boolean matches(Card topDiscard, Suit currentSuit);
}
