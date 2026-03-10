package edu.kennesaw.crazy8s.game;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Rank;
import edu.kennesaw.crazy8s.domain.Suit;
import edu.kennesaw.crazy8s.player.Player;

// Responsible for orchestrating turn actions including playing cards, drawing cards, and choosing suites.
// The exact implementation of these actions is determined by the implementation of the current player
public class TurnAction {

    // Displays information relevant to the turn to the player
    public static void displayTurnStats(GameContext gameContext, TurnContext turnContext){
        System.out.println("Turn #" + gameContext.getTurnNumber() + ":");
        System.out.println("The top card is : " + turnContext.getTopDiscard());
        System.out.println("The current rank to match is: " + turnContext.getCurrentRank());
        System.out.println("The current Suit to match is: " + turnContext.getCurrentSuit());
        System.out.println("It's " + turnContext.getCurrentPlayer().getName() + "'s turn");
        System.out.println("There are " + gameContext.getRemainingDeckSize() + " cards left in the deck");
    }

    // The player begins their turn
    public static void takeTurn(GameContext gameContext, TurnContext turnContext){
        Player currentPlayer = turnContext.getCurrentPlayer();

        currentPlayer.showAllCards();
        selectCard(gameContext,turnContext);
    }

    // The player selects a card, and TurnAction handles if the player has no cards to play
    public static void selectCard(GameContext gameContext, TurnContext turnContext){

        Player currentPlayer = turnContext.getCurrentPlayer();
        Card selectedCard = currentPlayer.playCard(turnContext);

        if (selectedCard == null){
            Card drawnCard = drawCard(gameContext, turnContext);
            if (drawnCard == null){
                return;
            }
            selectedCard = drawnCard;
        }

        currentPlayer.discard(selectedCard);
        gameContext.getDiscardPile().addCard(selectedCard);
        System.out.println(currentPlayer.getName() + " played the " + selectedCard);

        if (selectedCard.getRank() == Rank.EIGHT){

            turnContext.setCurrentSuit(currentPlayer.chooseSuit());
            gameContext.getDiscardPile().setCurrentSuit(turnContext.getCurrentSuit());
            System.out.println(currentPlayer.getName() + " has changed the suit to match to " + turnContext.getCurrentSuit());
        }
        else{
            gameContext.getDiscardPile().setCurrentSuit(selectedCard.getSuit());
            turnContext.setCurrentSuit(selectedCard.getSuit());
        }
    }

    // If the player has no cards to play, have them draw a card
    public static Card drawCard(GameContext gameContext, TurnContext turnContext){

        Player currentPlayer = turnContext.getCurrentPlayer();
        String playerName = currentPlayer.getName();

        Card drawnCard = gameContext.getGameDeck().drawCard();

        currentPlayer.drawCard(drawnCard);
        System.out.println(playerName + " drew the " + drawnCard);

        if (!drawnCard.matches(turnContext)){
            System.out.println(playerName + " cannot play the card they just drew.");
            return null;
        }

        Card playedCard = currentPlayer.playDrawnCard(drawnCard, turnContext);

        if (playedCard == null){
            System.out.println(playerName + " has not played the card that they just drew.");
            return null;

        }
        else{
            return playedCard;
        }
    }
}
