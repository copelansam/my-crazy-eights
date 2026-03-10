package edu.kennesaw.crazy8s.game;

import edu.kennesaw.crazy8s.cardDeck.Deck;
import edu.kennesaw.crazy8s.cardDeck.DiscardPile;
import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.player.PlayerManager;

public class GameContext {
    private final Deck gameDeck;
    private final DiscardPile discardPile;
    private int turnNumber;
    private PlayerManager players;

    GameContext(Deck gameDeck, DiscardPile discardPile, PlayerManager players){
        this.gameDeck = gameDeck;
        this.discardPile = discardPile;
        this.players = players;
        turnNumber = 0;
    }

    public Deck getGameDeck() {
        return gameDeck;
    }

    public Card drawCard(){
        return getGameDeck().drawCard();
    }

    public int getRemainingDeckSize(){
        return getGameDeck().size();
    }

    public boolean isDeckEmpty(){
        return getGameDeck().isEmpty();
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void incrementTurnNumber(){
        turnNumber++;
    }

    public PlayerManager getPlayers() {
        return players;
    }

}
