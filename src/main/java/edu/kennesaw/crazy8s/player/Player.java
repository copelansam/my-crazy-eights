package edu.kennesaw.crazy8s.player;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Suit;
import edu.kennesaw.crazy8s.game.GameContext;
import edu.kennesaw.crazy8s.game.TurnContext;


public interface Player {

    void takeTurn(GameContext gameContext, TurnContext turnContext);

    // Actions the user can take (implementation will vary based on whose turn it is)
    Card playCard(TurnContext context);
    Card playDrawnCard(Card drawnCard, TurnContext turnContext);
    Suit chooseSuit();

    // Handles updating the players hand based on their actions taken
    void drawCard(Card card);
    void discard(Card card);

    // Retrieve info relevant to the state of the user's hand to see if anyone won
    int getHandSize();
    boolean emptyHand();

    // Get info relevant to the user to display for turn context
    String getName();
    void showAllCards();

}
