package edu.kennesaw.crazy8s.player;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Suit;
import edu.kennesaw.crazy8s.game.GameContext;
import edu.kennesaw.crazy8s.game.TurnContext;

// Represents a player in the Crazy Eight Game
public interface Player {

    // Execute the players turn actions
    void takeTurn(GameContext gameContext, TurnContext turnContext);

    // Determines if the player will play a card from their hand
    Card playCard(TurnContext context);

    // Determines if the player plays the card they just drew
    Card playDrawnCard(Card drawnCard, TurnContext turnContext);

    // Lets the player select a suit in the event that they play an eight
    Suit chooseSuit();

    // Handles updating the players hand based on their actions taken
    void drawCard(Card card);

    // Remove a card from the player's hand
    void discard(Card card);

    // Retrieve the number of cards in the player's hand
    int getHandSize();

    // Check if the player's hand is empty
    boolean emptyHand();

    // Get the display name for the player
    String getName();

    // Displays all of the cards in the player's hand to help with observing game state
    void showAllCards();

}
