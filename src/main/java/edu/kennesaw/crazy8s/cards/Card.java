package edu.kennesaw.crazy8s.cards;

import edu.kennesaw.crazy8s.domain.Rank;
import edu.kennesaw.crazy8s.domain.Suit;
import edu.kennesaw.crazy8s.game.TurnContext;

public interface Card {

    Rank getRank();
    Suit getSuit();

    // Used to check that a card is playable because it matches specific rules determined in implementation
    boolean matches(TurnContext turnContext);
}
