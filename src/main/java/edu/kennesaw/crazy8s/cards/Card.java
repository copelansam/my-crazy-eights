package edu.kennesaw.crazy8s.cards;

import edu.kennesaw.crazy8s.domain.Rank;
import edu.kennesaw.crazy8s.domain.Suit;

public interface Card {

    Rank getRank();
    Suit getSuit();
    boolean matches(Card topDiscard, Suit currentSuit);
}
