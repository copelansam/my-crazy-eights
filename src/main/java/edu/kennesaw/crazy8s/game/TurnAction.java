package edu.kennesaw.crazy8s.game;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Rank;
import edu.kennesaw.crazy8s.domain.Suit;
import edu.kennesaw.crazy8s.player.Player;

public class TurnAction {


    public static void displayTurnStats(GameContext gameContext, TurnContext turnContext){
        System.out.println("Turn #" + gameContext.getTurnNumber() + ":");
        System.out.println("The top card is : " + turnContext.getTopDiscard());
        System.out.println("The current rank to match is: " + turnContext.getCurrentRank());
        System.out.println("The current Suit to match is: " + turnContext.getCurrentSuit());
        System.out.println("It's " + turnContext.getCurrentPlayer().getName() + "'s turn");
        System.out.println("There are " + gameContext.getRemainingDeckSize() + " cards left in the deck");
    }

    public static void takeTurn(GameContext gameContext, TurnContext turnContext){
        Player currentPlayer = turnContext.getCurrentPlayer();

        currentPlayer.showAllCards();
        selectCard(gameContext,turnContext);

    }

    public static Card drawCard(GameContext gameContext, TurnContext turnContext){

        Player currentPlayer = turnContext.getCurrentPlayer();
        String playerName = currentPlayer.getName();

        Card drawnCard = gameContext.drawCard();

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


}
