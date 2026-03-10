package edu.kennesaw.crazy8s.game;

import edu.kennesaw.crazy8s.cardDeck.Deck;
import edu.kennesaw.crazy8s.cardDeck.DiscardPile;
import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.player.Player;
import edu.kennesaw.crazy8s.player.PlayerManager;

import java.util.List;
import java.util.Scanner;

public class CrazyEightsGame {

    private GameContext gameContext;
    private Deck deck;
    private DiscardPile discardPile;
    private final Scanner scanner = new Scanner(System.in);


    public CrazyEightsGame(PlayerManager players, Deck deck, DiscardPile discardPile){

        gameContext = new GameContext(deck, discardPile, players);
        this.deck = deck;
        this.discardPile = discardPile;


    }

    public void start(){

        PlayerManager players = gameContext.getPlayers();

        dealCards(gameContext);

        playGame(deck, discardPile, players);
        endGame(gameContext);

    }

    private void dealCards(GameContext gameContext){

        List<Player> players = gameContext.getPlayers().getPlayers();
        // Gives each player 5 cards
        for (int i = 0; i < 5; i++){
            Card card = deck.drawCard();
            players.getFirst().drawCard(card);
            card = deck.drawCard();
            players.getLast().drawCard(card);
        }

        // Puts a card in the discard pile to start the game
        Card firstDiscard = deck.drawCard();
        discardPile.addCard(firstDiscard);
        discardPile.setCurrentSuit(firstDiscard.getSuit());

    }

    private void playGame(Deck deck, DiscardPile discardPile, PlayerManager players){

        while(!deck.isEmpty() && players.checkSmallestHand() > 0){
            gameContext.incrementTurnNumber();
            Player currentPlayer = players.getCurrentPlayer();
            TurnContext turnContext = createTurnContext(currentPlayer);
            TurnAction.displayTurnStats(gameContext,turnContext);
            currentPlayer.takeTurn(gameContext, turnContext);
            players.playerChange();
            System.out.println("Press anything to continue");
            scanner.nextLine();
        }
    }

    private void endGame(GameContext gameContext){

        PlayerManager players = gameContext.getPlayers();
        List<Player> winners = players.getPlayersWithSmallestHands();

        // If the deck is empty find which player has the least cards, handle ties
        if (gameContext.isDeckEmpty()){
            if (winners.size() == 1) {
                System.out.println(winners.getFirst().getName() + " has the least number of cards remaining and wins the game!");
            }
            else{
                System.out.println("Both players have the same number of cards. The game is a Tie!");
            }
        }
        // Otherwise, someone used all of their cards, find out who and declare them the victor
        else{
            System.out.println(winners.getFirst().getName() + " used all of the cards in their deck. They win!");
        }

    }


    public TurnContext createTurnContext(Player currentPlayer){
        return new TurnContext(discardPile.getCurrentSuit(), discardPile.getTopCard(), gameContext, currentPlayer);
    }

}
